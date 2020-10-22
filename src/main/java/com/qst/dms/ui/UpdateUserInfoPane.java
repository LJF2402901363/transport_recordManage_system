package com.qst.dms.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
 TODO :
 *2019年12月1日  下午12:25:27
 */
public class UpdateUserInfoPane extends BlankPanel{
	private static final long serialVersionUID = 1L;
	private GridLayout  girdLayout = null;
	private JTextField userIdField = null;
	private JTextField userNameField = null;
	private JLabel lbl_user = null;
	private JPasswordField oldPasswordFiled = null;
	private JLabel lbl_oldPassword = null;
	private JPasswordField newPasswordFiled = null;
	private JLabel lbl_newPassword = null;
	private JTextField hobbyFiled = null;
	private JTextArea addressArea = null;
	private JComboBox<String> degreeCombox = null;
	private final static String[] degrees = { "小学", "初中", "高中", "本科", "研究生", "硕士", "博士" };
	private JRadioButton male = null;
	private JRadioButton femalemale = null;
	private JButton btn_OK = null;
	private JButton btn_reset = null;
	private User user= null;
	private UserService userService = null;
	/**判定两次输入的密码是否相同*/
	private static boolean isEnsure = false;
    @SuppressWarnings("javadoc")
	public UpdateUserInfoPane(int width,int height,User user){
    	super(width, height);
    	this.girdLayout = new  GridLayout(9,1);
    	this.girdLayout.setVgap(2);
    	this.setLayout(girdLayout);
    	this.userIdField = new JTextField(15);
    	this.userNameField = new JTextField(15);
    	this. oldPasswordFiled = new JPasswordField(15);
    	this.newPasswordFiled = new JPasswordField(15);
    	this.hobbyFiled = new JTextField(20);
    	this.addressArea = new JTextArea();
    	this.degreeCombox = new JComboBox<String>(degrees);
    	this.lbl_user = new JLabel("");
    	this.lbl_user.setPreferredSize(new Dimension(120,15));
    	this.lbl_newPassword = new JLabel("");
    	this.lbl_newPassword.setPreferredSize(new Dimension(120,15));
    	this.lbl_oldPassword = new JLabel("");
    	this.lbl_oldPassword.setPreferredSize(new Dimension(120,15));
    	this.userService =  (UserService) ServiceFactory.getServiceImpl("UserService");
    	this.user =user;
        initConponents();
    }
	private void initConponents() {

		JPanel idPanel = createPanel("用户ID", String.valueOf(user.getId()), this.userIdField);
		JLabel lbl_id = new JLabel();
		lbl_id.setPreferredSize(new Dimension(120, 15));
		idPanel.add(lbl_id);
		this.add(idPanel);
		this.userIdField.setEditable(false);
		JPanel namePanel = createPanel("用户名", user.getUserName(), this.userNameField);
		namePanel.add(lbl_user);
		initUserNameFieldEvent();
		this.add(namePanel);
		JPanel oldPasswordPanel = createPanel("新的密码", "请输入新的密码", this.oldPasswordFiled);
		this.oldPasswordFiled.setEchoChar('\0');
		oldPasswordPanel.add(lbl_oldPassword);
		initOldPasswordFieldEvent();
		this.add(oldPasswordPanel);
		JPanel newPasswordPanel = createPanel("确认新密码", "请再次输入新的密码", this.newPasswordFiled);
		this.newPasswordFiled.setEchoChar('\0');
		newPasswordPanel.add(lbl_newPassword);
		initNewPasswordFieldEvent();
		this.add(newPasswordPanel);
		JPanel sexPanel = createSexPanel();
		JLabel lbl_sex = new JLabel();
		lbl_sex.setPreferredSize(new Dimension(120, 15));
		sexPanel.add(lbl_sex);
		this.add(sexPanel);
		JPanel hobbyPanel = createPanel("爱好", this.user.getHobby(), this.hobbyFiled);
		JLabel lbl_hobby = new JLabel();
		lbl_hobby.setPreferredSize(new Dimension(120, 15));
		hobbyPanel.add(lbl_hobby);
		this.add(hobbyPanel);
		JPanel addressPanel = createPanel("地址", "请输入新的地址", this.addressArea);
		this.addressArea.setLineWrap(true);
		JLabel lbl_adress = new JLabel();
		lbl_adress.setPreferredSize(new Dimension(120, 15));
		addressPanel.add(lbl_adress);
		this.add(addressPanel);
		JPanel degreePanel = createDegreePanel();
		JLabel lbl_degree = new JLabel();
		lbl_degree.setPreferredSize(new Dimension(120, 15));
		degreePanel.add(lbl_degree);
		this.add(degreePanel);
		JPanel btnPanel = initBtnPanel();
		initBtnActionEvent();
		this.add(btnPanel);
		
	}
	private void initBtnActionEvent() {
		this.btn_OK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isEnsure == true) {
					String password = new String(oldPasswordFiled.getPassword());
					String hobby = hobbyFiled.getText();
					String address = addressArea.getText();
					String degree = (String) degreeCombox.getSelectedItem();
					if (  password == null || password.length() == 0 || "请输入新的用户名".equals(password)) {
						JOptionPane.showConfirmDialog(UpdateUserInfoPane.this, "请重新检查您输入的新的用户名或密码！");
					} else {
						user.setPassword(password);
						user.setHobby(hobby);
						user.setAddress(address);
						user.setDegree(degree);
						// 更新用户
						boolean fla = userService.updateUser(user);
						if (fla == true) {
							JOptionPane.showConfirmDialog(UpdateUserInfoPane.this, "更新成功！");
						} else {
							JOptionPane.showConfirmDialog(UpdateUserInfoPane.this, "更新失败！");
						}
					}
				} else {
					JOptionPane.showConfirmDialog(UpdateUserInfoPane.this, "请重新检查您输入的新的用户名或密码！");
				}

			}
		});
		btn_reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userNameField.setText(user.getUserName());
				oldPasswordFiled.setText("请输入新的密码");
				oldPasswordFiled.setEchoChar('\0');
				newPasswordFiled.setText("请再次输入新的密码");
				newPasswordFiled.setEchoChar('\0');
				hobbyFiled.setText(user.getHobby());
				addressArea.setText("请输入新的地址");
				degreeCombox.setSelectedIndex(0);
			}
		});
	}
	private void initUserNameFieldEvent() {
		this.userNameField.setEditable(false);
	}
	private void initNewPasswordFieldEvent() {
		this.newPasswordFiled.addMouseListener(new MouseAdapter() {
 			 @Override
 			 public void mouseClicked(MouseEvent e) {
 				 if(e.getButton()==MouseEvent.BUTTON1){
 					 String newPwd = new String(newPasswordFiled.getPassword());
 					 if("请再次输入新的密码".equals(newPwd)){
 						 newPasswordFiled.setText("");
 						 newPasswordFiled.setEchoChar('*');
 					 }
 				 }
 			 }
 			 @Override
 			 public void mouseExited(MouseEvent e) {
 				 String newPwd = new String(newPasswordFiled.getPassword());
 				 if(newPwd== null || newPwd.length() == 0){
 					 newPasswordFiled.setText("请再次输入新的密码");
 					 newPasswordFiled.setEchoChar('\0');
 				 }
 			 }
 		 });
		

		this.newPasswordFiled.getDocument().addDocumentListener(newPasswordFiledDocumentListener());
		
		
	}
	private DocumentListener newPasswordFiledDocumentListener() {
		return new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				String oldPwd = new String(oldPasswordFiled.getPassword());
				String newPwd = new String(newPasswordFiled.getPassword());
 				if(newPwd == null ||newPwd.length() == 0){
 					isEnsure =false;
 					lbl_newPassword.setText("不能为空！");
 					lbl_newPassword.setForeground(Color.RED);
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else if("请再次输入新的密码".equals(newPwd)){
 					isEnsure =false;
 					lbl_newPassword.setText("");
 					lbl_newPassword.setForeground(Color.WHITE);
 				}
 				else if(oldPwd.startsWith(" ")){
 					lbl_newPassword.setText("不能以空格开始！");
 					lbl_newPassword.setForeground(Color.RED);
 					isEnsure =false;
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}
 				else if(oldPwd.contains(" ")){
 					isEnsure =false;
 					lbl_newPassword.setText("不能包含空格！");
 					lbl_newPassword.setForeground(Color.RED);
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}
 				else if(!oldPwd.equals(newPwd)&& !"请输入新的密码".equals(oldPwd)&& !"请再次输入新的密码".equals(newPwd)){
 					isEnsure =false;
 					lbl_newPassword.setText("两次输入的密码不同！");
 					lbl_newPassword.setForeground(Color.RED);
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else if(oldPwd.equals(newPwd)){
 					lbl_newPassword.setText("两次输入的密码相同");
 					isEnsure =true;
 					lbl_newPassword.setForeground(Color.GREEN);
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else {
 					isEnsure =false;
 					lbl_newPassword.setText("");
 					lbl_newPassword.setForeground(Color.WHITE);
 				}
				
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String oldPwd = new String(oldPasswordFiled.getPassword());
				String newPwd = new String(newPasswordFiled.getPassword());
 				if(newPwd == null ||newPwd.length() == 0){
 					isEnsure =false;
 					lbl_newPassword.setText("不能为空！");
 					lbl_newPassword.setForeground(Color.RED);
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else if("请再次输入新的密码".equals(newPwd)){
 					isEnsure =false;
 					lbl_newPassword.setText("");
 					lbl_newPassword.setForeground(Color.WHITE);
 				}
 				else if(oldPwd.startsWith(" ")){
 					lbl_newPassword.setText("不能以空格开始！");
 					lbl_newPassword.setForeground(Color.RED);
 					isEnsure =false;
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}
 				else if(oldPwd.contains(" ")){
 					isEnsure =false;
 					lbl_newPassword.setText("不能包含空格！");
 					lbl_newPassword.setForeground(Color.RED);
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}
 				else if(!oldPwd.equals(newPwd)&& !"请输入新的密码".equals(oldPwd)&& !"请再次输入新的密码".equals(newPwd)){
 					isEnsure =false;
 					lbl_newPassword.setText("两次输入的密码不同！");
 					lbl_newPassword.setForeground(Color.RED);
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else if(oldPwd.equals(newPwd)){
 					lbl_newPassword.setText("两次输入的密码相同");
 					isEnsure =true;
 					lbl_newPassword.setForeground(Color.GREEN);
 					lbl_newPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else {
 					isEnsure =false;
 					lbl_newPassword.setText("");
 					lbl_newPassword.setForeground(Color.WHITE);
 				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		};
	}
	private void initOldPasswordFieldEvent() {
		this.oldPasswordFiled.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {
 				if(e.getButton()==MouseEvent.BUTTON1){
 					String oldPwd = new String(oldPasswordFiled.getPassword());
 					if("请输入新的密码".equals(oldPwd)){
 						oldPasswordFiled.setText("");
 						oldPasswordFiled.setEchoChar('*');
 					}
 				}
 			}
 			@Override
 			public void mouseExited(MouseEvent e) {
 				String oldPwd = new String(oldPasswordFiled.getPassword());
 				if(oldPwd== null || oldPwd.length() == 0){
 					oldPasswordFiled.setText("请输入新的密码");
 					oldPasswordFiled.setEchoChar('\0');
 				}
 			}
 		});
		
		
		this.oldPasswordFiled.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String oldPwd = new String(oldPasswordFiled.getPassword());
 				if(oldPwd == null || oldPwd.length() == 0){
 					lbl_oldPassword.setText("不能为空！");
 					lbl_oldPassword.setForeground(Color.RED);
 					lbl_oldPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else if(oldPwd.startsWith(" ")){
 					lbl_oldPassword.setText("不能以空格开始！");
 					lbl_oldPassword.setForeground(Color.RED);
 					lbl_oldPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else if(oldPwd.contains(" ")){
 					lbl_oldPassword.setText("不能包含空格！");
 					lbl_oldPassword.setForeground(Color.RED);
 					lbl_oldPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else {
 					lbl_oldPassword.setText("");
 					lbl_oldPassword.setForeground(Color.WHITE);
 				}
				oldPasswordFiled.getDocument().addDocumentListener(newPasswordFiledDocumentListener());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String oldPwd = new String(oldPasswordFiled.getPassword());
 				if(oldPwd == null || oldPwd.length() == 0){
 					lbl_oldPassword.setText("不能为空！");
 					lbl_oldPassword.setForeground(Color.RED);
 					lbl_oldPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else if(oldPwd.startsWith(" ")){
 					lbl_oldPassword.setText("不能以空格开始！");
 					lbl_oldPassword.setForeground(Color.RED);
 					lbl_oldPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else if(oldPwd.contains(" ")){
 					lbl_oldPassword.setText("不能包含空格！");
 					lbl_oldPassword.setForeground(Color.RED);
 					lbl_oldPassword.setFont(new Font("微软雅黑",Font.BOLD,12));
 				}else {
 					lbl_oldPassword.setText("");
 					lbl_oldPassword.setForeground(Color.WHITE);
 				}
 				oldPasswordFiled.getDocument().addDocumentListener(newPasswordFiledDocumentListener());
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
	}
	private JPanel createSexPanel() {
		JPanel pane = new JPanel();
		pane.setOpaque(false);
		pane.setPreferredSize(new Dimension(400,30));
		ButtonGroup btngroup = new ButtonGroup();
		JLabel lbl = new JLabel("性别");
		JTextField sexField = new JTextField();
		sexField.setPreferredSize(new Dimension(200,30));
		sexField.setEditable(false);
		int sex = this.user.getSex();
		if(sex == 0){
			sexField.setText("男");;
		}else{
			sexField.setText("女");
		}
		this.male = new JRadioButton("男");
		this.femalemale = new JRadioButton("女");
		btngroup.add(male);
		btngroup.add(femalemale);
//		int sex = this.user.getSex();
//		if(sex == 0){
//			this.male.setSelected(true);
//		}else{
//			this.femalemale.setSelected(true);
//		}
		pane.add(lbl);
		pane.add(sexField);
//		pane.add(femalemale);
		return pane;
	}
	private JPanel createDegreePanel() {
		JPanel pane = new JPanel();
		pane.setOpaque(false);
		pane.setPreferredSize(new Dimension(400,30));
		JLabel lbl = new JLabel("学历");
		pane.add(lbl);
		pane.add(degreeCombox);
		return pane;
	}
	private JPanel createPanel(String panelName, String content, JTextArea addressFiled2) {
		JPanel pane = new JPanel();
		pane.setOpaque(false);
		pane.setPreferredSize(new Dimension(400,30));
		JLabel lbl = new JLabel(panelName);
		addressFiled2.setText(content);
		addressFiled2.setPreferredSize(new Dimension(200,30));
		pane.add(lbl);
		pane.add(addressFiled2 );
		return pane;
	}
	private JPanel initBtnPanel() {
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		btnPanel.setPreferredSize(new Dimension(550, 40));
		this.btn_OK = new JButton("确认修改");
		this.btn_reset = new JButton("重 置");
		btnPanel.add(this.btn_OK);
		btnPanel.add(this.btn_reset);
		return btnPanel;
	}
	private JPanel createPanel(String panelName,String content,JTextField text){
		JPanel pane1 = new JPanel();
		pane1.setOpaque(false);
		pane1.setPreferredSize(new Dimension(400,28));
		JLabel lbl = new JLabel(panelName);
		text.setText(content);
		text.setPreferredSize(new Dimension(200,28));
		pane1.add(lbl);
		pane1.add(text);
		return pane1;
	}
	

}
