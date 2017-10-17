package service;

import java.io.OutputStream;

import com.mongodb.gridfs.GridFSDBFile;

public interface BookdetailService {
	public GridFSDBFile getBookPicture(int id);
}
