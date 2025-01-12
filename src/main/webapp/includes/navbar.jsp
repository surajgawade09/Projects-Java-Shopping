<style>
.nav{
    position: fixed;
    top: 0;
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 70px;
    line-height: 70px;
    background:black;
    z-index: 100;
}
.nav-logo p{
    color: white;
    font-size: 25px;
    font-weight: 600;
}
.nav-menu ul{
    display: flex;
}
.nav-menu ul li{
    list-style-type: none;
}
.nav-menu ul li .link{
    text-decoration: none;
    font-weight: 300;
    color: #fff;
    padding-bottom: 10px;
    margin: 0 30px;
}
.link:hover, .active{
    border-bottom: 2px solid #fff;
}

ul li ul.dropdown li {
display: block;
background: black;
margin: 2px 0px;
}
ul li ul.dropdown {
width:auto;
background: black;
position: absolute;
z-index: 999;
display: none;
}
ul li a:hover {
background: black;
}
ul li:hover ul.dropdown{
display: block;
}
</style>

<nav class="nav">
        <div class="nav-logo">
            <p style="margin-left: 30px">LOGO .</p>
        </div>
        <div class="nav-menu" id="navMenu">
            <ul>
                <li><a href="Home.jsp" class="link active">Home</a></li>
                <li>
       				<a href="#" class="link ">Categories &#x25BE;</a>
       					<ul class="dropdown">
       					<li><a href="ProductView.jsp?view=all" class="link ">View All</a></li>
        				<li><a href="ProductView.jsp?view=electronics" class="link ">Electronics</a></li>
       					 <li><a href="ProductView.jsp?view=clothing" class="link ">Clothing</a></li>
        				<li><a href="ProductView.jsp?view=accessories" class="link ">Accessories</a></li>
       					</ul>
       			</li>
       			<%if(auth != null){ %>
       				<li><a href="CartView.jsp" class="link">Cart</a></li>
       				<li><a href="OrderView.jsp" class="link">Orders</a></li>
                    <li><a href="AccountView.jsp" class="link">Account</a></li>
                    <li><a href="SignOutController"><button type="button" class="btn btn-outline-warning" style="margin-right: 30px">Logout</button></a></li>
       				<% }
       			
       				else{%>
       					<li><a href="LoginSignUp.jsp"><button type="button" class="btn btn-outline-warning" style="margin-right: 30px">Signin/Login</button></a></li>
       				<%}%>
                
                 
                 
            </ul>
        </div>
    </nav>