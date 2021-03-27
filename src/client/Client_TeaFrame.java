package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import currency.HeadPicture;
import currency.PictureOpaque;
import currency.PictureZoom;
import dao.DBOperations;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

public class Client_TeaFrame extends JFrame implements ActionListener,MouseListener {

	DBOperations dbo=new DBOperations();
	String sqls;
	// 鼠标在窗口中的位置
	static Point origin = new Point();
    int left;// 窗体离屏幕左边的距离
    int top;// 窗体离屏幕顶部的距离
    int width; // 窗体的宽
    int height;// 窗体的高
    int state = 3;//窗体状态
    int opaque;
    File fmfile;
    File file1;
    File txfile;
    File file2;
    Timer timer = new Timer(10, this);//定时器 每隔10ms触发一次
    String chtype;
    IndexPanel teaindex;
    private Client client;
	private JPanel contentPane;
	private HeadPicture headpicture;
	private PictureOpaque bcg2;
	private Client_PerSetting perset;
	private JLabel labelname;
	private JLabel labelid;
	private JLabel labelsignature;
	private JButton index;
	private JButton card;
	private JButton leave;
	private JButton history;
	private JButton butteacard;
	private JButton buttealeave;
	private JButton butmanagercard;
	private JButton butmanagerleave;
	private JButton touxiang;
	private JTextArea txtrFbch;
	private ImageIcon indexpic1 = new ImageIcon("lib\\主页.png");
	private ImageIcon indexpic2 = new ImageIcon("lib\\主页2.png");
	private ImageIcon cardpic1 = new ImageIcon("lib\\打卡.png");
	private ImageIcon cardpic2 = new ImageIcon("lib\\打卡2.png");
	private ImageIcon leavepic1 = new ImageIcon("lib\\请假.png");
	private ImageIcon leavepic2 = new ImageIcon("lib\\请假2.png");
	private ImageIcon historypic1 = new ImageIcon("lib\\历史记录.png");
	private ImageIcon historypic2 = new ImageIcon("lib\\历史记录2.png");

	public PictureOpaque getBcg2() {
		return bcg2;
	}

	/**
	 * Create the frame.
	 */
	public Client_TeaFrame(Client client,String id) {
		JFrame jf = this;
		this.client = client;
//		从用户表拿到身份
		sqls="select * from 用户";
		dbo.DBoperations(sqls, 0);
		try {
			while(dbo.db.rs.next()) {
				if(id.equals(dbo.db.rs.getString("账号"))){
					chtype = dbo.db.rs.getString("身份");
				}
			}
		} catch (SQLException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}

		
		timer.start();
        this.addMouseListener(this);
		setTitle("疫情打卡/请假系统");
		setIconImage(Toolkit.getDefaultToolkit().getImage("lib\\图标2.png"));
		setResizable(false);
		setDefaultCloseOperation(NORMAL);
		setBounds(100, 100, 500, 880);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(jf,"是否确定要退出？", "提示", JOptionPane.OK_CANCEL_OPTION)) 
                {
                    client.exitLogin();
                    System.exit(0);
                }
            }
        });
		
		contentPane = new JPanel();
