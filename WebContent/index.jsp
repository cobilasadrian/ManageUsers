<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@page import="groups.Group"%>
<%@page import="users.User"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modulul de administrare a utilizatorilor</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" /> 
</head>
<body>

<jsp:useBean id="group" class="groups.Group"/>
<jsp:useBean id="groupServices" class="groups.GroupServices"/>
<jsp:useBean id="user" class="users.User"/>
<jsp:useBean id="userServices" class="users.UserServices"/> 

<div class="alignCenter"><h3>Grupuri</h3></div>
<form action="EditGroup" method="post">
	<table align="center" cellpadding="5" class="tableStyle">
		<tr bgcolor="#f1f1f1" >
			<td>ID</td>
			<td>Nume Grup</td>
			<td>Nume Afisat</td>
			<td>Data Crearii</td>
		</tr>

		<%
		List<Group> groupList=new ArrayList<Group>();
		groupList = groupServices.getAllGroups();
		
		for(int i=0; i<groupList.size(); i++)
		{	
		%>
		<jsp:setProperty name="group" property="idGroup"  value="<%=groupList.get(i).getIdGroup()%>"/>
		<jsp:setProperty name="group" property="groupName"  value="<%=groupList.get(i).getGroupName()%>"/>
		<jsp:setProperty name="group" property="groupDisplayName"  value="<%=groupList.get(i).getGroupDisplayName()%>"/>
		<jsp:setProperty name="group" property="creationDate"  value="<%=groupList.get(i).getCreationDate()%>"/>
		<tr>
			<td>
			<input type="radio" name="idGroup" value="<jsp:getProperty name="group" property="idGroup"/>"/>
			<jsp:getProperty name="group" property="idGroup"/>
			</td>
			<td>
			<jsp:getProperty name="group" property="groupName"/>
			</td>
			<td>
			<jsp:getProperty name="group" property="groupDisplayName"/>
			</td>
			<td>
			<jsp:getProperty name="group" property="creationDate"/>
			</td>
		</tr>
		<%}%>
	</table>
	<div class="alignCenter" style="margin-top:10px;">
	<input type="submit" name="editGroupButton" value="Editare Grup" />
	<input type="submit" name="deleteGroupButton" value="Stergere Grup" />
	<input type="button" value="Creare Grup" onclick="javascript:location.href='addNewGroup.jsp'"/>
	</div>
</form>

<div class="alignCenter"><h3>Utilizatori</h3></div>
<form action="EditUser" method="post">
	<table align="center" cellpadding="5" class="tableStyle">
		<tr bgcolor="#f1f1f1">
			<td>ID</td>
			<td>Nume Utilizator</td>
			<td>Nume Logare</td>
			<td>Data Crearii</td>
		</tr>
		
		<%
		List<User> userList=new ArrayList<User>();
		userList = userServices.getAllUsers();
			
		for(int i=0; i<userList.size(); i++)
		{	
		%>
		
		<jsp:setProperty name="user" property="idUser"  value="<%=userList.get(i).getIdUser()%>"/>
		<jsp:setProperty name="user" property="userName"  value="<%=userList.get(i).getUserName()%>"/>
		<jsp:setProperty name="user" property="userLoginName"  value="<%=userList.get(i).getUserLoginName()%>"/>
		<jsp:setProperty name="user" property="creationDate"  value="<%=userList.get(i).getCreationDate()%>"/>
		
		<tr>
			<td>
			<input type="radio" name="idUser" value="<jsp:getProperty name="user" property="idUser"/>"/>
			<jsp:getProperty name="user" property="idUser"/>
			</td>
			<td>
			<jsp:getProperty name="user" property="userName"/>
			</td>
			<td>
			<jsp:getProperty name="user" property="userLoginName"/>
			</td>
			<td>
			<jsp:getProperty name="user" property="creationDate"/>
			</td>
		</tr>
		<%}%>
	</table>
	<div class="alignCenter" style="margin-top:10px;">
	<input type="submit" name="editUserButton" value="Editare Utilizator" />
	<input type="submit" name="deleteUserButton" value="Stergere Utilizator" />
	<input type="button" value="Creare Utilizator" onclick="javascript:location.href='addNewUser.jsp'"/>
	</div>
</form>
		
</body>
</html>
