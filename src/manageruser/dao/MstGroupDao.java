/**
 * Copyright(C) 2020 Luvina Sorfware
 * MstGroupDao.java Feb 27, 2020 author: L� Minh
 */
package manageruser.dao;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import manageruser.entities.MstGroup;

/**
 * Lớp này dùng để: Định nghĩa các hành vi cho đối tượng MstGroupDao
 * @author : Lê Minh
 */
public interface MstGroupDao {

	
     /**
      * Phương thức dùng để: Lấy thông tinGroup của user trong bảng mst_group
      * @throws ClassNotFoundException : Lỗi không tìm thấy class
      * @throws SQLException : Lỗi thao tác với DB
      * @throws NoSuchAlgorithmException : Lỗi thuật toán
      * @return : Trả về danh sách các đối tượng MstGroup
     * @throws IOException : Lỗi đọc file
      */
	public List<MstGroup> getAllMstGroup() throws ClassNotFoundException, SQLException, IOException;
	
	
	
	//Kết thúc class
}