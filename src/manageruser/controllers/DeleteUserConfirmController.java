/**
 * Copyright (C) 2020 Luvina Software
 * DeleteUserConfirmController.java Apr 14, 2020 author Lê Minh 
 */
package manageruser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageruser.logics.TblUserLogic;
import manageruser.logics.impl.TblUserLogicImpl;
import manageruser.utils.Common;
import manageruser.utils.Constant;

/**
 * Lớp này dùng để xóa đối tượng userInfor
 * @author: Lê Minh
 */
@WebServlet("/DeleteUserConfirm.do")
public class DeleteUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Bắt đầu khối try
		try {
			// Khởi tạo đối tượng tblUserLogic
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			// Lấy userId từ request
			int userId = Common.convertStringToInt(req.getParameter(Constant.USER_ID), 0);
			// Lấy kết quả trả về từ phương thức kiểm tra id của user
			int result = tblUserLogic.isExistUser(userId);
			// Kết quả trả về bằng 1 là admin
			if (result == Constant.RULE_ADMIN) {
				// Hiển thị thông báo Không thể xóa ở màn hình hiển thị lỗi
				resp.sendRedirect(
						req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER020);
				// Kết quả trả về bằng 0 là user
			} else if (result == Constant.RULE_USER) {
				// Goi hàm deleteUserInfor Kiểu trả về là boolean
				boolean check = tblUserLogic.deleteUserInfor(userId);
				// Nếu kết quả là true thì hiển thị thông báo lỗi delete thành
				// công
				if (check) {
					// Di chuyển tới màn hình ADM006
					resp.sendRedirect(req.getContextPath() + Constant.SUCCESS + "?" + Constant.ACTION + "=" + Constant.DELETE);
					// Kết thúc if kiểm tra deleteUser
				} else {
					// Ngược lại thì hiển thị thông báo lỗi ở mành hình lỗi
					resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
				}
				// Kết quả trả về = 2 là không tồn tại user
			} else {
				// Ngược lại thì hiển thị thông báo không tồn tại user ở màn
				// hình thông báo lỗi
				resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION  + "=" + Constant.ER013);
			}
			// Kết thúc khối try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[DeleteUserConfirmController].[doPost]: " + e.getMessage());
			// Nếu có lỗi thì điều hướng tới trang hiển thị lỗi
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc khối catch
		}
		// Kết thúc phương thức
	}
	// Kết thúc class
}
