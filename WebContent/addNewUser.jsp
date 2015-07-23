<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creare Utilizator</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<script src="js/checkPassword.js"></script> 
</head>
<body>

<div class="alignCenter" style="margin-top:10px;">

	<fieldset style=" display: inline; padding:20px;">
	<legend>Creare Utilizator</legend>
		<form action="AddNewUser" method="post">
		<label>Nume Utilizator:</label><br/>
		<input type="text" name="NewUserName"/><br/><br/>
		<label>Nume Logare:</label><br/>
		<input type="text" name="NewUserLoginName"/><br/><br/>
		<label>Parola:</label><br/>
		<input type="password" name="NewUserPassword"  id="NewUserPasswordInput"/><br/><br/>
		<label>Verifica Parola:</label><br/>
		<input type="password" name="NewUserPasswordCheck" oninput="checkUserPassword('NewUserPasswordInput','NewUserPasswordCheckInput','addNewUserButton')" id="NewUserPasswordCheckInput"/><br/><br/>
		<input type="submit" name="addNewUserButton" value="Salveaza" disabled="disabled" id="addNewUserButton"/>
		<input type="button" value="Anuleaza" onclick="javascript:location.href='index.jsp'"/>
		</form>
	</fieldset>

</div>

</body>
</html>