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
	<form action="listUser.do" method="get" name="inputform">
	<table  class="tbl_input"   border="0" width="80%"  cellpadding="0" cellspacing="0" >	
		<tr>
			<td align="center" colspan="2">
				<div style="height:50px"></div>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
			<c:out value="${message}"></c:out>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<input class="btn" type="submit" value="OK" onclick=""/>
			</td>
		</tr>
	</table>
	</form>
<!-- End vung input -->

<!-- Begin vung footer -->
<div class = "lbl_footer">
	<br /><br /><br /><br/>
			Copyright ©　2010　ルビナソフトウエア株式会社. All rights reserved.
</div>
<!-- End vung footer -->
</body>

</html>