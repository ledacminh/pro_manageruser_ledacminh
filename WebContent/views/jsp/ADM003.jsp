<%@page import="manageruser.utils.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="views/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="views/js/ADM003.js"></script>
<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<%@include file="header.jsp"%>

	<!-- End vung header -->

	<!-- Begin vung input-->
	<form
	<c:choose>
			<c:when test = "${userInfor.userId != 0 }">
				action="EditInputUser.do"
			</c:when>
			<c:otherwise>
				action="AddUserInput.do" 
			</c:otherwise>
		</c:choose>
	  method="post"
		name="inputform">
		<input class="btn" type="hidden" name="action" value="submit" />
		<input type="hidden" name="userId" value="${userInfor.userId}" />  	
		
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>
			<c:forEach items="${listError}" var="err">
				<tr>
					<td class="errMsg">
						<div style="padding-left: 100px;">
							<c:out value="${err}"></c:out>
						</div>
					</td>
				</tr>
			</c:forEach>

			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<c:choose>
								<c:when test="${userInfor.userId != 0 }">
			                 	<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input class="txBox" type="text"
									name="loginName" value="${fn:escapeXml(userInfor.loginName)}"
									size="15" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" readonly /></td>
			                 </c:when>
								<c:otherwise>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input class="txBox" type="text"
									name="loginName" value="${fn:escapeXml(userInfor.loginName)}"
									size="15" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';"  /></td>
			                </c:otherwise>
							</c:choose>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="groupId">
										<option value="0" selected="selected">選択してください</option>
										<c:forEach items="${listMstGroup}" var="mstGroup">

											<c:if test="${userInfor.groupId == mstGroup.groupId }">
												<option value="${fn:escapeXml(mstGroup.groupId)}"
													selected="selected">${fn:escapeXml(mstGroup.groupName)}</option>
											</c:if>

											<c:if test="${userInfor.groupId != mstGroup.groupId }">
												<option value="${fn:escapeXml(mstGroup.groupId)}">${fn:escapeXml(mstGroup.groupName)}</option>
											</c:if>

										</c:forEach>
								</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
							</tr>

							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullName" value="${fn:escapeXml(userInfor.fullName)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullNameKana"
									value="${fn:escapeXml(userInfor.fullNameKana)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<c:set var="birthday"
								value="${fn:split(userInfor.getBirthday(), '/')}" />
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><select name="yearOfBirthday">
										<c:forEach items="${listYearCurrently}" var="year">

											<c:if test="${birthday[0] == year}">
												<option value="${fn:escapeXml(year)}" selected="selected">${fn:escapeXml(year)}</option>
											</c:if>
											<c:if test="${birthday[0] != year}">
												<option value="${fn:escapeXml(year)}">${fn:escapeXml(year)}</option>
											</c:if>
										</c:forEach>
								</select>年 <select name="monthOfBirthday">
										<c:forEach items="${listMonth}" var="month">
											<c:if test="${birthday[1] == month}">
												<option value="${fn:escapeXml(month)}" selected="selected">${fn:escapeXml(month)}</option>
											</c:if>

											<c:if test="${birthday[1] != month}">
												<option value="${fn:escapeXml(month)}">${fn:escapeXml(month)}</option>
											</c:if>
										</c:forEach>
								</select>月 <select name="dayOfBirthday">
										<c:forEach items="${listDay}" var="day">
											<c:if test="${birthday[2] == day}">
												<option value="${fn:escapeXml(day)}" selected="selected">${fn:escapeXml(day)}</option>
											</c:if>

											<c:if test="${birthday[2] != day}">
												<option value="${fn:escapeXml(day)}">${fn:escapeXml(day)}</option>
											</c:if>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="${fn:escapeXml(userInfor.email)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="${fn:escapeXml(userInfor.tel)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<c:choose>
								<c:when test="${userInfor.userId != 0 }">
								<td class="lbl_left" style="display: none;"><font color="red">*</font> パスワード:</td>
								<td align="left"><input class="txBox" type="password"
									name="password" value="${fn:escapeXml(userInfor.password)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';"
									style="display: none;"/></td>
			                 </c:when>
								<c:otherwise>
								<td class="lbl_left"><font color="red">*</font> パスワード:</td>
								<td align="left"><input class="txBox" type="password"
									name="password" value="${fn:escapeXml(userInfor.password)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
			                </c:otherwise>
							</c:choose>
							</tr>
							<tr>
								<c:choose>
								<c:when test="${userInfor.userId != 0 }">
								<td class="lbl_left" style="display: none;">パスワード（確認）:</td>
								<td align="left" style="display: none;"><input class="txBox" type="password"
									name="confirmPassword"
									value="${fn:escapeXml(userInfor.confirmPassword)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';"
									style="display: none;" /></td>
			                 </c:when>
								<c:otherwise>
								<td class="lbl_left">パスワード（確認）:</td>
								<td align="left"><input class="txBox" type="password"
									name="confirmPassword"
									value="${fn:escapeXml(userInfor.confirmPassword)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
			                </c:otherwise>
							</c:choose>
							</tr>
							<tr>
								<th align="left" colspan="2"><a href="#"
									onclick="showHide()">日本語能力</a></th>
							</tr>
						</table>
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0" id="TrinhDoTiengNhat" style="display: none">
							<tr>
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="codeLevel">
										<option value="">選択してください</option>
										<c:forEach items="${listMstJapan}" var="mstJapan">
											<c:if test="${userInfor.codeLevel == mstJapan.codeLevel }">
												<option value="${fn:escapeXml(mstJapan.codeLevel)}"
													selected="selected">${fn:escapeXml(mstJapan.nameLevel)}</option>
											</c:if>

											<c:if test="${userInfor.codeLevel != mstJapan.codeLevel }">
												<option value="${fn:escapeXml(mstJapan.codeLevel)}">${fn:escapeXml(mstJapan.nameLevel)}</option>
											</c:if>
										</c:forEach>
								</select></td>
							</tr>



							<c:set var="startDate"
								value="${fn:split(userInfor.getStartDate(), '/')}" />
							<tr>
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><select name="beginYear">
										<c:forEach items="${listYearCurrently}" var="year">
											<c:if test="${startDate[0] == year}">
												<option value="${fn:escapeXml(year)}" selected="selected">${fn:escapeXml(year)}</option>
											</c:if>

											<c:if test="${startDate[0] != year}">
												<option value="${fn:escapeXml(year)}">${fn:escapeXml(year)}</option>
											</c:if>
										</c:forEach>
								</select>年 <select name="beginMonth">
										<c:forEach items="${listMonth}" var="month">
											<c:if test="${startDate[1] == month}">
												<option value="${fn:escapeXml(month)}" selected="selected">${fn:escapeXml(month)}</option>
											</c:if>

											<c:if test="${startDate[1] != month}">
												<option value="${fn:escapeXml(month)}">${fn:escapeXml(month)}</option>
											</c:if>
										</c:forEach>
								</select>月 <select name="beginDay">
										<c:forEach items="${listDay}" var="day">
											<c:if test="${startDate[2] == day}">
												<option value="${fn:escapeXml(day)}" selected="selected">${fn:escapeXml(day)}</option>
											</c:if>

											<c:if test="${startDate[2]!= day}">
												<option value="${fn:escapeXml(day)}">${fn:escapeXml(day)}</option>
											</c:if>
										</c:forEach>
								</select>日</td>
							</tr>

							<c:set var="endDate"
								value="${fn:split(userInfor.getEndDate(), '/')}" />


							<tr>
								<td class="lbl_left">失効日:</td>
								<td align="left"><select name="endYear">
										<c:forEach items="${listYearNext}" var="year">
											<c:if test="${endDate[0] == year}">
												<option value="${fn:escapeXml(year)}" selected="selected">${fn:escapeXml(year)}</option>
											</c:if>

											<c:if test="${endDate[0]!= year}">
												<option value="${fn:escapeXml(year)}">${fn:escapeXml(year)}</option>
											</c:if>
										</c:forEach>
								</select>年 <select name="endMonth">
										<c:forEach items="${listMonth}" var="month">
											<c:if test="${endDate[1]== month}">
												<option value="${fn:escapeXml(month)}" selected="selected">${fn:escapeXml(month)}</option>
											</c:if>

											<c:if test="${endDate[1] != month}">
												<option value="${fn:escapeXml(month)}">${fn:escapeXml(month)}</option>
											</c:if>
										</c:forEach>
								</select>月 <select name="endDay">
										<c:forEach items="${listDay}" var="day">
											<c:if test="${endDate[2] == day}">
												<option value="${fn:escapeXml(day)}" selected="selected">${fn:escapeXml(day)}</option>
											</c:if>

											<c:if test="${endDate[2] != day}">
												<option value="${fn:escapeXml(day)}">${fn:escapeXml(day)}</option>
											</c:if>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">点数:</td>

								<td align="left"><input class="txBox" type="text"
									name="total" value="${fn:escapeXml(userInfor.total)}" size="5"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>

							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="確認" /></td>
					<td><input class="btn" type="button" value="戻る"
						onclick="location.href='listUser.do?action=back&key=${key}'" /></td>
				</tr>
			</table>
		</div>
		<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<div class="lbl_footer">
		<br /> <br /> <br /> <br /> Copyright © 2010 ルビナソフトウエア株式会社. All
		rights reserved.
	</div>
	<!-- End vung footer -->
</body>

</html>