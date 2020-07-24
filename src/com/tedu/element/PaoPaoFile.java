package com.tedu.element;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;
import com.tedu.music.Sound;

public class PaoPaoFile extends ElementObj{

	private int imgx=0;
	private long imgtime=0;
	private long imgtime2=0;
	private int attack;//攻击力
	private long timeout=0;//控制炸弹爆炸时间
	private String fx;
	

	private boolean pkType=false;//false为不爆炸
	private int img=0;
	private long boomtime=0;//用于控制爆炸速度
	public PaoPaoFile() {}
//	对创建这个对象的过程进行封装，外界只需要传输必要的约定参数，返回值就是对象实体
	public ElementObj createElement(String str) {
		Sound.lay();
		String[] split = str.split(",");
		for(String str1 : split) {
			String[] split2 = str1.split(":");//0下标是x,y,f 1下标是值
			URL url = PaoPaoFile.class.getClass().getResource("image/泡泡5.png");
			ImageIcon icon = new ImageIcon("image/泡泡5.png");
			this.setIcon(icon);
			switch(split2[0]) {
			case "x": this.setX(Integer.parseInt(split2[1]));break;
			case "y": this.setY(Integer.parseInt(split2[1]));break;
			}
		}
		this.setW(112);
		this.setH(112);
		return this;
	}
	
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX()+5+9, this.getY()+5+13, 
				this.getX()+this.getW()/4+19+9, this.getY()+this.getH()/4+19+13, 
				0+(imgx*32), 8, 
				31+(imgx*32), 46, null);
//		g.setColor(Color.red);//or new Color(255,2555,255);
//		g.fillOval(this.getX()+5,this.getY()+5, 5, 5);
	}

//	动态显示 &炸弹消失
	protected void updateImage(long time) {
		timeout++;
		if (timeout>180) {
			this.setLive(false);
			this.pkType=true;
			timeout=0;
		}
		if(time-imgtime>30) {
			imgtime=time;
			imgx++;
			if(imgx>3) {
				imgx=0;
			}
		}
	}
	
	@Override   //添加爆炸效果
	public void add(long gameTime) {//有啦时间就可以进行控制
		if(!this.pkType) {//如果是不发射状态 就直接return
//			System.out.println("爆炸1");
			return;
			
		}
//		System.out.println("爆炸2");
		this.pkType=false;//按一次，发射一个子弹。拼手速(也可以增加变量来控制)
//		new PlayFile(); // 构造一个类 需要做比较多的工作  可以选择一种方式，使用小工厂
//		将构造对象的多个步骤进行封装成为一个方法，返回值直接是这个对象
//		传递一个固定格式   {X:3,y:5,f:up} json格式
		ElementObj obj=GameLoad.getObj("boom");  	
		obj.setAttack(this.getAttack());
		obj.setAttackdown(this.getAttack());
		obj.setAttackup(this.getAttack());
		obj.setAttackleft(this.getAttack());
		obj.setAttackright(this.getAttack());
		ElementObj element = obj.createElement(this.toString());
//		System.out.println("子弹是否为空"+element);
//		装入到集合中
		ElementManager.getManager().addElement(element, GameElement.BOOM);

		List<ElementObj> play=ElementManager.getManager().getElementsByKey(GameElement.PLAY);
		List<ElementObj> play2=ElementManager.getManager().getElementsByKey(GameElement.PLAY2);
		for(ElementObj ob:play) {
			ob.setBoomNum(ob.getBoomNum()+1);
		}
		for(ElementObj ob:play2) {
			ob.setBoomNum(ob.getBoomNum()+1);
		}
//		如果要控制子弹速度等等。。。。还需要代码编写
	}
	
	@Override
	public String toString() {// 这里是偷懒，直接使用toString；建议自己定义一个方法
		//  {X:3,y:5,f:up,t:A} json格式
		int x=this.getX();
		int y=this.getY();
//		switch(this.fx) { // 子弹在发射时候就已经给予固定的轨迹。可以加上目标，修改json格式
//		case "up": x+=13;y+=11;break;
//		case "down" :y+=14;x+=11;break;
//		case "left" :y+=12;x+=7;break;
//		case "right" :x+=15;y+=9;break;
//		}//个人认为： 玩游戏有助于 理解面向对象思想;不能专门玩，需要思考，父类应该怎么抽象，子类应该怎么实现
////		学习技术不犯法，但是不要用技术做犯法的事.
		return "x:"+x+",y:"+y+",f:"+this.fx;
	}
	
	@Override
	public int getAttack() {
		return attack;
	}
	
	@Override
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
}
