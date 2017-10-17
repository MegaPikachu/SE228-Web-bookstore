package service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mongodb.gridfs.GridFSDBFile;
import com.opensymphony.xwork2.ActionContext;

import model.Book;
import model.Bookdetail;
import model.Order;
import model.Orderitem;
import model.User;
import service.AppService;
import dao.BookDao;
import dao.BookdetailDao;
import dao.OrderDao;
import dao.OrderitemDao;
import dao.UserDao;
import dao.UserPicDao;


public class AppServiceImpl implements AppService {

	private BookDao bookDao;
	private BookdetailDao bookdetailDao;
	private OrderDao orderDao;
	private OrderitemDao orderitemDao;
	private UserDao userDao;
	private UserPicDao userPicDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public BookdetailDao getBookdetailDao() {
		return bookdetailDao;
	}
	
	public void setBookdetailDao(BookdetailDao bookdetailDao) {
		this.bookdetailDao = bookdetailDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setOrderitemDao(OrderitemDao orderitemDao) {
		this.orderitemDao = orderitemDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setUserPicDao(UserPicDao userPicDao) {
		this.userPicDao = userPicDao;
	}

	/**
	 * book
	 * 
	 */
	public Integer addBook(Book book) {
		return bookDao.save(book);
	}
	
	public void addBookdetail(Bookdetail bookdetail){
		bookdetailDao.saveOrUpdate(bookdetail);
	}
	
	public void addBookpicture(int id, String source){
		bookdetailDao.savePicture(id, source);
	}
	
	public void updateBookpicture(int id, String source){
		bookdetailDao.updatePicture(id, source);
	}
	
	public void addUserpicture(int id, String source){
		userPicDao.savePicture(id, source);
	}
	
	public void updateUserpicture(int id, String source){
		userPicDao.updatePicture(id, source);
	}
	
	public GridFSDBFile getUserPicture(int id){ 
		return userPicDao.getUserPicture(id);
	}
	
	public void deleteBook(Book book) {
		bookDao.delete(book);
		Bookdetail detail = bookdetailDao.findById(book.getId());
		bookdetailDao.remove(detail);
		bookdetailDao.deletePicture(book.getId());
	}
	
	public void updateBook(Book book) {
		bookDao.update(book);
	}

	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}
	
	public Bookdetail getBookdetailById(int id) {
		return bookdetailDao.findById(id);
	}

	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	/**
	 * order
	 * 
	 */
	public Integer addOrder(Order order) {
		return orderDao.save(order);
	}

	public void deleteOrder(Order order) {
		orderDao.delete(order);
	}

	public void updateOrder(Order order) {
		orderDao.update(order);
	}

	public Order getOrderById(int id) {
		return orderDao.getOrderById(id);
	}

	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}

	public List<Orderitem> get_specific_orders(int userid, Date start_date, Date end_date, int thebookid) {
		return orderDao.get_specific_orders(userid, start_date, end_date, thebookid);
	}
	/**
	 * order item
	 * 
	 */
	public Integer addOrderitem(Orderitem orderitem) {
		return orderitemDao.save(orderitem);
	}

	public void deleteOrderitem(Orderitem orderitem) {
		orderitemDao.delete(orderitem);
	}

	public void updateOrderitem(Orderitem orderitem) {
		orderitemDao.update(orderitem);
	}

	public Orderitem getOrderitemById(int id) {
		return orderitemDao.getOrderitemById(id);
	}

	public List<Orderitem> getAllOrderitems() {
		return orderitemDao.getAllOrderitems();
	}

	/**
	 * user
	 * 
	 */
	public Integer addUser(User user) {
		return userDao.save(user);
	}

	public void deleteUser(User user) {
		userDao.delete(user);
	}

	public void updateUser(User user) {
		userDao.update(user);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}
	
	public User getUserByUsername(String username){
		return userDao.getUserByUsername(username);
	}
	

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	
	public boolean duplicate_username(String username){
		return userDao.duplicate_username(username);
	}

	public void pay(Date date) {
		ActionContext actionContext = ActionContext.getContext();  
		Map<String, Object> session = actionContext.getSession();  
		String username = (String)session.get("username");
		User the_user = userDao.getUserByUsername(username);
		int the_userid = the_user.getId();		
		
		Order order = new Order(the_userid, date);
		int the_orderid = orderDao.save(order);
	 	List<Integer> session_bookid=(List<Integer>)session.get("bookid"); 
	 	List<Double> session_price=(List<Double>)session.get("price"); 
	 	List<Double> session_amount=(List<Double>)session.get("amount"); 
		
	 	int the_bookid = 0;
	 	double the_price = 0;
	 	double the_amount = 0;
	 	Orderitem orderitem;
	 	if (session_bookid != null){
		 	for (int i = 0; i < session_bookid.size(); i++){
		 		the_bookid = session_bookid.get(i);
		 		the_price = session_price.get(i);
		 		the_amount = session_amount.get(i);
				orderitem = new Orderitem(the_orderid, the_bookid, (int)the_amount, the_price);
				orderitemDao.save(orderitem);
		 	}
	 	}
	 	session.clear();
		actionContext = ActionContext.getContext();  
		Map<String, Object> session2 = actionContext.getSession();  
	 	session2.put("username", username);
	 	session2.put("role", "Customer");		
	}

	public void addtoshoppingcart(double amount){
		ActionContext actionContext = ActionContext.getContext();  
		Map<String, Object> session = actionContext.getSession();  
		List<Integer> temp_bookid;
		List<Double> temp_price;
		List<Double> temp_amount;
		List<String> temp_title;
		int bookid = (int)session.get("last_id");
		double price = (double)session.get("last_price");
	//	amount = (double)session.get("last_amount");
		String title = (String)session.get("last_title");
		if (session.get("bookid") != null){
			temp_bookid = (List<Integer>)session.get("bookid");
			temp_bookid.add(bookid);
		}
		else{
			temp_bookid = new LinkedList<Integer>();
			temp_bookid.add(bookid);
		}
		
		if (session.get("price") != null){
			temp_price = (List<Double>)session.get("price");
			temp_price.add(price);
		}
		else{
			temp_price = new ArrayList<Double>();
			temp_price.add(price);
		}
		
		if (session.get("amount") != null){
			temp_amount = (List<Double>)session.get("amount");
			temp_amount.add(amount);
		}
		else{
			temp_amount = new ArrayList<Double>();
			temp_amount.add(amount);
		}		
		
		if (session.get("title") != null){
			temp_title = (List<String>)session.get("title");
			temp_title.add(title);
		}
		else{
			temp_title = new ArrayList<String>();
			temp_title.add(title);
		}		
		
		session.put("bookid", temp_bookid);
		session.put("price", temp_price);
		session.put("amount", temp_amount);
		session.put("title", temp_title);
	}
	
	public void deleteShoppingcart(int bookid){
		ActionContext actionContext = ActionContext.getContext();  
		Map<String, Object> session = actionContext.getSession();  
		List<Integer> temp_bookid = (List<Integer>)session.get("bookid");
		List<Double> temp_price = (List<Double>)session.get("price");
		List<Double> temp_amount = (List<Double>)session.get("amount");
		List<String> temp_title = (List<String>)session.get("title");
		int size = temp_bookid.size();
		int index = 0;
		for (int i = 0; i < size; i++){
			if (temp_bookid.get(i).equals(bookid) ){ 
				index = i;
			}
		}
		temp_bookid.remove(index);
		temp_price.remove(index);
		temp_amount.remove(index);
		temp_title.remove(index);
	}

}
