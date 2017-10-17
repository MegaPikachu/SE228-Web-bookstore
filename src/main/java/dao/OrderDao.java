package dao;

import java.sql.Date;
import java.util.List;

import model.Order;
import model.Orderitem;

public interface OrderDao {

	public Integer save(Order order);

	public void delete(Order order);

	public void update(Order order);

	public Order getOrderById(int id);

	public List<Order> getAllOrders();
	
	public List<Orderitem> get_specific_orders(int userid, Date start_date, Date end_date, int thebookid);

}