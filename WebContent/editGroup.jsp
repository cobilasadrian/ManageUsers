<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@page import="groups.Group"%>
<%@page import="users.User"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editare Grup</title>
<script type="text/javascript">   

	function deselectGroupMembersRadioButtons() {
	    var form = document.forms["groupForm"];
	    for( var i=0; i<form.length; i++ ){
	    	if( form[i].type == 'radio' && form[i].checked == true && form[i].name == 'idGroupMember'){
	    		form[i].checked = false;
	    	   }
	    }
	}
	
	function deselectUserMembersRadioButtons() {
	    var form = document.forms["groupForm"];
	    for( var i=0; i<form.length; i++ ){
	    	if( form[i].type == 'radio' && form[i].checked == true && form[i].name == 'idUserMember'){
	    		form[i].checked = false;
	    	   }
	    }
	}
		
</script>
</head>
<link rel="stylesheet" href="css/styles.css" type="text/css" /> 
<body>

<jsp:useBean id="group" class="groups.Group"/>
<jsp:useBean id="groupServices" class="groups.GroupServices"/>
<jsp:useBean id="user" class="users.User"/>
<jsp:useBean id="userServices" class="users.UserServices"/> 
  
<%
int idGroup = 0;
if(request.getParameter("idGroup") != null)
{
	idGroup = Integer.parseInt((String) request.getParameter("idGroup"));
	
	Group g = groupServices.getGroup(idGroup);
%>

<jsp:setProperty name="group" property="idGroup"  value="<%=g.getIdGroup()%>"/>
<jsp:setProperty name="group" property="groupName"  value="<%=g.getGroupName()%>"/>
<jsp:setProperty name="group" property="groupDisplayName"  value="<%=g.getGroupDisplayName()%>"/>
<jsp:setProperty name="group" property="creationDate"  value="<%=g.getCreationDate()%>"/>

<div class="alignCenter">
	<fieldset style=" display: inline; padding:20px;">
	<legend>Editare Grup</legend>
	
		<form method="post" action="EditGroup" name="groupForm">
		<div style="float:left; margin-right:20px; text-align:left;">	
		<table style="margin-bottom:10px; text-align:left;">
			<tr>
			<td>Nume Grup:</td>
			<td>
			<input type="text" name="editedGroupName" value="<jsp:getProperty name="group" property="groupName"/>"/>
			</td>
			</tr>
			<tr>
			<td>Nume Afisat:</td>
			<td>
			<input type="text" name="editedGroupDisplayName" value="<jsp:getProperty name="group" property="groupDisplayName"/>"/>
			</td>
			</tr>
		</table>
		<input type="text" name="editedIdGroup" value="<jsp:getProperty name="group" property="idGroup"/>" style="display:none;"/>
		<input type="submit" name="saveEditedGroupButton" value="Salveaza"/>
		<input type="button" value="Anuleaza" onclick="javascript:location.href='index.jsp'"/>
		</div>
	
		<div style="float:right;">
		<%
		List<Group> groupList = new ArrayList<Group>();
		groupList = groupServices.getChildrenGroupsFromGroup(group.getGroupName());
		
		List<User> userList = new ArrayList<User>();
		userList = userServices.getUsersFromGroup(group.getGroupName());
		
		if(groupList.size() != 0 || userList.size() != 0)
		{
		%>
			<table style="margin-bottom:10px;" cellpadding="5" class="tableStyle">
			<tr bgcolor="#f1f1f1">
				<td>ID</td>
				<td>Nume Membru</td>
				<td>Data Crearii</td>
			</tr>
			<%
			for(int i=0; i<groupList.size(); i++)
			{ 		
			%>
			
			<jsp:setProperty name="group" property="idGroup"  value="<%=groupList.get(i).getIdGroup()%>"/>
			<jsp:setProperty name="group" property="groupDisplayName"  value="<%=groupList.get(i).getGroupDisplayName()%>"/>
			<jsp:setProperty name="group" property="creationDate"  value="<%=groupList.get(i).getCreationDate()%>"/>
			
			<tr>
				<td>
				<input type="radio" name="idGroupMember" onclick="deselectUserMembersRadioButtons();" value="<jsp:getProperty name="group" property="idGroup"/>"/>
				<jsp:getProperty name="group" property="idGroup"/>
				</td>
				<td><jsp:getProperty name="group" property="groupDisplayName"/></td>
				<td><jsp:getProperty name="group" property="creationDate"/></td>
			</tr>
			
			<%}%>
			
			<%
			for(int i=0; i<userList.size(); i++)
			{ 		
			%>
			
			<jsp:setProperty name="user" property="idUser"  value="<%=userList.get(i).getIdUser()%>"/>
			<jsp:setProperty name="user" property="userLoginName"  value="<%=userList.get(i).getUserLoginName()%>"/>
			<jsp:setProperty name="user" property="creationDate"  value="<%=userList.get(i).getCreationDate()%>"/>
			
			<tr>
				<td>
				<input type="radio" name="idUserMember" onclick="deselectGroupMembersRadioButtons();" value="<jsp:getProperty name="user" property="idUser"/>"/>
				<jsp:getProperty name="user" property="idUser"/>
				</td>
				<td><jsp:getProperty name="user" property="userLoginName"/></td>
				<td><jsp:getProperty name="user" property="creationDate"/></td>
			</tr>
			
			<%}%>
			</table>
				
			<input type="submit" name="deleteMemberButton" value="Elimina Membru"/>
		<%}%>
		<input type="submit" name="addGroupMemberButton" value="Adauga Grup"/>
		<input type="submit" name="addUserMemberButton" value="Adauga Utilizator"/>
		</div>	
		</form>
	</fieldset>
</div>
<%
} else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}
%>
</body>
</html>