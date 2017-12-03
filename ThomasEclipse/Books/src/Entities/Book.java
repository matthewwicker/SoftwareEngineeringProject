package Entities;

public class Book {
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public int getSupplier() {
		return supplier;
	}
	public void setSupplier(int supplierid) {
		this.supplier = supplierid;
	}
	private int ISBN;
	private String author;
	private String title;
	private double price;
	private String genre;
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	private String description;
	private int rating;
	private int quantity;
	private int threshold;
	private int supplier;
	private String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	private int edition;
	private String publisher;
	private int publicationYear;
	private double buyingPrice;
	private double sellingPrice;
	
	public void setEdition(int editon) {
		this.edition = edition;
	}
	public int getEdition() {
		return edition;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublicationYear(int year) {
		this.publicationYear = year;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setBuyingPrice(double bprice) {
		this.buyingPrice = bprice;
	}
	public double getBuyingPrice() {
		return buyingPrice;
	}
	public void setSellingPrice(double sprice) {
		this.sellingPrice = sprice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	//other stuff
	//supplierID as an int
}
