/**
 * Copyright (C) 2020 Luvina Software
 * SystemErrosController.java Mar 26, 2020 author Lê Minh 
 */
package manageruser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageruser.utils.Constant;
import manageruser.utils.MessageErrorProperties;

/**
 * Phương thức dùng để: Điều khiển đến trang hiển thị thông báo lỗi
 * @param req:  Là một đối tượng HttpServletRequest dùng để gửi yêu cầu từ phía client
 * @param resp : Là một đối tượng HttpServletResponse dùng để phản hồi kết quả từ Server - client
 * @author: Lê Minh
 */
@WebServlet("/error")
public class SystemErrosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Bắt đầu khối try
		try {
			// Khởi tạo tham chứa nội dung lỗi
			String errorMessage = Constant.EMPTY_STRING;
			// Lấy giá trị của action từ req
			String action = req.getParameter(Constant.ACTION);
			// Nếu là mã lỗi ER013
			if (action.equals(Constant.ER013)) {
				// Lấy giá trị mã lỗi từ fil messageError.properties
				errorMessage = MessageErrorProperties.getString(Constant.ER013);
				//Nêu là mã lỗi ER020
			} else if (action.equals(Constant.ER020)) {
				// Lấy giá trị mã lỗi từ fil messageError.properties
				errorMessage = MessageErrorProperties.getString(Constant.ER020);
				//Nếu là mã lỗi ER015
			} else if(action.equals(Constant.ER015)) {
				// Lấy giá trị mã lỗi từ fil messageError.properties
				errorMessage = MessageErrorProperties.getString(Constant.ER015);
			}
			// Set giá trị của thông báo lỗi lên req
			req.setAttribute("errorMessage", errorMessage);
			// Di chuyển tới màn hình system_erro.jsp
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ERROR_PAGE_PATH);
			requestDispatcher.forward(req, resp);
			// Kết thúc try
		} catch (Exception e) {
			System.out.println("[SystemErrosController].[doGet] " + e.getMessage());
			// kết thúc khối catch
		}
		// kết thúc phương thức
	}
	// Kết thúc class
}
