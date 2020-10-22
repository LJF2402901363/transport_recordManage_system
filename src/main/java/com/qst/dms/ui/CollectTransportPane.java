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
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.qst.dms.data.TransportStore;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.Transport;

/**
 * @author 陌意随影
 TODO :收集信息面板
 *2019年12月1日  下午12:25:27
 */
public class CollectTransportPane extends BlankPanel{
	private static final long serialVersionUID = 1L;
	private GridLayout  girdLayout = null;
	private JPanel topPanel = null;
	private JTextField desField = null;
	private JLabel lbl_des = null;
	private JTextField transFiled = null;
	private JLabel lbl_trans = null;
	private JTextField receverFiled = null;
	private JLabel lbl_recever = null;
	private JComboBox<String> transStage = null;
	private String[] stages = {"发货中","送货中","已签收"};
	private JButton btn_OK = null;
	private JButton btn_reset = null;
    @SuppressWarnings("javadoc")
	public CollectTransportPane(int width,int height){
    	super(width, height);
    	this.girdLayout = new  GridLayout(5,1);
    	this.girdLayout.setHgap(30);
    	this.girdLayout.setVgap(30);
    	this.setLayout(girdLayout);
    	this.desField = new JTextField(15);
    	this.transFiled = new JTextField(15);
    	this.receverFiled = new JTextField(15);
    	this.topPanel = new JPanel();
    	//初始化上层面板
    	initTopPanel();
    	//初始化下层面板
    	initButtonPanel();
    	//初始化控件的事件
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
			desField.setText("请输入目的地");
			transFiled.setText("请输入经手人");
			receverFiled.setText("请输入收货人");
			transStage.setSelectedIndex(0);
			}
		});
		
	}

	private void iniLoginIpFieldEvent() {
			receverFiled.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String str = receverFiled.getText();
					if(e.getButton() == MouseEvent.BUTTON1){
						if("请输入收货人".equals(str)){
							receverFiled.setText("");
						}
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {
					String str = receverFiled.getText();
					if(str.length() == 0){
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						receverFiled.setText("请输入收货人");
					}
				}
			});
			
			 this.receverFiled.getDocument().addDocumentListener(new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent e) {
						String content = receverFiled.getText();
						if(content.startsWith(" ")){
							lbl_recever.setText("不能以空格开始！");
							lbl_recever.setFont(new Font("微软雅黑", Font.BOLD,10));
							lbl_recever.setForeground(Color.red);
						}else{
							lbl_recever.setText("");
							lbl_recever.setForeground(Color.WHITE);
						}
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						String content = receverFiled.getText();
						if(content.startsWith(" ")){
							lbl_recever.setText("不能以空格开始！");
							lbl_recever.setFont(new Font("微软雅黑", Font.BOLD,10));
							lbl_recever.setForeground(Color.red);
						}else{
							lbl_recever.setText("");
							lbl_recever.setForeground(Color.WHITE);
						}
					  
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						
					}
				});
			
		}

	private void iniLoginAddressFieldEvent() {
		transFiled.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = transFiled.getText();
				if(e.getButton() == MouseEvent.BUTTON1){
					if("请输入经手人".equals(str)){
						transFiled.setText("");
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String str = transFiled.getText();
				if(str.length() == 0){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					transFiled.setText("请输入经手人");
				}
			}
		});
		
		 this.transFiled.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void removeUpdate(DocumentEvent e) {
					String content = transFiled.getText();
					if(content.startsWith(" ")){
						lbl_trans.setText("不能以空格开始！");
						lbl_trans.setFont(new Font("微软雅黑", Font.BOLD,10));
						lbl_trans.setForeground(Color.red);
					}else{
						lbl_trans.setText("");
						lbl_trans.setForeground(Color.WHITE);
					}
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					String content = transFiled.getText();
					if(content.startsWith(" ")){
						lbl_trans.setText("不能以空格开始！");
						lbl_trans.setFont(new Font("微软雅黑", Font.BOLD,10));
						lbl_trans.setForeground(Color.red);
					}else{
						lbl_trans.setText("");
						lbl_trans.setForeground(Color.WHITE);
					}
				  
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					
				}
			});
		
	}

	private void initUserNameFieldEvent() {
	
		this.desField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = desField.getText();
				if(e.getButton() == MouseEvent.BUTTON1){
					if("请输入目的地".equals(str)){
						desField.setText("");
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String str = desField.getText();
				if(str.length() == 0){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					desField.setText("请输入目的地");
				}
			}
		});
		
	 this.desField.getDocument().addDocumentListener(new DocumentListener() {
		@Override
		public void removeUpdate(DocumentEvent e) {
			String content = desField.getText();
			if(content.startsWith(" ")){
				lbl_des.setText("不能以空格开始！");
				lbl_des.setFont(new Font("微软雅黑", Font.BOLD,10));
				lbl_des.setForeground(Color.red);
			}else if(content.contains(" ")){
				lbl_des.setText("不能包含空格！");
				lbl_des.setFont(new Font("微软雅黑", Font.BOLD,10));
				lbl_des.setForeground(Color.red);
			}
			else{
				lbl_des.setText("");
				lbl_des.setForeground(Color.WHITE);
			}
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			String content = desField.getText();
			if(content.startsWith(" ")){
				lbl_des.setText("不能以空格开始！");
				lbl_des.setFont(new Font("微软雅黑", Font.BOLD,10));
				lbl_des.setForeground(Color.red);
			}else if(content.contains(" ")){
				lbl_des.setText("不能包含空格！");
				lbl_des.setFont(new Font("微软雅黑", Font.BOLD,10));
				lbl_des.setForeground(Color.red);
			}else{
				lbl_des.setText("");
				lbl_des.setForeground(Color.WHITE);
			}
		  
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			
		}
	});
	}
	private void initButtonPanel() {
		JPanel pane1 = new JPanel();
		pane1.setOpaque(false);
		pane1.setPreferredSize(new Dimension(550, 40));
		JLabel lbl_login = new JLabel("物流状态");
		this.transStage = new JComboBox<>(stages);
		pane1.add(lbl_login);
		pane1.add(this.transStage);
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
		JLabel lbl_userName = new JLabel("目的地");
		this.desField.setText("请输入目的地");
		this.desField.setPreferredSize(new Dimension(400, 30));
		this.lbl_des = new JLabel(" ");
		this.lbl_des.setPreferredSize(new Dimension(100,15));
		pane1.add(lbl_userName);
		pane1.add(this.desField);
		pane1.add(lbl_des);
		this.add(pane1);
		JPanel pane2 = new JPanel();
		pane2.setOpaque(false);
		pane2.setPreferredSize(new Dimension(550, 40));
		JLabel lbl_LoginAaddress = new JLabel("经手人");
		this.transFiled.setText("请输入经手人");
		this.transFiled.setPreferredSize(new Dimension(400, 30));
		this.lbl_trans = new JLabel(" ");
		this.lbl_trans.setPreferredSize(new Dimension(100,15));
		pane2.add(lbl_LoginAaddress);
		pane2.add(transFiled);
		pane2.add(this.lbl_trans);
		this.add(pane2);
		JLabel lbl_transrecever = new JLabel("收货人");
		this.receverFiled.setText("请输入收货人");
		this.receverFiled.setPreferredSize(new Dimension(400, 30));
		this.lbl_recever = new JLabel(" ");
		this.lbl_recever.setPreferredSize(new Dimension(100,15));
		JPanel pane3 = new JPanel();
		pane3.setOpaque(false);
		pane3.setPreferredSize(new Dimension(550, 40));
		pane3.add(lbl_transrecever);
		pane3.add(this.receverFiled);
		pane3.add(this.lbl_recever);
		this.add(pane3);
	}
	private class BtnOkActionEvent implements ActionListener{
       List<Transport>  list = TransportStore.getTrans();
		@Override
		public void actionPerformed(ActionEvent e) {
			String address = desField.getText().trim();
			if(address==null||address.length() == 0){
				JOptionPane.showConfirmDialog(CollectTransportPane.this, "目的地格式不正确请重新输入！");
				return ;	
				}
			String trans = transFiled.getText().trim();
			if(trans == null ||trans.length() == 0 ){
				JOptionPane.showConfirmDialog(CollectTransportPane.this, "地址名格式不正确请重新输入！");
				return ;	
				}
			String recever = receverFiled.getText().trim();
			if(recever == null ||recever.length() ==0){
				JOptionPane.showConfirmDialog(CollectTransportPane.this, "ip格式不正确请重新输入！");
				return ;	
				}
			int transportType = transStage.getSelectedIndex()+1;
			Transport transport = new Transport(new Date(),address,Transport.GATHER,trans,recever,transportType);
			JOptionPane.showConfirmDialog(CollectTransportPane.this, "采集物流数据成功！");
			list.add(transport);
		}
		
	}
}
