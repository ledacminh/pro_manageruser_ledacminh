<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<form action="EditInputUser.do" method="post" name="inputform" id = "formDelete" >	
	<input type="hidden" name="userId" value="${userInfor.userId}" /> 
	<table  class="tbl_input" border="0" width="75%"  cellpadding="0" cellspacing="0" >			
		<tr>
			<th align="left">
				<div style="padding-left:100px;">
					情報確認					
				</div>
				<div style="padding-left:100px;">&nbsp;</div>
			</th>			
		</tr>				
		<tr>
			<td align="left" >
				<div style="padding-left:100px;">
					<table border="1" width="70%" class="tbl_input" cellpadding="4" cellspacing="0" >					
					<tr>
						<td class="lbl_left">アカウント名:</td>
						<td align="left" ><c:out value ="${userInfor.loginName}" /></td>
					</tr>
					<tr>
						<td class="lbl_left">グループ:</td>
						<td align="left"> <c:out value ="${userInfor.groupName}" /></td>
					</tr>                   
					<tr>
						<td class="lbl_left">氏名:</td>
						<td align="left" style = "word-break : break-all;"><c:out value = "${userInfor.fullName}" /></td>
					</tr>	
					<tr>
						<td class="lbl_left">カタカナ氏名:</td>
						<td align="left" style = "word-break : break-all;"> <c:out value = "${userInfor.fullNameKana}" /></td>
					</tr>
					<tr>
						<td class="lbl_left">生年月日:</td>
						<td align="left"><c:out value = "${userInfor.birthday}" /></td>
					</tr>				
					<tr>
						<td class="lbl_left">メールアドレス:</td>
						<td align="left" style = "word-break : break-all;"><c:out value = "${userInfor.email}" /></td>
					</tr>
					
					
					<tr>
						<td class="lbl_left">電話番号:</td>
						<td align="left">
						<c:if test="${userInfor.tel != null}">
						 ${fn:escapeXml(fn:replace(userInfor.tel,"-",""))}
						</c:if></td>
					</tr>	
					<tr>
						<th colspan = "2"><a href = "#" onclick="showHide()">日本語能力</a></th>
					</tr>
					</table>
					<div id="TrinhDoTiengNhat" style="display: none">
					<table border="1" width="70%" class="tbl_input" cellpadding="4" cellspacing="0">					
					<tr>
						<td class="lbl_left" >資格:</td>
						<td align="left" > <c:out value = "${userInfor.nameLevel}" /></td>
					</tr>
					<tr>
						<td class="lbl_left" >資格交付日:</td>
						<td align="left"  > <c:out value  = "${userInfor.startDate}" /></td>
					</tr>
					<tr>
						<td class="lbl_left"  >失効日:</td>
						<td align="left" > <c:out value = "${userInfor.endDate}"/> </td>
					</tr>	
					<tr>
						<td class="lbl_left"  >点数:</td>
						<td align="left"> <c:out value = "${userInfor.total}" /></td>
					</tr>	
					</table>
					</div>
				</div>				
			</td>		
		</tr>
	</table>
	<div style="padding-left:100px;">&nbsp;</div>
	<fmt:setLocale value="ja_JP" />
	<fmt:requestEncoding value="UTF-8" />
	<fmt:setBundle basename="message" />
	<c:set var="message">
		<fmt:message key="MSG004"></fmt:message>
	</c:set>
		<!-- Begin vung button -->
	<div style="padding-left:100px;" />
	<table border="0" cellpadding="4" cellspacing="0" width="300px">	
	  
		<tr>
			<th width="200px" align="center">&nbsp;</th>
			
			<td>
				<input class="btn" type="button" value="編集" onclick="location.href='EditInputUser.do?userId=${userInfor.userId}'"/>					
			</td>
			<td>
				<input class="btn" type="button" value="削除"  onclick ="ConfirmDelete('${message}')" />					
			</td>	
			<td>
				<input class="btn" type="button" value="戻る" onclick="location.href='listUser.do?action=back'"/>						
			</td>
		</tr>		
	</table>
	<!-- End vung button -->	
</form>
<!-- End vung input -->



<input type="hidden" name="userId" value="${userInfor.userId}" />
<!-- Begin vung footer -->
<div class = "lbl_footer">
	<br /><br /><br /><br />
			Copyright ©　2010　ルビナソフトウエア株式会社. All rights reserved.
</div>
<!-- End vung footer -->
</body>

</html>