<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Information</title>
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
            margin: auto;
            border: 1px solid #ddd;
        }
        th, td {
            text-align: center;
            padding: 8px;
            border-bottom: 1px solid #ddd;
            font-weight: bold;
        }
        th {
            background-color: #4CAF50;
            color: white;
            border-right: 1px solid #ddd;
        }
        td {
        	border-left: 1px solid #ddd;
        }
        td:first-child{
        	color: darkblue;
        }
        td:nth-child(2){
        	color: green;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>
    <h2>User Information</h2>
    <table>
        <tr>
            <td>Name</td>
            <td><c:out value="${name}"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><c:out value="${email}"/></td>
        </tr>
        <tr>
            <td>Birthday</td>
            <td><c:out value="${birthday}"/></td>
        </tr>
        <tr>
            <td>Birthtime</td>
            <td><c:out value="${birthtime}"/></td>
        </tr>
        <tr>
            <td>Gender</td>
            <td><c:out value="${gender}"/></td>
        </tr>
        <tr>
            <td>From</td>
            <td><c:out value="${country}"/></td>
        </tr>
		<c:if test="${not empty favoriteIDEs}">
    		<tr>
		        <td>Favorite IDE</td>
		        <td><c:out value="${favoriteIDEs}"/></td>
		    </tr>
		</c:if>

        <tr>
            <td>TOEIC Score</td>
            <td><c:out value="${toeicScore}"/></td>
        </tr>
        <tr>
            <td>Message</td>
            <td><c:out value="${message}"/></td>
        </tr>
    </table>
</body>
</html>
