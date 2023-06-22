/**
 * Copyright(C) 2020 Luvina Sorfware
 * TblDetailUserJapanDao.java Feb 27, 2020 author: L� Minh
 */
package manageruser.dao;

import java.io.IOException;
import java.sql.SQLException;

import manageruser.entities.TblDetailUserJapan;



/**
 * Phương thức dùng để: Định nghĩa các hành vi cho đối tuộng TblDetailUserJapanDaoImpl
 * 
 * @author :  Lê Minh
 */
public interface TblDetailUserJapanDao extends BaseDao{

	

	/**
	 * Phương thức insert 1 đối tượng TblDetailUserJapan vào tbl tbl_detail_user_japan
	 * @param tblDetailUserJapan : Là 1 đối tượng TblDetailUserJapan
	 * @return : int
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 */
	public void insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException ,ClassNotFoundException;
	
	
	/**
	 * Phương thức lấy ra đối tượng TblDetailUserJapan tương ứng với userId truyền vào 
	 * @param userId : Là userId của user
	 * @return : Đối tương TblDetailUserJapan
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws IOException : Lỗi đọc file
	 */
	public TblDetailUserJapan getTblDetailByUserId(int userId) throws ClassNotFoundException, SQLException, IOException;
	
	
	/**
	 * Phương thức dùng để xóa trình độ tiếng nhật của đối tượng user
	 * @param userId : Là id của user
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws SQLException : Lỗi thao tác với DB
	 */
	public void deleteTblDetailUserJapan(int userId) throws ClassNotFoundException, SQLException;
	
	/**
	 * Phương thức dùng để update trình độ tiếng nhật của đối tượng user
	 * @param  tblDetailUserJapan : Là 1 đối tượng TblDetailUserJapan
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws SQLException : Lỗi thao tác với DB
	 */
	public void updateTblDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws ClassNotFoundException, SQLException;
	
	
	
	
	
}



