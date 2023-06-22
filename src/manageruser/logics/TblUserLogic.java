/**
 /**
 * Copyright(C) 2020 Luvina Sorfware
 * TblUserLogic.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.logics;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import manageruser.entities.TblUser;
import manageruser.entities.UserInfor;


/**
 * Lớp này dùng để: Định nghĩa các hành vi cho đối tượng TblUserLogicImpl
 * 
 * @author : Lê Minh
 */
public interface TblUserLogic {

	/**
	 * Phương thức dùng để kiểm tra đăng nhập khi nhập tài khoản và mật khẩu ở màn hình ADM001
	 * @param loginName : tên đăng nhập
	 * @param password :  mật khẩu
	 * @throws NoSuchAlgorithmException :Lỗi không tìm thấy thuật toán
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác DB
	 * @return : true : nếu tồn tại
	 * @return :false : nếu không tồn tại
	 * @throws IOException : Lỗi đọc file
	 */
	public boolean existLoginId(String loginName, String password)
			throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, IOException;

	/**
	 * Phương thức dùng để lấy tổng số bản ghi có trong DB
	 * @param groupId : Mã nhóm
	 * @param fullName: Tên đầy đủ của user
	 * @return: Trả về tổng số records
	 * @throws NoSuchAlgorithmException : Lỗi không tìm thấy thuật toán
	 * @throws ClassNotFoundException   : Lỗi không tìm thấy class
	 * @throws SQLException  : Lỗi thao tác với DB
	 * @throws IOException : Lỗi đọc file
	 */
	public int getTotalUsers(int groupId, String fullName)
			throws ClassNotFoundException, SQLException, IOException;

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
	 * @throws NoSuchAlgorithmException  : Lỗi thuật toán
	 * @throws ClassNotFoundException : Lỗi không tim thấy class
	 * @throws SQLException  : Lỗi thao tác với DB
	 * @return: List<UserInfor> Trả về danh sách UserInfor   
	 * @throws IOException : Lỗi đọc file
	 */
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException, IOException;
	
	/**
	 * Phương thức check đã tồn tại giá trị trường loginName trong DB
	 * @param loginName
	 * @return true nếu đã tồn tại, false nếu chưa tồn tại
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws IOException : Lỗi đọc file
	 */
	public boolean isExistLoginName(String loginName) throws ClassNotFoundException, SQLException, IOException;
	
	/**
	 * Phương thức check đã tồn tại giá trị trường email trong DB
	 * @param email : Là email của user
	 * @return true nếu đã tồn tại, false nếu chưa tồn tại
	 * @throws SQLException : Lỗi thao tác DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws IOException : Lỗi đọc file
	 */
	public boolean isExistEmail(String email) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Phương thức dùng để tạo 1 userInfor
	 * @param userInfor : Là 1 đối tượng userInfor
	 * @throws : ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws : SQLException : Lỗi thao tác với DB
	 * @return : boolean
	 * @throws NoSuchAlgorithmException : Lỗi thuật toán
	 * @throws IOException : Lỗi đọc file
	 */
	public boolean createUser(UserInfor userInfor)throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, IOException;

	/**
	 * Phương thức trả về thông tin chi tiết của 1 đối tượng user
	 * @param userId Là id của user
	 * @return : UserInfor
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws IOException : Lỗi đọc file
	 * @throws NoSuchAlgorithmException : Lỗi thuật toán
	 */
	public UserInfor getUserInforById(int userId) throws ClassNotFoundException, SQLException, IOException;
	
	/**
	 * Phương thức lấy ra thông tin của đối tượng userInfor
	 * @param userId : Là id của user
	 * @return : email của user tương ứng với userId
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws IOException : Lỗi đọc file
	 */
	public String getEmailById(int userid) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * Phương thức check tồn tại email tương ứng với userId
	 * @param email: email của user
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * * @return kết quả check : boolean
	 * @throws IOException : Lỗi đọc file
	 */
	public boolean isExistEmail(int userId, String email) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * Phương thức dùng để thay đổi thông tin trong bảng tbl_user
	 * @param tblUser : Là 1 đối tượng tblUser
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 */
	public void  updateTblUser(TblUser tblUser) throws SQLException, ClassNotFoundException;
	
	/**
	 * Phương thức dùng để update thông tin bảng tbl_user và tbl_detail_user_japan
	 * @param userInfor :Là 1 đối tượng userInfor
	 * @param existCodeLevle: Là trạng thái trình độ tiếng nhật của user
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws IOException : Lỗi đọc file
	 * @throws NoSuchAlgorithmException : Lỗi thuật toán
	 */
	public boolean updateUserInfor(UserInfor userInfor, boolean existCodeLevel) throws SQLException, ClassNotFoundException,  IOException, NoSuchAlgorithmException;
	
	/**
	 * Phương thức dùng để update thông tin bảng tbl_user và tbl_detail_user_japan
	 * @param useId: Là id của user
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws IOException : Lỗi đọc file
	 */
	public boolean deleteUserInfor(int userId) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * Phương thức dùng để kiểm tra tồn tại Id của userId
	 * @param userId : Là id của user
	 * @return : int 0: là user  1: Là Admin 2: Là không tồn tại 
	 * @throws IOException : Lỗi đọc file
	 * @throws : ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws : SQLException : Lỗi thao tác với DB
	 */
	public int isExistUser(int userId) throws ClassNotFoundException, SQLException, IOException;
	
}
