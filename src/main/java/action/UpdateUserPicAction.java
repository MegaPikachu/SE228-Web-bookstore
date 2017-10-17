package action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Date;

import model.Book;
import model.Bookdetail;
import model.User;
import service.AppService;

public class UpdateUserPicAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String picture;

	private AppService appService;


	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	
	@Override
	public String execute() throws Exception {

		String username = (String)session().getAttribute("username");
		User user = appService.getUserByUsername(username);
		int id = user.getId();
		String src = picture;
		appService.updateUserpicture(id, src);
	//	attachment.createNewFile();
	//	int w=1;
		return SUCCESS;
	}



}
