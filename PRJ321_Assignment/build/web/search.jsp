<%-- 
    Document   : search
    Created on : Nov 6, 2020, 1:19:04 PM
    Author     : Admin
--%>
<%@page import="baohc.login.CusDTO" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.LOGIN_USER}"/>
        <c:if test="${not empty user}">
            <font color ="red">Welcome, ${user.lastname}</font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <h1>Search Page</h1>
        <form action="search">
            Search Value<input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br>
        <c:set var="error" value="${requestScope.ERROR}"/>
        ${error.passwordLengthError}
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:set var="error" value ="${requestScope.ERROR}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last name</th>
                            <th>Role</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="number">
                        <form action="updateAccount">
                            <tr>
                                <td>
                                    ${number.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                    <%--
                                    <c:if test="${not empty error}">
                                        <c:set var="accountUpdate" value="${param.currentUserUpdate}"/>
                                        <c:if test="${accountUpdate eq dto.username}">
                                            <br>
                                            <font color="red">${error.passwordLengthError}</font>
                                            <br>
                                        </c:if>
                                    </c:if>
                                    --%>
                                    </br>
                                    <font color="red">${error.passwordLengthError}</font> 
                                </td>
                                <td>
                                    <input type="text" name="txtLastname" value="${dto.lastname}" />
                                    <%--  <c:if test="${not empty error}">
                                         <c:set var="accountUpdate" value="${param.currentUserUpdate}"/>
                                         <c:if test="${accountUpdate eq dto.username}">
                                             <br>
                                             <font color="red">${error.lastnameLengthError}</font>
                                             <br>
                                         </c:if>
                                     </c:if>
                                    --%>
                                    </br>
                                    <font color="red">${error.lastnameLengthError}</font>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <c:if test="${dto.isAdmin}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="deleteAccount">
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched!!
            </h2>
        </c:if>
    </c:if >
    <a href="loadProduct">Click here to buy without login</a>
</body>
</html>
