<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Securities</title>
</head>
<body>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of securities</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Ticker</th>
                <th>Caption</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${securities}" var="security">
                <tr>
                    <td>${security.ticker}</td>
                    <td>${security.caption}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>