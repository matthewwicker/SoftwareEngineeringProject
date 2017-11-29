<!DOCTYPE html>
<html>
<head>
<title>Books</title>
    <meta charset="utf-8" />
    <link href="custom.css" type="text/css" rel="stylesheet" />
    <style>
    @import url('https://fonts.googleapis.com/css?family=Lobster');
    </style>

</head>
<body>
    <div id="wrapper">
        <div id="header">
            <img src="book.png" style="position: absolute; height: 80px"/>
            <a href="home.ftlh" style="text-decoration:none"><h1><small>your</small>Shelf</h1></a>
            <div id="search" >
                <input type="text" name="searchbar" value="Search" style="height:25px; border: 1px solid gray; float:relative;padding-right:0px">
                <div class="bropdown" style="float: absolute; height: 29px;padding-left:3px;padding-right:80px">
                    <button class="bropbtn" style="float: absolute;height: 29px;">Search by &nabla;</button>
                      <div class="bropdown-content">
                         <a> Title  </a> 
                         <a> Author </a> 
                         <a> ISBN   </a> 
                         <a> year   </a> </div></div>
           </div>
        </div>
        <div id="navigation">
            <ul class="navi">
                <li> <div class="dropdown">
                     <button class="dropbtn">Genres</button>
                       <div class="dropdown-content">
                         <a href="genres.html">Science Fiction</a>
                         <a href="genres.html">Young Adult</a>
                         <a href="genres.html">Kids</a>
                       </div> </div> </li>
                <li><a href="best.html">Best Selling</a></li>
                <li><a href="deals.html">Deals</a></li>
                <li><a href="favorites.html">Our Favorites</a></li>
                <li style="float: right; padding-right: 95px"><a href="account.ftlh">Account</a></li>
                <li style="float: right; padding-top: 0px"><a href="cart.html" style="padding-top: 0px"><img src="cart.png" style="height: 38px; align: top; border: 1.5px solid white; border-radius: 20px"  /></a></li>
            </ul>
            <div class="bropdown" style="float: right; padding-right: 10px; height: 20px; width:80px">
                    <button class="bropbtn" style="height: 35px; background: #DB7093"><a href="signin.html" style="color:white; text-decoration:none">LOG IN</a></button>
                      <div class="bropdown-content" style="padding: 20px">
                          <div id="username">
                              <input type="text" name="username" value="Username" style="width: 120px">
                          </div>
                          <div id="password">
                              <input type="password" name="password" value="Password" style="width: 120px">
                         </div>
                          <button style="height: 30px">
                          <a href="account.ftlh">Enter</a> 
                            </button></div></div>
        </div>
        <div id="content-background">
        <div id="content">
            <div id="pageTitle">
               Add Book
            </div>
            <form action = "myservlet" method = "post">
            <div><table style="width:70%; position: absolute; padding-top:50px;">
                <tr>
                <th>
                ISBN 
                </th><th>
                <input type="text" name='isbn' value=${"isbn"}><br/>
                </th></tr>
                <tr>
                <th>
	        Title  
                </th><th>
                <input type="text" name="title" value=${"title"}><br/>
                </th></tr>
                <tr>
                <th>
		Author 
                </th><th>
                <input type="text" name="author" value=${"author"}><br/>
                </th></tr>
                <tr>
                <th>
		Price
                </th><th>
                <input type="text" name="price" value=${"price"}><br/>
                </th></tr>
                <tr>
                <th>
		Genre 
                </th><th>
                 <input type="text" name="genre" value=${"genre"}><br/>
                </th></tr>
                <tr>
                <th>
		Description 
                </th><th>
		<input type="text" name="description" value=${"descr"}><br/>
                </th></tr>
                <tr>
                <th>
		Quantity
                </th><th>
		<input type="text" name="quantity" value=${"quantity"}></th></tr>
		         <tr>
                <th>
		Threshold
                </th><th>
		<input type="text" name="threshold" value=${"thresh"}>
                </th></tr><tr><th></th><th>
      	<button type="submit" name="Task" onclick="return requiredParam(isbn.value, title.value, author.value, price.value, genre.value, description.value, quantity.value, threshold.value)" class="inputButton" value="CreateBook">Create Book</button>
        
    
            	</th></tr></table></div> 
         </form>
         </div></div>
    </div>
    <!---FOOTER-->
    <footer>
     <div class="container-fluid">
       <div class="row">  
            
            <div class="col-md-3 col-sm-6 col-xs-12">
                    <ul class="menu">
                         <span>Menu</span>    
                         <li>
                            <a href="home.ftlh">Home</a>
                          </li>
                               
                          <li>
                             <a href="genres.html">Genres</a>
                          </li>
                               
                          <li>
                            <a href="deals.html">Deals</a>
                          </li>
                               
                          <li>
                             <a href="best.html">Best Selling</a>
                          </li>
                          <li>
                             <a href="favorites.html">Our Favorites</a>
                          </li>
                          <li>
                             <a href="support.html">Support</a>
                          </li>
                     </ul>
            </div>
           
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
        </div>
    </footer> 
    <!-- Footer 2 End -->
   </form>
</body>
</html>
