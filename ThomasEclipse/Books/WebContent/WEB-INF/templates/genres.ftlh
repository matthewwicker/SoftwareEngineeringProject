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
                 <select name="searchby"> <span id="search_concept">Search by&nbsp</span><span class="caret"></span>
                     <option value="title">Title</option>
                     <option value="isbn">ISBN</option>
                     <option value="genre">Genre</option>
                     <option value="author">Author</option>
                 </select>
             </div>
             <input type="hidden" name="search_param" value="all" id="search_param">
             <input type="text" class="form-control" name="searchval" placeholder="Search..." value="">
              <span class="input-group-btn">
                 <button type="submit" class="btn btn-default" type="button" name="Task" value="Search"><span class="glyphicon glyphicon-search"></span></button>
               </span>
       </div>
          </div>
      </div>
     </div> 

  </div>
</div>

<!---NAVIGATION BAR-->
<nav class="navbar">
  <div class="container-fluid">
    
    <div class="collapse navbar-collapse" id="myNavbar">
      
       <div class="collapse navbar-collapse" id="myNavbar">
      
      <ul class="nav navbar-nav">
      
                <li><button class="btn btn-link"  type="submit" name="navigator" value="home.ftlh">Home</button></li>
        <li class="dropdown">
			  <button class="btn btn-link dropdown-toggle" type="button" data-toggle="dropdown">Genres
			  <span class="caret"></span></button>
          <ul class="dropdown-menu">
           <li><button class="btn btn-link"  type="submit" name="Task" value= "Search_genre_YA"  >Young Adult</button></li>
           <li><button class="btn btn-link"  type="submit" name="Task" value= "Search_genre_kids">Kids</button></li>
           <li><button class="btn btn-link"  type="submit" name="Task" value= "Search_genre_scifi" >Science Fiction</button></li>
           </ul>
        </li>
                <li><button class="btn btn-link"  type="submit" name="Task" value= "Search_genre_best">Best Selling</button></li>
                <li><button type="submit" name="Task" value= "Search_genre_deals"  class="btn btn-link">Deals</button></li>
                <li><button type="submit" name="Task" value= "Search_genre_favorites"  class="btn btn-link">Our Favorites</button></li>
        </ul>
      <ul class="nav navbar-nav navbar-right">

        <li class="dropdown">
        				<button class="btn btn-link dropdown-toggle" type="button" data-toggle="dropdown">${loginbutton}
			  					<span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-lr animated slideInRight" role="menu">
                            <div class="col-lg-12">
                                <div class="text-center"><h3><b>Log In</b></h3></div>
                                <form id="ajax-login-form" action="m" method="post" role="form" autocomplete="off">
                                    <div class="form-group">
                                        <label for="username">Email</label>
                                        <input type="text" name="qusername" id="qusername" tabindex="1" class="form-control" placeholder="Username" value="" autocomplete="off">
                                    </div>

                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" name="qpassword" id="qpassword" tabindex="2" class="form-control" placeholder="Password" autocomplete="off">
                                    </div>

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-xs-7">
                                                <input type="checkbox" tabindex="3" name="remember" id="remember">
                                                <label for="remember"> Remember Me</label>
                                            </div>
                                            <div class="col-xs-5 pull-right">
                                                <button type="submit" name="Task" value="SignInHead" tabindex="4" class="btn btn-success">Log In</button>
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
        <li><button class="btn btn-link" type="submit" name="Task" value="GoToAccount"><span class="glyphicon glyphicon-user"></span> Your Account</button></li>
        <li><button class="btn btn-link" type="submit" name="Task" value="GoToCart"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</button></li>
      </ul>

    </div>
  </div>
</nav>
                                                <!---BODY-->
<div class="container">
  
  <div class="page-header">
    <h2>${searchheader}</h2>
  </div>

  <div class="row">

     <div class="btn-group show-on-hover">
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
            Sort By <span class="caret"></span>
          </button>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">ABC <span class="glyphicon glyphicon-arrow-down"></a></li>
            <li><a href="#">ABC <span class="glyphicon glyphicon-arrow-up"></a></li>
            <li><a href="#">Price <span class="glyphicon glyphicon-arrow-down"></a></li>
            <li><a href="#">Price <span class="glyphicon glyphicon-arrow-up"></a></li>
          </ul>
        </div>

 <#list books as book>
      <div class="col-sm-3">
      <button  type="submit" id=${book.ISBN} name="Task" onclick="javafunction('${book.ISBN}');">
      <div class="panel panel-default">
        <div class="panel-body"><img src="${book.image}" class="img-responsive"></div>
        <div class="panel-footer">
            ${book.title} <br/>
            ${book.author} <br/>
            $${book.price}<br/>
            Rating: ${book.rating}/5.0<br/>
        </div>
      </div>
      </button>
    </div>
</#list>
<script type="text/javascript">

		
         function javafunction(isbn){
        	 		try {
        	 			document.getElementById(isbn).setAttribute('value', "GoToItem_" + isbn);
        	 		}
        	 		catch(err) {
        	 			alert("Failing to get " + isbn)
        	 			alert(err.message);
        	 		}
        	 		}
 </script>

</div><br><br>
</div>
    <!-- Footer 2 End -->
    </form>
</body>
</html>
