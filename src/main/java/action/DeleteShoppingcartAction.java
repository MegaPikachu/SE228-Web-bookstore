package action;

import service.AppService;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Map;  
import com.opensymphony.xwork2.ActionContext;  
import com.opensymphony.xwork2.ActionSupport;

import model.Book;
import model.Order;
import model.Orderitem;
import model.User;  

public class DeleteShoppingcartAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private int bookid;
	private AppService appService;
		
	public void setAppService(AppService appService) {
		this.appService = appService;
	}
	
	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	@Override
	public String execute() throws Exception {
		appService.deleteShoppingcart(bookid);		
		return SUCCESS;
	}





}
