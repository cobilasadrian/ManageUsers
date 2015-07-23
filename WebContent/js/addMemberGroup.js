window.onload = function()
{	
	var ListMembers = document.getElementById("ListMembers");
	var selectedValue = ListMembers.options[ListMembers.selectedIndex].text;
	document.getElementById("NewMember").value = selectedValue;	
}

function changeList(element)
{
	var selectedValue = element.options[element.selectedIndex].text;
	document.getElementById("NewMember").value = selectedValue;	   
}