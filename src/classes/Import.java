package classes;

import java.util.Vector;

public class Import {

	private String RECORD_DATE;
	
	private int amount;
	
	private Vector<Import_has_book> Import_has_book;
	
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

	public Vector<Import_has_book> getImport_has_book() {
		return Import_has_book;
	}

	public void setBookInRecords(Vector<Import_has_book> Import_has_book) {
		this.Import_has_book = Import_has_book;
	}

	public String getBookNames() {
		return bookNames;
	}

	public void setBookNames(String bookNames) {
		this.bookNames = bookNames;
	}
	
	
}
