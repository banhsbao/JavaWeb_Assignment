<%-- 
    Document   : confirmcard
    Created on : Nov 7, 2020, 2:31:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Cart</title>
    </head>
    <body>
        <c:set var="Auth" value="${sessionScope.LOGIN_USER}"/>
        <c:if test="${not empty Auth}">
            <font color ="red">Welcome, ${Auth.lastname}</font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <h1>Confirm Cart</h1>
        <c:set var="listItems" value="${requestScope.CART_ITEMS}"/>
        <c:if test="${not empty requestScope.CART_ITEMS}">
            <font color="red">Please check your cart again</font>
            <br>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listItems}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${item.key}
                            </td>
                            <td>
                                ${item.value}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <a href="viewCart">Click here to change your cart</a>
            <br>
            <h2>Checkout Informations</h2>
            <br>
            <form action="checkout" method="POST">
                <c:set var="error" value="${requestScope.ERROR_CHECKOUT}"/>
                <c:set var="user" value="${sessionScope.LOGIN_USER}"/>
                Name* &nbsp; &nbsp; <input type="text" name="txtUsername" value="${param.username}" /> (2-50 characters)<br>
                <font color="red">${error.usernameLengthError}</font><br>
                Address*<input type="text" name="txtAddress" value="${param.txtAddress}" /> (2-100 characters)<br>
                <font color="red">${error.addressLengthError}</font><br>
                Phone* &nbsp; <input type="text" name="txtPhone" value="${param.txtPhone}" /> (10-20 numbers)<br>
                <font color="red">${error.phoneLengthError}</font><br>
                <font color="red">${error.phoneFormatError}</font><br>
                <input type="submit" value="Checkout" />
            </form>
        </c:if>
        <c:if test="${empty listItems}">
            <h3 style="color:red">Your cart is empty.</h3>
            <a href="loadProduct">Click here to shopping</a>
        </c:if>
    </body>
</html>