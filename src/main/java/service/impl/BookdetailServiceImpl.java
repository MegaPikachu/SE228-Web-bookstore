package service.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.mongodb.gridfs.GridFSDBFile;

import dao.BookdetailDao;
import service.AppService;
import service.BookdetailService;

public class BookdetailServiceImpl implements BookdetailService{
	private BookdetailDao bookdetailDao;

	public BookdetailDao getBookdetailDao() {
		return bookdetailDao;
	}

	public void setBookdetailDao(BookdetailDao bookdetailDao) {
		this.bookdetailDao = bookdetailDao;
	}
	
	public GridFSDBFile getBookPicture(int id){ 
		return bookdetailDao.getBookPicture(id);
	}
}
