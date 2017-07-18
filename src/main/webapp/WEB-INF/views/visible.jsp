<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page isELIgnored="false"%>
    <title>Suggestion page</title>
    <%--<script src='https://www.google.com/recaptcha/api.js'></script>--%>
    <%--<script>--%>
        <%--var onloadCallback = function() {--%>
            <%--grecaptcha.render('html_element', {--%>
                <%--'sitekey' : '6LfeHx4UAAAAAAKUx5rO5nfKMtc9-syDTdFLftnm'--%>
            <%--});--%>
        <%--};--%>
        <%--function onSubmit() {--%>
            <%--document.getElementById('demo-form').submit();--%>
        <%--}--%>
    <%--</script>--%>

    <script type="text/javascript">
        var verifyCallback = function(response) {
            //alert(response);
        };
        var widgetId1;
        var widgetId2;
        var onloadCallback = function() {
            // Renders the HTML element with id 'example1' as a reCAPTCHA widget.
            // The id of the reCAPTCHA widget is assigned to 'widgetId1'.
            widgetId1 = grecaptcha.render('example1', {
                'sitekey' : '6LfeHx4UAAAAAAKUx5rO5nfKMtc9-syDTdFLftnm',
                'theme' : 'light'
            });
            widgetId2 = grecaptcha.render(document.getElementById('example2'), {
                'sitekey' : '6LfeHx4UAAAAAAKUx5rO5nfKMtc9-syDTdFLftnm'
            });
            grecaptcha.render('example3', {
                'sitekey' : '6LfeHx4UAAAAAAKUx5rO5nfKMtc9-syDTdFLftnm',
                'callback' : verifyCallback,
                'theme' : 'dark'
            });
        };
    </script>


    <style>
        body {
            font-family: Helvetica, sans-serif;
        }
        .status-message {
            background-color: #5ff;
            margin-bottom: 10px;
            text-align: center;
        }
        textarea {
            margin: 10px 0;
            resize: none;
        }
    </style>
</head>
<body>
<h2>Hello, ${name}. Welcome to ${nameOfApp}! <a href="<c:url value='/'/>">Go Back</a></h2>
<hr/>
<body>
<h3>Give us another feedback by visible captcha!</h3>
<% if (Boolean.TRUE.equals(request.getAttribute("success"))) { %>
<div class='status-message'>Submittion status: ${success} </div>
<div class='status-message'>Your comment: ${message} </div>
<% } else if (Boolean.FALSE.equals(request.getAttribute("success"))) { %>
<div class='status-message'>There was an error.</div>
<% }%>

<%--<form action="javascript:alert(grecaptcha.getResponse(widgetId1));">--%>
<form id="demo-form1" action="<c:url value='/visible' />" method="POST">
        Your comment <br><textarea name='message' cols='50' rows='5'></textarea><br>
    <div id="example1"></div>
    <br>
    <input type="submit" value="submit">
</form>
<br>
<%--<form action="javascript:grecaptcha.reset(widgetId2);">--%>
<form id="demo-form2" action="<c:url value='/visible' />" method="POST">
    Your comment <br><textarea name='message' cols='50' rows='5'></textarea><br>
    <div id="example2"></div>
    <br>
    <input type="submit" value="submit">
</form>
<br>
<%--<form action="?" method="POST">--%>
<form id="demo-form3" action="<c:url value='/visible' />" method="POST">
    Your comment <br><textarea name='message' cols='50' rows='5'></textarea><br>
    <div id="example3"></div>
    <br>
    <input type="submit" value="submit">
</form>
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit&hl=fa"
        async defer>
</script>
</body>
</body>
</html>