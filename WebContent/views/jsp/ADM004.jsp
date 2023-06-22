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
				action="EditUserConfirm.do"
			</c:when>
			<c:otherwise>
				action="AddUserConfirm.do"
			</c:otherwise>
		</c:choose>
	 method="post" name="inputform">	
	<input type="hidden" name="key" value="${key}" /> 
	<input type="hidden" name="userId" value="${userInfor.userId}" /> 
	
	<table  class="tbl_input" border="0" width="75%"  cellpadding="0" cellspacing="0" >			
		<tr>
			<th align="left">
				<div style="padding-left:100px;">
					情報確認<br/>
					入力された情報をＯＫボタンクリックでＤＢへ保存してください
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
						<td align="left" style = "word-break : break-all;" > <c:out value = "${userInfor.fullNameKana}" /></td>
					</tr>
					<tr>
						<td class="lbl_left">生年月日:</td>
						<td align="left"><c:out value = "${userInfor.birthday}" /></td>
					</tr>				
					<tr>
						<td class="lbl_left">メールアドレス:</td>
						<td align="left" style = "word-break : break-all;" ><c:out value = "${userInfor.email}" /></td>
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
						<c:if test="${fn:length(userInfor.codeLevel) > 0 }">
						<td align="left"  > <c:out value  = "${userInfor.startDate}" /></td> </c:if>
						
						<c:if test="${fn:length(userInfor.codeLevel) == 0 }">
						<td align="left"  > <c:out value  = "" /></td> </c:if>
						
					</tr>
					<tr>
						<td class="lbl_left"  >失効日:</td>
						
						<c:if test="${fn:length(userInfor.codeLevel) > 0 }">
						<td align="left" > <c:out value = "${userInfor.endDate}"/> </td> </c:if>
						<c:if test="${fn:length(userInfor.codeLevel) == 0 }">
						<td align="left"  > <c:out value  = "" /></td> </c:if>
						
						
					</tr>	
					<tr>
						<td class="lbl_left"  >点数:</td>
						<c:if test="${fn:length(userInfor.codeLevel) > 0 }">
				    	<td align="left"> <c:out value = "${userInfor.total}" /></td> </c:if>
						
						<c:if test="${fn:length(userInfor.codeLevel) == 0 }">
						<td align="left"  > <c:out value  = "" /></td> </c:if>
						
					</tr>	
					</table>
					</div>
				</div>				
			</td>		
		</tr>
	</table>
	<div style="padding-left:100px;">&nbsp;</div>
		<!-- Begin vung button -->
	<div style="padding-left:45px;"/>
	<table border="0" cellpadding="4" cellspacing="0" width="300px">	
		<tr>
			<th width="200px" align="center">&nbsp;</th>
				<td>
					<input class="btn" type="submit" value="OK" />					
				</td>	
				
				
				
				
				
				<td><c:if test="${userInfor.userId == 0 }">
							<input class="btn" type="button" value="戻る"
								onclick="location.href='AddUserInput.do?action=back&key=${key}'" />
						</c:if> <c:if test="${userInfor.userId != 0 }">
							<input class="btn" type="button" value="戻る"
								onclick="location.href='EditInputUser.do?action=back&key=${key}&userId=${userInfor.userId}'" />
						</c:if></td>
				
				
				
				
				
				
				
				
				
				
				
		</tr>		
	</table>
	<!-- End vung button -->	
</form>
<!-- End vung input -->

<!-- Begin vung footer -->
<div class = "lbl_footer" >
	<br/><br/><br/><br/>
			Copyright ©　2010　ルビナソフトウエア株式会社. All rights reserved.
</div>
<!-- End vung footer -->
</body>

</html>