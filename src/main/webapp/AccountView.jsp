<%@page import="shop.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%response.setHeader("Cache-Control","no-cache,no-store");
    Customer auth=(Customer)request.getSession().getAttribute("auth");
    if(auth!=null)
    {
    	request.setAttribute("auth",auth);
    }
    else
    {
    	response.sendRedirect("LoginSignUp.jsp");
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>
</head>
<style>
table td{
padding: 10px;
text-align: center;
font-size: 15px;
}
</style>
<body>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>
<div class="container text-center" style="margin-top: 100px">
  <div class="row align-items-center">
    <div class="col">
    <div class="img-acc">
    	<img alt="" src="Images/shopping.webp" style="max-width: 400px">
    	</div>
    	<a href="ProductView.jsp"><button class="btn btn-outline-warning">Go Shopping!</button></a>
    </div>
    <div class="col">
     	<img alt="" src="Images/profilepic.png" style="width: 250px;height: 250px">
     	<p style="font-size: 30px;">Welcome <b><%= auth.getCustomername().toUpperCase() %></b> !</p>
     	<table style="border:1px solid;width: 100%">
     	<tr>
     	</tr>
     	<tr>
     	<td>Phone Number :</td>
     	<td style="font-size: 20px;font-weight: 600 "><%=auth.getPhonenumber() %></td>
     	
     	</tr>
     	<tr>
     	<td>Email :</td>
     	<td style="font-size: 20px;font-weight: 600 "><%=auth.getEmail() %></td>
     	
     	</tr>
     	</table>
    </div>
    <div class="col">
    <div class="img-acc">
    	<img alt="" src="Images/orderbox.png" style="max-width: 450px">
    	</div>
    	<a href="OrderView.jsp"><button class="btn btn-outline-warning">Check Orders</button></a>
    </div>
    </div>
  </div>
</div>

</body>
</html>