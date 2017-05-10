package classes;


public class Import_has_book {

	private String idBook_FK;
	private String idImport_FK;
	private String IN_SUM;
	
	private Book book;
	
	private Import Import;

	public String getidBook_FK() {
		return idBook_FK;
	}

	public void setBOOK_ID_FK(String idBook_FK) {
		idBook_FK = idBook_FK;
	}

	public String getidImport_FK() {
		return idImport_FK;
	}

	public void setidImport_FK(String idImport_FK) {
		idImport_FK = idImport_FK;
	}

	public String getIN_SUM() {
		return IN_SUM;
	}

	public void setIN_SUM(String in_sum) {
		IN_SUM = in_sum;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Import getImport() {
		return Import;
	}

	public void setInRecord(Import Import) {
		this.Import = Import;
	}
	
}
