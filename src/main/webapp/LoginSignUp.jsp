<%@page import="shop.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<title>Login&Signup</title>
<link rel="stylesheet" href="boxicons/css/boxicons.min.css">
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<link href="LoginSignUp.css" rel="stylesheet">
</head>
<body>	
	<div class="wrapper">
    <nav class="nav">
        <div class="nav-button">
            <button class="btn white-btn" id="loginBtn" onclick="login()">Sign In</button>
            <button class="btn" id="registerBtn" onclick="register()">Sign Up</button>
        </div>
    </nav>
	<div class="container text-center">
  <div class="row align-items-start">
    <div class="col">
    </div>
    <div class="col">
      <div class="form-box">
        
        <!------------------- login form -------------------------->
        <div class="login-container" id="login">
            <div class="top">
                <span>Don't have an account? <a href="#" onclick="register()">Sign Up</a></span>
                <header>Login</header>
            </div>
            <form action="LoginController">
            <div class="input-box">
                <input type="text" class="input-field" name="Lemail" placeholder="Username" required="required">
                <i class="bx bx-user"></i>
            </div>
            <div class="input-box" >
                <input type="password" class="input-field"  name="Lpassword" placeholder="Password"  required="required" >
                <i class="bx bx-lock-alt"></i>
            </div>
            <div class="input-box">
                <input type="submit" class="submit" value="Sign In">
            </div>
            </form>
            <div class="two-col">
				<%
				
				String strlog=(String)session.getAttribute("loginerror");
				String strsign=(String)session.getAttribute("signuperror");
				if(!session.isNew())
				{
					if(strlog!=null)
					{
				%>
				<label style="margin-left: 200px;color: red;"><% out.print(strlog); %></label>
				<%} 
					else if(strsign!=null)
					{
				%>
				<label style="margin-left: 150px;color: red;"><% out.print(strsign); %></label>
				<%} %>
					<%session.invalidate(); }%>
				
            </div>
        </div>
        <!------------------- registration form -------------------------->
        <div class="register-container" id="register">
            <div class="top">
                <span>Have an account? <a href="#" onclick="login()">Login</a></span>
                <header>Sign Up</header>
            </div>
            <form action="SignUpController">
            <div class="two-forms">
                <div class="input-box">
                    <input type="text" class="input-field" name="Rcustname" placeholder="Customer name" required="required">
                    <i class="bx bx-user"></i>
                </div>
                <div class="input-box">
                    <input type="tel" class="input-field" name="Rtel" placeholder="phone number" required="required" pattern="[0-9]{10}">
                    <i class="bx bx-user"></i>
                </div>
            </div>
            <div class="input-box">
                <input type="email" class="input-field" name="Remail" placeholder="Email" required="required">
                <i class="bx bx-user"></i>
            </div>
            <div class="input-box">
                <input type="password" class="input-field" name="Rpassword" placeholder="Password" required="required">
                <i class="bx bx-lock-alt"></i>
            </div>
            
            <div class="input-box">
                <input type="submit" class="submit" value="Register">
            </div>
            </form>
            <div class="two-col">
            </div>
        </div>
        
    </div>
</div> 
    </div>
  </div>
</div>

<script>
   
   function myMenuFunction() {
    var i = document.getElementById("navMenu");
    if(i.className === "nav-menu") {
        i.className += " responsive";
    } else {
        i.className = "nav-menu";
    }
   }
 
</script>
<script>
    var a = document.getElementById("loginBtn");
    var b = document.getElementById("registerBtn");
    var x = document.getElementById("login");
    var y = document.getElementById("register");
    function login() {
        x.style.left = "4px";
        y.style.right = "-520px";
        a.className += " white-btn";
        b.className = "btn";
        x.style.opacity = 1;
        y.style.opacity = 0;
    }
    function register() {
        x.style.left = "-510px";
        y.style.right = "5px";
        a.className = "btn";
        b.className += " white-btn";
        x.style.opacity = 0;
        y.style.opacity = 1;
    }
</script>
</body>
</html>