<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

<body>
  
  <div class="container">
  <p>${Message}</p>
  
  <h1>Employee Directory</h1>
  <hr/>
  <div class="row">
  <div class="col-md-4">
  		<form action="${pageContext.request.contextPath}/empDemo" method="POST" >
  		<%System.out.println("INSIDE EMPLOYEE-ADD.JSP");%>		
	    <div class="form-group"><input type="number"  name="fempid" value="${emplist.empid}" placeholder="Enter Employee ID" class="form-control"/><br/></div>
		<div class="form-group"><input type="text"  name="fname" value="${emplist.name}" placeholder="Enter Name" class="form-control"/><br/></div>
		<div class="form-group"><input type="text" name="fgender" value="${emplist.sex}" placeholder="Enter Gender" class="form-control"/><br/></div>
		<div class="form-group"><input type="number" name="fage" value="${emplist.age}" placeholder="Enter Age" class="form-control"/><br/></div>
		<input type="hidden" name="backendId" value = "${emplist.backendId}">
		<button class="btn btn-primary" type="submit">Save Record</button>
		<%System.out.println("INSIDE EMPLOYEE-LIST.JSP, SAVING NEW EMPLOYEE...");%>
		<button class="btn btn-primary" type="reset" onclick="window.location.href='${pageContext.request.contextPath}/empDemo?action=LIST'">Cancel</button>
		<%System.out.println("INSIDE EMPLOYEE-LIST.JSP, BACK TO LIST PAGE...");%>
		</form>
  
  </div>  
  </div>  
  </div>

</body>
</html>