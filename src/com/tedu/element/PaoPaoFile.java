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
	private int attack;//������
	private long timeout=0;//����ը����ըʱ��
	private String fx;
	

	private boolean pkType=false;//falseΪ����ը
	private int img=0;
	private long boomtime=0;//���ڿ��Ʊ�ը�ٶ�
	public PaoPaoFile() {}
//	�Դ����������Ĺ��̽��з�װ�����ֻ��Ҫ�����Ҫ��Լ������������ֵ���Ƕ���ʵ��
	public ElementObj createElement(String str) {
		Sound.lay();
		String[] split = str.split(",");
		for(String str1 : split) {
			String[] split2 = str1.split(":");//0�±���x,y,f 1�±���ֵ
			URL url = PaoPaoFile.class.getClass().getResource("image/����5.png");
			ImageIcon icon = new ImageIcon("image/����5.png");
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

//	��̬��ʾ &ը����ʧ
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
	
	@Override   //��ӱ�ըЧ��
	public void add(long gameTime) {//����ʱ��Ϳ��Խ��п���
		if(!this.pkType) {//����ǲ�����״̬ ��ֱ��return
//			System.out.println("��ը1");
			return;
			
		}
//		System.out.println("��ը2");
		this.pkType=false;//��һ�Σ�����һ���ӵ���ƴ����(Ҳ�������ӱ���������)
//		new PlayFile(); // ����һ���� ��Ҫ���Ƚ϶�Ĺ���  ����ѡ��һ�ַ�ʽ��ʹ��С����
//		���������Ķ��������з�װ��Ϊһ������������ֱֵ�����������
//		����һ���̶���ʽ   {X:3,y:5,f:up} json��ʽ
		ElementObj obj=GameLoad.getObj("boom");  	
		obj.setAttack(this.getAttack());
		obj.setAttackdown(this.getAttack());
		obj.setAttackup(this.getAttack());
		obj.setAttackleft(this.getAttack());
		obj.setAttackright(this.getAttack());
		ElementObj element = obj.createElement(this.toString());
//		System.out.println("�ӵ��Ƿ�Ϊ��"+element);
//		װ�뵽������
		ElementManager.getManager().addElement(element, GameElement.BOOM);

		List<ElementObj> play=ElementManager.getManager().getElementsByKey(GameElement.PLAY);
		List<ElementObj> play2=ElementManager.getManager().getElementsByKey(GameElement.PLAY2);
		for(ElementObj ob:play) {
			ob.setBoomNum(ob.getBoomNum()+1);
		}
		for(ElementObj ob:play2) {
			ob.setBoomNum(ob.getBoomNum()+1);
		}
//		���Ҫ�����ӵ��ٶȵȵȡ�����������Ҫ�����д
	}
	
	@Override
	public String toString() {// ������͵����ֱ��ʹ��toString�������Լ�����һ������
		//  {X:3,y:5,f:up,t:A} json��ʽ
		int x=this.getX();
		int y=this.getY();
//		switch(this.fx) { // �ӵ��ڷ���ʱ����Ѿ�����̶��Ĺ켣�����Լ���Ŀ�꣬�޸�json��ʽ
//		case "up": x+=13;y+=11;break;
//		case "down" :y+=14;x+=11;break;
//		case "left" :y+=12;x+=7;break;
//		case "right" :x+=15;y+=9;break;
//		}//������Ϊ�� ����Ϸ������ ����������˼��;����ר���棬��Ҫ˼��������Ӧ����ô��������Ӧ����ôʵ��
////		ѧϰ���������������ǲ�Ҫ�ü�������������.
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
