<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- BootStrap(HTML／CSS／JavaScriptから構成されるWEBフレームワーク)を使用するためのCDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/form.css">
<title>ログイン画面</title>
</head>
<body>
	<div class="form-wrap">
		<h1 class="form-title">管理者ログイン画面</h1>
		<!-- ログインボタンを押下後、処理をLoginServletのdoPostメソッドに渡す -->
		<form action="/CustomerManagement/LoginServlet" method="post">
		  <div class="form-item">
		    <label for="adminID" class="form-label">管理者ID</label>
		    　　<!-- 入力された管理者IDの値をLoginServletのdoPostメソッドに渡す -->
		    <input type="text" class="form-control" id="adminID" name="admin_id" value="1">
		  </div>
		  <div class="form-item">
		    <label for="pass" class="form-label">パスワード</label>
		    　　<!-- 入力されたパスワードの値をLoginServletのdoPostメソッドに渡す -->
		    <input type="password" class="form-control" id="pass" name="password" value="11111111">
		  </div>
		  <button type="submit" class="btn btn-primary">ログイン</button>
		</form>
	</div>
</body>
</html>