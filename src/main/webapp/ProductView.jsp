<%@page import="shop.model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.dao.ShoppingDaoImpl"%>
<%@page import="shop.dao.ShoppingDao"%>
<%@page import="java.util.LinkedList"%>
<%@page import="shop.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="shop.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%response.setHeader("Cache-Control","no-cache,no-store");
    Customer auth=(Customer)request.getSession().getAttribute("auth");
    if(auth!=null)
    {
    	request.setAttribute("auth",auth);
    }

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<link href="ProductView.css" rel="stylesheet"> 
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>
</head>
<body>
<%
List<Product> lst=new LinkedList<Product>();
String str=request.getParameter("view");
ShoppingDao dao=new ShoppingDaoImpl();

if(str!=null){
lst=dao.ReturnProducts(str);
}
else{
	lst=dao.ReturnProducts("all");
}
String flag=(String)request.getSession().getAttribute("flag");
%>
<div class="heading">
<p>All Products</p>
<hr>
</div>
<div class="alert">
<%
if(!session.isNew())
{
	if(flag=="added")
	{ %>
		<div class="alert alert-light"  role="alert">
		 <p style="text-align:center ;color:Black;font-size: 20px;font-weight: 600"><i class="fa-solid fa-cart-shopping"></i> Added To Cart !</p>
		</div>
	<%}
	else if(flag=="exists")
	{ %>
		<div class="alert alert-light" role="alert">
		 <p style="text-align:center ;color:Black;font-size: 20px;font-weight: 600"><i class="fa-regular fa-circle-check"></i> Already in Cart ! <%if(auth!=null){ %> Check in <a href="CartView.jsp">Cart</a><%} %></p>
		</div>
	<%}
}
%>
</div>
<div class="row">
<%
	if(!lst.isEmpty())
	{
		for(Product p:lst)
		{
			%>
		<div class="col md-3">
     		<div class="product">
				<img src="Images/<%=p.getProdimg()%>">
				<div class="product-info">
					<h4 class="product-title"><%=p.getProdname() %></h4>
					<h6 class="product-category">Category: <%=p.getProdcategory() %></h6>
					<p class="product-price">Price: ₹ <%=p.getPordprice() %></p>
					<a class="product-btn" href="AddToCartController?id=<%= p.getProdid()%>">Add to cart</a>
					<a class="product-btn" href="#">Buy Now!</a>
				</div>
			</div>
    	</div>	
		<% }
	}
%>    
</div>

</body>
</html>