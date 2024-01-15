<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>顧客登録</title>
<link rel="stylesheet" type="text/css" href="css/form.css">
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="form-wrap">
		<h1 class="form-title">顧客登録画面</h1>
		<form action="/CustomerManagement/CustomerRegisterServlet" method="post">
		  <div class="form-item">
		    <label for="customerName" class="form-label">お客様名</label>
		    <input type="text" class="form-control" id="name" name="name" required>
		  </div>
		  <div class="form-item">
		    <label for="customerEmail" class="form-label">メールアドレス</label>
		    <input type="text" class="form-control" id="email" name="email" required>
		    <p id="result" />
		  </div>
		  <div class="form-item">
		    <label for="address" class="form-label">住所</label>
		    <input type="text" class="form-control" id="address" name="address" required>
		  </div>
		  <button type="submit" class="btn btn-primary">登録する</button>
		</form>
		<a href="#" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">顧客一覧画面へ</a>
	</div>
</body>
</html>