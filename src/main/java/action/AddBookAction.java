package action;

import java.sql.Date;

import model.Book;
import model.Bookdetail;
import service.AppService;

public class AddBookAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String author;
	private double price;
	private String publisher;
	private Date date;
	private String detail;
	private String picture;

	private AppService appService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}


	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	
	@Override
	public String execute() throws Exception {

		Book book = new Book(title, author, price, publisher, date);
		int the_id = appService.addBook(book);
		
		Bookdetail bookdetail = new Bookdetail(the_id,detail);
		appService.addBookdetail(bookdetail);
		String src = picture;
		appService.addBookpicture(the_id, src);

		return SUCCESS;
	}



}
