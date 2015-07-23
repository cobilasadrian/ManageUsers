<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@page import="groups.Group"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adaugarea unui nou membru</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<script src="js/addMemberGroup.js"></script>
</head>
<body>
<jsp:useBean id="group" class="groups.Group"/>
<jsp:useBean id="groupServices" class="groups.GroupServices"/>
<%
int idGroup = 0;
if(request.getParameter("idGroup") != null)
{
	idGroup = Integer.parseInt((String) request.getParameter("idGroup"));
%>	
<div class="alignCenter">
	<fieldset style=" display: inline; padding:20px;">
		<legend>Adauga grup</legend>
		<form action="EditGroup" method="post">
			<label>Nume Grup:</label>
			<input  type="text" name="NewMemberGroupName"  id="NewMember"/>
	
			<input type="text" name="editedIdGroup" value="<%=idGroup %>" style="display:none;"/>
			
			<select name="NewIdMemberGroup"  onchange="changeList(this)" id="ListMembers">
				<%
				List<Group> list=new ArrayList<Group>();
				list = groupServices.getGroupsFromOutsideGroup(idGroup);
			
				for(int i=0; i<list.size(); i++)
				{ 
				%>
				<jsp:setProperty name="group" property="idGroup"  value="<%=list.get(i).getIdGroup()%>"/>
				<jsp:setProperty name="group" property="groupDisplayName"  value="<%=list.get(i).getGroupDisplayName()%>"/>
				
				<option value="<jsp:getProperty name="group" property="idGroup"/>">
				<jsp:getProperty name="group" property="groupDisplayName"/>
				</option>
				<%} %>
			</select>
			<div class="alignCenter" style="margin:10px;">
			<input type="submit" name="saveMemberGroupButton" value="Adauga Grup"/>
			<input type="button" value="Anuleaza" onclick="javascript:location.href='editGroup.jsp?idGroup=<%=idGroup%>'"/>
			</div>	
		</form>	
	</fieldset>
</div>	
<%} else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}%>
</body>
</html>