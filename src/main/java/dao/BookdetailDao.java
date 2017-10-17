package dao;

import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;

import model.Bookdetail;

public interface BookdetailDao {

	public Bookdetail findById(int id);

	public List<Bookdetail> findAll(Class<Bookdetail> entityClass);

	public void remove(Bookdetail bookdetail);

	public void add(Bookdetail bookdetail);

	public void saveOrUpdate(Bookdetail bookdetail);
	
	public void savePicture(int id, String source);
	
	public void updatePicture(int id, String source);
	
	public GridFSDBFile getBookPicture(int id);
	
	public void deletePicture(int id);
	
}
