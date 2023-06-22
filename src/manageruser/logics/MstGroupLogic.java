/**
 * Copyright(C) 2020 Luvina Sorfware
 * abc.java Feb 28, 2020 author: Lê Minh
 */
package manageruser.logics;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import manageruser.entities.MstGroup;

/**
 * Lớp này dùng để: Định nghĩa các hành vi cho đối tượng MstGroupLogicImpl
 * @author : Lê Minh
 */
public interface MstGroupLogic {

	/**
	 * Phương thức này dùng để: Lấy thông tin của bảng mst_group 
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác v
	 * @return: List<MstGroup>
	 * @throws IOException : Lỗi đọc file
	 */
	public List<MstGroup> getAllMstGroup() throws ClassNotFoundException, SQLException, IOException;
	
	
	/**
	 * Phương thức dùng để: Lấy ra tên groupName tương ứng với groupId trong bảng mst_group
	 * @param groupId : Là Id group 
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác với DB
	 * @return : trả về groupName 
	 * @throws IOException : Lỗi đọc file
	 */
	public String getGroupNameById(int groupId) throws ClassNotFoundException, SQLException, IOException;
	
	
	/**
	 * Phương thức kiểm tra groupId có tồn tại trong database không
	 * @param groupId : Id của nhóm
	 * @return kết quả check true nếu tồn tại, false nếu không tồn tại
	 * @throws ClassNotFoundException, SQLException
	 * @throws IOException : Lỗi đọc file
	 */
	public boolean isExitGroupId(int groupId) throws ClassNotFoundException, SQLException, IOException;
	
	
	
	

}
