package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ServerFrame extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ServerFrame(Server server) {
		setTitle("疫情打卡/请假系统服务器");
		setIconImage(Toolkit.getDefaultToolkit().getImage("lib\\图标2.png"));
		setResizable(false);
		setDefaultCloseOperation(NORMAL);
		setBounds(100, 100, 536, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton jbt_start = new JButton("启动服务器");
		jbt_start.setBounds(14, 32, 113, 27);
		contentPane.add(jbt_start);
		
		JButton jbt_stop = new JButton("停止服务器");
		jbt_stop.setEnabled(false);
		jbt_stop.setBounds(197, 32, 113, 27);
		contentPane.add(jbt_stop);
		
		JButton jbt_close = new JButton("关闭服务器");
		jbt_close.setBounds(391, 32, 113, 27);
		contentPane.add(jbt_close);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 89, 223, 369);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6D88\u606F\u8BB0\u5F55", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(281, 89, 223, 369);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5728\u7EBF\u7528\u6237", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		scrollPane_1.setViewportView(textArea_1);
		
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                jbt_close.doClick();
            }
        });
        
		jbt_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbt_start.setEnabled(false);
				jbt_stop.setEnabled(true);
				server.startServer();
			}
		});
		
		jbt_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					server.stopServer();
					jbt_start.setEnabled(true);
					jbt_stop.setEnabled(false);
			}
		});
		
		jbt_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (jbt_stop.isEnabled()) {
					int flag = JOptionPane.showConfirmDialog(jbt_stop, "是否要关闭服务器？", "确认",JOptionPane.OK_CANCEL_OPTION);
					if (flag == JOptionPane.OK_OPTION) {
						jbt_stop.doClick();
						server.close();
					}
				}
				else
					server.close();
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}
}
