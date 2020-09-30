<html>
<head>
<title></title>
</head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.22/datatables.min.css"/>

<body>
<div class="container">
	<form action="loginprocess" method="post" enctype='multipart/form-data'>
	<%System.out.println("INSIDE INDEX.JSP...");%>
		<div class="card">
			<div class="card-header">			
			<p>${Userrecord}</p>
			<p>${ErrorMsg}</p>			
			</div>
			<h4>Login to application</h4>  
			<div class="card-body">
			<div class="form-group"> <input type="text" name="username" class="form-control" placeholder="Username"/><br/></div>
			<div class="form-group"> <input type="password" name="password" class="form-control" placeholder="Password"/><br/></div>
			<div class="card-header"><a href="${pageContext.request.contextPath}/views/admin-register.jsp">New User?Sign Up</a></div>
			<%System.out.println("INSIDE INDEX.JSP, CREATING NEW ADMIN...");%>
			
			</div>			
		<div>
		<br/>
		<input type="submit" value="Login" class="btn btn-primary"/>
		<%System.out.println("INSIDE INDEX.JSP, LOG IN BUTTON...");%>
		</div>
</div>

</form>
</div>
</body>
</html>