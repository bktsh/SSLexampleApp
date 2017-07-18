<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page isELIgnored="false"%>
    <title>Generic Error Page</title>
</head>
<body>
        <h2>Unknown Error Occured, please contact support.</h2>
        <h3>Debug Information:</h3>
        Requested URL= ${url}<br><br>
        Exception= ${exception.message}<br><br>
        <strong>Exception Stack Trace</strong><br>
        <c:forEach items="${exception.stackTrace}" var="ste">
            ${ste}
        </c:forEach>
    </body>
</html>