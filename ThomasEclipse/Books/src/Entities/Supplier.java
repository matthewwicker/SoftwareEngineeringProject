package Entities;

public class Supplier {

	private int supplierid;
	public int getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactCell() {
		return contactCell;
	}
	public void setContactCell(String contactCell) {
		this.contactCell = contactCell;
	}
	public String getContactBuisness() {
		return contactBuisness;
	}
	public void setContactBuisness(String contactBuisness) {
		this.contactBuisness = contactBuisness;
	}
	private String name;
	private int uid;
	private String contactName;
	private String contactCell;
	private String contactBuisness;
}
