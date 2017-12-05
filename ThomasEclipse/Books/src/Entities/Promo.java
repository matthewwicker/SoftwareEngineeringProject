package Entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Calendar;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(startDate, formatter);
            this.startDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        catch(Exception e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(startDate, formatter);
            this.startDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(endDate, formatter);
            this.endDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        catch(Exception e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(endDate, formatter);
            this.endDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }
    
    public boolean isValidForDate() {
        Date dateInQuestion = this.getEndDate();
        System.out.println("HERE IS THE END DATE OF THIS PROMO: " + this.getEndDate());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateInQuestion);
        
        LocalDate localDate = LocalDate.now();
        Date futureDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("HERE IS THE DATE Now: " + futureDate);
        if (dateInQuestion.after(futureDate)) {
          // If today is after the dateinquestion (end of promo) is after today, we are good. return true
            return true;
        }
        System.out.println("THE PROMO IS INVALID");
        return false;
    }
    
	private int ISBN;
	private double percentOff;
	private Date startDate;
	private Date endDate;
}
