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
<br>
<b>Your transaction has been processed. You will receive your redemption code shortly in your email.</b>
<br>
<br>
Please refer to your latest 5 redemptions in the table below:
<table>
		<tr>
			<%--row --%>
			<th>Name of Items</th>
			<%--header --%>
			<th>Points</th>
			<th>Transaction Date</th>

		</tr>

		<c:forEach items="${historical_list}" var="x">
			<%--keylist (list of 3 attribute per {} sent from servlet. https://www.tutorialspoint.com/jsp/jstl_core_foreach_tag.htm --%>
			<tr>
				<td>${x.name}</td>
				<td>${x.points}</td>
				<td>${x.created_date}</td>
				<!-- <td><input id="${x.name}"  name='txn'  type="submit" value="${x.name}" > </td>	 -->			
			</tr>
		</c:forEach>
	</table>
	<br>
<a href="http://127.0.0.1:8080/miniapp/ContactUs.jsp">Click here to contact us if you have any issue.</a>
<br>
<a href="http://127.0.0.1:8080/miniapp/PromotionRedirect">Click here to return to Promotion Page.</a>
<br>
<a href="http://127.0.0.1:8080/miniapp/login.jsp">Click here to return to Login Page.</a>

</body>
</html>