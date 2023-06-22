/**
 * Copyright(C) 2020 Luvina Sorfware
 * Constant.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.utils;


/**
 * Lớp này dùng để khởi tạo các biến là hằng số
 * @author : Lê Minh
 */
public class Constant {
	// Đường dẫn đến các màn hình
	public static final String ADM001 = "views/jsp/ADM001.jsp";
	public static final String ADM002 = "views/jsp/ADM002.jsp";
	public static final String ADM003 ="views/jsp/ADM003.jsp";
	public static final String ADM004 ="views/jsp/ADM004.jsp";
	public static final String ADM006 = "views/jsp/ADM006.jsp";
	public static final String ADM005 = "views/jsp/ADM005.jsp";
	public static final String ERROR_PAGE_PATH = "views/jsp/system_error.jsp";
	
	//URL mapping với các controller
	public static final String ERROR_PAGE = "/error";
	public static final String ADD_USER_CONFIRM = "/AddUserConfirm.do";
	public static final String ADD_USER_VALIDATE =  "/AddUserValidate.do";
	public static final String EDIT_USER_CONFIRM = "/EditUserConfirm.do";
	public static final String SUCCESS =  "/success.do";
	public static final String LOGIN_PAGE = "/login.do";
	public static final String LIST_USER = "/listUser.do";
	
	//Đường dẫn đến các file.propertiess
	public static final String PROPERTIES_DATABASE_PATH = "database.properties";
	public static final String PROPERTIES_MESSAGE_PATH = "message.properties";
	public static final String PROPERTIES_MESSAGE_ERROR_PATH = "messageError.properties";
	public static final String CONFIG_PROPERTIES_PATH = "config.properties";
	
	//Sort mặc định tăng dần
	public static final String SORT_TYPE_DEFAULT = "";
	public static final String SORT_BY_FULL_NAME_DEFAULT = "ASC";
	public static final String SORT_BY_CODE_LEVEL_DEFAULT = "ASC";
	public static final String SORT_BY_END_DATE_DEFAULT = "DESC";
	
	//Sort giảm dần
	public static final String SORT_BY_FULL_NAME_OTHER = "DESC";
	public static final String SORT_BY_CODE_LEVEL_OTHER = "DESC";
	public static final String SORT_BY_END_DATE_OTHER = "ASC";
	
	//Các trường sort ưu tiên
	public static final String SORT_TYPE_FULL_NAME = "full_name";
	public static final String SORT_TYPE_CODE_LEVEL = "code_level";
	public static final String SORT_TYPE_END_DATE = "end_date";
	
	//Tên các trường sort
	public static final String SORT_BY_FULL_NAME = "sortByFullName";
	public static final String SORT_BY_CODE_LEVEL = "sortByCodeLevel";
	public static final String SORT_BY_END_DATE = "sortByEndDate";
	
	// Tên action
	public static final String ACTION = "action";
	public static final Object ACTION_PAGING = "paging";
	public static final Object ACTION_SORT = "sort";
	public static final String ACTION_SUBMIT = "submit";
	public static final String ACTION_BACK = "back";
	public static final String ACTION_INSERT_SUCCESS = "success";
	public static final String INSERT = "insert";
	public static final String DELETE = "delete";
	public static final String UPDATE = "update";
	
	// Tên các mã lỗi
	public static final String MSG001 = "MSG001";
	public static final String MSG002 = "MSG002";
	public static final String MSG003 = "MSG003";
	public static final String MSG004 = "MSG004";
	public static final String MSG005 = "MSG005";
	public static final String ER013 = "ER013";
	public static final String ER015 = "ER015";
	public static final String ER020 = "ER020";
	
	// format kiểm tra các hạng mục
	public static final String FORMAT_LOGIN_NAME = "^[a-zA-Z_][a-zA-Z_0-9]+";
	public static final String FORMAT_KANA = "[ァ-ﾝﾞ]+";
	public static final String FORMAT_EMAIL = ".+[@]{1}.+[.]{1}.+";
	public static final String FORMAT_TEL = "\\d{1,4}-\\d{1,4}-\\d{1,4}";
	public static final String CHECK_HALF_SIZE = "^[0-9]+$";
	
	//Các chuỗi hay sử dụng
	public static final String USERINFOR = "userInfor";
	public static final String GROUP_ID = "groupId";
	public static final String FULL_NAME = "fullName";
	public static final String FULL_NAME_KANA = "fullNameKana";
	public static final String EMAIL = "email";
	public static final String TEL = "tel";
	public static final String PASSWORD = "password";
	public static final String CONFIRM_PASSWORD = "confirmPassword";
	public static final String CODE_LEVEL = "codeLevel";
	public static final String TOTAL = "total";
	public static final String USER_ID = "userId";
	public static final String SORT_TYPE = "sortType";
	public static final String CURRENT_PAGE = "currentPage";
	public static final String TOTAL_PAGE = "totalPage";
	public static final String LIMIT = "LIMIT";
	public static final String LOGIN_NAME = "loginName";
	public static final String PASS_WORD = "password";
	public static final String BEGIN_YEAR = "BEGIN_YEAR";
	public static final String END_YEAR = "END_YEAR";
	public static final String CODE_LEVEL_DEFAULT = "default";
	public static final String CODE_ERROR_MESSAGE = "CODE_ERROR_MESSAGE";
	public static final String EMPTY_STRING = "";
	public static final String LIST_ERROR = "listError";
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String CHECK = "check";
	public static final String KEY = "key";
	public static final String MESSAGE_ERROR = "MESSAGE_ERROR";
	public static final String LIST_USER_INFOR = "listUserInfor";
	public static final String LIST_MSTGROUP = "listMstGroup";
	public static final String LIST_PAGING = "listPaging";
	public static final String UTF8 = "UTF-8";
	
    //Các giá trị số nguyên hay sử dụng
	public static final int RULE_ADMIN = 0;
	public static final int RULE_USER = 1;
	public static final int CURRENT_PAGE_DEFAULT = 1;
	public static final int OFFSET_DEFAULT = 0;
	public static final int GROUP_ID_DEFAULT = 0;
	public static final int LIMIT_RECORD = 5;
	public static final int MAX_LOGIN = 15;
	public static final int MIN_LOGIN = 4;
	public static final int MAX_DEFAULT = 255;
	public static final int START_DEFAULT = 1;
	public static final int MAX_PASSWORD = 15;
	public static final int MIN_PASSWORD = 5;
	public static final int MAX_TOTAL = 3;
	public static final int MIN_TOTAL = 1;
	public static final int LIMIT_PAGE = 3;
	public static final int USER_ID_DEFAULT = 0;
	// Kết thúc class
	
	
}
