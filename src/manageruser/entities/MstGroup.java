/**
 * Copyright(C) 2020 Luvina Sorfware
 * MstGroup.java Feb 27, 2020 author: L� Minh
 */
package manageruser.entities;

/**
 * Lớp này là một lớp javabean gồm có constructer các phương thức get và set cho các thuộc tính
 * của bảng mst_group
 * @author: Lê Minh
 */
public class MstGroup {

	private int groupId;
	private String groupName;

	/**
	 * @return the groupId
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

}
