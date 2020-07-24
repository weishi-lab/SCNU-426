package com.tedu.music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author hao
 *@˵�� ��Ƶ��������
 */
public class MusicPlayer implements Runnable{
	File soundFile;//�����ļ�
	Thread thread;//���߳�
	boolean circulate;//�Ƿ�ѭ������
	
	public MusicPlayer(String filepath,boolean circulate) throws FileNotFoundException{
		this.circulate=circulate;
		soundFile=new File(filepath);
		if (!soundFile.exists()) {
			throw new FileNotFoundException(filepath+"δ�ҵ�");
		}
	}
	
	@Override
	public void run() {
		byte[] auBuffer = new byte[1024*128];//����128K������
		do {
			AudioInputStream audioInputStream = null;//������Ƶ����������
			SourceDataLine auline = null;//��Ƶ��Դ������
			try {//�������ļ��л�ȡ��Ƶ������
				audioInputStream=AudioSystem.getAudioInputStream(soundFile);
				AudioFormat format = audioInputStream.getFormat();//����Դ���������ͺ�ָ����Ƶ��ʽ���������ж���
				DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);//������Ƶϵͳ������ָ��Line.INFO�����е�����ƥ����ж���
				auline = (SourceDataLine)AudioSystem.getLine(info);
				auline.open(format);//����ָ����ʽ��Դ������
				auline.start();//Դ�����п�����д�
				int byteCount=0;//��¼��Ƶ�������ж������ֽ���
				while (byteCount!=-1) {
					byteCount = audioInputStream.read(auBuffer,0,auBuffer.length);//����Ƶ�������ж���128K������
					if (byteCount>=0) {//���������Ч����
						auline.write(auBuffer,0,byteCount);//����Ч����д����������
					}
				}
			} catch (UnsupportedAudioFileException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}finally {
				auline.drain();//���������
				auline.close();//�ر�������
			}
		} while (circulate);
	}
	
	public void play() {
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		thread.stop();
	}
	
}
