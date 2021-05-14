<%@page import="com.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management </title>
<link rel="stylesheet" href="Views/boostrap.min.css">
<script src="Components/jQuery-3.2.1.min.js"></script>
<script src="Components/admins.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>User Management </h1>
<form id="formAdmin" name="formAdmin" >
 User Name: 
<input id="Name" name="Name" type="text" 
 class="form-control form-control-sm">
<br> User address: 
<input id="Address" name="Address" type="text" 
 class="form-control form-control-sm">
<br> User Email: 
<input id="Email" name="Email" type="text" 
 class="form-control form-control-sm">
<br> User Age: 
<input id="Age" name="Age" type="text" 
 class="form-control form-control-sm">
 <br> User contactNum: 
 <input id="pno" name="contactNum" type="text" 
 class="form-control form-control-sm">
 <br> User Position: 
 <input id="user_type" name="User_Type" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidAdminIDSave" name="hidAdminIDSave" value="">

</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divAdminGrid">

<%
Admin registerObj = new Admin(); 
 out.print(registerObj.readAdmin()); 
%>
</div>

</div></div></div>

</body>
</html>