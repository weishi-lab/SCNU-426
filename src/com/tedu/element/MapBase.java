package com.tedu.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * @author hao
 *@说明 地板类
 */
public class MapBase extends ElementObj{
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
		Random random=new Random();
		int i=random.nextInt(2);
		ImageIcon icon=null;
			switch(arr[0]) {//设置图片信息 图片还未加载到内存中
			case "GREY":icon = new ImageIcon("image/floor2.png");break;
			case "GREEN":icon = new ImageIcon("image/floor1.png");break;
			case "YELLOW":icon = new ImageIcon("image/floor3.png");break;
			case "ICE":icon = new ImageIcon("image/floor4.png");break;
			}
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
	
}
