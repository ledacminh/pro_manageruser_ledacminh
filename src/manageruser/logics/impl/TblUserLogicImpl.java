/**
 * Copyright(C) 2020 Luvina Sorfware
 * TblUserLogicImpl.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.logics.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import com.mysql.jdbc.Connection;
import manageruser.dao.TblDetailUserJapanDao;
import manageruser.dao.TblUserDao;
import manageruser.dao.impl.TblDetailUserJapanDaoImpl;
import manageruser.dao.impl.TblUserDaoImpl;
import manageruser.entities.TblDetailUserJapan;
import manageruser.entities.TblUser;
import manageruser.entities.UserInfor;
import manageruser.logics.TblDetailUserJapanLogic;
import manageruser.logics.TblUserLogic;
import manageruser.utils.Common;
import manageruser.utils.Constant;

/**
 * Trong lớp này xây dựng các phương thức xử lí logic liên quan đến thông tin
 * của bảng TblUser gửi đến servlet
 * @author : Lê Minh
 */
public class TblUserLogicImpl implements TblUserLogic {
	// Khởi tạo đối tượng tblUserDaoImpl
	TblUserDao tblUserDao = new TblUserDaoImpl();
	//Khởi tạo đối tượng TblDetailUserJapanDao
	TblDetailUserJapanDao tblDetailUserJapanDao = new TblDetailUserJapanDaoImpl();
	// Khởi tạo  tượng tblUser
	TblUser tblUser = new TblUser();
	// Khởi tạo  tượng TblDetailUserJapan
	TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
	TblDetailUserJapanLogic tblDetailUserJapanLogic = new TblDetailUserJapanLogicImpl();
	/**
	 * Phương thức dùng để kiểm tra đăng nhập khi nhập tài khoản và mật khẩu ở màn hình ADM001
	 * @param loginName : tên đăng nhập
	 * @param password :  mật khẩu
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác DB
	 * @return : true : nếu tồn tại
	 * @return :false : nếu không tồn tại
	 * @throws IOException : Lỗi đọc file
	 */
	@Override
	public boolean existLoginId(String logintName, String password)
			throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, IOException {
		// Trả về đối tượng user có fullName bằng với giá trị của tham số
		// loginName
		tblUser = tblUserDao.getTblUserByUserName(logintName);
		// Bắt đầu if kiểm tra tblUser khác null
		if (tblUser != null) {
			// Mã hóa SHA-1 password lấy từ màn hình ADM001
			String encryptPassword = Common.encryptPassword(tblUser.getSalt(), password);
			// So sánh password sau khi mã hóa với passoword của user lấy từ DB
			// Bắt đầu if kiểm tra password
			if (Common.compareString(encryptPassword, tblUser.getPassword())) {
				// Trẻ về true
				return true;
				// Kết thúc if kiểm tra password
			}
			// Kết thúc if
		}
		// Ngược lại trả về false
		return false;
		// Kết thúc phương thức
	}

	
	/**
	 * Phương thức dùng để lấy tổng số bản ghi có trong DB
	 * @param groupId : Mã nhóm
	 * @param fullName: Tên đầy đủ của user
	 * @return: Trả về tổng số records
	 * @throws ClassNotFoundException   : Lỗi không tìm thấy class
	 * @throws SQLException  : Lỗi thao tác với DB
	 * @throws IOException : Lỗi đọc file
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName)
			throws  ClassNotFoundException, SQLException, IOException {
		//Thay thế kí tự wildCard
		fullName = Common.replaceWildCard(fullName);
		// Trả về tổng số record
		return tblUserDao.getTotalUsers(groupId, fullName);
		// Kết thúc phương thưc
	}

	
	/**
	 * Phương thức dùng để: Lấy ra danh sách user
	 * @param offset : Vị trí bản ghi bắt đầu của 1 trang
	 * @param limit  : Giới hạn bản ghi trong 1 trang
	 * @param groupId  : goupId của user
	 * @param fullName   : từ khóa tìm kiếm user theo tên
	 * @param sortType   : Kiểu sắp xếp ưu tiên
	 * @param sortByFullName  : sắp xếp tăng or giảm dần theo full_name
	 * @param sortByCodeLevel  : sắp xếp tăng or giảm dần theo code_level
	 * @param sortByEndDate  : sắp xếp tăng or giảm dần theo end_date
	 * @throws ClassNotFoundException : Lỗi không tim thấy class
	 * @throws SQLException  : Lỗi thao tác với DB
	 * @return: List<UserInfor> Trả về danh sách UserInfor   
	 * @throws IOException : Lỗi đọc file
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException, IOException {
		//Thay thế các kí tự wildCard
	    fullName = Common.replaceWildCard(fullName);
		// Gọi đến phwogn thwscs getListUser
		List<UserInfor> getListUsers = tblUserDao.getListUsers(offset, limit, groupId, fullName, sortType,
				sortByFullName, sortByCodeLevel, sortByEndDate);
		// Trả về kết quả
		return getListUsers;
		// Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#isExitLoginName(java.lang.String)
	 */
	@Override
	public boolean isExistLoginName(String loginName) throws ClassNotFoundException, SQLException, IOException {
		// Khởi tạo đối tượng tblUser
		TblUser tblUser = new TblUser();
		// Lấy giá trị tương ứng với loginName
		tblUser = tblUserDao.getTblUserByLoginName(loginName);
		// Khởi tạo biến lưu giá trị kiểm tra bằng false
		boolean check = false;
		// Kiểm tra nếu loginName khác null
		if (tblUser.getLoginName() != null) {
			// Gán giá trị bằng true cho biến kiếm tra
			check = true;
			// kết thúc if kiểm tra loginName khác null
		}
		// Trả về kết quả kiểm tra
		return check;
		// Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#isExitEmail(java.lang.String)
	 */
	@Override
	public boolean isExistEmail(String email) throws ClassNotFoundException, SQLException, IOException {
		// Khởi tạo đối tượng tblUser
		TblUser tblUser = new TblUser();
		// Lấy giá trị tương ứng với email
		tblUser = tblUserDao.getTblUserByEmail(email);
		// Khởi tạo biến lưu giá trị kiểm tra bằng false
		boolean check = false;
		// Kiểm tra giá trị email nếu khác null
		if (tblUser.getEmail() != null) {
			// Gán giá trị bằng true
			check = true;
			// Kết thúc if kiểm tra email khác null
		}
		// Trả về kết quả kiểm tra
		return check;
		// Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#createUser(manageruser.entities.UserInfor)
	 */
	@Override
	public boolean createUser(UserInfor userInfor)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, IOException {
		// Khởi tạo biến kiểm tra tạo userInfor thành công
		boolean checkInsertSucsses = false;
		// Bắt đầu khối try
		try {
			// Mở kết nối
			tblUserDao.onpenConnection();
			// Set tự động comit = false
			tblUserDao.setCommit(false);
			// Tạo 1 đối tượng tblUser từ userInfor
			TblUser tblUser = Common.getTblUserByUserInfor(userInfor);
			// Lấy giá trị Id trả về nếu insert thành công
			int userId = tblUserDao.insertUser(tblUser);
			// Lấy thuộc tính conn từ tblUserDaoImpl
			Connection conn = tblUserDao.getConnection();
			// Nêu giá trị trả về kiểm tra xem có tồn tại trình độ tiếng nhật
			if (userId > 0) {
				// kiểm tra nếu tồn tại trình độ tiếng nhật
				if (userInfor.getCodeLevel().length() > 0) {
					// Khởi tạo đối tượng tblDetailUserJapanDaoImpl
					TblDetailUserJapanDaoImpl tblDetailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
					// Set conn cho thuộc tính conn của đối tượng
					// tblDetailUserJapanDaoImpl
					tblDetailUserJapanDaoImpl.setConnection(conn);
					// Tạo 1 đối tượng tblDetailUserJapan từ đối tượng userInfro
					TblDetailUserJapan tblDetailUserJapan = Common.getTblDetailUserJapanByUserInfor(userInfor);
					// Set giá trị cho đối tượng tblDetailUserJapan
					tblDetailUserJapan.setUserId(userId);
					// Kiểm tra đã insert thành công hay chưa
					tblDetailUserJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapan);
					// Kết thúc if kiểm tra tồn trị TĐTN
				}
				// Kết thúc kiểm tra userId
			}
			// Gán giá trị kiểm tra bằng true
			checkInsertSucsses = true;
			// thực hiện comit
			tblUserDao.commit();
			// Kết thúc try
		} catch (ClassCastException | SQLException  | IOException | NoSuchAlgorithmException e) {
			// Ghi log lỗi
			System.out.println("TblUserLogicImpl].[createUser] " + e.getMessage());
			
			// Thực hiện rolllback
			tblUserDao.rollBack();
			// Gán giá trị kiểm tra bằng false
			checkInsertSucsses = false;
			//Ném lỗi 
			throw e;
			// Kết thúc khối try
		} finally {
			// Đóng kết nối
			tblUserDao.closeConnection();
			// Kết thúc khối finally
		}
		// Trả về kết quả
		return checkInsertSucsses;
		// Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#getUserInforById(int)
	 */
	@Override
	public UserInfor getUserInforById(int userId) throws ClassNotFoundException, SQLException, IOException {
		// Khởi tạo đối tượng userInfor
		UserInfor userInfor = new UserInfor();
		// Bắt đầu khối try
		try {
			// Lấy đối tượng userInfor tương ứng với userId
			userInfor = tblUserDao.getUserInforById(userId);
			// Kết thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("[TblUserLogicImpl].[getUserInforById] " + e.getMessage());
			//Ném lỗi
			throw e;
			// Kết thúc khối catch
		}
		// Trả về kết quả
		return userInfor;
		// Kết thúc phương thức
	}
	


	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#getEmailById(int)
	 */
	@Override
	public String getEmailById(int userid) throws SQLException, ClassNotFoundException, IOException {
		// Lấy giá trị email
		String email = tblUserDao.getEmailById(userid);
		// Trả về kết quả
		return email;
		// kết thúc phương thức
	}
	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#isExistEmail(int, java.lang.String)
	 */
	@Override
	public boolean isExistEmail(int userId, String email) throws SQLException, ClassNotFoundException, IOException {
		//Trả về kết quả
	   return tblUserDao.isExistEmail(userId, email);
		//Kết thúc phương thức
	}


	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#updateTblUser(manageruser.entities.TblUser)
	 */
	@Override
	public void updateTblUser(TblUser tblUser) throws SQLException, ClassNotFoundException {
		//update thông tin trong bảng tbl_user
           	tblUserDao.updateTblUser(tblUser);
           	//Kết thúc phương thức
	}


	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#updateUserInfor(manageruser.entities.UserInfor, boolean)
	 */
	@Override
	public boolean updateUserInfor(UserInfor userInfor, boolean existcodeLevel)
			throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException {
		// Khởi tạo giá trị cho biến kiểm tra = false
		boolean update = false;
		// Khởi tạo đối tượng tblUser
		TblUser tblUser = new TblUser();
		// Khởi tạo đối tượng tblDetailUserJapn
		TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
		// Bắt đầu khối try
		try {
			// Mởi kết nối
			tblUserDao.onpenConnection();
			// Set autoCommit = false
			tblUserDao.setCommit(false);
			// Lấy đối tượng tblUser từ userInfor
			tblUser = Common.getTblUserByUserInfor(userInfor);
			// Lấy đối tượng tblDetailUserJapan từ userInfor
			tblDetailUserJapan = Common.getTblDetailUserJapanByUserInfor(userInfor);
			// Update đối tượng tblUser
			tblUserDao.updateTblUser(tblUser);
			// Lấy kết nối thực hiện update đối tượng tblDetailUserJapan
			Connection conn = tblUserDao.getConnection();
			// Set giá trị cho kết nối của đối tượng tblDetailUserJapanDao
			tblDetailUserJapanDao.setConnection(conn);
			// Nếu cả DB và đối tượng tblDetailUserJapan đều tồn tại TĐTN
			if (!Constant.EMPTY_STRING.equals(tblDetailUserJapan.getCodeLevel()) && existcodeLevel) {
				// thực hiện update thông tin trong bảng tbl_detail_user_japan
				tblDetailUserJapanDao.updateTblDetailUserJapan(tblDetailUserJapan);
				// Nếu đối tượng tblDetailUserJapan mà không tồn tại TDTN mà
				// trong DB tồn tại TĐTN
			} else if (Constant.EMPTY_STRING.equals(tblDetailUserJapan.getCodeLevel()) && existcodeLevel) {
				// Thực hiện delete thông tin trong bảng tbl_detail_user_japan
				tblDetailUserJapanDao.deleteTblDetailUserJapan(tblDetailUserJapan.getUserId());
				// Ngược lại nếu đối tượng tblDetailUserJapan tồn tại TĐTN và
				// trong DB thì không tồn tại TĐTN
			} else if (!Constant.EMPTY_STRING.equals(tblDetailUserJapan.getCodeLevel()) && !existcodeLevel) {
				// Thực hiện insert mới vào bảng tbl_detail_user_japan
				tblDetailUserJapanDao.insertDetailUserJapan(tblDetailUserJapan);
			}
			// Update thành công thì trả giá trị về true
			update = true;
			// Thực hiện commit
			tblUserDao.commit();
			// Kết thúc khối try
		} catch (SQLException | ClassNotFoundException | IOException | NoSuchAlgorithmException e) {
			// Nếu có lỗi xảy ra thì trả kết quả về false
			update = false;
			// roll back lai dữ liệu
			tblUserDao.rollBack();
			// Ghi log lỗi
			System.out.println("[TblUserLogicImpl].[updateUserInfor] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		} finally {
			// Đóng kết nối
			tblUserDao.closeConnection();
		}
		// Trả về kết quả update
		
		return update;
		// Kết thúc phương thức
	}


	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#deleteUserInfor(int)
	 */
	@Override
	public boolean deleteUserInfor(int userId) throws SQLException, ClassNotFoundException, IOException {
		// Khởi tạo giá trị kiểm tra bằng false
		boolean check = false;
		// Bắt đầu khối try
		try {
			// Mở kết nối
			tblUserDao.onpenConnection();
			// Set auto commit = flase
			tblUserDao.setCommit(false);
			//Lấy đối tượng tblDetailUserJapan tương ứng với userId
			TblDetailUserJapan tblDetailUserJapan = tblDetailUserJapanDao.getTblDetailByUserId(userId);
			// Kiểm tra nếu tồn tại trình độ tiếng nhật
			if (tblDetailUserJapan.getCodeLevel() != null) {
				// Lấy kết nối của đối tượng tblUserDao
				Connection connection = tblUserDao.getConnection();
				// Sử dụng chung kết nối với đối tượng tblUserDao
				tblDetailUserJapanDao.setConnection(connection);
				// Gọi phương thức xóa đối tượng tblDetailUserDaoImpl
				tblDetailUserJapanDao.deleteTblDetailUserJapan(userId);
				// Kết thúc if kiểm tra tồn tại trình độ tiếng nhật
			}
			// Gọi phương thức xóa tblUser
			tblUserDao.deleteTblUserById(userId);
			// Trả về kết quả là true nếu không xảy ra lỗi
			check = true;
			// Xác thực kết thúc hành động xóa
			tblUserDao.commit();
			// Kết thúc khối try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// ghi log lỗi
			System.out.println("[TblUserLogicImpl].[deleteUserInfor] " + e.getMessage());
			// Nếu có lỗi xảy ra thì rollback lại data
			
			// Rollback lại data
			tblUserDao.rollBack();
			// Nếu xảy ra lỗi thì trả kết quả về false
			check = false;
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
			
		} finally {
			// Đóng kết nối
			tblUserDao.closeConnection();
			// Kết thúc khối finally
		}
		// Trả về kết quả
		return check;
		// Kết thúc phương thức
	}


	/* (non-Javadoc)
	 * @see manageruser.logics.TblUserLogic#isExistUser(int)
	 */
	@Override
	public int isExistUser(int userId) throws ClassNotFoundException, SQLException, IOException {
		// Khởi tạo đối tượng tblUser
		TblUser tblUser = new TblUser();
		// Lấy đối tượng tlbUser theo userId
		tblUser = tblUserDao.getTblUserById(userId);
		// Lấy ra userId của user
		int id = tblUser.getUserId();
		// Lấy rule của đối tượng tblUser
		int rule = tblUser.getRule();
		// Nếu id khác không, rule = 0 thì giá trị trả về 0 và là user
		if (id != Constant.USER_ID_DEFAULT && rule == Constant.RULE_USER) {
			// Nếu là user trả về kết quả bằng 0
			return 1;
			// Nếu id khác 0, rule =1 thì giá trị trả về là 1 và là admin
		} else if (id != Constant.USER_ID_DEFAULT  && rule == Constant.RULE_ADMIN) {
			// Nếu là admin trả về kết quả bằng 1
			return 0;
			// Ngược lại, không tồn tại user trả về 2
		} else {
			return 2;
		}
		// Kết thúc phương thức
	}
	// Kết thúc class

}