function validateUserName(username,password){
	var returnValue = true;
	if (username == ""){
    	alert("Please provide username");
    	returnValue = false;
	}
	else if (password == ""){
    	alert("Please provide password");
    	returnValue = false;
	}
    return returnValue;
}