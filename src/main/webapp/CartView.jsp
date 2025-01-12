<%@page import="shop.dao.ShoppingDaoImpl"%>
<%@page import="shop.dao.ShoppingDao"%>
<%@page import="java.util.List"%>
<%@page import="shop.model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    pageEncoding="UTF-8"
    <%response.setHeader("Cache-Control","no-cache,no-store");
    Customer auth=(Customer)request.getSession().getAttribute("auth");
    if(auth!=null)
    {
    	request.setAttribute("auth",auth);
    }
    else{
    	response.sendRedirect("LoginSignUp.jsp");
    }
    float CGST=0;
    float SGST=0;
    float discount=0;
    float grandTotal=0;
    ArrayList<Cart> cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
    List<Cart> cartproduct=null;
    if(cart_list!=null)
    {
    	ShoppingDao dao=new ShoppingDaoImpl();
    	cartproduct=dao.GetcartProducts(cart_list);
    	float gross_total=dao.GetCartTotal(cart_list);
    	request.setAttribute("cart_list",cart_list);
    	request.setAttribute("grosstotal", gross_total);
    	float gross_total1=(float)request.getAttribute("grosstotal");
        CGST=(float)(gross_total1 * 0.05);
        SGST=(float)(gross_total1 * 0.05);
        discount=(float)(gross_total1 * 0.2);
        grandTotal=(float)Math.round((gross_total1+CGST+SGST-discount));
    }
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
</head>
<style type="text/css">

.table tbody td{
vertical-align: middle;
}


.btn-incre, .btn-decre{
box-shadow: none;
font-size: 20px;
}

</style>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header" style="justify-content: center;">
        <h3 class="modal-title fs-5" id="staticBackdropLabel">Bill Payement Gateway</h3>
      </div>
      <div class="modal-body">
        <table style="width: 100%">
        <tr>
        <td style="font-size: 20px;padding: 20px;text-align: center;">Gross total : </td>
        <td style="font-size: 20px;padding: 20px;text-align: center;">₹ ${(grosstotal>0)?grosstotal:0 }</td>
        </tr>
        <tr>
        <td style="padding: 20px;text-align: center;">CGST : </td>
        <td style="padding: 20px;text-align: center;">₹ <%=CGST %></td>
        </tr>
        <tr>
        <td style="padding: 20px;text-align: center;">SGST : </td>
        <td style="padding: 20px;text-align: center;">₹ <%=SGST %></td>
        </tr>
        <tr>
        <td style="padding: 20px;text-align: center;">Discount : </td>
        <td style="padding: 20px;text-align: center;">₹ <%=discount %></td>
        </tr>
        <tr style="border-top: 2px solid">
        <td style="font-size: 25px;padding: 20px;text-align: center;">Grand total : </td>
        <td style="font-size: 25px;padding: 20px;text-align: center;">₹ <%=grandTotal %></td>
        </tr>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">cancel</button>
         <button class="btn btn-outline-dark" data-bs-target="#staticBackdrop2" data-bs-toggle="modal">Pay Bill</button>      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header" style="justify-content: center;">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">Payment Successful </h1>
      </div>
      <div class="modal-body" style="text-align: center;font-size: 15px">
        Your Order has been Placed!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-warning"><a href="CheckOutController">View Orders</a></button>
      </div>
    </div>
  </div>
</div>
		
			<% 
				if(cart_list!=null)
				{  %>
				<div class="container my-3" >
		<div class="d-flex py-3" style="margin-top: 80px"><h3>Gross Price: ₹ ${(grosstotal>0)?grosstotal:0 } </h3><button  type="button" class=" btn btn-warning" data-bs-toggle="modal" data-bs-target="#staticBackdrop1">Check Out!</button> </div>
				<table class="table" style="margin-top: 20px">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col">Name</th>
							<th scope="col">Category</th>
							<th scope="col">Price</th>
							<th scope="col" style="text-align: center">Quantity</th>
							<th scope="col"></th>
						</tr>
				</thead>
			<tbody>
				<%
					for(Cart c:cartproduct){
		
				%>
				<tr>
					<td><img alt="" src="Images/<%=c.getProdimg() %>" style="width: 150px;height: 120px"></td>
					<td><%=c.getProdname() %></td>
					<td><%=c.getProdcategory() %></td>
					<td> <%=c.getPordprice() %> ₹</td>
					<td>
						<input type="hidden" name="id" value="<%=c.getProdid() %>" class="form-input">
							<div class="form-group d-flex justify-content-center">
								<a class="btn bnt-sm btn-incre" style="font-size: 20px;"  href="QuantityIncDecController?action=inc&id=<%= c.getProdid()%>"><i class="fas fa-plus-square"></i></a> 
								<input type="text" name="quantity" class="form-control" style="max-width: 120px"  value="<%=c.getQuantity() %>" readonly> 
								<a class="btn btn-sm btn-decre" style="font-size: 20px;margin-top: 2px" href="QuantityIncDecController?action=dec&id=<%= c.getProdid()%>"><i class="fas fa-minus-square"></i></a>
							</div>
						
					</td>
					<td><a href="RemoveFromCartController?id=<%=c.getProdid()%>" style="margin-bottom: 10px" class="btn btn-sm btn-outline-danger">Remove</a></td>
				</tr>
				<%} } 
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
			<%  } %>
			</tbody>
		</table>
	</div>


</body>
</html>