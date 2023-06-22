/**
 * Copyright(C) 2020 Luvina Sorfware
 * LoginController.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageruser.utils.Constant;
import manageruser.validates.ValidateUser;

/**
 * Phương thức dùng để: Đăng nhập mành hình ADM001
 * 
 * @author: Lê Minh
 */
// Cấu hình đường dẫn
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Phương thức này dùng để: Lấy dữ liệu từ người dùng nhập, sau đó xử lí để
	 * đăng nhập màn hình ADM001
	 * @param req: Là một đối tượng HttpServletRequest dùng để gửi yêu cầu từ phía client
	 * @param resp  : Là một đối tượng HttpServletResponse dùng để phản hồi kết quả từ Server - client
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Bắt đầu khối try
		try {
			// khởi tạo mảng lỗi
			List<String> listError = null;
			// Lấy thông tin đăng nhập loginName từ Request
			String loginName = req.getParameter(Constant.LOGIN_NAME);
			// Lấy thông tin đăng nhập password từ Request
			String password = req.getParameter(Constant.PASS_WORD);
			// Validate thông tin đăng nhập
			ValidateUser validateUser = new ValidateUser();
			// lưu lỗi vào listErrr
			listError = validateUser.validateLogin(loginName, password);
			// nếu có lỗi
			if (listError.size() > 0) {
				// Set danh sách thông báo lỗi lên Request
				req.setAttribute(Constant.LIST_ERROR, listError);
				// Set loginName lên Request
				req.setAttribute(Constant.LOGIN_NAME, loginName);
				// forward đến ADM001
				RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.ADM001);
				// chuyển tiếp yêu cầu
				dispatcher.forward(req, resp);
				// Ngược lại nếu không có lỗi
			} else {
				// Khởi tạo session
				HttpSession session = req.getSession();
				// Set thông tin đăng nhập lên session
				session.setAttribute(Constant.LOGIN_NAME, Constant.LOGIN_NAME);
				// Chuyển đến ListUserController
				resp.sendRedirect(req.getContextPath() + Constant.LIST_USER);
			}
			// Kết thúc khối try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[LoginController].[doPost] " + e.getMessage());
			// Chuyển nội dung lỗi đến trang system_error.jsp
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc catch
		}
		// kết thúc phương thức
	}
	// kết thúc phương thức
}
