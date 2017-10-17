package action;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.struts2.ServletActionContext;

import com.mongodb.gridfs.GridFSDBFile;

import dao.BookdetailDao;
import service.BookdetailService;

public class GetBookpictureAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private int id;
	private BookdetailService bookdetailService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BookdetailService getBookdetailService() {
		return bookdetailService;
	}

	public void setBookdetailService(BookdetailService bookdetailService) {
		this.bookdetailService = bookdetailService;
	}
	

	
	
	@Override
	public String execute() throws Exception { 
		HttpServletResponse response= null;
        response = ServletActionContext.getResponse();

        GridFSDBFile gridFSDBFile = bookdetailService.getBookPicture(id);
        
		OutputStream sos = response.getOutputStream();    
        response.setContentType("application/octet-stream");
        String name = (String) gridFSDBFile.get("filename");  
        String fileName = new String(name.getBytes("GBK"), "ISO8859-1");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); 
        gridFSDBFile.writeTo(sos);  
        sos.flush();  
        sos.close();
		/*HttpServletResponse response= null;
        response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream() ;
		os = bookdetailService.getBookPicture(id);
		os.flush();
		os.close();
		System.out.println(os);
		
		HttpServletResponse response= null;
		ServletOutputStream out = null;
		try {
            response = ServletActionContext.getResponse();
            response.setContentType("multipart/form-data");
          //  out = response.getOutputStream();
           // out.write(bookdetailService.getBookPicture(id));
            out = bookdetailService.getBookPicture(id);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
        */
		return null;
    }




}
