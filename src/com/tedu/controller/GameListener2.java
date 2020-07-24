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
 * @说明 监听类，用于监听用户的操作KeyListener
 * @author 24390
 *
 */

public class GameListener2 implements KeyListener{

	private ElementManager em = ElementManager.getManager();
//	private boolean press = true;
	/**
	 * 能否通过一个集合来记录所有按下的键，如果重复触发，就直接结束
	 * 同时，第一次按下，记录到集合中，第二次判定集合中是否有
	 *     松开就直接删除集合中的记录
	 *  set集合
	 */
	private Set<Integer> set = new HashSet<Integer>();
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		int key = e.getKeyCode();
		if(set.contains(key)) {//判定集合中是否已经存在，包含此对象
			//如果包含，直接结束
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

	//松开
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		if(!set.contains(e.getKeyCode())){//如果这个不存在，就停止
			return;
		}//存在(已经按过这个按键)
		set.remove(e.getKeyCode());//移除数据
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
