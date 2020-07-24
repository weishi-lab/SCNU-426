package com.tedu.show;
import com.tedu.game.GameStart;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameListener2;
import com.tedu.controller.GameThread;
import com.tedu.game.GameStart;



	@SuppressWarnings("serial")
	public class BeginJPanel extends JPanel{
	 private ImageIcon img;
	 private int w;
	 private int h;
	 
	 //构造函数
	 public BeginJPanel(){
//	  Sound.start();
	  this.img = new ImageIcon("image/title.png");
	  this.w = 800;
	  this.h = 600;
	  init();
	  this.setVisible(true);
	 }
	
	 private void init() {
	
	  this.setLayout(null);
	  
	  JLabel jLabel = new JLabel(img);
	  img.setImage(img.getImage().getScaledInstance(w, h,Image.SCALE_DEFAULT ));
	  jLabel.setBounds(0, 0, w, h);
	  
	  ImageIcon img2 = new ImageIcon("image/instructions.png");
	  final JLabel jLabel2 = new JLabel(img2);
	  jLabel2.setBounds(w/2-100, h/6-100, 700, 800);
	  jLabel2.setVisible(false);
	  
	  
	  JButton startButton = new JButton();
	  startButton.setIcon(new ImageIcon("image/rect2.png"));
	  startButton.setBounds(w/6-80, h/2-250, 222, 82);
	  startButton.setBorderPainted(false);
	  startButton.setFocusPainted(false);
	  startButton.setContentAreaFilled(false);
	  startButton .addActionListener(new ActionListener() {
	   
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    GameJFrame gj = new GameJFrame();
	    /*实例化面板，注入到jFrame中 */
	    GameMainJPanel jp = new GameMainJPanel();
	    //实例化监听
	    GameListener listener = new GameListener();
	    GameListener2 listener2 = new GameListener2();
	    //实例化主进程
	    GameThread th = new GameThread();
	    //注入
	    gj.setjPanel(jp);
	    gj.setKeyListener(listener);
	    gj.setKeyListener2(listener2);
	    gj.setThread(th);
	    
	    gj.start();
	   }
	  });
	
	  JButton magicBoxButton = new JButton();
	  magicBoxButton.setIcon(new ImageIcon("image/rect3.png"));
	  magicBoxButton.setBounds(w/6-80, h-h/3-250, 222, 82);
	  magicBoxButton.setBorderPainted(false);
	  magicBoxButton.setFocusPainted(false);
	  magicBoxButton.setContentAreaFilled(false);
	  magicBoxButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				if(!jLabel2.isVisible())
					jLabel2.setVisible(true);
				else {
					jLabel2.setVisible(false);
				}
			}
		});
		this.add(startButton);
	
		this.add(magicBoxButton);
		this.add(jLabel2);
		this.add(jLabel);
		
		
		this.setVisible(true);
		this.setOpaque(true);
	}
  
  
 }