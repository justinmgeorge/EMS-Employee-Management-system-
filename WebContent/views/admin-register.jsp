<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
</head>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

<body>
  
  <div class="container">
  
  <h2>Sign Up</h2>
  <h6>It's quick and easy.</h6>
  <hr/>
  <div class="row">
  <div class="col-md-4">
  		<form action="${pageContext.request.contextPath}/adminUserReg" method="POST" enctype='multipart/form-data'>	
  		<%System.out.println("INSIDE EMPLOYEE-REGISTER.JSP");%>
  		<span><p>${Userrecord}</p></span>  
  		
  		<p><input type="file" accept="image/*" name="photo" id="file" onchange="loadFile(event)" style="display: none;"></p>
  		<p><label for="file" style="cursor: pointer;">Click to upload Image</label></p>
  		<%System.out.println("INSIDE EMPLOYEE-REGISTER.JSP, UPLOADING IMAGE");%>
  		<img id="output" width="150" height="200" /> 		
  		<script>
			var loadFile = function(event) {
			var photo = document.getElementById('output');
			photo.src = URL.createObjectURL(event.target.files[0]);
			};
		</script>
		<br>
		<br/>
		<div class="form-group"><input type="text"  name="fname" placeholder="First Name" class="form-control" required/><br/></div>
  		<div class="form-group"><input type="text"  name="lname" placeholder="Last Name" class="form-control" required/><br/></div>
  		<div class="form-group"><input type="text"  name="mailid" placeholder="Email Address" class="form-control" /><br/></div>
	    <div class="form-group"><input type="text"  name="usrnm" placeholder="Enter Username" class="form-control" required/><br/></div>
		<div class="form-group"><input type="text"  name="pass" placeholder="Enter Password" class="form-control" required/><br/></div>
		<div class="form-group">
		<label for="role" >User Role:</label>s
		<select id="role" name="role">
  		<option value="SystemAdmin">System Administrator</option>
  		<option value="Back_office">Back Office</option>
		</select>
		</div>
		
		<div>Note: (*)Mandatory</div>
		
		<button class="btn btn-primary" type="submit">Sign Up</button>
		<%System.out.println("INSIDE EMPLOYEE-REGISTER.JSP, USER SIGN UP");%>
		<button class="btn btn-primary" type="button" onclick="window.location.href='index.jsp'">Cancel</button>
		<%System.out.println("INSIDE EMPLOYEE-REGISTER.JSP, CANCEL SIGN UP");%>	
		</form>
  
  </div>  
  </div>  
  </div>

</body>
</html>