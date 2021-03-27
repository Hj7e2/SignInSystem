package client;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class HistoryPanel extends JPanel{
//	打卡历史集合
	DefaultListModel<situation> iltems1=new DefaultListModel<situation>();
//	请假历史集合
	DefaultListModel<situation> iltems2=new DefaultListModel<situation>();
	DBOperations dbo=new DBOperations();
	situation leave[] = new situation[100];
	situation card[] = new situation[100];
	String sqls;
	String dayly;
	private JTextField textcollege;
	private JTextField textname;
	private JTextField textid;
	private JTextField texttelephone;
	private JTextField textdept;
	public HistoryPanel(String id,String chtype) {
		JPanel jp = this;
		setOpaque(false);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 13, 407, 144);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0,120)));
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		JLabel labelcollege = new JLabel("学院：");
		labelcollege.setFont(new Font("宋体", Font.PLAIN, 18));
		labelcollege.setBounds(14, 13, 54, 27);
		panel.add(labelcollege);
		
		JLabel label_dept = new JLabel("专业-班级：");
		label_dept.setFont(new Font("宋体", Font.PLAIN, 18));
		label_dept.setBounds(14, 44, 104, 27);
		panel.add(label_dept);
		
		JLabel labelname = new JLabel("姓名：");
		labelname.setFont(new Font("宋体", Font.PLAIN, 18));
		labelname.setBounds(14, 71, 54, 27);
		panel.add(labelname);
		
		JLabel labelid = new JLabel("学号/工号：");
		labelid.setFont(new Font("宋体", Font.PLAIN, 18));
		labelid.setBounds(164, 71, 104, 27);
		panel.add(labelid);
		
		JLabel labeltelephone = new JLabel("联系方式：");
		labeltelephone.setFont(new Font("宋体", Font.PLAIN, 18));
		labeltelephone.setBounds(14, 100, 90, 27);
		panel.add(labeltelephone);
		
		textcollege = new JTextField();
		textcollege.setEditable(false);
		textcollege.setOpaque(false);
		textcollege.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textcollege.setFont(new Font("宋体", Font.BOLD, 16));
		textcollege.setBounds(70, 16, 323, 24);
		panel.add(textcollege);
		textcollege.setColumns(10);
		
		textname = new JTextField();
		textname.setEditable(false);
		textname.setFont(new Font("宋体", Font.BOLD, 16));
		textname.setOpaque(false);
		textname.setColumns(10);
		textname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textname.setBounds(70, 74, 90, 24);
		panel.add(textname);
		
		textid = new JTextField();
		textid.setEditable(false);
		textid.setFont(new Font("宋体", Font.BOLD, 16));
		textid.setOpaque(false);
		textid.setColumns(10);
		textid.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textid.setBounds(258, 74, 135, 24);
		panel.add(textid);
		
		texttelephone = new JTextField();
		texttelephone.setFont(new Font("宋体", Font.BOLD, 16));
		texttelephone.setOpaque(false);
		texttelephone.setColumns(10);
		texttelephone.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		texttelephone.setBounds(103, 103, 290, 24);
		panel.add(texttelephone);
		
		textdept = new JTextField();
		textdept.setEditable(false);
		textdept.setOpaque(false);
		textdept.setFont(new Font("宋体", Font.BOLD, 16));
		textdept.setColumns(10);
		textdept.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textdept.setBounds(113, 47, 280, 24);
		panel.add(textdept);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setRequestFocusEnabled(false);
		scrollPane.setBounds(41, 195, 188, 368);
		add(scrollPane);
		
		JList list = new JList(card);
		list.setOpaque(false);
		list.setBackground(new Color(0,0,0,0));
		list.setFont(new Font("宋体", Font.PLAIN, 16));
		list.setRequestFocusEnabled(false);
		list.setBorder(new TitledBorder(null, "\u6253\u5361\u8BB0\u5F55", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(list);
		scrollPane.getViewport().setOpaque(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setOpaque(false);
		scrollPane_1.setRequestFocusEnabled(false);
		scrollPane_1.setBounds(260, 195, 188, 368);
		add(scrollPane_1);
		
		JList list_1 = new JList(leave);
		list_1.setOpaque(false);
		list_1.setBackground(new Color(0,0,0,0));
		list_1.setFont(new Font("宋体", Font.PLAIN, 16));
		list_1.setRequestFocusEnabled(false);
		list_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8BF7\u5047\u8BB0\u5F55", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_1.setViewportView(list_1);
		scrollPane_1.getViewport().setOpaque(false);
		

		
		sqls="select * from "+ chtype;
		dbo.DBoperations(sqls, 0);
		try {
			while(dbo.db.rs.next()) {
				if(id.equals(dbo.db.rs.getString("账号")))
				{
					textname.setText(dbo.db.rs.getString("姓名"));
					textcollege.setText(dbo.db.rs.getString("学院"));
					if(chtype.equals("学生")) {
						textdept.setText(dbo.db.rs.getString("专业")+"-"+dbo.db.rs.getString("班级"));
						textid.setText(dbo.db.rs.getString("学号"));
					}else {
						textdept.setText(dbo.db.rs.getString("专业"));
						textid.setText(dbo.db.rs.getString("工号"));
					}
					texttelephone.setText(dbo.db.rs.getString("联系方式"));
					break;
				}
			}
			
			sqls="select * from 打卡情况";
			dbo.DBoperations(sqls, 0);
			
			int x=0;
			while(dbo.db.rs.next()) {
				if(id.equals(dbo.db.rs.getString("账号")))
				{
					card[x] = new situation(dbo.db.rs.getString("日期"), dbo.db.rs.getString("异常情况"));
					iltems1.addElement(card[x]);
					x++;
				}
			}
			
			sqls="select * from 请假情况";
			dbo.DBoperations(sqls, 0);
			
			while(dbo.db.rs.next()) {
				if(id.equals(dbo.db.rs.getString("账号")))
				{
					leave[x++] = new situation(dbo.db.rs.getString("请假日期"), dbo.db.rs.getString("请假时间")+dbo.db.rs.getString("请假原因"));
					iltems2.addElement(leave[x]);
					x++;
				}
			}
			
			
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		dbo.db.dbclose();
		
		//创建Calendar对象
		Calendar cal=Calendar.getInstance();
		//用Calendar类提供的方法获取年、月、日、时、分
		int year  =cal.get(Calendar.YEAR);   //年
		int month =cal.get(Calendar.MONTH)+1;  //月  默认是从0开始  即1月获取到的是0
		int day   =cal.get(Calendar.DAY_OF_MONTH);  //日，即一个月中的第几天
		dayly = year+"-"+month+"-"+day;
	}
	
	class situation {
		String date;
		String str;
		situation(String date,String str){
			this.date = date;
			this.str = str;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getStr() {
			return str;
		}
		public void setStr(String str) {
			this.str = str;
		}
		@Override
		public String toString() {
			return "日期:" + date + ", 情况:" + str;
		}
		
	}
}

