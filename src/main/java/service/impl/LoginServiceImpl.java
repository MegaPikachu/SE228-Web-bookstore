package service.impl;


import service.LoginService;

import java.io.ByteArrayInputStream;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

public class LoginServiceImpl implements LoginService {
	private UserDao userDao;
	
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public String login_ok(String username, String password){
		Boolean flag = userDao.login_ok(username,password);
		User user = userDao.getUserByUsername(username); 
		if (flag){	
			ActionContext actionContext = ActionContext.getContext();  
			Map<String, Object> session = actionContext.getSession();  
			session.put("username", username);
			session.put("role", user.getRole());
			return user.getRole();
		}
		else{
			return "log in error";
		}
	}

}
