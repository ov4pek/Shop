<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.10.2016
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Base</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/basis.css"/>">
</head>
<body>
<div class="header">
    <div class="container">
        <div class="header_top">
            <div class="logo">
                <a href="/main"><img src="<c:url value="/images/logo_moto.png"/>" alt=""/></a>
            </div>
            <div class="top_right">
                <ul>
                    <li><a href="/registration">Create Account </a></li>
                    |
                    <li><a href="/basket">Basket</a></li>
                    |
                    <li class="login">
                        <c:if test="${session ne 1}">
                            <div id= "loginContainer"><a href="/input" class="loginButton"><span>Sign Up</span></a>
                            </div>
                        </c:if>
                        <c:if test="${session eq 1}">
                            <div id="loginContainer"><a href="/exit" class="loginButton"><span>Exit</span></a>
                            </div>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="head-bann">
    <div class="container">
        <div class="head-nav">
            <span class="menu"> </span>
            <ul class="megamenu skyblue">
                <li><a class="color1" href="/main">Home</a></li>
                <li><a class="color2" href="/catalog?type=helmets">Helmets</a>
                <li><a class="color3" href="/catalog?type=gloves">Gloves</a>
                <li><a class="color4" href="/catalog?type=protection">Protection</a>
                <li><a class="color5" href="/catalog?type=jackets">Jackets</a>
                <li><a class="color6" href="/catalog?type=trousers">Trousers</a>
                <li><a class="color7" href="/catalog?type=shoes">Shoes</a>
            </ul>
        </div>
    </div>
</div>
</div>

</body>
</html>
