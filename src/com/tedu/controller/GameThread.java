package com.tedu.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import com.tedu.element.BlueBottle;
import com.tedu.element.ElementObj;
import com.tedu.element.Obstacle;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;
import com.tedu.music.Sound;
import com.tedu.show.BeginJPanel;
import com.tedu.show.GameJFrame;
import com.tedu.show.OverJPanel;

/**
 * 游戏的主进程，用于控制游戏加载，游戏关卡，游戏运行时自动化
 *       游戏判定：游戏地图切换 资源释放和重新读取。。。
 * @author 24390
 * @继承 使用继承的方式实现多线程（一般建议使用接口实现）
 */

public class GameThread extends Thread{
	
	private ElementManager em;
	
	public GameThread() {
		em = ElementManager.getManager();
	}
	
	@Override
	public void run() {//游戏的run方法  主线程
		while(true) {
//      游戏开始前  读取进度条，加载游戏资源(场景资源)
			gameLoad();
//		游戏进行时  游戏过程中
			gameRun();
//		游戏场景结束  游戏资源回收(场景资源)
			gameOver();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	/**
	 * 游戏的加载
	 */
	private void gameLoad() {
//		ElementObj obj=new BlueBottle().createElement("x:50,y:50");
//		em.addElement(obj,GameElement.BLUEBOTTLE);
//		ElementObj obj2=new BlueBottle().createElement("x:50,y:50");
//		em.addElement(obj2,GameElement.BLUEBOTTLE);
		Sound.background();//播放bgm
//		Sound.start();
		GameLoad.loadImg();
		Random random=new Random();
		int i=random.nextInt(2);
		GameLoad.MapLoad(i);
		GameLoad.MapLoadBase(i);
		GameLoad.WallLoad(i);
//		加载主角
		GameLoad.loadPlay();//也可以带参数，单机还是2人
		GameLoad.loadPlay2();
//		加载敌人NPC等
//		全部加载完成，游戏启动
	}
	/**
	 * 游戏进行时
	 * 游戏过程中需要做的事情：1.自动化  玩家的移动，碰撞，死亡
	 *                       2.新元素的增加(NPC死亡后出现道具)
	 *                       3.暂停等等。。。
	 *  先实现主角的移动
	 */
	private long gameTime = 0L;
	private void gameRun() {
//		System.out.println("qidong");
//		long gameTime=0L;
		while(true) {//预留扩展  true可以变为变量，用于控制关卡结束等
			Map<GameElement, List<ElementObj>> all = em.getGameElements();
			List<ElementObj> obstacles = em.getElementsByKey(GameElement.OBSTACLE);//可爆物体
			List<ElementObj> walls = em.getElementsByKey(GameElement.WALL);//不可爆障碍物
			List<ElementObj> plays = em.getElementsByKey(GameElement.PLAY);//人物
			List<ElementObj> play2 = em.getElementsByKey(GameElement.PLAY2);
//			System.out.println(plays.size());
//			List<ElementObj> enemy=em.getElementsByKey(GameElement.ENEMY);
			List<ElementObj> file=em.getElementsByKey(GameElement.PLAYFILE);
			List<ElementObj> bm=em.getElementsByKey(GameElement.BOOM);

			List<ElementObj> blueB=em.getElementsByKey(GameElement.BLUEBOTTLE);
			List<ElementObj> redHead=em.getElementsByKey(GameElement.REDHEAD);
			List<ElementObj> superCard=em.getElementsByKey(GameElement.SUPERCARD);
			List<ElementObj> Pp=em.getElementsByKey(GameElement.PP);
			
//			if(plays.get(0).isLive()) {
//				System.out.println("haihuoz");
//			}
			moveAndUpdate(all, gameTime);
			
			
			ElementFunction(plays,blueB);
			ElementFunction(plays,redHead);
			ElementFunction(plays,superCard);
			ElementFunction(plays,Pp);
			ElementFunction(play2,blueB);
			ElementFunction(play2,redHead);
			ElementFunction(play2,superCard);
			ElementFunction(play2,Pp);
			ElementCS(obstacles,plays);
			ElementCS(walls,plays);
			ElementCS(obstacles,play2);
			ElementCS(walls,play2);
			ElementPK(bm,walls);
			ElementPK2(bm,obstacles);
			ElementPK3(plays,bm);
			ElementPK3(play2,bm);
//			ElementPK(obstacles,plays);
//			ElementPK(walls,plays);
			
			gameTime++;//唯一的时间控制
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	//人物触碰道具
		public void ElementFunction(List<ElementObj> listA,List<ElementObj>listB) {
			for(int i=0;i<listA.size();i++) {
				ElementObj boom=listA.get(i);
				for(int j=0;j<listB.size();j++) {
					ElementObj function=listB.get(j);
					if(boom.pk(function)) {
						Sound.get();
						switch(function.getCharacter()) {
						case "蓝药水":boom.setAttack(boom.getAttack()+1);break;
						case "蓝泡泡":boom.setBoomNum(boom.getBoomNum()+1);break;
						case "超人卡":boom.setLife(2);function.setCrash(false);break;
//						case "红骷髅":break;
						case "红骷髅":boom.setDirection(boom.getDirection()*(-1));break;
						}
//						System.out.println("炸弹威力增加一层");
						
						function.setLive(false);
						break;
					}
				}
			}
		}
	
	
		//play和地图碰撞会无法穿越
		 public void ElementCS(List<ElementObj> listA,List<ElementObj> listB) {
		   
		   //2个对象不可穿越
		   for(int i=listA.size()-1;i>=0;i--) {
		    ElementObj map=listA.get(i);
		    for(int j=listB.size()-1;j>=0;j--) {
		     ElementObj play=listB.get(j);
		     if(!map.pk(play)) {
		      continue;
		     }
		     if(play.getDirection()==1) {
		      switch(play.getFx()) {
		      case "left":play.setX(play.getX()+1);break;
		      case "right":play.setX(play.getX()-1);break;
		      case "up":play.setY(play.getY()+1);break;
		      case "down":play.setY(play.getY()-1);break;
		      }
//		     System.out.println("碰到了"+"x:"+play.getX()+","+"y:"+play.getY());
		     }
		     else if(play.getDirection()==-1) {
		      switch(play.getFx()) {
		      case "right":play.setX(play.getX()+1);break;
		      case "left":play.setX(play.getX()-1);break;
		      case "down":play.setY(play.getY()+1);break;
		      case "up":play.setY(play.getY()-1);break;
		      }
		     }
		    }
		   }
		  }
	
//	水花和不可爆炸碰撞
	public void ElementPK(List<ElementObj> listA, List<ElementObj> listB) {
//		System.out.println("diaoy");
//		循环，一对一判定，如果为真，就设置2个对象的死亡状态
		for(int i=0;i<listA.size();i++) {
//			System.out.println("i"+i);
			ElementObj boom=listA.get(i);
			for(int j=0;j<listB.size();j++) {
				int maxB=listB.size()-1;
//				System.out.println("j"+j);
				ElementObj obj=listB.get(j);
				int distanceX=Math.abs(boom.getX()-obj.getX());
				int distanceY=Math.abs(boom.getY()-obj.getY());
//				System.out.println("boom:x:"+boom.getX()+";y:"+boom.getY());
//				System.out.println("obj:x:"+obj.getX()+";y:"+obj.getY());
				if(distanceX==0)
					{int x=0;}
//				System.out.println("存在distancex为0");
//				System.out.println(distanceX+":"+distanceY);
//				System.out.println(boom.getX()+":"+boom.getY());
				if(distanceX==0&&distanceY==0) {
//					System.out.println("水花与不可爆炸物重叠");
					return;
				}
				
				else if(distanceY==0) {
//					System.out.println("Y相等"+distanceX);
					if(distanceX/50<=(boom.getAttack()+1)) {//攻击范围大于等于距离
						if(boom.getX()<obj.getX()) {//右边有物体
							if((distanceX/50-2)<boom.getAttackright()) {//距离右边障碍物距离小于右攻击水柱
							boom.setAttackright(distanceX/50-2); //需要注意水花的攻击范围是attack+1
//							System.out.println("右边改了"+boom.getAttackright());
							}
						}
						else {//左边有物体
							if((distanceX/50-2)<boom.getAttackleft()) {//距离左边障碍物距离小于左攻击水柱
							boom.setAttackleft(distanceX/50-2); //需要注意水花的攻击范围是attack+1
//							System.out.println("左边边改了");
							}
						}	
					}
					else
						continue;
				}
				else if(distanceX==0) {
//					System.out.println("X相等");
					if(distanceY/50<=(boom.getAttack()+1)) {
						if(boom.getY()<obj.getY()) {
							if((distanceY/50-2)<boom.getAttackdown()) {
							boom.setAttackdown(distanceY/50-2); //需要注意水花的攻击范围是attack+1
//							System.out.println("下边改了");
							}
						}
						else {
							if((distanceY/50-2)<boom.getAttackup()) {
								boom.setAttackup(distanceY/50-2); //需要注意水花的攻击范围是attack+1
								}
							
						}	
					}
					else
						continue;
				}
				
			}
		}
	}
	
//	水花和人物碰撞
	public void ElementPK3(List<ElementObj> listA, List<ElementObj> listB) {
//		循环，一对一判定，如果为真，就设置2个对象的死亡状态
		for(int i=0;i<listA.size();i++) {
			ElementObj play=listA.get(i);//角色
			int live=play.getLife();
			for(int j=0;j<listB.size();j++) {
				ElementObj obj=listB.get(j);//水花
				int distanceX=Math.abs(play.getX()-obj.getX());
				int distanceY=Math.abs(play.getY()-obj.getY());
				if (distanceX < 43) {
					if (distanceY <= 50*(obj.getAttack()+2) ) {
						if(play.getLife() >0)
						{
							if (play.getLife()>1 && obj.getCrash()==false) {
								Sound.crash();
							}
							if (obj.getCrash()==false) {
								play.setLife(live-1);
								if (play.getLife()<=0) {
									play.setLive(false);
									GameJFrame gj = new GameJFrame();
									
									OverJPanel oj = new OverJPanel();		
									gj.setjPanel(oj);

									
									gj.start();
									play.isLive();
								}
								obj.setCrash(true);
							}
							
							continue;
						}
						
					}
				}
				
				else if(distanceY < 43) {
					if (distanceX <= 50*(obj.getAttack()+2)) {
						if (play.getLife() >0) {
							if (play.getLife()>1 && obj.getCrash()==false) {
								Sound.crash();
							}
							if (obj.getCrash()==false) {
								play.setLife(live-1);
								if (play.getLife()<=0) {
									play.setLive(false);
									GameJFrame gj = new GameJFrame();
									
									OverJPanel oj = new OverJPanel();		
									gj.setjPanel(oj);

									
									gj.start();
									play.isLive();
								}
								obj.setCrash(true);
							}
							
							continue;
						}
						
					}
				}
			}
		}
	}
	
//	水花和可爆炸碰撞
	public void ElementPK2(List<ElementObj> listA, List<ElementObj> listB) {
		for(int i=0;i<listA.size();i++) {
			ElementObj boom=listA.get(i);
			for(int j=0;j<listB.size();j++) {
				ElementObj obj=listB.get(j);
				int maxB=listB.size()-1;
				int distanceX=Math.abs(boom.getX()-obj.getX());
				int distanceY=Math.abs(boom.getY()-obj.getY());
				if(distanceX==0)
					{
						int x=0;
					}
//				System.out.println("存在distancex为0");
				if(distanceX==0&&distanceY==0) {
					return;
				}
				
				else if(distanceY==0) {
//					System.out.println("Y相等"+distanceX);
					if(distanceX/50<=(boom.getAttack()+1)) {//攻击范围大于等于距离
						if(boom.getX()<obj.getX()) {//右边有物体
							if((distanceX/50-2)<boom.getAttackright()) {//距离右边障碍物距离小于右攻击水柱
							boom.setAttackright(distanceX/50-1); //需要注意水花的攻击范围是attack+1
//							System.out.println("getAttackright:"+boom.getAttackright());
							if (obj.getX()<=boom.getX()+50*(boom.getAttackright()+1)) {
								obj.setLive(false);
								obj.isLive();
								Obstacle obs=new Obstacle();
								obs.layObject(obj.getX(),obj.getY());
							}
							
//							System.out.println("右边改了"+boom.getAttackright());
							}
						}
						else {//左边有物体
							if((distanceX/50-2)<boom.getAttackleft()) {//距离左边障碍物距离小于左攻击水柱
							boom.setAttackleft(distanceX/50-1); //需要注意水花的攻击范围是attack+1
							int t=boom.getAttackleft()+1;
							if (obj.getX()>= boom.getX()-50*t ) {
								if (obj==listB.get(maxB)) {
									obj.setLive(false);
									obj.isLive();
									Obstacle obs=new Obstacle();
									obs.layObject(obj.getX(),obj.getY());
								}
								else if (listB.get(j+1).getX()>boom.getX() || listB.get(j+1).getY()>boom.getY()) {
									obj.setLive(false);
									obj.isLive();
									Obstacle obs=new Obstacle();
									obs.layObject(obj.getX(),obj.getY());
									}
								
								}	
							}
						}	
					}
					else
						continue;
				}
				else if(distanceX==0) {
//					System.out.println("X相等");
					if(distanceY/50<=(boom.getAttack()+1)) {//攻击范围大于等于距离
						if(boom.getY()<obj.getY()) {//下边有物体
							if((distanceY/50-2)<boom.getAttackdown()) {
							boom.setAttackdown(distanceY/50-1); //需要注意水花的攻击范围是attack+1
							obj.setLive(false);
							obj.isLive();
							Obstacle obs=new Obstacle();
							obs.layObject(obj.getX(),obj.getY());
							}
						}
						else {//上边有物体
							if((distanceY/50-2)<boom.getAttackup()) {
							boom.setAttackup(distanceY/50-1); //需要注意水花的攻击范围是attack+1
//							System.out.println("getattackup:"+boom.getAttackup());
							int t=boom.getAttackup()+1;
							if (obj.getY() >= boom.getY()-50*t) {
								int count=0;
								if (obj==listB.get(maxB)) {
									obj.setLive(false);
									obj.isLive();
									Obstacle obs=new Obstacle();
									obs.layObject(obj.getX(),obj.getY());
								}
								else {
									for(int k=j+1;listB.get(k).getY()<=boom.getY() && k<maxB;k++) {//遍历当前可爆物以后、水花以前的所有的可爆物，
//									若水花上方物体下方没有物体，才碰撞							
									if (listB.get(k).getX()==boom.getX()) {
										count++;//若物体与水花之间有物体，count++
									}
//									System.out.println("count:"+count);
								}
								if (count!=0) {
									continue;
								}
								else if (count==0) {
									obj.setLive(false);
									obj.isLive();
									Obstacle obs=new Obstacle();
									obs.layObject(obj.getX(),obj.getY());
								}
								}
								
									
							}
								
							}
						}	
					}
					else
						continue;
				}
				
			}
		}
	}
	
//	游戏元素自动化方法
	public void moveAndUpdate(Map<GameElement, List<ElementObj>> all, long gameTime) {
//		System.out.println("model动了");
//		GameElement.values();//隐藏方法，返回值是一个数组，数组的顺序就是定义枚举的顺序
		
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list = all.get(ge);
//			编写这样直接操作集合数据的代码建议不要使用迭代器即foreach
			for(int i=list.size()-1; i>=0;i--) {
				ElementObj obj = list.get(i);
				if(!obj.isLive()) {
					//启动一个死亡方法(方法中可以做很多事情，例如死亡动画，掉装备)
					obj.die();
					list.remove(i);
					continue;
				}
				obj.model(gameTime);//调用每个类的自己的show方法完成自己的显示
//				System.out.println("model动了");
			}
		}
	}
	
	/**
	 * 游戏切换关卡
	 */
	private void gameOver() {
		
	}
	

}
