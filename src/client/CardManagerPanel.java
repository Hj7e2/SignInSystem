package client;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import currency.Person;
import dao.DBOperations;

import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JList;

public class CardManagerPanel extends JPanel{
//	未打卡人员集合
	DefaultListModel<Person> iltems1=new DefaultListModel<Person>();
//	异常人员集合
	DefaultListModel<Person> iltems2=new DefaultListModel<Person>();
	DBOperations dbo=new DBOperations();
	String sqls;
	String dayly;
//	已打卡人员id
	String readyCard[] = new String[100];
//	异常人员id
	String situation[] = new String[100];
//	异常情况
	String state[] = new String[100];
//	因为校长，院长，教师权限不同所以要再开一个数组
	String finalstate[] = new String[100];
	Person noCardPer[] = new Person[100];
	Person situationPer[] = new Person[100];
	Person per[] = new Person[100];
	Person finalcard[] = new Person[100];
	Person finalsituation[] = new Person[100];
	int perNumber = 0;
	int readyCardNumber = 0;
	int noCardNumber = 0;
	int situationNumber = 0;
	int finalCardNumber = 0;
	int finalSituationNumber = 0;
	int choice = -1;
	Client client;
	private JTextField textcollege;
	private JTextField textname;
	private JTextField textid;
	private JTextField texttelphone;
	private JTextField textdept;
	private JTextArea textsituation;
	private JList listsituation;
	private JList listnocard;
	public CardManagerPanel(Client client,String id,String chtype) {
		JPanel jp = this;
		this.client = client;
		setOpaque(false);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(32, 38, 177, 253);
		scrollPane.setOpaque(false);
		add(scrollPane);
		
		listnocard = new JList(iltems1);
		listnocard.setBackground(new Color(0,0,0,0));
		listnocard.setFont(new Font("宋体", Font.PLAIN, 16));
		listnocard.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 获取被选中的选项索引
                int index = listnocard.getSelectedIndex();
                //鼠标选择响应两次（点击时，释放点击时），如果在点击时
                if (e.getValueIsAdjusting()) {   
                	choice = index;
    				listsituation.clearSelection();//取消另一个jlist的焦点
                	textcollege.setText(finalcard[index].getCollege());
                	textdept.setText(finalcard[index].getMajor()+finalcard[index].getClasses());
                	textid.setText(finalcard[index].getId());
                	textname.setText(finalcard[index].getName());
                	texttelphone.setText(finalcard[index].getTelephone());
                	textsituation.setText("");
                }
			}
		});
		listnocard.setOpaque(false);
		listnocard.setBorder(new TitledBorder(null, "\u672A\u6253\u5361\u4EBA\u5458", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(listnocard);
		scrollPane.getViewport().setOpaque(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(262, 38, 177, 253);
		scrollPane_1.setOpaque(false);
		add(scrollPane_1);
		
		listsituation = new JList(iltems2);
		listsituation.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 获取被选中的选项索引
                int index = listsituation.getSelectedIndex();
                //鼠标选择响应两次（点击时，释放点击时），如果在点击时
                if (e.getValueIsAdjusting()) {  
                	choice = -1;
                	listnocard.clearSelection();
                	textcollege.setText(finalsituation[index].getCollege());
                	textdept.setText(finalsituation[index].getMajor()+finalsituation[index].getClasses());
                	textid.setText(finalsituation[index].getId());
                	textname.setText(finalsituation[index].getName());
                	texttelphone.setText(finalsituation[index].getTelephone());
                	textsituation.setText(finalstate[index]);
                }
			}
		});
		listsituation.setFont(new Font("宋体", Font.PLAIN, 16));
		listsituation.setBackground(new Color(0,0,0,0));
		listsituation.setOpaque(false);
		listsituation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5F02\u5E38\u4EBA\u5458", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_1.setViewportView(listsituation);
		scrollPane_1.getViewport().setOpaque(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0,120)));
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(32, 347, 407, 232);
		add(panel);
		
		JLabel labelcollege = new JLabel("学院：");
		labelcollege.setFont(new Font("宋体", Font.PLAIN, 18));
		labelcollege.setBounds(14, 13, 54, 27);
		panel.add(labelcollege);
		
		JLabel labeldept = new JLabel("专业-班级：");
		labeldept.setFont(new Font("宋体", Font.PLAIN, 18));
		labeldept.setBounds(14, 44, 104, 27);
		panel.add(labeldept);
		
		JLabel labelname = new JLabel("姓名：");
		labelname.setFont(new Font("宋体", Font.PLAIN, 18));
		labelname.setBounds(14, 71, 54, 27);
		panel.add(labelname);
		
		JLabel labelid = new JLabel("学号/工号：");
		labelid.setFont(new Font("宋体", Font.PLAIN, 18));
		labelid.setBounds(164, 71, 104, 27);
		panel.add(labelid);
		
		JLabel labeltelphone = new JLabel("联系方式：");
		labeltelphone.setFont(new Font("宋体", Font.PLAIN, 18));
		labeltelphone.setBounds(14, 100, 90, 27);
		panel.add(labeltelphone);
		
		JLabel labelsituation = new JLabel("异常情况：");
		labelsituation.setFont(new Font("宋体", Font.PLAIN, 18));
		labelsituation.setBounds(14, 130, 90, 27);
		panel.add(labelsituation);
		
		textcollege = new JTextField();
		textcollege.setOpaque(false);
		textcollege.setFont(new Font("宋体", Font.BOLD, 16));
		textcollege.setEditable(false);
		textcollege.setColumns(10);
		textcollege.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textcollege.setBounds(70, 16, 323, 24);
		panel.add(textcollege);
		
		textname = new JTextField();
		textname.setOpaque(false);
		textname.setFont(new Font("宋体", Font.BOLD, 16));
		textname.setEditable(false);
		textname.setColumns(10);
		textname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textname.setBounds(70, 74, 80, 24);
		panel.add(textname);
		
		textid = new JTextField();
		textid.setOpaque(false);
		textid.setFont(new Font("宋体", Font.BOLD, 16));
		textid.setEditable(false);
		textid.setColumns(10);
		textid.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textid.setBounds(273, 74, 120, 24);
		panel.add(textid);
		
		texttelphone = new JTextField();
		texttelphone.setEditable(false);
		texttelphone.setOpaque(false);
		texttelphone.setFont(new Font("宋体", Font.BOLD, 16));
		texttelphone.setColumns(10);
		texttelphone.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		texttelphone.setBounds(103, 103, 290, 24);
		panel.add(texttelphone);
		
		textdept = new JTextField();
		textdept.setOpaque(false);
		textdept.setFont(new Font("宋体", Font.BOLD, 16));
		textdept.setEditable(false);
		textdept.setColumns(10);
		textdept.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textdept.setBounds(113, 47, 280, 24);
		panel.add(textdept);
		
		textsituation = new JTextArea();
		textsituation.setEditable(false);
		textsituation.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textsituation.setLineWrap(true);
		textsituation.setOpaque(false);
		textsituation.setFont(new Font("宋体", Font.BOLD, 16));
		textsituation.setBounds(113, 140, 280, 79);
		panel.add(textsituation);
		
		JButton Refresh = new JButton("刷新");
		Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check(id,chtype);
			}
		});
		Refresh.setBounds(32, 304, 113, 27);
		add(Refresh);
		
		JButton remindall = new JButton("一键提醒");
		remindall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<finalCardNumber;i++) {
//					向服务器发送消息
					client.transMess(finalcard[i].getZhanghao()+"@tixing");
				}
			}
		});
		remindall.setBounds(326, 304, 113, 27);
		add(remindall);
		
		JButton remind = new JButton("提醒");
		remind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(choice == -1)
