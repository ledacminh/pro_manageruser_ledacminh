/**
 * Copyright(C) 2020 Luvina Sorfware
 * TblDetailJapanLogicImpl.java Mar 2, 2020 author: Lê Minh
 */
package manageruser.logics.impl;
import java.io.IOException;
import java.sql.SQLException;
import manageruser.dao.TblDetailUserJapanDao;
import manageruser.dao.impl.TblDetailUserJapanDaoImpl;
import manageruser.entities.TblDetailUserJapan;
import manageruser.logics.TblDetailUserJapanLogic;

/**
 *Lớp này dùng để: Xử lí logic lấy dữ liêu từ bản tbl_detail_user_japan
 * @author :  Lê Minh
 */
public class TblDetailUserJapanLogicImpl implements TblDetailUserJapanLogic {
	TblDetailUserJapanDao tblDetailUserJapanDao = new TblDetailUserJapanDaoImpl();
	/* (non-Javadoc)
	 * @see manageruser.logics.TblDetailUserJapanLogic#isExistCodeLevel(int)
	 */
	@Override
	public boolean isExistCodeLevel(int userId) throws ClassNotFoundException, SQLException, IOException {
		//Khởi tạo biên lưu giá trị kiểm tra bằng false
		boolean check = false;
		//Lấy đối tượng tblDetailUserJapan tương ứng vơi userid
		TblDetailUserJapan tblDetailUserJapan = tblDetailUserJapanDao.getTblDetailByUserId(userId);
		//Kiểm tra nếu giá trị codeLevel khác null
		if(tblDetailUserJapan.getCodeLevel() != null) {
			//Trả về giá trị true là  tồn tại TĐTN
			check = true;
		}
		//Trả về kết quả
		return check;
		//Kết thúc phương thức
	}

	/* (non-Javadoc)
	 * @see manageruser.logics.TblDetailUserJapanLogic#updateTblDetailUserJapan(manageruser.entities.TblDetailUserJapan)
	 */
	@Override
	public void updateTblDetailUserJapan(TblDetailUserJapan tblDetailUserJapan)
			throws ClassNotFoundException, SQLException {
		//thực hiện update đối tượng TblDetailUserJapan
		tblDetailUserJapanDao.updateTblDetailUserJapan(tblDetailUserJapan);
		//Kêt thúc phương thức
	}
}
