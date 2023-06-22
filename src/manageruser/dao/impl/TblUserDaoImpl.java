/**
 * Copyright(C) 2020 Luvina Sorfware
 * TblUserDaoImpl.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import manageruser.dao.TblUserDao;
import manageruser.entities.TblUser;
import manageruser.entities.UserInfor;
import manageruser.utils.Common;
import manageruser.utils.Constant;

/**
 * Lóp này dùng để: Lấy thông tin user từ DB 
 * @author : Lê Minh
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {
	// Khởi tạo 1 đối tuộng tblUser
	TblUser tblUser = new TblUser();
	
	/**
	 * Phương thức dùng để: Lấy thông tin người dùng từ loginName
	 * @param loginName:  login được truyền vào khi đăng nhập
	 * @return: Trả về thông tin user được lấy ra.
	 * @throws ClassNotFoundException:  Không tìm thấy drive forname
	 * @throws SQLException:   Lỗi câu lệnh spl
	 * @throws IOException : Lỗi đọc file
	 */
	@Override
	public TblUser getTblUserByUserName(String loginName) throws ClassNotFoundException, SQLException, IOException {
		StringBuilder sqlQuery = new StringBuilder();
		// Bắt đầu khối try
		try {
			// Chuỗi truy vấn SQL
			// Select login_name, password, salt
			sqlQuery.append("SELECT `login_name`, `password` , `Salt`");
			// từ bảng tbl_user
			sqlQuery.append("FROM `tbl_user`");
			// set điều kiện
			sqlQuery.append("WHERE `login_name` = ? AND `Rule` = ?");
			// Mở kết nối tới database
			onpenConnection();
			// Nếu kết nối thành công
			if (conn != null) {
				// Khởi tạo đối tượng PreparedStatement
				prepareStatment = conn.prepareStatement(sqlQuery.toString());
				int i = 1;
				// Set giá trị cho tham số loginName
				prepareStatment.setString(i++, loginName);
				// Set giá trị cho tham số rule
				prepareStatment.setInt(i++, Constant.RULE_USER);
				// Lưu kết quả trả về của hành động truy vấn
				resultSet = prepareStatment.executeQuery();
				// Bắt đầu vòng lặp while
				while (resultSet.next()) {
					// Gán giá trị cho thuộc tính loginName của tblUser
					tblUser.setLoginName(resultSet.getString("login_name"));
					// Gán giá trị cho thuộc tính password của tblUser
					tblUser.setPassword(resultSet.getString("password"));
					// Gán giá trị cho thuộc tính Salt của tblUser
					tblUser.setSalt(resultSet.getString("Salt"));
					// Kết thúc while
				}
				// Kết thúc if
			}
			// Kết thúc try
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log lỗi
			System.out.println("[TblUserDaoImpl].[getTblUserByUserName] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		} finally {
			// đóng kết nôi
			closeConnection();
			// kết thúc fianlly
		}
		// Trả về đối tượng TblUser
		return tblUser;
		// Kết thức phương thức
	}
	
	/**
	 * Phương thức dùng để lấy tổng số records trong DB
	 * @param groupId: Mã nhóm
	 * @param fullName:Tên đầy đủ của user
	 * @return: Trả về tổng số records
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws IOException : Lỗi đọc file
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException, IOException {
		// Khởi tạo total để lưu tổng số records
		int total = 0;
		// Khởi tạo chuỗi sqlQuery để lưu câu truy vấn
		StringBuilder sqlQuery = new StringBuilder();
		// Bắt đầu khối try
		try {
			// Chuỗi truy vấn SQL
			sqlQuery.append("SELECT count(*) AS total ");
			sqlQuery.append("FROM tbl_user AS u INNER JOIN mst_group AS g ");
			sqlQuery.append("ON u.group_id = g.group_id ");
			sqlQuery.append("WHERE u.rule = ?");
			// Mở kết nối với DB
			onpenConnection();
			// Nếu kết nối thành công
			if (conn != null) {
				// Biến chạy i
				int i = 1;
				// trường hợp mã group là mã của user thì nối thêm chuỗi vào câu
				// truy vấn
				if (groupId > 0) {
					sqlQuery.append(" AND g.group_id = ? ");
				}
				// trường hợp tên khác rỗng thì nối thêm chuỗi vào câu truy vấn
				if (fullName.length() > 0) {
					sqlQuery.append(" AND u.full_name  LIKE ? ");
				}
				// Khởi tạo đối tượng PreparedStatement
				prepareStatment = conn.prepareStatement(sqlQuery.toString());
				// Truyền giá trị cho tham số u.rule
				prepareStatment.setInt(i++, Constant.RULE_USER);
				// Kiểm tra nếu groupId > 0
				if (groupId > 0) {
					// Truyền giá trị cho tham số g.group_id
					prepareStatment.setInt(i++, groupId);
				}
				// Kiểm tra nếu fullName khác rỗng
				if (fullName.length() > 0) {
					
					// Set giá trị cho tham số fullName
					prepareStatment.setString(i++, "%" + fullName + "%");
				}
				// Thực thi câu truy vấn
				resultSet = prepareStatment.executeQuery();
				// Bắt đầu vòng lặp while
				while (resultSet.next()) {
					// Lấy tổng số records
					total = resultSet.getInt("total");
					// kết thúc while
				}
				// Kết thúc if
			}
			// Kết thúc khối try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("[TblUserDaoImpl].[getTotalUser] " + e.getMessage());
			//Ném lỗi
			throw e;
			// Kết thúc khối catch
		} finally {
			// đóng kết nôi
			closeConnection();
			// Kết thúc khối finally
		}
		// Trả về tổng số records
		return total;
		// Kết thúc phương thức
	}

	
	/**
	 * Phương thức dùng để:Lấy danh sách user
	 * @param offset: vị trí data cần lấy nào
	 * @param limit: số lượng lấy limit
	 * @param groupId: Mã nhóm tìm kiếm
	 * @param fullName: Tên tìm kiếm
	 * @param sortType: Nhận biết cột nào được ưu tiên sắp xếp
	 * @param sortByFullName: Giá trị sắp xếp của cột Tên ( ASC or DESC)
	 * @param sortByCodeLevel: Giá trị sắp xếp của cột trình độ tiếng nhật ( ASC or DESC)
	 * @param sortByEndDate: Giá trị sắp xếp của cột ngày hết hạn ( ASC or DESC)
	 * @return: Danh sách UserInfor
	 * @throws ClassNotFoundException  : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác với DB
	 *@return: Danh sách UserInfor         
	 * @throws IOException : Lỗi đọc file
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException, IOException {
		// Khai báo danh dách listUserInfor
		List<UserInfor> listUserInfor = new ArrayList<>();
		//Khởi tạo đối tương StringBuilder
		StringBuilder sqlQuery = new StringBuilder();
		//Bắt đầu khối try
		try {
			// Mở kết nối với DB
			onpenConnection();
			// Kiểm tra kết nối với DB
			if (conn != null) {
				// select các trường trong DB user_id full_name, birthday,
				// group_name, email, tel, name_level, end_date, total
				sqlQuery.append(
						" SELECT u.user_id, u.login_name,u.full_name_kana, u.full_name, u.birthday, g.group_name, g.group_id, u.email, u.tel, j.name_level, d.end_date, d.start_date,d.code_level, d.total  ");
				// Từ 2 bảng tbl_user và mst_group
				sqlQuery.append("FROM tbl_user AS u INNER JOIN mst_group AS g ");
				// Điều kiện inner join
				sqlQuery.append("ON u.group_id = g.group_id ");
				// Tiếp tục left join với 2 bảng tbl_detail_user_japan INNER
				// JOIN mst_japan
				sqlQuery.append("LEFT JOIN (tbl_detail_user_japan AS d INNER JOIN mst_japan AS j ");
				// Điều kiện inner join 2 bảng tbl_detail_user_japan, mst_japan
				sqlQuery.append("ON d.code_level = j.code_level) ");
				// Điều kiên inner left join
				sqlQuery.append("ON u.user_id = d.user_id ");
				// kiểm tra rule = ?
				sqlQuery.append("Where u.rule = ? ");
				// trường hợp mã group là mã của user thì nối thêm chuỗi vào câu truy vấn
				if (groupId > 0) {
					sqlQuery.append(" AND g.group_id = ? ");
					// kết thúc khối if
				}
				//Kiểm tra nếu fullName khác rỗng
				if (fullName.length() > 0) {
					sqlQuery.append(" AND u.full_name  LIKE ? ");
					// kết thúc khối if
				}
				// Trường hợp sắp xếp ưu tiên theo trường  full_name
				if (Common.isExitColumnName("full_name") && "full_name".equals(sortType)) {
					// ưu tiên order by theo full_name
					sqlQuery.append("ORDER BY u.full_name ");
					// sắp xếp theo giá trị truyền vào của sortByFullName
					sqlQuery.append(sortByFullName);
					// Nếu tồn tại title của code_level trong DB thì cho phép
					// thêm lệnh truy vấn vào câu sqlQuery
					if (Common.isExitColumnName("code_level")) {
						// thêm truy vấn order by theo code_level
						sqlQuery.append(",j.code_level ");
						// thêm truy vấn ordey by theo giá trị truyền vào của
						// sortByCodeLevel
						sqlQuery.append(sortByCodeLevel);
						// kết thúc if
					}
					// Nếu tồn tại title của end_date trong DB thì cho phép thêm
					// lệnh truy vấn vào câu sqlQuery
					if (Common.isExitColumnName("end_date")) {
						// thêm truy vấn order by theo end_date
						sqlQuery.append(",d.end_date ");
						// thêm truy vấn ordey by theo giá trị truyền vào của
						// ortByEndDate
						sqlQuery.append(sortByEndDate);
						// Kết thúc lệnh if
					}
					// Phòng chống lỗi SQL injection
					// Trường hợp sắp xếp ưu tiên theo trường end_date
				} else if (Common.isExitColumnName("end_date") && "end_date".equals(sortType)) {
					// ưu tiên order by theo end_date
					sqlQuery.append("ORDER BY end_date ");
					// thêm lệnh sắp xếp theo giá trị truyền vào của
					// sorByEndDate
					sqlQuery.append(sortByEndDate);
					// Nếu tồn tại title của full_name trong DB thì cho phép
					// thêm lệnh truy vấn vào câu sqlQuery
					if (Common.isExitColumnName("full_name")) {
						// thêm lệnh Sắp xếp theo truyền full_name
						sqlQuery.append(", u.full_name ");
						// thêm lệnh truy vấn sắp xếp theo giá trị truyền vào của sortByFullName
						sqlQuery.append(sortByFullName);
						// Kết thúc lệnh if
					}
					// Nếu tồn tại title của code_level trong DB thì cho phép
					// thêm lệnh truy vấn vào câu sqlQuery
					if (Common.isExitColumnName("code_level")) {
						// thêm lệnh truy vấn sắp xếp theo trường code_level
						sqlQuery.append(", j.code_level ");
						// thêm lệnh truy vấn săp xếp theo giá trị truyền vào
						// của sortByCodeLevel
						sqlQuery.append(sortByCodeLevel);
						// Kết thúc lệnh if
					}
				} else if (Common.isExitColumnName("code_level") && "code_level".equals(sortType)) {
					// Thêm truy vấn order by theo trường code_level
					sqlQuery.append("ORDER BY j.code_level ");
					// thêm truy vấn order by theo giá trị truyền vào cảu
					// sortByCodeLevel
					sqlQuery.append(sortByCodeLevel);
					// Nếu tồn tại title của code_level trong DB thì cho phép
					// thêm lệnh truy vấn vào câu sqlQuery
					if (Common.isExitColumnName("full_name")) {
						// Thêm lệnh truy vấn sắp xếp theo trường full_name
						sqlQuery.append(", u.full_name ");
						// thêm lệnh truy vấn sắp xếp thoe giá trị truyền vào
						// của sorByFullName
						sqlQuery.append(sortByFullName);
						// Kết thúc lệnh if
					}
					// Nếu tồn tại title của end_date trong DB thì cho phép
					// thêm lệnh truy vấn vào câu sqlQuery
					if (Common.isExitColumnName("end_date")) {
						// thêm lệnh truy vân sắp xếp theo trường end_date
						sqlQuery.append(",d.end_date ");
						// Thêm lệnh truy vấn sắp xếp theo giá trị truyền vào
						// cảu sorByEnDate
						sqlQuery.append(sortByEndDate);
						// Kết thúc lệnh if
					}
					// Kết thúc lện if
				}
				// thêm lệnh truy vấn lấy tối đa số bản ghi bắt đầu từ một vị trị trong DB
				sqlQuery.append(" LIMIT ?  OFFSET ?");
				// Khởi tạo 1 biến đếm i
				int i = 1;
				//Thực thi câu truy vấn
				prepareStatment = conn.prepareStatement(sqlQuery.toString());
				// Xét giá trị cho tham số rule
				prepareStatment.setInt(i++, Constant.RULE_USER);
				//Kiểm tra nếu groupId lớn hơn 0
				if (groupId > 0) {
					// Truyền giá trị cho tham số g.group_id
					prepareStatment.setInt(i++, groupId);
					// Kết thúc lệnh if
				}
				//Kiểm tra nếu fullName khác rỗng
				if (fullName.length() > 0) {
				     // Truyền giá trị cho tham số u.fullName
					prepareStatment.setString(i++, "%" + fullName + "%");
					// Kết thúc lệnh if
				}
				// truyền giá trị cho tham số LIMIT
				prepareStatment.setInt(i++, limit);
				// Set giá trị cho OFFSET
				prepareStatment.setInt(i++, offset);
				// Thực thi câu lệnh truy vấn và lưu kết quả vào reusultSet
				resultSet = prepareStatment.executeQuery();
				//Bắt đầu vòng while
				while (resultSet.next()) {
					// Khởi tạo đối tượng userInfor
					UserInfor userInfor = new UserInfor();
					// Sét giá trị lấy ra từ resultSet cho thuộc tính user_id
					userInfor.setUserId(resultSet.getInt("user_id"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính full_name_kana
					userInfor.setFullNameKana(resultSet.getString("full_name_kana"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính login_name
					userInfor.setLoginName(resultSet.getString("login_name"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính full_name
					userInfor.setFullName(resultSet.getString("full_name"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính birthday
					String birthday = Common.convertFormatBirthday(resultSet.getString("birthday"));
					userInfor.setBirthday(birthday);
					// Sét giá trị lấy ra từ resultSet cho thuộc tính group_id
					userInfor.setGroupId(resultSet.getInt("group_id"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính group_name
					userInfor.setGroupName(resultSet.getString("group_name"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính email
					userInfor.setEmail(resultSet.getString("email"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính tel 
					userInfor.setTel(resultSet.getString("tel"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính codelevel
					userInfor.setCodeLevel(resultSet.getString("code_level"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính name_level
					userInfor.setNameLevel(resultSet.getString("name_level"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính start_date
					String startDate = Common.convertFormatBirthday(resultSet.getString("start_date"));
					userInfor.setStartDate(startDate);
					// Sét giá trị lấy ra từ resultSet cho thuộc tính end-date
					String endDate = Common.convertFormatBirthday(resultSet.getString("end_date"));
					userInfor.setEndDate(endDate);
					// Sét giá trị lấy ra từ resultSet cho thuộc tính total của
					userInfor.setTotal(resultSet.getString("total"));
					// Thêm đối tượng userInfor vào danh sách listUserInfor
					listUserInfor.add(userInfor);
					// kết thúc lệnh whitle
				}
				// Kết thúc lệnh if
			}
			// kết thúc khối try
		} catch (SQLException | ClassNotFoundException  | IOException e) {
			// Ghi log lỗi
			System.out.println("[TblUserDaoImpl].[getListUsers]" + e.getMessage());
			//Ném lỗi 
			throw e;
			// kết thúc catch
		} finally {
			// Đóng kết nối
			closeConnection();
			// Kết thúc finaly
		}
		// Trả về danh sách listUerInfor
		return listUserInfor;
		// Kết thúc phương thức
	}

	
	/**
	 * Phương thức dùng để lấy tất cả title của các cột trong DB 
	 * @throws: ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws : SQLException : Lỗi thao tác với DB
	 * @return: List<string>
	 * @throws IOException : Lỗi đọc file
	 */
	@Override
	public  List<String> getTotalColumnName() throws SQLException, ClassNotFoundException, IOException {
		// Khởi tạo List<String> để lưu các title lấy từ DB
		// Bắt đầ khối try
	
		try {
			// Khởi tạo đối tương StringBuidler để lưu câu truy vấn sql
			StringBuilder sql = new StringBuilder();
			// select tất cả title của các bảng lấy từ DB
			sql.append(" SELECT * FROM tbl_user AS u INNER JOIN mst_group AS g ON u.group_id = g.group_id ");
			// left join
			sql.append("LEFT JOIN (tbl_detail_user_japan AS d INNER JOIN mst_japan AS j ");
			// Điều kiện inner join của 2 bảng (tbl_detail_user_japan INNER JOIN
			// mst_japan
			sql.append("ON d.code_level = j.code_level) ");
			// Điều kiện left join
			sql.append("ON u.user_id = d.user_id ");
			// mở kết nối với DB
			onpenConnection();
			// Nếu kết nối thàn công
			if (conn != null) {
				// Thực thi truy vấn
				prepareStatment = conn.prepareStatement(sql.toString());
				// thực thi câu truy vấn và lưu kết quả vào resultSet
				resultSet = prepareStatment.executeQuery();
				// Lấy đối tượng resultSet dùng resultSetMetaData
				ResultSetMetaData resultSetMetadata = (ResultSetMetaData) resultSet.getMetaData();
				// Bắt đầu vòng lặp for
				for (int i = 1; i <= resultSetMetadata.getColumnCount(); i++) {
					// Thêm title của cột vào listTotalColumnName
					listColumnSort.add(resultSetMetadata.getColumnName(i));
					// Kết thúc vòng lặp for
				}
				// Kết thúc if
			}
			// Kết thúc khối try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("[TblUserDaoImpl].[getTotalColumnName]" + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		} finally {
			// Đóng kết nối
			closeConnection();
			// Kết thúc finnaly
		}
		// Trả về kết quả
		
		return listColumnSort;
		
		// kết thúc catch
	}
	// Kết thúc phương thức

	/* (non-Javadoc)
	 * @see manageruser.dao.TblUserDao#isExit(java.lang.String)
	 */
	@Override
	public TblUser getTblUserByLoginName(String loginName) throws SQLException, ClassNotFoundException, IOException {
		// Khởi tạo đối tượng tblUser
		TblUser tblUser = new TblUser();
		// Bắt đầu khối try
		try {
			// Mở kết nối đến database
			onpenConnection();
			// Kiểm tra connection khac null
			if (conn != null) {
				// Khai báo câu query dùng để truy vấn
				String sqlQuery = "SELECT login_name FROM  tbl_user WHERE login_name = ?";
				// Khai báo 1 prepareStatement
				prepareStatment = conn.prepareStatement(sqlQuery.toString());
				// Khai báo ResultSet lưu kết quả câu truy vấn
				int i = 1;
				// Set giá trị cho tham số loginName
				prepareStatment.setString(i++, loginName);
				// Lấy kết quả từ câu truy vấn
				resultSet = prepareStatment.executeQuery();
				// Mở vòng lặp để set giá trị cho các thuộc tính của TblUser
				while (resultSet.next()) {
					tblUser.setLoginName(resultSet.getString("login_name"));
					// Kết thúc while
				}
				// Kết thúc if kiểm tra kết nối khác null
			}
			// Kết thúc khối try
		} catch (ClassNotFoundException | SQLException  | IOException e) {
			// Ghi log lỗi
			System.out.println("[TblUserDaoImpl].[isExitLoginName] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc try
		}
		// Trả về kết quả
		return tblUser;

		// Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.dao.TblUserDao#isExitEmail(java.lang.String)
	 */
	@Override
	public TblUser getTblUserByEmail(String email) throws SQLException, ClassNotFoundException, IOException {
		// Khởi tạo đối tượng tblUser
		TblUser tblUser = new TblUser();
		// Bắt đầu khối try
		try {
			// Mở kết nối đến database
			onpenConnection();
			// Kiểm tra connection khac null
			if (conn != null) {
				// Khai báo câu query dùng để truy vấn
				String sqlQuery = "SELECT email FROM  tbl_user WHERE email = ? ";
				// Khai báo 1 prepareStatement
				prepareStatment = conn.prepareStatement(sqlQuery.toString());
				// Khởi tạo biến chạy = 1
				int i = 1;
				// Set giá trị cho tham số email
				prepareStatment.setString(i++, email);
				// Lấy kết quả câu truy vấn
				resultSet = prepareStatment.executeQuery();
				// Mở vòng lặp để set giá trị cho các thuộc tính của TblUser
				while (resultSet.next()) {
					tblUser.setEmail(resultSet.getString("email"));
					// Kết thúc while
				}
				// Kết thúc if kiểm tra connection
			}
			// Kết thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("[TblUserDaoImpl].[isExitEmail] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc try
		}
		// Trả về false
		return tblUser;
		// Kết thúc phương thức
	}
	

	/* (non-Javadoc)
	 * @see manageruser.dao.TblUserDao#insertUser(manageruser.entities.TblUser)
	 */
	@Override
	public int insertUser(TblUser tblUser) throws SQLException {
		// Khởi tạo userId = 0
		int userId = 0;
		// Bắt đầu vòng try
		try {
			// Kiểm tra kết nối khác null
			if (conn != null) {
				// Khởi tại chuỗi sql
				String sqlQuery = "INSERT INTO tbl_user (group_id,login_name,password,full_name,full_name_kana,email,tel,birthday,Rule,Salt)\r\n "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				// thực thi câu truy vấn
				prepareStatment = conn.prepareStatement(sqlQuery.toString(), Statement.RETURN_GENERATED_KEYS);
				// Khởi tạo biến đếm = 1
				int i = 1;
				// Set giá trị cho groupId
				prepareStatment.setInt(i++, tblUser.getGroupId());
				// Set giá trị cho LoginName
				prepareStatment.setString(i++, tblUser.getLoginName());
				// Set giá trị cho password
				prepareStatment.setString(i++, tblUser.getPassword());
				// Set giá trị cho fullName
				prepareStatment.setString(i++, tblUser.getFullName());
				// Set giá trị cho fullNameKana
				prepareStatment.setString(i++, tblUser.getFullNameKana());
				// Set giá trị cho email
				prepareStatment.setString(i++, tblUser.getEmail());
				// Set giá trị cho tel
				prepareStatment.setString(i++, tblUser.getTel());
				// Set giá trị cho birthday
				prepareStatment.setDate(i++, Common.convertStringToDate(tblUser.getBirthday()));
				// Set giá trị cho Rule
				prepareStatment.setInt(i++, tblUser.getRule());
				// Set giá trị cho Salt
				prepareStatment.setString(i++, tblUser.getSalt());
				// Thực thi câu truy vấn
				prepareStatment.execute();
				// Lấy ra user_id sau khi insert thành công
				resultSet = prepareStatment.getGeneratedKeys();
				// Bắt đầu vòng lặp while
				while (resultSet.next()) {
					// Lấy giá trị cột user_id
					userId = resultSet.getInt(1);
					// Kết thúc while
				}
				// Kết thúc if
			}
			// Kết thúc try
		} catch (SQLException e) {
			// Ghi log lỗi
			System.out.println("[TblUserDaoImpl].[insertUser]: " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Trả về kết quả
		return userId;

		// Kết thúc phương thưc
	}

	/* (non-Javadoc)
	 * @see manageruser.dao.TblUserDao#getUserInforById(int)
	 */
	@Override
	public UserInfor getUserInforById(int userId) throws SQLException, ClassNotFoundException, IOException {
		// Khởi tạo đối tượng useInfor
		UserInfor userInfor = new UserInfor();
		// Bắt đầu khối try
		try {
			// Mở kết nối
			onpenConnection();
			// Kiểm tra kết nối khác null
			if (conn != null) {
				// Khởi tạo đối tượng StringBuilder
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append(
						" SELECT u.user_id,u.password, u.login_name,u.full_name_kana, u.full_name, u.birthday, g.group_name, g.group_id, u.email, u.tel, j.name_level, d.end_date, d.start_date,d.code_level, d.total  ");
				// Từ 2 bảng tbl_user và mst_group
				sqlQuery.append("FROM tbl_user AS u INNER JOIN mst_group AS g ");
				// Điều kiện inner join
				sqlQuery.append("ON u.group_id = g.group_id ");
				// Tiếp tục left join với 2 bảng tbl_detail_user_japan INNER
				// JOIN mst_japan
				sqlQuery.append("LEFT JOIN (tbl_detail_user_japan AS d INNER JOIN mst_japan AS j ");
				// Điều kiện inner join 2 bảng tbl_detail_user_japan, mst_japan
				sqlQuery.append("ON d.code_level = j.code_level) ");
				// Điều kiên inner left join
				sqlQuery.append("ON u.user_id = d.user_id ");
				// kiểm tra rule = ?
				sqlQuery.append("Where u.rule = ? AND u.user_id = ?");
				// Thực thi câu truy vấn
				prepareStatment = conn.prepareStatement(sqlQuery.toString());
				// Khởi tạo biến chạy = 1
				int i = 1;
				// Set giá trị cho tham số rule
				prepareStatment.setInt(i++, Constant.RULE_USER);
				// Set giá trị cho tham số user_id
				prepareStatment.setInt(i++, userId);
				// Lấy kết quả try vấn
				resultSet = prepareStatment.executeQuery();
				// Bắt đầu vòng lặp while
				while (resultSet.next()) {
					// Sét giá trị lấy ra từ resultSet cho thuộc tính user_id
					userInfor.setUserId(resultSet.getInt("user_id"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính
					// full_name_kana
					userInfor.setFullNameKana(resultSet.getString("full_name_kana"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính login_name
					userInfor.setLoginName(resultSet.getString("login_name"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính full_name
					userInfor.setFullName(resultSet.getString("full_name"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính birthday
					String birthday = Common.convertFormatBirthday(resultSet.getString("birthday"));
					userInfor.setBirthday(birthday);
					// Sét giá trị lấy ra từ resultSet cho thuộc tính group_id
					userInfor.setGroupId(resultSet.getInt("group_id"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính group_name
					userInfor.setGroupName(resultSet.getString("group_name"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính email
					userInfor.setEmail(resultSet.getString("email"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính tel
					userInfor.setTel(resultSet.getString("tel"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính password
					userInfor.setPassword(resultSet.getString("password"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính codeLevle
					userInfor.setCodeLevel(resultSet.getString("code_level"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính name_level
					userInfor.setNameLevel(resultSet.getString("name_level"));
					// Sét giá trị lấy ra từ resultSet cho thuộc tính start_date
					String startDate = Common.convertFormatBirthday(resultSet.getString("start_date"));
					userInfor.setStartDate(startDate);
					// Sét giá trị lấy ra từ resultSet cho thuộc tính end-date
					String endDate = Common.convertFormatBirthday(resultSet.getString("end_date"));
					userInfor.setEndDate(endDate);
					// Sét giá trị lấy ra từ resultSet cho thuộc tính total của
					userInfor.setTotal(resultSet.getString("total"));
					// Kết thúc vòng lặp while
				}
				// Kết thúc if kiểm tra kết nối
			}
			// Kết thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// ghi log lỗi
			System.out.println("[TblUserDaoImpl].[getUserInforById] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		}
		// trả về kết quả
		return userInfor;
		// Kết thúc phương thức
	}

	
	
	/* (non-Javadoc)
	 * @see manageruser.dao.TblUserDao#getEmailById(int)
	 */
	@Override
	public String getEmailById(int userid) throws SQLException, ClassNotFoundException, IOException {
		// Khởi tạo giá trị email bằng rỗng
		String email = Constant.EMPTY_STRING;
		// Bắt đầu khối try
		try {
			// Mở kết nối
			onpenConnection();
			// Kiểm tra nếu có kết nối
			if (conn != null) {
				// Câu truy vân lấy ra email tương ứng với Id
				String sql = "SELECT u.email FROM tbl_user as u WHERE u.rule = ? AND u.user_id = ? ";
				// thực thi câu truy vấn
				prepareStatment = conn.prepareStatement(sql);
				int i = 1 ; 
				prepareStatment.setInt(i++, Constant.RULE_USER);
				// Set giá trị cho tham sô userId
				prepareStatment.setInt(i++, userid);
				// Lấy kết quả truy vấn
				resultSet = prepareStatment.executeQuery();
				// Bắt đầu vòng lặp while
				while (resultSet.next()) {
					// Lấy ra kết quả
					email = resultSet.getString("email");
					// Kết thúc vòng lặp while
				}
				// kết thúc if kiểm tra kêt nối
			}

			// Kết thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// ghi log lõi
			System.out.println("[tblUserDaoImpl].[getEmailById] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Trả về kết quả
		return email;
		// Kết thúc phương thức
	}

	
	
	
	/* (non-Javadoc)
	 * @see manageruser.dao.TblUserDao#isExitEmailEdit(int, java.lang.String)
	 */
	@Override
	public boolean isExistEmail(int userId, String email) throws SQLException, ClassNotFoundException, IOException {
		// Khởi tạo biến kiểm tra
		boolean check = false;
		// Bắt đầu khối try
		try {
			// Mở kết nối
			onpenConnection();
			// Kiểm tra nếu kết nối khác null
			if (conn != null) {
				// Truy vấn sql
				String sql = "SELECT email FROM tbl_user WHERE rule = ? AND user_id != ?";
				// Thực thi câu truy vấn
				prepareStatment = conn.prepareStatement(sql);
				//Khởi tạo biến chạy  = 1
				int i  = 1; 
				// Set giá trị cho tham số rule
				prepareStatment.setInt(i++, Constant.RULE_USER);
				// Set giá trị cho tham số user_id
				prepareStatment.setInt(i++, userId);
				// Lấy kết quả truy vấn
				resultSet = prepareStatment.executeQuery();
				// Trả về kết quả
				while (resultSet.next()) {
					// Lấy giá trị trong cột email
					String emailDB = "";
					emailDB = resultSet.getString("email");
					// So sánh nếu bằng với email giá trị đầu vào
					if (emailDB.equals(email)) {
						// Trả về kết quả bằng true, tức là email không hợp lệ
						check = true;
						// Kết thúc if so sánh email
					}
					// Kết thúc while
				}
				// Kết thúc if kiểm tra kết nối
			}
			// Kết thúc khối try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// ghi log lỗi
			System.out.println("TblUserDaoImpl].[isExistEmailEdit] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		}
		// Trả về kết quả
		return check;
		// Kết thúc phương thức
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see manageruser.dao.TblUserDao#updateTblUser(manageruser.entities.TblUser)
	 */
	@Override
	public void updateTblUser(TblUser tblUser) throws SQLException {
		// Bắt đầu khối try
		try {
			// Kiểm tra kết nối khác null
			if (conn != null) {
				// Khởi tạo đối tượng StringBuilder
				StringBuilder sqlQuery = new StringBuilder();
				// Try vấn sql
				sqlQuery.append("UPDATE tbl_user ");
				sqlQuery.append(
						"SET group_id = ?, full_name = ?, full_name_kana = ?, email = ?, tel = ?, birthday = ? ");
				sqlQuery.append("WHERE user_id = ? AND rule = ? ");
				// Thực thi câu truy vấn
				prepareStatment = conn.prepareStatement(sqlQuery.toString());
				// Khởi tạo biến chạy = 1
				int i = 1;
				// Set giá trị cho tham số group_id
				prepareStatment.setInt(i++, tblUser.getGroupId());
				// Set giá trị cho tham số full_name
				prepareStatment.setString(i++, tblUser.getFullName());
				// Set giá trị cho tham số full_name_kana
				prepareStatment.setString(i++, tblUser.getFullNameKana());
				// Set giá trị cho tham số gmail
				prepareStatment.setString(i++, tblUser.getEmail());
				// Set giá trị cho tham số email
				prepareStatment.setString(i++, tblUser.getTel());
				// Set giá trị cho tham số birthday
				prepareStatment.setDate(i++, Common.convertStringToDate(tblUser.getBirthday()));
				// Set giá trị cho tham số user_id
				prepareStatment.setInt(i++, tblUser.getUserId());
				// Set giá trị cho tham số rule
				prepareStatment.setInt(i++, Constant.RULE_USER);
				// thực thi truy vấn
				prepareStatment.execute();
				// Kết thúc if kiểm tra kết nối khác null
			}
			// Kết thúc khối try
		} catch (SQLException e) {
			// Ghi log lỗi
			System.out.println("[TblUserImpl].[updateTblUser] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kêt thúc khối catch
		}
		// Kết thúc phương thức
	}
	
	
	
	
	
	/* (non-Javadoc)
	 * @see manageruser.dao.TblUserDao#deleteTblUserById(int)
	 */
	@Override
	public void deleteTblUserById(int userId) throws SQLException {
		// Bắt đầu khối try
		try {
			// Kiểm tra nếu kết nối khác null
			if (conn != null) {
				// Truy vấn
				String sql = "DELETE FROM tbl_user WHERE user_id = ?";
				// thực thi truy vấn
				prepareStatment = conn.prepareStatement(sql);
				// Set giá trị cho tham số user_id
				prepareStatment.setInt(1, userId);
				// Thực thi truy vấn
				prepareStatment.execute();
				// Kết thúc khối try
			}
		} catch (SQLException e) {
			// Ghi log lỗi
			System.out.println("TblUserDaoImpl.[deleteTblUserById] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Kết thúc phương thức
	}

	
	
	/* (non-Javadoc)
	 * @see manageruser.dao.TblUserDao#getTblUserById(int)
	 */
	@Override
	public TblUser getTblUserById(int userId) throws SQLException, ClassNotFoundException, IOException {
		TblUser tblUser = new TblUser();
		// Bắt đầu khối try
		try {
			// Mở kết nối
			onpenConnection();
			// Kiểm tra kết nối khác null
			if (conn != null) {
				// Câu truy vấn sql
				String sql = "SELECT *FROM tbl_user WHERE user_id = ?";
				// Thực thi truy vấn
				prepareStatment = conn.prepareStatement(sql);
				// Khởi tạo biến đếm
				int i = 1;
				// Set giá trị cho tham sô user_id
				prepareStatment.setInt(i++, userId);
				// Trả về kết quả
				resultSet = prepareStatment.executeQuery();
				// Bắt đầu vòng lặp while
				while (resultSet.next()) {
					// Set giá trị userId
					tblUser.setUserId(resultSet.getInt("user_id"));
					// Set giá trị rule
					tblUser.setRule(resultSet.getInt("rule"));
					// Kết thúc vòng while
				}
				// Kết thúc if kiểm tra kết nối
			}
			// Kết thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("[TblUserDaoImpl].[getTblUserById] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		}
		// Trả về kết quả
		return tblUser;
		// Kết thúc phương thức
	}
	// Kết thúc class
}
