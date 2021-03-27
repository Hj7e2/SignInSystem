package client;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import dao.DBOperations;

import javax.swing.UIManager;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LeavePanel extends JPanel{
	
	DBOperations dbo=new DBOperations();
	String sqls;
	String dayly;
	private JTextField textcollege;
	private JTextField textname;
	private JTextField textid;
	private JTextField texttelephone;
	private JTextField textdept;
	private JTextField textdate;
	private JTextField texttime;
	public LeavePanel(String id,String chtype) {
		JPanel jp = this;
		setOpaque(false);
//		setBackground(Color.white);
		setSize(500, 620);
		setBackground(new Color(0,0,0,0));
//		setOpaque(false);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0,120)));
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(41, 13, 407, 144);
		add(panel);
		panel.setLayout(null);
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0,120)));
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(41, 170, 407, 418);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel labeldate = new JLabel("日期：");
		labeldate.setFont(new Font("宋体", Font.PLAIN, 18));
		labeldate.setBounds(14, 22, 90, 18);
		panel_1.add(labeldate);
		
		textdate = new JTextField();
		textdate.setEditable(false);
		textdate.setFont(new Font("宋体", Font.BOLD, 15));
		textdate.setOpaque(false);
		textdate.setBounds(70, 20, 323, 27);
		panel_1.add(textdate);
		textdate.setColumns(10);
		
		JLabel labelreason = new JLabel("请假原因：");
		labelreason.setFont(new Font("宋体", Font.PLAIN, 18));
		labelreason.setBounds(14, 60, 90, 18);
		panel_1.add(labelreason);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 87, 379, 199);
		scrollPane.setOpaque(false);
		panel_1.add(scrollPane);
		
		JTextArea txtreason = new JTextArea();
		txtreason.setFont(new Font("宋体", Font.BOLD, 16));
		txtreason.setBackground(Color.WHITE);
		txtreason.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);	
		scrollPane.setViewportView(txtreason);	
		
		JButton button = new JButton("提交");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(texttime.getText().equals(""))
					JOptionPane.showMessageDialog(jp,"请假时间必须填写","警告",JOptionPane.WARNING_MESSAGE);//提示框;
				else {
					String ureason = txtreason.getText();
					String utime = texttime.getText();
					String utel = texttelephone.getText();
					String situation = "";

					String notes="记录插入成功！";
					sqls="insert into 请假情况 values('"+id+"','"+dayly+"','"+utime+"','"+ureason+"','否')";
					if(notes.equals(dbo.DBoperations(sqls, 2))) 
					{
						JOptionPane.showMessageDialog(jp,"提交成功","提示",JOptionPane.INFORMATION_MESSAGE);//提示框
					}
					else
						JOptionPane.showMessageDialog(jp,"您已请过假","错误",JOptionPane.WARNING_MESSAGE);//提示框
				}
			}
		});
		button.setBounds(134, 378, 113, 27);
		panel_1.add(button);
		
		JLabel labeltime = new JLabel("请假时间：");
		labeltime.setFont(new Font("宋体", Font.PLAIN, 18));
		labeltime.setBounds(14, 308, 90, 18);
		panel_1.add(labeltime);
		
		texttime = new JTextField();
		texttime.setFont(new Font("宋体", Font.BOLD, 15));
		texttime.setOpaque(false);
		texttime.setBounds(105, 307, 288, 24);
		panel_1.add(texttime);
		texttime.setColumns(10);
		
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
		textdate.setText(dayly);
	}
}
