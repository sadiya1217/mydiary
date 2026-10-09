<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyDiary App Homepage</title>
<link rel="stylesheet"
	href="<c:url value="/resources/styles/style.css"/>">
</head>
<body>

	<div class="header">

		<div class="first">
			<img src="<c:url value="/resources/images/diary.jpg"/>" width="60"
				height="60">
		</div>
		<div class="second">MyDiary App</div>

	</div>

	<br />
	<br />

	<hr />
	<br />
	<br />
	<h1>
		welcome <span style="color: SteelBlue">${user.username}</span>
	</h1>
	<a href="./signout" style="color: red; float: right">Signout</a>

	<div class="bodypart">

		<div class="bodypart1">
			<img src="<c:url value="/resources/images/diary.jpg"/>">
		</div>
		VIEWENTRY
		</h1>
		<br />
		<br />
		<br /> <label>Date</label> <input type="text" name="entrydate"
			class="formcontrol"
			value="<fmt:formatDate value="${entry.entrydate}" pattern="yyyy-MM-dd"/>"
			readonly> <br />
		<br /> <label>Description</label>

		<textarea rows="10" cols="30" name="descreption" readonly>${entry.descreption}</textarea>

		<br />
		<br /> <a href="./userhome">
			<button type="button">BACK</button>
		</a>


	</div>




	</div>

</body>
</html>