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
 * 加载器（工具：用户读取配置文件的工具）工具类，大多提供的static方法
 * @author 24390
 *
 */

public class GameLoad {
//	得到资源管理器
	private static ElementManager em = ElementManager.getManager();

	public static Map<String, ImageIcon> imgMap = new HashMap<>();
	public static Map<String, List<ImageIcon>> imgMaps;
	
//	用户读取文件的类
	private static Properties pro = new Properties();
	
	/**
	 * 传入地图ID，由加载方法依据文件规则自动生成地图文件名称，加载文件
	 * @param mapId  文件编号 文件id
	 */
	
//	传入地板
	public static void MapLoadBase(int mapId) {
		Random random=new Random();
		int j=random.nextInt(3);
		String mapName = "com/tedu/text/"+(mapId+j+1)+"base.map";// 得到文件路径
//		使用io流来获取文件对象
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName);
		if(maps == null) {
			return;
		}
		try {
			pro.clear();
			pro.load(maps);
//			可以直接动态的获取所有的key  有key就可以获取value
//			java学习最好的老师是java的API文档
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
//	传入物体（可爆）
	public static void MapLoad(int mapId) {
		String mapName = "com/tedu/text/"+(mapId+1)+"obj.map";// 得到文件路径
//		使用io流来获取文件对象
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName);
		if(maps == null) {
			return;
		}
		try {
			pro.clear();
			pro.load(maps);
//			可以直接动态的获取所有的key  有key就可以获取value
//			java学习最好的老师是java的API文档
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	

	
	
//	传入障碍物
	public static void WallLoad(int mapId) {
		String mapName = "com/tedu/text/"+(mapId+1)+"obs.map";// 得到文件路径
//		使用io流来获取文件对象
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName);
		if(maps == null) {
			return;
		}
		try {
			pro.clear();
			pro.load(maps);
//			可以直接动态的获取所有的key  有key就可以获取value
//			java学习最好的老师是java的API文档
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 加载图片  代码和图片之间相差一个路径问题
	 * @param args
	 */
	public static void loadImg() {//可以带参数，不同关卡需要不同图片资源
		String texturl="com/tedu/text/GameData.pro";//文件命名可以更有规律
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
//		imgMap用于存放数据
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for(Object o:set) {
				String url = pro.getProperty(o.toString());
				imgMap.put(o.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载玩家
	 */
	public static void loadPlay() {
		loadObj();
		String playStr="50,50,paopao";

//		Class<?> class1 = objMap.get("play");
		ElementObj obj = getObj("paopao");

		ElementObj play = obj.createElement(playStr);

//		ElementObj play = new Play().creatElement(playStr);
//		解耦，降低代码和代码之间的耦合度
		em.addElement(play, GameElement.PLAY);
	}
	
	public static void loadPlay2() {
		loadObj();
		String playStr="750,450,paopao2";

//		Class<?> class1 = objMap.get("play");
		ElementObj obj = getObj("paopao2");

		ElementObj play = obj.createElement(playStr);

//		ElementObj play = new Play().creatElement(playStr);
//		解耦，降低代码和代码之间的耦合度
		em.addElement(play, GameElement.PLAY2);
	}
	
	public static ElementObj getObj(String str) {
		try {
			Class<?> class1 = objMap.get(str);
			Object newInstance = class1.newInstance();
			if(newInstance instanceof ElementObj) {
				return (ElementObj)newInstance;  //这个对象就和new Play等价
			}
		} catch (InstantiationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 使用配置文件 来实例化对象 通过固定的key(字符串来实例化)
	 * @param args
	 */
	private static Map<String,Class<?>> objMap = new HashMap<>();
	public static void loadObj() {
		String texturl="com/tedu/text/obj.pro";//文件命名可以更有规律
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
//	用于测试
	public static void main(String[] args) {
//		MapLoad(1);
//		try {
////			通过类路径名城  com.tedu.Play
//			Class<?> forName = Class.forName("");
////			通过类名 直接访问类
//			Class<?> forName1 = GameLoad.class;
////			通过实体对象 获取 反射对象
//			GameLoad gameLoad = new GameLoad();
//			Class<? extends GameLoad> class1 = gameLoad.getClass();
//		} catch (ClassNotFoundException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
		
		
	}
	
	
	
	
}
