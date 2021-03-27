package server;

public class Server {
	 
    private ServerFrame serverFrame;
    private ServerThread serverThread;
 
    public ServerFrame getServerFrame() {
        return serverFrame;
    }
 
    public void setServerFrame(ServerFrame serverFrame) {
        this.serverFrame = serverFrame;
    }
 
    public Server() {
    }
 
    //启动服务器
    public void startServer() {
        try {
            serverThread = new ServerThread(serverFrame);
        } catch (Exception e) {
            System.exit(0);
        }
        serverThread.setFlag_exit(true);
        serverThread.start();
    }
 
    //停止服务器
    public void stopServer() {
        serverThread.stopServer();
    }
 
    //关闭服务器
    public void close() {
        if (serverThread != null) {
            if (serverThread.isAlive()) {
                serverThread.stopServer();
            }
        }
        System.exit(0);
    }
 
    public static void main(String[] args) {
        Server server = new Server();
        ServerFrame serverFrame = new ServerFrame(server);
        server.setServerFrame(serverFrame);
        serverFrame.setVisible(true);
    }
}