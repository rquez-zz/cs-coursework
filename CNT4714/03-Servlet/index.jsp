<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Project3</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Welcome to the Project 3 Remote Database Management System</h1>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-12 text-center">
            <p>
                You are connected to the Project3 database. Please enter any valid SQL query or update statement.
                If no query/update command is given the Execute button will display all supplier information in the database.
                All execution results will appear below.
            </p>
            <form class="" action="/Project3/MySQLServlet" method="post">
                <div class="form-group">
                    <textarea name="command" name="command" rows="8" cols="100" id="command" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <input type="submit" name="execute" value="Execute Command" class="btn btn-primary">
                    <input type="button" name="clear" value="Clear Form" class="btn btn-primary" onclick="document.getElementById('command').value = '';">
                </div>
            </form>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <c:choose>
                <c:when test="${not empty error}">
                    <h3 class="text-center">Error:</h3>
                    <div class="bg-danger">
                        <h5>Error executing the SQL statement:</h5>
                        <p>${error}</p>
                    </div>
                </c:when>
                <c:when test="${not empty rowsUpdated}">
                    <h3 class="text-center">Update:</h3>
                    <div class="bg-success">
                        <h5>The statement executed successfully. ${rowsUpdated} row(s) affected.</h5>
                        <c:if test="${not empty statusesUpdated}">
                            <h5>Business Logic Detected! Updated ${statuesUpdated} supplier status mark(s).</h5>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <h3 class="text-center">Database Results:</h3>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <c:forEach items="${rows[0]}" var="column">
                                    <td><c:out value="${column.key}" /></td>
                                </c:forEach>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${rows}" var="columns">
                                <tr>
                                    <c:forEach items="${columns}" var="column">
                                        <td><c:out value="${column.value}" /></td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
