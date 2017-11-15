package Entities;

public class User {
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getfName() {
		return fname;
	}
	public void setfName(String fname) {
		this.fname = fname;
	}
	public String getlName() {
		return lname;
	}
	public void setlName(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private int uid;
	private String email;
	private String fname;
	private String lname;
	private String password;
	private String phoneNumber;
	private String type;
	
}
