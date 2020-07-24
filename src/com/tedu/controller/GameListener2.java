package com.tedu.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tedu.element.ElementObj;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

/**
 * @˵�� �����࣬���ڼ����û��Ĳ���KeyListener
 * @author 24390
 *
 */

public class GameListener2 implements KeyListener{

	private ElementManager em = ElementManager.getManager();
//	private boolean press = true;
	/**
	 * �ܷ�ͨ��һ����������¼���а��µļ�������ظ���������ֱ�ӽ���
	 * ͬʱ����һ�ΰ��£���¼�������У��ڶ����ж��������Ƿ���
	 *     �ɿ���ֱ��ɾ�������еļ�¼
	 *  set����
	 */
	private Set<Integer> set = new HashSet<Integer>();
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
		int key = e.getKeyCode();
		if(set.contains(key)) {//�ж��������Ƿ��Ѿ����ڣ������˶���
			//���������ֱ�ӽ���
			return;
		}
		set.add(key);
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY2);
		for(ElementObj obj:play) {
			obj.keyClick(true, e.getKeyCode());
		}
//		List<ElementObj> play2 = em.getElementsByKey(GameElement.PLAY2);
//		for(ElementObj obj:play2) {
//			obj.keyClick(true, e.getKeyCode());
//		}
//		if(press) {
//		for(ElementObj obj:play) {
//			obj.keyClick(true, e.getKeyCode());
//			press = false;
//			break;
//		}
//		}
//		else {
//			for(ElementObj obj:play) {
//				obj.keyClick(false, e.getKeyCode());
//			}
//		}
		
	}

	//�ɿ�
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO �Զ����ɵķ������
		if(!set.contains(e.getKeyCode())){//�����������ڣ���ֹͣ
			return;
		}//����(�Ѿ������������)
		set.remove(e.getKeyCode());//�Ƴ�����
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY2);
		for(ElementObj obj:play) {
			obj.keyClick(false, e.getKeyCode());
		}
//		List<ElementObj> play2 = em.getElementsByKey(GameElement.PLAY);
//		for(ElementObj obj:play2) {
//			obj.keyClick(false, e.getKeyCode());
//		}
	}

}
