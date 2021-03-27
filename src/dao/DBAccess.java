package dao;

import java.sql.*;

public class DBAccess {
	private Statement stmt=null;//SQL语句对象
	public ResultSet rs=null;//查询语句返回的对象
	private Connection conn=null;//连接
	private PreparedStatement prestmt=null;
	private String driver="com.mysql.cj.jdbc.Driver";//数据库驱动
	private String dbName="signinsystem";  //数据库名称     
	private String url ="jdbc:mysql://localhost:3306/"+dbName+"?serverTimezone=UTC&useSSL=false";
	private String user="root";
	private String pwd="root";
	private String notes="数据库操作提示";
	
	//数据库连接
	public void dbconn() {
		try {
			Class.forName(driver);//加载数据库驱动程序
			conn=DriverManager.getConnection(url,user,pwd);//建立连接
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );//向数据库发送SQL语句
		}
		catch(ClassNotFoundException ec) {
			System.out.println(ec);
		}//捕获类对象异常
		catch(SQLException es) {
			System.out.println(es);
		}//捕获数据库异常
		catch(Exception ex) {
			System.out.println(ex);
		}//捕获其他异常
	}
	
	//查询数据库记录
	public ResultSet dbSelect(String selString) {
		try {
			rs=stmt.executeQuery(selString);//执行查询
		}
		catch(SQLException es) {
			System.out.println(es);
			notes="数据库查询出现异常！";
		}
		return rs;
	}
	
	//更新数据库记录
	public String dbUpdate(String updateString) {
		try {
			prestmt=conn.prepareStatement(updateString);//生成预编译
			prestmt.executeUpdate();
			notes="记录更新成功！";
		}
		catch(SQLException es) {
			System.out.println(es);
			notes="数据库更新出现异常！";
		}
		return notes;
	}
	
	//插入数据库记录
	public String dbInsert(String insertString) {
		try {
			prestmt=conn.prepareStatement(insertString);
			prestmt.executeUpdate();
			notes="记录插入成功！";
		}
		catch(SQLException es) {
			System.out.println(es);
			notes="数据库插入出现异常！";
		}
		return notes;
	}
	
	//删除数据库记录
	public String dbDelete(String delString) {
		try {
			prestmt=conn.prepareStatement(delString);
			prestmt.executeUpdate();
			notes="记录删除成功！";
		}
		catch(SQLException es) {
			System.out.println(es);
			notes="数据库删除出现异常！";
		}
		return notes;
	}
	
	//关闭数据库连接
	public void dbclose() {
		if(conn!=null) {
			try {
				rs.close();//关闭记录集
				stmt.close();//关闭SQL语句发送
				conn.close();//关闭数据库连接
			}
			catch(Exception e) {}
		}
	}
}
