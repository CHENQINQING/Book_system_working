package classes;


public class book_has_sales {

	private String idBook_FK;
	
	private String idSales_FK;
	
	private String TRADE_SUM;
	
	private Book book;
	
	private sales sales;

	public String getidBook_FK() {
		return idBook_FK;
	}

	public void setBOOK_ID_FK(String idBook_FK) {
		idBook_FK = idBook_FK;
	}

	public String getidSales_FK() {
		return idSales_FK;
	}

	public void setidSales_FK(String idSales_FK) {
		idSales_FK = idSales_FK;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public sales getsales() {
		return sales;
	}

	public void setSaleRecord(sales sales) {
		this.sales = sales;
	}

	public String getTRADE_SUM() {
		return TRADE_SUM;
	}

	public void setTRADE_SUM(String trade_sum) {
		TRADE_SUM = trade_sum;
	}
	
	
}
