<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Promotion Page</title>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
<form action="RedemptionServlet" method="POST">
Username:<%= request.getAttribute("id") %>
<br>
Points Balance:<%= request.getAttribute("pointbalance") %>
<br>
Please refer to available promotions in the table below:
<table>
		<tr>
			<%--row --%>
			<th>Name of Items</th>
			<%--header --%>
			<th>Points</th>
			<th>Image</th>
			<th>Stock Count</th>
			<th>Redemption</th>
		</tr>

		<c:forEach items="${key_list}" var="x">
			<%--keylist (list of 3 attribute per {} sent from servlet. https://www.tutorialspoint.com/jsp/jstl_core_foreach_tag.htm --%>
			<tr>
				<td>${x.name}</td>
				<td>${x.points}</td>
				<td><img src=${x.imagename} alt=${x.imagename} width="200" height="134"></td>
				<td>${x.stock_count}</td>
				<td><input id="${x.name}"  name='txn'  type="submit" value="${x.name}" ></td>	
				<!-- <td><input id="${x.name}"  name='txn'  type="submit" value="${x.name}" > </td>	 -->			
			</tr>
		</c:forEach>
	</table>
	<br>
<a href="http://127.0.0.1:8080/miniapp/login.jsp">If nothing to redeem, click here to exit.</a>
</form>
</body>
</html>