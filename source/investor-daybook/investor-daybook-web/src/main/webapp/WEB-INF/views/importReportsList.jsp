<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stored XML reports</title>
</head>
<body>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of reports </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>filename</th>
                <th>date from</th>
                <th>date to</th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reports}" var="report">
                <tr>
                    <td>${report.filename}</td>
                    <td>${report.dateFrom}</td>
                    <td>${report.dateTo}</td>
                    <td><a href="<c:url value='/import-report-${report.id}' />" >import</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
