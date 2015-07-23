<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@page import="users.User"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adaugarea unui nou membru</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<script src="js/addMemberGroup.js"></script>
</head>
<body>

<jsp:useBean id="user" class="users.User"/>
<jsp:useBean id="userServices" class="users.UserServices"/>

<%
int idGroup = 0;
if(request.getParameter("idGroup") != null)
{
	idGroup = Integer.parseInt((String) request.getParameter("idGroup"));
%>	
<div class="alignCenter">
	<fieldset style="display: inline; padding:20px;">
		<legend>Adauga utilizator</legend>
		<form action="EditGroup" method="post">
			<label>Nume Utilizator:</label>
			<input  type="text" name="NewMemberUserName" id="NewMember"/>
			
			<input type="text" name="editedIdGroup" value="<%=idGroup %>" style="display:none;"/>
			
			<select name="NewIdMemberUser" onchange="changeList(this)" id="ListMembers">
				<%
				List<User> list=new ArrayList<User>();
				list = userServices.getUsersFromOutsideGroup(idGroup);
			
				for(int i=0; i<list.size(); i++)
				{ 
				%>
				<jsp:setProperty name="user" property="idUser"  value="<%=list.get(i).getIdUser()%>"/>
				<jsp:setProperty name="user" property="userLoginName"  value="<%=list.get(i).getUserLoginName()%>"/>
				
				<option value="<jsp:getProperty name="user" property="idUser"/>">
				<jsp:getProperty name="user" property="userLoginName"/>
				</option>
				<%} %>
			</select>	
			<div class="alignCenter" style="margin:10px;">
			<input type="submit" name="saveMemberUserButton" value="Adauga Utilizator"/>
			<input type="button" value="Anuleaza" onclick="javascript:location.href='editGroup.jsp?idGroup=<%=idGroup%>'"/>
			</div>
		</form>	
	</fieldset>
</div>
<%} else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}%>
</body>
</html>