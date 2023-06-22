/**
 * Copyright (C) 2020 Luvina Software
 * SuccessController.java Apr 7, 2020 author Lê Minh 
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
import manageruser.utils.MessageProperties;

/**
 * Xử lý các logic thông báo thành công và lỗi hệ thống
 * @param req :  yêu cầu client gửi lên sever
 * @param resp : dữ liệu sever gửi về cho client
 * @author: Lê Minh
 */
@WebServlet("/success.do")
public class SuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Khai báo tham số để lưu câu thông báo lỗi
		String message = "";
		// Bắt đầu khối try
		try {
			// Lấy giá trị từ req
			String action = req.getParameter(Constant.ACTION);
			// Kiểm tra nếu là trường hợp thêm mới thành công
			if (Constant.INSERT.equals(action)) {
				// Lấy câu thông báo lỗi thêm mới từ file message.properties
				message = MessageProperties.getString(Constant.MSG001);
				// Kiểm tra nếu là trường hợp update thành công
			} else if (Constant.UPDATE.equals(action)) {
				// Lấy câu thông báo lỗi update từ file message.properties
				message = MessageProperties.getString(Constant.MSG002);
				// Kiềm tra nếu là trường hợp delete thành công
			} else if (Constant.DELETE.equals(action)) {
				// Lấy câu thông báo lỗi update từ file message.properties
				message = MessageProperties.getString(Constant.MSG003);
			}
			// Set câu thông báo lỗi lên req
			req.setAttribute("message", message);
			// Di chuyển tới màn hình ADM006
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM006);
			requestDispatcher.forward(req, resp);
			// Kết thúc try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[SuccessController].[doGet]: " + e.getMessage());
			// Di chuyển tới màn hình thông báo lỗi
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc cath
		}
		// Kết thúc phương thức
	}
	// Kết thúc class
}
