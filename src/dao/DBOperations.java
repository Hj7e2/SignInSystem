package dao;

public class DBOperations {
	public DBAccess db=new DBAccess();//封装了数据库操作的类
	String sql;
	String notes="";
	public String DBoperations(String sql,int action){
		db.dbconn();
		switch(action) {
		case 0:
			db.dbSelect(sql);
			break;
		case 1:
			notes=db.dbUpdate(sql);
			db.dbclose();
			break;
		case 2:
			notes=db.dbInsert(sql);
			db.dbclose();
			break;
		case 3:
			notes=db.dbDelete(sql);
			db.dbclose();
			break;
		}
		return notes;
	}
}
