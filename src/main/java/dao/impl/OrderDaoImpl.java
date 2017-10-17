package dao.impl;

import java.util.List;
import java.util.Map;

import model.Order;
import model.Orderitem;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.OrderDao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.catalina.LifecycleListener;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	public Integer save(Order order) {
		return (Integer) getHibernateTemplate().save(order);
	}

	public void delete(Order order) {
		getHibernateTemplate().delete(order);
	}

	public void update(Order order) {
		getHibernateTemplate().merge(order);
	}

	public Order getOrderById(int id) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where o.id=?", id);
		Order order = orders.size() > 0 ? orders.get(0) : null;
		return order;
	}

	public List<Order> getAllOrders() {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order");
		return orders;
	}

	public List<Orderitem> get_specific_orders(int userid, Date start_date, Date end_date, int thebookid) {
	//	Configuration cfg = new Configuration().configure();
		   //      SessionFactory factory = cfg.buildSessionFactory();
		    //     Session session = factory.openSession();
		    //     Connection con = session.connection();
		//Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
			//	SQLQuery sqlQuery = session.createSQLQuery("call order_statistics(?,?,?)");
		SQLQuery sqlQuery = getSessionFactory().openSession().createSQLQuery("CALL orderitem_statistics(?,?,?,?)");
		       //  String sql = "{call order_statistics(?,?,?)}";
		   //      try {
		    //     CallableStatement cs = con.prepareCall(sql);
		        sqlQuery.setInteger(0, userid);
		         sqlQuery.setDate(1, start_date);
		         sqlQuery.setDate(2, end_date);
		         sqlQuery.setInteger(3, thebookid);
		         sqlQuery.executeUpdate();
		         SQLQuery query = getSessionFactory().openSession().createSQLQuery("select * from selected_orderitem");
		         //query.executeUpdate();
		         List<Orderitem> get_orderitem  =(List<Orderitem>)query.list();
		         System.out.println(((List<Orderitem>)query.list()).size());
		         int i = 1;
		         for(i = 2; i < 2;i++);
		         
		         //   Map map = (Map)list.get(i);
		         ///	 ResultSet rs = cs.executeQuery();
			//	} catch (SQLException e) {
			//		// TODO Auto-generated catch block
			//		e.printStackTrace();
		//		}
		      //   while(rs.next()){
		           //  int id = rs.getInt("empId");
		           //  String name = rs.getDate("");
		           //  System.out.println(id+"\t"+name);
		      //   }
		
		return get_orderitem;
	}

}
