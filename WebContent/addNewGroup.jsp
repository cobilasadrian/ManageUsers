<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crearea unui grup</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" /> 
</head>
<body>
<div class="alignCenter" style="margin-top:10px;">

	<fieldset style=" display: inline; padding:20px;">
	<legend>Creare Grup</legend>
		<form action='AddNewGroup' method="post">
		<label>Nume Grup:</label><br/>
		<input type="text" name="newGroupName"/><br/><br/>
		<label>Nume Afisat:</label><br/>
		<input type="text" name="newGroupDisplayName"/><br/><br/>
		<input type="submit" name="addNewGroupButton" value="Salveaza"/>
		<input type="button" value="Anuleaza" onclick="javascript:location.href='index.jsp'"/>
		</form>
	</fieldset>
	
</div>

</body>
</html>