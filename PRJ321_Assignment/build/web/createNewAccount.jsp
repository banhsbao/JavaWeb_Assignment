<%-- 
    Document   : createAccount
    Created on : Nov 7, 2020, 1:49:39 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create New Account Occurs Error</h1>
        <c:set var="error" value="${requestScope.ERROR_CREATE_NEW_ACCOUT}"/>
        <form action="createNewAccount" method="POST">
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6 - 20 characters)<br>
            <font color="red">${error.usernameLengthError}</font><br>
            Password* <input type="password" name="txtPassword" value="" /> (6 - 30 characters)<br>
            <font color="red">${error.passwordLengthError}</font><br>
            Confirm* <input type="password" name="txtConfirm" value="" /><br>
            <font color="red">${error.confirmNoMatchedError}</font><br>
            Last Name* <input type="text" name="txtLastname" value="${param.txtLastname}" /> (2 - 50 characters)<br>
            <font color="red">${error.lastnameLengthError}</font><br>
            <font color="red">${error.usernameIsExisted}</font><br>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        <a href="index">Have Account? Login now.</a>
    </body>
</html>
