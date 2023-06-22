/**
 * Copyright (C) 2020 Luvina Software
 * ValidateUserConfirmControllser.java Apr 11, 2020 author Lê Minh 
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
import manageruser.logics.TblDetailUserJapanLogic;
import manageruser.logics.TblUserLogic;
import manageruser.logics.impl.MstJapanLogicImpl;
import manageruser.logics.impl.TblDetailUserJapanLogicImpl;
import manageruser.logics.impl.TblUserLogicImpl;
import manageruser.utils.Constant;

/**
 * Lớp này dùng để validate userInfor trường hợp edit
 * @author: Lê Minh
 */
@WebServlet("/EditUserConfirm.do")
public class EditUserConfirmControllser  extends HttpServlet{
	private static final long serialVersionUID = 1L;
       /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Bắt đầu khối try
		try {
			// Lấy session
			HttpSession session = req.getSession();
			boolean check = (boolean) session.getAttribute(Constant.CHECK);
			session.removeAttribute(Constant.CHECK);
			// Nếu giá trị biến check = true
			if (check) {
				// Khởi tạo các đối tượng MstGroupLogic
				MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
				// Lấy giá trị key từ req
				String key = req.getParameter(Constant.KEY);
				// Khởi tao đối tượng userInfor
				UserInfor userInfor = new UserInfor();
				// Lấy đối tượng userInfor từ sesion
				userInfor = (UserInfor) session.getAttribute(key);
				// Khởi tạo giá trị nameLelvel = rỗng
				String nameLevel = Constant.EMPTY_STRING;
				// Kiểm tra nếu tôn trị trình độ tiếng nhật
				if (!Constant.EMPTY_STRING.equals(userInfor.getCodeLevel())) {
					// Lấy tên trình độ tiếng nhật tương ứng
					nameLevel = mstJapanLogic.getNameLevelByCodeLevel(userInfor.getCodeLevel());
					// Set giá trị cho thuộc tính nameLevel
					userInfor.setNameLevel(nameLevel);
				}
				// Set đối tượng userInfor lên req
				req.setAttribute(Constant.USERINFOR, userInfor);
				// Set giá trị biên case lên req
				req.setAttribute(Constant.KEY, key);
				// Di chuyển tới màn hình ADM004
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM004);
				requestDispatcher.forward(req, resp);
				// Kết thúc kiểm tra giá trị biến check
			} else {
				// Di chuyển đến màn hình thông báo lỗi
				resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" +  Constant.ACTION + "=" + Constant.ER015);
			}
			// Kết thúc khối try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[EditUserConfirmControllser].[doGet] " + e.getMessage());
			// Di chuyển đến màn hình thông báo lỗi
			resp.sendRedirect(
					req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc khối catch
		}
		// Kết thúc phương thức
	}
	
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Khởi tạo đối tượng tblUserLoglic
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		// Khởi tạo đối tượng TblDetailUserJapanLogic
		TblDetailUserJapanLogic tblDetailUserJapanLogic = new TblDetailUserJapanLogicImpl();
		// Lấy sesion
		HttpSession session = req.getSession();
		// Bắt đầu khối try
		try {
			// Lấy giá trị key từ req
			String key = req.getParameter(Constant.KEY);
			// Lấy đối tượng userInfor từ session
			UserInfor userInfor = (UserInfor) session.getAttribute(key);
			// Hủy session có tên là key
			session.removeAttribute(key);
			// Id của user
			int userId = userInfor.getUserId();
			// email của user
			String email = userInfor.getEmail();
			//Lấy kết quả kiểm tra userId
			int result = tblUserLogic.isExistUser(userId);
			System.out.println("dong 112 resulut = " + result);
			// Kiểm tra nếu tồn tại userId và giá trị của email hợp lệ
			if (result == Constant.RULE_USER && !tblUserLogic.isExistEmail(userId, email)) {
				// Kiểm tra tồn tại trình độ tiếng nhật
				boolean existcodeLevel = tblDetailUserJapanLogic.isExistCodeLevel(userId);
				// Kiểm tra trạng thái update
				boolean updateUserInfor = tblUserLogic.updateUserInfor(userInfor, existcodeLevel);
				// Nếu thành công thì hiển thị msg ở màn hình ADM006
				if (updateUserInfor) {
					// Di chuyển tới màn hình ADM006
					resp.sendRedirect(
							req.getContextPath() + Constant.SUCCESS + "?" + Constant.ACTION + "=" + Constant.UPDATE);
					// Ngược lại thì hiển msg ở mành hình lỗi
				} else {
					// Di chuyển tới màn hình SystemError
					resp.sendRedirect(
							req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
				}
				// Ngược lại kiểm tra userId và email
			} else {
				// Di chuyên tới màn hình lỗi
				resp.sendRedirect(
						req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			}
			// Kết thúc khối try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[EditUserConfirmControllser].[doPost] " + e.getMessage());
			// Di chuyển đến màn hình thông báo lỗi
			resp.sendRedirect(
					req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc catch
		}
		// Kêt thúc phương thức
	}
	// Kết thúc class
}
