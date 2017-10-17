package dao;

import com.mongodb.gridfs.GridFSDBFile;

public interface UserPicDao {
	
	public void savePicture(int id, String source);
	
	public void updatePicture(int id, String source);
	
	public GridFSDBFile getUserPicture(int id);
	
	public void deletePicture(int id);
	
}
