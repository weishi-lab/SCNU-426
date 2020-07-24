package com.tedu.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

/**
 * @author hao
 *@说明 物体类（可炸掉）
 */
public class Obstacle extends ElementObj{
	private ElementManager em=ElementManager.getManager();
	private int imgx=0;
	private long imgtime=0;
	@Override
	public void showElement(Graphics g) {
			g.drawImage(this.getIcon().getImage(), this.getX(),this.getY(), 
					this.getW(),this.getH(), null);
	}
	
	@Override
	public ElementObj createElement(String str) {
		String []arr=str.split(",");
		ImageIcon icon=null;
		Random random=new Random();
		int i=random.nextInt(7);//0-6分别代表不同障碍
		icon = new ImageIcon("image/"+i+".png");
		this.setIcon(icon);
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		int w=icon.getIconWidth();
		int h=icon.getIconHeight();
		this.setH(h);
		this.setW(w);
		this.setX(x);
		this.setY(y);
		this.setIcon(icon);
		return this;
	}
	
	
	protected void updateImage(long time) {
		if(time-imgtime>30) {
			imgtime=time;
			imgx++;
			if(imgx>3) {
				imgx=0;
			}
		}
	}
	
	
	/**
	 * 物体被炸掉后调用此方法 掉落道具或者不掉落道具
	 */
	public void layObject(int x,int y) {
//		随机生成一个道具
		Random random=new Random();
		int j=random.nextInt(5);
		switch(j) {
			case 0:ElementObj element = new BlueBottle().createElement("x:"+x+",y:"+y);
			em.addElement(element, GameElement.BLUEBOTTLE);break;
			case 1:ElementObj element2 = new RedHead().createElement("x:"+x+",y:"+y);
			em.addElement(element2, GameElement.REDHEAD);break;
			case 2:ElementObj element3 = new Pp().createElement("x:"+x+",y:"+y);
			em.addElement(element3, GameElement.PP);break;
			case 3:ElementObj element4 = new SuperCard().createElement("x:"+x+",y:"+y);
			em.addElement(element4, GameElement.SUPERCARD);break;
			case 4:break;
		}
	}
	
	

}
