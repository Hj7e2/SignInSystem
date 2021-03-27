package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import currency.HeadPicture;
import currency.PictureOpaque;
import currency.PictureZoom;
import dao.DBOperations;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Client_PerSetting extends JFrame {

	DBOperations dbo=new DBOperations();
	String sqls;
	// 全局的位置变量，用于表示鼠标在窗口上的位置
	static Point origin = new Point();
	private JPanel contentPane;
	int flag_picture = 0;
	HeadPicture headpicture;
	PictureOpaque bcg;
	File selectedFile;
	File txfile = null;
	File fmfile = null;
	private JButton close;
	private JTextField textid;
	private JTextField textcollege;
	private JTextField textmajor;
	private JTextField textgrade;
	private JTextField textclasses;
	private JTextField texttelephone;
	private JTextField textdate;
	private JTextField textsignature;
	private JTextField textname;
	private JTextArea txtrFbch;
	
	public JButton getClose() {
		return close;
	}
	
	public File getFmfile() {
		return fmfile;
	}

	public File getTxfile() {
		return txfile;
	}

	/**
	 * Create the frame.
	 */
	public Client_PerSetting(String id,String chtype) {
		JFrame jf = this;
		setResizable(false);	
		setBounds(100, 100, 715, 505);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("个人资料设置");
		setIconImage(Toolkit.getDefaultToolkit().getImage("lib\\图标2.png"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);//无边框(删除标题栏)
		setLocationRelativeTo(null);
		
		try {
			headpicture = new HeadPicture(new File("lib\\默认头像.jpg"), 14, 392, 100);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		contentPane.add(headpicture);
		
		textsignature = new JTextField("个性签名");
		textsignature.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		textsignature.setOpaque(false);
		textsignature.setBackground(Color.WHITE);
		textsignature.setForeground(Color.WHITE);
//		textsignature.setCaretPosition(0);
		textsignature.setCaretColor(Color.white);
		textsignature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtrFbch.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtrFbch.setVisible(false);
			}
		});
		textsignature.setFont(new Font("宋体", Font.PLAIN, 16));
		textsignature.setBounds(128, 447, 172, 45);
		contentPane.add(textsignature);
		
		txtrFbch = new JTextArea();
		txtrFbch.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtrFbch.setBackground(new Color(255, 250, 205));
		txtrFbch.setEditable(false);
		txtrFbch.setLineWrap(true);
		txtrFbch.setFont(new Font("宋体", Font.PLAIN, 20));
		txtrFbch.setText("个性签名补充框");
		txtrFbch.setBounds(128, 400, 329, 53);
		txtrFbch.setVisible(false);
		contentPane.add(txtrFbch);
		
		JButton closejf = new JButton("");
		closejf.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		closejf.setBackground(Color.WHITE);
		closejf.setFocusable(false);
		closejf.setOpaque(false);
		closejf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closejf.setIcon(new ImageIcon("lib\\关闭黑.png"));
		closejf.setBounds(685, 0, 30, 30);
		contentPane.add(closejf);
		
		JButton minimize = new JButton("");
		minimize.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		minimize.setBackground(Color.WHITE);
		minimize.setFocusable(false);
		minimize.setOpaque(false);
		minimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setState(JFrame.ICONIFIED);
			}
		});
		minimize.setIcon(new ImageIcon("lib\\最小化黑.png"));
		minimize.setBounds(655, 0, 30, 30);
		contentPane.add(minimize);
		
		JLabel labelid = new JLabel("学号/工号：");
		labelid.setFont(new Font("宋体", Font.PLAIN, 24));
		labelid.setBounds(314, 32, 142, 32);
		contentPane.add(labelid);
		
		textname = new JTextField("姓名");
		textname.setOpaque(false);
		textname.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		textname.setForeground(Color.WHITE);
		textname.setFont(new Font("宋体", Font.PLAIN, 36));
		textname.setBounds(128, 400, 172, 55);
		textname.setCaretColor(new Color(255,255,255));
		contentPane.add(textname);
		
		JLabel labelsex = new JLabel("性别：");
		labelsex.setFont(new Font("宋体", Font.PLAIN, 24));
		labelsex.setBounds(314, 77, 121, 32);
		contentPane.add(labelsex);
		
		JLabel labelcollege = new JLabel("学院：");
		labelcollege.setFont(new Font("宋体", Font.PLAIN, 24));
		labelcollege.setBounds(314, 122, 72, 32);
		contentPane.add(labelcollege);
		
		JLabel labelmajor = new JLabel("专业：");
		labelmajor.setFont(new Font("宋体", Font.PLAIN, 24));
		labelmajor.setBounds(314, 208, 72, 32);
		contentPane.add(labelmajor);
		
		JLabel labelgrade = new JLabel("年级：");
		labelgrade.setFont(new Font("宋体", Font.PLAIN, 24));
		labelgrade.setBounds(314, 167, 72, 32);
		contentPane.add(labelgrade);
		
		JLabel labelclasses = new JLabel("班级：");
		labelclasses.setFont(new Font("宋体", Font.PLAIN, 24));
		labelclasses.setBounds(314, 253, 72, 32);
		contentPane.add(labelclasses);
		
		JLabel labeltelephone = new JLabel("联系方式：");
		labeltelephone.setFont(new Font("宋体", Font.PLAIN, 24));
		labeltelephone.setBounds(314, 343, 121, 32);
		contentPane.add(labeltelephone);
		
		JLabel labelbirthday = new JLabel("出生日期：");
		labelbirthday.setFont(new Font("宋体", Font.PLAIN, 24));
		labelbirthday.setBounds(314, 298, 142, 32);
		contentPane.add(labelbirthday);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("lib\\装饰 (1).png"));
		label.setBounds(314, 397, 50, 50);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("lib\\装饰 (2).png"));
		label_1.setBounds(395, 397, 50, 50);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("lib\\装饰 (3).png"));
		label_2.setBounds(485, 397, 50, 50);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("lib\\装饰 (5).png"));
		label_3.setBounds(645, 397, 50, 50);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("lib\\装饰 (4).png"));
		label_4.setBounds(566, 397, 50, 50);
		contentPane.add(label_4);
		
		textid = new JTextField();
		textid.setFont(new Font("宋体", Font.BOLD, 16));
		textid.setBounds(437, 32, 258, 31);
		contentPane.add(textid);
		textid.setColumns(10);
		
		textcollege = new JTextField();
		textcollege.setFont(new Font("宋体", Font.BOLD, 16));
		textcollege.setBounds(384, 126, 311, 31);
		contentPane.add(textcollege);
		textcollege.setColumns(10);
		
		textmajor = new JTextField();
		textmajor.setFont(new Font("宋体", Font.BOLD, 16));
		textmajor.setBounds(384, 208, 311, 31);
		contentPane.add(textmajor);
		textmajor.setColumns(10);
		
		JComboBox boxsex = new JComboBox();
		boxsex.setFont(new Font("宋体", Font.BOLD, 16));
		boxsex.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		boxsex.setBounds(384, 77, 90, 31);
		contentPane.add(boxsex);
		
		textgrade = new JTextField();
		textgrade.setFont(new Font("宋体", Font.BOLD, 16));
		textgrade.setBounds(384, 167, 311, 32);
		contentPane.add(textgrade);
		textgrade.setColumns(10);
		
		textclasses = new JTextField();
		textclasses.setFont(new Font("宋体", Font.BOLD, 16));
		textclasses.setBounds(384, 253, 311, 31);
		contentPane.add(textclasses);
		textclasses.setColumns(10);
		
		texttelephone = new JTextField();
		texttelephone.setFont(new Font("宋体", Font.BOLD, 16));
		texttelephone.setBounds(437, 347, 258, 31);
		contentPane.add(texttelephone);
		texttelephone.setColumns(10);
		
		textdate = new JTextField();
		textdate.setFont(new Font("宋体", Font.BOLD, 16));
		textdate.setBounds(437, 298, 258, 31);
		contentPane.add(textdate);
		textdate.setColumns(10);
		
		close = new JButton("关闭");
		close.setFont(new Font("宋体", Font.PLAIN, 24));
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setBounds(574, 460, 121, 32);
		contentPane.add(close);
		
		JButton save = new JButton("保存");
		save.setFont(new Font("宋体", Font.PLAIN, 24));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uget[] = {textid.getText(),textname.getText(),boxsex.getSelectedItem().toString(),textclasses.getText(),
						textcollege.getText(),textmajor.getText(),textgrade.getText(),textdate.getText(),texttelephone.getText(),
						textsignature.getText()};
				String uset[] = {"学号","姓名","性别","班级","学院","专业","年级","出生日期","联系方式","个性签名","头像","封面"};
				
				if(chtype == "学生")
					for(int i = 0; i < 10;i++) {
						sqls = "update 学生 set "+ uset[i] +" = '"+ uget[i] +"' where 账号='"+ id +"'";
						dbo.DBoperations(sqls, 1);
					}
				else {
					for(int i = 0; i < 10;i++) {
						if(i != 0 && i != 3) {
							sqls = "update 教师 set "+ uset[i] +" = '"+ uget[i] +"' where 账号='"+ id +"'";
							dbo.DBoperations(sqls, 1);
						}
					}
					sqls = "update 教师 set 工号 = '"+ uget[0] +"' where 账号='"+ id +"'";
					dbo.DBoperations(sqls, 1);
				}
				
				if(txfile != null) {
					sqls = "update 用户 set "+ uset[10] +" = '"+ txfile.toString().replaceAll("\\\\", "\\\\\\\\") +"' where 账号='"+ id +"'";
					dbo.DBoperations(sqls, 1);
				}
				if(fmfile != null) {
					sqls = "update 用户 set "+ uset[11] +" = '"+ fmfile.toString().replaceAll("\\\\", "\\\\\\\\") +"' where 账号='"+ id +"'";
					dbo.DBoperations(sqls, 1);
				}
				
				txtrFbch.setText(textsignature.getText());
				
				JOptionPane.showMessageDialog(null, "保存成功","提示",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		save.setBounds(384, 460, 121, 32);
		contentPane.add(save);

		JButton touxiang = new JButton("");
		touxiang.setBackground(Color.WHITE);
		touxiang.setOpaque(false);
		touxiang.setBorderPainted(false);
		touxiang.setBounds(14, 392, 100, 100);
//		touxiang.setFocusPainted(false);
		contentPane.add(touxiang);
		
		JLabel changefm = new JLabel("");
		changefm.setIcon(new ImageIcon("lib\\更换封面.png"));
		changefm.setBounds(14, 14, 15, 15);
		contentPane.add(changefm);
		
		JLabel changebcg = new JLabel("更改封面");
		changebcg.setFont(new Font("宋体", Font.PLAIN, 14));
		changebcg.setForeground(Color.LIGHT_GRAY);
		changebcg.setBounds(35, 12, 72, 18);
		contentPane.add(changebcg);
		
		JLabel bcg2 = new JLabel("");
		bcg2.setBackground(new Color(102, 51, 51));
		bcg2.setOpaque(true);
		bcg2.setBounds(0, 379, 300, 126);
		contentPane.add(bcg2);
		
		bcg = new PictureOpaque("lib\\默认封面.png", 0, 0, 300, 380,0.9f);
		contentPane.add(bcg);
		
		//设置拖动
		jf.addMouseListener(new MouseAdapter() {
			// 按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		
		jf.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = jf.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				jf.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		});
		
//		更改封面
		changebcg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser=new JFileChooser();//创建文件选择器对象
				//设置文件过滤
				fileChooser.setFileFilter(new FileNameExtensionFilter("支持的图片格式(*.jpg、*.png)","jpg","png"));
				int flag = fileChooser.showOpenDialog(null);//显示文件选择对话框
				if(flag == JFileChooser.APPROVE_OPTION) {
					selectedFile=fileChooser.getSelectedFile();//获取选择的对象文件
				}
				
				if(selectedFile != null) {
//					封面置为选择的文件
					fmfile = selectedFile;
					bcg.setVisible(false);
					contentPane.remove(bcg);
					bcg = new PictureOpaque(selectedFile.toString(), 0, 0, 300, 380,0.9f);
					contentPane.add(bcg);
//					repaint();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));//将鼠标变为手型
			}
			@Override
			public void mouseExited(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//将鼠标变为指针
			}
		});
		
		touxiang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser=new JFileChooser();//创建文件选择器对象
				//设置文件过滤
				fileChooser.setFileFilter(new FileNameExtensionFilter("支持的图片格式(*.jpg、*.png)","jpg","png"));
				int flag = fileChooser.showOpenDialog(null);//显示文件选择对话框
				if(flag == JFileChooser.APPROVE_OPTION) {
					selectedFile=fileChooser.getSelectedFile();//获取选择的对象文件
				}
				/*
				 * System.out.println(selectedFile.toURI()); try {
				 * System.out.println(selectedFile.toURL());
				 * System.out.println(selectedFile.toURI().toURL()); } catch
				 * (MalformedURLException e2) { // TODO 自动生成的 catch 块 e2.printStackTrace(); }
				 * System.out.println(selectedFile);
				 */

				if(selectedFile != null) {
					try {
						txfile = selectedFile;
						headpicture.setVisible(false);
						contentPane.remove(headpicture);
						headpicture = new HeadPicture(selectedFile, 14, 392, 100);
						contentPane.add(headpicture,0);							
//						repaint();
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));//将鼠标变为手型
			}
			@Override
			public void mouseExited(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//将鼠标变为指针
			}
		});

