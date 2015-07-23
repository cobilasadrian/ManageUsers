
function checkUserPassword(item1,item2,item3)
{
	var PasswordInput = document.getElementById(item1);
	var PasswordCheckInput = document.getElementById(item2);
	var Button = document.getElementById(item3);
	
	if(PasswordCheckInput.value == PasswordInput.value){
		PasswordCheckInput.style.borderColor = "green";
		Button.disabled = false;
	}
	else {
		PasswordCheckInput.style.borderColor = "red";
		Button.disabled = true;		
	}
}


