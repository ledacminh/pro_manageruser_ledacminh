<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./views/css/style.css" rel="stylesheet" type="text/css" />
<title>ユーザ管理</title>
</head>
<body align="center">
	<form action="login.do" method="post">
		<center>
			<table class="tbl_input" cellpadding="4" cellspacing="0"
				width="400px">
				<tr>
					<th width="120px">&nbsp;</th>
					<th></th>
				</tr>
				<tr>
					<th colspan="2" align="left">アカウント名およびパスワードを入力してください</th>
				</tr>
				<c:forEach items="${listError}" var="err"> 
					<tr>
						<td class="errMsg" colspan="2"><c:out value="${err}"></c:out></td>
					
						
						
					</tr>
				</c:forEach>
				<tr>
					<td class="errMsg" colspan="2">&nbsp;</td>
				</tr>
				<tr align="left">
					<td class="lbl_left">アカウント名:</td>
					<td align="left">
					<input class="txBox" type="text"
						name="loginName" value="<c:out value="${loginName}" />" size="20"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" autofocus />
						</td>
				</tr>
				<tr>
					<td class="lbl_left">パスワード:</td>
					<td align="left"><input class="txBox" type="password"
						name="password" value="" size="20"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>
					<td></td>
					<td align="left"><input class="btn btn_wider" type="submit"
						value="ログイン" /></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>