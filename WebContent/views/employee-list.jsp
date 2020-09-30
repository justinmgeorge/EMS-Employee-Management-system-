
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>

<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.22/datatables.min.css"/>
<body>

<%System.out.println("INSIDE EMPLOYEE-LIST.JSP");%>
  <div class="container" style="overflow:scroll; height:100%; max-height:800px" ><span style="float:right">
  <button class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/logout?result=LOGOUT'">Log Out</button></span>
  
  
  <p>${Message}</p> 
  
  <img src="data:;base64,${image}" width="170px"/>
   
  <p style="font-weight: bold;">Welcome, ${user} </p> 

  <br/>
 
  <button class="btn btn-primary" onclick="window.location.href='views/employee-add.jsp'">Add Employee</button>
  <%System.out.println("INSIDE EMPLOYEE-LIST.JSP, ADDING EMPLOYEE...");%>
    
  <table border ="1" class="table table-striped table-bordered" id="datatable">
     <thead>
     <tr class="thead-dark">
       <th>Id</th>
       <th>Name</th>
       <th>Gender</th>
       <th>Age</th>
       <th>Edit employee details</th>   
    </tr>
     </thead>
    <tbody>
    <c:forEach items = "${emplist}" var="employee">
    <tr>       
   <td>${employee.empid}</td>
       <td>${employee.name}</td>
       <td>${employee.sex}</td>
       <td>${employee.age}</td>
       <td>
       <a href="${pageContext.request.contextPath}/empDemo?action=LISTSINGLE&empid=${employee.empid}">Edit</a>
       <%System.out.println("INSIDE EMPLOYEE-LIST.JSP, EDITING EMPLOYEE...");%>
       |
       <a href="${pageContext.request.contextPath}/empDemo?action=DELETEEMP&delempid=${employee.empid}">Delete</a>
       <%System.out.println("INSIDE EMPLOYEE-LIST.JSP, DELETING EMPLOYEE...");%>
       </td>       
    </tr>    
    </c:forEach>
    </tbody>  
  </table>
  </div>
  <script src="https://unpkg.com/jquery@3.3.1/dist/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.22/datatables.min.js"></script>
  
  <script>
  $(document).ready(function(){
  	$('#datatable').DataTable();
  });
  </script> 

</body>
</html>