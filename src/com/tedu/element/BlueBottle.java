package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

/**
 * @author hao
 *@说明 蓝药瓶类
 */
public class BlueBottle extends ElementObj{
	private int imgx=0;
	private long imgtime=0;
	private String character="蓝药水";//道具的属性
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
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
			ImageIcon icon = new ImageIcon("image/bluebottle.png");
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
