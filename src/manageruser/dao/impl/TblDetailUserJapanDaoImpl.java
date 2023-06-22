/**
 * Copyright(C) 2020 Luvina Sorfware
 * TblDetailUserJapanDaoImpl.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.dao.impl;

import java.io.IOException;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import manageruser.dao.TblDetailUserJapanDao;
import manageruser.entities.TblDetailUserJapan;
import manageruser.utils.Common;
import manageruser.utils.Constant;

/**
 * Lớp này dùng để: Lấy thông tin của User trong bảng TblDetailUserJapan
 * @author : Lê Minh
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {

	/* (non-Javadoc)
	 * @see manageruser.dao.TblDetailUserJapanDao#insertDetailUserJapan(manageruser.entities.TblDetailUserJapan)
	 */
	@Override
	public void insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan)
			throws SQLException {
		// Khởi tạo biến row để kiểm tra đã insert thành công hay chưa
		try {
			// Kiểm tra nếu conn khác null
			if (conn != null) {
				// Khởi tạo chuỗi truy vấn
				String sql = "INSERT INTO tbl_detail_user_japan(user_id, code_level, start_date, end_date, total)\r\n"
						+ "VALUES(?, ?, ?, ?, ?);";
				// thực thi truy vấn
				prepareStatment = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				// Khởi tạo biến chạy i = 1;
				int i = 1;
				// Set giá trị cho user_id
				prepareStatment.setInt(i++, tblDetailUserJapan.getUserId());
				// Set giá trị cho code_level
				prepareStatment.setString(i++, tblDetailUserJapan.getCodeLevel());
				// Set giá trị cho startDate
				prepareStatment.setDate(i++, Common.convertStringToDate(tblDetailUserJapan.getStartDate()));
				// Set giá trị cho endDate
				prepareStatment.setDate(i++, Common.convertStringToDate(tblDetailUserJapan.getEndDate()));
				// Set giá trị cho total
				prepareStatment.setString(i++, tblDetailUserJapan.getTotal());
				prepareStatment.execute();
				// Kết thúc if kiểm tra kết nối
			}
			// Kết thúc khối try
		} catch (SQLException e) {
			// Ghi log lỗi
			System.out.println("[TblDetailUserJapanDaoImpl].[insertDetailUserJapan]: " + e.getMessage());
			//ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Trả về kết quả
	}

	
	
	
	/* (non-Javadoc)
	 * @see manageruser.dao.TblDetailUserJapanDao#isExistCodeLevel(int)
	 */
	@Override
	public TblDetailUserJapan getTblDetailByUserId(int userId) throws ClassNotFoundException, SQLException, IOException {
		TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
		// Bắt đầu khối try
		try {
			// Mở kết nối với DB
			onpenConnection();
			// Kiểm tra nếu kết nối khác null
			if (conn != null) {
				// Khởi tạo đối tượng StringBuilder
				StringBuilder sqlQuery = new StringBuilder();
				// Truy vấn
				sqlQuery.append("SELECT j.code_level FROM tbl_user as u ");
				sqlQuery.append("INNER JOIN tbl_detail_user_japan as j ");
				sqlQuery.append("ON u.user_id = j.user_id ");
				sqlQuery.append("WHERE u.rule = ? AND u.user_id = ?");
				// Thực thi câu truy vấn
				prepareStatment = conn.prepareStatement(sqlQuery.toString());
				//Khởi tạo biến chạy i =1 
				int i = 1; 
				// Set giá trị cho tham số user_id
				prepareStatment.setInt(i++, Constant.RULE_USER);
				prepareStatment.setInt(i++, userId);
				// Lấy ra kết quả
				resultSet = prepareStatment.executeQuery();
				// Bắt đầu vòng lặp while
				while (resultSet.next()) {
                      tblDetailUserJapan.setCodeLevel(resultSet.getString("code_level"));
				}
			} // Kết thúc khối try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("[TblDtailUserJapanDaoImpl].[getTblDetailByUserId] " + e.getMessage());
			//Ném lỗi
			throw e;

			// Kết thúc khối catch
		}
		// Trả về kết quả kiểm tra
		return tblDetailUserJapan;
		// Kết thúc phương thức
	}
	
	/* (non-Javadoc)
	 * @see manageruser.dao.TblDetailUserJapanDao#deleteTblDetailUserJapan(int)
	 */
	@Override
	public void deleteTblDetailUserJapan(int userId) throws  SQLException {
		// Bắt đầu khối try
		try {
			// Kiểm tra nếu kết nối khac null
			if (conn != null) {
				// Try vấn delete
				String sqlQuery = "DELETE FROM tbl_detail_user_japan WHERE user_id = ?";
				prepareStatment = conn.prepareStatement(sqlQuery);
				// Set giá trị cho tham số user_id
				prepareStatment.setInt(1, userId);
				// thực thi câu truy vấn
				prepareStatment.execute();
				// Kết thúc if kiểm tra kết nối
			}
			// Kết thúc khói if
		} catch (SQLException e) {
			// Ghi log lỗi
			System.out.println("TblDetailUserJapanDaoImpl.[deleteTblDetailUserJapan] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Kêt thúc phương thức
	}
	
	/* (non-Javadoc)
	 * @see manageruser.dao.TblDetailUserJapanDao#updateTblDetailUserJapan(manageruser.entities.TblDetailUserJapan)
	 */
	@Override
	public void updateTblDetailUserJapan(TblDetailUserJapan tblDetailUserJapan)
			throws SQLException {

		// Bắt đầu khối try
		try {
			// Kiểm tra nếu kết nối khác null
			if (conn != null) {
				// Khởi tạo đối tượng StringBuilder
				StringBuilder sqlQuery = new StringBuilder();
				// Câu try vấn
				sqlQuery.append("UPDATE tbl_detail_user_japan ");
				sqlQuery.append("SET code_level = ? , start_date = ? , end_date = ? , total = ? ");
				sqlQuery.append("WHERE user_id = ?");
				// thực thi câu truy vấn
				prepareStatment = conn.prepareStatement(sqlQuery.toString());
				// Khởi tạo biến chạy = 1
				int i = 1;
				// Set giá trị cho tham số code_level
				prepareStatment.setString(i++, tblDetailUserJapan.getCodeLevel());
				// Set giá trị cho thuộc tính start_date
				System.out.println(Common.convertStringToDate(tblDetailUserJapan.getStartDate()));
				prepareStatment.setDate(i++, Common.convertStringToDate(tblDetailUserJapan.getStartDate()));
				// Set giá trị cho tham số end_date
				prepareStatment.setDate(i++, Common.convertStringToDate(tblDetailUserJapan.getEndDate()));
				// Set giá trị cho tham số total
				prepareStatment.setString(i++, tblDetailUserJapan.getTotal());
				// Set giá trị cho tham số userId
				prepareStatment.setInt(i++, tblDetailUserJapan.getUserId());
				prepareStatment.execute();
				// Kết thúc if kiểm tra kết nối
			}
			// Kết thúc khối try
		} catch (SQLException e) {
			// Ghi log lỗi
			System.out.println("[TblDetailUserJapanDaoImpl].[updateTblDetailUserJapan] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thú khối catch
		}

		// Kết thúc phương thức
	}

	// Kết thúc class
}
