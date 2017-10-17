package action;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import service.AppService;


public class AddtoShoppingcartAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private AppService appService;
	private int bookid;
	private double price;
	private double amount;
	private String title;
	
	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
	
	@Override
	public String execute() throws Exception {
		appService.addtoshoppingcart(amount);

		return SUCCESS;
	}


}
