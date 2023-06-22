/**
 * Copyright(C) 2020 Luvina Sorfware
 * TblUserDao.java Feb 27, 2020 author: L� Minh
 */
package manageruser.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import manageruser.entities.TblUser;
import manageruser.entities.UserInfor;

/**
 * Class là một Interface xử lí thao tác vưới DB bảng tbl_user
 * @author : Lê Minh
 */
public interface TblUserDao extends BaseDao {

	/**
	 * Phương thức dùng để: Lấy thông tin người dùng từ loginName
	 * @param loginName:  login được truyền vào khi đăng nhập
	 * @return: Trả về thông tin user được lấy ra.
	 * @throws ClassNotFoundException:  Không tìm thấy drive forname
	 * @throws SQLException:   Lỗi câu lệnh spl
	 * @throws IOException : Lỗi đọc file
	 */
	public TblUser getTblUserByUserName(String loginName) throws ClassNotFoundException, SQLException, IOException;

	
	/**
	 * Phương thức dùng để lấy tổng số records trong DB
	 * @param groupId: Mã nhóm
	 * @param fullName:Tên đầy đủ của user
	 * @return: Trả về tổng số records
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws IOException : Lỗi đọc file
	 */
	public int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException, IOException;
	
	
	
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
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Phương thức dùng để lấy tất cả title của các cột trong DB 
	 * @throws: ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws : SQLException : Lỗi thao tác với DB
	 * @return: List<string>
	 * @throws IOException : Lỗi đọc file
	 */
	public List<String> getTotalColumnName() throws ClassNotFoundException, SQLException, IOException;
	
	/**
	 * Phương thức check tồn tại tên đăng nhập
	 * @param loginName : Tên đăng nhập của user
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * * @return kết quả check : TblUser
	 * @throws IOException : Lỗi đọc file
	 */
	public TblUser getTblUserByLoginName(String loginName) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * Phương thức check tồn tại email
	 * @param email: email của user
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @return kết quả check : TblUser
	 * @throws IOException : Lỗi đọc file
	 */
	public TblUser  getTblUserByEmail(String email) throws SQLException, ClassNotFoundException, IOException;
	
	
	
	/**
	 * Thực hiện thêm mới 1 user vào DB
	 * @param tblUser đối tượng chứa thông tin của user
	 */
	public int insertUser (TblUser tblUser) throws SQLException;
	
	
	
	/**
	 * Phương thức lấy ra thông tin của đối tượng userInfor
	 * @param userId : Là id của user
	 * @return : 1 đối tượng userInfor
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws IOException : Lỗi đọc file
	 */
	public UserInfor getUserInforById(int userId) throws SQLException, ClassNotFoundException, IOException;
	
	
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
	public void updateTblUser(TblUser tblUser) throws SQLException;
	
	
	/**
	 * Phương thức dùng để xóa thông tin trong bảng tbl_user
	 * @param userId : Là id của user
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws SQLException : Lỗi thao tác với DB
	 */
	public void deleteTblUserById(int userId ) throws  SQLException;
    
	
	/**
	 * Phương thức đùng để lấy thông tin trong bảng tbl_user
	 * @param userId : Là id của user
	 * @return : 1 đối tượng tblUsser
	 * @throws SQLException : lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm  thấy thư viện
	 * @throws IOException : Lỗi đọc file
	 */
	public TblUser getTblUserById( int userId) throws SQLException, ClassNotFoundException, IOException;
	
	
	
	
	// Kết thúc class
}