package com.qst.dms.ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.qst.dms.entity.User;

/**
 * @author 陌意随影
 TODO :
 *2019年12月1日  下午12:25:27
 */
public class UserInfoPane extends BlankPanel{
	private static final long serialVersionUID = 1L;
	private GridLayout  girdLayout = null;
	private JTextField userIdField = null;
	private JTextField userNameField = null;
	private JPasswordField passwordFiled = null;
	private JTextField sexFiled = null;
	private JTextField hobbyFiled = null;
	private JTextField addressFiled = null;
	private JTextField degreeFiled = null;
	private User user = null;
    @SuppressWarnings("javadoc")
	public UserInfoPane(int width,int height,User user){
    	super(width, height);
    	this.girdLayout = new  GridLayout(7,1);
    	this.girdLayout.setVgap(10);
    	this.setLayout(girdLayout);
    	this.userIdField = new JTextField(15);
    	this.userNameField = new JTextField(15);
    	this. passwordFiled = new JPasswordField(15);
    	this.sexFiled = new JTextField(15);
    	this.hobbyFiled = new JTextField(15);
    	this.addressFiled = new JTextField(15);
    	this.degreeFiled = new JTextField(15);
    	this.user = user;
        initConponents();
    }
	private void initConponents() {
		JPanel idPanel = createPanel("用户ID",String.valueOf(user.getId()),this.userIdField);
    	 this.add(idPanel);
    	 JPanel namePanel = createPanel("用户名",user.getUserName(),this.userNameField);
 		 this.add(namePanel);
 		 JPanel passwordPanel = createPanel("密码",user.getPassword(),this.passwordFiled);
 		this.passwordFiled.setEchoChar('*');
 		 this.add(passwordPanel);
 		 int sex = user.getSex();
 		 JPanel sexPanel = createPanel("性别",sex==0?"男":"女",this.sexFiled);
 		 this.add(sexPanel);
 		 JPanel hobbyPanel = createPanel("爱好",user.getHobby(),this.hobbyFiled);
 		 this.add(hobbyPanel);
 		 JPanel addressPanel = createPanel("地址",user.getAddress(),this.addressFiled);
 		 this.add(addressPanel );
 		 JPanel degreePanel = createPanel("学历",user.getDegree(),this.degreeFiled);
 		 this.add(degreePanel);
 		 
	}
	private JPanel createPanel(String panelName,String content,JTextField text){
		JPanel pane = new JPanel();
		pane.setOpaque(false);
		pane.setPreferredSize(new Dimension(400,30));
		JLabel lbl = new JLabel(panelName);
		text.setText(content);
		text.setPreferredSize(new Dimension(200,30));
		text.setEditable(false);
		pane.add(lbl);
		pane.add(text);
		return pane;
	}
	

}
