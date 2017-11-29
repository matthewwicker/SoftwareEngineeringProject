package Entities;

public class CartItem {

	private int ISBN;
	private int numBooks;
	private int cartId;
	private double price;
	private double total;
	private String title;

	
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double prc) {
		price = prc;
	}
	public int getNumBooks() {
		return numBooks;
	}
	public void setNumBooks(int numBooks) {
		this.numBooks = numBooks;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public double getTotal() {
		return numBooks * price;
	}
	public void setTotal() {
		total = price * numBooks;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String tit) {
		title = tit;
	}
}
