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
		return;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress() {
		return address;
	}
	public String getBilladd() {
		return billadd;
	}
	public void setBilladd(String billadd) {
		this.billadd = billadd;
	}
	public String getShipadd() {
		return shipadd;
	}
	public void setShipadd(String shipadd) {
		this.shipadd = shipadd;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setAddress(String address1, String address2) {
		this.address = address1 + address2;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public String getSubscribed() {
		return subscribed;
	}
	public void setSubscribed(String subscribed) {
		System.out.println("SOMEONE CALLED THIS METHOD");
		this.subscribed = subscribed;
	} 
	public String getSuspended() {
		return suspended;
	}
	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}
	
	private int uid;
	private String email;
	private String fname;
	private String lname;
	private String password;
	private String phoneNumber;
	private String type;
	private String address;
	private String billadd;
	private String shipadd;
	private String username;
	private String subscribed;
	private String suspended;	
}
