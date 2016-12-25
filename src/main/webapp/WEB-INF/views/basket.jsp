<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 18.11.2016
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>basket</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/basis.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/basket.css"/>">
</head>
<body>
<jsp:include page="base.jsp"/>
<div class="window">
    <div class="container">
        <div class="basket" id="basket">
            <c:choose>
                <c:when test="${fn:length(goods) gt 0}">
                    <c:forEach items="${goods}" var="good">
                        <div class="good">
                            <div class="basket_img">
                                <img class="img_good" src="<c:url value="${good.img}"/>" alt=""/>
                            </div>
                            <div class="basket_good_name">
                                <div>
                                    <h5 class="title">${good.name}</h5>
                                </div>
                            </div>
                            <div class="price">${good.price} руб.</div>
                            <div class="quantity">Quantity:${good.quantity} </div>

                            <form class="basket_item" action="<c:url value="/good/edit"/>" method="post">
                                <select class="select_quantity" name='quantity' required>
                                    <option disabled>Select quantity</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                </select>
                                <input type="text" name="id" hidden value="${good.id}">
                                <input type="submit" class="btn_basket" value="Edit quantity">
                            </form>
                            <div class="link">
                                <a href="<c:url value="/basket/remove?id=${good.id}"/>">
                                    <button class="btn_delete">Remove</button>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h3>No goods to display</h3>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="clear-float"></div>
        <div class="link">
            <form action="/check" method="get">
                <input class="btn_basket" type="submit" name="doCheck" value="Оформить заказ">
            </form>
        </div>
        <div class="link">
            <%--<form method="post">--%>
                <input class="btn_delete" type="submit" onclick="removeFromBucket()" name="deleteAll" value="Удалить все">
            <%--</form>--%>
        </div>
    </div>
    <div>
    </div>
    <div class="clear-float"></div>
    <div class="banner-bottom"></div>

</div>
<script src="webjars/jquery/1.9.1/jquery.js"></script>
<script src="webjars/jquery-validation/1.15.1/jquery.validate.js"></script>
<script src="<c:url value="/js/AjaxBasket.js"/>"></script>
</body>
</html>
