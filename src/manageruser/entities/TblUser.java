/**
 * Copyright(C) 2020 Luvina Sorfware
 * TblUser.java Feb 27, 2020 author: L� Minh
 */
package manageruser.entities;


/**
 * Đây là class javabean chứa: Phương thức khởi tạo không tham số và hàm setter,
 * getter
 * @author: Lê Minh
 */

public class TblUser {
	private int userId;
	private int groupId;
	private String loginName;
	private String password;
	private String fullName;
	private String fullNameKana;
	private String email;
	private String tel;
	private String birthday;
	private int rule;
	private String salt;

	// Hàm khởi tạo không tham số
	public TblUser() {
		super();
	}

	/**
	 * Phương thức dùng để: Lấy giá trị thuộc tính userId
	 * 
	 * @return the userId: Trả về ID của user
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Phương thức gán giá trị cho thuộc tính userId 
	 * 
	 * @param userId:
	 *            Là ID của user
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Phương thức dùng để: Lấy giá trị thuộc tính groupId của user
	 * @return the groupId :
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the loginName`
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the fullNameKana
	 */
	public String getFullNameKana() {
		return fullNameKana;
	}

	/**
	 * @param fullNameKana
	 *            the fullNameKana to set
	 */
	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param string
	 *            the birthday to set
	 */
	public void setBirthday(String string) {
		this.birthday = string;
	}

	/**
	 * @return the rule
	 */
	public int getRule() {
		return rule;
	}

	/**
	 * @param i
	 *            the rule to set
	 */
	public void setRule(int i) {
		this.rule = i;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

}