//		进入界面时 填写相应资料
		try {
			switch(chtype) {
			case "学生":
				sqls="select * from 学生";
				break;
			case "教师":
				sqls="select * from 教师";
				textclasses.setEditable(false);
				break;
			case "院长":
				textclasses.setEditable(false);
				textmajor.setEditable(false);
				textgrade.setEditable(false);
				sqls="select * from 教师";
				break;
			case "校长":
				textclasses.setEditable(false);
				textcollege.setEditable(false);
				textmajor.setEditable(false);
				textgrade.setEditable(false);
				sqls="select * from 教师";
				break;
			}

			dbo.DBoperations(sqls, 0);

			while(dbo.db.rs.next()) {
				if(id.equals(dbo.db.rs.getString("账号")))
				{
					if(chtype == "学生") {
						textid.setText(dbo.db.rs.getString("学号"));
						textclasses.setText(dbo.db.rs.getString("班级"));
					}
					else
						textid.setText(dbo.db.rs.getString("工号"));
					textname.setText(dbo.db.rs.getString("姓名"));
					textcollege.setText(dbo.db.rs.getString("学院"));
					textmajor.setText(dbo.db.rs.getString("专业"));
					textgrade.setText(dbo.db.rs.getString("年级"));
					textdate.setText(dbo.db.rs.getString("出生日期"));
					texttelephone.setText(dbo.db.rs.getString("联系方式"));
					txtrFbch.setText(dbo.db.rs.getString("个性签名"));
					textsignature.setText(dbo.db.rs.getString("个性签名"));
					textsignature.setCaretPosition(0);//是光标移动到最左端（从左边开始显示）
					boxsex.setSelectedItem(dbo.db.rs.getString("性别"));

					break;
				}
			}
			dbo.db.dbclose();
			
//			从用户表拿到头像和封面
			sqls="select * from 用户";
			dbo.DBoperations(sqls, 0);
			while(dbo.db.rs.next()) {
				if(id.equals(dbo.db.rs.getString("账号"))){
					if(dbo.db.rs.getString("头像") != null) {
						txfile = new File(dbo.db.rs.getString("头像"));
						if(txfile.canRead()) {
							
							headpicture.setVisible(false);
							contentPane.remove(headpicture);
							headpicture = new HeadPicture(txfile, 14, 392, 100);
							contentPane.add(headpicture,0);							
//							repaint();
						}
					}
					
					if(dbo.db.rs.getString("封面") != null) {
						fmfile = new File(dbo.db.rs.getString("封面"));
						if(fmfile.canRead()) {
							bcg.setVisible(false);
							contentPane.remove(bcg);
							bcg = new PictureOpaque(fmfile.toString(), 0, 0, 300, 380,0.9f);
							contentPane.add(bcg);							
//							repaint();
						}
					}
				}
			}
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		dbo.db.dbclose();
	}
}
