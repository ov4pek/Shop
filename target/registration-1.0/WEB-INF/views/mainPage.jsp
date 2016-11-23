<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.10.2016
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>MainPage</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/basis.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/mainPage.css"/>">
</head>
<body>
<jsp:include page="base.jsp"/>
<div class="banner">
    <div class="container">
        <div class="container-padding">
            <div class="banner-info">
                <h1>Лучший магазин экипировки в России</h1>
                <p>Сотни товаров, тысячи наименований, все лучшее по <br>самым привлекательным ценам!</p>
            </div>
        </div>
    </div>
</div>

<div class="window">
    <div class="container">
        <div class="products">
            <div class="info">
                <h3 class="new-models">LAST PRODUCTS</h3>
                <ul class=items_block" id=items">
                    <c:choose>
                        <c:when test="${fn:length(catalog_goods) gt 0}">
                            <c:forEach items="${catalog_goods}" var="catalog_goods">
                                <li class="item_img">
                                    <div class="column">
                                        <form action="/info" method="get">
                                            <a href="/info?catalogId=${catalog_goods.catalogId}"><img
                                                    src="<c:url value="${catalog_goods.img}"/>"
                                                    class="img-responsive" alt=""></a>
                                        </form>
                                        <div class="good-name">
                                            <h4 name="name_good">${catalog_goods.name}</h4>
                                            <p name="price">${catalog_goods.price} рублей</p>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h3>Catalog empty</h3>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="clear-float"></div>
<div class="banner-bottom"></div>
</body>
</html>
