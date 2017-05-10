package classes;

import java.util.List;
import java.util.Vector;


public class sales {
	private String RECORD_DATE;
	private int amount;
	private double totalPrice;
	private Vector<book_has_sales> book_has_sales;
	
	private String bookNames;

	public String getRECORD_DATE() {
		return RECORD_DATE;
	}

	public void setRECORD_DATE(String record_date) {
		RECORD_DATE = record_date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Vector<book_has_sales> getBookSaleRecords() {
		return book_has_sales;
	}

	public void setBookSaleRecords(Vector<book_has_sales> book_has_sales) {
		this.book_has_sales = book_has_sales;
	}

	public String getBookNames() {
		return bookNames;
	}

	public void setBookNames(String bookNames) {
		this.bookNames = bookNames;
	}
	
	
}
