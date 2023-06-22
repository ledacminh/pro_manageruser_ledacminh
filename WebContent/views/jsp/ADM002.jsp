<%@page import="manageruser.utils.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="views/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/user.js"></script>

<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<%@include file="header.jsp"%>
	<!-- End vung header -->

	<!-- Begin vung dieu kien tim kiem -->
	<form action="listUser.do" method="get" name="mainform">
		<input type="hidden" name="action" value="search" /> <input
			type="hidden" name="currentPage" value="${currentPage}" />
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="fullName" value="${fn:escapeXml(fullName)}" size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>

						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="groupId">
									<option value="0" selected="selected">全て</option>
									<c:forEach items="${listMstGroup}" var="mstGroup">

										<c:if test="${groupId == mstGroup.groupId }">
											<option value="${mstGroup.groupId}" selected="selected">${fn:escapeXml(mstGroup.groupName)}</option>
										</c:if>

										<c:if test="${groupId != mstGroup.groupId }">
											<option value="${mstGroup.groupId}">${fn:escapeXml(mstGroup.groupName)}</option>
										</c:if>


									</c:forEach>


							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button" value="新規追加"
								onclick="window.location.href = './AddUserInput.do?type=default'" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	<c:if test="${empty MSG005}">
		<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
			width="80%">

			<tr class="tr2">
				<th align="center" width="20px">ID</th>
				<th align="left"><c:choose>
						<c:when
							test="${sortByFullName == Constant.SORT_BY_FULL_NAME_DEFAULT}">
							<c:url value="/listUser.do?action=sort" var="urlSortByFullName">
								<c:param name="sortType"><%=Constant.SORT_TYPE_FULL_NAME%></c:param>
								<c:param name="sortByFullName"><%=Constant.SORT_BY_FULL_NAME_OTHER%></c:param>
								<c:param name="groupId">${groupId}</c:param>
								<c:param name="fullName">${fullName}</c:param>
								<c:param name="currentPage">${currentPage}</c:param>
							</c:url>
						氏名 <a href="${urlSortByFullName}">▲▽</a>
						</c:when>
						<c:otherwise>
							<c:url value="/listUser.do?action=sort" var="urlSortByFullName">
								<c:param name="sortType"><%=Constant.SORT_TYPE_FULL_NAME%></c:param>
								<c:param name="sortByFullName"><%=Constant.SORT_BY_FULL_NAME_DEFAULT%></c:param>
								<c:param name="groupId">${groupId}</c:param>
								<c:param name="fullName">${fullName}</c:param>
								<c:param name="currentPage">${currentPage}</c:param>
							</c:url>
						氏名 <a href="${urlSortByFullName}">△▼</a>
						</c:otherwise>
					</c:choose></th>
				<th align="left">生年月日</th>
				<th align="left">グループ</th>
				<th align="left">メールアドレス</th>
				<th align="left" width="70px">電話番号</th>
				<th align="left"><c:choose>
						<c:when
							test="${sortByCodeLevel == Constant.SORT_BY_CODE_LEVEL_DEFAULT}">
							<c:url value="/listUser.do?action=sort" var="urlSortByCodeLevel">
								<c:param name="sortType"><%=Constant.SORT_TYPE_CODE_LEVEL%></c:param>
								<c:param name="sortByCodeLevel"><%=Constant.SORT_BY_CODE_LEVEL_OTHER%></c:param>
								<c:param name="groupId">${groupId}</c:param>
								<c:param name="fullName">${fullName}</c:param>
								<c:param name="currentPage">${currentPage}</c:param>
							</c:url>
						日本語能力  <a href="${urlSortByCodeLevel}">▲▽</a>
						</c:when>
						<c:otherwise>
							<c:url value="/listUser.do?action=sort" var="urlSortByCodeLevel">
								<c:param name="sortType"><%=Constant.SORT_TYPE_CODE_LEVEL%></c:param>
								<c:param name="sortByCodeLevel"><%=Constant.SORT_BY_CODE_LEVEL_DEFAULT%></c:param>
								<c:param name="groupId">${groupId}</c:param>
								<c:param name="fullName">${fullName}</c:param>
								<c:param name="currentPage">${currentPage}</c:param>
							</c:url>
						日本語能力  <a href="${urlSortByCodeLevel}">△▼</a>
						</c:otherwise>
					</c:choose></th>
				<th align="left"><c:choose>
						<c:when
							test="${sortByEndDate == Constant.SORT_BY_END_DATE_DEFAULT}">
							<c:url value="/listUser.do?action=sort" var="urlSortByEndDate">
								<c:param name="sortType"><%=Constant.SORT_TYPE_END_DATE%></c:param>
								<c:param name="sortByEndDate"><%=Constant.SORT_BY_END_DATE_OTHER%></c:param>
								<c:param name="groupId">${groupId}</c:param>
								<c:param name="fullName">${fullName}</c:param>
								<c:param name="currentPage">${currentPage}</c:param>
							</c:url>
						失効日 <a href="${urlSortByEndDate}">△▼</a>
						</c:when>
						<c:otherwise>
							<c:url value="/listUser.do?action=sort" var="urlSortByEndDate">
								<c:param name="sortType"><%=Constant.SORT_TYPE_END_DATE%></c:param>
								<c:param name="sortByEndDate"><%=Constant.SORT_BY_END_DATE_DEFAULT%></c:param>
								<c:param name="groupId">${groupId}</c:param>
								<c:param name="fullName">${fullName}</c:param>
								<c:param name="currentPage">${currentPage}</c:param>
							</c:url>
						失効日 <a href="${urlSortByEndDate}">▲▽</a>
						</c:otherwise>
					</c:choose></th>
				<th align="left">点数</th>
			</tr>
			<c:forEach items="${listUserInfor}" var="user">
				<tr>
					<td align="right"><a href="ViewDetailUser.do?userId=${user.userId}"><c:out value="${user.userId}" /></a>
					</td>
					<c:if test="${fn:length(user.fullName) < 25 }">
						<td><c:out value="${user.fullName}"></c:out></td>
					</c:if>
					<c:if test="${fn:length(user.fullName) > 25 }">
						<td><c:out value="${fn:substring(user.fullName, 0, 22)}"></c:out>...</td>
					</c:if>

					<td align="center"><c:out value="${user.birthday}"></c:out></td>

					<c:if test="${fn:length(user.groupName) < 25 }">
						<td><c:out value="${user.groupName}"></c:out></td>
					</c:if>
					<c:if test="${fn:length(user.groupName) > 25 }">
						<td><c:out value="${fn:substring(user.groupName, 0, 22)}"></c:out>...</td>
					</c:if>


					<c:if test="${fn:length(user.email) < 25 }">
						<td><c:out value="${user.email}"></c:out></td>
					</c:if>
					<c:if test="${fn:length(user.email) > 25 }">
						<td><c:out value="${fn:substring(user.email, 0, 22)}"></c:out>...</td>
					</c:if>
					<td><c:out value="${user.tel}"></c:out></td>
					<td><c:out value="${user.nameLevel}"></c:out></td>
					<td align="center"><c:out value="${user.endDate}"></c:out></td>
					<td align="right"><c:out value="${user.total}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${not empty MSG005}">
		<c:out value="${MSG005}"></c:out>
	</c:if>

	<!-- End vung hien thi danh sach user -->

	<!-- Begin vung paging -->
	<table>
		<tr>
			<c:url value="/listUser.do?action=paging" var="urlPaging">
				<c:param name="sortType">${sortType}</c:param>
				<c:param name="sortByFullName">${sortByFullName}</c:param>
				<c:param name="sortByCodeLevel">${sortByCodeLevel}</c:param>
				<c:param name="sortByEndDate">${sortByEndDate}</c:param>
				<c:param name="groupId">${groupId}</c:param>
				<c:param name="fullName">${fullName}</c:param>
			</c:url>
			<td class="lbl_paging"><c:if test="${totalPage > 1 }">
					<c:if test="${currentPage > limitPage}">
						<a
							href="${urlPaging}&currentPage=${listPaging.get(0) - limitPage}">
							&lt;&lt;</a>&nbsp;
					</c:if>

					<c:forEach items="${listPaging}" var="page">
						<c:if test="${currentPage == page}">
							<a href="${urlPaging}&currentPage=${page}"
								style="text-decoration: none">${page}</a>&nbsp;	
					</c:if>
						<c:if test="${currentPage != page}">
							<a href="${urlPaging}&currentPage=${page}">${page}</a>&nbsp;	
					</c:if>
					</c:forEach>

					<c:if test="${!listPaging.contains(totalPage) }">
						<a
							href="${urlPaging}&currentPage=${listPaging.get(0) + limitPage}">
							&gt;&gt;</a>
					</c:if>
				</c:if></td>
		</tr>
	</table>
	<!-- End vung paging -->

	<!-- Begin vung footer -->
	<div class="lbl_footer">
		<br /> <br /> <br /> <br /> Copyright © 2010 ルビナソフトウエア株式会社. All
		rights reserved.
	</div>
	<!-- End vung footer -->

</body>

</html>




