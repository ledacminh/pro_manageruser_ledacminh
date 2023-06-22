/**
 * Copyright(C) 2020 Luvina Sorfware
 * Common.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.utils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;


import manageruser.dao.TblUserDao;
import manageruser.dao.impl.TblUserDaoImpl;
import manageruser.entities.TblDetailUserJapan;
import manageruser.entities.TblUser;
import manageruser.entities.UserInfor;

/**
 * Lớp này dùng để: Định nghĩa các phương thức dùng chung
 * @author : Lê Minh
 */
public class Common {
	/**
	 * Phương thức dùng để mã hóa mật khẩu trước khi insert vào DB
	 * @param salt : là giá trị mã hóa cùng với mật khẩu
	 * @param password : Là giá trị của passoword
	 * @return : Trả về kết quả mã hóa
	 * @throws NoSuchAlgorithmException : Lỗi thuật toán
	 */
	public static String encryptPassword(String salt, String password) throws NoSuchAlgorithmException {
		// Khai báo password sau khi được mã hóa SHA-1
		String newPassword = "";
		// Ghép salt và password thành 1 chuỗi đầu vào để mã hóa SHA-1
		String input = salt + password;
		// Mã hóa input thành mảng byte rồi lưu vào mảng byte[] inputBytes
		byte[] inputBytes = input.getBytes();
		// Bắt đầu khối try
		try {
			// Cấu hình sử dụng hàm băm SHA-1
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			// tạo lại digest bởi mảng byte inputBytes
			messageDigest.update(inputBytes);
			// Trả về giá trị của digest dưới dạng 1 mảng byte.
			byte[] degestedBytes = messageDigest.digest();
			// Chuyển mảng về dạng Hex
			newPassword = DatatypeConverter.printHexBinary(degestedBytes);
			// Kết thúc khối try
			// Bắt đầu khối catch và bắt lỗi ngoại lệ xảy ra
		} catch (NoSuchAlgorithmException e) {
			// ghi log lỗi
			System.out.println("Lỗi: Class_EncryptPassword_phương thức  encryptPassword" + e.getMessage());
			// Kết thúc khối catch
		}
		// Trả về password sau khi được mã hóa
		return newPassword;
		// Kết thúc phương thức
	}
	
	/**
	 * Phương thức dùng để: So sánh password sau khi được mã hóa lấy từ đầu vào
	 * và password của user trong DB
	 * @param inputPassword:  Là mật khẩu của user lấy từ trong DB
	 * @param encryptPassword: Là mật khẩu sau khi được mã hóa SHA-1
	 * @return: Trả về kết quả so sánh true or false
	 */
	public static boolean compareString(String inputPassword, String encryptPassword) {
		// khởi tạo biến check
		boolean check = false;
		// so sánh 2 giá trị vừa truyền vào
		if (inputPassword.equals(encryptPassword)) {
			// nếu giống nhau gán check = true
			check = true;
			// Kết thúc if, bắt đầu khối else
		} else {
			// nếu khác nhau gán check = false
			check = false;
			// Kết thúc khối else
		}
		// trả về giá trị biến check
		return check;
		// kết thúc phương thức
	}

