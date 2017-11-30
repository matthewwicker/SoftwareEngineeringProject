<!DOCTYPE html>
<html>
<head>
<title>Books</title>
    <meta charset="utf-8" />
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
<!---BODY-->
<div id="content-background">
            <div class="page-header">
			    <h2>Dashboard</h2>
			</div>
			
	   <table>
                <tr>
                  <th>First Name</th>
                  <th><input type="text" name='fname' value=${"fname"}><br/><br/></th>
                </tr>
                <tr>
                  <th>Last Name</th>
                  <th><input type="text" name="lname" value=${"lname"}><br/><br/></th>
                </tr>
                <tr>
                  <th>Phone No.</th>
                  <th><input type="text" name="pnumb" value=${"phone"}><br/><br/></th>
                </tr>
                <tr>
                  <th>Email</th>
                  <th><input type="text" name="email" value=${"email"}><br/><br/></th>
                </tr>
                <tr>
                  <th>Shipping Address</th>
                <th> <input type="text" name="ship" value=${"addressline1"}><br/>
                     <input type="text" name="ship" value=${"addressline2"}></p></th>
                </tr>
                
                
                
      </table>
                
                <button id="cart"type="submit" name="Task" value="UpdateInfo">Update Info</button>
                <button id="cart" type="submit" name="Task" value="AddNewBookShipment">Change Order Status</button>
     			<button id="cart"type="submit" name="Task" value="SignOut">Sign Out</button><br><br>
               <!-----  <button id="cart" type="submit" name="Task" value="GoToCreateBook">Create Book</button> ---->
                <button id="cart" type="submit" name="Task" value="GoToCart">View Cart</button>
                <!----- <button id="cart" type="submit" name="Task" value="GoToPromotion">Create/Edit Promotion</button> ---->
                <button id="cart" type="submit" name="Task" value="UpdatePromoPref">Update Promo Preference </button>
                </br>
                <th>Validate</th>
                <th> <input type="text" name="vcode" value=${"vcode"}><br/>
                </tr>
                </br>
                </br>
                <button id="cart" type="submit" name="Task" value="Validation">Validate Account</button>
                </br>
                <h2 style="color:green">Validation successful</h2>
                
</div>
    

   </form>

</body>
</html>
</body>
</html>