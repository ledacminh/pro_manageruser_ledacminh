/**
 * Copyright (C) 2020 Luvina Software
 * ViewDetailUserController.java Apr 10, 2020 author Lê Minh 
 */
package manageruser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageruser.entities.UserInfor;
import manageruser.logics.TblUserLogic;
import manageruser.logics.impl.TblUserLogicImpl;
import manageruser.utils.Constant;

/**
 * LỚp này có chức năng hiển thị chi tiết thông tin của user
 * @author: Lê Minh
 */
@WebServlet("/ViewDetailUser.do")
public class ViewDetailUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Khởi tạo đối tượng tblUserLogic
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		// Khởi tạo đối tượng userInfro
		UserInfor userInfor = new UserInfor();
		// Bắt đầu khối try
		try {
			//Lấy giá trị userId từ req
			int userId = Integer.parseInt(req.getParameter("userId"));
			//Lấy kết quả kiểm tra userId
			int result = tblUserLogic.isExistUser(userId);
			// Kiểm tra nếu tồn tại id
			if (result == Constant.RULE_USER) {
				// Lấy đối tượng userInfor tương ứng trong list
				userInfor = tblUserLogic.getUserInforById(userId);
				// Set giá trị userInfor lên req
				req.setAttribute(Constant.USERINFOR, userInfor);
				// Di chuyển đến màn hình ADM005
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM005);
				requestDispatcher.forward(req, resp);
				// Ngược userId là của admin
			} else if (result == Constant.RULE_ADMIN) {
				// Di chuyển tới màn hình hiển thị thông báo lỗi không tồn tại user
				resp.sendRedirect(
						req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER013);
				//Ngược lại di chuyển tới màn hình hiển thị thông báo lỗi không tồn tại user
			} else {
				resp.sendRedirect(
						req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER013);
			}
			// Kết thúc khối try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[ViewDetailUserController].[doGet] " + e.getMessage());
			/// Di chuyển đến trang thông báo lỗi
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" +  Constant.ER015);
			// Kết thúc catch
		}
		// Kết thúc phương thức
	}
	// Kết thúc class
}
