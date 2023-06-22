/**
 * Copyright (C) 2020 Luvina Software
 * AddUserConfirmControllser.java Apr 1, 2020 author Lê Minh 
 */
package manageruser.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manageruser.entities.UserInfor;
import manageruser.logics.MstJapanLogic;
import manageruser.logics.TblUserLogic;
import manageruser.logics.impl.MstJapanLogicImpl;
import manageruser.logics.impl.TblUserLogicImpl;
import manageruser.utils.Constant;

/**
 * Lớp này dùng để: Chuyển dữ liệu lên màn hình xác nhận ADM004
 * @author: Lê Minh
 */
@WebServlet("/AddUserConfirm.do")
public class AddUserConfirmControllser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Khởi tạo đối tượng tblUserLogic
	private TblUserLogic tblUserLogic = new TblUserLogicImpl();
	//Khởi tạo đối tượng MstJapanLogic
	private MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
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
			// Lấy session
			HttpSession session = req.getSession();
			// Lấy giá trị của biến check trên session
			boolean check = (boolean) session.getAttribute(Constant.CHECK);
			// Hủy session
			session.removeAttribute(Constant.CHECK);
			// Kiểm tra nếu giá trị của biến check = tre
			if (check) {
				// Lấy giá trị key từ session
				String key = req.getParameter(Constant.KEY);
				// Lấy đối tượng userInfor từ session ứng với giá trị key
				UserInfor userInfor = (UserInfor) session.getAttribute(key);
				//Hủy session userInfor
				// Nếu tồn tại trình độ tiếng nhật lấy ra tên trình độ tiếng
				// nhật
				String nameLevel = Constant.EMPTY_STRING;
				//Kiểm tra nếu tồn tại trình độ tiếng nhật
				if (userInfor.getCodeLevel().length() > 0 ) {
					// Lấy ra tên trình độ tiếng nhật
					nameLevel = mstJapanLogic.getNameLevelByCodeLevel(userInfor.getCodeLevel());
					// Set giá trị cho trình độ tiếng nhật
					userInfor.setNameLevel(nameLevel);
				}
				// Set đối tượng userInfor lên req
				req.setAttribute(Constant.USERINFOR, userInfor);
				// Set giá trị key lên req
				req.setAttribute(Constant.KEY, key);
				// Di chuyển tới màn hình ADM004
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM004);
				requestDispatcher.forward(req, resp);
				// Ngược lại với check = false
			} else {
				// Di chuyển đến màn hình lỗi
				resp.sendRedirect(
						req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
				// kết thúc khối else
			}
			//Kết thúc khối try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[AddUserConfirmControllser].[doGet]: " + e.getMessage());
			// Nếu có lỗi thì điều hướng tới trang hiển thị lỗi
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc khối catch
		}
		// Kết thúc phương thức
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy session
		HttpSession session = req.getSession();
		// Khởi tạo tham số để kiểm tra tồn tại loginName = false
		boolean exitLoginName = false;
		// Khởi tạo tham số để kiểm tra tồn tại email = false
		boolean exitEmail = false;
		// bắt đầu khối try
		try {
			// Lấy giá trị của key trên sesion
			String key = req.getParameter(Constant.KEY);
			// Lấy đối tượng userInfor tương ứng với key
			UserInfor userInfor = (UserInfor) session.getAttribute(key);
			//Hủy session userInfor
			session.removeAttribute(key);
			// Lấy giá trị kiểm tra tồn tại loginName
			exitLoginName = tblUserLogic.isExistLoginName(userInfor.getLoginName());
			// Lấy giá trị kiêm tra tồn tại email
			exitEmail = tblUserLogic.isExistEmail(userInfor.getEmail());
			// Nếu email và loginName đều không tồn tại
			if (!exitEmail && !exitLoginName) {
				if (tblUserLogic.createUser(userInfor)) {
					// Di chuyển tới màn hình thông báo đã insert thành công
					resp.sendRedirect(req.getContextPath() + Constant.SUCCESS + "?" + Constant.ACTION + "=" + Constant.INSERT );
				} else {
					// Ngược lại di chuyển tới màn hình thông báo lỗi
					resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
				}
			} else {
				// Ngược lại di chuyển tới màn hình thông báo lỗi
				resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			}
			//Kết thúc khối try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[AddUserConfirmController].[doPost]: " + e.getMessage());
			// Nếu có lỗi thì điều hướng tới trang hiển thị lỗi
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc khối catch
		}
		//Kết thúc phương thức
	}
	// Kết thúc class
}
