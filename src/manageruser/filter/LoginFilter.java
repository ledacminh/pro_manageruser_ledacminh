/**
 * Copyright (C) 2020 Luvina Software
 * LoginFilter.java Apr 10, 2020 author Lê Minh 
 */
package manageruser.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageruser.utils.Common;
import manageruser.utils.Constant;

/**
 * Lớp này dùng để chuyển hướng trang sau khi đã kiểm tra đăng nhập
 * @author: Lê Minh
 */
public class LoginFilter implements Filter {
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Ép kiểu request dạng ServeltRequet về dạng req HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		// Lấy session
		HttpSession session = req.getSession();
		// Ép kiểu response dạng ServeltRequet về dạng resp HttpServletRequest
		HttpServletResponse resp = (HttpServletResponse) response;
		try {
			// Lấy URL của controller
			String servletPath = req.getServletPath();
			// Kiểm tra nếu chưa đăng nhập
			if (!servletPath.equals(Constant.LOGIN_PAGE) && !Common.checkLogin(session)) {
				// Di chuyển tới màn hình ADM001
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM001);
				requestDispatcher.forward(req, resp);
				// Ngược lại thì cho phép controller đi qua
			} else {
				// Cho đi qua filter
				chain.doFilter(req, resp);
			}
			// Kết thúc khối try
		} catch (Exception e) {
			// Ghi log lỗi
			System.out.println("[LoginFilter].[doFilter]: " + e.getMessage());
			// Nếu có lỗi thì điều hướng tới trang hiển thị lỗi
			resp.sendRedirect(
					req.getContextPath() + Constant.ERROR_PAGE + "?" + Constant.ACTION + "= " + Constant.ER015);
			// Kết thúc khối catch
		}

		// Kết thúc phương thức
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	// Kết thúc phương thức
}
