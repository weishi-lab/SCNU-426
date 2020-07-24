package com.tedu.show;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.tedu.element.ElementObj;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

/**
 * @˵�� ��Ϸ����Ҫ���
 * @author 24390
 *@����˵�� ��Ҫ����Ԫ�ص���ʾ��ͬʱ���н����ˢ�£����̣߳�
 *���߳�ˢ�� 1.����ʵ���߳̽ӿ�
 *          2.�����ж���һ���ڲ�����ʵ��
 */

public class GameMainJPanel extends JPanel implements Runnable{
//����������
	private ElementManager em;
	
	public GameMainJPanel() {
		init();
	}
	
	public void init() {
		em = ElementManager.getManager();//�õ�Ԫ�ع���������
	}
	/**
	 * paint�����ǽ��л滭Ԫ��
	 * Լ����������ִֻ��һ�Σ���ʵʱˢ����Ҫ���߳�
	 */
	
	@Override //���ڻ滭��
	public void paint(Graphics g) {
		super.paint(g);
		//map key-value key�����򲻿��ظ���
		//set ��map��keyһ�� ���򲻿��ظ�
		Map<GameElement, List<ElementObj>> all = em.getGameElements();
//		GameElement.values();//���ط���������ֵ��һ�����飬�����˳����Ƕ���ö�ٵ�˳��
		
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list = all.get(ge);
			for(int i=0; i<list.size();i++) {
				ElementObj obj = list.get(i);
//				if(ge.equals(GameElement.PLAYFILE)) {
//					System.out.println("::::::"+obj);
//				}
				obj.showElement(g);//����ÿ������Լ���show��������Լ�����ʾ
			}
		}
		
		
//		Set<GameElement> set = all.keySet();//�õ����е�key����
//		for(GameElement ge:set) {//������
//			List<ElementObj> list = all.get(ge);
//			for(int i=0; i<list.size();i++) {
//				ElementObj obj = list.get(i);
//				obj.showElement(g);//����ÿ������Լ���show��������Լ�����ʾ
//			}
//		}
	}

	@Override
	public void run() {
		while(true) {
			this.repaint();
			//һ������£����̶߳���ʹ��һ�����ߣ������ٶ�
			try {
				Thread.sleep(10);//����50����
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	
}
