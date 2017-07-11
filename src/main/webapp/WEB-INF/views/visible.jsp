
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page isELIgnored="false"%>
    <title>Suggestion page</title>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script>
        var onloadCallback = function() {
            grecaptcha.render('html_element', {
                'sitekey' : '6LfeHx4UAAAAAAKUx5rO5nfKMtc9-syDTdFLftnm'
            });
        };
        function onSubmit() {
            document.getElementById('demo-form').submit();
        }
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
    <form id="demo-form" action="<c:url value='/visible' />" method="POST">
        Your comment <br><textarea name='message' cols='50' rows='5'></textarea><br>
        <div class="g-recaptcha" data-sitekey="6LfeHx4UAAAAAAKUx5rO5nfKMtc9-syDTdFLftnm"></div>
        <button class='g-recaptcha'
                data-sitekey='6LfeHx4UAAAAAAKUx5rO5nfKMtc9-syDTdFLftnm'
                data-callback='onSubmit'>
            Submit
        </button>
    </form>
</body>
</body>
</html>