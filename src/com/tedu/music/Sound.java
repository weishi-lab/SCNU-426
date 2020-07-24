package com.tedu.music;

import java.io.FileNotFoundException;

/**
 * @author hao
 *@˵�� ��Ч������
 */
public class Sound {
	static final String DIR = "music/";//�����ļ���
	static final String BACKGROUND = "background.wav";//��������
	static final String HIT = "explode.wav";//ը����ը��Ч
	static final String LAY = "lay.wav";//��ը����Ч
	static final String GET = "get.wav";//���������Ч
	static final String GAMEOVER = "gameover.wav";//��Ϸ������Ч
	static final String START = "start.wav";//��Ϸ��ʼ��Ч
	static final String CRASH = "crash.wav";//���ﱻը����Ч
//	�����Զ����������� ���ը�� ը����ը�ȵ�
	
	private static void play(String file,boolean circulate) {
		MusicPlayer player;
		try {
			player = new MusicPlayer(file,circulate);//����������
			player.play();//��������ʼ����
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ű�������
	 */
	static public void background() {
		play(DIR+BACKGROUND,true);
	}
	
	/**
	 * ����ը����ը����
	 */
	static public void hit() {
		play(DIR+HIT,false);
	}
	
	/**
	 * ���ŷ���ը����Ч
	 */
	static public void lay() {
		play(DIR+LAY,false);
	}
	
	/**
	 * ���Ż�ȡ������Ч
	 */
	static public void get() {
		play(DIR+GET,false);
	}
	
	/**
	 * ������Ϸ������Ч
	 */
	static public void gameover() {
		play(DIR+GAMEOVER,false);
	}
	
	/**
	 * ������Ϸ��ʼ��Ч
	 */
	static public void start() {
		play(DIR+START,false);
	}
	
	/**
	 * �������ﱻը����Ч
	 */
	static public void crash() {
		play(DIR+CRASH,false);
	}
	
}
