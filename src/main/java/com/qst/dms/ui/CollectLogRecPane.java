package com.qst.dms.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.qst.dms.data.LogRecStore;
import com.qst.dms.entity.LogRec;

/**
 * @author 陌意随影
 TODO :
 *2019年12月1日  下午12:25:27
 */
public class CollectLogRecPane extends BlankPanel{
	private static final long serialVersionUID = 1L;
	private GridLayout  girdLayout = null;
	private JPanel topPanel = null;
	private JTextField userNameField = null;
	private JLabel lbl_user = null;
	private JTextField loginAddressFiled = null;
	private JLabel lbl_address = null;
	private JTextField loginIpFiled = null;
	private JLabel lbl_ip = null;
	private JRadioButton rb_login = null;
	private JRadioButton rb_logout = null;
	private JButton btn_OK = null;
	private JButton btn_reset = null;
    @SuppressWarnings("javadoc")
	public CollectLogRecPane(int width,int height){
    	super(width, height);
    	this.girdLayout = new  GridLayout(5,1);
    	this.girdLayout.setHgap(30);
    	this.girdLayout.setVgap(30);
    	this.setLayout(girdLayout);
    	this.userNameField = new JTextField(15);
    	this.loginAddressFiled = new JTextField(15);
    	this.loginIpFiled = new JTextField(15);
    	this.topPanel = new JPanel();
    	initTopPanel();
    	initButtonPanel();
    	initConponentEvent();
    }

	private void initConponentEvent() {
		initUserNameFieldEvent() ;
		iniLoginAddressFieldEvent();
		iniLoginIpFieldEvent();
		initResetBtnEvent();
		initOkBtnEvent();
	}
	private void initOkBtnEvent() {
		//在这里添加多确定按钮的事件
		this.btn_OK.addActionListener(new BtnOkActionEvent());
	}

