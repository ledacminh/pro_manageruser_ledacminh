/**
 * Copyright(C) 2020 Luvina Sorfware
 * MstJapan.java Feb 27, 2020 author: L� Minh
 */
package manageruser.entities;

/**
 * Lớp này là một lớp javabean gồm có contructer các phương thức get và set cho các thuộc tính 
 * của bảng mst_japan
 * @author: Lê Minh
 */
public class MstJapan {

	private String codeLevel;
	private String nameLevel;

	/**
	 * Constructor khởi tạo không tham số
	 */
	public MstJapan() {
		super();
	}

	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}

	/**
	 * @param codeLevel the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}

	/**
	 * @param nameLevel the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	

}
