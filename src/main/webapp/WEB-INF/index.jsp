<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Login and Registration</title>
</head>
<body>
    <div class="container mt-5">
    	<div class="d-flex justify-content-around">
	    	<div class="col-4 mt-3">
	    		<div class="form-control bg-info bg-gradient">
	    			<h2>Register Here!</h2>
	    			<div class="d-flex justify-content-between">
					    <form:form action="/register" method="post" modelAttribute="newUser">
					        <div class="form-group">
					            <label>User Name:</label>
					            <form:input path="userName" class="form-control" />
					            <form:errors path="userName" class="text-danger" />
					        </div>
					        <div class="form-group mt-3">
					            <label>Email:</label>
					            <form:input path="email" class="form-control" />
					            <form:errors path="email" class="text-danger" />
					        </div>
					        <div class="form-group mt-3">
					            <label>Password:</label>
					            <form:password path="password" class="form-control" />
					            <form:errors path="password" class="text-danger" />
					        </div>
					        <div class="form-group mt-3">
					            <label>Confirm Password:</label>
					            <form:password path="confirmPassword" class="form-control" />
					            <form:errors path="confirmPassword" class="text-danger" />
					        </div>
					        <input type="submit" value="Register" class="btn btn-warning my-3" />
		    			</form:form>
	    			</div>
	    		</div>
	    	</div>
	    	<div class="col-4 mt-3">
	    		<div class="form-control">
	    			<h6 class="text-danger">Already have an account?</h6>
	    			<h2>Login</h2>
	    			<div class="d-flex justify-content-between">
					    <form:form action="/login" method="post" modelAttribute="newLogin">
					        <div class="form-group">
					            <label>Email:</label>
					            <form:input path="email" class="form-control" />
					            <form:errors path="email" class="text-danger" />
					        </div>
					        <div class="form-group mt-3">
					            <label>Password:</label>
					            <form:password path="password" class="form-control" />
					            <form:errors path="password" class="text-danger" />
					        </div>
					        <input type="submit" value="Login" class="btn btn-success my-3" />
					    </form:form>
	    			</div>

	    		</div>
	    	
	    	

	    	</div>
    	</div>

	    

    </div>

    
</body>
</html>

