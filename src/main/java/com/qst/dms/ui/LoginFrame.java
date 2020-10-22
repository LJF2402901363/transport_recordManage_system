package com.qst.dms.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.qst.dms.entity.User;
import com.qst.dms.factory.ServiceFactory;
import com.qst.dms.service.UserService;
import com.qst.dms.util.ConstantsConfig;
import com.qst.dms.util.VerificationUtil;

/**
 * @author 陌意随影
 TODO :用户登录的界面
 *2019年11月30日  下午9:06:19
 */
public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private static final int  WIDTH =400;
	private static final int  HEIGHT = 300;
	private Container contentPanel = null;
	private JTextField userName = null;
	private JLabel lbl_userName = null;
	private JPasswordField password = null;
	private JLabel lbl_password = null;
	private JTextField checkFiled = null;
	private JButton btn_login = null;
	private JButton btn_reset = null;
	private JButton btn_register= null;
	private CheckCodeLabel checkCodeLabel = null;
	private boolean isCorrect = false;
	private JLabel lbl_reflush= new JLabel("看不清？刷新一下");
    @SuppressWarnings("javadoc")
	public LoginFrame(){
    	this.setTitle("登录");
    	this.setBounds(400, 200, WIDTH, HEIGHT);
    	this.contentPanel=this.getContentPane();
    	//初始化控件
    	initConponents();
    	//给控件添加监听事件
    	initConponentsEvent();
//    	this.setResizable(false);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setVisible(true);
    }
	private void initConponentsEvent() {
		//给用户名输入框添加事件
		userNameFiedAction();
		//给密码框添加事件
		passwordFieldAction();
		//按键提交事件
		BtnActionEvent();
	}
	private void BtnActionEvent() {
		this.btn_login.addActionListener(new BtnLoginAction());
		this.btn_reset.addActionListener(new BtnResetAction());
		this.btn_register.addActionListener(new BtnRegisterAction());
	}
	
	private void passwordFieldAction() {
		this.password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String pwd = new String(password.getPassword());
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (pwd.equals("请输入密码")) {
						password.setText("");
						password.setEchoChar('*');
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				String pwd = new String(password.getPassword());
				if (pwd.length() == 0) {
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
					lbl_password.setText("密码不能为空！");
					lbl_password.setForeground(Color.RED);
				} else if (pwd.startsWith(" ")) {
					lbl_password.setText("不能以空格开始！");
					lbl_password.setForeground(Color.RED);
				}else if(pwd.contains(" ")){
					lbl_password.setText("不能包含空格！");
					lbl_password.setForeground(Color.RED);
				} else if("请输入密码".equals(pwd)){
					lbl_password.setText("");
					lbl_password.setForeground(Color.WHITE);
				}else {
					boolean fla = VerificationUtil.isLegalFormat(pwd, ConstantsConfig.PASSWORD_REGEXFORMATA);
					 if(fla){
						  lbl_password.setText("格式正确");
							lbl_password.setForeground(Color.GREEN);
					 }else{
						  lbl_password.setText("格式不合法");
							lbl_password.setForeground(Color.RED);
					 }
				}
				
				
					
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String pwd = new String(password.getPassword());
				
				if (pwd.length() == 0) {
					lbl_password.setText("密码不能为空！");
					lbl_password.setForeground(Color.RED);
				} else if (pwd.startsWith(" ")) {
					lbl_password.setText("不能以空格开始！");
					lbl_password.setForeground(Color.RED);
				}else if(pwd.contains(" ")){
					lbl_password.setText("不能包含空格！");
					lbl_password.setForeground(Color.RED);
				}else if("请输入密码".equals(pwd)){
					lbl_password.setText("");
					lbl_password.setForeground(Color.WHITE);
				} else {
					boolean fla = VerificationUtil.isLegalFormat(pwd, ConstantsConfig.PASSWORD_REGEXFORMATA);
					 if(fla){
						  lbl_password.setText("格式正确");
							lbl_password.setForeground(Color.GREEN);
					 }else{
						  lbl_password.setText("格式不合法");
							lbl_password.setForeground(Color.RED);
					 }
				}
				

			}
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}
	private void userNameFiedAction() {
		
		this.userName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String content = userName.getText();
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (content.equals("请输入用户名")) {
						userName.setText("");
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				String content = userName.getText();
				if (!content.equals("请输入用户名") && content.length() == 0) {
					userName.setText("请输入用户名");
				}
			}

		});
		this.userName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				String content = userName.getText();
				if (content.length() == 0) {
					lbl_userName.setText("用户名不能为空！");
					lbl_userName.setForeground(Color.RED);
				} else if (content.startsWith(" ")) {
					lbl_userName.setText("不能以空格开头！");
					lbl_userName.setForeground(Color.RED);
				}else if(content.contains(" ")){
					lbl_userName.setText("不能包含空格！");
					lbl_userName.setForeground(Color.RED);
				}else if("请输入用户名".equals(content)){
					lbl_userName.setText("");
					lbl_userName.setForeground(Color.WHITE);
				} else {
					boolean fla = VerificationUtil.isLegalFormat(content, ConstantsConfig.USERNAME_REGEXFORMATA);
					 if(fla){
						 lbl_userName.setText("格式正确");
						 lbl_userName.setForeground(Color.GREEN);
					 }else{
						 lbl_userName.setText("格式不合法");
						 lbl_userName.setForeground(Color.RED);
					 }
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String content = userName.getText();
				if (content.length() == 0) {
					lbl_userName.setText("用户名不能为空！");
					lbl_userName.setForeground(Color.RED);
				} else if (content.startsWith(" ")) {
					lbl_userName.setText("不能以空格开头！");
					lbl_userName.setForeground(Color.RED);
				}else if(content.contains(" ")){
					lbl_userName.setText("不能包含空格！");
					lbl_userName.setForeground(Color.RED);
				}else if("请输入用户名".equals(content)){
					lbl_userName.setText("");
					lbl_userName.setForeground(Color.WHITE);
				} else {
					boolean fla = VerificationUtil.isLegalFormat(content, ConstantsConfig.USERNAME_REGEXFORMATA);
					 if(fla){
						 lbl_userName.setText("格式正确");
						 lbl_userName.setForeground(Color.GREEN);
					 }else{
						 lbl_userName.setText("格式不合法");
						 lbl_userName.setForeground(Color.RED);
					 }
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});
	}
	private void initConponents() {
		JPanel panel = new JPanel();
//		panel.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		JPanel panel1 = new JPanel();
//		panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
		panel1.setPreferredSize(new Dimension(380,40));
		JLabel lbl_user = new JLabel("用户名");
		this.lbl_userName = new JLabel(" ");
		this.lbl_userName.setPreferredSize(new Dimension(110,30));
		this.userName = new JTextField("请输入用户名",15);
		this.userName.setPreferredSize(new Dimension(200,30));
		panel1 .add(lbl_user);
		panel1.add(userName);
		panel1.add(this.lbl_userName);
		panel.add(panel1);
		JPanel panel2 = new JPanel();
//		panel2.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		panel2.setPreferredSize(new Dimension(380,40));
		JLabel lbl_pwd = new JLabel("密  码");
		this.lbl_password = new JLabel(" ");
		this.lbl_password.setPreferredSize(new Dimension(110,30));
		this.password = new JPasswordField("请输入密码",15);
		this.password.setPreferredSize(new Dimension(200,30));
		this.password.setEchoChar('\0');
		panel2.add(lbl_pwd);
		panel2.add(password);
		panel2.add(this.lbl_password);
		panel.add(panel2);
		JPanel panel3 = new JPanel();
//		panel3.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
		panel3.setPreferredSize(new Dimension(380,40));
		JLabel lbl_chek = new JLabel("验证码");
		this.checkFiled = new JTextField("请输入验证码");
		
		this.checkFiled.setColumns(8);
		this.checkFiled.setPreferredSize(new Dimension(100,30));
		lbl_reflush.setPreferredSize(new Dimension(100,20));
		lbl_reflush.setFont(new Font("微软雅黑",Font.BOLD,12));
		Color color =lbl_reflush.getForeground();
		 this.checkCodeLabel = new CheckCodeLabel();
		 this.checkCodeLabel.setBorder(BorderFactory.createEmptyBorder());
		panel3.add(lbl_chek);
		panel3.add(this.checkFiled);
		panel3.add(  checkCodeLabel);
		panel3.add(lbl_reflush);
		initCheckCodeLabelActionEvent(color);
		initReflushActionEvent(color);
		initCheckFiledActionEvent();
		JPanel panel4 = new JPanel();
//		panel4.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
		panel4.setPreferredSize(new Dimension(380,40));
		this.btn_login = new JButton("登录");
		this.btn_reset = new JButton("重置");
		this.btn_register = new JButton("注册");
		panel4.add(btn_login);
		panel4.add(btn_reset);
		panel4.add(btn_register);
		JPanel panel5 = new JPanel(new BorderLayout());
//		panel5.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
		panel5.setPreferredSize(new Dimension(380,140));
		panel5.add(panel3,BorderLayout.NORTH);
		panel5.add(panel4,BorderLayout.SOUTH);
		panel.add(panel5);
		this.contentPanel.add(panel);
	}
	private void initReflushActionEvent(Color color) {
		lbl_reflush.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				checkCodeLabel.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_reflush.setForeground(Color.red);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_reflush.setForeground(color);
			}
		});
	}
	private void initCheckCodeLabelActionEvent(Color color) {
		this.checkCodeLabel.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				checkFiled.setText("请输入验证码");
				lbl_reflush.setText("看不清？刷新一下");
				lbl_reflush.setForeground( color);
				isCorrect = false;
			}
		});
	}
	private void initCheckFiledActionEvent() {
		this.checkFiled.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String content = checkFiled.getText();
				if(content == null ||content.length() == 0){
 					return;
 				}
 				if(!"请输入验证码".equals(content)&&content.length() >0){
 					String code = checkCodeLabel.getCode();
 					if(content.equalsIgnoreCase(code)){
 						isCorrect = true;
 						lbl_reflush.setText("验证成功");
 						lbl_reflush.setForeground(Color.GREEN);
 					}else{
 						isCorrect = false;
 						lbl_reflush.setText("验证错误");
 						lbl_reflush.setForeground(Color.RED);
 					}
 				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				
				String content = checkFiled.getText();
				if(content == null ||content.length() == 0){
 					return;
 				}
 				if(!"请输入验证码".equals(content)&&content.length() >0){
 					String code = checkCodeLabel.getCode();
 					if(content.equalsIgnoreCase(code)){
 						isCorrect = true;
 						lbl_reflush.setText("验证成功");
 						lbl_reflush.setForeground(Color.GREEN);
 					}else{
 						isCorrect = false;
 						lbl_reflush.setText("验证错误");
 						lbl_reflush.setForeground(Color.RED);
 					}
 				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		 checkFiled.addMouseListener(new MouseAdapter() {
     		@Override
 			public void mouseClicked(MouseEvent e) {
 				String content = checkFiled.getText().trim();
 				if("请输入验证码".startsWith(content)){
 					checkFiled.setText("");
 				}
 			}
 			@Override
 			public void mouseExited(MouseEvent e) {
 				String content = checkFiled.getText();
 				if(content == null ||content.length() == 0){
 					checkFiled.setText("请输入验证码");
 				}
 				if(!"请输入验证码".equals(content)||content.length() >0){
 					String code = checkCodeLabel.getCode();
 					
 					if(content.equals(code)){
 						isCorrect = true;
 						lbl_reflush.setText("验证成功");
 						lbl_reflush.setForeground(Color.GREEN);
 					}
 				}
 			}
		});
	}
	private class BtnLoginAction implements ActionListener{
		private UserService userService = (UserService) ServiceFactory.getServiceImpl("UserService");
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean fla   = LoginFrame.this.isCorrect;
			if(fla == false){
				JOptionPane.showConfirmDialog(LoginFrame.this, "请输入正确的验证码后登录！");
				return;
			}
			String username = userName.getText();
			String pwd = new String(password.getPassword());
			if("请输入用户名".equals(username) && "请输入密码".equals(pwd)){
				JOptionPane.showConfirmDialog(LoginFrame.this, "请输入用户名和密码！");
				return;
				
			}
			if("请输入用户名".equals(username)){
				JOptionPane.showConfirmDialog(LoginFrame.this, "请输入用户名！");
				return;
				
			}
			if("请输入密码".equals(pwd)){
				JOptionPane.showConfirmDialog(LoginFrame.this, "请输入密码！");
				return;
				
			}
			if (username.startsWith(" ") || username.length() == 0) {
				JOptionPane.showConfirmDialog(LoginFrame.this, "用户名不符合规范！");
				return;
			}
			if (pwd.startsWith(" ") || pwd.length() == 0) {
				JOptionPane.showConfirmDialog(LoginFrame.this, "密码不符合规范！");
				return;
			}
			List<User> userList = userService.getUserByUserName(username);
			if (userList.size() == 0) {
				Integer result = JOptionPane.showConfirmDialog(LoginFrame.this, "用户名不存在，是否立即注册！");
				if (result == 0) {
					LoginFrame.this.setVisible(false);
					new RegisterFrame(LoginFrame.this);
				}
			}else{
				User user = userService.LoginUser(username, pwd);
				if (user == null) {
					JOptionPane.showConfirmDialog(LoginFrame.this, "密码不正确，请重新输入！");
				} else {
					JOptionPane.showConfirmDialog(LoginFrame.this, "登录成功！");
					new MainFrame(LoginFrame.this,user);
					LoginFrame.this.setVisible(false);
					;
				}
			}
		}
	}
	private class BtnResetAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			userName.setText("请输入用户名");
			password.setText("请输入密码");
			password.setEchoChar('\0');
			checkCodeLabel.repaint();
			checkFiled.setText("请输入验证码");
		}
		
	}
	private class BtnRegisterAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			LoginFrame.this.setVisible(false);
			new RegisterFrame(LoginFrame.this);
			
		}
		
	}
	
}
