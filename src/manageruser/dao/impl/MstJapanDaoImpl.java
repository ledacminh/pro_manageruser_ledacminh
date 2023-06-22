/**
 * Copyright(C) 2020 Luvina Sorfware
 * MstJapanDaoImpl.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.dao.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageruser.dao.MstJapanDao;
import manageruser.entities.MstJapan;
import manageruser.utils.Common;

/**
 * Lớp này Implement MstJapanDao để xử lí thao tác với DB bảng mst_japan
 * @author : Lê Minh
 */
public class MstJapanDaoImpl extends BaseDaoImpl implements MstJapanDao {

	/* (non-Javadoc)
	 * @see manageruser.dao.MstJapanDao#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() throws  IOException , ClassNotFoundException, SQLException {
		// Khai báo chuỗi listMstJapan dùng để lưu thông tin lấy từ bảng
		// mst_japan
		List<MstJapan> listMstJapan = new ArrayList<>();
		// Khai báo đối tượng StringBuilder để lưu câu truy vấn
		StringBuilder sqlQuery = new StringBuilder();
		// Bắt đầy khối try
		try {
			// Mở kết nối với DB
			onpenConnection();
			// Kiểm tra kết nối khác null
			if (conn != null) {
				// Select group_id, group name từ bẩng mst_japan
				sqlQuery.append("SELECT code_level, name_level ");
				// từ bảng mst_japan
				sqlQuery.append("FROM mst_japan ");
				// Kiểm tra title grou_name có là title của bảng mst_japan hay
				// không
				if (Common.isExitColumnName("code_level")) {
					// Thêm lệnh truy vấn order by group_name
					sqlQuery.append("ORDER BY code_level ");
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
					// Khởi tạo đối tượng mstJapan
					MstJapan mstJapan = new MstJapan();
					// Set giá trị lấy từ resultSet và thuộc tính code_level của
					// đối tượng mstJapan
					mstJapan.setCodeLevel(resultSet.getString(i++));
					// Set giá trị lấy từ resultSet và thuộc tính name_level của
					// đối tượng mstJapan
					mstJapan.setNameLevel(resultSet.getString(i++));
					// thêm đối thwomgj mstJapan vào list
					listMstJapan.add(mstJapan);
					// Kết thúc while
				}
				// Kết thúc if kiểm tra nếu khác null
			}
			// kết thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("Lỗi: [MstJapanDaoImpl].[getAllMstGroup]" + e.getMessage());
			//Ném lỗi
			throw e;
			// Kết thúc catch
		} finally {
			// đóng kết nôi
			closeConnection();
			// kết thuc finally
		}
		// Trả về kết quả
		return listMstJapan;
		// Kết thức phương thức
	}
	// Kết thúc class
}