//		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setFocusable(true);//获得焦点
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		txfile = new File("lib\\默认头像.jpg");
		try {
			headpicture = new HeadPicture(new File("lib\\默认头像.jpg"), 14, 13, 85);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		contentPane.add(headpicture);
		
		labelname = new JLabel("姓名");
		labelname.setFont(new Font("宋体", Font.BOLD, 24));
		labelname.setBounds(124, 23, 121, 31);
		contentPane.add(labelname);
		
		labelid = new JLabel("学号");
		labelid.setFont(new Font("宋体", Font.BOLD, 24));
		labelid.setBounds(259, 23, 194, 31);
		contentPane.add(labelid);
		
		labelsignature = new JLabel("个性签名");
		labelsignature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtrFbch.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtrFbch.setVisible(false);
			}
		});
		labelsignature.setFont(new Font("宋体", Font.PLAIN, 24));
		labelsignature.setBounds(124, 60, 329, 31);
		contentPane.add(labelsignature);
		
		touxiang = new JButton("");
		touxiang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				new Client_PerSetting(id, "学生").setVisible(true);
				perset.setVisible(true);
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
		touxiang.setOpaque(false);
		touxiang.setFocusable(false);
		touxiang.setBorderPainted(false);
		touxiang.setBackground(Color.WHITE);
		touxiang.setBounds(14, 13, 85, 85);
		contentPane.add(touxiang);
		
		JLabel label_3 = new JLabel("首页");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(14, 810, 70, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("打卡");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(132, 810, 70, 18);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("请假");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(259, 810, 70, 18);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("历史记录");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(383, 810, 70, 18);
		contentPane.add(label_6);
		
		txtrFbch = new JTextArea();
		txtrFbch.setBounds(151, 96, 329, 53);
		contentPane.add(txtrFbch);
		txtrFbch.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtrFbch.setBackground(new Color(255, 250, 205));
		txtrFbch.setEditable(false);
		txtrFbch.setLineWrap(true);
		txtrFbch.setFont(new Font("宋体", Font.PLAIN, 20));
		txtrFbch.setText("个性签名补充框");
		txtrFbch.setVisible(false);
		
		perset = new Client_PerSetting(id,chtype);
		
		CardPanel teacard = new CardPanel(id,"教师");
		teacard.setBounds(0, 125, 500, 595);
		contentPane.add(teacard);
		teacard.setVisible(false);
		
		LeavePanel tealeave = new LeavePanel(id,"教师");
		tealeave.setBounds(0, 125, 500, 595);
		contentPane.add(tealeave);
		tealeave.setVisible(false);
		
		teaindex = new IndexPanel();
		teaindex.setBounds(0, 125, 500, 595);
		contentPane.add(teaindex);
		
		HistoryPanel teahistory = new HistoryPanel(id,"教师");
		teahistory.setBounds(0, 125, 500, 595);
		contentPane.add(teahistory);
		teahistory.setVisible(false);
		
		JSlider slider = teaindex.getSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				opaque = slider.getValue();
				bcg2.setVisible(false);
				contentPane.remove(bcg2);
				if(fmfile.canRead())
					bcg2 = new PictureOpaque(fmfile.toString(), 0, 125, 500, 595,(float)opaque/100);
				else
					bcg2 = new PictureOpaque("lib\\默认封面.png", 0, 125, 500, 595,(float)opaque/100);
				contentPane.add(bcg2);							
//				repaint();
			}
		});
		
		JButton clo = perset.getClose();
		clo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check(id);
				file1 = perset.getFmfile();
				if(file1 != fmfile && file1.canRead() && file1 != null) {
					fmfile = file1;
					bcg2.setVisible(false);
					contentPane.remove(bcg2);
					bcg2 = new PictureOpaque(fmfile.toString(), 0, 125, 500, 595,0.5f);
					contentPane.add(bcg2);		
//					repaint();
				}
				
				file2 = perset.getTxfile();
				if(file2 != txfile && file2.canRead() && file2 != null) {
					txfile = file2;
					headpicture.setVisible(false);
					contentPane.remove(headpicture);
					try {
						headpicture = new HeadPicture(txfile, 14, 13, 85);
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					contentPane.add(headpicture,0);							
//					repaint();	
				}
			}
		});
		
		CardManagerPanel cardmanager = new CardManagerPanel(client,id,chtype);
		cardmanager.setBounds(0, 125, 500, 595);
		contentPane.add(cardmanager);
		cardmanager.setVisible(false);
		
		LeaveManagerPanel leavemanager = new LeaveManagerPanel(client,id,chtype);
		leavemanager.setBounds(0, 125, 500, 595);
		contentPane.add(leavemanager);
		leavemanager.setVisible(false);
		
		index = new JButton("");
		index.setBorderPainted(false);
		index.setIcon(indexpic1);
		index.setFocusPainted(false);//取消焦点
		index.setOpaque(false);
		index.setBackground(new Color(255, 255, 255));
		index.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index.setIcon(indexpic1);
				card.setIcon(cardpic2);
				leave.setIcon(leavepic2);
				history.setIcon(historypic2);
				teaindex.setVisible(true);
				tealeave.setVisible(false);
				teacard.setVisible(false);
				teahistory.setVisible(false);
				cardmanager.setVisible(false);
				leavemanager.setVisible(false);
				
				butteacard.setVisible(false);
				butmanagercard.setVisible(false);
				buttealeave.setVisible(false);
				butmanagerleave.setVisible(false);
			}
		});
		
		butteacard = new JButton("教师打卡");
		butteacard.setForeground(Color.GRAY);
		butteacard.setFont(new Font("宋体", Font.BOLD, 16));
		butteacard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				butteacard.setBorder(new MatteBorder(1, 0, 4, 1, (Color) new Color(0, 0, 0,50)));
				butmanagercard.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0,50)));
				teacard.setVisible(true);
				cardmanager.setVisible(false);
			}
		});
		butteacard.setBackground(Color.WHITE);
		butteacard.setOpaque(false);
		butteacard.setFocusable(false);
		butteacard.setBorder(new MatteBorder(1, 0, 4 ,1, (Color) new Color(0, 0, 0,50)));
		butteacard.setBounds(0, 103, 250, 27);
		butteacard.setVisible(false);
		contentPane.add(butteacard);
		
		butmanagercard = new JButton("打卡管理");
		butmanagercard.setForeground(Color.GRAY);
		butmanagercard.setFont(new Font("宋体", Font.BOLD, 16));
		butmanagercard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				butteacard.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0,50)));
				butmanagercard.setBorder(new MatteBorder(1, 1, 4, 0, (Color) new Color(0, 0, 0,50)));
				cardmanager.setVisible(true);
				teacard.setVisible(false);
			}
		});
		butmanagercard.setOpaque(false);
		butmanagercard.setFocusable(false);
		butmanagercard.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0,50)));
		butmanagercard.setBackground(Color.WHITE);
		butmanagercard.setBounds(250, 103, 250, 27);
		butmanagercard.setVisible(false);
		contentPane.add(butmanagercard);
		
		buttealeave = new JButton("教师请假");
		buttealeave.setBounds(0, 103, 250, 27);
		buttealeave.setForeground(Color.GRAY);
		buttealeave.setFont(new Font("宋体", Font.BOLD, 16));
		buttealeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttealeave.setBorder(new MatteBorder(1, 0, 4, 1, (Color) new Color(0, 0, 0,50)));
				butmanagerleave.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0,50)));
				tealeave.setVisible(true);
				leavemanager.setVisible(false);
			}
		});
		buttealeave.setBackground(Color.WHITE);
		buttealeave.setOpaque(false);
		buttealeave.setFocusable(false);
		buttealeave.setBorder(new MatteBorder(1, 0, 4 ,1, (Color) new Color(0, 0, 0,50)));
		buttealeave.setVisible(false);
		contentPane.add(buttealeave);
		
		butmanagerleave = new JButton("请假管理");
		butmanagerleave.setForeground(Color.GRAY);
		butmanagerleave.setFont(new Font("宋体", Font.BOLD, 16));
		butmanagerleave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttealeave.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0,50)));
				butmanagerleave.setBorder(new MatteBorder(1, 0, 4, 1, (Color) new Color(0, 0, 0,50)));
				leavemanager.setVisible(true);
				tealeave.setVisible(false);
			}
		});
		butmanagerleave.setOpaque(false);
		butmanagerleave.setFocusable(false);
		butmanagerleave.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0,50)));
		butmanagerleave.setBackground(Color.WHITE);
		butmanagerleave.setBounds(250, 103, 250, 27);
		butmanagerleave.setVisible(false);
		contentPane.add(butmanagerleave);
		index.setBounds(14, 735, 70, 70);
		contentPane.add(index);
		
		card = new JButton("");
		card.setBackground(Color.WHITE);
		card.setOpaque(false);
		card.setBorderPainted(false);
		card.setFocusPainted(false);
		card.setIcon(cardpic2);
		card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index.setIcon(indexpic2);
				card.setIcon(cardpic1);
				leave.setIcon(leavepic2);
				history.setIcon(historypic2);
				teaindex.setVisible(false);
				tealeave.setVisible(false);
				teahistory.setVisible(false);
				cardmanager.setVisible(false);
				leavemanager.setVisible(false);
				teacard.setVisible(true);
				
				butteacard.setVisible(true);
				butmanagercard.setVisible(true);
				butteacard.setBorder(new MatteBorder(1, 0, 4, 1, (Color) new Color(0, 0, 0,50)));
				butmanagercard.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0,50)));
				buttealeave.setVisible(false);
				butmanagerleave.setVisible(false);
			}
		});
		card.setBounds(132, 735, 70, 70);
		contentPane.add(card);
		
		leave = new JButton("");
		leave.setBackground(Color.WHITE);
		leave.setBorderPainted(false);
		leave.setOpaque(false);
		leave.setFocusPainted(false);
		leave.setIcon(leavepic2);
		leave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index.setIcon(indexpic2);
				card.setIcon(cardpic2);
				leave.setIcon(leavepic1);
				history.setIcon(historypic2);
				teaindex.setVisible(false);
				teacard.setVisible(false);
				tealeave.setVisible(true);
				teahistory.setVisible(false);
				cardmanager.setVisible(false);
				leavemanager.setVisible(false);
				
				butteacard.setVisible(false);
				butmanagercard.setVisible(false);
				buttealeave.setVisible(true);
				butmanagerleave.setVisible(true);
				buttealeave.setBorder(new MatteBorder(1, 0, 4, 1, (Color) new Color(0, 0, 0,50)));
				butmanagerleave.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0,50)));
			}
		});
		leave.setBounds(259, 735, 70, 70);
		contentPane.add(leave);
		
		history = new JButton("");
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index.setIcon(indexpic2);
				card.setIcon(cardpic2);
				leave.setIcon(leavepic2);
				history.setIcon(historypic1);
				teaindex.setVisible(false);
				teacard.setVisible(false);
				tealeave.setVisible(false);
				teahistory.setVisible(true);
				cardmanager.setVisible(false);
				leavemanager.setVisible(false);
				
				butteacard.setVisible(false);
				butmanagercard.setVisible(false);
				buttealeave.setVisible(false);
				butmanagerleave.setVisible(false);
			}
		});
		history.setBackground(Color.WHITE);
		history.setOpaque(false);
		history.setBorderPainted(false);
		history.setFocusPainted(false);
		history.setIcon(historypic2);
		history.setBounds(383, 735, 70, 70);
		contentPane.add(history);
		
		PictureZoom bcg = new PictureZoom("lib\\beijing1.jpg", 0, 0, 550, 130);
		contentPane.add(bcg);
		
		PictureZoom bcg1 = new PictureZoom("lib\\beijing1.jpg", 0, 720, 550, 130);
		contentPane.add(bcg1);
		
		fmfile = new File("lib\\默认封面.png");
		bcg2 = new PictureOpaque("lib\\默认封面.png", 0, 125, 500, 595,0.5f);
		contentPane.add(bcg2);
		
		check(id);
