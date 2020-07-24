package com.tedu.element;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;
import com.tedu.music.Sound;

/**
 * @˵�� ����ӵ��࣬�����ʵ�����������Ҷ�����úʹ���
 * @author renjj
 * @����Ŀ�������
 *   1.�̳���Ԫ�ػ���;��дshow����
 *   2.��������ѡ������д�����������磺move��
 *   3.˼���������������е�����
 */
public class PlayBoom extends ElementObj{
	private int attack=0;
	private int attackup=0;//�Ϲ���ˮ��
	private int attackdown=0;//�¹���ˮ��
	private int attackleft=0;//�󹥻�ˮ��
	private int attackright=0;//�ҹ���ˮ��
	private int moveNum=0;//�ƶ��ٶ�ֵ
	private long imgtime=0;//���ڿ���ͼƬ�仯�ٶ�
	private long boomtime=0;//���ڿ��Ʊ�ը�ٶ�
	private boolean crash=false;

//	ʣ�µĴ����չ; ������չ�������ӵ��� ���⣬�����ȵȡ�(��������Ҫ���ӵ�����)
	public PlayBoom() {}//һ���յĹ��췽��
//	�Դ����������Ĺ��̽��з�װ�����ֻ��Ҫ�����Ҫ��Լ������������ֵ���Ƕ���ʵ��
	@Override   //{X:3,y:5,f:up}
	public  ElementObj createElement(String str) {//�����ַ����Ĺ���
		Sound.hit();
		String[] split = str.split(",");
		for(String str1 : split) {//X:3
			String[] split2 = str1.split(":");// 0�±� �� x,y,f   1�±���ֵ
			ImageIcon icon=GameLoad.imgMap.get("boom");
//			System.out.println("ceshi"+icon.getIconHeight());
			this.setIcon(icon);
			switch(split2[0]) {
			case "x": this.setX(Integer.parseInt(split2[1])+9);break;
			case "y":this.setY(Integer.parseInt(split2[1])+13);break;
			}
		}
		this.setW(132);
		this.setH(132);
		return this;
	}
	
	
	@Override
	 public void showElement(Graphics g) { 
	//  g.setColor(Color.red);// new Color(255,255,255)
	//  g.fillOval(this.getX(), this.getY(), this.getW(), this.getH());
	//  System.out.println(this.attackup);
	  g.drawImage(this.getIcon().getImage(), 
	    this.getX()-11-9, this.getY()-15+13, 
	    this.getX()+34+9+9, this.getY()+45+13, 
	    150, 350, 
	    190, 390, 
	    null); //middle
	  g.drawImage(this.getIcon().getImage(), 
	    this.getX()+40, this.getY()-15, 
	    this.getX()+70+(50*attackright)+9+9+12, this.getY()+30+13, 
	    160, 150, 
	    190, 190, 
	    null); //right2
	  g.drawImage(this.getIcon().getImage(), 
	    this.getX()+1+3, this.getY()-55-(50*attackup)-15+13-10-23, 
	    this.getX()+45+9+9+3,   this.getY()+13 , 
	    930, 150, 
	    970, 190, 
	    null);//up2
	//  
	  g.drawImage(this.getIcon().getImage(), 
	    this.getX()-5, this.getY()+1+13-(20*attackdown), 
	    this.getX()+35+9+9  ,   this.getY()+70+(50*attackdown)+33, 
	    730, 150, 
	    770, 190, 
	    null); //down2
	////  
	////  
	  g.drawImage(this.getIcon().getImage(), 
	    this.getX()-45-(50*attackleft)-20, this.getY()-13+3, 
	    this.getX()+15+(5*attackleft), this.getY()+31+10+4, 
	    540, 150, 
	    580, 190, 
	    null); //left2
	 }
	
	@Override
	protected void updateImage(long time) {
		if(time -imgtime>20) {
			imgtime=time;
			boomtime++;
			if(boomtime>3) {
				this.setLive(false);
				
			}
		}
		
	}
	
	@Override
	public int getAttack() {
		return attack;
	}
	
	@Override
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getAttackup() {
		return attackup;
	}
	public void setAttackup(int attackup) {
		this.attackup = attackup;
	}
	public int getAttackdown() {
		return attackdown;
	}
	public void setAttackdown(int attackdown) {
		this.attackdown = attackdown;
	}
	public int getAttackleft() {
		return attackleft;
	}
	public void setAttackleft(int attackleft) {
		this.attackleft = attackleft;
	}
	public int getAttackright() {
		return attackright;
	}
	public void setAttackright(int attackright) {
		this.attackright = attackright;
	}
	
	@Override
	public boolean getCrash() {
		// TODO �Զ����ɵķ������
		return crash;
	}
	
	@Override
	public void setCrash(boolean crash) {
		this.crash = crash;
	}
	
	
//	@Override
//	protected void move() {
//		if(this.getX()<0 || this.getX() >900 || 
//				this.getY() <0 || this.getY()>600) {
//			this.setLive(false);
//			return;
//		}
//		switch(this.fx) {
//		case "up": this.setY(this.getY()-this.moveNum);break;
//		case "left": this.setX(this.getX()-this.moveNum);break;
//		case "right": this.setX(this.getX()+this.moveNum);break;
//		case "down": this.setY(this.getY()+this.moveNum);break;
//		}
//		
//	}
	/**
	 * �����ӵ���˵��1.���߽�  2.��ײ  3.��ҷű���
	 * ����ʽ���ǣ����ﵽ����������ʱ��ֻ���� �޸�����״̬�Ĳ�����
	 */
	
//	@Override
//	public void die() {
//		ElementManager em=ElementManager.getManager();
//		ImageIcon icon=new ImageIcon("image/tank/play2/player2_up.png");
//		ElementObj obj=new Play(this.getX(),this.getY(),50,50,icon);//ʵ��������
////		��������뵽 Ԫ�ع�������
////		em.getElementsByKey(GameElement.PLAY).add(obj);
//		em.addElement(obj,GameElement.DIE);//ֱ�����
//	}

	
	
}





