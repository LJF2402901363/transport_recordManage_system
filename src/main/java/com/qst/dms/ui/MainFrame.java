package com.qst.dms.ui;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import com.qst.dms.data.LogRecStore;
import com.qst.dms.data.TransportStore;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.entity.User;
import com.qst.dms.factory.DataAnalyseFactory;
import com.qst.dms.factory.ServiceFactory;
import com.qst.dms.gather.LogRecAnalyse;
import com.qst.dms.gather.TransportAnalyse;
import com.qst.dms.net.ClientThread;
import com.qst.dms.net.ServerThread;
import com.qst.dms.service.LogRecService;
import com.qst.dms.service.TransportService;
import com.qst.dms.tablemodel.LogRecJTableModel;
import com.qst.dms.tablemodel.TransportJTableModel;
import com.qst.dms.util.ConstantsConfig;

/**
 * @author 陌意随影
 TODO :日志操作主界面
 *2019年11月30日  下午11:54:02
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int  WIDTH = 600;
	private static final int  HEIGHT = 550;
	private Container contentPanel = null;
	private JPanel    mainPanel = null;
	private JMenu memu_userInfo  = null;
	private JMenu memu_operation  = null;
	private JMenu memu2_help  = null;
	private JMenu collection_menu = null;
	private JMenuItem item_logrec = null;
	private JMenuItem item_trans = null;
	private JMenu matched_menu = null;
	private JMenuItem item_matchedlogrec = null;
	private JMenuItem item_matchedtrans = null;
	private JMenu save_menu = null;
	private JMenuItem item_savetrans = null;
	private JMenuItem item_savetlogrec = null;
	private JMenu send_menu = null;
	private JMenuItem item_sendlogrec = null;
	private JMenuItem item_sendtrans = null;
	private JMenu show_menu = null;
	private JMenuItem item_showtlogrec = null;
	private JMenuItem item_showtrans = null;
	private JMenu exit_menu = null;
	private JButton btn_collection = null;
	private JButton btn_matchedLogrec = null;
	private JButton btn_matchedTrans =  null;
	private JButton btn_save = null;
	private JButton btn_send = null;
	private JButton btn_show = null;
	private JButton btn_query = null;
	private JTabbedPane tabbedPane = null;
	private CollectLogRecPane collectLogRecPane = null;
	private CollectTransportPane collectLogTransportPane = null;
	private UserInfoPane  userInfoPanel = null;
	private UpdateUserInfoPane updaUserInfoPanel = null;
	private BlankPanel  logRecBlankPanel = null;
	private BlankPanel  transBlankPanel = null;
	private JTable showLogRecTable = null;
	private LogRecJTableModel logRecJTableModel  =null;
	private JTable showTransTable = null;
	private TransportJTableModel transportJTableModel  =null;
	private LogRecService logRecService = null;
	private TransportService transportService = null; 
	private LogRecAnalyse logRecAnalyse = null;
	private TransportAnalyse transportAnalyse = null;
	private List<MatchedLogRec> matchedLogRecList = null;
	private List<MatchedTransport> matchedTransList = null;
	private LoginFrame loginFrame  = null;
	private JPopupMenu  logrecPopupMenu = null;
	private JPopupMenu  transPopupMenu = null;
	private User user = null;
	private  ClientThread clientThread = null;
	private ServerThread  serverThread = null;
	@SuppressWarnings("javadoc")
	public MainFrame(LoginFrame loginFrame,User user ){
		this.setTitle("Q-DMS系统客户端");
		this.setBounds(450, 150, WIDTH, HEIGHT);
		this.contentPanel = this.getContentPane();
		this.mainPanel = new JPanel(new FlowLayout());
		this.mainPanel.setBounds(0, 0, WIDTH, HEIGHT);
		this.contentPanel.add( this.mainPanel);
		this.logRecService = (LogRecService) ServiceFactory.getServiceImpl("LogRecService");
		this.transportService =(TransportService) ServiceFactory.getServiceImpl("TransportService");
		this.logRecAnalyse =(LogRecAnalyse) DataAnalyseFactory.getDataAnalyseImpl("LogRecAnalyse");
		this.transportAnalyse=(TransportAnalyse) DataAnalyseFactory.getDataAnalyseImpl("TransportAnalyse");
		this.user =user;
		this.loginFrame = loginFrame;
		this.serverThread  = new ServerThread(ConstantsConfig.PORT);
		this.clientThread = new ClientThread( ConstantsConfig.HOST, ConstantsConfig.PORT);
		startNetThread();
		
		initConponent();
		initConponentEvent();
		this.setResizable(false);
		this.setVisible(true);
		initWindowEvent(loginFrame);
    }
	private void startNetThread() {
		this.serverThread.start();
		this.clientThread.start();
	}
	private void initWindowEvent(LoginFrame loginFrame) {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainFrame.this.dispose();
				if(loginFrame != null){
					loginFrame.dispose();;
				}
			}
		});
	}
	private void initConponentEvent() {
		//初始化收集数据的事件
		initCollectionEvent();
		//初始化匹配数据
		initMatchedDataEvent();
		//初始化保存数据的事件
		initSaveDataEvent();
		//发送数据
		initSendDataEvent();
		//初始化显示数据的事件
		initShowDataEvent();
	}
	private void initSendDataEvent() {
		this.btn_send.addActionListener(e->{
			if (matchedTransList == null || matchedTransList.size() == 0) {
				JOptionPane.showConfirmDialog(MainFrame.this, "保存物流的记录为0！");
			} else {
				MainFrame.this.clientThread.sendMatchedTransport(matchedTransList);
				JOptionPane.showConfirmDialog(MainFrame.this, "成功保存物流记录" + matchedTransList.size() + "条");
				matchedTransList = null;
			}
			if (matchedLogRecList == null || matchedLogRecList.size() == 0) {
				JOptionPane.showConfirmDialog(MainFrame.this, "保存匹配日志的记录为0！");
			} else {
				MainFrame.this.clientThread.sendMatchedLogRec(matchedLogRecList );
				JOptionPane.showConfirmDialog(MainFrame.this, "成功保存日志记录" + matchedLogRecList.size() + "条");
				matchedLogRecList = null;
			}
		});
		this.item_sendlogrec.addActionListener(e->{
			if (matchedLogRecList == null || matchedLogRecList.size() == 0) {
				JOptionPane.showConfirmDialog(MainFrame.this, "保存匹配日志的记录为0！");
			} else {
				MainFrame.this.clientThread.sendMatchedLogRec(matchedLogRecList );
				JOptionPane.showConfirmDialog(MainFrame.this, "成功保存日志记录" + matchedLogRecList.size() + "条");
				matchedLogRecList = null;
			}
		});
         this.item_sendtrans.addActionListener(e->{
        	 if (matchedTransList == null || matchedTransList.size() == 0) {
 				JOptionPane.showConfirmDialog(MainFrame.this, "保存物流的记录为0！");
 			} else {
 				MainFrame.this.clientThread.sendMatchedTransport(matchedTransList);
 				JOptionPane.showConfirmDialog(MainFrame.this, "成功保存物流记录" + matchedTransList.size() + "条");
 				matchedTransList = null;
 			}
		});
		
	}
	private void initSaveDataEvent() {
		this.btn_save.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchedTransList == null || matchedTransList.size() == 0) {
					JOptionPane.showConfirmDialog(MainFrame.this, "保存物流的记录为0！");
				} else {
					FileDialog fileDialog = new FileDialog(MainFrame.this,"保存物流文件",FileDialog.SAVE);
					fileDialog.setVisible(true);
					String dir = fileDialog.getDirectory();
					if(dir != null || dir.length() != 0){
						String path = dir+fileDialog.getFile();
						transportService.saveToLocal(path, matchedTransList);
						JOptionPane.showConfirmDialog(MainFrame.this, "成功保存物流记录" + matchedTransList.size() + "条");
					}
//					matchedTransList = null;
				}
				if (matchedLogRecList == null || matchedLogRecList.size() == 0) {
					JOptionPane.showConfirmDialog(MainFrame.this, "保存匹配日志的记录为0！");
				} else {
					FileDialog fileDialog = new FileDialog(MainFrame.this,"保存日志文件",FileDialog.SAVE);
					fileDialog.setVisible(true);
					String dir = fileDialog.getDirectory();
					if(dir != null || dir.length() != 0){
						String path = dir+fileDialog.getFile();
						logRecService.saveToLocal(path, matchedLogRecList);
						JOptionPane.showConfirmDialog(MainFrame.this, "成功保存日志记录" + matchedLogRecList.size() + "条");
					}
//					matchedLogRecList = null;
				}
			}
		});
		this.item_savetlogrec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchedLogRecList == null || matchedLogRecList.size() == 0) {
					JOptionPane.showConfirmDialog(MainFrame.this, "保存匹配日志的记录为0！");
				} else {
					FileDialog fileDialog = new FileDialog(MainFrame.this,"保存日志文件",FileDialog.SAVE);
					fileDialog.setVisible(true);
					String dir = fileDialog.getDirectory();
					if(dir != null || dir.length() != 0){
						String path = dir+fileDialog.getFile();
						logRecService.saveToLocal(path, matchedLogRecList);
						JOptionPane.showConfirmDialog(MainFrame.this, "成功保存日志记录" + matchedLogRecList.size() + "条");
					};
					
//					matchedLogRecList = null;
				}
			}
		});

		this.item_savetrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchedTransList == null || matchedTransList.size() == 0) {
					JOptionPane.showConfirmDialog(MainFrame.this, "保存物流的记录为0！");
				} else {
					FileDialog fileDialog = new FileDialog(MainFrame.this,"保存物流文件",FileDialog.SAVE);
					fileDialog.setVisible(true);
					String dir = fileDialog.getDirectory();
					if(dir != null){
						String path = dir+fileDialog.getFile();
						transportService.saveToLocal(path,  matchedTransList);
						JOptionPane.showConfirmDialog(MainFrame.this, "成功保存物流记录" + matchedTransList.size() + "条");
					};
					//matchedTransList = null;
				}

			}
		});
	}
	private void initShowDataEvent() {
		this.showLogRecTable = new JTable();
		showLogRecTable.setOpaque(false);
		this.showLogRecTable.setPreferredScrollableViewportSize(new Dimension(580,400));
		this.showLogRecTable.setBorder(BorderFactory.createEmptyBorder());
		this.logRecJTableModel = new LogRecJTableModel();
		this.showLogRecTable.setModel(logRecJTableModel);
		//开启自动刷新数据的线程
		new Thread(new UpdateLogRecTableData(logRecJTableModel)).start();
		 this.logrecPopupMenu =new JPopupMenu();
		 JMenuItem deleteItem = new JMenuItem("删除选中的数据");
		 deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int [] selections  = showLogRecTable.getSelectedRows();
				for(int t:selections){
					String value = showLogRecTable.getValueAt(t, 0).toString().trim();
					int id = Integer.parseInt(value);
					if(id == -1){
						return;
					}
					Integer result = JOptionPane.showConfirmDialog(MainFrame.this, "该删除操作会将匹配的日志数据一起删除，且不可恢复，是否确认删除？");
					if(result == 0){
						logRecService.remove(id);
						logRecJTableModel.update(logRecService.readLogfromDataBase());
					}
				}
			}
		});
		 this.logrecPopupMenu.add(deleteItem, 0);
		this.showLogRecTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logrecPopupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
		});
		showLogRecTable.getColumnModel().getColumn(1).setPreferredWidth(150);;
		JScrollPane js1 = new JScrollPane(this.showLogRecTable);
		this.showTransTable = new JTable();
		this.showTransTable.setBorder(BorderFactory.createEmptyBorder());
		this.showTransTable.setPreferredScrollableViewportSize(new Dimension(580,420));
		this.transportJTableModel = new  TransportJTableModel();
		//开启自动刷新的物流日志的线程
		showTransTable.setModel(this.transportJTableModel);
		//开启自动刷新匹配物流日志的线程
		new Thread(new UpdateTansTableData(transportJTableModel)).start();
		this.showTransTable.getColumnModel().getColumn(1).setPreferredWidth(150);;
		JScrollPane js2 = new JScrollPane(showTransTable);
		this.btn_show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.removeAll();
				logRecJTableModel.update(logRecService.readLogfromDataBase());
				transportJTableModel.update(transportService.readTransportFromDataBase());
				tabbedPane.add("日志", js1);
				tabbedPane.add("物流", js2);
				
			}
		});
		this.item_showtlogrec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.removeAll();
				tabbedPane.add("日志", js1);
				tabbedPane.add("物流", js2);
				logRecJTableModel.update(logRecService.readLogfromDataBase());
				transportJTableModel.update(transportService.readTransportFromDataBase());
				tabbedPane.setSelectedIndex(0);
			}
		});
		transPopupMenu = new JPopupMenu();
		JMenuItem deleteTransItem = new JMenuItem("删除选中的数据");
		this.transPopupMenu.add(deleteTransItem, 0);
		showTransTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				transPopupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		deleteTransItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int [] selections  = showTransTable.getSelectedRows();
				for(int t:selections){
					String value = showTransTable.getValueAt(t, 0).toString().trim();
					int sendid = Integer.parseInt(value);
					if(sendid == -1){
						return;
					}
					Integer result = JOptionPane.showConfirmDialog(MainFrame.this, "该删除操作会将匹配的物流数据一起删除，且不可恢复，是否确认删除？");
					if(result == 0){
						transportService.removeMatchedTransBySendid(sendid);
						transportJTableModel.update(transportService.readTransportFromDataBase());
					}
				}
			}
		});
		
		
		
    this.item_showtrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.removeAll();
				tabbedPane.add("日志", js1);
				tabbedPane.add("物流", js2);
				logRecJTableModel.update(logRecService.readLogfromDataBase());
				transportJTableModel.update(transportService.readTransportFromDataBase());
				tabbedPane.setSelectedIndex(1);
			}
		});
	}
	private void initMatchedDataEvent() {
		this.btn_matchedLogrec.addActionListener(new MatchedLogRecActionListener());
		this.item_matchedlogrec.addActionListener(new MatchedLogRecActionListener());
		this.item_matchedtrans.addActionListener(new MatchedTransportActionListener());
		this.btn_matchedTrans.addActionListener(new MatchedTransportActionListener());
	}
	private class MatchedTransportActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			transportAnalyse.setDatas(TransportStore.getTrans());
			transportAnalyse.doFilter();
			matchedTransList  = transportAnalyse.matchData();
			if(matchedTransList==null||matchedTransList.size() == 0){
				JOptionPane.showConfirmDialog(MainFrame.this, "匹配物流成功的记录为0！");
			}else{
				JOptionPane.showConfirmDialog(MainFrame.this, "匹配物流成功的记录为"+matchedTransList.size()+"条！");
			}
		}
		
	}
	private class MatchedLogRecActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//设置要分析的数据
			logRecAnalyse.setDatas(LogRecStore.getLogs());
			logRecAnalyse.doFilter();
             matchedLogRecList = logRecAnalyse.matchData();
				if(matchedLogRecList == null||matchedLogRecList.size() == 0){
					JOptionPane.showConfirmDialog(MainFrame.this, "匹配日志成功的记录为0！");
				}else{
					JOptionPane.showConfirmDialog(MainFrame.this, "匹配日志成功的记录为"+matchedLogRecList.size()+"条！");
				}
		}
		
	}
	private void initCollectionEvent() {
		this.item_logrec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			collectLogTransportPane = new CollectTransportPane(500, 420);
			tabbedPane.removeAll();
			tabbedPane.add("日志",collectLogRecPane);
			tabbedPane.add("物流",collectLogTransportPane);
			tabbedPane.setSelectedIndex(0);
			}
		});
		this.item_trans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			tabbedPane.removeAll();
			tabbedPane.add("日志",collectLogRecPane);
			tabbedPane.add("物流",collectLogTransportPane);
			tabbedPane.setSelectedIndex(1);
			}
		});
		btn_collection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			tabbedPane.removeAll();
			tabbedPane.add("日志",collectLogRecPane);
			tabbedPane.add("物流",collectLogTransportPane);
			}
		});
	}
	private void initConponent() {
		JPanel menupanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		menupanel1.setPreferredSize(new Dimension(580,40));
		JMenuBar menuBar = new JMenuBar();
		this.memu_operation = new JMenu("系统操作");
		this.memu_userInfo = new JMenu("个人信息");
		initInfoMenuEvent();
		//初始化操作控件
		initOperationMenu();
		this.memu_operation.setFont(new Font("微软雅黑",Font.BOLD,16));
		this.memu2_help = new JMenu("帮助");
		this.memu2_help.setFont(new Font("微软雅黑",Font.BOLD,16));
		 menuBar.add(this.memu_userInfo);
		 menuBar.add(this.memu_operation);
		 menuBar.add( this.memu2_help);
		 menupanel1.add(menuBar);
		 this.mainPanel.add( menupanel1);
		//初始化工具条
		 initToolBarConponent();
		 //初始化选项面板
		 initTabbedPane();
	}
	private void initInfoMenuEvent() {
		this.memu_userInfo.setFont(new Font("微软雅黑",Font.BOLD,16));
		 this.userInfoPanel = new UserInfoPane(500, 420 ,this.user);
		 this.updaUserInfoPanel = new UpdateUserInfoPane(500, 420 ,this.user);
		this.memu_userInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.removeAll();
				tabbedPane.add("个人信息", userInfoPanel);
				tabbedPane.add("修改个人信息", updaUserInfoPanel);
			}
		});
	}
	
	private void initTabbedPane() {
		this.tabbedPane = new JTabbedPane();
		this.logRecBlankPanel = new BlankPanel(500, 420);
		this.collectLogRecPane = new CollectLogRecPane(500, 420);
		tabbedPane.addTab("日志",this.logRecBlankPanel );
		this.transBlankPanel = new BlankPanel(500, 420);
		this.collectLogTransportPane = new CollectTransportPane(500, 420);
		this.tabbedPane.addTab("物流", this.transBlankPanel);
		this.mainPanel.add(this.tabbedPane);
	}
	private void initToolBarConponent() {
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.setPreferredSize(new Dimension(590, 40));
		panel2.setOpaque(false);
		JToolBar toolBar = new JToolBar();
		String collection_path = "src/images/collection.png";
		ImageIcon collection_icon = new ImageIcon(collection_path);
		this.btn_collection = new JButton(collection_icon);
		this.btn_collection.setBorder(BorderFactory.createEmptyBorder());
		toolBar.add(this.btn_collection);
		String matchedLogrec_path = "src/images/matchedLogrec.png";
		ImageIcon matchedLogrec_icon = new ImageIcon(matchedLogrec_path);
		this.btn_matchedLogrec = new JButton(matchedLogrec_icon);
		this.btn_matchedLogrec.setBorder(BorderFactory.createEmptyBorder());
		toolBar.add(this.btn_matchedLogrec);
		String matchedTrans_path = "src/images/matchedTrans.png";
		ImageIcon matchedTrans_icon = new ImageIcon(matchedTrans_path);
		this.btn_matchedTrans = new JButton(matchedTrans_icon);
		this.btn_matchedTrans.setBorder(BorderFactory.createEmptyBorder());
		toolBar.add(this.btn_matchedTrans);
	
		String saveData_path = "src/images/saveData.png";
		ImageIcon saveData_icon = new ImageIcon(saveData_path);
		this.btn_save = new JButton(saveData_icon);
		this.btn_save.setBorder(BorderFactory.createEmptyBorder());
		toolBar.add(this.btn_save);
		String send_path = "src/images/sendImg.png";
		ImageIcon send_icon = new ImageIcon(send_path);
		this.btn_send = new JButton(send_icon);
		this.btn_send.setBorder(BorderFactory.createEmptyBorder());
		toolBar.add(this.btn_send);
		String show_path = "src/images/showImg.png";
		ImageIcon show_icon = new ImageIcon(show_path);
		this.btn_show = new JButton(show_icon);
		this.btn_show.setBorder(BorderFactory.createEmptyBorder());
		toolBar.add(this.btn_show);
		this.btn_query = new JButton("查询");
		this.btn_query.setPreferredSize(new Dimension(30,50));
		JToolBar bar = new JToolBar();
		bar.add(btn_query);
		panel2.add(toolBar);
		this.mainPanel.add(panel2);
	}
	private void initOperationMenu() {
		Font font = new Font("微软雅黑", Font.BOLD, 14);
		this.collection_menu = new JMenu("采集数据");
		this.collection_menu.setFont(font);
		this.item_logrec = new JMenuItem("采集日志数据");
		this.item_logrec.setFont(font);
		this.collection_menu.add(this.item_logrec);
		this.item_trans = new JMenuItem("采集物流数据");
		this.item_trans.setFont(font);
		this.collection_menu.add(this.item_trans);
		this.matched_menu = new JMenu("匹配数据");
		this.matched_menu.setFont(font);
		this.item_matchedlogrec = new JMenuItem("匹配日志数据");
		this.item_matchedlogrec.setFont(font);
		this.matched_menu.add(this.item_matchedlogrec);
		this.item_matchedtrans = new JMenuItem("匹配物流数据");
		this.item_matchedtrans.setFont(font);
		this.matched_menu.add(this.item_matchedtrans);
		this.save_menu = new JMenu("保存数据");
		this.save_menu.setFont(font);
		this.item_savetlogrec = new JMenuItem("保存日志数据");
		this.item_savetrans = new JMenuItem("保存物流数据");
		this.save_menu.add(item_savetlogrec);
		this.save_menu.add(this.item_savetrans);
		this.send_menu = new JMenu("发送数据");
		this.send_menu.setFont(font);
		this.item_sendlogrec = new JMenuItem("发送日志数据");
		this.item_sendtrans = new JMenuItem("发送物流数据");
		
		
		this.send_menu.add(this.item_sendlogrec);
		this.send_menu.add(this.item_sendtrans);
		this.show_menu = new JMenu("显示数据");
		this.show_menu.setFont(font);
		this.item_showtlogrec = new JMenuItem("显示日志数据");
		this.item_showtrans = new JMenuItem("显示物流数据");
		this.show_menu.add(this.item_showtlogrec);
		this.show_menu.add(this.item_showtrans);
		this.exit_menu = new JMenu("退出系统");
		this.exit_menu.setFont(font);
		this.memu_operation.add(this.collection_menu);
		this.memu_operation.add(this.matched_menu);
		this.memu_operation.add(this.save_menu);
		this.memu_operation.add(this.send_menu);
		this.memu_operation.add(this.show_menu);
		this.memu_operation.add(this.exit_menu);
		initEvent();
	}
	private void initEvent() {
		this.exit_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					 MainFrame.this.dispose();
					 if(loginFrame != null){
						 loginFrame.setVisible(true);
					 }
				}
			}
		});
}
	
	
}
