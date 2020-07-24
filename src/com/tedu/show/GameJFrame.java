package com.tedu.show;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @˵�� ��Ϸ���� ��Ҫʵ�ֵĹ��ܣ��رգ���ʾ�������С��
 * @����˵������ҪǶ����壬�������̵߳ȵ�
 *  @author 24390
 *  @����˵�� swing awt �����С����¼�û��ϴ�ʹ������Ĵ�����ʽ��	
 *  @���� 1.���󶨵�����
 *  	  2.������
 *        3.��Ϸ���߳�����
 *        4.��ʾ����
 *        
 *  
 *  
 */

public class GameJFrame extends JFrame{
	public static int GameX = 816;// GAMEX
	public static int GameY = 638;
	private JPanel jPanel = null;//������ʾ�����
	private KeyListener keyListener=null;
	private KeyListener keyListener2=null;
	private MouseMotionListener mouseMotionListener = null;
	private MouseListener mouseListener = null;
	private Thread thread = null;//��Ϸ���߳�
	
	public GameJFrame() {
		init();
	}
	public void init() {
		this.setSize(GameX, GameY);//���ô����С
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�����˳����ҹر�
		this.setLocationRelativeTo(null);//��Ļ������ʾ
	}
	
	/*���岼��*/
	public void addButton() {
		//this.setLayout(manager);//���ָ�ʽ��������ӿؼ�
	}
	
	
	/**
	 *�������� 
	*/
	public void start() {
		if(jPanel!=null) {
			this.add(jPanel);
		}
		if(keyListener != null) {
			this.addKeyListener(keyListener);
			this.addKeyListener(keyListener2);
		}
		if(thread!=null) {
			thread.start();//�����߳�
		}
		//�����ˢ��
		this.setVisible(true);//��ʾ����
		//���jPanel��runnable��ʵ���������
		if(this.jPanel instanceof Runnable) {
			//��������ת���ж���ǿ������ת���������
//			new Thread((Runnable)this.jPanel).start();
			Runnable run = (Runnable)this.jPanel;
			Thread th = new Thread(run);
			th.start();
		}
	}
	
	/*setע�룺ssm ͨ��set����ע�������ļ��ж�ȡ������;�������ļ�
	 * �е����ݸ�ֵΪ�������
	 * ����ע�룺��Ҫ��Ϲ��췽��
	 * spring ��ioc���ж�����Զ����ɣ�����
	 *  */
	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}
	
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	public void setKeyListener2(KeyListener keyListener) {
		this.keyListener2 = keyListener;
	}
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	
	
	
}
