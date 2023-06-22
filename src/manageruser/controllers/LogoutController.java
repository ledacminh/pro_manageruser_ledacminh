/**
 * Copyright (C) 2020 Luvina Software
 * LogoutController.java Mar 4, 2020 author Lê Minh 
 */
package manageruser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageruser.utils.Constant;

/**
 * Lớp này dùng để: Hủy sesstion và Logout màn hình hiện tại
 * @author: Lê Minh
 */
// Cấu hình đường dẫn cho servlet Logout màn hình
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Phương thức này dùng để: Di chuyển trang hiện tại đang thao tác đếnm màn hình ADM001
	 * @param req:  Là một đối tượng HttpServletRequest dùng để gửi yêu cầu từ phía client
	 * @param resp : Là một đối tượng HttpServletResponse dùng để phản hồi kết
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Khởi tạo 1 đối tượng sesion
		HttpSession session = req.getSession();
		// Lấy giá trị của sesion
		String name = (String) session.getAttribute(Constant.LOGIN_NAME);
		// So sánh giá trị lấy được của getValidSession và Constant.LOGIN_NAME
		if (Constant.LOGIN_NAME.equals(name)) {
			// hủy 1 session
			session.invalidate();
			// Chuyển đến trang ADM001
			req.getRequestDispatcher(Constant.ADM001).forward(req, resp);
			// Kết thúc lệnh if
		} else {
			// Chuyển đến trang thông báo lỗi
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc else
		}
		// kết thúc phương thức
	}
	// Kết thúc class
}
