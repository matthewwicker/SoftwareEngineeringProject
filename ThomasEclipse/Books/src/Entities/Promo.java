package Entities;

import java.time.LocalDate;
import java.util.Date;

public class Promo {
/*
 code
int(11) PK
isbn
int(11)
percentoff
double
startdate
datetime
enddate
datetime
 */
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public double getPercentOff() {
		return percentOff;
	}
	public void setPercentOff(double percentOff) {
		this.percentOff = percentOff;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	private int ISBN;
	private double percentOff;
	private String startDate;
	private String endDate;
}
