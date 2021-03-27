package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import dao.DBAccess;
import dao.DBOperations;
 
 
//继承Thread类，使用多线程
public class Client extends Thread {
	DBAccess db=new DBAccess();
	String sqls;
    //创建客户端Socket对象
    public Socket c_socket;
    private Client_LandFrame c_LandFrame;
    private Client_TeaFrame c_TeaFrame;
    private Client_StuFrame c_StuFrame;
    //创建数据输入流对象
    public DataInputStream dis = null;
    //创建数据输出流对象
    public DataOutputStream dos = null;
    //退出标记
    private boolean flag_exit = false;
    //定义线程id
    private int threadID;
    //初始化账号
    public String id = null;


	public int getThreadID() {
		return threadID;
	}

	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

	public Client_LandFrame getC_LandFrame() {
		return c_LandFrame;
	}

	public void setC_LandFrame(Client_LandFrame c_LandFrame) {
		this.c_LandFrame = c_LandFrame;
	}

	public Client_TeaFrame getC_TeaFrame() {
		return c_TeaFrame;
	}

	public void setC_TeaFrame(Client_TeaFrame c_TeaFrame) {
		this.c_TeaFrame = c_TeaFrame;
	}

	public Client_StuFrame getC_StuFrame() {
		return c_StuFrame;
	}

	public void setC_StuFrame(Client_StuFrame c_StuFrame) {
		this.c_StuFrame = c_StuFrame;
	}

	public Client() {
    }
  
    /**登录
     *
     * @param username  用户名
     * @param hostIp    主机IP
     * @param hostPort  主机端口
     * @return
     *
     * 登录模块
     * 加入登录错误提示功能
     * 可提示的内容有：端口号错误，主机地址错误，连接服务异常
     * 如果三项都输入正确，则返回true
     *
     */
    public String login(String id, String hostIp, String hostPort) {
        this.id = id;
        String login_mess = null;
        try {
            c_socket = new Socket(hostIp, Integer.parseInt(hostPort));
        } catch (NumberFormatException e) {
            login_mess = "连接的服务器端口号port为整数,取值范围为：1024<port<65535";
            return login_mess;
        } catch (UnknownHostException e) {
            login_mess = "主机地址错误";
            return login_mess;
        } catch (IOException e) {
            login_mess = "连接服务器失败，请稍后再试";
            return login_mess;
        }
        return "true";
    }
 
    //显示学生界面
    public void showStuFrame(String chtype) {
        getDataInit();
        c_StuFrame = new Client_StuFrame(this, id);
        c_StuFrame.setVisible(true);
        //将退出标记置为true
        flag_exit = true;
        //启动该线程
        this.start();
 
    }
 
    //显示教师界面
    public void showTeaFrame(String chtype) {
        getDataInit();
        c_TeaFrame = new Client_TeaFrame(this, id);
        c_TeaFrame.setVisible(true);
        //将退出标记置为true
        flag_exit = true;
        //启动该线程
        this.start();
 
    }
    
    //获取数据单元模块
    //从输入流中获取数据，封装到通道
    private void getDataInit() {
        try {
            dis = new DataInputStream(c_socket.getInputStream());
            dos = new DataOutputStream(c_socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
 
    public void run() {	
    	db.dbconn();//连接数据库
		sqls = "select * from 提醒";
		db.dbSelect(sqls);
		
//		当客户端登录时 先从数据库里查看 是否有批复的请假 或 是否被提醒打卡
		try {
			while(db.rs.next()) {
				if(db.rs.getString("账号").equals(id)) {
					String piyu = db.rs.getString("批语");
					String date = db.rs.getString("日期");
					if(db.rs.getString("事件").equals("打卡"))
					{
						if(c_StuFrame != null)
	        				c_StuFrame.getremind();
	        			else
	        				c_TeaFrame.getremind();
						sqls = "delete from 提醒 where 账号 ='"+ id +"'and 事件 = '打卡' and 日期 = '1'";
						db.dbDelete(sqls);
					}
					else
					{
						if(c_StuFrame != null)
	        				c_StuFrame.getreply("批语:"+piyu+"\n日期:"+date);
	        			else
	        				c_TeaFrame.getreply("批语:"+piyu+"\n日期:"+date);
						sqls = "delete from 提醒 where 账号 ='"+ id +"'and 事件 = '请假' and 日期 = '"+date+"'";
						db.dbDelete(sqls);
					}
				}
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		db.dbclose();
		
        while (flag_exit) {
        	try {
//        		读取输入 会阻塞
        		String Message = dis.readUTF();
//        		消除前后的空格
        		Message.trim();
//        		收到线程id时 把用户id发送给服务器
        		if(Message.contains("@threadId")) {
        			//以@threadId分割Message字符串，得到userid
        			String[] userid = Message.split("@threadId");
        			setThreadID(Integer.parseInt(userid[0]));
        			dos.writeUTF(userid[0]+"@userid"+id+"@userid");
        			continue;
        		}
//        		收到打卡提醒时
        		if (Message.contains("@tixing")) {
        			if(c_StuFrame != null)
        				c_StuFrame.getremind();
        			else
        				c_TeaFrame.getremind();
        			continue;
        		}
        		if (Message.contains("@qingjia")) {
        			String[] userInfo = Message.split("@qingjia");
        			if(c_StuFrame != null)
        				c_StuFrame.getreply("批语:"+userInfo[0]+"\n日期:"+userInfo[1]);
        			else
        				c_TeaFrame.getreply("批语:"+userInfo[0]+"\n日期:"+userInfo[1]);
        			continue;
        		}
        		if (Message.contains("@exit")) {
        			if(c_StuFrame != null)
        				c_StuFrame.getrelogin();
        			else
        				c_TeaFrame.getrelogin();
        			exitLogin();
        			continue;
        		}
        		if (Message.contains("@serverexit")) {
        			dos.writeUTF("@clientexit");
        			if(c_StuFrame != null)
        				c_StuFrame.closeClient();
        			else
        				c_TeaFrame.closeClient();
        			continue;
        		}
        	} catch (IOException e) {
        		// TODO 自动生成的 catch 块
        		e.printStackTrace();
        	}
        }
    }
 
    //传输数据
    public void transMess(String mess) {
        try {
            dos.writeUTF(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    //退出登录
    public void exitLogin() {
    	 try {
             dos.writeUTF(getThreadID()+"@exit"+id+"@exit");
             flag_exit = false;
             System.exit(0);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
 
    //退出客户端
    public void exitClient() {
        flag_exit = false;
        System.exit(0);
    }
    
    public static void main(String[] args) {
        Client client = new Client();
        Client_LandFrame c_LandFrame = new Client_LandFrame(client);
        client.setC_LandFrame(c_LandFrame);
        //显示窗体
        c_LandFrame.setVisible(true);
    }
}
