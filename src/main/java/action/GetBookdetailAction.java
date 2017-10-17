package action;


import org.apache.struts2.json.annotations.JSON;  

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.Bookdetail;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.AppService;


public class GetBookdetailAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private String result;
	private Map<String,Object> dataMap;
	private double price;
	private double amount;
	private String title;
	
	private AppService appService;
	private InputStream inputStream;

    public InputStream getInputStream() {  
        return inputStream;  
    }  
  
    public void setInputStream(InputStream inputStream) {  
        this.inputStream = inputStream;  
    }  
	
	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public Map<String,Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String,Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@Override
	public String execute() throws Exception {

		Bookdetail bookdet= appService.getBookdetailById(id);
		String book_detail = bookdet.getDetail();
		
	//	dataMap = new HashMap<String, Object>();  
	//	dataMap.put("bookdetail", book_detail);
		
	//	request().setAttribute("bookdetail", book_detail);
	 //  	inputStream = new ByteArrayInputStream(book_detail
     //           .getBytes("UTF-8")); 	
	//   	ActionContext actionContext = ActionContext.getContext();  
	//	Map<String, Object> session = actionContext.getSession();  
	//	session.put("bookdetail", book_detail);
	   	
	 //  	JSONObject jsonObject = new JSONObject();   
	 //  	jsonObject.put("bookdetail", book_detail);
	  // 	JSONArray jsonArray = new JSONArray(); 
	 //  	jsonArray.add(0,book_detail);
	 //  	jarray = jsonArray;
	 //  	JSONArray.fromObject(String)
		
	   	Map<String,String> map = new HashMap<String,String>();
	   	map.put("bookdetail", book_detail);
	//   	map.put("2", "3");
	   	JSONObject json = JSONObject.fromObject(map);
	   	result = json.toString();
	   	//ServletActionContext.getResponse().getWriter().print(result);
	   	session().setAttribute("last_price", price);
	 //  	session().setAttribute("last_amount", amount);
	   	session().setAttribute("last_title", title);
	   	session().setAttribute("last_id", id);
	   	return SUCCESS;
	}










}