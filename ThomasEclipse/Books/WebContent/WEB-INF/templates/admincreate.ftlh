<!DOCTYPE html>
<html>
<head>
<title>Books</title>
    <meta charset="utf-8" />
        <script type='text/javascript' src="optional.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="custom.css" rel="stylesheet" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
      @import url('https://fonts.googleapis.com/css?family=Lobster');
      @import url('https://fonts.googleapis.com/css?family=Roboto');
  </style>
</head>
<body>

  <form action = "myservlet" method = "post">
<!---HEADER-->
<div class="jumbotron">
  <div class="container-fluid">
    
    <div class="row">  
      <div class="col-md-8">
          <img src="book.png" class="img-responsive">
          <h1><small>yourShelf</small></h1>
      </div>
      
      <div class="col-sm-4">
        <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                      <span id="search_concept">Search by&nbsp</span><span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#title">Title</a></li>
                      <div class="bropdown-content">
                      <li><a href="#title">Title</a></li>
                      <li><a href="#author">Author</a></li>
                      <li><a href="#isbn">ISBN</a></li>
                      <li><a href="#year">Year</a></li>
                    </ul>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" placeholder="Search...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
          </div>
      </div>
     </div> 

  </div>
</div>

<!---NAVIGATION BAR-->
<nav class="navbar">
  <div class="container-fluid">
    
    <div class="collapse navbar-collapse" id="myNavbar">
      
      <ul class="nav navbar-nav">
                <li><button class="btn btn-link" type="submit" name="navigator" value="home.ftlh">Home</button></li>
        
		<li class="dropdown">
			  <button class="btn btn-link dropdown-toggle" type="button" data-toggle="dropdown">Genres
			  <span class="caret"></span></button>
			  <ul class="dropdown-menu">
			    <li><button class="btn btn-link" type="submit" name="navigator" value="genres.ftlh">Science Fiction</button></li>
			    <li><button class="btn btn-link" type="submit" name="navigator" value="genres.ftlh">Young Adult</button></li>
			    <li><button class="btn btn-link" type="submit" name="navigator" value="genres.ftlh">Kids</button></li>
			  </ul>
			</li>
                <li><button class="btn btn-link" type="submit" name="navigator" value="best.ftlh">Best Selling</button></li>
                <li><button class="btn btn-link" type="submit" name="navigator" value="deals.ftlh">Deals</button></li>
                <li><button class="btn btn-link" type="submit" name="navigator" value="favorites.ftlh">Our Favorites</button></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">

        <li class="dropdown">
        				<button class="btn btn-link dropdown-toggle" type="button" data-toggle="dropdown">Log In
			  					<span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-lr animated slideInRight" role="menu">
                            <div class="col-lg-12">
                                <div class="text-center"><h3><b>Log In</b></h3></div>
                                <form id="ajax-login-form" action="m" method="post" role="form" autocomplete="off">
                                    <div class="form-group">
                                        <label for="username">Email</label>
                                        <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="" autocomplete="off">
                                    </div>

                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" autocomplete="off">
                                    </div>

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-xs-7">
                                                <input type="checkbox" tabindex="3" name="remember" id="remember">
                                                <label for="remember"> Remember Me</label>
                                            </div>
                                            <div class="col-xs-5 pull-right">
                                                <button type="submit" name="Task" value="SignIn" tabindex="4" class="btn btn-success">Log In</button>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="text-center">
                                                    <li><button class="btn btn-link" type="submit" name="navigator" value="" style="background-color:white;text-transform:lowercase">Forgot Password?</button></li>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" class="hide" name="token" id="token" value="a465a2791ae0bae853cf4bf485dbe1b6">
                            </div>
                        </ul>
        </li>
        <li><button class="btn btn-link" type="submit" name="navigator" value="account.ftlh"><span class="glyphicon glyphicon-user"></span> Your Account</button></li>
        <li><button class="btn btn-link" type="submit" name="navigator" value="cart.ftlh"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</button></li>
      </ul>

    </div>
  </div>
</nav>
    <div class="container">
  
              
  <div class="col-md-6">
  
   <div class="text-center">
          <h3>Edit or Create User</h3>
        </div>
        <input name="fname" type="text" placeholder="First Name" autofocus="autofocus" class="input pass" value=""/>
        <input name="lname" type="text" placeholder="Last Name" class="input pass" value=""/>
        <input name="type" type="text" placeholder="Account Type" class="input pass" value=""/>
        <input name="email" type="email" placeholder="Email address" class="input pass" value=""/>
        <input name="password" type="password" placeholder="Choose a password (must be more than 6 characters)" class="input pass" value=""/>
        <input name="cpassword" type="password" placeholder="Confirm password" class="input pass" value=""/>
        <input name="phone" type="text" placeholder="Phone Number" value="3193193199" class="input pass" value=""/>
        <input name="address1" type="text" placeholder="Street Address" value="1004 Cedar St" class="input pass" value=""/>
        <input name="address2" type="text" placeholder="City, State, Zip" value="Athens, GA 30602" class="input pass" value=""/>
        <button type="submit" name="Task" onclick="return requiredParam(fname.value,lname.value,email.value,phone.value)" class="inputButton" value="CreateUser">Make Change?</button>
        
       </div></div>
<!---FOOTER-->
    <footer>
     <div class="container-fluid">
       <div class="row">  
         
                    
            <div class="col-md-3 col-sm-6 col-xs-12">
                  <ul class="menu">
                        <span>Contact</span>       
                        <li>
                            770-584-8574
                        </li>
                        <li>
                            95 Kuhl Avenue
                            <br/>Atlanta, Georgia 30329
                        </li> 
                        <li>
                            bookyourshelf@mail.com
                        </li> 
                   </ul>
            </div>

        </div>
    </footer> 
    <!-- Footer 2 End -->
     </form>
</body>
</html>
