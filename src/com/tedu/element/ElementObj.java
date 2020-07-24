package com.tedu.element;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/*
 * @˵�� ����Ԫ�صĻ���
 * 
 * 
 */
public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
	
	private boolean live=true;//����״̬ true������  false��������
//	���Բ���ö��ֵ���������(���棬�����������޵�)
//	�����¶���һ�������ж�״̬�ı�������Ҫ˼����1.��ʼ��  2.ֵ�ĸı�  3.ֵ���ж�
	
	private int life=1;//���������ֵ
	private int attack;//������ 
	private String character;//���ߵ�����
	private int boomNum=1;//���ڿ���ը������
	private int direction=1;//����
	private int attackup=0;//�Ϲ���ˮ��
	private int attackdown=0;//�¹���ˮ��
	private int attackleft=0;//�󹥻�ˮ��
	private int attackright=0;//�ҹ���ˮ��
	private boolean crash = false;
	public boolean getCrash() {
		return crash;
	}
	public void setCrash(boolean crash) {
		this.crash = crash;
	}
	public ElementObj() {//�����ã�ֻΪ�̳в������д
		
	}
/**
 * �������Ĺ��췽�������������ഫ�����ݵ�����
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
	 * ���󷽷�����ʾԪ��
	 * g ���� ���ڽ��л滭
	 */
	
	/**
	 * ֻҪ��VO�࣬��Ҫ����set��get����
	 * @param g
	 */
	public abstract void showElement(Graphics g);
	
	/**
	 * ʹ�ø��ඨ����ռ����¼��ķ���
	 * ֻ����Ҫʵ�ּ��̼��������࣬��д���������Լ������
	 * ��ʽ2ʹ�ýӿڵķ�ʽ��ʹ�ýӿڷ�ʽ��Ҫ�ڼ������������ת��
	 * bl �������  trueΪ���£�falseΪ�ɿ�
	 * key �������ļ��̵�codeֵ
	 * 
	 */
	public void keyClick(boolean bl, int key) {
		
	}
	/**
	 * �ƶ�����;��Ҫ�ƶ������࣬����д�������
	 */
	protected void move() {
		
	}
	/**
	 * ���ģʽ  ģ��ģʽ;��ģ��ģʽ�ж��� ����ִ�з������Ⱥ�˳��,������ѡ������д����
	 *       1.�ƶ�  2.��װ  3.�ӵ�����  
	 */
	public final void model(long gameTime) {
//		�Ȼ�װ�����ƶ����ٷ����ӵ�
		updateImage(gameTime);
		move();
		add(gameTime);
	}
	protected void updateImage(long gameTime) {}
	protected void add(long gameTime) {}
	
//	��������  ������̳е�
	public void die() {// ����Ҳ��һ������
		
	}
	
	public ElementObj createElement(String str) {

		return null;
	}
	public Rectangle getRectangle() {
//		���Խ�������ݽ��д���
		return new Rectangle(x, y, w, h);
	}
	/**
	 * ��ײ����
	 * һ����this����  һ���Ǵ���ֵobj
	 * @param obj
	 * @return boolean ����true˵����ײ  ����flase ˵��û��ײ
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









