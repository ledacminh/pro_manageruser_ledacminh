/**
 * Copyright(C) 2020 Luvina Sorfware
 * TblDetailUserJapan.java Feb 27, 2020 author: L� Minh
 */
package manageruser.entities;

/**
 * Lớp này là một lớp javabean gồm có contructer các phương thức get và set cho các thuộc tính 
 * của bảng tbl_detail_user_japan
 * @author: Lê Minh
 */
public class TblDetailUserJapan {

	private int detailUserJapanId;
	private int userId;
	private String codeLevel;
	private String startDate;
	private String endDate;
	private String total;

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * Constructor khởi tạo không tham số
	 */
	public TblDetailUserJapan() {
		super();
	}

	/**
	 * @return the detailUserJapanId
	 */
	public int getDetailUserJapanId() {
		return detailUserJapanId;
	}

	/**
	 * @param detailUserJapanId
	 *            the detailUserJapanId to set
	 */
	public void setDetailUserJapanId(int detailUserJapanId) {
		this.detailUserJapanId = detailUserJapanId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}

	/**
	 * @param string
	 *            the codeLevel to set
	 */
	public void setCodeLevel(String string) {
		this.codeLevel = string;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
}
