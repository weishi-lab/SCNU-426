package com.tedu.music;

import java.io.FileNotFoundException;

/**
 * @author hao
 *@说明 音效工具类
 */
public class Sound {
	static final String DIR = "music/";//音乐文件夹
	static final String BACKGROUND = "background.wav";//背景音乐
	static final String HIT = "explode.wav";//炸弹爆炸音效
	static final String LAY = "lay.wav";//放炸弹音效
	static final String GET = "get.wav";//人物出场音效
	static final String GAMEOVER = "gameover.wav";//游戏结束音效
	static final String START = "start.wav";//游戏开始音效
	static final String CRASH = "crash.wav";//人物被炸到音效
//	还可以定义其他音乐 如放炸弹 炸弹爆炸等等
	
	private static void play(String file,boolean circulate) {
		MusicPlayer player;
		try {
			player = new MusicPlayer(file,circulate);//创建播放器
			player.play();//播放器开始播放
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 播放背景音乐
	 */
	static public void background() {
		play(DIR+BACKGROUND,true);
	}
	
	/**
	 * 播放炸弹爆炸音乐
	 */
	static public void hit() {
		play(DIR+HIT,false);
	}
	
	/**
	 * 播放放置炸弹音效
	 */
	static public void lay() {
		play(DIR+LAY,false);
	}
	
	/**
	 * 播放获取道具音效
	 */
	static public void get() {
		play(DIR+GET,false);
	}
	
	/**
	 * 播放游戏结束音效
	 */
	static public void gameover() {
		play(DIR+GAMEOVER,false);
	}
	
	/**
	 * 播放游戏开始音效
	 */
	static public void start() {
		play(DIR+START,false);
	}
	
	/**
	 * 播放人物被炸到音效
	 */
	static public void crash() {
		play(DIR+CRASH,false);
	}
	
}
