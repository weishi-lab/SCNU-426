package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * @author hao
 *@说明 超人卡类
 */
public class SuperCard extends ElementObj{
	private int imgx=0;
	private long imgtime=0;
	private String character="超人卡";
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), 
				this.getX()+this.getW(), this.getY()+this.getH()*2, 
				0+(imgx*32),0, 
				31+(imgx*32),49, null);
	}
	
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		for(String str1 : split) {
			String[] split2 = str1.split(":");//0下标是x,y,f 1下标是值
			ImageIcon icon = new ImageIcon("image/supercard.png");
			this.setIcon(icon);
			switch(split2[0]) {
			case "x": this.setX(Integer.parseInt(split2[1]));break;
			case "y": this.setY(Integer.parseInt(split2[1]));break;
			}
		}
		this.setW(50);
		this.setH(25);
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
	
	@Override
	public String getCharacter() {
		return character;
	}


}
