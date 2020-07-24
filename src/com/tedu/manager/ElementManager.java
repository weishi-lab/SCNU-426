package com.tedu.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tedu.element.ElementObj;

/**
 * @˵�� ������Ԫ�ع�������ר�Ŵ洢���е�Ԫ�أ�ͬʱ���ṩ����
 * ������ͼ�Ϳ��ƻ�ȡ����
 * @author 24390
 *@����һ���洢����Ԫ�����ݣ���ô��ţ� list map set ���󼯺�
 *@�����������������ͼ�Ϳ���Ҫ���ʣ��������ͱ���ֻ��һ��������ģʽ
 */

public class ElementManager {
	private List<Object> listMap;
	private List<Object> listPlay;
	/*
	 * String ��Ϊkey ƥ�����е�Ԫ�� play -> List<Object> listPlay
	 *                              enemy -> List<Object> listEnemy
	 * ö�����ͣ�����map��key�������ֲ�һ������Դ�����ڻ�ȡ��Դ
	 * List��Ԫ�صķ���Ӧ����Ԫ�ػ���
	 * ����Ԫ�ض����Դ�ŵ�map�����У���ʾģ��ֻ��Ҫ��ȡ�����map�Ϳ���
	 * ��ʾ���еĽ�����Ҫ��ʾ��Ԫ�أ�����Ԫ�ػ����showElement())
	 */
	private Map<GameElement, List<ElementObj>> gameElements;
	

	public Map<GameElement, List<ElementObj>> getGameElements(){
		return gameElements;
	}
	//���Ԫ�أ�����ɼ��������ã�
	public void addElement(ElementObj obj,GameElement ge) {
		//List<ElementObj> list = gameElements.get(ge);
		//list.add(obj);
		gameElements.get(ge).add(obj);//��Ӷ��󵽼����У���keyֵ���д洢
	}
	
	//����key����list���ϣ�ȡ��ĳһ��Ԫ��
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	/*
	 * ����ģʽ���ڴ�������ֻ��һ��ʵ��
	 * ����ģʽ-���������Զ�����ʵ��
	 * ����ģʽ-����Ҫʹ�õ�ʱ��ż���ʵ��
	 * 
	 * ��д��ʽ��
	 * 1.��Ҫһ����̬�����ԣ�����һ������������������
	 * 2.�ṩһ����̬�ķ������������ʵ����return ����������
	 * 3.һ��Ϊ��ֹ�������Լ�ʹ�ã����ǿ���ʵ�����������Ի�˽�л����췽��
	 *   ElementManager em=new ElementManager();
	 */
	
	private static ElementManager EM=null;//����
	// synchronized�߳���->��֤������ִ����ֻ��һ���߳�
	public static ElementManager getManager() {
		if(EM == null) {//��������
			EM = new ElementManager();
		}
		return EM;
	}
	
	private ElementManager() {//˽�л����췽��
		init();//ʵ��������
	}
//	static {//����ʵ�������� ��̬���������౻���ص�ʱ��ֱ��ִ��
//		EM = new ElementManager();//ֻ��ִ��һ��
//	}
	/*
	 * ��������Ϊ�������ܳ��ֵĹ�����չ����дinit����׼��
	 */
	
	public void init() {//ʵ�������������
		gameElements = new HashMap<GameElement, List<ElementObj>>();
//      ��ÿ��Ԫ�ؼ��϶����뵽map��
//		gameElements.put(GameElement.PLAY, new ArrayList<ElementObj>());
//		gameElements.put(GameElement.MAPS, new ArrayList<ElementObj>());
//		gameElements.put(GameElement.ENEMY, new ArrayList<ElementObj>());
//		gameElements.put(GameElement.BOSS, new ArrayList<ElementObj>());
		for(GameElement ge:GameElement.values()) {
			gameElements.put(ge, new ArrayList<ElementObj>());
		}
	}
}
