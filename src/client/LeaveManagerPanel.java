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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LeaveManagerPanel extends JPanel{
	DefaultListModel<Person> iltems = new DefaultListModel<Person>();
	DBOperations dbo=new DBOperations();
	String sqls;
	String dayly;
	String name;
	Client client;
	int choice = -1;
	int perNumber = 0;
	int noHandNumber = 0;
	Person per[] = new Person[100];
	Person noHandPer[] = new Person[100];
	Person finalPer[] = new Person[100];
	String noHand[] = new String[100];
	String leavedate[] = new String[100];
	String leavetime[] = new String[100];
	String leavereason[] = new String[100];
	String finaldate[] = new String[100];
	String finaltime[] = new String[100];
	String finalreason[] = new String[100];
	private JTextField textcollege;
	private JTextField textname;
	private JTextField textid;
	private JTextField texttelephone;
	private JTextField textdept;
	private JTextField textdate;
	private JTextField texttime;
	private JTextField textReviewer;
	private JTextField textAuditdate;
	public LeaveManagerPanel(Client client,String id,String chtype) {
		JPanel jp = this;
		this.client = client;
//		从用户表拿到身份
		sqls="select * from 教师";
		dbo.DBoperations(sqls, 0);
		try {
			while(dbo.db.rs.next()) {
				if(id.equals(dbo.db.rs.getString("账号"))){
					name = dbo.db.rs.getString("姓名");
				}
			}
		} catch (SQLException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		dbo.db.dbclose();
		
		setOpaque(false);
//		setBackground(Color.white);
		setSize(500, 620);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(173, 29, 313, 488);
		panel_1.setToolTipText("");
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0,120)));
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel labeldate = new JLabel("请假日期：");
		labeldate.setFont(new Font("宋体", Font.PLAIN, 18));
		labeldate.setBounds(14, 145, 90, 18);
		panel_1.add(labeldate);
		
		textdate = new JTextField();
		textdate.setEditable(false);
		textdate.setFont(new Font("宋体", Font.BOLD, 16));
		textdate.setOpaque(false);
		textdate.setBounds(106, 143, 196, 24);
		panel_1.add(textdate);
		textdate.setColumns(10);
		
		JLabel labelreason = new JLabel("请假原因：");
		labelreason.setFont(new Font("宋体", Font.PLAIN, 18));
		labelreason.setBounds(14, 215, 90, 18);
		panel_1.add(labelreason);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 246, 288, 76);
		scrollPane.setOpaque(false);
		panel_1.add(scrollPane);
		
		JTextArea txtreason = new JTextArea();
		txtreason.setEditable(false);
		txtreason.setFont(new Font("宋体", Font.BOLD, 16));
		txtreason.setBackground(Color.WHITE);
		txtreason.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);	
		scrollPane.setViewportView(txtreason);	
		
		JLabel labeltime = new JLabel("请假时间：");
		labeltime.setFont(new Font("宋体", Font.PLAIN, 18));
		labeltime.setBounds(14, 184, 90, 18);
		panel_1.add(labeltime);
		
		texttime = new JTextField();
		texttime.setEditable(false);
		texttime.setFont(new Font("宋体", Font.BOLD, 16));
		texttime.setOpaque(false);
		texttime.setBounds(106, 182, 196, 24);
		panel_1.add(texttime);
		texttime.setColumns(10);
		
		JLabel labeltelephone = new JLabel("联系方式：");
		labeltelephone.setBounds(14, 104, 90, 27);
		panel_1.add(labeltelephone);
		labeltelephone.setFont(new Font("宋体", Font.PLAIN, 18));
		
		texttelephone = new JTextField();
		texttelephone.setEditable(false);
		texttelephone.setBounds(106, 107, 196, 24);
		panel_1.add(texttelephone);
		texttelephone.setFont(new Font("宋体", Font.BOLD, 16));
		texttelephone.setOpaque(false);
		texttelephone.setColumns(10);
		texttelephone.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		
		JLabel labelname = new JLabel("姓名：");
		labelname.setBounds(14, 74, 54, 27);
		panel_1.add(labelname);
		labelname.setFont(new Font("宋体", Font.PLAIN, 18));
		
		textname = new JTextField();
		textname.setEditable(false);
		textname.setBounds(62, 77, 72, 24);
		panel_1.add(textname);
		textname.setFont(new Font("宋体", Font.BOLD, 16));
		textname.setOpaque(false);
		textname.setColumns(10);
		textname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		
		JLabel labeldept = new JLabel("专业-班级：");
		labeldept.setBounds(14, 44, 104, 27);
		panel_1.add(labeldept);
		labeldept.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel labelcollege = new JLabel("学院：");
		labelcollege.setBounds(14, 13, 54, 27);
		panel_1.add(labelcollege);
		labelcollege.setFont(new Font("宋体", Font.PLAIN, 18));
		
		textcollege = new JTextField();
		textcollege.setEditable(false);
		textcollege.setBounds(72, 16, 230, 24);
		panel_1.add(textcollege);
		textcollege.setOpaque(false);
		textcollege.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textcollege.setFont(new Font("宋体", Font.BOLD, 16));
		textcollege.setColumns(10);
		
		textdept = new JTextField();
		textdept.setEditable(false);
		textdept.setBounds(106, 47, 196, 24);
		panel_1.add(textdept);
		textdept.setOpaque(false);
		textdept.setFont(new Font("宋体", Font.BOLD, 16));
		textdept.setColumns(10);
		textdept.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		
		JLabel labelid = new JLabel("学号：");
		labelid.setBounds(148, 74, 54, 27);
		panel_1.add(labelid);
		labelid.setFont(new Font("宋体", Font.PLAIN, 18));
		
		textid = new JTextField();
		textid.setEditable(false);
		textid.setBounds(201, 77, 101, 24);
		panel_1.add(textid);
		textid.setFont(new Font("宋体", Font.BOLD, 16));
		textid.setOpaque(false);
		textid.setColumns(10);
		textid.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		
		JLabel labelReviewer = new JLabel("审核人：");
		labelReviewer.setFont(new Font("宋体", Font.PLAIN, 18));
		labelReviewer.setBounds(106, 416, 85, 21);
		panel_1.add(labelReviewer);
		
		textReviewer = new JTextField();
		textReviewer.setEditable(false);
		textReviewer.setFont(new Font("宋体", Font.BOLD, 16));
		textReviewer.setOpaque(false);
		textReviewer.setBounds(181, 415, 121, 24);
		panel_1.add(textReviewer);
		textReviewer.setColumns(10);
		
		JLabel labelComments = new JLabel("批语：");
		labelComments.setFont(new Font("宋体", Font.PLAIN, 18));
		labelComments.setBounds(14, 335, 72, 18);
		panel_1.add(labelComments);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setOpaque(false);
		scrollPane_2.setBounds(14, 358, 288, 47);
		panel_1.add(scrollPane_2);
		
		JTextArea textComments = new JTextArea();
		textComments.setLineWrap(true);
		textComments.setFont(new Font("宋体", Font.BOLD, 16));
		textComments.setOpaque(false);
		scrollPane_2.getViewport().setOpaque(false);	
		scrollPane_2.setViewportView(textComments);
		
		JLabel labelAuditdate = new JLabel("审核日期：");
		labelAuditdate.setFont(new Font("宋体", Font.PLAIN, 18));
		labelAuditdate.setBounds(90, 455, 101, 18);
		panel_1.add(labelAuditdate);
		
		textAuditdate = new JTextField();
		textAuditdate.setEditable(false);
		textAuditdate.setFont(new Font("宋体", Font.BOLD, 16));
		textAuditdate.setOpaque(false);
		textAuditdate.setBounds(181, 452, 121, 24);
		panel_1.add(textAuditdate);
		textAuditdate.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setOpaque(false);
		scrollPane_1.setBounds(14, 29, 145, 488);
		add(scrollPane_1);
		
		JList list = new JList(iltems);
		list.setBackground(new Color(0,0,0,0));
		list.setFont(new Font("宋体", Font.PLAIN, 16));
		list.setBorder(new TitledBorder(null, "\u8BF7\u5047\u4EBA\u5458", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 获取被选中的选项索引
                int index = list.getSelectedIndex();
                //鼠标选择响应两次（点击时，释放点击时），如果在点击时
                if (e.getValueIsAdjusting()) {   
                	choice = index;
                	textcollege.setText(finalPer[index].getCollege());
                	textdept.setText(finalPer[index].getMajor()+finalPer[index].getClasses());
                	textid.setText(finalPer[index].getId());
                	textname.setText(finalPer[index].getName());
                	texttelephone.setText(finalPer[index].getTelephone());
                	textdate.setText(finaldate[index]);
                	texttime.setText(finaltime[index]);
                	txtreason.setText(finalreason[index]);
                }
			}

		});
		list.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setViewportView(list);
		
		JButton refresh = new JButton("刷新");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check(id,chtype);
			}
		});
		refresh.setBounds(75, 550, 113, 27);
		add(refresh);
		
		JButton approval = new JButton("审批");
		approval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(choice == -1)
					JOptionPane.showMessageDialog(jp, "请选择正确的目标","提示",JOptionPane.WARNING_MESSAGE);
				else
					client.transMess(finalPer[choice].getZhanghao()+"@qingjia"+textComments.getText()+"@qingjia"+dayly+"@qingjia");
				sqls = "update 请假情况 set 是否处理 = '是' where 账号 = '"+ finalPer[choice].getZhanghao()+ "'";
				dbo.DBoperations(sqls, 1);
			}
		});
		approval.setBounds(290, 550, 113, 27);
		add(approval);
		
		//创建Calendar对象
		Calendar cal=Calendar.getInstance();
		//用Calendar类提供的方法获取年、月、日、时、分
		int year  =cal.get(Calendar.YEAR);   //年
		int month =cal.get(Calendar.MONTH)+1;  //月  默认是从0开始  即1月获取到的是0
		int day   =cal.get(Calendar.DAY_OF_MONTH);  //日，即一个月中的第几天
		dayly = year+"-"+month+"-"+day;
		textAuditdate.setText(dayly);
		textReviewer.setText(name);
		
		check(id,chtype);
	}
	
	public void check(String id,String chtype) {
		perNumber = 0;
		noHandNumber = 0;
		
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
			
			sqls = "select * from 请假情况";
			dbo.DBoperations(sqls, 0);

			while(dbo.db.rs.next()) {						
				if(dbo.db.rs.getString("是否处理").equals("否")) {
					noHand[noHandNumber] = dbo.db.rs.getString("账号");
					leavedate[noHandNumber] = dbo.db.rs.getString("请假日期");
					leavetime[noHandNumber] = dbo.db.rs.getString("请假时间");
					leavereason[noHandNumber] = dbo.db.rs.getString("请假原因");
					noHandNumber++;
					}
				}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		dbo.db.dbclose();
		
		int x = 0;
		List<String> list = Arrays.asList(noHand);
		for(int i=0;i<perNumber;i++) {
			if(list.contains(per[i].getZhanghao()))
				noHandPer[x++] = per[i];
		}
		
		String ucollege = "x";//初始化 不能为空和null
		String ugrade = "x";
		
		iltems.clear();
		switch(chtype){
		case "校长":
			x = 0;
			for(int i=0;i<noHandNumber;i++){
				finalPer[x] = noHandPer[i];
				iltems.addElement(finalPer[x]);
				finaldate[x] = leavedate[i];
				finaltime[x] = leavetime[i];
				finalreason[x++] = leavereason[i];
			}
			
			break;
		case "院长":
			for(int i=0;i<perNumber;i++)
				if(per[i].getZhanghao().equals(id))
					ucollege = per[i].getCollege();
			
			x = 0;
			for(int i=0;i<noHandNumber;i++) 
				if(noHandPer[i].getCollege().equals(ucollege)&&!noHandPer[i].getZhanghao().equals(id))
				{
					finalPer[x] = noHandPer[i];
					iltems.addElement(finalPer[x]);
					finaldate[x] = leavedate[i];
					finaltime[x] = leavetime[i];
					finalreason[x++] = leavereason[i];
				}
			
			break;
		case "教师":
			for(int i=0;i<perNumber;i++)
				if(per[i].getZhanghao().equals(id)) {
					ucollege = per[i].getCollege();
					ugrade = per[i].getGrade();
				}
			
			x = 0;
			for(int i=0;i<noHandNumber;i++) 
				if(noHandPer[i].getCollege().equals(ucollege)&&noHandPer[i].getGrade().equals(ugrade)&&!noHandPer[i].getChtype().equals("教师"))
				{
					finalPer[x] = noHandPer[i];
					iltems.addElement(finalPer[x]);
					finaldate[x] = leavedate[i];
					finaltime[x] = leavetime[i];
					finalreason[x++] = leavereason[i];
				}
			
			break;
		}
	}
}
