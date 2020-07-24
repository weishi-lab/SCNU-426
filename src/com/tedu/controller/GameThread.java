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
 * ��Ϸ�������̣����ڿ�����Ϸ���أ���Ϸ�ؿ�����Ϸ����ʱ�Զ���
 *       ��Ϸ�ж�����Ϸ��ͼ�л� ��Դ�ͷź����¶�ȡ������
 * @author 24390
 * @�̳� ʹ�ü̳еķ�ʽʵ�ֶ��̣߳�һ�㽨��ʹ�ýӿ�ʵ�֣�
 */

public class GameThread extends Thread{
	
	private ElementManager em;
	
	public GameThread() {
		em = ElementManager.getManager();
	}
	
	@Override
	public void run() {//��Ϸ��run����  ���߳�
		while(true) {
//      ��Ϸ��ʼǰ  ��ȡ��������������Ϸ��Դ(������Դ)
			gameLoad();
//		��Ϸ����ʱ  ��Ϸ������
			gameRun();
//		��Ϸ��������  ��Ϸ��Դ����(������Դ)
			gameOver();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	/**
	 * ��Ϸ�ļ���
	 */
	private void gameLoad() {
//		ElementObj obj=new BlueBottle().createElement("x:50,y:50");
//		em.addElement(obj,GameElement.BLUEBOTTLE);
//		ElementObj obj2=new BlueBottle().createElement("x:50,y:50");
//		em.addElement(obj2,GameElement.BLUEBOTTLE);
		Sound.background();//����bgm
//		Sound.start();
		GameLoad.loadImg();
		Random random=new Random();
		int i=random.nextInt(2);
		GameLoad.MapLoad(i);
		GameLoad.MapLoadBase(i);
		GameLoad.WallLoad(i);
//		��������
		GameLoad.loadPlay();//Ҳ���Դ���������������2��
		GameLoad.loadPlay2();
//		���ص���NPC��
//		ȫ��������ɣ���Ϸ����
	}
	/**
	 * ��Ϸ����ʱ
	 * ��Ϸ��������Ҫ�������飺1.�Զ���  ��ҵ��ƶ�����ײ������
	 *                       2.��Ԫ�ص�����(NPC��������ֵ���)
	 *                       3.��ͣ�ȵȡ�����
	 *  ��ʵ�����ǵ��ƶ�
	 */
	private long gameTime = 0L;
	private void gameRun() {
//		System.out.println("qidong");
//		long gameTime=0L;
		while(true) {//Ԥ����չ  true���Ա�Ϊ���������ڿ��ƹؿ�������
			Map<GameElement, List<ElementObj>> all = em.getGameElements();
			List<ElementObj> obstacles = em.getElementsByKey(GameElement.OBSTACLE);//�ɱ�����
			List<ElementObj> walls = em.getElementsByKey(GameElement.WALL);//���ɱ��ϰ���
			List<ElementObj> plays = em.getElementsByKey(GameElement.PLAY);//����
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
			
			gameTime++;//Ψһ��ʱ�����
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	//���ﴥ������
		public void ElementFunction(List<ElementObj> listA,List<ElementObj>listB) {
			for(int i=0;i<listA.size();i++) {
				ElementObj boom=listA.get(i);
				for(int j=0;j<listB.size();j++) {
					ElementObj function=listB.get(j);
					if(boom.pk(function)) {
						Sound.get();
						switch(function.getCharacter()) {
						case "��ҩˮ":boom.setAttack(boom.getAttack()+1);break;
						case "������":boom.setBoomNum(boom.getBoomNum()+1);break;
						case "���˿�":boom.setLife(2);function.setCrash(false);break;
//						case "������":break;
						case "������":boom.setDirection(boom.getDirection()*(-1));break;
						}
//						System.out.println("ը����������һ��");
						
						function.setLive(false);
						break;
					}
				}
			}
		}
	
	
		//play�͵�ͼ��ײ���޷���Խ
		 public void ElementCS(List<ElementObj> listA,List<ElementObj> listB) {
		   
		   //2�����󲻿ɴ�Խ
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
//		     System.out.println("������"+"x:"+play.getX()+","+"y:"+play.getY());
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
	
//	ˮ���Ͳ��ɱ�ը��ײ
	public void ElementPK(List<ElementObj> listA, List<ElementObj> listB) {
//		System.out.println("diaoy");
//		ѭ����һ��һ�ж������Ϊ�棬������2�����������״̬
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
//				System.out.println("����distancexΪ0");
//				System.out.println(distanceX+":"+distanceY);
//				System.out.println(boom.getX()+":"+boom.getY());
				if(distanceX==0&&distanceY==0) {
//					System.out.println("ˮ���벻�ɱ�ը���ص�");
					return;
				}
				
				else if(distanceY==0) {
//					System.out.println("Y���"+distanceX);
					if(distanceX/50<=(boom.getAttack()+1)) {//������Χ���ڵ��ھ���
						if(boom.getX()<obj.getX()) {//�ұ�������
							if((distanceX/50-2)<boom.getAttackright()) {//�����ұ��ϰ������С���ҹ���ˮ��
							boom.setAttackright(distanceX/50-2); //��Ҫע��ˮ���Ĺ�����Χ��attack+1
//							System.out.println("�ұ߸���"+boom.getAttackright());
							}
						}
						else {//���������
							if((distanceX/50-2)<boom.getAttackleft()) {//��������ϰ������С���󹥻�ˮ��
							boom.setAttackleft(distanceX/50-2); //��Ҫע��ˮ���Ĺ�����Χ��attack+1
//							System.out.println("��߱߸���");
							}
						}	
					}
					else
						continue;
				}
				else if(distanceX==0) {
//					System.out.println("X���");
					if(distanceY/50<=(boom.getAttack()+1)) {
						if(boom.getY()<obj.getY()) {
							if((distanceY/50-2)<boom.getAttackdown()) {
							boom.setAttackdown(distanceY/50-2); //��Ҫע��ˮ���Ĺ�����Χ��attack+1
//							System.out.println("�±߸���");
							}
						}
						else {
							if((distanceY/50-2)<boom.getAttackup()) {
								boom.setAttackup(distanceY/50-2); //��Ҫע��ˮ���Ĺ�����Χ��attack+1
								}
							
						}	
					}
					else
						continue;
				}
				
			}
		}
	}
	
//	ˮ����������ײ
	public void ElementPK3(List<ElementObj> listA, List<ElementObj> listB) {
//		ѭ����һ��һ�ж������Ϊ�棬������2�����������״̬
		for(int i=0;i<listA.size();i++) {
			ElementObj play=listA.get(i);//��ɫ
			int live=play.getLife();
			for(int j=0;j<listB.size();j++) {
				ElementObj obj=listB.get(j);//ˮ��
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
	
//	ˮ���Ϳɱ�ը��ײ
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
//				System.out.println("����distancexΪ0");
				if(distanceX==0&&distanceY==0) {
					return;
				}
				
				else if(distanceY==0) {
//					System.out.println("Y���"+distanceX);
					if(distanceX/50<=(boom.getAttack()+1)) {//������Χ���ڵ��ھ���
						if(boom.getX()<obj.getX()) {//�ұ�������
							if((distanceX/50-2)<boom.getAttackright()) {//�����ұ��ϰ������С���ҹ���ˮ��
							boom.setAttackright(distanceX/50-1); //��Ҫע��ˮ���Ĺ�����Χ��attack+1
//							System.out.println("getAttackright:"+boom.getAttackright());
							if (obj.getX()<=boom.getX()+50*(boom.getAttackright()+1)) {
								obj.setLive(false);
								obj.isLive();
								Obstacle obs=new Obstacle();
								obs.layObject(obj.getX(),obj.getY());
							}
							
//							System.out.println("�ұ߸���"+boom.getAttackright());
							}
						}
						else {//���������
							if((distanceX/50-2)<boom.getAttackleft()) {//��������ϰ������С���󹥻�ˮ��
							boom.setAttackleft(distanceX/50-1); //��Ҫע��ˮ���Ĺ�����Χ��attack+1
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
//					System.out.println("X���");
					if(distanceY/50<=(boom.getAttack()+1)) {//������Χ���ڵ��ھ���
						if(boom.getY()<obj.getY()) {//�±�������
							if((distanceY/50-2)<boom.getAttackdown()) {
							boom.setAttackdown(distanceY/50-1); //��Ҫע��ˮ���Ĺ�����Χ��attack+1
							obj.setLive(false);
							obj.isLive();
							Obstacle obs=new Obstacle();
							obs.layObject(obj.getX(),obj.getY());
							}
						}
						else {//�ϱ�������
							if((distanceY/50-2)<boom.getAttackup()) {
							boom.setAttackup(distanceY/50-1); //��Ҫע��ˮ���Ĺ�����Χ��attack+1
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
									for(int k=j+1;listB.get(k).getY()<=boom.getY() && k<maxB;k++) {//������ǰ�ɱ����Ժ�ˮ����ǰ�����еĿɱ��
//									��ˮ���Ϸ������·�û�����壬����ײ							
									if (listB.get(k).getX()==boom.getX()) {
										count++;//��������ˮ��֮�������壬count++
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
	
//	��ϷԪ���Զ�������
	public void moveAndUpdate(Map<GameElement, List<ElementObj>> all, long gameTime) {
//		System.out.println("model����");
//		GameElement.values();//���ط���������ֵ��һ�����飬�����˳����Ƕ���ö�ٵ�˳��
		
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list = all.get(ge);
//			��д����ֱ�Ӳ����������ݵĴ��뽨�鲻Ҫʹ�õ�������foreach
			for(int i=list.size()-1; i>=0;i--) {
				ElementObj obj = list.get(i);
				if(!obj.isLive()) {
					//����һ����������(�����п������ܶ����飬����������������װ��)
					obj.die();
					list.remove(i);
					continue;
				}
				obj.model(gameTime);//����ÿ������Լ���show��������Լ�����ʾ
//				System.out.println("model����");
			}
		}
	}
	
	/**
	 * ��Ϸ�л��ؿ�
	 */
	private void gameOver() {
		
	}
	

}
