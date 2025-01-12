<%@page import="shop.model.Cart"%>
<%@page import="java.util.ArrayList"%>
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
<title>Home</title>
<link href="Home.css" rel="stylesheet">
<%@ include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container text-center" style="margin-top: 200px">
  <div class="row align-items-start">
    <div class="col">
    <img  sty class="img-top" alt="" src="Images/i2.png">
    <h4 style="font-weight: 500;font-size: 20px" >Iphone 14 pro</h4>
    <p>₹ 1,29,999</p>
    </div>
    <div class="col" style="margin-top: 70px">
     <h1>Dive into widerange<br />of Electronics!</h1>
            <p>Dont miss Offers!</p>
            <a href="ProductView.jsp?view=electronics" class="btn btn-outline-warning"	style="border-radius: 20px">Explore &#8594;</a>
    </div>
    <div class="col">
     <img class="img-top" alt="" src="Images/i1.png">
     <h4 style="font-weight: 500;font-size: 20px" >Iphone 14 </h4>
    <p>₹ 79,999</p>
    </div>
  </div>
</div>

<!--------------------Offer----------------------------->
   <div class="offer" style="margin-top: 100px">
    <div class="small-container">
        <div class="row">
            <div class="col-2">
                <img src="Images/applewatch.png" alt="">
            </div>
            <div class="col-2">
                <p>Exclusive offer on all products & services.</p>
                <h1>Smart Brand</h1>
                <a href="ProductView.jsp?view=electronics" class="btn btn-outline-dark" style="border-radius: 20px">Explore &#8594</a>
            </div>
        </div>
    </div>
   </div>
   
   <div class="container text-center" style="margin-top: 100px">
  <div class="row align-items-center">
    <div class="col-3">
      <h1>Discover new <br />Designs with Nike!</h1>
            <p>Upto 40% off!</p>
            <a href="ProductView.jsp?view=clothing" class="btn btn-outline-warning" style="border-radius: 20px">Explore &#8594;</a>
    </div>
    <div class="col-9">
      <div class="container text-center">
  <div class="row align-items-center">
    <div class="col-3">
    <div class="product">
				<img src="Images/nik4.webp">
				<div class="product-info">
					<h4 class="product-title">Hoodies</h4>
				</div>
			</div>
    </div>
    <div class="col-3">
      <div class="product">
				<img src="Images/nik3.webp">
				<div class="product-info">
					<h4 class="product-title">Unisex clothes</h4>
				</div>
			</div>
    </div>
    <div class="col-3">
      <div class="product">
				<img src="Images/nik2.webp">
				<div class="product-info">
					<h4 class="product-title">Drifit jackets</h4>
				</div>
			</div>
    </div>
  </div>
</div>
    </div>
  </div>
</div>
</body>
</html>