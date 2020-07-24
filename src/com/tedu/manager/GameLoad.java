package com.tedu.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;

import com.tedu.element.BlueBottle;
import com.tedu.element.ElementObj;
import com.tedu.element.MapBase;
import com.tedu.element.MapObj;
import com.tedu.element.Obstacle;
import com.tedu.element.Wall;


/**
 * �����������ߣ��û���ȡ�����ļ��Ĺ��ߣ������࣬����ṩ��static����
 * @author 24390
 *
 */

public class GameLoad {
//	�õ���Դ������
	private static ElementManager em = ElementManager.getManager();

	public static Map<String, ImageIcon> imgMap = new HashMap<>();
	public static Map<String, List<ImageIcon>> imgMaps;
	
//	�û���ȡ�ļ�����
	private static Properties pro = new Properties();
	
	/**
	 * �����ͼID���ɼ��ط��������ļ������Զ����ɵ�ͼ�ļ����ƣ������ļ�
	 * @param mapId  �ļ���� �ļ�id
	 */
	
//	����ذ�
	public static void MapLoadBase(int mapId) {
		Random random=new Random();
		int j=random.nextInt(3);
		String mapName = "com/tedu/text/"+(mapId+j+1)+"base.map";// �õ��ļ�·��
//		ʹ��io������ȡ�ļ�����
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName);
		if(maps == null) {
			return;
		}
		try {
			pro.clear();
			pro.load(maps);
//			����ֱ�Ӷ�̬�Ļ�ȡ���е�key  ��key�Ϳ��Ի�ȡvalue
//			javaѧϰ��õ���ʦ��java��API�ĵ�
			Enumeration<?> names = pro.propertyNames();
			while(names.hasMoreElements()) {
				String key = names.nextElement().toString();
				String [] arrs = pro.getProperty(key).split(";");
				for(int i=0;i<arrs.length;i++) {
					ElementObj element = new MapBase().createElement(key+","+arrs[i]);
					em.addElement(element, GameElement.MAPBASE);
				}
			}
			
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
//	�������壨�ɱ���
	public static void MapLoad(int mapId) {
		String mapName = "com/tedu/text/"+(mapId+1)+"obj.map";// �õ��ļ�·��
//		ʹ��io������ȡ�ļ�����
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName);
		if(maps == null) {
			return;
		}
		try {
			pro.clear();
			pro.load(maps);
//			����ֱ�Ӷ�̬�Ļ�ȡ���е�key  ��key�Ϳ��Ի�ȡvalue
//			javaѧϰ��õ���ʦ��java��API�ĵ�
			Enumeration<?> names = pro.propertyNames();
			while(names.hasMoreElements()) {
				String key = names.nextElement().toString();
				String [] arrs = pro.getProperty(key).split(";");
				for(int i=0;i<arrs.length;i++) {
					ElementObj element = new Obstacle().createElement(key+","+arrs[i]);
					em.addElement(element, GameElement.OBSTACLE);
				}
			}
//			ElementObj blue=new BlueBottle().createElement("x:850,y:20");
//			em.addElement(blue, GameElement.BLUEBOTTLE);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	

	
	
//	�����ϰ���
	public static void WallLoad(int mapId) {
		String mapName = "com/tedu/text/"+(mapId+1)+"obs.map";// �õ��ļ�·��
//		ʹ��io������ȡ�ļ�����
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName);
		if(maps == null) {
			return;
		}
		try {
			pro.clear();
			pro.load(maps);
//			����ֱ�Ӷ�̬�Ļ�ȡ���е�key  ��key�Ϳ��Ի�ȡvalue
//			javaѧϰ��õ���ʦ��java��API�ĵ�
			Enumeration<?> names = pro.propertyNames();
			while(names.hasMoreElements()) {
				String key = names.nextElement().toString();
				String [] arrs = pro.getProperty(key).split(";");
				for(int i=0;i<arrs.length;i++) {
					ElementObj element = new Wall().createElement(key+","+arrs[i]);
					em.addElement(element, GameElement.WALL);
				}
			}
			
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ����ͼƬ  �����ͼƬ֮�����һ��·������
	 * @param args
	 */
	public static void loadImg() {//���Դ���������ͬ�ؿ���Ҫ��ͬͼƬ��Դ
		String texturl="com/tedu/text/GameData.pro";//�ļ��������Ը��й���
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
//		imgMap���ڴ������
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for(Object o:set) {
				String url = pro.getProperty(o.toString());
				imgMap.put(o.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	/**
	 * �������
	 */
	public static void loadPlay() {
		loadObj();
		String playStr="50,50,paopao";

//		Class<?> class1 = objMap.get("play");
		ElementObj obj = getObj("paopao");

		ElementObj play = obj.createElement(playStr);

//		ElementObj play = new Play().creatElement(playStr);
//		������ʹ���ʹ���֮�����϶�
		em.addElement(play, GameElement.PLAY);
	}
	
	public static void loadPlay2() {
		loadObj();
		String playStr="750,450,paopao2";

//		Class<?> class1 = objMap.get("play");
		ElementObj obj = getObj("paopao2");

		ElementObj play = obj.createElement(playStr);

//		ElementObj play = new Play().creatElement(playStr);
//		������ʹ���ʹ���֮�����϶�
		em.addElement(play, GameElement.PLAY2);
	}
	
	public static ElementObj getObj(String str) {
		try {
			Class<?> class1 = objMap.get(str);
			Object newInstance = class1.newInstance();
			if(newInstance instanceof ElementObj) {
				return (ElementObj)newInstance;  //�������ͺ�new Play�ȼ�
			}
		} catch (InstantiationException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ʹ�������ļ� ��ʵ�������� ͨ���̶���key(�ַ�����ʵ����)
	 * @param args
	 */
	private static Map<String,Class<?>> objMap = new HashMap<>();
	public static void loadObj() {
		String texturl="com/tedu/text/obj.pro";//�ļ��������Ը��й���
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for(Object o:set) {
				String classUrl=pro.getProperty(o.toString());
				Class<?> forName = Class.forName(classUrl);
				objMap.put(o.toString(), forName);
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
//	���ڲ���
	public static void main(String[] args) {
//		MapLoad(1);
//		try {
////			ͨ����·������  com.tedu.Play
//			Class<?> forName = Class.forName("");
////			ͨ������ ֱ�ӷ�����
//			Class<?> forName1 = GameLoad.class;
////			ͨ��ʵ����� ��ȡ �������
//			GameLoad gameLoad = new GameLoad();
//			Class<? extends GameLoad> class1 = gameLoad.getClass();
//		} catch (ClassNotFoundException e) {
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}
		
		
	}
	
	
	
	
}
