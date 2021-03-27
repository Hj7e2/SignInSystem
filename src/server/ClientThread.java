package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.DBOperations;
 
/**
 * 客户端处理线程
 */
 
public class ClientThread extends Thread {
 
	DBOperations dbo=new DBOperations();
	String sqls;
    //服务器客户端Socket对象
    public Socket clientSocket;
    //服务端线程对象
    public ServerThread serverThread;
    //数据输入流对象
    public DataInputStream dis;
    //数据输出流对象
    public DataOutputStream dos;
    //标记
    private boolean flag_exit = false;
    String str;
 
    //客户端Socket
    public ClientThread(Socket socket, ServerThread serverThread) {
        clientSocket = socket;
        this.serverThread = serverThread;
        try {
            //封装数据输入输出流
            dis = new DataInputStream(clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    //重写run方法
    @Override
    public void run() {
        while (flag_exit) {
        	try {
//        		同步锁 服务器线程向客户端处理线程传递 客户端线程id
                synchronized (serverThread.messages) {
                    if (!serverThread.messages.isEmpty()) {
                        str = (String) serverThread.messages.firstElement();
                        serverThread.messages.removeElement(str);
                        //向客户端发送自己的线程id
                        if (str.contains("@threadId")) {
                           dos.writeUTF(str);
                           continue;
                        }
                    }
                }
                
        		String Message = dis.readUTF();
        		Message.trim();
        		if(Message.contains("@userid")) {
        			String[] userInfo = Message.split("@userid");
//        			检测该账号是否已登录
        			synchronized (serverThread.clients) {
        					for (int i = 0; i < serverThread.clients.size(); i++) {
        						int threadID = (int) serverThread.clients.elementAt(i).getId();
        						if (serverThread.users.get(threadID).equals(userInfo[1])) {
        							dos.writeUTF("@exit");
        						} 
        					}
        			}
//        			检测完再添加进用户表，为了后面删除
        			serverThread.users.remove(Integer.parseInt(userInfo[0]));
        			serverThread.users.put(Integer.parseInt(userInfo[0]), userInfo[1]);
        			continue;
        		}
        		
        		if (Message.contains("@tixing")) {
        			int flag = 0;
        			String[] userInfo = Message.split("@tixing");
        			synchronized (serverThread.clients) {
//        				如果客户端在线 就直接提醒 没有就存入数据库
        				for (int i = 0; i < serverThread.clients.size(); i++) {
        					int threadID = (int) serverThread.clients.elementAt(i).getId();
        					if (serverThread.users.get(threadID).equals(userInfo[0])) {
        						serverThread.clients.elementAt(i).dos.writeUTF("@tixing");
        						flag = 1;
        					} 
        				}
            			if(flag == 0) {
            				sqls="insert into 提醒 values('"+userInfo[0]+"','打卡','1','')";
            				dbo.DBoperations(sqls, 2);
            			}
        			}
        			continue;
        		}
        		
        		if (Message.contains("@qingjia")) {
        			int flag = 0;
        			String[] userInfo = Message.split("@qingjia");
        			synchronized (serverThread.clients) {
        				for (int i = 0; i < serverThread.clients.size(); i++) {
        					int threadID = (int) serverThread.clients.elementAt(i).getId();
        					if (serverThread.users.get(threadID).equals(userInfo[0])) {
        						serverThread.clients.elementAt(i).dos.writeUTF(userInfo[1]+"@qingjia"+userInfo[2]+"@qingjia");
        						flag = 1;
        					} 
        				}
        				if(flag == 0) {
            				sqls="insert into 提醒 values('"+userInfo[0]+"','请假','"+userInfo[2]+"','"+userInfo[1]+"')";
            				dbo.DBoperations(sqls, 2);
            			}
        			}	
        			continue;
        		}
        		
        		if (Message.contains("@exit")) {
                    String[] userInfo = Message.split("@exit");
                    int userID = Integer.parseInt(userInfo[0]);
                    serverThread.users.remove(userID);
                    synchronized (serverThread.clients) {
                        for (int i = 0; i < serverThread.clients.size(); i++) {
                            int threadID = (int) serverThread.clients.elementAt(i).getId();
                            if ( userID == threadID) {
                                serverThread.clients.removeElementAt(i);
                                i--;
                            }
                        }
                    }
//                    因为dis.read会阻塞 所以退出时发送无意义字段
                    dos.writeUTF("@无意义");
                    clientSocket.close();
                    setFlag_exit(false);
                    continue;
        		}
        		
        		if (Message.contains("@clientexit")) {
        			closeClienthread();
                    continue;
        		}
        	} catch (IOException e) {
        		// TODO 自动生成的 catch 块
        		e.printStackTrace();
        	}
        }
    }
 
    //关闭客户端线程，并提示服务器的客户端Socket为空
    public void closeClienthread() {
        if (clientSocket != null) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("server's clientSocket is null");
            }
        }
 
        try {
            setFlag_exit(false);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
 
    public void setFlag_exit(boolean flag_exit) {
        this.flag_exit = flag_exit;
    }
}