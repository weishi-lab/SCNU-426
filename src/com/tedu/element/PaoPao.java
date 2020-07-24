package com.tedu.element;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

/**
 * 1.�̳и���
 * 2.��д���ַ�����ʵ��ҵ���߼�
 * 3.��д���̵߳���ײ
 * 4.��������ļ���ʽ�иı䣬����д GameLoad����ļ��ط���
 * @author 24390
 *
 */


public class PaoPao extends ElementObj{
	
	private boolean left = false;//��
	private boolean up = false;//��
	private boolean right = false;//��
	private boolean down = false;//��
	private String fx = "down";
	private boolean pkType = false;
//	��������
	private int x=0;
	private int imgx=0;
	private long imgtime=0;//���ڿ���ͼƬ�仯�ٶ�
	private int control;//���Ƴ���
	
	private int boomNum=1;//���ڿ���ը������(������)
	private int attack=0;//������(��ҩˮ)
	private int life=2;//���������ֵ(������)
	private int direction=1;//����(������)
	@Override
	public void showElement(Graphics g) {
//		g.drawImage(this.getIcon().getImage(), 
//				this.getX(), this.getY(), 
//				this.getW(), this.getH(), null);
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(),
				this.getX()+this.getW(), this.getY()+this.getH(), 
				26+(imgx*100), 42+control, 
				72+(imgx*100), 99+control,null);
		
//		this.setW(this.getW()/16);
//		this.setH(this.getH()/16);
//		System.out.println(get);
	}
	
	@Override
	protected void updateImage(long time) {
		if(time-imgtime>30) {
			imgtime=time;
			imgx++;
			if(imgx>3) {
				imgx=0;
			}
		}
	}
	
	protected void move() {
//		��ȡ��ǰ�������ĵ����ڸ��ӵ�����
		  int x=0;
		  int y=0;
		  int num = this.getX()%50;
		  int num1 = this.getY()%50;
		  if(num < 25) {
		   x = this.getX()-num-5+this.getW()/2;
		  }
		  else {
		   x = this.getX()+50-num-5+this.getW()/2;
		  }
		  if(num1 < 25) {
		   y = this.getY()-num1-5+this.getH()/2;
		  }
		  else {
		   y = this.getY()+50-num1-5+this.getH()/2;
		  }
		  int x1=x-16;
		  int y1=y-16;
	  if (this.left && this.getX()>=0) {
	//   System.out.println("���ƶ�");
		  if (Math.abs(this.getY()-y1)<7 && Math.abs(this.getY()-y1)>0 && this.getY()>50) 
		  {
			this.setX(x1);
			this.setY(y1);
		  }

	   this.setX(this.getX() - 1*direction);
	  }
	  if (this.up && this.getY()>=0) {
	//   System.out.println("���ƶ�");
		  if (Math.abs(this.getX()-x1)<7 && Math.abs(this.getX()-x1)>0 && this.getX()>50) 
		  {
			this.setX(x1);
			this.setY(y1);
		  }
		  if (this.getX()-x1<14 && this.getX()>50 && Math.abs(this.getX()-x1)>0) {
			  this.setX(x1);
			  this.setY(y1);
		}
	   this.setY(this.getY() - 1*direction);
	  }
	  
	  if (this.right && this.getX()<=816-55) {
	//   System.out.println("���ƶ�");
		  if (Math.abs(this.getY()-y1)<7 && Math.abs(this.getY()-y1)>0 && this.getY()>50) 
		  {
			this.setX(x1);
			this.setY(y1);
		  }
	   this.setX(this.getX() + 1*direction);
	  }
	  if (this.down && this.getY()<=638-43) {
	//   System.out.println("���ƶ�");
		  if (Math.abs(this.getX()-x1)<7 && Math.abs(this.getX()-x1)>0 && this.getX()>50) 
		  {
			this.setX(x1);
			this.setY(y1);
		  }
		  if (this.getX()-x1<14 && this.getX()>50 && Math.abs(this.getX()-x1)>0) {
			  this.setX(x1);
			  this.setY(y1);
		}
	   this.setY(this.getY() + 1*direction);
	  }
	  if(this.getX()<0)
	   this.setX(0);
	  else if(this.getX()>1000)
	   this.setX(1000);
	  if(this.getY()<0)
	   this.setY(0);
	  else if(this.getY()>552)
	   this.setY(552);
	 }
	
	
	public void keyClick(boolean bl, int key) {
//		System.out.println("������");
		if(bl) {
			//����
			switch(key) {
			case 65:
				this.right = false;this.up = false;this.fx="left";
				this.down = false;this.left = true;this.control=100; 
			break;
			case 87:
				this.down = false;this.right = false;this.fx="up";
				this.left = false;this.up = true;this.control=300; 
				break;
			case 68:
				this.left = false;this.up = false;this.fx="right";
				this.down = false;this.right = true;this.control=200; 
				break;
			case 83:
				this.up = false;this.right = false;this.fx="down";
				this.left = false;this.down = true;this.control=0; 
				break;
			case 32:
				this.pkType=true;break;
			}
		}else {
//			System.out.println("�ɿ���");
			switch(key) {
			case 65:this.left = false;break;
			case 87:this.up = false;break;
			case 68:this.right = false;break;
			case 83:this.down = false;break;
			case 32:this.pkType = false;break;
			}
		}
	}
	
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		ImageIcon icon = GameLoad.imgMap.get(split[2]);
		this.setIcon(icon);
		this.setW(43);
		this.setH(43);
		return this;
	}
	
	public void add(long gameTime) {
		if(!this.pkType||this.boomNum==0) {
		
			return;
		}
//		System.out.println("youzidan");
		this.pkType=false;//��һ�η���һ���ӵ�
//		new PaoPaoFile();
//		���������Ķ��������з�װ��Ϊһ������������ֱֵ�����������
//		����һ���̶���ʽ  {X:3,y:5,f:up}
		ElementObj obj = GameLoad.getObj("file");
		obj.setAttack(this.getAttack());
		ElementObj element = obj.createElement(this.toString());
//		װ�뵽������
		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
		this.boomNum--;
	}
	
	
	public String toString() {
		int x=(this.getX()+this.getW()/2)/50*50-9;
		int y=(this.getY()+this.getH()/2+8)/50*50-13;
//		switch(this.fx) {
//		case "up": x+=20;break;
//		case "left": y+=20;break;
//		case "right": x+=50;y+=20;break;
//		case "down": y+=20;x+=18;break;
//		}
		return "x:"+x+",y:"+y+",f:"+this.fx;
	}

	@Override
	public String getFx() {
		return this.fx;
	}
	
	@Override
	public int getAttack() {
		return attack;
	}
	
	@Override
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	@Override
	public int getBoomNum() {
		return boomNum;
	}
	
	@Override
	public void setBoomNum(int boomNum) {
		this.boomNum = boomNum;
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public void setLife(int life) {
		this.life = life;
	}


	@Override
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	@Override
	 public int getDirection() {
	  return direction;
	 }
	
}




