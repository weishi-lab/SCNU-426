package com.tedu.element;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/*
 * @说明 所有元素的基类
 * 
 * 
 */
public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
	
	private boolean live=true;//生存状态 true代表存活  false代表死亡
//	可以采用枚举值来定义这个(生存，死亡，隐身，无敌)
//	当重新定义一个用于判定状态的变量，需要思考：1.初始化  2.值的改变  3.值的判定
	
	private int life=1;//人物的生命值
	private int attack;//攻击力 
	private String character;//道具的属性
	private int boomNum=1;//用于控制炸弹数量
	private int direction=1;//方向
	private int attackup=0;//上攻击水柱
	private int attackdown=0;//下攻击水柱
	private int attackleft=0;//左攻击水柱
	private int attackright=0;//右攻击水柱
	private boolean crash = false;
	public boolean getCrash() {
		return crash;
	}
	public void setCrash(boolean crash) {
		this.crash = crash;
	}
	public ElementObj() {//无作用，只为继承不报错而写
		
	}
/**
 * 带参数的构造方法；可以由子类传输数据到父类
 * @param x
 * @param y
 * @param w
 * @param h
 * @param icon
 */

	public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.icon = icon;
	}
	/**
	 * 抽象方法，显示元素
	 * g 画笔 用于进行绘画
	 */
	
	/**
	 * 只要是VO类，就要生成set和get方法
	 * @param g
	 */
	public abstract void showElement(Graphics g);
	
	/**
	 * 使用父类定义接收键盘事件的方法
	 * 只有需要实现键盘监听的子类，重写这个方法（约定：）
	 * 方式2使用接口的方式；使用接口方式需要在监听类进行类型转换
	 * bl 点击类型  true为按下，false为松开
	 * key 代表触发的键盘的code值
	 * 
	 */
	public void keyClick(boolean bl, int key) {
		
	}
	/**
	 * 移动方法;需要移动的子类，请重写这个方法
	 */
	protected void move() {
		
	}
	/**
	 * 设计模式  模板模式;在模板模式中定义 对象执行方法的先后顺序,由子类选择性重写方法
	 *       1.移动  2.换装  3.子弹发射  
	 */
	public final void model(long gameTime) {
//		先换装，再移动，再发射子弹
		updateImage(gameTime);
		move();
		add(gameTime);
	}
	protected void updateImage(long gameTime) {}
	protected void add(long gameTime) {}
	
//	死亡方法  给子类继承的
	public void die() {// 死亡也是一个对象
		
	}
	
	public ElementObj createElement(String str) {

		return null;
	}
	public Rectangle getRectangle() {
//		可以将这个数据进行处理
		return new Rectangle(x, y, w, h);
	}
	/**
	 * 碰撞方法
	 * 一个是this对象  一个是传入值obj
	 * @param obj
	 * @return boolean 返回true说明碰撞  返回flase 说明没碰撞
	 */
	public boolean pk(ElementObj obj) {
		return this.getRectangle().intersects(obj.getRectangle());
	}

	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public int getBoomNum() {
		return boomNum;
	}
	public void setBoomNum(int boomNum) {
		this.boomNum = boomNum;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public String getFx() {
		// TODO Auto-generated method stub
		return null;
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
	
	
}









