package currency;

public class Person {
	String zhanghao = "";
	String id = "";
	String name = "";
	String sex = "";
	String college = "";
	String major = "";
	String grade = "";
	String classes = "";
	String birthday = "";
	String telephone = "";
	String chtype = "";
	
	public Person() {
		
	}
	
	public Person(String zhanghao, String id, String name, String sex, String college, String major, String grade,
			String classes, String birthday, String telephone, String chtype) {
		super();
		this.zhanghao = zhanghao;
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.college = college;
		this.major = major;
		this.grade = grade;
		this.classes = classes;
		this.birthday = birthday;
		this.telephone = telephone;
		this.chtype = chtype;
	}
	
//教师
	public Person(String zhanghao, String id, String name, String college, String major, String grade, String telephone,
			String chtype) {
		super();
		this.zhanghao = zhanghao;
		this.id = id;
		this.name = name;
		this.college = college;
		this.major = major;
		this.grade = grade;
		this.telephone = telephone;
		this.chtype = chtype;
	}

//	学生
	public Person(String zhanghao, String id, String name, String college, String major, String grade, String classes,
			String telephone, String chtype) {
		super();
		this.zhanghao = zhanghao;
		this.id = id;
		this.name = name;
		this.college = college;
		this.major = major;
		this.grade = grade;
		this.classes = classes;
		this.telephone = telephone;
		this.chtype = chtype;
	}

	public String getZhanghao() {
		return zhanghao;
	}
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getChtype() {
		return chtype;
	}
	public void setChtype(String chtype) {
		this.chtype = chtype;
	}
	
	@Override
	public String toString() {
		return "账号:" + zhanghao + ",姓名:" + name;
	}	
}
