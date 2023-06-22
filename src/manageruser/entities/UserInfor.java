/**
 * Copyright (C) 2020 Luvina Software
 * UserInfor.java Mar 17, 2020 author Lê Minh 
 */
package manageruser.entities;


/**
 * Lớp này là một lớp javabean gồm có contructer các phương thức get và set cho các thuộc tính 
 * của đối tượng UserInfor
 * @author: Lê Minh
 */
public class UserInfor {

	public UserInfor() {

	}

	private int userId, groupId, rule;
	private String fullName, groupName, email, tel, nameLevel, fullNameKana, salt,loginName,password,confirmPassword, totalDefault;
	String birthday;
	private String codeLevel, startDate, endDate, total;
	private int  yearOfBirthday,  monthOfBirthday,  dayOfBirthday, beginYear,beginMonth,beginDay,endYear,endMonth,endDay;
    
	/**
	 * @return the yearOfBirthday
	 */
	public int getYearOfBirthday() {
		return yearOfBirthday;
	}

	/**
	 * @param yearOfBirthday the yearOfBirthday to set
	 */
	public void setYearOfBirthday(int yearOfBirthday) {
		this.yearOfBirthday = yearOfBirthday;
	}

	/**
	 * @return the monthOfBirthday
	 */
	public int getMonthOfBirthday() {
		return monthOfBirthday;
	}

	/**
	 * @param monthOfBirthday the monthOfBirthday to set
	 */
	public void setMonthOfBirthday(int monthOfBirthday) {
		this.monthOfBirthday = monthOfBirthday;
	}

	/**
	 * @return the dayOfBirthday
	 */
	public int getDayOfBirthday() {
		return dayOfBirthday;
	}

	/**
	 * @param dayOfBirthday the dayOfBirthday to set
	 */
	public void setDayOfBirthday(int dayOfBirthday) {
		this.dayOfBirthday = dayOfBirthday;
	}

	/**
	 * @return the beginYear
	 */
	public int getBeginYear() {
		return beginYear;
	}

	/**
	 * @param beginYear the beginYear to set
	 */
	public void setBeginYear(int beginYear) {
		this.beginYear = beginYear;
	}

	/**
	 * @return the beginMonth
	 */
	public int getBeginMonth() {
		return beginMonth;
	}

	/**
	 * @param beginMonth the beginMonth to set
	 */
	public void setBeginMonth(int beginMonth) {
		this.beginMonth = beginMonth;
	}

	/**
	 * @return the beginDay
	 */
	public int getBeginDay() {
		return beginDay;
	}

	/**
	 * @param beginDay the beginDay to set
	 */
	public void setBeginDay(int beginDay) {
		this.beginDay = beginDay;
	}

	/**
	 * @return the endYear
	 */
	public int getEndYear() {
		return endYear;
	}

	/**
	 * @param endYear the endYear to set
	 */
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	/**
	 * @return the endMonth
	 */
	public int getEndMonth() {
		return endMonth;
	}

	/**
	 * @param endMonth the endMonth to set
	 */
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	/**
	 * @return the endDay
	 */
	public int getEndDay() {
		return endDay;
	}

	/**
	 * @param endDay the endDay to set
	 */
	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @return the totalDefault
	 */
	public String getTotalDefault() {
		return totalDefault;
	}

	/**
	 * @param totalDefault the totalDefault to set
	 */
	public void setTotalDefault(String totalDefault) {
		this.totalDefault = totalDefault;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the rule
	 */
	public int getRule() {
		return rule;
	}

	/**
	 * @param rule
	 *            the rule to set
	 */
	public void setRule(int rule) {
		this.rule = rule;
	}

	

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

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
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}

	/**
	 * @param nameLevel
	 *            the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
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

	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}

	/**
	 * @param codeLevel
	 *            the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
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
