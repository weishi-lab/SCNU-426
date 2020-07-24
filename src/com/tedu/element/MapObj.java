package com.tedu.element;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class MapObj extends ElementObj{
/**
 * 配合配置文件，设置文件格式，可仿照坦克，但记录的是下标
 */
	BufferedImage[]imgmap=new BufferedImage[20];
	
	public void MaoObj() {
		try {
			ReadImageFromDisk();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}// 读取图片

	}
	
	
	public void ReadImageFromDisk() throws Exception{
		// TODO 自动生成的方法存根
        imgmap[GRASS]=ImageIO.read(new File("image/map/grass.png"));
        imgmap[ROAD]= ImageIO.read(new File("image/map/road2.png"));
        imgmap[ROAD2]= ImageIO.read(new File("image/map/road.png"));
        imgmap[BOX]= ImageIO.read(new File("image/map/TownBox.png"));
        imgmap[BOXR]= ImageIO.read(new File("image/map/TownBlockRed.png"));
        imgmap[BOXO]= ImageIO.read(new File("image/map/TownBlockYellow.png"));
        imgmap[HomeB]= ImageIO.read(new File("image/map/TownHouseBlue.png"));
        imgmap[HomeR]= ImageIO.read(new File("image/map/TownHouseRed.png"));
        imgmap[HomeY]= ImageIO.read(new File("image/map/TownHouseYellow.png"));
        imgmap[Tree]= ImageIO.read(new File("image/map/TownTree.png"));
        imgmap[BUSH]= ImageIO.read(new File("image/map/TownBush.png"));
	}

	// 宏定义
    static final int GRASS = 0;
    static final int ROAD = 1;
    static final int ROAD2 = 2;
    static final int HomeR = 3;
    static final int HomeB = 4;
    static final int HomeY = 5;
    static final int Tree = 6;
    static final int BOX = 7;
    static final int BUSH = 8;
    static final int BOXR = 9;
    static final int BOXO = 10;
    
    static int [][]MAP_INFO = {
            {GRASS,BOXO,BOXR,BOXO,BOXR,BUSH,ROAD,ROAD2,BOX,BUSH,HomeY,BOXR,HomeY,GRASS,HomeY},
            {GRASS,HomeR,BOX,HomeR,BOX,Tree,BOX,ROAD2,ROAD,Tree,BOXR,BOXO,GRASS,GRASS,GRASS},
            {GRASS,GRASS,BOXO,BOXR,BOXO,BUSH,ROAD,BOX,BOX,BUSH,HomeY,BOX,HomeY,BOX,HomeY},
            {BOX,HomeY,BOX,HomeY,BOX,Tree,BOX,ROAD2,ROAD,Tree,BOXO,BOXR,BOXO,BOXR,BOXO},
            {BOXR,BOXO,BOXR,BOXO,BOXR,BUSH,ROAD,ROAD2,BOX,BUSH,HomeY,BOX,HomeY,BOX,HomeY},
            {BOXO,HomeR,BOXO,HomeR,BOXO,HomeR,BOX,BOX,ROAD,GRASS,BOXR,BOXO,BOXR,BOXO,BOXR},
            {Tree,BUSH,Tree,BUSH,Tree,BUSH,ROAD,ROAD2,BOX,BUSH,Tree,BUSH,Tree,BUSH,Tree},
            {BOXR,BOXO,BOXR,BOXO,BOXR,GRASS,BOX,ROAD2,ROAD,Tree,BOXR,HomeR,BOXR,HomeR,BOXR},
            {HomeB,BOX,HomeB,BOX,HomeB,BUSH,ROAD,BOX,BOX,BUSH,BOXO,BOXR,BOXO,BOXR,BOXO},
            {BOXO,BOXR,BOXO,BOXR,BOXO,Tree,BOX,ROAD2,ROAD,Tree,BOX,HomeR,BOX,HomeR,BOX,HomeR,BOX},
            {HomeB,GRASS,HomeB,BOX,HomeB,BUSH,ROAD,ROAD2,BOX,BUSH,BOXR,BOXO,BOXR,BOXO,GRASS},
            {GRASS,GRASS,BOXR,BOXO,BOXR,Tree,BOX,BOX,ROAD,Tree,BOX,HomeR,BOX,HomeR,GRASS},
            {HomeB,GRASS,HomeB,BOXR,HomeB,BUSH,ROAD,ROAD2,BOX,BUSH,BOXO,BOXR,BOXO,GRASS,GRASS}
    };
    
    
    
    
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		// 画背景
        for(int j=0;j<13;j++) {
            for (int i = 0; i < 15; i++) {
                //g.drawImage(imgmap[GRASS], 50*i, 50*j, 50, 50, null);
                switch (MAP_INFO[j][i]) {
                    case GRASS:
                        g.drawImage(imgmap[GRASS], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case ROAD:
                        g.drawImage(imgmap[ROAD], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case ROAD2:
                        g.drawImage(imgmap[ROAD2], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case HomeR:
                        g.drawImage(imgmap[HomeR], 50 * i, 50 * j - 15, 50, 65, null);
                        break;
                    case HomeY:
                        g.drawImage(imgmap[HomeY], 50 * i, 50 * j - 15, 50, 65, null);
                        break;
                    case HomeB:
                        g.drawImage(imgmap[HomeB], 50 * i, 50 * j - 15, 50, 65, null);
                        break;
                    case Tree:
                        g.drawImage(imgmap[GRASS], 50 * i, 50 * j, 50, 50, null);
                        g.drawImage(imgmap[Tree], 50 * i, 50 * j - 20, 50, 70, null);
                        break;
                    case BOX:
                        g.drawImage(imgmap[BOX], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case BOXO:
                        g.drawImage(imgmap[BOXO], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case BOXR:
                        g.drawImage(imgmap[BOXR], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case BUSH:
                        g.drawImage(imgmap[GRASS], 50 * i, 50 * j, 50, 50, null);
                        g.drawImage(imgmap[BUSH], 50 * i, 50 * j - 15, 50, 65, null);
                        break;
                }
            }
        }
	}

}
