<%-- 
    Document   : viewCart
    Created on : Nov 7, 2020, 2:38:19 AM
    Author     : Admin
--%>
<%@page import="java.util.Map"%>
<%@page import="baohc.cart.CartObjectDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.LOGIN_USER}"/>
        <c:if test="${not empty user}">
            <font color ="red">Welcome, ${user.lastname}</font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <h1>Your Cart</h1>
        <c:set var="listItems" value="${requestScope.CART_ITEMS}"/>
        <c:if test="${not empty requestScope.CART_ITEMS}">
            <form action="updateItem">

                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Select to Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${requestScope.CART_ITEMS}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${item.key}
                                </td>
                                <td>
                                    <input type="text" name="txtQuantity" value="${item.value}"/>
                                    <input type="hidden" name="txtItemName" value="${item.key}" />
                                </td>
                                <td>
                                    <input type="checkbox" name="chkDelete" value="${item.key}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="2">
                                <a href="loadProduct">Add more book to your cart</a>
                            </td>
                            <td>
                                <input type="submit" value="Update Quantity" name="btAction" />
                            </td>
                            <td>
                                <input type="submit" value="Delete Item" name="btAction"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <c:set var="listErrors" value="${requestScope.ERROR_UPDATE_CART}"/>
            <c:forEach var="error" items="${listErrors.quantityFormatError}">
                <font color="red">${error}</font><br>
            </c:forEach>
            <form action="confirmCart">
                <input type="submit" value="Confirm Cart" name="btAction" />
            </form>
        </c:if>
        <c:if test="${empty listItems}">
            Your cart is empty.<br>
            <a href="loadProduct">Add more book to your cart</a>
        </c:if>
    </body>
</html>
