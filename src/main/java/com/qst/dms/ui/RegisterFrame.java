package com.qst.dms.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.qst.dms.entity.User;
import com.qst.dms.factory.ServiceFactory;
import com.qst.dms.service.UserService;

/**
 * @author 陌意随影
 TODO :用户注册i类
 *2019年11月28日  下午11:34:04
 */
public class RegisterFrame  extends JFrame{
	private static final long serialVersionUID = 5560471623697548683L;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
    private Container contentPanel = null;
    private JTextField userNameField = null;
    private JLabel    lbl_user =null;
    private JPasswordField password = null;
    private JLabel    lbl_pwd =null;
    private JPasswordField ensurePassword = null;
    private JLabel    lbl_ensure =null;
    private JRadioButton maleButton = null;
    private JRadioButton feMaleButton = null;
    private  static int  sex = 0 ;
    private ButtonGroup buttonGroup = null;
    private JCheckBox[] hobbyBox = null;
    private JTextArea  adressArea = null;
    private JComboBox<String> degreeCombox = null;
    private final static String[] degrees = {"小学","初中","高中","本科","研究生","硕士","博士"};
    private JButton btn_ensure = null;
    private JButton btn_reset = null;
    private LoginFrame loginFrame  = null;
    @SuppressWarnings("javadoc")
    
    
	public RegisterFrame(LoginFrame loginFrame){
    	this.setTitle("用户注册界面");
       	this.setBounds(450, 200, WIDTH, HEIGHT);
       	this.contentPanel = this.getContentPane();
       	this.loginFrame = loginFrame;
       	initConponent();
       	this.setResizable(false);
       	this.setVisible(true);
    	this.addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			loginFrame.setVisible(true);
    			RegisterFrame.this.dispose();
    		}
		} );

    }
   	@SuppressWarnings("javadoc")
	public RegisterFrame(){
       	this.setTitle("用户注册界面");
       	this.setBounds(450, 200, WIDTH, HEIGHT);
       	this.contentPanel = this.getContentPane();
       	initConponent();
       	this.setResizable(false);
       	this.setVisible(true);
       }



	private void initConponent() {
		initTopPanel();
		initCenterPanel();
		initButtonPanle();
		initToPanelEvent();
		initCenterPanelEvent();
		initButtonPanel();
	}

	private void initCenterPanelEvent() {
		this.feMaleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					sex = 0;
				}
			}
		});
		this.maleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					sex = 1;
				}
			}
		});
	}
	private void initButtonPanel() {
		this.btn_ensure.addActionListener(new EnsureOkMouseListener());
		this.btn_reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					userNameField.setText("请输入用户名");
					password.setText("请输入密码");
					password.setEchoChar('\0');
					ensurePassword.setText("请再次确认密码");
					ensurePassword.setEchoChar('\0');
					maleButton.setSelected(false);
					feMaleButton.setSelected(false);
					for(int i = 0 ;i < hobbyBox.length;i++){
						hobbyBox[i].setSelected(false);
					}
					adressArea.setText("");
					degreeCombox.setSelectedIndex(0);
				}
			}
		});
		
	}
	private void initToPanelEvent() {
	 //初始化用户名输入框的事件
	 initUserNameFieldEvent();
	 //初始化密码框的事件
	 initPasswordFiedEvent();
	 //初始化确认密码框的事件
	 initEnsurePasswordFiledEvent();
	}

	private void initEnsurePasswordFiledEvent() {
		this.ensurePassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pwd = new String(ensurePassword.getPassword());
					 if("请再次确认密码".equals(pwd)){
						 ensurePassword.setText("");
						 ensurePassword.setEchoChar('●');
					 }
					 
					 
			}
		});
		this.ensurePassword.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					 String pwd = new String(ensurePassword.getPassword());
					 if("请再次确认密码".equals(pwd)){
						 ensurePassword.setText("");
						 ensurePassword.setEchoChar('●');
					 }
				}
			}
			 @Override
			public void mouseExited(MouseEvent e) {
				 String pwd = new String(ensurePassword.getPassword());
				 if(pwd.length() == 0){
					 try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					 ensurePassword.setText("请再次确认密码");
					 ensurePassword.setEchoChar('\0');
				 }
			}
		});
		 this.ensurePassword.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String password1 =new String( password.getPassword());
				String password2 =new String( ensurePassword.getPassword());
				if(password1.equals("请输入密码")&&password2.equals("请再次确认密码")){
					lbl_ensure.setText("");
					return;
				}
				if(password2.length() == 0 ){
					lbl_ensure.setText("密码不能为空！");
					lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD,10));
					lbl_ensure.setForeground(Color.red);
				}else if(!password1.equals(password2)&&!password2.equals("请再次输入密码")){
					lbl_ensure.setText("前后两次密码不一致！");
					lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD,10));
					lbl_ensure.setForeground(Color.red);
				}else{
					lbl_ensure.setText("前后两次密码相同！");
					lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD,10));
					lbl_ensure.setForeground(Color.GREEN);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String password1 =new String( password.getPassword());
				String password2 =new String( ensurePassword.getPassword());
				if(password1.equals("请输入密码")&&password2.equals("请再次确认密码")){
					lbl_ensure.setText("");
					return;
				}
				if( password2.length() == 0 ){
					lbl_ensure.setText("密码不能为空！");
					lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD,10));
					lbl_ensure.setForeground(Color.red);
				}else if(!password1.equals(password2)&&!password2.equals("请再次输入密码")){
					lbl_ensure.setText("前后两次密码不一致！");
					lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD,10));
					lbl_ensure.setForeground(Color.red);
				}else{
					lbl_ensure.setText("前后两次密码相同！");
					lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD,10));
					lbl_ensure.setForeground(Color.GREEN);
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
	}
	private void initPasswordFiedEvent() {
		this.password.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					 String pwd = new String(password.getPassword());
					 if("请输入密码".equals(pwd)){
						 password.setText("");
						 password.setEchoChar('●');
					 }
				}
			}
			 @Override
			public void mouseExited(MouseEvent e) {
				 String pwd = new String(password.getPassword());
				 if(pwd.length() == 0){
					 try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					 password.setText("请输入密码");
					 password.setEchoChar('\0');
				 }
			}
		});
		 
		 this.password.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String pwd = new String(password.getPassword());
				if (pwd.length() == 0) {
					lbl_pwd.setText("密码不能为空！");
					lbl_pwd.setFont(new Font("微软雅黑", Font.BOLD, 10));
					lbl_pwd.setForeground(Color.RED);
				} else if (pwd.contains(" ")) {
					lbl_pwd.setText("不能以空格开始！");
					lbl_pwd.setFont(new Font("微软雅黑", Font.BOLD, 10));
					lbl_pwd.setForeground(Color.RED);
				}else if(pwd.contains(" ")){
					lbl_pwd.setText("不能包含空格！");
					lbl_pwd.setFont(new Font("微软雅黑", Font.BOLD, 10));
					lbl_pwd.setForeground(Color.RED);
				} else {
					lbl_pwd.setText("");
					lbl_pwd.setForeground(Color.WHITE);
				}
				String password1 = new String(password.getPassword());
				String password2 = new String(ensurePassword.getPassword());
				if(password1.equals("请输入密码")&&password2.equals("请再次确认密码")){
					lbl_ensure.setText("");
					return;
				}
				if (password2.length() != 0 && !password2.equals("请再次确认密码")) {
					if (!password1.equals(password2) &&!password2.equals("请再次输入密码")) {
						lbl_ensure.setText("前后两次密码不一致！");
						lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD, 10));
						lbl_ensure.setForeground(Color.red);
					} else {
						lbl_ensure.setText("前后两次密码相同！");
						lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD, 10));
						lbl_ensure.setForeground(Color.GREEN);
					}
				}

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String pwd = new String(password.getPassword());
				RegisterFrame.this.initEnsurePasswordFiledEvent();
				if (pwd.length() == 0) {
					lbl_pwd.setText("密码不能为空！");
					lbl_pwd.setFont(new Font("微软雅黑", Font.BOLD, 10));
					lbl_pwd.setForeground(Color.RED);
				} else if (pwd.contains(" ")) {
					lbl_pwd.setText("不能以空格开始！");
					lbl_pwd.setFont(new Font("微软雅黑", Font.BOLD, 10));
					lbl_pwd.setForeground(Color.RED);
				} else if(pwd.contains(" ")){
					lbl_pwd.setText("不能包含空格！");
					lbl_pwd.setFont(new Font("微软雅黑", Font.BOLD, 10));
					lbl_pwd.setForeground(Color.RED);
				}else {
					lbl_pwd.setText("");
					lbl_pwd.setForeground(Color.WHITE);
				}
				String password1 = new String(password.getPassword());
				String password2 = new String(ensurePassword.getPassword());
				if(password1.equals("请输入密码")&&password2.equals("请再次确认密码")){
					lbl_ensure.setText("");
					return;
				}
				if (password2.length() != 0 && !password2.equals("请再次确认密码")) {
					if (!password1.equals(password2) &&!password2.equals("请再次输入密码")) {
						lbl_ensure.setText("前后两次密码不一致！");
						lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD, 10));
						lbl_ensure.setForeground(Color.red);
					} else {
						lbl_ensure.setText("前后两次密码相同！");
						lbl_ensure.setFont(new Font("微软雅黑", Font.BOLD, 10));
						lbl_ensure.setForeground(Color.GREEN);
					}
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
				if(e.getButton() == MouseEvent.BUTTON1){
					String str = userNameField.getText();
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
			}else{
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
			}
			else{
				lbl_user.setText("");
				lbl_user.setForeground(Color.WHITE);
			}
		  
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			
		}
	});
	}



	private void initButtonPanle() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500,120));
		JLabel lbl_address = new JLabel("地址:");
		this.adressArea = new JTextArea();
		this.adressArea.setPreferredSize(new Dimension(340,50));
         buttonPanel.add(lbl_address);
         buttonPanel.add(adressArea);
		JLabel lbl_degree = new JLabel("学历:");
		this.degreeCombox = new JComboBox<>(degrees);
		degreeCombox.setPreferredSize(new Dimension(200,20));
		buttonPanel.add(lbl_degree);
		buttonPanel.add(degreeCombox);
		JPanel panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(400,30));
		this.btn_ensure = new JButton("确定");
		this.btn_reset = new JButton("重置");
		panel3.add(btn_ensure);
		panel3.add(btn_reset);
		buttonPanel.add(panel3);
		this.contentPanel.add(buttonPanel,BorderLayout.SOUTH);
	}

	private void initCenterPanel() {
		JPanel centerPanel = new JPanel();
		JLabel lbl_sex = new JLabel("性别");
		this.maleButton = new JRadioButton("男");
		this.feMaleButton = new JRadioButton("女");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(feMaleButton);
		buttonGroup.add(maleButton);
		centerPanel.add(lbl_sex);
		centerPanel.add(this.maleButton);
		JPanel panel1= new JPanel();
		panel1.add(lbl_sex);
		panel1.add(maleButton);
		panel1.add(feMaleButton);
		 panel1.setPreferredSize(new Dimension(400,30));
		centerPanel.add(panel1);
		JLabel lbl_hobby = new JLabel("爱好");
		centerPanel.add(lbl_hobby);
		this.hobbyBox = new JCheckBox[4];
		this.hobbyBox[0] = new JCheckBox("阅读");
		this.hobbyBox[1] = new JCheckBox("上网");
		this.hobbyBox[2] = new JCheckBox("游泳");
		this.hobbyBox[3] = new JCheckBox("旅游");
		centerPanel.add(this.hobbyBox[0]);
		centerPanel.add(this.hobbyBox[1]);
		centerPanel.add(this.hobbyBox[2]);
		centerPanel.add(this.hobbyBox[3]);
		this.contentPanel.add(centerPanel,BorderLayout.CENTER);
	}



	private void initTopPanel() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(20);
		flowLayout.setHgap(10);
		JPanel topPanel = new JPanel( flowLayout);
		topPanel.setOpaque(false);
		JLabel lbl_userName = new JLabel(" 用户名");
		lbl_userName.setPreferredSize(new Dimension(50,40));
		this.userNameField = new JTextField(11);
		this.userNameField.setText("请输入用户名");
		this.userNameField.setPreferredSize(new Dimension(50,30));
		this.userNameField.setMargin(new Insets( 0,0, 0, 53));
		this.lbl_user = new JLabel(" ");
		this.lbl_user.setPreferredSize(new Dimension(100,15));
		topPanel.add(lbl_userName);
		topPanel.add(this.userNameField);
		topPanel.add(this.lbl_user);
		JLabel lbl_password = new JLabel("密  码");
		lbl_password.setPreferredSize(new Dimension(50,40));
		this.password = new JPasswordField(11);
		this.password.setText("请输入密码");
		this.password.setEchoChar('\0');
		this.password.setPreferredSize(new Dimension(50,30));
		this.password.setMargin(new Insets( 0,0, 0, 53));
		this.lbl_pwd = new JLabel();
		this.lbl_pwd.setPreferredSize(new Dimension(100,15));
		topPanel.add(lbl_password);
		topPanel.add(password);
		topPanel.add(lbl_pwd);
		JLabel lbl_ensurePassword = new JLabel("确认密码");
		lbl_ensurePassword.setPreferredSize(new Dimension(60,40));
		this.ensurePassword = new JPasswordField(11);
		this.ensurePassword.setText("请再次确认密码");
		this.ensurePassword.setEchoChar('\0');
		this.ensurePassword.setPreferredSize(new Dimension(50,30));
		this.ensurePassword.setMargin(new Insets( 0,0, 0, 53));
		this.lbl_ensure = new JLabel();
		this.lbl_ensure.setPreferredSize(new Dimension(100,15));
		topPanel.add(lbl_ensurePassword);
		topPanel.add(ensurePassword);
		topPanel.add(lbl_ensure);
		topPanel.setPreferredSize(new Dimension(600,150));
		this.contentPanel.add(topPanel,BorderLayout.NORTH);
	}
	private class EnsureOkMouseListener implements  ActionListener{
		UserService userService = (UserService) ServiceFactory.getServiceImpl("UserService");
		@Override
		public void actionPerformed(ActionEvent e) {
			String name =RegisterFrame.this.userNameField.getText();
			String password1 =new String(RegisterFrame.this.password.getPassword());
			String password2 =new String(RegisterFrame.this.ensurePassword.getPassword());
			String hobby ="";
			for(int i = 0;i < hobbyBox.length;i++){
				if(hobbyBox[i].isSelected()){
					hobby=hobby+hobbyBox[i].getText()+",";
				}
			}
			hobby = hobby.substring(0, hobby.lastIndexOf(","));
			String address = RegisterFrame.this.adressArea.getText().trim();
			String degree = (String) RegisterFrame.this.degreeCombox.getSelectedItem();
			if(password1.equals(password2)){
				User user = new User(name,password1,sex,hobby,address,degree);
				boolean fla =userService.saveUser(user);	
				if(fla){
					JOptionPane.showConfirmDialog(RegisterFrame.this, "新用户注册成功！");
					if(loginFrame != null){
						loginFrame.setVisible(true);
					}
					RegisterFrame.this.dispose();
				}else{
					JOptionPane.showConfirmDialog(RegisterFrame.this, "该用户已经存在！");
				}
			}else{
				JOptionPane.showConfirmDialog(RegisterFrame.this, "两次密码不一致，请重新输入！");
			}
		}
		
	}
	
}
