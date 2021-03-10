<%-- 
    Document   : onlineStore
    Created on : Nov 7, 2020, 2:36:44 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Store</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.LOGIN_USER}"/>
        <c:if test="${not empty user}">
            <font color ="red">Welcome, ${user.lastname}</font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <h1>Online Store</h1>
        ${requestScope.CHECK_OUT_SUCESS}<br>
        <form action="addItem">
            <c:set var="listProduct" value="${requestScope.LIST_PRODUCT}"/>
            Choose book add to cart
            <select name="cboBook">
                <c:forEach var="item" items="${listProduct}">
                    <option>${item.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Add Book To Cart" name="btAction"/>
        </form>
        <form action="viewCart">
            <input type="submit" value="View Your Cart" name="btAction"/>
        </form>
        <a href="index">Click here to login</a>
    </body>
</html>
