<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="users.User"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editare Utilizator</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<script src="js/checkPassword.js"></script> 
</head>
<body>
<jsp:useBean id="user" class="users.User"/>
<jsp:useBean id="userServices" class="users.UserServices"/> 
 <%
int idUser = 0;
if(request.getParameter("idUser") != null)
{
	idUser = Integer.parseInt((String) request.getParameter("idUser"));
	
	User u = userServices.getUser(idUser);
%>
<jsp:setProperty name="user" property="idUser"  value="<%=u.getIdUser()%>"/>
<jsp:setProperty name="user" property="userName"  value="<%=u.getUserName()%>"/>
<jsp:setProperty name="user" property="userLoginName"  value="<%=u.getUserLoginName()%>"/>
<jsp:setProperty name="user" property="userPassword"  value="<%=u.getUserPassword()%>"/>

<div style="text-align:center;">
	<fieldset style=" display: inline; padding:20px;">
	<legend>Editare Utilizator</legend>
		<form action="EditUser" method="post" name="userForm">
		<div style="float:left; margin-right:20px;">	
		<table style="margin-bottom:10px; text-align:left;">
			<tr>
			<td>Nume Utilizator:</td>
			<td>
			<input type="text" name="editedIdUser" value="<jsp:getProperty name="user" property="idUser"/>" style="display:none;"/>
			<input type="text" name="editedUserName" value="<jsp:getProperty name="user" property="userName"/>"/>
			</td>
			</tr>
			<tr>
			<td>Nume Logare:</td>
			<td>
			<input type="text" name="editedUserLoginName" value="<jsp:getProperty name="user" property="userLoginName"/>"/>
			</td>
			</tr>
			<tr>
			<td>Parola:</td>
			<td>
			<input type="password" name="editedUserPassword" value="<jsp:getProperty name="user" property="userPassword"/>" id="editedUserPasswordInput"/>
			</td>
			<tr>
			<td>Verifica Parola:</td>
			<td>
			<input type="password" name="editedUserPasswordCheck" oninput="checkUserPassword('editedUserPasswordInput','editedUserPasswordCheckInput','saveEditedUserButton')" id="editedUserPasswordCheckInput" value="<jsp:getProperty name="user" property="userPassword"/>"/>
			</td>
			</tr>
			<tr>		
		</table>
		<input type="submit" name="saveEditedUserButton" value="Salveaza" id="saveEditedUserButton">
		<input type="button" value="Anuleaza" onclick="javascript:location.href='index.jsp'"/>
		</div>
		</form>
	</fieldset>
</div>
<%} else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}%>	

</body>
</html>