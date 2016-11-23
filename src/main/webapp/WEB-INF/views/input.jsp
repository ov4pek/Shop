<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30.09.2016
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Input</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/basis.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/registrationCSS.css"/>">
</head>
<body>
<jsp:include page="base.jsp"/>

<div class="window">
    <div class="container" id="input_container">
        <div class="registration">
            <h2>Enter your login and password.<br>
                New User? <span> Create a account. </span></h2>
            <h2 class="error-message">${inputErrorMessage}</h2>
            <div class="input_form">
                <form id="input_form" action="" method="post">
                    <div>
                        <label>
                            <input placeholder="Enter your login:" type='email' class="inputform"
                                   name="inputLogin" required autofocus value=${login}>
                        </label>
                    </div>
                    <div>
                        <label>
                            <input placeholder="Enter password:" type='password' class="inputform"
                                   name="inputPassword" required autofocus>
                        </label>
                    </div>
                    <div>
                        <input id="register-submit" type="submit" value="Sign In">
                    </div>

                    <%--<p>Enter your login<br>--%>
                    <%--<input type="email" name="inputLogin" value=${login}></p>--%>
                    <%--<p>Enter password <br>--%>
                    <%--<input type="password" name="inputPassword"></p>--%>
                    <%--<input type="submit" value="Sign In">--%>
                </form>
            </div>
        </div>
        <%--<a href="/registration">Sign Up</a>--%>
    </div>
</div>
<div class="clear-float"></div>
<div class="banner-bottom"></div>
</body>
</html>
