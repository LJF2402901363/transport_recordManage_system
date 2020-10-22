package com.qst.dms.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JLabel; 
  
/**
 * @author 陌意随影
 TODO :验证码JLabel
 *2019年12月21日  上午12:48:29
 */
public class CheckCodeLabel extends JLabel { 
private static final long serialVersionUID = 1L;
private static Random random = new Random(); 
private int width = 60;//验证码的框宽
private int height =25;//验证码的框高
private int font_size = 20;//字体的大小
private int x = 0;//位置x
private int y = 0;//位置y
private int jam = 5;//干扰元素 建议使用 4~7 之间的数字 
private String code = "";//保存验证码 


@SuppressWarnings("javadoc")
public CheckCodeLabel(){
		super("验证码");
		this.setPreferredSize(new Dimension(width,height));
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				repaint();
			}
		});
	}

	/**
	 * 
	 * @return 获得随机颜色
	 */
	public Color getRandomColor() {
		int R = random.nextInt(255), G = random.nextInt(255), B = random.nextInt(255);
		return new Color(R, G, B);
	}

	/**
	 *  绘画验证码 绘画验证码
	 * @param g
	 */
	public void checkCode(Graphics g) {
		drawBorder(g);
		drawCode(g);
		drawJam(g);
	}

	/**
	 *  绘画边框和背景
	 * @param g
	 */
	public void drawBorder(Graphics g) {
		Color gc = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.setColor(gc);
	}

	/**
	 * 绘画验证码内容
	 * @param g
	 */
	public void drawCode(Graphics g) {
		Color c = g.getColor();
    	g.setFont(new Font("微软雅黑", Font.BOLD, font_size));
    	char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwsyz0123456789".toCharArray();
    	int index = 0;
    	int len = ch.length;
    	Random r = new Random();
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0;i < 4;i++){
    		index =r.nextInt(len);
    		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
    		g.drawString(ch[index]+"", i*12+2, font_size);
    		sb.append(ch[index]);
    	}
		g.setColor(c);
		this.code=sb.toString().trim();
	}

	/**
	 * 绘画干扰元素
	 * @param g
	 */
	public void drawJam(Graphics g) {
		Color gc = g.getColor();
		for (int i = 0; i < jam; i++) {
			g.setColor(getRandomColor());
			g.drawLine(x + random.nextInt(width), y + random.nextInt(height), x + random.nextInt(width),
					y + random.nextInt(height));
		}
		g.setColor(gc);
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.drawString("单击可刷新验证码", 30, 50);
		checkCode(g);
		g.setColor(c);
	}
	  
	/**
	 * @return 返回验证码
	 */
	public String getCode() {
		return code.trim();
	}
}