//		从用户表拿到头像和封面
		sqls="select * from 用户";
		dbo.DBoperations(sqls, 0);
		try {
			while(dbo.db.rs.next()) {
				if(id.equals(dbo.db.rs.getString("账号"))){
					if(dbo.db.rs.getString("头像") != null) {
						txfile = new File(dbo.db.rs.getString("头像"));
						if(txfile.canRead()) {
							headpicture.setVisible(false);
							contentPane.remove(headpicture);
							headpicture = new HeadPicture(txfile, 14, 13, 85);
							contentPane.add(headpicture,0);							
//							repaint();
						}
					}
				
					if(dbo.db.rs.getString("封面") != null) {
						fmfile = new File(dbo.db.rs.getString("封面"));
						if(fmfile.canRead()) {
							bcg2.setVisible(false);
							contentPane.remove(bcg2);
							bcg2 = new PictureOpaque(fmfile.toString(), 0, 125, 500, 595,0.5f);
							contentPane.add(bcg2);							
//							repaint();
						}
					}
				}
			}
		} catch (SQLException | IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}
	
	public void check(String id) {
		sqls="select * from 教师";
		dbo.DBoperations(sqls, 0);
		try {
			while(dbo.db.rs.next()) {
				if(id.equals(dbo.db.rs.getString("账号")))
				{
					labelname.setText(dbo.db.rs.getString("姓名"));
					labelid.setText(dbo.db.rs.getString("工号"));
					String sign = dbo.db.rs.getString("个性签名");
					labelsignature.setText(sign);
					if(sign.equals(""))
						txtrFbch.setText("个性签名补充框");
					else
						txtrFbch.setText(dbo.db.rs.getString("个性签名"));
					break;
				}
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		left = getLocationOnScreen().x;
		top = getLocationOnScreen().y;
//		System.out.println(top);
//		System.out.println(state);
		width = getWidth();
		height = getHeight();
		if ((top < 0)) {
			this.state = 1;//被隐藏状态
		} 
		
		if(this.state == 2){
			setLocation(left, 5 - height);//隐藏
		}
		
		teaindex.setTime();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		if(this.state == 1){
            setLocation(left, 0);
            this.state = 3;
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
//		鼠标在窗口中的位置
		origin.x = e.getX();
		origin.y = e.getY();

		if(top > 5)
			this.state = 3;

		if(origin.x >  width-10 || origin.x < 10 || origin.y > height-10) {
			if(top > -5 && top < 5)
				this.state = 2;
		}
	}
	
    public void closeClient() {
        JOptionPane.showMessageDialog(this, "服务器已关闭", "提示",JOptionPane.OK_OPTION);
        client.exitClient();
        setVisible(false);
    }

	public void getremind() {
		JOptionPane.showMessageDialog(this,"请及时打卡","提示",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void getrelogin() {
		JOptionPane.showMessageDialog(this,"不能重复登录","警告",JOptionPane.ERROR_MESSAGE);	
	}
	
	public void getreply(String mess) {
		JOptionPane.showMessageDialog(this,mess,"回复",JOptionPane.INFORMATION_MESSAGE);	
	}
}
