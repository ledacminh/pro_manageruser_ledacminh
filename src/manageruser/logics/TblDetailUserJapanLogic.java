/**
 * Copyright(C) 2020 Luvina Sorfware
 * TblDetailJapanLogic.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.logics;

import java.io.IOException;
import java.sql.SQLException;

import manageruser.entities.TblDetailUserJapan;

/**
 * Lớp này dùng để: Định nghĩa các hành vi cho đối tượng TblDetailJapanLogicImpl
 * @author :  Lê Minh
 */
public interface TblDetailUserJapanLogic {

	
	
	/**
	 * Phương thức kiểm tra có tồn tại trình độ tiếng nhật tương ứng với userId truyền vào 
	 * @param userId : Là userId của user
	 * @return : boolean
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws IOException : Lôi đọc file
	 */
	public boolean isExistCodeLevel(int userId) throws ClassNotFoundException, SQLException, IOException;
	
	
	/**
	 * Phương thức dùng để update trình độ tiếng nhật của đối tượng user
	 * @param  tblDetailUserJapan : Là 1 đối tượng TblDetailUserJapan
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws SQLException : Lỗi thao tác với DB
	 */
	public void updateTblDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws ClassNotFoundException, SQLException;
	
}
