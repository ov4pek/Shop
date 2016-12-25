<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 23.11.16
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>admin</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/basis.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/admin.css"/>">

</head>
<jsp:include page="base.jsp"/>
<div class="window">
    <div class="container">
        <div class="basket">
            <form method="post" enctype="multipart/form-data">
                <div class="add_container">
                    <input name="name" type='text' placeholder="Name" value=${name}>
                </div>
                <div class="add_container">
                    <input name="price" type='text' placeholder="Price" value=${price}>
                </div>
                <div class="add_container">
                    <textarea name='desription' id="desription" placeholder="desription">${description}</textarea>
                </div>
                <div class="add_container">
                    <select name="type" required>
                        <option disabled>Select type</option>
                        <option>helmets</option>
                        <option>gloves</option>
                        <option>protection</option>
                        <option>jackets</option>
                        <option>trousers</option>
                        <option>shoes</option>
                    </select>
                </div>
                <div class="add_container">
                    <input type="file" accept="image/*" name="image">
                </div>
                <input type="submit" id="add_btn" name="edit" value="Edit">
            </form>

        </div>
        <div class="clear-float"></div>
    </div>
    <div>
    </div>
    <div class="clear-float"></div>
    <div class="banner-bottom"></div>

</div>
<body>

</body>
</html>
