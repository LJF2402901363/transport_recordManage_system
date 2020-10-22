package com.qst.dms.ui;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * @author 陌意随影
 TODO :空白的面板
 *2019年12月1日  下午2:55:29
 */
public class BlankPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public BlankPanel(int width,int height){
		this.setPreferredSize(new Dimension(width, height));
		this.setOpaque(false);
	}

}
