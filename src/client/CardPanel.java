package client;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import dao.DBOperations;

import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class CardPanel extends JPanel{
	
	DBOperations dbo=new DBOperations();
	String sqls;
	String dayly;
	private JTextField textcollege;
	private JTextField textname;
	private JTextField textid;
	private JTextField texttelephone;
	private JTextField textdept;
	private JTextField textdate;
	private JTextField texttemperature;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	public CardPanel(String id,String chtype) {
		JPanel jp = this;
		setOpaque(false);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0,120)));
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(41, 170, 407, 418);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel labeldate = new JLabel("日期：");
		labeldate.setFont(new Font("宋体", Font.PLAIN, 18));
		labeldate.setBounds(14, 22, 72, 18);
		panel_1.add(labeldate);
		
		textdate = new JTextField();
		textdate.setEditable(false);
		textdate.setFont(new Font("宋体", Font.BOLD, 15));
		textdate.setOpaque(false);
		textdate.setBounds(70, 20, 323, 27);
		panel_1.add(textdate);
		textdate.setColumns(10);
		
		JLabel labeltemperature = new JLabel("今日体温：");
		labeltemperature.setFont(new Font("宋体", Font.PLAIN, 18));
		labeltemperature.setBounds(14, 56, 90, 18);
		panel_1.add(labeltemperature);
		
		texttemperature = new JTextField();
		texttemperature.setFont(new Font("宋体", Font.BOLD, 15));
		texttemperature.setOpaque(false);
		texttemperature.setBounds(111, 53, 282, 27);
		panel_1.add(texttemperature);
		texttemperature.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("是否隔离：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(14, 123, 90, 18);
		panel_1.add(lblNewLabel_1);
		
		JRadioButton gl1 = new JRadioButton("是");
		gl1.setEnabled(false);
		gl1.setOpaque(false);
		gl1.setBackground(Color.WHITE);
		buttonGroup.add(gl1);
		gl1.setBounds(134, 121, 59, 27);
		panel_1.add(gl1);
		
		JRadioButton gl0 = new JRadioButton("否");
		gl0.setOpaque(false);
		gl0.setBackground(Color.WHITE);
		gl0.setSelected(true);
		buttonGroup.add(gl0);
		gl0.setBounds(252, 121, 79, 27);
		panel_1.add(gl0);
		
		JLabel label_4 = new JLabel("近期（14天）是否去过湖北、武汉？");
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));
		label_4.setBounds(14, 153, 294, 18);
		panel_1.add(label_4);
		
		JRadioButton wc1 = new JRadioButton("是");
		wc1.setOpaque(false);
		wc1.setBackground(Color.WHITE);
		buttonGroup_1.add(wc1);
		wc1.setBounds(14, 180, 79, 27);
		panel_1.add(wc1);
		
		JRadioButton wc0 = new JRadioButton("否");
		wc0.setOpaque(false);
		wc0.setBackground(Color.WHITE);
		wc0.setSelected(true);
		buttonGroup_1.add(wc0);
		wc0.setBounds(168, 180, 79, 27);
		panel_1.add(wc0);
		
		JLabel lblNewLabel_2 = new JLabel("今日心里状况？");
		lblNewLabel_2.setBounds(14, 212, 105, 18);
		panel_1.add(lblNewLabel_2);
		
		JRadioButton xl1 = new JRadioButton("健康");
		xl1.setOpaque(false);
		xl1.setSelected(true);
		xl1.setBackground(Color.WHITE);
		buttonGroup_2.add(xl1);
		xl1.setBounds(14, 239, 79, 27);
		panel_1.add(xl1);
		
		JRadioButton xl2 = new JRadioButton("偶有情绪波动但能自我调节");
		xl2.setOpaque(false);
		xl2.setBackground(Color.WHITE);
		buttonGroup_2.add(xl2);
		xl2.setBounds(14, 271, 209, 27);
		panel_1.add(xl2);
		
		JRadioButton xl3 = new JRadioButton("轻度心理问题但能自助");
		xl3.setOpaque(false);
		xl3.setBackground(Color.WHITE);
		buttonGroup_2.add(xl3);
		xl3.setBounds(14, 303, 179, 27);
		panel_1.add(xl3);
		
		JRadioButton xl4 = new JRadioButton("严重心理问题需求助");
		xl4.setOpaque(false);
		xl4.setBackground(Color.WHITE);
		buttonGroup_2.add(xl4);
		xl4.setBounds(14, 335, 163, 27);
		panel_1.add(xl4);
		
		JLabel label_7 = new JLabel("是否确诊：");
		label_7.setFont(new Font("宋体", Font.PLAIN, 18));
		label_7.setBounds(14, 91, 90, 18);
		panel_1.add(label_7);
		
		JRadioButton qz1 = new JRadioButton("是");
		qz1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gl1.setEnabled(true);
			}
		});
		buttonGroup_3.add(qz1);
		qz1.setOpaque(false);
		qz1.setBackground(Color.WHITE);
		qz1.setBounds(134, 89, 59, 27);
		panel_1.add(qz1);
		
		JRadioButton qz0 = new JRadioButton("否");
		qz0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gl1.setEnabled(false);
			}
		});
		buttonGroup_3.add(qz0);
		qz0.setSelected(true);
		qz0.setOpaque(false);
		qz0.setBackground(Color.WHITE);
		qz0.setBounds(252, 89, 79, 27);
		panel_1.add(qz0);
		
		JButton signin = new JButton("打卡");
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(texttemperature.getText().equals(""))
					JOptionPane.showMessageDialog(jp,"温度必须填写","警告",JOptionPane.WARNING_MESSAGE);//提示框;
				else {
					String sign = "[123456789.]*";//正则表达式
					double utemp = 0;
//					检测输入的温度是否是浮点数
					if(!texttemperature.getText().matches(sign)) {
						JOptionPane.showMessageDialog(jp,"温度必须是浮点数","警告",JOptionPane.WARNING_MESSAGE);//提示框;
					}
					else {
						utemp = Double.parseDouble(texttemperature.getText());
						String utel = texttelephone.getText();
						String situation = "";
						int flag = 0;

						if( utemp > 37.5 || utemp < 36) {
							situation += "体温异常 ";
							flag = 1;
						}	
						if(wc1.isSelected()) {
							situation += "外出湖北 ";
						}
						if(qz1.isSelected()) {
							if(gl1.isSelected())
								situation += "已确诊，已隔离 ";
							else
								situation += "已确诊，未隔离 ";
							flag = 1;
						}
						if(!xl1.isSelected()) {
							if(xl2.isSelected())
								situation += xl2.getText();
							else if(xl3.isSelected())
								situation += xl3.getText();
							else
								situation += xl4.getText();
							flag = 1;
						}
						if(flag == 0)
							situation = "无";

						String notes="记录插入成功！";
						sqls="insert into 打卡情况 values('"+id+"','"+dayly+"',"+utemp+",'"+utel+"','"+situation+"')";
						if(notes.equals(dbo.DBoperations(sqls, 2))) 
						{
							JOptionPane.showMessageDialog(jp,"打卡成功","提示",JOptionPane.INFORMATION_MESSAGE);//提示框
						}
						else
							JOptionPane.showMessageDialog(jp,"您今天已打过卡","错误",JOptionPane.WARNING_MESSAGE);//提示框
					}
				}
			}
		});
		signin.setBounds(134, 378, 113, 27);
		panel_1.add(signin);
		
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
