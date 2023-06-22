/**
 * Copyright(C) 2020 Luvina Sorfware
 * ValidateUser.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.validates;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import manageruser.entities.UserInfor;
import manageruser.logics.MstGroupLogic;
import manageruser.logics.MstJapanLogic;
import manageruser.logics.TblUserLogic;
import manageruser.logics.impl.MstGroupLogicImpl;
import manageruser.logics.impl.MstJapanLogicImpl;
import manageruser.logics.impl.TblUserLogicImpl;
import manageruser.utils.Common;
import manageruser.utils.Constant;
import manageruser.utils.MessageErrorProperties;

/**
 * Phương thức dùng để kiểm tra data của  các hạng mục trên màn hình 
 * @author: Lê Minh
 */
public class ValidateUser {
	// Danh sách các lỗi
	List<String> listError;
	// Khởi tạo TblUserLogic
	TblUserLogic tblUserLogic = new TblUserLogicImpl();
	/**
	 * Validate thông tin đăng nhập
	 * @param loginName : chuỗi tên đăng nhập
	 * @param password :   chuỗi mật khẩu
	 * @return List<String> danh sách thông báo lỗi
	 * @throws NoSuchAlgorithmException : lỗi không tìm thấy thuật toán
	 * @throws IOException : Lỗi đọc file
	 */
	public List<String> validateLogin(String loginName, String password)
			throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, IOException {
		//Khởi tạo danh sách lỗi 
           listError = new ArrayList<>();
		// Bắt đầu khối try
		try {
			// Kiểm tra rỗng đối với loginName
			if (Constant.EMPTY_STRING.equals(loginName)) {
				// Thêm thông báo lỗi nếu loginName rỗng
				listError.add(MessageErrorProperties.getString("ER001_USERNAME"));
				// Kết thúc if
			}
			// Kiểm tra rỗng đối với password
			if (Constant.EMPTY_STRING.equals(password)) {
				// Thêm thông báo lỗi nếu password rỗng
				listError.add(MessageErrorProperties.getString("ER001_PASSWORD"));
				// Kết thúc if
			}
			// Nếu không có lỗi
			if (listError.size() == 0) {
				// Kiểm tra tồn tại đối với TblUser mà có loginName và password
				boolean isExist = tblUserLogic.existLoginId(loginName, password);
				// Nếu TblUser không tồn tại
				if (!isExist) {
					// Thêm thông báo lỗi nếu không tồn tại
					listError.add(MessageErrorProperties.getString("ER016"));
					// Kết thúc if
				}
				// Kết thúc if kiểm tra giá trị isExist
			}
			// kết thúc try
			
			// Bắt đầu catch
		} catch (NoSuchAlgorithmException | ClassNotFoundException | SQLException e) {
			// Ghi log lỗi
			System.out.println("[Validate].[validateLogin]" + e.getMessage());
			// Kết thúc catch
		}
		// return danh sách thông báo lỗi
		return listError;
		
		// Kết thúc phương thức
	}
	
	
	/**
	 * Phương thức dùng để validate đối tương userInfor
	 * @param userInfor : Là 1 đối tượng UserInfor
	 * @throws NoSuchAlgorithmException : Lỗi thuật toán
	 * @throws ClassNotFoundException : Lỗi không tìm thấy class
	 * @throws SQLException : Lỗi thao tác với DB
	 * @return : danh sách lỗi
	 */
	public List<String> validateUserInfor(UserInfor userInfor)
			throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, IOException  {
		// Bắt đầu khối try
		try {
			// Khởi tạo danh sách lỗi
			listError = new ArrayList<>();
			// validate hạng mục LoginName
			validateLoginName(userInfor.getLoginName(), userInfor.getUserId());
			// validate hạng mục groupId
			validateGroupId(userInfor.getGroupId());
			// validate hạng mục fullName
			validateFullName(userInfor.getFullName());
			// validate hạng mục fullNameKana
			validateFullNameKana(userInfor.getFullNameKana());
			// validate hạng mục ngày sinh nhật
			validateBirtday(userInfor.getBirthday());
			// validate hạng mục gmail
			validateEmail(userInfor.getEmail(), userInfor.getUserId());
			// validate hạng mục số điện thoại
			validateTel(userInfor.getTel());
			// validate hạng mục nhập mật khẩu
			validatePassword(userInfor.getPassword(), userInfor.getUserId());
			// validate hạng mục nhập mật khẩu xác nhận
			validateConfirmPassword(userInfor.getConfirmPassword(), userInfor.getPassword(), userInfor.getUserId());
			// validate hạng mục trình độ tiếng nhật
			validateCodeLevel(userInfor.getCodeLevel());
			if (userInfor.getCodeLevel().length() > 0) {
				// validate hạng mục ngày cấp chửng chỉ
				validateStartDate(userInfor.getStartDate());
				// validate hạng mục ngày hết hạn
				validateEndDate(userInfor.getStartDate(), userInfor.getEndDate());
				// validate hạng mục tổng số điểm
				validateTotal(userInfor.getCodeLevel(), userInfor.getTotal());
			}
			// Kết thúc khối catch
		} catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | IOException e) {
			// Ghi log lỗi
			System.out.println("[ValidateUser].[validateUserInfor] " + e.getMessage());
			//Ném lỗi
			throw e;
			// Kết thúc khối catch
		}
		// Trả về danh sách mã lỗi
		return listError;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để : validate hạng mục total
	 * @param codeLevel : Trình độ tiếng nhật
	 * @param total : Tổng số điểm
	 * @throws IOException : Lỗi đọc file
	 */
	private void validateTotal(String codeLevel, String total) throws SQLException , ClassNotFoundException, IOException {
		// Bắt đầu khối try
		try {
			// Khởi tạo đối tượng mstJapanLogic
			MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
			// Kiểm tra nếu tồn tại trình độ tiếng nhật
			if (mstJapanLogic.isExitCodeLevel(codeLevel)) {
				// Kiểm tra nếu trình độ tiếng nhật bằng rỗng
				if (Constant.EMPTY_STRING.equals(total)) {
					// Thêm mã lỗi ER001_TOTAL
					listError.add(MessageErrorProperties.getString("ER001_TOTAL"));
					// Ngược lại kiểm tra nếu không phải là số halfsize
				} else if (!Common.checkHalfSize(total)) {
					// Thêm mã lỗi ER018_TOTAL
					listError.add(MessageErrorProperties.getString("ER018_TOTAL"));
					// Ngược lại, kiểm tra nếu độ dài quá 3 kí tự
				} else if (!Common.checkLength("" + total, Constant.MIN_TOTAL, Constant.MAX_TOTAL)) {
					// Thêm mã lỗi ER006_TOTAL
					listError.add(MessageErrorProperties.getString("ER006_TOTAL"));
				}
				// Kết thúc if kiểm tra tồn tại trình độ tiếng nhất
			}
			// Kết thúc try
		} catch (SQLException | ClassNotFoundException  e) {
			// Ghi log lỗi
			System.out.println("[ValidateUserInfor].[validateTotal] " + e.getMessage());
			//ném lỗi 
			throw e;
			// Kết thúc catch
		}
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để : Validate hạng mục ngày hết hạn
	 * @param startDate : Ngày chấp chứng chỉ
	 * @param endDate : Ngày hết hạn
	 */
	private void validateEndDate(String startDate, String endDate) {
		// Kiểm tra nếu ngày hết hạn không hợp lệ
		if (!Common.isExistDate(endDate)) {
			// Thêm mã lỗi ER011_END_DATE
			listError.add(MessageErrorProperties.getString("ER011_END_DATE"));
			// Ngược lại kiểm tra ngày hết hạn phải lớn hơn ngày cấp chứng chỉ
		} else if (!Common.compareDay(startDate, endDate)) {
			// Thêm mã lỗi ER012_END_DATE
			listError.add(MessageErrorProperties.getString("ER012_END_DATE"));
		}
		// Kết thúc phương thức
	}


	/**
	 * Phương thức dùng để validate hạng mục ngày cấp chứng chỉ
	 * @param startDate : Ngày bắt đầu
	 */
	private void validateStartDate(String startDate) {
		// Kiểm tra nếu ngày cấp chứng chỉ không hợp lệ
		if (!Common.isExistDate(startDate)) {
			// thêm mã lỗi ER011_START_DATE
			listError.add(MessageErrorProperties.getString("ER011_START_DATE"));
			// Kết thúc if
		}
		// Kết thúc phương thức
	}


	/**
	 * Phương thức dùng để: Validate hạng mục codeLevel
	 * @param codeLevel : Là trình độ tiếng nhật
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException :Lỗi không tìm thấy class
	 * @throws IOException : Lỗi đọc file
	 */
	private void validateCodeLevel(String codeLevel)
			throws ClassNotFoundException, SQLException, IOException {
		// Bắt đầu khối try
		try {
			// Khởi tạo đối tượng mstJapanLogic
			MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
			// Kiểm tra nếu không tồn tại
			if (codeLevel.length() > 0 && !mstJapanLogic.isExitCodeLevel(codeLevel)) {
				// thêm mã lỗi ER014_CODE_LEVEL
				listError.add(MessageErrorProperties.getString("ER014_CODE_LEVEL"));
				// Kết thúc if
			}
			// kết thúc khối try
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// Ghi log lỗi
			System.out.println("[ValidateUserInfor].[validateCodeLevel] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// kết thúc phương thức
	}

	/**
	 * Phương thức dùng để : Validate hạng mục mật khẩu xác nhận của user
	 * @param confirmPassword : Là mật khẩu ở hạng mục xác nhận
	 * @param password : Là mật khẩu ở hạng mục nhập mật khẩu
	 */
	private void validateConfirmPassword(String confirmPassword, String password, int userId) {
		// Nếu userId bằng 0 thì validate
		if (userId == 0) {
			// Kiểm tra nếu mật khẩu xác nhận không bằng mật khẩu ở hạng mục
			// nhập mật khẩu
			if (validatePassword(password, userId) && !confirmPassword.equals(password)) {
				// Thêm mã lỗi ER017_PASSWORD_CONFIRM
				listError.add(MessageErrorProperties.getString("ER017_PASSWORD_CONFIRM"));
				// Ngược lại nếu mật khẩu xác nhận bằng mật khẩu ở hạng mục nhập
				// mật khẩu
			} else {
				// Vì bên trên có gọi lại hàm validate hạng mật khẩu
				// Nên xóa các mã lỗi của hạng mục sau khi gọi lại phương thức
				for (int i = 0; i < listError.size(); i++) {
					if (listError.get(i).equals(MessageErrorProperties.getString("ER001_PASSWORD"))
							|| listError.get(i).equals(MessageErrorProperties.getString("ER008_PASSWORD"))
							|| listError.get(i).equals(MessageErrorProperties.getString("ER007_PASSWORD"))) {
						listError.remove(i);
						// Kết thúc điều kiện if xóa các mã lỗi
					}
					// Kết thúc vòng lặp for
				}
				// Kết thúc khối else
			}
			// Kết thúc if kiểm tra userId bằng 0
		}
		// Kết thúc phương thức
	}
	


	/**
	 * Phương thức dùng để: Validate hạng mục số điện thoại
	 * @param password : Là số điện thoại của user
	 */
	public boolean validatePassword(String password, int userId) {
		// Khởi tạo biến lưu giá trị kiểm tra password
		boolean checkPassword = true;
		// Nếu userdId bằng 0 thì validate
		if (userId == 0) {
			// Kiểm tra nếu không nhập số điện thoại
			if (Constant.EMPTY_STRING.equals(password)) {
				// thêm mã lỗi ER001_PASSWORD
				listError.add(MessageErrorProperties.getString("ER001_PASSWORD"));
				// Gán giá trị kiểm tra bằng false
				checkPassword = false;
				// Kiểm tra nếu không phải kí tự 1 byte
			} else if (!Common.checkOneByte(password)) {
				// thêm mã lỗi ER008_PASSWORD
				listError.add(MessageErrorProperties.getString("ER008_PASSWORD"));
				// Kiểm tra nếu nằm ngoài khoảng 5-15 kí tự
				checkPassword = false;
			} else if (!Common.checkLength(password, Constant.MIN_PASSWORD, Constant.MAX_PASSWORD)) {
				// thêm mã lỗi ER007_PASSWORD
				listError.add(MessageErrorProperties.getString("ER007_PASSWORD"));
				// Gán giá trị kiểm tra bằng false
				checkPassword = false;
			}
			// Kết thúc if kiểm tra userId bằng 0
		}
		// Kết thúc phương thức
		return checkPassword;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để: Validate hạng mục số điện thoại
	 * @param tel : Là số điện thoại của user
	 */
	private void validateTel(String tel) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {
		// Kiểm tra nếu không nhập hạng mục số điện thoại
		if (Constant.EMPTY_STRING.equals(tel)) {
			// Thêm mã lỗi ER001_TEL
			listError.add(MessageErrorProperties.getString("ER001_TEL"));
			// Ngược lại kiểm tra độ dài data nếu lớn hơn 15 kí tự
		} else if (!Common.checkLength(tel, Constant.START_DEFAULT, Constant.MAX_DEFAULT - 240)) {
			// thêm mã lỗi ER006_TEL
			listError.add(MessageErrorProperties.getString("ER006_TEL"));
			// Ngược lại kiểm tra format
		} else if (!Common.checkFormatTel(tel)) {
			// Nếu sai format thì thêm mã lỗi ER005_TEL
			listError.add(MessageErrorProperties.getString("ER005_TEL"));
		}
		// Kết thúc phương thức
	}


	/**
	 * Đây là phương thức để kiểm tra validate dữ liệu hạng mục email
	 * @param email : Là giá trị ở hạng muc email
	 * @param userId: Là id của user
	 * @throws NoSuchAlgorithmException : Lỗi thuật toán
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws IOException : Lỗi đọc file
	 */
	private void validateEmail(String email, int userId) throws ClassNotFoundException, SQLException, IOException {
		// Bắt đầu khối try
		try {
			// Khởi tạo đối tượng tblUsrLogic
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			// Lấy email từ DB tương ứng với Id của user
			String emailFromDB = tblUserLogic.getEmailById(userId);
			// Trường hợp userId bằng 0 thì thực hiện kiểm tra email
			if (userId == 0) {
				// Kiểm tra email
				checkEmail(email);
				// Ngược lại nêu userId khác 0
			} else {
				// Kiểm tra nếu email đã thay đổi với email ở trong DB thì thực hiện kiểm tra
				if (!email.equals(emailFromDB)) {
					// Kiểm tra email
					checkEmail(email);
					// Kết thúc if kiểm tra email
				}
				// Kêt thúc else
			}
			// Kêt thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("[ValidateUser].[validateEmail] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		}
		// Kết thúc phương thức
	}
	
	
	
	/**
	 * Đây là phương thức phụ để kiểm tra validate dữ liệu hạng mục email
	 * @param email : Là giá trị ở hạng muc email
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws IOException : Lỗi đọc file
	 */
	public void checkEmail(String email) throws ClassNotFoundException, SQLException, IOException {
		// Bắt đầu khối try
		try {
			// Kiểm tra lỗi bắt buộc nhập
			if (Constant.EMPTY_STRING.equals(email)) {
				// Thêm mã lỗi ER001_EMAIL vào danh sách
				listError.add(MessageErrorProperties.getString("ER001_EMAIL"));
				// Ngược lại, kiểm tra điều kiên biên
			} else if (!Common.checkLength(email, Constant.START_DEFAULT, Constant.MAX_DEFAULT - 155)) {
				// Thêm mã lỗi ER006_EMAIL vào danh sách
				listError.add(MessageErrorProperties.getString("ER006_EMAIL"));
				// Kiểm tra format của email
			} else if (!Common.checkFormatEmail(email)) {
				// Thêm mã lỗi ER005_EMAIL
				listError.add(MessageErrorProperties.getString("ER005_EMAIL"));
				// Ngược lại kiểm tra tồn tại email
			} else if (tblUserLogic.isExistEmail(email)) {
				// Thêm mã lỗi ER003_EMAIL vào danh sách
				listError.add(MessageErrorProperties.getString("ER003_EMAIL"));
			}
			// Kêt thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("[ValidateUser].[validateEmail] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		}
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để validate hạng mục ngày sinh nhật
	 * @param birthday
	 */
	private void validateBirtday(String birthday) {
		// Nếu kiểm tra không hợp lệ
		if (!Common.isExistDate(birthday)) {
			// Thêm mã lỗi ER011_BIRTHDAY vào danh sách lỗi
			listError.add(MessageErrorProperties.getString("ER011_BIRTHDAY"));
			// Kết thúc if
		}
		// Kết thúc phương thức
	} 
	
	/**
	 * Phương thức dùng để validate hạng mục fullNameKana
	 * @param fullNameKana
	 */
	private void validateFullNameKana(String fullNameKana) {
		// Kiểm tra phải là kí tự kiểu kana
		if (!Common.checkFullNameKana(fullNameKana) && fullNameKana.length() > 0) {
			// Nếu không phải kana thì thêm msg lỗi ER009_FULL_NAME_KANA
			listError.add(MessageErrorProperties.getString("ER009_FULL_NAME_KANA"));
			// Kiểm tra độ dài data
		} else if (!Common.checkLength(fullNameKana, Constant.START_DEFAULT - 1, Constant.MAX_DEFAULT)) {
			// Nếu lớn hơn 255 kí tự thì thêm msg lỗi ER006_FULL_NAME_KANA
			listError.add(MessageErrorProperties.getString("ER006_FULL_NAME_KANA"));
		}
		// kết thúc phương thức
	}

	
	
	
	/**
	 * Phương thức dùng để validate hạng mục fullName
	 * @param fullName : Tên đầy đủ của user
	 */
	private void validateFullName(String fullName) {
		// Kiểm tra hạng mục fullName khác rỗng
		if (Constant.EMPTY_STRING.equals(fullName)) {
			// Nếu đúng rỗng thì thêm mã lỗi ER001_FULL_NAME
			listError.add(MessageErrorProperties.getString("ER001_FULL_NAME"));
			// Ngước lại kiểm tra độ dài của trường fullName
		} else if (!Common.checkLength(fullName, Constant.START_DEFAULT, Constant.MAX_DEFAULT)) {
			// Nếu lớn hơn 255 kí tự thì thêm mã lỗi ER006_FULL_NAME
			listError.add(MessageErrorProperties.getString("ER006_FULL_NAME"));
		}
		// Kết thúc phương thức
	}


	/**
	 * Phương thức dùng để validate trường groupId
	 * @param groupId : Mà id của group
	 * @throws SQLException : Lõi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws IOException : Lỗi đọc file
	 */
	private void validateGroupId(int groupId) throws  ClassNotFoundException, SQLException, IOException {
		// Bắt đầu khối try
		try {
			// Khởi tạo đối tượng msstGroupLogic
			MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
			// Kiểm tra với groupId mặc định
			if (groupId == Constant.GROUP_ID_DEFAULT) {
				// Thêm mã lỗi ER002_GROUP_ID
				listError.add(MessageErrorProperties.getString("ER002_GROUP_ID"));
				// Ngược lại kiểm tra tồn tại groupId trong DB
			} else if (!mstGroupLogic.isExitGroupId(groupId)) {
				// Thêm mã lỗi ER004_GROUP_ID
				listError.add(MessageErrorProperties.getString("ER004_GROUP_ID"));
			}
			// Kết thúc try
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// ghi log lỗi
			System.out.println("[ValidateUser].[validateGroupId] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		}
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để validate trường loginName
	 * @param loginName : Tên đăng nhập của user
	 * @throws IOException : Lỗi đọc file
	 */
	private void validateLoginName(String loginName, int userId)
			throws ClassNotFoundException, SQLException, IOException {
		// Bắt đầu khối try
		try {
			// Nếu userId bằng 0 thì validate
			if (userId == 0) {
				// Kiểm tra rỗng đối với loginName
				if (Constant.EMPTY_STRING.equals(loginName)) {
					// Thêm lỗi ER001_LOGIN_NAME
					listError.add(MessageErrorProperties.getString("ER001_LOGIN_NAME"));
					// Ngược lại, kiểm tra format
				} else if (!Common.checkFormatLoginName(loginName)) {
					// Thêm lỗi ER019_LOGIN_NAME
					listError.add(MessageErrorProperties.getString("ER019_LOGIN_NAME"));
					// Ngược lại, kiểm tra khoảng kí tự cho phép nhập
				} else if (!Common.checkLength(loginName, Constant.MIN_LOGIN, Constant.MAX_LOGIN)) {
					// Thêm lỗi ER007_LOGIN_NAME
					listError.add(MessageErrorProperties.getString("ER007_LOGIN_NAME"));
					// Ngược lại kiểm tra sự tồn tại của loginName trong DB
				} else if (tblUserLogic.isExistLoginName(loginName)) {
					// Thêm lỗi ER003_LOGIN_NAME
					listError.add(MessageErrorProperties.getString("ER003_LOGIN_NAME"));
				}
				// Kết thúc if kiểm tra userId bằng 0
			}
			// Kết thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// ghi log lỗi
			System.out.println("[ValidateUser].[validateLoginName] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc catch
		}
		// Kết thúc phương thức
	}
	//Kết thúc class
}
