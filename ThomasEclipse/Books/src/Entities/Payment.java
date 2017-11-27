package Entities;

public class Payment {
	
	public int getCcid() {
		return ccid;
	}
	public void setCcid(int ccid) {
		this.ccid = ccid;
	}
	public int getCc_number() {
		return cc_number;
	}
	public void setCc_number(int cc_number) {
		this.cc_number = cc_number;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	private int ccid;
	private int cc_number;
	private int user;
	private int aid;
}
