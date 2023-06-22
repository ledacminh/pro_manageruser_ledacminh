/**
 * Copyright(C) 2020 Luvina Sorfware
 * MstGroupDaoImpl.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.dao.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageruser.dao.MstGroupDao;
import manageruser.entities.MstGroup;
import manageruser.utils.Common;

/**
 * Lớp này dùng để: Lấy thông tin của user từ bảng MstGroup
 * @author : Lê Minh
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {

    /**
     * Phương thức dùng để: Lấy thông tinGroup của user trong bảng mst_group
     * @throws ClassNotFoundException : Lỗi không tìm thấy class
     * @throws SQLException : Lỗi thao tác với DB
     * @return : Trả về danh sách các đối tượng MstGroup
     * @throws IOException : Lỗi đọc file
     */
	@Override
	public List<MstGroup> getAllMstGroup() throws ClassNotFoundException, SQLException,IOException {
		// Khai báo chuỗi listMstGroup dùng để lưu thông tin lấy từ bảng
		// mst_group
		List<MstGroup> listMstGroup = new ArrayList<>();
		// Khai báo đối tượng StringBuilder để lưu câu truy vấn
		StringBuilder sqlQuery = new StringBuilder();
		// Bắt đầy khối try
		try {
			// Mở kết nối với DB
			onpenConnection();
			// Kiểm tra kết nối khác null
			if (conn != null) {
				// Select group_id, group name từ bẩng mst_group
				sqlQuery.append("SELECT group_id, group_name ");
				// từ bảng mst_group
				sqlQuery.append("FROM mst_group ");
				// Kiểm tra title grou_name có là title của bảng mst_group hay
				// không
				if (Common.isExitColumnName("group_name")) {
					// Thêm lệnh truy vấn order by group_name
					sqlQuery.append("ORDER BY group_name ");
					// Kết thúc if
				}
				// Khởi tạo đối tượng PreparedStatement
				PreparedStatement prepareStatment = conn.prepareStatement(sqlQuery.toString());
				// Lưu kết quả trả về của hành động truy vấn
				resultSet = prepareStatment.executeQuery();
				// Bắt đầu while
				while (resultSet.next()) {
					// Khởi tạo biến đếm i
					int i = 1;
					// Khởi tạo đối tượng mstGroup
					MstGroup mstGroup = new MstGroup();
					// Set giá trị lấy từ resultSet và thuộc tính groupId
					mstGroup.setGroupId(resultSet.getInt(i++));
					// Set giá trị lấy từ resultSet và thuộc tính groupName
					mstGroup.setGroupName(resultSet.getString(i++));
					// thêm đối tương mstGroup vào listMstGroup
					listMstGroup.add(mstGroup);
					// Kết thúc while
				}
				// Kết thúc if kiểm tra nếu khác null
			}
			// kết thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("Lỗi:_Class MstGroupDaoImpl_Phương thức getAllMstGroup");
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		} finally {
			// đóng kết nôi
			closeConnection();
			// kết thuc finally
		}
		// Trả về kết quả
		return listMstGroup;
		// Kết thức phương thức
	}
	// Kết thúc class
}
