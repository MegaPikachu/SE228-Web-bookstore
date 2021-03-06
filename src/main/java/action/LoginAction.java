package action;

import service.AppService;
import service.LoginService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.Map;  
import com.opensymphony.xwork2.ActionContext;  
import com.opensymphony.xwork2.ActionSupport;

import model.User;  

public class LoginAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private AppService appService;
	private LoginService loginService;
	private String username;
	private String password;
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
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	
	@Override
	public String execute() throws Exception {
		
		/*
		Boolean flag = loginService.login_ok(username, password);
		User user = appService.getUserByUsername(username);
		if (flag){
			inputStream = new ByteArrayInputStream(user.getRole()  
	                .getBytes("UTF-8")); 
			ActionContext actionContext = ActionContext.getContext();  
			Map<String, Object> session = actionContext.getSession();  
			session.put("username", username);
			session.put("role", user.getRole());
			
	
			return "success";
		}
		else{
			inputStream = new ByteArrayInputStream("log in error"  
	                .getBytes("UTF-8")); 
			return "success";
		}
		*/
		String the_result = loginService.login_ok(username, password);
		inputStream = new ByteArrayInputStream(the_result  .getBytes("UTF-8")); 
		return "success";
		
	}


	
}
