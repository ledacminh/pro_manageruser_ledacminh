/**
 * Copyright (C) 2020 Luvina Software
 * ListUserController.java Mar 17, 2020 author Lê Minh 
 */
package manageruser.controllers;

import java.io.IOException;
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
import manageruser.entities.UserInfor;
import manageruser.logics.MstGroupLogic;
import manageruser.logics.TblUserLogic;
import manageruser.logics.impl.MstGroupLogicImpl;
import manageruser.logics.impl.TblUserLogicImpl;
import manageruser.utils.Common;
import manageruser.utils.ConfigProperties;
import manageruser.utils.Constant;
import manageruser.utils.MessageProperties;

/**
 * Lớp này dủng để gửi thông tin user lên trang ADM002.jsp
 * @author: Lê Minh
 */
@WebServlet("/listUser.do")
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Phương thức này dùng để: Gửi và nhận dữ liệu lên trang ADM002.jsp
	 * @param req:  Là một đối tượng HttpServletRequest dùng để gửi yêu cầu từ  phía client
	 * @param resp : Là một đối tượng HttpServletResponse dùng để phản hồi kết  quả về phía client
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Bắt đầu khối try
		try {
			// Lấy session hiện tại
			HttpSession session = req.getSession();
			// Khởi tạo đối tượng TblUserLogicImpl()
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			// Khởi tạo đối tượng MstGroupLogicImpl()
			MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
			// Khởi tạo danh sách listUserInfor để lưu danh sách đối tượng
			// UserInfor
			List<UserInfor> listUserInfor = new ArrayList<UserInfor>();
			// Khởi tạo listPaing để lưu lưu danh sách trang
			List<Integer> listPaging = new ArrayList<Integer>();
			// Khởi tạo listMstGroup để lưu thông tin danh sách của đối tượng
			// MstGroup
			List<MstGroup> listMstGroup = mstGroupLogic.getAllMstGroup();
			// Kiểu sort ưu tiên bằng rỗng
			String sortType = Constant.SORT_TYPE_DEFAULT;
			// Mặc định sắp xếp tăng dần theo fullName
			String sortByFullName = Constant.SORT_BY_FULL_NAME_DEFAULT;
			// Mặc định sắp xếp tăng dần theo code_level
			String sortByCodeLevel = Constant.SORT_BY_CODE_LEVEL_DEFAULT;
			// Mặc định sắp xếp giảm dần theo ngày hết hạn
			String sortByEndDate = Constant.SORT_BY_END_DATE_DEFAULT;
			// Gán fullName ban đầu bằng rỗng
			String fullName = Constant.EMPTY_STRING;
			// Bắt đầu khối if
			// Nếu giá trị lấy về từ ADM002.jsp khác null
			if (req.getParameter(Constant.FULL_NAME) != null) {
				fullName = req.getParameter(Constant.FULL_NAME);
				// Kết thúc if
			}
			// Lấy Id của group từ vùng điều kiện tìm kiếm.
			int groupId = Common.convertStringToInt(req.getParameter(Constant.GROUP_ID), Constant.GROUP_ID_DEFAULT);
			// Lấy tổng số page hiển thị trong 1 vùng page
			int limitPage = Common.getLimitPage();
			// Lấy số bản ghi tối đa hiên thị trong 1 trang
			int limitRecord = Common.convertStringToInt(ConfigProperties.getString("LIMIT_RECORD"), 0);
			// Trang hiển thị mặc định
			int currentPage = Constant.CURRENT_PAGE_DEFAULT;
			// vi trị bản ghi bắt đầu hiện thị trong 1 trang
			int offset = Constant.OFFSET_DEFAULT;
			// Lấy name của action
			String action = req.getParameter(Constant.ACTION);
			// Kiểm tra giá trị action khác null, trường hợp giá trị action
			// = sort or paging
			if (action != null) {
				if (action.equals(Constant.ACTION_SORT) || action.equals(Constant.ACTION_PAGING)) {
					// Lấy giá trị sắp xếp ưu tiên
					sortType = req.getParameter(Constant.SORT_TYPE);
					// Kiểm tra nếu đúng sắp xếp ưu tiên theo full_name
					if (Constant.SORT_TYPE_FULL_NAME.equals(sortType)) {
						// Lấy kiểu xếp theo full_name
						sortByFullName = req.getParameter(Constant.SORT_BY_FULL_NAME);
						// Kiểm tra nếu đúng sắp xếp ưu tiên theo code_level
					} else if (Constant.SORT_TYPE_CODE_LEVEL.equals(sortType)) {
						// Lấy kiểu sắp xếp theo code_level
						sortByCodeLevel = req.getParameter(Constant.SORT_BY_CODE_LEVEL);
						// Kiểm tra sắp nếu đúng sắp xếp theo end_date
					} else if (Constant.SORT_TYPE_END_DATE.equals(sortType)) {
						// Lấy kiểu sắp xếp theo end_date
						sortByEndDate = req.getParameter(Constant.SORT_BY_END_DATE);
					}
					// Lấy số trang hiện tại
					currentPage = Common.convertStringToInt(req.getParameter(Constant.CURRENT_PAGE), currentPage);
					// Lấy vị trí của bản ghi đầu tiên hiển thị trong 1 page
					offset = Common.getOffset(currentPage, limitRecord);
					// Kiểm tra nếu đúng giá trị của action back
				} else if (action.equals(Constant.ACTION_BACK)) {
					// lấy giá trị của ô textbox hiên tại
					fullName = (String) session.getAttribute(Constant.FULL_NAME);
					// Lấy groupId hiên tại
					groupId = (int) session.getAttribute(Constant.GROUP_ID);
					// Lấy vị trí trang hiện tại
					currentPage = (int) session.getAttribute(Constant.CURRENT_PAGE);
					// Lấy vị trí của bản ghi đầu tiên hiển thị trong 1 page
					offset = Common.getOffset(currentPage, limitRecord);
					// Lấy giá trị sắp xếp ưu tiên hiện tại
					sortType = (String) session.getAttribute(Constant.SORT_TYPE);
					// Kiểm tra nếu đúng sắp xếp ưu tiên theo full_name
					if (Constant.SORT_TYPE_FULL_NAME.equals(sortType)) {
						// Lấy kiểu xếp theo full_name
						sortByFullName = (String) session.getAttribute(Constant.SORT_BY_FULL_NAME);
						// Ngược lại, Kiểm tra nếu đúng sắp xếp ưu tiên theo
						// code_level
					} else if (Constant.SORT_TYPE_CODE_LEVEL.equals(sortType)) {
						// Lấy kiểu sắp xếp theo code_level
						sortByCodeLevel = (String) session.getAttribute(Constant.SORT_BY_CODE_LEVEL);
						// Ngược lại, Kiểm tra sắp nếu đúng sắp xếp theo
						// end_date
					} else if (Constant.SORT_TYPE_END_DATE.equals(sortType)) {
						// Lấy kiểu sắp xếp theo end_date
						sortByEndDate = (String) session.getAttribute(Constant.SORT_BY_END_DATE);
						// Kết thúc khối lệnh else if kiểm tra sắp xếp ưu
						// tiên theo end_date
					}
					// Kết thúc kiểm tra action back
				}
				// Kết thúc kiểm tra giá trị của action
			}
			// Lấy giá trị tổng số user
			int totalUser = tblUserLogic.getTotalUsers(groupId, fullName);
			//Khởi tạo tổng số page = 0;
			int totalPage = 0;
			//Kiểm tra nếu tổng số user > 0
			if (totalUser > 0) {
				// Lấy danh sách UserInfor thỏa mãn
				listUserInfor = tblUserLogic.getListUsers(offset, limitRecord, groupId, fullName, sortType,
						sortByFullName, sortByCodeLevel, sortByEndDate);
				// Lấy tổng số page
				totalPage = Common.getTotalPage(totalUser, limitRecord);
				// lấy giá trị paging
				listPaging = Common.getListPaging(totalUser, limitRecord, currentPage);
				// Nếu không có user thì hiện thị câu thông báo lỗi MSG005
			} else {
				// Lấy câu thông báo lỗi MSG005 từ file message.properties
				String MSG005 = MessageProperties.getString(Constant.MSG005);
				// Set giá trị của câu thông báo lên req 
				req.setAttribute(Constant.MSG005, MSG005);
			}
			// Set giá trị của trang hiên tại lên request
			req.setAttribute(Constant.CURRENT_PAGE, currentPage);
			// Set giá trị của limitPage lên request
			req.setAttribute("limitPage", limitPage);
			// Set giá trị của totalPage lên request
			req.setAttribute(Constant.TOTAL_PAGE, totalPage);
			// Set giá trị của sortType lên request
			req.setAttribute(Constant.SORT_TYPE, sortType);
			// Set giá trị của sortByFullName lên request
			req.setAttribute(Constant.SORT_BY_FULL_NAME, sortByFullName);
			// Set giá trị của sortByCodeLevel lên request
			req.setAttribute(Constant.SORT_BY_CODE_LEVEL, sortByCodeLevel);
			// Set giá trị của sortByEndDate lên request
			req.setAttribute(Constant.SORT_BY_END_DATE, sortByEndDate);
			// Set giá trị của listUserInfor lên request
			req.setAttribute(Constant.LIST_USER_INFOR, listUserInfor);
			// Set giá trị của listMstGroup lên request
			req.setAttribute(Constant.LIST_MSTGROUP, listMstGroup);
			// Set giá trị của listPaging lên request
			req.setAttribute(Constant.LIST_PAGING, listPaging);
			// Set giá trị của fullName lên session
			session.setAttribute(Constant.FULL_NAME, fullName);
			// Set giá trị của groupId lên session
			session.setAttribute(Constant.GROUP_ID, groupId);
			// Set giá trị của currentPage lên session
			session.setAttribute(Constant.CURRENT_PAGE, currentPage);
			// Set giá trị của sortType lên session
			session.setAttribute(Constant.SORT_TYPE, sortType);
			// Set giá trị của sortByFullName lên session
			session.setAttribute(Constant.SORT_BY_FULL_NAME, sortByFullName);
			// Set giá trị của sortByCodeLevel lên session
			session.setAttribute(Constant.SORT_BY_CODE_LEVEL, sortByCodeLevel);
			// Set giá trị của sortByEndDate lên session
			session.setAttribute(Constant.SORT_BY_END_DATE, sortByEndDate);
			// Gửi dữ liệu lên trang ADM002.jsp
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM002);
			requestDispatcher.forward(req, resp);
			// Bắt đầu khối catch
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[ListUserController].[doGet]: " + e.getMessage());
			// Nếu có lỗi thì điều hướng tới trang hiển thị lỗi
			resp.sendRedirect(
					req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "=" + Constant.ER015);
			// Kết thúc khối catch
		}
		// Kết thúc phương thức
	}
	// Kết thúc class
}