	private void initResetBtnEvent() {
		this.btn_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			userNameField.setText("请输入用户名");
			loginAddressFiled.setText("请输入登录地址");
			loginIpFiled.setText("请输入登录的ip地址");
			rb_login.setSelected(false);
			rb_logout.setSelected(false);
			}
		});
		
	}

	private void iniLoginIpFieldEvent() {
			loginIpFiled.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String str = loginIpFiled.getText();
					if(e.getButton() == MouseEvent.BUTTON1){
						if("请输入登录的ip地址".equals(str)){
							loginIpFiled.setText("");
						}
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {
					String str = loginIpFiled.getText();
					if(str.length() == 0){
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						loginIpFiled.setText("请输入登录的ip地址");
					}
				}
			});
			
			 this.loginIpFiled.getDocument().addDocumentListener(new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent e) {
						String content = loginIpFiled.getText();
						if(content.startsWith(" ")){
							lbl_ip.setText("不能以空格开始！");
							lbl_ip.setFont(new Font("微软雅黑", Font.BOLD,10));
							lbl_ip.setForeground(Color.red);
						}else{
							lbl_ip.setText("");
							lbl_ip.setForeground(Color.WHITE);
						}
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						String content = loginIpFiled.getText();
						if(content.startsWith(" ")){
							lbl_ip.setText("不能以空格开始！");
							lbl_ip.setFont(new Font("微软雅黑", Font.BOLD,10));
							lbl_ip.setForeground(Color.red);
						}else{
							lbl_ip.setText("");
							lbl_ip.setForeground(Color.WHITE);
						}
					  
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						
					}
				});
			
		}

	private void iniLoginAddressFieldEvent() {
		loginAddressFiled.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = loginAddressFiled.getText();
				if(e.getButton() == MouseEvent.BUTTON1){
					if("请输入登录地址".equals(str)){
						loginAddressFiled.setText("");
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String str = loginAddressFiled.getText();
				if(str.length() == 0){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					loginAddressFiled.setText("请输入登录地址");
				}
			}
		});
		
		 this.loginAddressFiled.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void removeUpdate(DocumentEvent e) {
					String content = loginAddressFiled.getText();
					if(content.startsWith(" ")){
						lbl_address.setText("不能以空格开始！");
						lbl_address.setFont(new Font("微软雅黑", Font.BOLD,10));
						lbl_address.setForeground(Color.red);
					}else{
						lbl_address.setText("");
						lbl_address.setForeground(Color.WHITE);
					}
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					String content = loginAddressFiled.getText();
					if(content.startsWith(" ")){
						lbl_address.setText("不能以空格开始！");
						lbl_address.setFont(new Font("微软雅黑", Font.BOLD,10));
						lbl_address.setForeground(Color.red);
					}
					else{
						lbl_address.setText("");
						lbl_address.setForeground(Color.WHITE);
					}
				  
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					
				}
			});
		
	}

	private void initUserNameFieldEvent() {
	
		this.userNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = userNameField.getText();
				if(e.getButton() == MouseEvent.BUTTON1){
					if("请输入用户名".equals(str)){
						userNameField.setText("");
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String str = userNameField.getText();
				if(str.length() == 0){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					userNameField.setText("请输入用户名");
				}
			}
		});
		
	 this.userNameField.getDocument().addDocumentListener(new DocumentListener() {
		@Override
		public void removeUpdate(DocumentEvent e) {
			String content = userNameField.getText();
			if(content.startsWith(" ")){
				lbl_user.setText("不能以空格开始！");
				lbl_user.setFont(new Font("微软雅黑", Font.BOLD,10));
				lbl_user.setForeground(Color.red);
			}else if(content.contains(" ")){
				lbl_user.setText("不能包含空格！");
				lbl_user.setFont(new Font("微软雅黑", Font.BOLD,10));
				lbl_user.setForeground(Color.red);
			}
			else{
				lbl_user.setText("");
				lbl_user.setForeground(Color.WHITE);
			}
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			String content = userNameField.getText();
			if(content.startsWith(" ")){
				lbl_user.setText("不能以空格开始！");
				lbl_user.setFont(new Font("微软雅黑", Font.BOLD,10));
				lbl_user.setForeground(Color.red);
			}else if(content.contains(" ")){
				lbl_user.setText("不能包含空格！");
				lbl_user.setFont(new Font("微软雅黑", Font.BOLD,10));
				lbl_user.setForeground(Color.red);
			}else{
				lbl_user.setText("");
				lbl_user.setForeground(Color.WHITE);
			}
		  
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			
		}
	});
	}
	private void initButtonPanel() {
		JPanel pane1 = new JPanel();
		pane1.setPreferredSize(new Dimension(550, 40));
		pane1.setOpaque(false);
		ButtonGroup buttonGroup = new ButtonGroup();
		this.rb_login = new JRadioButton("登入");
		this.rb_logout = new JRadioButton("登出");
		buttonGroup.add(rb_login);
		buttonGroup.add(rb_logout);
		JLabel lbl_login = new JLabel("登录状态");
		pane1.add(lbl_login);
		pane1.add(rb_login);
		pane1.add(rb_logout);
		this.add(pane1);
		JPanel pane2 = new JPanel();
		pane2.setOpaque(false);
		pane2.setPreferredSize(new Dimension(550, 40));
		this.btn_OK = new JButton("确认");
		this.btn_reset = new JButton("重置");
		pane2.add(this.btn_OK);
		pane2.add(btn_reset);
		this.add(pane2);
	}

	private void initTopPanel() {
		FlowLayout topPanelFlowLayout = new FlowLayout();
		this.topPanel.setLayout(topPanelFlowLayout);
		JPanel pane1 = new JPanel();
		pane1.setOpaque(false);
		pane1.setPreferredSize(new Dimension(550, 40));
		JLabel lbl_userName = new JLabel("用户名");
		this.userNameField.setText("请输入用户名");
		this.userNameField.setPreferredSize(new Dimension(400, 30));
		this.lbl_user = new JLabel(" ");
		this.lbl_user.setPreferredSize(new Dimension(100,15));
		pane1.add(lbl_userName);
		pane1.add(this.userNameField);
		pane1.add(lbl_user);
		this.add(pane1);
		JPanel pane2 = new JPanel();
		pane2.setOpaque(false);
		pane2.setPreferredSize(new Dimension(550, 40));
		JLabel lbl_LoginAaddress = new JLabel("登录地址");
		this.loginAddressFiled.setText("请输入登录地址");
		this.loginAddressFiled.setPreferredSize(new Dimension(400, 30));
		this.lbl_address = new JLabel(" ");
		this.lbl_address.setPreferredSize(new Dimension(100,15));
		pane2.add(lbl_LoginAaddress);
		pane2.add(loginAddressFiled);
		pane2.add(this.lbl_address);
		this.add(pane2);
		JLabel lbl_loginIp = new JLabel("登录ip");
		this.loginIpFiled.setText("请输入登录的ip地址");
		this.loginIpFiled.setPreferredSize(new Dimension(400, 30));
		this.lbl_ip = new JLabel(" ");
		this.lbl_ip.setPreferredSize(new Dimension(100,15));
		JPanel pane3 = new JPanel();
		pane3.setOpaque(false);
		pane3.setPreferredSize(new Dimension(550, 40));
		pane3.add(lbl_loginIp);
		pane3.add(this.loginIpFiled);
		pane3.add(this.lbl_ip);
		this.add(pane3);
	}
	private class BtnOkActionEvent implements ActionListener{
		ArrayList<LogRec> list = LogRecStore.getLogs();
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = userNameField.getText().trim();
			if (name == null || name.length() == 0) {
				JOptionPane.showConfirmDialog(CollectLogRecPane.this, "用户名格式不正确请重新输入！");
				return;
			}
			String address = loginAddressFiled.getText().trim();
			if (address == null || address.length() == 0) {
				JOptionPane.showConfirmDialog(CollectLogRecPane.this, "地址名格式不正确请重新输入！");
				return;
			}
			String ip = loginIpFiled.getText().trim();
			if (ip == null || ip.length() == 0) {
				JOptionPane.showConfirmDialog(CollectLogRecPane.this, "ip格式不正确请重新输入！");
				return;
			}
			int loginType = rb_login.isSelected() ? 1 : 0;
			LogRec logRec = new LogRec(new Date(), address, LogRec.GATHER, name, ip, loginType);
			JOptionPane.showConfirmDialog(CollectLogRecPane.this, "采集日志成功！");
			list.add(logRec);
		}

	}
}
