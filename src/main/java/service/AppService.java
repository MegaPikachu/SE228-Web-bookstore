package service;

import java.sql.Date;
import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;

import model.Book;
import model.Bookdetail;
import model.Order;
import model.Orderitem;
import model.User;

/**
 * 
 * 
 */
public interface AppService {

	/**
	 * book
	 * 
	 */
	public Integer addBook(Book book);

	public void addBookpicture(int id, String source);
	
	public void updateBookpicture(int id, String source);
	
	public void addBookdetail(Bookdetail bookdetail);

	public void deleteBook(Book book);

	public void updateBook(Book book);

	public Book getBookById(int id);
	
	public Bookdetail getBookdetailById(int id);

	public List<Book> getAllBooks();

	/**
	 * order
	 * 
	 */
	public Integer addOrder(Order order);

	public void deleteOrder(Order order);

	public void updateOrder(Order order);

	public Order getOrderById(int id);

	public List<Order> getAllOrders();

	/**
	 * order item
	 * 
	 */
	public Integer addOrderitem(Orderitem orderitem);

	public void deleteOrderitem(Orderitem orderitem);

	public void updateOrderitem(Orderitem orderitem);

	public Orderitem getOrderitemById(int id);

	public List<Orderitem> getAllOrderitems();
	
	public List<Orderitem> get_specific_orders(int userid, Date start_date, Date end_date, int thebookid);

	/**
	 * user
	 * 
	 */
	public Integer addUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);

	public User getUserById(int id);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUsers();
	
	public void addUserpicture(int id, String source);
	
	public void updateUserpicture(int id, String source);
	
	public GridFSDBFile getUserPicture(int id);
	
	/**
	 * register
	 * 
	 */
	
	public boolean duplicate_username(String username);
	
	/**
	 * pay
	 * 
	 */
	
	public void pay(Date date);
	
	public void addtoshoppingcart(double amount);
	
	public void deleteShoppingcart(int bookid);
}
