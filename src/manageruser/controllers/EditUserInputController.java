/**
 * Copyright (C) 2020 Luvina Software
 * EditInputUserController.java Apr 11, 2020 author Lê Minh 
 */
package manageruser.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageruser.entities.MstGroup;
import manageruser.entities.MstJapan;
import manageruser.entities.UserInfor;
import manageruser.logics.MstGroupLogic;
import manageruser.logics.MstJapanLogic;
import manageruser.logics.TblUserLogic;
import manageruser.logics.impl.MstGroupLogicImpl;
import manageruser.logics.impl.MstJapanLogicImpl;
import manageruser.logics.impl.TblUserLogicImpl;
import manageruser.utils.Common;
import manageruser.utils.ConfigProperties;
import manageruser.utils.Constant;
import manageruser.validates.ValidateUser;

/**
 * Lớp này dùng để hiển thị dữ liệu từ màn hình ADM005 sang ADM003
 * @author: Lê Minh
 */

@WebServlet("/EditInputUser.do")
public class EditUserInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Khởi tạo đối tượng mstGroupLogic
	private MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
	// Khởi tạo đối tượng TblUserLogic
	private TblUserLogic tblUserLogic = new TblUserLogicImpl();
	//Khởi tạo giá trị biên check = flase
	boolean check = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Bắt đầu khối try
		try {
			// Lấy giá trị userId của user từ req
			int userId = Common.convertStringToInt(req.getParameter("userId"), 0);
			//Lấy kết quả kiểm tra userId
			int result = tblUserLogic.isExistUser(userId);
			// Kiểm tra nếu tồn tại id
			if (result == Constant.RULE_USER) {
				// Lấy đối tượng userInfor tương ứng trong list
				UserInfor userInfor = new UserInfor();
				// Gọi phương thức setDataLogic
				setDataLogicADM003(req);
				// Lấy giá trị của đối tượng userInfro
				userInfor = getDefaultValue(req);
				// Set giá trị userInfor lên req
				if (userInfor.getCodeLevel() == null || Constant.EMPTY_STRING.equals(userInfor.getCodeLevel())) {
					// Lấy năm hiện tại
					int currentYear = Common.getCurrentYear();
					// Lấy tháng hiện tại
					int currentMonth = Common.getCurrentMonth();
					// Lấy ngày hiện tại
					int currentDay = Common.getCurrentDay();
					// Giá trị này bắt đầu
					String startDate = currentYear + "/" + currentMonth + "/" + currentDay;
					// Khởi tạo ngày hết hạn
					String endDate = (currentYear + 1) + "/" + currentMonth + "/" + currentDay;
					// Set gia trị cho thuộc tính startDate
					userInfor.setStartDate(startDate);
					// Set giá trị cho thuộc tính endDate
					userInfor.setEndDate(endDate);
					// Kết thúc if kiểm tra tồn tại trinh độ tiếng nhật
				}
				// Set đối tương userInfor lên req
				req.setAttribute(Constant.USERINFOR, userInfor);
				// Di chuyển đến màn hình ADM003
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM003);
				requestDispatcher.forward(req, resp);
				// Ngược lại nếu không tồn tại Id
			} else {
				// Di chuyển tới màn hình hiển thị thông báo lỗi
				resp.sendRedirect(
						req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER013);
			}
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[EditInputUserController].[doGet] " + e.getMessage());
			// Di chuyển tới màn hình ADM003
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc catch
		}
		// Kết thúc phương thức
	}

	/**
	 * @param req : request 
	 * @return : Trả về 1 đối tượng userInfor
	 * @throws SQLException : Lỗi thao tác với DB
	 * @throws ClassNotFoundException : Lỗi không tìm thấy thư viện
	 * @throws IOException : Lỗi đọc file
	 */
	public UserInfor getDefaultValue(HttpServletRequest req)
			throws ClassNotFoundException, SQLException, IOException {
		// Khởi tạo đối tượng userInfor
		UserInfor userInfor = new UserInfor();
		// Lấy giá trị của action
		String action = req.getParameter(Constant.ACTION);
		// Nếu action bằng null
		if (action == null) {
			// Lấy userId
			int userId = Common.convertStringToInt(req.getParameter(Constant.USER_ID), 0);
			// Lấy giá trị của userInfor theo userId
			userInfor = tblUserLogic.getUserInforById(userId);
			// Ngược lại kiểm tra action bằng submit
		} else if (action.equals(Constant.ACTION_SUBMIT)) {
			// Lấy giá trị userId của user từ req
			int userId = Common.convertStringToInt(req.getParameter(Constant.USER_ID), 0);
			// Lấy Id của group
			int groupId = Common.convertStringToInt(req.getParameter(Constant.GROUP_ID), 0);
			// Kiểm tra tồn tại groupId
			String groupName = mstGroupLogic.getGroupNameById(groupId);
			// Lấy giá trị loginName
			String loginName = req.getParameter(Constant.LOGIN_NAME);
			// Lấy giá trị fullName
			String fullName = req.getParameter(Constant.FULL_NAME);
			// Lấy giá trị fullNameKana
			String fullNameKana = req.getParameter(Constant.FULL_NAME_KANA);
			// Lấy giá trị ngày sinh nhật
			String birthday = req.getParameter("yearOfBirthday") + "/" + req.getParameter("monthOfBirthday") + "/"
					+ req.getParameter("dayOfBirthday");
			// Lấy giá trị email
			String email = req.getParameter(Constant.EMAIL);
			// Lấy giá trị số điện thoại
			String tel = req.getParameter(Constant.TEL);
			// Lấy giá trị password
			String password = req.getParameter(Constant.PASS_WORD);
			// Lấy giá trị xác nhận mật khẩu
			String confirmPassword = req.getParameter(Constant.CONFIRM_PASSWORD);
			// Lấy giá trị trình độ tiếng nhật
			String codeLevel = req.getParameter(Constant.CODE_LEVEL);
			// Lấy giá trị ngày cấp chứng chỉ
			String startDate = req.getParameter("beginYear") + "/" + req.getParameter("beginMonth") + "/"
					+ req.getParameter("beginDay");
			// Lấy giá trị ngày hết hạn
			String endDate = req.getParameter("endYear") + "/" + req.getParameter("endMonth") + "/"
					+ req.getParameter("endDay");
			// Lấy giá trị hạng mục total
			String total = req.getParameter(Constant.TOTAL);
			// Set giá trị cho thuộc tính userId
			userInfor.setUserId(userId);
			// Set giá trị cho thuộc tính loginName
			userInfor.setLoginName(loginName);
			// Set giá trị cho thuộc tính groupId
			userInfor.setGroupId(groupId);
			// Set giá trị cho thuộc tính groupName
			userInfor.setGroupName(groupName);
			// Set giá trị cho thuộc tính fullName
			userInfor.setFullName(fullName);
			// Set giá trị cho thuộc tính fullNameKana
			userInfor.setFullNameKana(fullNameKana);
			// Set giá trị cho thuộc tính birthDay
			userInfor.setBirthday(birthday);
			// Set giá trị cho thuộc tính email
			userInfor.setEmail(email);
			// Set giá trị cho thuộc tính tel
			userInfor.setTel(tel);
			// Set giá trị cho thuộc tính password
			userInfor.setPassword(password);
			// Set giá trị cho thuộc tính confirmPassword
			userInfor.setConfirmPassword(confirmPassword);
			// Set giá trị cho thuộc tính codeLevel
			userInfor.setCodeLevel(codeLevel);
			// Set giá trị cho thuộc tính startDate
			userInfor.setStartDate(startDate);
			// Set giá trị cho thuộc tính endDate
			userInfor.setEndDate(endDate);
			// Set giá trị cho thuộc tính total
			userInfor.setTotal(total);
			// Set giá trị cho thuộc tính slat
			userInfor.setSalt(Common.getSalt());
			// Kiểm tra nếu là action back
		} else if (action.equals(Constant.ACTION_BACK)) {
			// Lấy session
			HttpSession session = req.getSession();
			// Lấy giá trị key từ req
			String key = req.getParameter(Constant.KEY);
			// Lấy đối tượng userInfor từ session
			userInfor = (UserInfor) session.getAttribute(key);
		}
		// Trả về kết quả
		return userInfor;
		// kết thúc phương thức
	}

	/**
	 * Phương thức dùng để: Set các giá trị default lên các hạng mục selectbox
	 * trên màn hình ADM003
	 * @param req : Là một đối tượng HttpServletRequest dùng để gửi yêu cầu từ phía client
	 * @throws SQLException : Lỗi thao tác SQL
	 * @throws ClassNotFoundException :lỗi không tìm thấy thư viện
	 */
	private void setDataLogicADM003(HttpServletRequest req)
			throws ClassNotFoundException, SQLException , IOException {
		// Khởi tạo đối tượng MstGroupLogic
		MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
		// Khởi tạo đối tượngMstJapanLogic
		MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
		// Lấy danh sách listMstGroup
		List<MstGroup> listMstGroup = mstGroupLogic.getAllMstGroup();
		// Lấy danh sách listMstJapan
		List<MstJapan> listMstJapan = mstJapanLogic.getAllMstJapan();
		// Lấy giá trị năm bắt đầu trong file config.properties
		int beginYear = Common.convertStringToInt(ConfigProperties.getString("BEGIN_YEAR"), 0);
		// Lấy danh sách listYearCurrently
		List<Integer> listYearCurrently = Common.getListYear(beginYear, Common.getCurrentYear());
		// lấy danh dách ListYearNext
		List<Integer> listYearNext = Common.getListYear(beginYear, Common.getCurrentYear() + 1);
		// Lấy danh sách các tháng để hiển thị giá trị lên select box tháng trên
		List<Integer> listMonth = Common.getListMonth();
		// Lấy danh sách các ngày
		List<Integer> listDay = Common.getListDay();
		// Set giát trị của listMstGroup lên resquets
		req.setAttribute("listMstGroup", listMstGroup);
		// Set giát trị của listMstJapan lên resquets
		req.setAttribute("listMstJapan", listMstJapan);
		// Set giát trị của listyearCurrently lên resquets
		req.setAttribute("listYearCurrently", listYearCurrently);
		// Set giát trị của listYearNext lên resquets
		req.setAttribute("listYearNext", listYearNext);
		// Set giát trị của listMonth lên resquets
		req.setAttribute("listMonth", listMonth);
		// Set giát trị của listDay lên resquets
		req.setAttribute("listDay", listDay);
		// Kết thúc phương thức
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Bắt đầu khối try
		try {
			// Gán giá trị cho biến kiểm tra = true
			check = true;
			// Lấy sesion
			HttpSession session = req.getSession();
			// Khởi tạo danh sánh lỗi
			List<String> listValidateUserInfor = new ArrayList<String>();
			// Khởi tạo đối tượng validateUser
			ValidateUser validateUser = new ValidateUser();
			// Lấy giá trị của đối tượng userInfor từ màn hình ADM003
			UserInfor userInfor = getDefaultValue(req);
			// Kiểm tra validate đối twowongwng userInfor và trả về ds lỗi
			listValidateUserInfor = validateUser.validateUserInfor(userInfor);
			// Nếu có lỗi
			if (listValidateUserInfor.size() > 0) {
				// Gửi danh sách lỗi lên req
				req.setAttribute("listError", listValidateUserInfor);
				// Set Data logic
				setDataLogicADM003(req);
				// Gửi đối tượng userInfro lên req
				req.setAttribute(Constant.USERINFOR, userInfor);
				// Gửi dữ liệu đến màn hình ADM003
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM003);
				requestDispatcher.forward(req, resp);
				// Ngược lại nếu không có lõi
			} else {
				// Lấy giá trị key
				String key = Common.getValueKey();
				// Set đối tượng userInfor lên sesion
				session.setAttribute(key, userInfor);
				// Set giá trị biên check lên session
				session.setAttribute(Constant.CHECK, check);
				// Chuyển hướng tới AddUserConfirmController
				resp.sendRedirect(req.getContextPath() + Constant.EDIT_USER_CONFIRM + "?" + Constant.KEY + "=" + key);
				// Kết thúc if
			}
			// Kết thúc khối try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[EditInputUserController].[doPost] " + e.getMessage());
			resp.sendRedirect(
					req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc catch
		}
		// Kết thúc phương thức
	}
//Kết thúc class
}
