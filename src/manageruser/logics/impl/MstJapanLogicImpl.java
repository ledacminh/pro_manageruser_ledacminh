/**
 * Copyright(C) 2020 Luvina Sorfware
 * MstJapanLogicImpl.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.logics.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import manageruser.dao.impl.MstJapanDaoImpl;
import manageruser.entities.MstJapan;
import manageruser.logics.MstJapanLogic;
import manageruser.utils.Constant;

/**
 * Lớp này dủng để xử lí dữ liệu lấy từ bảng mst_japan
 * @author :  Lê Minh
 */
public class MstJapanLogicImpl implements MstJapanLogic {
	/* (non-Javadoc)
	 * @see manageruser.logics.MstJapanLogic#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException, IOException {
		// Khởi tạo danh sách listMstJapan
		List<MstJapan> listMstJapan = new ArrayList<MstJapan>();
		// Khởi tạo đối tượng mstJapanImpl
		MstJapanDaoImpl mstJapanDaoImpl = new MstJapanDaoImpl();
		// Bắt đầu khối try
		try {
			// Lấy giá trị
			listMstJapan = mstJapanDaoImpl.getAllMstJapan();
			// Kết thúc khối try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// ghi log lõi
			System.out.println("[MstJapanLogicImpl].[getAllMstJapan] " + e.getMessage());
			// Kết thúc khối catch
			//Ném lỗi
			throw e;
		}
		// Trả về kết quả listMstJapan
		return listMstJapan;
		// Kết thúc phương thức
	}
	

	/* (non-Javadoc)
	 * @see manageruser.logics.MstJapanLogic#getNameLevelByCodeLevel(java.lang.String)
	 */
	@Override
	public String getNameLevelByCodeLevel(String codeLevel)
			throws ClassNotFoundException, SQLException, IOException {
		// Khai báo nameLevel
		String nameLevel = Constant.EMPTY_STRING;
		// Khai báo biến kiểm tra
		try {
			// Lấy danh sách listMstJapan
			List<MstJapan> listMstJapan = getAllMstJapan();
			// Bắt đầu vòng for
			// So sánh codeLevel với các giá trị trong DB
			for (int i = 0; i < listMstJapan.size(); i++) {
				// Nếu bằng nhau thì trả về tên trình độ tiếng nhật tương ứng
				if (listMstJapan.get(i).getCodeLevel().equals(codeLevel)) {
					// Trả về trình độ tiếng nhật tương ứng
					nameLevel = listMstJapan.get(i).getNameLevel();
					// Kết thúc if
				}
				// Kết thúc vòng for
			}
			// Kết thúc try
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// Ghi log lỗi
			System.out.println("[MstJapanLogicImpl].[getNameLevelByCodeLevel] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Trả về null nếu không có kết quả phù hợp
		return nameLevel;
		// Kết thúc phương thức
	}


	/* (non-Javadoc)
	 * @see manageruser.logics.MstJapanLogic#isExitCodeLevel(java.lang.String)
	 */
	@Override
	public boolean isExitCodeLevel(String codeLevel)
			throws ClassNotFoundException, SQLException, IOException {
		// Khởi tại thâm số để kiểm tra
		boolean check = false;
		// Bắt đầu khối try
		try {
			// Lấy ra thông tin trình độ tiếng nhật
			List<MstJapan> listMstJapan = getAllMstJapan();
			// Bắt đầu vòng for
			for (int i = 0; i < listMstJapan.size(); i++) {
				// so sánh trình độ tiếng nhật đưa vào so với trình độ tiếng
				// nhật trong DB
				if (codeLevel.equals(listMstJapan.get(i).getCodeLevel())) {
					// trả về true nếu tồn tại
					check = true;
					// Kết thúc if
				}
				// Kết thúc for
			}
			// Kết thúc try
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// ghi log lỗi
			System.out.println("[MstJapanLogicImpl].[isExitCodeLevel] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Trả về false nếu không tồn tại
		return check;
		// Kết thúc phương thức
	}
	//Kết thúc class
}
