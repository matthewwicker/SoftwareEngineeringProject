package Entities;

import java.time.LocalDate;
import java.util.Formatter;
import java.time.format.DateTimeFormatter;
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
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-M-d");
		LocalDate date = LocalDate.parse(startDate, formatter);
		this.startDate = date;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-M-d");
		LocalDate date = LocalDate.parse(endDate, formatter);
		this.endDate = date;
	}
	private int ISBN;
	private double percentOff;
	private LocalDate startDate;
	private LocalDate endDate;
}