//					如果没有选择人
					JOptionPane.showMessageDialog(jp, "请选择正确的目标","提示",JOptionPane.WARNING_MESSAGE);
				else
					client.transMess(finalcard[choice].getZhanghao()+"@tixing");
			}
		});
		remind.setBounds(180, 304, 113, 27);
		add(remind);
		
		//创建Calendar对象
		Calendar cal=Calendar.getInstance();
		//用Calendar类提供的方法获取年、月、日、时、分
		int year  =cal.get(Calendar.YEAR);   //年
		int month =cal.get(Calendar.MONTH)+1;  //月  默认是从0开始  即1月获取到的是0
		int day   =cal.get(Calendar.DAY_OF_MONTH);  //日，即一个月中的第几天
		dayly = year+"-"+month+"-"+day;
		
		check(id,chtype);
	}
	
	public void check(String id,String chtype) {
		perNumber = 0;
		readyCardNumber = 0;
		noCardNumber = 0;
		situationNumber = 0;
		finalCardNumber = 0;
		finalSituationNumber = 0;
		readyCard = new String[100];
		situation = new String[100];
		state = new String[100];
		finalstate = new String[100];
		noCardPer = new Person[100];
		situationPer = new Person[100];
		per = new Person[100];
		finalcard = new Person[100];
		finalsituation = new Person[100];
		
		
		try {
			sqls = "select * from 教师";
			dbo.DBoperations(sqls, 0);
			
			while(dbo.db.rs.next()) {
				String uzhanghao = dbo.db.rs.getString("账号");
				String uid = dbo.db.rs.getString("工号");
				String uname = dbo.db.rs.getString("姓名");
				String ucollege = dbo.db.rs.getString("学院");
				String umajor = dbo.db.rs.getString("专业");
				String utel = dbo.db.rs.getString("联系方式");
				String ugrade = dbo.db.rs.getString("年级");
				per[perNumber] = new Person(uzhanghao,uid,uname,ucollege,umajor,ugrade,utel,"教师");
				perNumber++;
			}
			
			sqls = "select * from 学生";
			dbo.DBoperations(sqls, 0);
			
			while(dbo.db.rs.next()) {
				String uzhanghao = dbo.db.rs.getString("账号");
				String uid = dbo.db.rs.getString("学号");
				String uname = dbo.db.rs.getString("姓名");
				String ucollege = dbo.db.rs.getString("学院");
				String umajor = dbo.db.rs.getString("专业");
				String utel = dbo.db.rs.getString("联系方式");
				String ugrade = dbo.db.rs.getString("年级");
				String uclass = dbo.db.rs.getString("班级");
				per[perNumber] = new Person(uzhanghao,uid,uname,ucollege,umajor,ugrade,uclass,utel,"学生");
				perNumber++;
			}
			
			sqls = "select * from 打卡情况";
			dbo.DBoperations(sqls, 0);

			while(dbo.db.rs.next()) {						
				if(dbo.db.rs.getString("日期").equals(dayly)) {
					readyCard[readyCardNumber] = dbo.db.rs.getString("账号");
					readyCardNumber++;
					if(!dbo.db.rs.getString("异常情况").equals("无")) {
						situation[situationNumber] = dbo.db.rs.getString("账号");
						state[situationNumber] = dbo.db.rs.getString("异常情况");
						situationNumber++;
					}
				}
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		dbo.db.dbclose();
		
		int x = 0;
		List<String> list1 = Arrays.asList(readyCard);
		List<String> list2 = Arrays.asList(situation);
		for(int i=0;i<perNumber;i++) {
			if(!list1.contains(per[i].getZhanghao()))
				noCardPer[noCardNumber++] = per[i];
			if(list2.contains(per[i].getZhanghao()))
				situationPer[x++] = per[i];
		}
		
		String ucollege = "x";//初始化 不能为空和null
		String ugrade = "x";
		
		iltems1.clear();
		iltems2.clear();
		switch(chtype){
		case "校长":
			for(int i=0;i<noCardNumber;i++){
				finalcard[finalCardNumber++] = noCardPer[i];
				iltems1.addElement(finalcard[finalCardNumber-1]);
			}

			for(int i=0;i<situationNumber;i++){
				finalsituation[finalSituationNumber] = situationPer[i];
				iltems2.addElement(finalsituation[finalSituationNumber]);
				finalstate[finalSituationNumber++] = state[i];
			}
			
			break;
		case "院长":
			for(int i=0;i<perNumber;i++)
				if(per[i].getZhanghao().equals(id))
					ucollege = per[i].getCollege();
			
			for(int i=0;i<noCardNumber;i++) 
				if(noCardPer[i].getCollege().equals(ucollege))
				{
					finalcard[finalCardNumber++] = noCardPer[i];
					iltems1.addElement(finalcard[finalCardNumber-1]);
				}

			for(int i=0;i<situationNumber;i++)
				if(situationPer[i].getCollege().equals(ucollege))
				{
					finalsituation[finalSituationNumber] = situationPer[i];
					iltems2.addElement(finalsituation[finalSituationNumber]);
					finalstate[finalSituationNumber++] = state[i];
				}
			
			break;
		case "教师":
			for(int i=0;i<perNumber;i++)
				if(per[i].getZhanghao().equals(id)) {
					ucollege = per[i].getCollege();
					ugrade = per[i].getGrade();
				}
			
			for(int i=0;i<noCardNumber;i++) 
				if(noCardPer[i].getCollege().equals(ucollege)&&noCardPer[i].getGrade().equals(ugrade))
				{
					finalcard[finalCardNumber++] = noCardPer[i];
					iltems1.addElement(finalcard[finalCardNumber-1]);
				}
			
			for(int i=0;i<situationNumber;i++)
				if(situationPer[i].getCollege().equals(ucollege)&&situationPer[i].getGrade().equals(ugrade))
				{
					finalsituation[finalSituationNumber] = situationPer[i];
					iltems2.addElement(finalsituation[finalSituationNumber]);
					finalstate[finalSituationNumber++] = state[i];
				}
			
			break;
		}
	}
}
