package client;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import currency.HeadPicture;
import currency.PictureOpaque;
import currency.PictureZoom;
import dao.DBAccess;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Rectangle;
import javax.swing.border.MatteBorder;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Client_LandFrame extends JFrame {

	DBAccess db=new DBAccess();
	String sql;
	String hostIp = "127.0.0.1";//连接的服务器地址
	String hostPort = "8000";//端口号
	static Point origin = new Point();
	HeadPicture headpicture;
	private JPanel contentPane;
	private JTextField zhanghao;
	private JPasswordField password;
	private JButton land;

	/**
	 * Create the frame.
	 */
	public Client_LandFrame(Client client) {
		JFrame jf = this;
		setResizable(false);
		setBounds(100, 100, 550, 420);
		setTitle("疫情打卡/请假系统");
		setIconImage(Toolkit.getDefaultToolkit().getImage("lib\\图标2.png"));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		contentPane.setFocusable(true);//获得焦点
		setUndecorated(true);//无边框(删除标题栏)

		//圆形头像
		try {
			headpicture = new HeadPicture(new File("lib\\默认头像.jpg"), 225, 130, 100);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		contentPane.add(headpicture);
		
		JButton close = new JButton("");
		close.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		close.setBackground(Color.WHITE);
		close.setFocusable(false);
		close.setOpaque(false);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.exitClient();
				dispose();	
			}
		});
		close.setIcon(new ImageIcon("lib\\关闭白.png"));
		close.setBounds(520, 0, 30, 30);
		contentPane.add(close);
		
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
		minimize.setIcon(new ImageIcon("lib\\最小化白.png"));
		minimize.setBounds(490, 0, 30, 30);
		contentPane.add(minimize);
				
		//图片缩放
//		PictureZoom bcg = new PictureZoom("D:\\信息\\eclipse_workpace\\疫情打卡请假系统\\lib\\beijing1.jpg", 0, 0, 550, 190);
//		contentPane.add(bcg);
		
		zhanghao = new JTextField();
		zhanghao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String id = zhanghao.getText();
				db.dbconn();//连接数据库
				sql = "select * from 用户";
				db.dbSelect(sql);
				int flag=1;
//				根据账号显示头像
				try {
					while(db.rs.next())
					{
						if(id.equals(db.rs.getString("账号"))&&db.rs.getString("头像") != null) 
						{
							File file = new File(db.rs.getString("头像"));
//							如果该账号数据库中头像可读
							if(file.canRead()) {
//								关闭原来头像
								headpicture.setVisible(false);
								contentPane.remove(headpicture);
								headpicture = new HeadPicture(file, 225, 130, 100);
								contentPane.add(headpicture,0);							
//								repaint();
							}
						}
					}
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				db.dbclose();
			}
		});
		zhanghao.setOpaque(false);
		zhanghao.setFont(new Font("宋体", Font.BOLD, 24));
		zhanghao.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(169, 169, 169)));
		zhanghao.setBounds(165, 252, 259, 24);
		contentPane.add(zhanghao);
		zhanghao.setColumns(10);
		
		password = new JPasswordField();
		password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				land.doClick();
			}
		});
		password.setOpaque(false);
		password.setFont(new Font("宋体", Font.BOLD, 24));
		password.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(169, 169, 169)));
		password.setBounds(165, 304, 259, 24);
		contentPane.add(password);
		
		land = new JButton("登录");
		land.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = zhanghao.getText();
				String mima = String.valueOf(password.getPassword());
				String chtype; //存储用户身份的字符串
				db.dbconn();//连接数据库
				sql="select * from 用户";
				db.dbSelect(sql);
				int flag=1;
				try {
					while(db.rs.next())
					{
						//如果账号、密码相符
						if(id.equals(db.rs.getString("账号"))
								&&mima.equals(db.rs.getString("密码"))) 
						{
							chtype = db.rs.getString("身份");
							String login_mess = client.login(id, hostIp, hostPort);
							flag=0;
							if (login_mess.equals("true")) {
								setVisible(false);
								if(chtype.equals("学生"))
									client.showStuFrame(id);//显示学生界面
								else
									client.showTeaFrame(id);//显示教师界面
								break;
							}
							else
								JOptionPane.showMessageDialog(jf,login_mess,"错误",JOptionPane.WARNING_MESSAGE);//提示框
						}
					}
					if(flag==1)
						JOptionPane.showMessageDialog(jf,"账号或密码不正确！","错误",JOptionPane.WARNING_MESSAGE);//提示框
				}
				catch(SQLException ex) {
					System.err.print(ex.toString());
				}
				db.dbclose();
			}
		});
		land.setBackground(new Color(0, 191, 255));
		land.setForeground(new Color(255, 255, 255));
		land.setFont(new Font("宋体", Font.BOLD, 24));
		land.setBounds(131, 358, 293, 43);
		contentPane.add(land);
		
		JLabel tubiao = new JLabel("");
		tubiao.setIcon(new ImageIcon("lib\\图标2.png"));
		tubiao.setBounds(131, 246, 30, 30);
		contentPane.add(tubiao);
		
		JLabel mima = new JLabel("");
		mima.setIcon(new ImageIcon("lib\\mima.png"));
		mima.setBounds(131, 298, 30, 30);
		contentPane.add(mima);
		
		JLabel Iconimage = new JLabel("");
		Iconimage.setIcon(new ImageIcon("lib\\图标2.png"));
		Iconimage.setBounds(0, 0, 30, 30);
		contentPane.add(Iconimage);
		
		JButton register = new JButton("注册账号");
		register.setForeground(Color.GRAY);
		register.setFont(new Font("宋体", Font.PLAIN, 14));
		register.setBackground(Color.WHITE);
		register.setOpaque(false);
		register.setFocusable(false);
		register.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		register.setBounds(0, 387, 85, 27);
		contentPane.add(register);
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));//将鼠标变为手型
			}
			@Override
			public void mouseExited(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//将鼠标变为指针
			}
		});
		
		JButton code = new JButton("");
		code.setOpaque(false);
		code.setIcon(new ImageIcon("lib\\code1.png"));
		code.setForeground(Color.GRAY);
		code.setFont(new Font("宋体", Font.PLAIN, 14));
		code.setFocusable(false);
		code.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		code.setBackground(Color.WHITE);
		code.setBounds(496, 374, 40, 40);
		contentPane.add(code);
		
		JLabel bcg = new JLabel("");
		bcg.setIcon(new ImageIcon("lib\\登录背景.gif"));
		bcg.setBounds(0, 0, 550, 190);
		contentPane.add(bcg);
		
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
	}

}
