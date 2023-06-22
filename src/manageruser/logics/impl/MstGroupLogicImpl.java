/**
 * Copyright(C) 2020 Luvina Sorfware
 * MstGroupLogicImpl.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.logics.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageruser.dao.MstGroupDao;
import manageruser.dao.impl.MstGroupDaoImpl;
import manageruser.entities.MstGroup;
import manageruser.logics.MstGroupLogic;

/**
 * Lớp này dùng để: Xử lí logic dữ liệu lấy từ gửi lên servlet
 * @author : Lê Minh
 */
public class MstGroupLogicImpl implements MstGroupLogic {
	
	

	/* (non-Javadoc)
	 * @see manageruser.logics.MstGroupLogic#getGroupNameById(int)
	 */
	@Override
	public String getGroupNameById(int groupId) throws ClassNotFoundException, SQLException, IOException {
		// Khởi tạo danh sách listMstGroup
		List<MstGroup> listMstGroup = new ArrayList<>();
		// Khởi tạo giá trị cho groupName
		String groupName = "";
		// Bắt đầu khối try
		try {
			// Lấy danh sách MstGroup từ DB
			listMstGroup = getAllMstGroup();
			// Bắt đầu vòng lặp for
			for (int i = 0; i < listMstGroup.size(); i++) {
				// Kiểm tra nếu groupId truyền vào bằng với groupId trong danh sách
				if (listMstGroup.get(i).getGroupId() == groupId) {
					// Nếu đúng thì trả về groupName tương ứng
					groupName = listMstGroup.get(i).getGroupName();
					// Kết thúc if
				}
				// Kết thúc vòng for
			}
			// Kết thúc khối try
		} catch ( SQLException | ClassNotFoundException | IOException e) {
			// Ghi log lỗi
			System.out.println("[MstGroupLogicImpl].[getGroupNameById] " + e.getMessage());
			//Ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Trả về kết quả
		return groupName;
		// Kết thúc phương thưc
	}

	/* (non-Javadoc)
	 * @see manageruser.logics.MstGroupLogic#isExitGroupId(int)
	 */
	@Override
	public boolean isExitGroupId(int groupId) throws ClassNotFoundException, SQLException, IOException {

		// Khởi tạo danh dách listMstGroup
		List<MstGroup> listMstGroup = new ArrayList<>();
		// Khởi tạo biến kiểm tra
		boolean check = false;
		// Bắt đầu khối try
		try {
			// Lấy giá trị listMstGroup
			listMstGroup = getAllMstGroup();
			// Bắt đầu vòng lặp for
			for (int i = 0; i < listMstGroup.size(); i++) {
				// Kiểm tra nếu có giá trị bằng với groupId
				if (listMstGroup.get(i).getGroupId() == groupId) {
					// Gán giá trị bằng true;
					check = true;
					// Kết thúc if
				}
				// Kết thúc vòng for
			}
			// Kết thúc try
		} catch (ClassNotFoundException | SQLException | IOException  e) {
			// Ghi log lỗi
			System.out.println("[MstGroupLogicImpl].[isExitGroupId] " + e.getMessage());
			//ném lỗi 
			throw e;
			// Kết thúc khối catch
		}
		// Trả vê kết quả kiểm tra
		return check;

		// Kết thúc phương thức
	}
	//Kết thúc class

	/* (non-Javadoc)
	 * @see manageruser.logics.MstGroupLogic#getAllMstGroup()
	 */
	@Override
	public List<MstGroup> getAllMstGroup() throws ClassNotFoundException, SQLException, IOException {
		// Khởi tạo listMstGroup để lưu thông tin của đối tượng MstGroup
		List<MstGroup> listMstGroup = new ArrayList<>();
		// Bắt đầu khối try
		try {
			// Khởi tạo đối tượng mstGroupImpl
			MstGroupDao mstGroupDaoImpl = new MstGroupDaoImpl();
			// Gọi đến phương thức getAllMstGroup
			listMstGroup = mstGroupDaoImpl.getAllMstGroup();
			// Kết thúc khối try
		} catch (SQLException | ClassNotFoundException | IOException  e) {
			// Ghi log lỗi
			System.out.println("[MstGroupLogicImpl].[getAllMstGroup] " + e.getMessage());
			// Kết thúc khối catch
		}
		// Trẻ về kết quả
		return listMstGroup;
		// Kết thúc phương thức
	}
}
