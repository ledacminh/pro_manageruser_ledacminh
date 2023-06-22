/**
 * Copyright(C) 2020 Luvina Sorfware
 * BaseDao.java Feb 27, 2020 author: L� Minh
 */
package manageruser.dao.impl;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import manageruser.dao.BaseDao;
import manageruser.utils.DatabaseProperties;

/**
 * Lớp này dùng để: Đóng và mở kết nối với CSDL
 * @author : Lê Minh
 */
public class BaseDaoImpl implements BaseDao {
	
	//Khai báo đối tượng resultSet
	protected ResultSet resultSet = null;
	//Khai báo đối tượng prepareStatment
	protected PreparedStatement prepareStatment = null;
	// Khởi tạo đối tượng cons, đối tượng conn dùng để mở kết nối với CSDL
	protected Connection conn = null;
	protected static final List<String>  listColumnSort = new  ArrayList<String>();

	/**
	 * Phương thức này dùng để: Mở kết nối với CSDL
	 * @throws SQLException: Lối SQL
	 * @throws ClassNotFoundException: Lỗi không tìm thấy CSDL
	 * @return : Trả về 1 kết nối với CSDL
	 * @throws IOException : Lỗi đọc file
	 */
	@Override
	public Connection onpenConnection() throws SQLException, ClassNotFoundException, IOException {
		// Bắt đầu khối try
		try {
			// Địa chỉ kết nối
			String HOST_NAME = DatabaseProperties.getString("HOST_NAME");
			// Tên CSDL trong MýQL
			String DB_NAME = DatabaseProperties.getString("DB_NAME");
			// Tên đăng nhập trong MySQL
			String USER_NAME = DatabaseProperties.getString("USER_NAME");
			// Mật khẩu trong SQL
			String PASSWORD = DatabaseProperties.getString("PASSWORD");
			// Tải lớp drive
			Class.forName(DatabaseProperties.getString("CLASS_FORNAME"));
			// Lấy URL
			String url = DatabaseProperties.getString("URL");
			// Lây địa chỉ cổng kết nối
			String host = DatabaseProperties.getString("HOST");
			// Khởi tạo 1 đối tượng String dùng để lưu đường dẫn tử Esclip đến
			// MySQL
			String connectionURL = url + HOST_NAME + host + DB_NAME;
			// Lấy kết nối
			conn = (Connection) DriverManager.getConnection(connectionURL, USER_NAME, PASSWORD);
			// Kết thúc khối try
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// Lỗi không tìm thấy DB hoặc lỗi SQL Exception
			System.out.println("Lỗi: Class: BaseDaoImpl.java_Phương thức: openConnection " + e.getMessage());
			// Ném lỗi
			throw e;
			// Kết thúc khối catch
		}
		// Trả về 1 connection
		return conn;
		// kết thúc phương thức
	}
	
	/**
	 * Phương thức dùng để đóng kết nối với CSDL
	 * @return: Không trả về
	 */
	@Override
	public void closeConnection() {
		// Bắt đầu khối try
		try {
			// Nếu không có kết nối hoặc đã đóng kết nối với CSDL thì đóng kết
			// nối
			if (conn != null && !conn.isClosed()) {
				// Đóng kết nối
				conn.close();
			}
			// Kết thúc khối try
		} catch (SQLException e) {
			// Ghi log lỗi
			System.out.println("[BaseDaoImpl].[closeConnection] " + e.getMessage());
			// kết thúc try- catch
		}
		// Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.dao.BaseDao#setCommit(boolean)
	 */
	@Override
	public void setCommit(boolean autoCommit) throws SQLException {
		//Set giá trị cho conn
		conn.setAutoCommit(autoCommit);
		//Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.dao.BaseDao#getConnection()
	 */
	@Override
	public Connection getConnection() {
		//trả về conn
		return conn;
		
		//Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.dao.BaseDao#setConnection(com.mysql.jdbc.Connection)
	 */
	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
		
		//Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.dao.BaseDao#rollBack()
	 */
	@Override
	public void rollBack() throws SQLException {
		//Bắt đầu khối try
		try {
			//Nếu kêt nối bằng null
			if (conn == null) {
				//rollback
				conn.rollback();
			}
			//Kết thúc khối try
		} catch (SQLException e) {
			System.out.println("[BaseDBImpl].[rollBack] " + e.getMessage());
		}
		//Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.dao.BaseDao#commit()
	 */
	@Override
	public void commit() throws SQLException {
		// Bắt đầu khối try
		try {
			// Kiểm tra nếu kết nối khác null
			if (conn != null) {
				// thực hiện commit
				conn.commit();
			}
			// Kết thúc khối try
		} catch (SQLException e) {
			// ghi log lỗi
			System.out.println("[BaseDBImpl].[Commit] " + e.getMessage());
			// Kết thúc khối catch
		}
		// Kết thúc phương thức
	}

//Kết thúc class
}