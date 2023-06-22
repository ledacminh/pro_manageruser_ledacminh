/**
 * Copyright(C) 2020 Luvina Sorfware
 * MstJapanLogic.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.logics;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import manageruser.entities.MstJapan;

/**
 * Lớp này là một Interface dùng để xử lí logic cho các chức năng liên quan đến mst_japan
 * @author :  Lê Minh
 */
public interface MstJapanLogic {

	/**
	 * Phương thức này dùng để: Lấy thông tin của bảng mst_japan 
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác v
	 * @return: List<MstJapan>
	 * @throws IOException : Lỗi đọc file
	 */
	public List<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException, IOException;
	
	
	/**
	 * Phương thức dùng để : Lấy ra Tên trình độ tiếng nhật khi có code_level tương ứng
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác với DB
	 * @param codeLevel : trình độ tiếng nhật
	 * @return : Trả về tên trình độ tiếng nhật tương ứng
	 * @throws IOException : Lỗi đọc file
	 */
	public String  getNameLevelByCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException, IOException;
	
	
	/**
	 * Phương thức dùng để kiểm tra tồn tại trình độ tiếng nhật
	 * @param codeLevel : Là trình độ tiếng nhật
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException  : Lỗi thao tác với DB
	 * @return : boolean
	 * @throws IOException : Lỗi đọc file
	 */
	public boolean  isExitCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException, IOException;
	
	
	
	
}
