package Entities;

public class Order {

	private int uid;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public int getNumOrdered() {
		return numOrdered;
	}
	public void setNumOrdered(int numOrdered) {
		this.numOrdered = numOrdered;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	private int ISBN;
	private int numOrdered;
	private double price;
	private int orderId;
	
}
