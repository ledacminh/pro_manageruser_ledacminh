/**
 * Copyright(C) 2020 Luvina Sorfware
 * MstJapanDao.java Feb 27, 2020 author: L� Minh
 */
package manageruser.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import manageruser.entities.MstJapan;

/**
 * Class là một Interface xử lí thao tác với DB bảng mst_group
 * @author :  Lê Minh
 */
public interface MstJapanDao {

	/**
     * Phương thức dùng để: Lấy thông tin trình độ tiếng nhật  của user trong bảng mst_japan
     * @throws ClassNotFoundException : Lỗi không tìm thấy class
     * @throws SQLException : Lỗi thao tác với DB
     * @return : Trả về danh sách các đối tượng MstJapan
	 * @throws IOException : Lỗi đọc file
     */
	public List<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException, IOException;
	
	
	
	
	//Kết thúc class
}
