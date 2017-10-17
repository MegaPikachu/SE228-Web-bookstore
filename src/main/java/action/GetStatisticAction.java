package action;

import java.io.IOException;
import java.sql.Date;

import java.util.List;
import model.Order;
import model.Orderitem;
import net.sf.json.JSONArray;
import service.AppService;

public class GetStatisticAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	private int userid;
	private Date startdate;
	private Date enddate;
	private int thebookid;

	private AppService appService;

	public AppService getAppService() {
		return appService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getThebookid() {
		return thebookid;
	}

	public void setThebookid(int thebookid) {
		this.thebookid = thebookid;
	}
	
	public void get() throws Exception {

		List<Orderitem> orderitem = appService.get_specific_orders(userid, startdate, enddate, thebookid);
				
		try {
			JSONArray jsonArray = JSONArray.fromObject(orderitem);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
