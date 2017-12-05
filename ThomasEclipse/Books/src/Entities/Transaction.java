package Entities;

import java.util.Date;

public class Transaction {

	public int getTransactioncol() {
		return transactioncol;
	}
	public void setTransactioncol(int transactioncol) {
		this.transactioncol = transactioncol;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public int getCcid() {
		return ccid;
	}
	public void setCcid(int ccid) {
		this.ccid = ccid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setPromoCode(String code) {
		this.promoCode = code;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setStatus(String code) {
		this.status = code;
	}
	public String getStatus() {
		return status;
	}
	private int transactioncol;
	private int cartid;
	private int ccid;
	private double amount;
	private Date date;
	private String status;
	private String promoCode;
	
}
