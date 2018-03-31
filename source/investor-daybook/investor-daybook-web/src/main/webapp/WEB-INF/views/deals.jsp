<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Deals</title>
</head>
<body>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of deals stock</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Ticker</th>
                <th>Caption</th>
                <th>Date</th>
                <th>Operation</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Stage</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${stockDeals}" var="stockDeal">
                <tr>
                    <td>${stockDeal.security.ticker}</td>
                    <td>${stockDeal.security.caption}</td>
                    <td>${stockDeal.dateTime}</td>
                    <td>${stockDeal.operation}</td>
                    <td>${stockDeal.price}</td>
                    <td>${stockDeal.amount}</td>
                    <td>${stockDeal.stage}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="panel-heading"><span class="lead">List of deals bond</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Ticker</th>
                <th>Caption</th>
                <th>Date</th>
                <th>Operation</th>
                <th>Price</th>
                <th>Amount</th>
                <th>ACY</th>
                <th>Stage</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bondDeals}" var="bondDeal">
                <tr>
                    <td>${bondDeal.security.ticker}</td>
                    <td>${bondDeal.security.caption}</td>
                    <td>${bondDeal.dateTime}</td>
                    <td>${bondDeal.operation}</td>
                    <td>${bondDeal.pricePct}</td>
                    <td>${bondDeal.amount}</td>
                    <td>${bondDeal.accumulatedCouponYield}</td>
                    <td>${bondDeal.stage}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>