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

public class PayAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private InputStream inputStream;
	private Date date;
	private AppService appService;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setAppService(AppService appService) {
		this.appService = appService;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public String execute() throws Exception {
		
		appService.pay(date);
		
		return SUCCESS;
	}


}
