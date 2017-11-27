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
	else if (username != "sarah" || password != "elliott" ){
    	alert("Invalid username and password combination");
    	returnValue = false;
	}
    return returnValue;
}