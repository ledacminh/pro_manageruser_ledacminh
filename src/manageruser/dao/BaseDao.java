/**
 * Copyright(C) 2020 Luvina Sorfware
 * BaseDao.java Feb 28, 2020 author: Lê Minh
 */
package manageruser.dao;
import java.io.IOException;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

/**
 * Lớp này dùng để: Định nghĩa các hành vi cho đối tượng BaseDaoImpl
 * 
 * @author : Lê Minh
 */
public interface BaseDao {


	/**
	 * method để mở kết nối với DB
	 * @return : connection sau khi đã kết nối được 
	 * @throws SQLException : lỗi sql exception
	 * @throws ClassNotFoundException : lỗi ko kết nối được driver
	 * @throws IOException 
	 */
	public Connection onpenConnection()throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * method đóng kết nối với DB
	 * @throws SQLException : lỗi sql exception
	 * @throws ClassNotFoundException : lỗi ko kết nối được driver
	 */
	public void closeConnection() throws SQLException, ClassNotFoundException;
	
	/**
	 * Hành vi cài đặt tự động cam kết khi thực thi truy vấn
	 * @param autoCommit true nếu tự động commit, false nếu không tự động commit
	 * @throws SQLException
	 */
	public void setCommit(boolean autoCommit) throws SQLException;
	
	/**
	 * Hành vi get Connection conn
	 * @return giá trị conn
	 */
	public Connection getConnection();
	
	/**
	 * Thiết lập giá trị cho conn
	 * @param conn: giá trị conn
	 */
	public void setConnection(Connection conn);
	
	/**
	 * Phương thức dùng để rollback data
	 * @throws SQLException : Lỗi thao tác với DB
	 */
	public void rollBack() throws SQLException;
	
	/**
	 * ham commit
	 */
	void commit() throws SQLException;
	
	
	
	// Kết thúc class
}
