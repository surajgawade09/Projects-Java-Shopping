<%@page import="shop.model.Order"%>
<%@page import="java.util.List"%>
<%@page import="shop.dao.ShoppingDaoImpl"%>
<%@page import="shop.dao.ShoppingDao"%>
<%@page import="shop.model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%response.setHeader("Cache-Control","no-cache,no-store");
    Customer auth=(Customer)request.getSession().getAttribute("auth");
    List<Order> orders=null;
    if(auth!=null)
    {
    	request.setAttribute("auth",auth);
    	ShoppingDao dao= new ShoppingDaoImpl();
    	orders=dao.RetrieveOrder(auth.getPhonenumber());
    
    }
    else{
    	response.sendRedirect("LoginSignUp.jsp");
    }

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
</head>
<style>
.heading{
	margin-top: 100px;
	align-items: center;
	justify-content: center;
	text-align: center;
}
.heading p{
	text-align: center;
	font-size: 25px;
	font-family: 'Anton', sans-serif;
font-family: 'Oswald', sans-serif;
font-family: 'Roboto Slab', serif;
}
.table tbody td{
vertical-align: middle;
}


</style>
<body>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>
<div class="container" style="margin-top: 100px">

		
			<% if(orders!=null){
				 %>
				 <div class="heading">
					<p>All Orders</p>
					<hr>
					</div>
				 <table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col"></th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%	for(Order o:orders)
					{ %>
					
						<tr>
						<td><%= o.getDate() %></td>
						<td><%=o.getProdname() %></td>
						<td><%=o.getProdcategory() %></td>
						<td><%=o.getQuantity() %></td>
						<td><%=o.getPordprice() %></td>
						<td><img alt="error" src="Images/<%=o.getProdimg() %>" style="width: 150px;height: 120px"></td>
						<td><a href="CancelOrderController?p_id=<%=o.getProdid() %>&u_id=<%=o.getUid()%>" style="margin-bottom: 10px" class="btn btn-sm btn-outline-danger">cancel</a></td>
						</tr>	
						
				<%	}
			}
			else{
				
				%>
				<div class="container text-center" style="margin-top: 300px">
  					<div class="row align-items-end">
    					<div class="col">
     					</div>
    				<div class="col">
      				<h1>Your Cart is Empty! <i class="fa-regular fa-face-frown" style="color: red"></i></h1>
      				<a href="ProductView.jsp"><button class="btn btn-outline-warning" style="margin-top: 10px">View Products</button></a>
    				</div>
   					 <div class="col">
   					 </div>
  					</div>
				</div>
			<%} %>
			
			</tbody>
			</table>
</div>
</body>
</html>