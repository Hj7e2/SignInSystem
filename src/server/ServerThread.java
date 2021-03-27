package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ServerThread extends Thread{
	//服务端Socket对象
    public ServerSocket serverSocket;
    //信息 用Vector集合存储
    public Vector<String> messages;
    //用户线程 用Vector集合存储
    public Vector<ClientThread> clients;
    //Map集合存储用户信息
    public Map<Integer, String> users;

    //定义服务器端端口号
    public int Port = 8000;
    public boolean login = true;
    public ServerFrame serverFrame;
    private boolean flag_exit = false;
    
	public ServerThread(ServerFrame serverFrame) {
        this.serverFrame = serverFrame;
        //创建集合存储信息
        messages = new Vector<String>();
        clients = new Vector<ClientThread>();
        users = new HashMap<Integer, String>();
        try {
            serverSocket = new ServerSocket(Port);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null,"不能开启两个服务器！","错误",JOptionPane.WARNING_MESSAGE);//提示框
            System.exit(0);
        }
    }
	
	@Override
    public void run() {
        Socket socket;
        //当标记为ture的时候检查服务器端Socket是否在开启状态，如果关闭，将标记置为false
        while (flag_exit) {
            try {
                if (serverSocket.isClosed()) {
                    flag_exit = false;
                } 
                else {
                    try {
                        //监听客户端socket
                        socket = serverSocket.accept();
                    } catch (SocketException e) {
                        socket = null;
                        flag_exit = false;
                    }
 
                    //当客户端socket不为null时，启动客户端线程
                    if (socket != null) {
                        ClientThread clientThread = new ClientThread(socket, this);
                        clientThread.setFlag_exit(true);
                        clientThread.start();
                        synchronized (clients) {
                            clients.addElement(clientThread);
                        }
//                       通过message给客户端处理线程发送客户端线程id
                        synchronized (messages) {
                            users.put((int) clientThread.getId(), "@login");
                            messages.add(clientThread.getId() + "@threadId");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    //停止服务器
    public void stopServer() {
        try {
//        	向每个客户端处理线程发送服务器退出消息
        	synchronized (clients) {
				for (int i = 0; i < clients.size(); i++) {
						clients.elementAt(i).dos.writeUTF("@serverexit");
					} 
				}
            if (this.isAlive()) {
                serverSocket.close();
                setFlag_exit(false);
            }
        } catch (Throwable e) {
        }
    }
 
    //设置标记退出
    public void setFlag_exit(boolean b) {
        flag_exit = b;
    }
}
