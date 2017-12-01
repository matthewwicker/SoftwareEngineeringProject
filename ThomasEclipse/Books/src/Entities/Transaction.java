package Entities;

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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setPromoCode(String code) {
		this.promoCode = code;
	}
	public String getPromoCode() {
		return promoCode;
	}
	private int transactioncol;
	private int cartid;
	private int ccid;
	private double amount;
	private String date;
	private String promoCode;
	
}
