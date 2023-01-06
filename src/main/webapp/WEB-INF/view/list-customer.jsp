<%--
  Created by IntelliJ IDEA.
  User: Dominik
  Date: 05.01.2023
  Time: 01:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.example.webcustomertracker.SortUtils" %>
<html>
<head>
    <title>Customers list</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div id="wrapper">
    <div id="headder">
        <h2>Customer Relationship Manager</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <input type="button" value="Add Customer"
               onclick="window.location.href='showAddCustomerForm'; return false;"
               class="add-button">

        <form:form action="search" method="get">
            Search customer: <input type="text" name="customerName">
            <input type="submit" value="Search" class="add-button">
        </form:form>

        <c:url var="sortLinkFirstName" value="/customer/list">
            <c:param name="sort" value="<%=Integer.toString(SortUtils.FIRST_NAME)%>"/>
        </c:url>
        <c:url var="sortLinkLastName" value="/customer/list">
            <c:param name="sort" value="<%=Integer.toString(SortUtils.LAST_NAME)%>"/>
        </c:url>
        <c:url var="sortLinkEmail" value="/customer/list">
            <c:param name="sort" value="<%=Integer.toString(SortUtils.EMAIL)%>"/>
        </c:url>

        <table>
            <tr>
                <th><a href="${sortLinkFirstName}">First Name</a></th>
                <th><a href="${sortLinkLastName}">Last Name</a></th>
                <th><a href="${sortLinkEmail}">Email</a></th>
            </tr>
            <c:forEach var="tempCustomer" items="${customers}">

                <c:url var="updateLink" value="/customer/showUpdateForm">
                    <c:param name="customerId" value="${tempCustomer.id}"></c:param>
                </c:url>

                <c:url var="deleteLink" value="/customer/deleteCustomer">
                    <c:param name="customerId" value="${tempCustomer.id}"></c:param>
                </c:url>

                <tr>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>
                    <td><a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                        onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a></td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>

</body>
</html>
