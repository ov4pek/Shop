<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 23.11.16
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>GoodInfo</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/basis.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/goodInfo.css"/>">
</head>
<body>
<jsp:include page="base.jsp"/>

<div class="window">
    <div class="container">
        <div class="information">
            <div class="img_good_cont">
                <img class="img_good" src="<c:url value="${img_good}"/>" alt=""/>
            </div>
            <div class="descr">
                <div class="btns">
                    <div class="add_good">
                        <form action="/basket/add?id=${catalogId}" method="get">
                            <input type="text" name="id" hidden value=${catalogId} >
                            <select class="select_quantity" name='quantity' required>
                                <option disabled>Select quantity</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                            <input type="submit" class="btn_add" name="add" value="Add to bucket">
                        </form>
                    </div>
                    <div class="add_good">
                        <form action="/catalog/edit?id=${catalogId}" method="get">
                            <input type="text" name="id" hidden value=${catalogId} >
                            <input type="submit" class="btn_add" name="edit" value="Edit">
                        </form>
                    </div>
                    <div class="add_good">
                        <form action="?id=${catalogId}" method="post">
                            <input type="text" name="id" hidden value=${catalogId} >
                            <input type="submit" class="btn_add" name="remove" value="Remove">
                        </form>
                    </div>
                </div>

                <h3>${good_name}</h3>
                <p>${good_price} руб.</p>

                <div class="text_information">
                    <h3>Information</h3>
                    <p>${good_description}</p>
                </div>
            </div>
            <div class="comments_window">
                <form name="add_comments" method="post">
                    <textarea name="comment" id="comment" placeholder="Чтобы оставить комментарий надо быть авторизованным"></textarea><br><br>
                    <div>
                        <input type="submit" name="btnAddComment" id="btnAddComment" value="Оставить комментарий">
                    </div>
                </form>
                <c:choose>
                    <c:when test="${fn:length(comments) gt 0}">
                        <c:forEach items="${comments}" var="comment">
                            <div class="comment">
                                <div class="comment_user">
                                    <h4>${comment.userName}</h4>
                                </div>
                                <div class="comment_date">
                                    <div>
                                        <h5 class="title">${comment.date}</h5>
                                    </div>
                                </div>
                                <div class="comment_text">
                                        ${comment.text}
                                </div>
                                <hr>

                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h3>Комментариев пока нет, будьте первым!</h3>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<div class="clear-float"></div>
<div class="banner-bottom"></div>


</body>
</html>