	/**
	 * Phương thức dùng để kiểm tra đăng nhập
	 * @param session : là session khi đăng nhấp
	 * @return: true : Nếu đã đăng nhập
	 * @return: false : Nếu chưa đăng nhập
	 */
	public static boolean checkLogin(HttpSession session) {
		// Lấy giá trị session từ ListUserController
		String sessionName = (String) session.getAttribute(Constant.LOGIN_NAME);
		// Bắt đầu if, so sánh với giá trị của sessionName
		if (Constant.LOGIN_NAME.equals(sessionName)) {
			// Trả về true nếu đã đăng nhập
			return true;
			// Kết thúc if
		}
		// Trả về false nếu chưa đăng nhấp
		return false;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để: Kiểm tra tồn tại tên cột truyền vào có thuộc tên cột trong DB hay không
	 * @param name:  Là tên cột truyền vào
	 * @return: true nếu tồn tại, false nếu không tồn tại
	 * @throws : SQLException : Lỗi thao tác với DB
	 * @throws : ClassCastException : Lỗi không tìm thấy thư viện
	 */
	public static boolean isExitColumnName(String name) throws ClassNotFoundException , SQLException, IOException {
		// Khởi tạo 1 đối tương TblUserDaoImpl();
		TblUserDao tblUserDaoImpl = new TblUserDaoImpl();
		// Khai báo listTotalColumnName để lưu các title của các cột của các bảng
		List<String> listTotalColumnName;
		// Bắt đầu khối try
		try {
			// Lấy ra danh sách các title của các bảng
			listTotalColumnName = tblUserDaoImpl.getTotalColumnName();
			// Bắt đầu vòng lặp for
			for (int i = 0; i < listTotalColumnName.size(); i++) {
				// Kiểm tra title của trong danh sách với giá trị của tham số  truyền vào
				if (name.equalsIgnoreCase(listTotalColumnName.get(i))) {
					// Trả về true nếu có giá trị truyền vào tồn tại trong danh sách lấy ra.
					return true;
					// kết thúc vòng lệnh if
				}
				// kết thúc vòng lặp for
			}
			// kết thúc khối try
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// Ghi log lỗi
			System.out.println("[Common].[isExitColumnName]" + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối try
	
		}
		// Trả về false nếu có giá trị truyền vào không tồn tại trong danh sách lấy ra.
		return false;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức trả về 1 danh sách các paging
	 * @param totalUser  : Tổng số user
	 * @param limit : Số bản ghi tối đa hiển thị trên 1 page
	 * @param currentPage : Trang hiện tại đang thao tác
	 * @return: List<Interger> : Trang hiện tại đang thao tác
	 */
	public static List<Integer> getListPaging(int totalUser, int limit, int currentPage) {
		// Khởi tạo listPaging để lưu số thứ tự page
		List<Integer> listPaging = new ArrayList<>();
		// Tính tổng số page
		int totalPage = Common.getTotalPage(totalUser, limit);
		// Khởi tạo giá trị page bắt đầu của 1 vùng page
		int beginPage = 0;
		// Khởi tạo giá trị page kết thúc của 1 vùng page
		int endPage = 0;
		// Khởi tạo vùng page
		int currentRegion = 0;
		// Nếu vị trí page hiện tại chia hết cho giới hạn page tối đa của 1 vùng
		// page
		// Bắt đầu khối if
		if (currentPage % Constant.LIMIT_PAGE == 0) {
			// Tính vùng page hiện tại
			currentRegion = currentPage / Constant.LIMIT_PAGE;
			// Ngược lại
		} else {
			// Tính vùng page hiện tại
			currentRegion = currentPage / Constant.LIMIT_PAGE + 1;
			// Kết thúc khối else
		}
		// Xác định điểm bắt đầu của vùng page hiện tại
		beginPage = (currentRegion - 1) * Constant.LIMIT_PAGE + 1;
		// Xác định điểm kết thúc của vùng page hiện tại
		endPage = currentRegion * Constant.LIMIT_PAGE;
		// Kiêm tra điểm kết thúc của 1 vùng page với tổng số page
		if (endPage > totalPage) {
			// Gán điểm kết thúc page = tổng số page
			endPage = totalPage;
		}
		// Bắt đầu Vòng lặp for
		for (int i = beginPage; i <= endPage; i++) {
			// Thêm số thứ tự page vào listPaging
			listPaging.add(i);
			// Kết thúc vòng lặp for
		}
		// Trả về danh sách số nguyên cần lấy ra
		return listPaging;
		// Kết thúc phương thức
	}
	
	/**
	 * Phương thức dùng để : Lấy ra vị trí bắt đầu của bản ghi trong 1 trang
	 * @param currentPage : Trang hiện tại đang thao tác
	 * @param limit  : Giới hạn bản ghi trong 1 trang
	 * @return : Trả về vị trí bản ghi bắt đầu của 1 trang
	 */
	public static int getOffset(int currentPage, int limit) {
		// Khởi tạo vị trí của bản ghi bắt đầu trong 1 page
		int offset = 0;
		// nếu vị trí page hiên tại đang thao tác lơn 1 trí offset
		offset = (currentPage - 1) * limit;
		// Trả vê vị trí offset
		return offset;
		// kết thúc phương thức
	}

	/**
	 * Phương thức dùng để lấy ra số bản ghi tối ra hiển thị trong 1 trang từ file database.properties
	 * @return : Số bản ghi tối đa.
	 */
	public static int getLimit() throws IOException {
		// Khởi tạo litmit bản ghi bằng 0
		int limit = 0;
		// Bắt đầu khối try
		try {
			// Lấy giá trị có key = limit trong file config.properties
			String value = ConfigProperties.getString(Constant.LIMIT);
			// Chuyển giá trị lấy được sang kiểu số nguyên
			limit = Integer.parseInt(value);
			// Bắt đầu khối catch
			// Bắt lỗi toán học khi chuyển chuỗi số nguyên sang kiểu số nguyên
		} catch (ArithmeticException e) {
			// Ghi log lỗi
			System.out.println("[Common].[getLimit]" + e.getMessage());
			// Kết thúc khối catch
		}
		// Trả về số bản ghi tối đa hiển thị trong 1 trang.
		return limit;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để tính tổng số page
	 * @param totalUser : tổng số user
	 * @param limit: số bản ghi tối đa hiển thị trong 1 trang
	 * @return: tổng số page
	 */
	public static int getTotalPage(int totalUser, int limit) {
		// Khởi tạo biến totalPage = 0
		int totalPage = 0;
		// Nếu limit khá 0 và tổng số page chia hết cho số bản ghi tối đa hiện
		// thị trong 1 trang.
		if (limit != 0 && totalUser % limit == 0) {
			// totalPaging = tổng số user chai cho số limit tối da.
			totalPage = totalUser / limit;
			// Nếu limit khác 0 và tổng số page không chia hết cho số bản ghi
			// tối đa hiện thị trong 1 trang.
		} else if (limit != 0 && totalUser % limit != 0) {
			// tổng số page
			totalPage = totalUser / limit + 1;
			// Kêt thúc khói else if
		}
		// Trả về totalPage
		return totalPage;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để chuyển mỗi chuỗi số sang kiểu số nguyên
	 * @param value : Là chuối số nguyên truyền vào.
	 * @param defaultValue : Giá trị mặc định trả về nếu xảy ra lỗi
	 * @return : result : Là giá trị trả về.
	 */
	public static int convertStringToInt(String value, int defaultValue) {
		// Gán giá trị trả về bằng giá trị mặc định nếu xảy ra lỗi
		int result = defaultValue;
		// Bắt đầu khối try
		try {
			// Chuyển chuỗi số sang số nguyên
			result = Integer.parseInt(value);
			// Kết thúc try, bắt đầu khối catch và bắt lỗi toán học
		} catch (NumberFormatException e) {
			// Ghi log lỗi
			result = defaultValue;
			
		}
		// Trả về kết quả
		return result;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để: Lấy ra giới hạn trang hiển thị trong 1 vùng.
	 * @return : giới hạn tổng số trang hiện thị trong 1 vùng
	 */
	public static int getLimitPage() throws IOException {
		// Trả về giới hạn page
		return Common.convertStringToInt(ConfigProperties.getString("LIMIT_PAGE"), 0);
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để chuyển đổi toán tử wildcard
	 * @param : value : là chuỗi truyền vào để thay thế toán tử wildcard
	 * @return: chuỗi sau khi thay thế toán tử wildcard
	 */
	public static String replaceWildCard(String value) {
		// Nếu giá trị truyền vào khác null
		if (value != null) {
			// thay thế các kí tự wildCard có trong chuỗi.
			value = value.replace("%", "\\%").replace("_", "\\_");
			// Kết thúc if
		}
		// Trả về kết quả
		return value;
		// Kết thúc phương thức
	}
	
	
	/**
	 * Phương thức dùng để : Trả về 1 danh sách số nguyên là các năm
	 * Có giá trị từ năm bắt đầu cho đến năm kết thúc
	 * @param beginYear : Năm bắt đầu
	 * @param endYear : Năm kết thúc
	 * @return : listYear
	 */
	public static List<Integer> getListYear(int beginYear, int endYear) {
		// Khởi tạo danh sách listUear
		List<Integer> listYear = new ArrayList<>();
		// Bắt đầu vòng lặp for
		for (int i = endYear; i >= beginYear; i--) {
			// Thêm 1 giá trị vào danh sách listUear
			listYear.add(i);
			// Kết thúc vòng lặp for
		}
		// Trả về danh sách listYear
		return listYear;
		// Kết thúc phương thức
	}
	
	/**
	 * Phương thức dùng để : Trả về 1 danh sách số nguyên là các tháng 1-12
	 * @return : listMonth
	 */
	public static List<Integer> getListMonth() {
		// Khởi tạo danh sách listMonth
		List<Integer> listMonth = new ArrayList<>();
		// Bắt đầu vòng lặp for
		for (int i = 1; i <= 12; i++) {
			// Thêm 1 giá trị vào danh sách listUser
			listMonth.add(i);
			// Kết thúc vòng lặp for
		}
		// Trả về kết quả listMonth
		return listMonth;
		// Kết thúc phương thức
	}
	
	/**
	 * Phương thức dùng để: Trả về 1c danh sách số nguyên là các ngày từ 1-31
	 * @return : listDay
	 */
	public static List<Integer> getListDay() {
		// Khởi tạo danh sách listMonth
		List<Integer> listDay = new ArrayList<>();
		// Bắt đầu vòng lặp for
		for (int i = 1; i <= 31; i++) {
			// thêm 1 giá trị vào danh sách listDay
			listDay.add(i);
			// Kết thúc vòng lặp for
		}
		// Trả về kết quả listDay
		return listDay;
		// Kết thúc phương thức
	}

	
	
	/**
	 * Phương thức kiểm tra format hạng mục đăng nhập loginName
	 * @param loginName : Tên đăng nhập
	 * @return : boolean
	 */
	public static boolean checkFormatLoginName(String loginName) {
		// Trả về kết quả
		return loginName.matches(Constant.FORMAT_LOGIN_NAME);
		// Kết thúc phương thức
	}
     
	
	
	/**
	 * Phương thức kiểm tra điều kiện biên
	 * @param text : Chuỗi kiểm tra
	 * @param beginLengthText : Độ dài chuỗi min
	 * @param endLengthText : Độ dài chuỗi max
	 * @return : boolean
	 */
	public static boolean checkLength(String text, int beginLengthText, int endLengthText) {
		// Lấy độ dài chuỗi text
		int length = text.length();
		// Trả về kết quả
		return (length >= beginLengthText && length <= endLengthText) ? true : false;
	}
	
	/**
	 * Phương thức kiểm tra hạng mục nhập fullNamekana
	 * @param fullNameKana : Chuỗi kí tự nhập ở hạng mục
	 * @return : boolean
	 */
	public static boolean checkFullNameKana(String fullNameKana) {
		// Trả về kết quả
		return fullNameKana.matches(Constant.FORMAT_KANA);
		// Kết thúc phương thức
	}
	
	/**
	 * Phương thức kiểm tra ngày hợp lệ
	 * @param date: năm / tháng / ngày cần kiểm tra
	 * @return : boolean
	 */
	public static boolean isExistDate(String date) {
		// Khai báo thuộc tính lưu kết quả check
		boolean check = false;
		// Cắt chuỗi ngày nhập vào
		String arrDate[] = date.split("/");
		// Gán giá trị cho năm
		int year = Integer.parseInt(arrDate[0]);
		// Gán giá trị cho tháng
		int month = Integer.parseInt(arrDate[1]);
		// Gán giá trị cho ngày
		int day = Integer.parseInt(arrDate[2]);
		// Sử dụng switch case để check ngày hợp lệ
		switch (month) {
		// Nếu là các tháng 4, 6, 9, 11 thì ngày <= 30
		case 4:
		case 6:
		case 9:
		case 11:
			if (day <= 30) {
				// Gán giá trị bằng true
				check = true;
				// Kết thúc if kiểm tra day <=30
			}
			break;
		// Nếu là tháng 2
		case 2:
			// nếu năm nhuận thì ngày <= 29
			if (year % 4 == 0 && year % 100 != 0 && day <= 29) {
				// Gán giá trị bằng true
				check = true;
				// Nếu không phải năm nhuận thì số ngày <= 28
			} else if (day <= 28) {
				// Gán giá trị bằng true
				check = true;
				// Kết thúc else-if kiểm tra day <=28
			}
			break;
		// Nếu các tháng còn lại thì số ngày luôn đúng
		default:
			// Gán giá trị bằng true
			check = true;
			break;
		}
		// Trả về kết quả
		return check;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức kiểm tra format của hạng mục email
	 * @param email : Là email  của user
	 * @return : boolean
	 */
	public static boolean checkFormatEmail(String email) {
	    // Trả về kết quả
	    return email.matches(Constant.FORMAT_EMAIL);
	    //Kết thúc phương thức
	}
	/**
	 * Phương thức dùng để check format hạng mục số điện thoại
	 * @param tel : Là số điện thoại của user
	 * @return : boolean
	 */
	public static boolean checkFormatTel(String tel) {
		//Trả về kết quả 
		return tel.matches(Constant.FORMAT_TEL);
		//Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để check số halfsize
	 * @param : key : Là chuỗi cần kiểm tra
	 * @return : boolean
	 */
	public static boolean checkHalfSize(String key) {
		//Trả về kết quả
		return key.matches(Constant.CHECK_HALF_SIZE);
		//kết thúc phương thức
	}
	
	
	/**
	 * Phương thức dùng để: Kiểm tra số nhập vào là kí tự  byte
	 * @param password : Là mật khẩu của user
	 * @return : boolean
	 */
	public static boolean checkOneByte(String string) {
		// Khởi tạo biến để lưu giá trị kiểm tra
		boolean result = false;
		// Nếu đúng là kí tự 1 byte
		if (string.length() == string.getBytes(Charset.forName("UTF-8")).length) {
			// Gán giá trị kiểm tra bằng true
			result = true;
			// Kết thúc if
		}
		// Trả về kết quả
		return result;
		// Kết thúc phương thức
	}
	
	/**
	 * Phương thức dùng để: Kiểm tra [Ngày hết hạn] phải lớn hơn [Ngày cấp chứng chỉ].
	 * @param startDay : Ngày cấp chứng chỉ
	 * @param endDay : Ngày hết hạn
	 * @return : boolean
	 */
	public static boolean compareDay(String startDay, String endDay) {

		// Khởi tạo biến kiểm tra, bán mặc đình bằng false
		boolean check = false;
		// Cắt chuỗi startDay
		String[] listStartDay = startDay.split("/");
		// Cắt chuỗi endDay
		String[] listEndDay = endDay.split("/");
		// Gán giá trị cho năm bắt đầu
		int startYear = Integer.parseInt(listStartDay[0]);
		// Gán giá trị cho tháng bắt đầu
		int startMonth = Integer.parseInt(listStartDay[1]);
		// Gán giá trị cho ngày bắt đầu
		int startDayOfMonth = Integer.parseInt(listStartDay[2]);
		// Gán giá trị cho năm kết thúc
		int endYear = Integer.parseInt(listEndDay[0]);
		// Gán giá trị cho tháng kết thúc
		int endMonth = Integer.parseInt(listEndDay[1]);
		// Gán giá trị cho ngày kết thúc
		int endDayOfMonth = Integer.parseInt(listEndDay[2]);
		// Nếu năm kết thúc lớn hơn năm hiện tại
		if (endYear > startYear) {
			// Gán giá trị check = true
			check = true;
			// Nếu năm kết thúc bằng năm hiện tại và tháng kết thúc lớn hơn
			// tháng bắt đầu
		} else if (endYear == startYear && endMonth > startMonth) {
			// Gán giá trị check = true
			check = true;
			// Nếu năm kết thúc bằng năm hiện tại và tháng kết thúc bằng tháng
			// bắt đầu, ngày kết thúc > lớn hơn ngày bắt đầu
		} else if (endYear == startYear && endMonth == startMonth && endDayOfMonth > startDayOfMonth) {
			// Gán giá trị check = true
			check = true;
		}
		// Trả về kết quả kiểm tra
		return check;
		// Kết thúc phương thức
	}

	/**
	 *  Phương thức lấy ra năm hiên tại
	 * @return : year
	 */
	public static int getCurrentYear() {
		// Khởi tạo 1 đối tượng calendar
		Calendar calendar = Calendar.getInstance();
		// Lấy giá trị năm hiện tại
		int year = calendar.get(Calendar.YEAR);
		// Thêm giá trị năm vào listDateMonthYear
		// Trả về kết quả
		return year;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức lấy ra tháng hiên tại
	 * @return : month
	 */
	public static int getCurrentMonth() {
		// Khởi tạo 1 đối tượng calendar
		Calendar calendar = Calendar.getInstance();
		// Lấy giá trị tháng hiện tại
		int month = calendar.get(Calendar.MONTH) + 1;
		// Trả về kết quả
		return month;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức lấy ra ngày hiện tại
	 * @return : day
	 */
	public static int getCurrentDay() {
		// Khởi tạo 1 đối tượng calendar
		Calendar calendar = Calendar.getInstance();
		// Lấy giá trị tháng hiện tại
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		// Trả về kết quả
		return day;
		// Kết thúc phương thức
	}
	
	/**
	 * Phương thức trả về thời gian hiện tại
	 * @return : thời gian hiện tại
	 */
	public static String getValueKey() {
		// Trả về kết quả
		return java.time.LocalDateTime.now() + "";

		// Kết thúc phương thức
	}

	/**
	 * Phương thức trả về 1 chuỗi string để mã hóa password 
	 * khi thực hiện insert 1 user
	 * @return
	 */
	public static String getSalt() {
		// Trả về kết quả
		return java.time.LocalTime.now() + "";
		// Kết thúc phương thức
	}

	
	
	/**
	 * Phương thức tạo 1 đối tượng tblUserByUserInfor
	 * @param userInfor : Là đối tượng tblUserInfor
	 * @return : 1 đối tượng tbluser
	 * @throws NoSuchAlgorithmException : Lỗi thuật toán
	 */
	public static TblUser getTblUserByUserInfor(UserInfor userInfor) throws NoSuchAlgorithmException {
		// Khởi tạo đói tương tblUser
		TblUser tblUser = new TblUser();
		// Bắt đầu khối try
		try {
			// Set giá trị cho thuộc tính userId
			tblUser.setUserId(userInfor.getUserId());
			// Set giá trị cho thuộc tính loginName
			tblUser.setLoginName(userInfor.getLoginName());
			// Set giá trị cho thuộc tính groupId
			tblUser.setGroupId(userInfor.getGroupId());
			// Set giá trị cho thuộc tính birthday
			tblUser.setBirthday(userInfor.getBirthday());
			// Set giá trị cho thuộc tính fullName
			tblUser.setFullName(userInfor.getFullName());
			// Set giá trị cho thuộc tính fullNameKana
			tblUser.setFullNameKana(userInfor.getFullNameKana());
			// Set giá trị cho thuộc tính email
			tblUser.setEmail(userInfor.getEmail());
			// Set giá trị cho thuộc tính tel
			tblUser.setTel(userInfor.getTel());
			// Set giá trị cho thuộc tính rule
			tblUser.setRule(Constant.RULE_USER);
			// lấy giá trị salt
			tblUser.setSalt(userInfor.getSalt());
			// Mã hóa password
			String password = Common.encryptPassword(userInfor.getSalt(), userInfor.getPassword());
			// Set giá trị cho thuộc tính password
			tblUser.setPassword(password);
			// Kết thúc khối try
		} catch (NoSuchAlgorithmException e) {
			// Ghi log lỗi
			System.out.println("[Comon].[getTblUserByUserInfor] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Trả về kết quả
		return tblUser;
		// kết thúc phương thức
	}
	
	/**
	 * Thực hiện lấy thông tin đối tượng TblDetailUserJapan từ đối tượng UserInfor
	 * @param userInfor đối tượng UserInfor
	 * @return tblDetailUserJapan đối tượng TblDetailUserJapan
	 */
	
	public static TblDetailUserJapan getTblDetailUserJapanByUserInfor(UserInfor userInfor) {
		// Khởi tạo đối tượng tblDetailUserJapan
		TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
		// Set giá trị cho thuộc tính userId
		tblDetailUserJapan.setUserId(userInfor.getUserId());
		// Set giá trị cho thuộc tính codeLevle
		tblDetailUserJapan.setCodeLevel(userInfor.getCodeLevel());
		// Set giá trị cho thuộc tính startDate
		tblDetailUserJapan.setStartDate(userInfor.getStartDate());
		// Set giá trị cho thuộc tính endDate
		tblDetailUserJapan.setEndDate(userInfor.getEndDate());
		// Set giá trị cho thuộc tính total
		tblDetailUserJapan.setTotal(userInfor.getTotal());
		// Trả về đối tượng tblDetailUserJapan
		return tblDetailUserJapan;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để chuyển đổi date kiểu String sang dạng date trong Mysql
	 * @param birthday
	 * @return
	 */
	public static Date convertStringToDate(String date) {
		// Chuyển String sang date
		String dateFormat = date.replace("/", "-");
		Date dateTime = Date.valueOf(dateFormat);
		java.sql.Date sqlDate = new java.sql.Date(dateTime.getTime());
		// Trả về kêt quả
		return sqlDate;

		// Kết thức phương thức
	}
	
	/*
	 *Phương thức dùng để chuyển format số điện thoại nhập ở màn hình ADM003 sang ADM004
	 *@param tel: Là chuỗi string nhập ở màn hình adm003
	 */
	public static String convertFormatTel(String tel) {
		// Khai báo kiểu số điện thoại mới
		String newTel = "";
		// Cắt chuỗi
		String arr[] = tel.split("-");
		// Bắt đầu vòng lặp for
		for (int i = 0; i < arr.length; i++) {
			// Ghép các chuỗi con lại với nhau
			newTel += arr[i];
			// Kết thúc vòng lặp for
		}
		// Trả về kết quả
		return newTel;
		// Kết thúc phương thức
	}

	/**
	 * Phương thức dùng để : Chuyển formart của birhday lấy từ DB hiển thị lên màn hình adm003
	 * @param birthday : Ngày sinh nhật của user
	 * @return : newFormat của ngày sinh nhật
	 */
	public static String convertFormatBirthday(String data) {
		// Khởi tạo giá trị birthday sau khi convert bằng rỗng
		String newBirthday = "";
		// Chuyển data từ kiểu Date sang String
		String daString = data + "";
		// Chuyển format mới
		if (!daString.equals("null")) {
			newBirthday = daString.replace("-", "/");
			// Kết thúc if
		}
		// Trả về kết quả
		return newBirthday;
		// Kết thúc phương thức
	}
	
	// Kết thức class
}