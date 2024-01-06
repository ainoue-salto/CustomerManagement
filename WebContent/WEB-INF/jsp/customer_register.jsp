<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>顧客登録</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/WebContent/WEB-INF/js/validation.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="mx-auto" style="width: 300px;">
		<h1 class="mb-3" style="text-align: center">顧客登録画面</h1>
		<form action="/CustomerManagement/CustomerRegisterServlet" method="post">
		  <div class="mb-3">
		    <label for="customerName" class="form-label">お客様名</label>
		    <input type="text" class="form-control" id="customerName" name="customer_name" required>
		  </div>
		  <div class="mb-3">
		    <label for="customerEmail" class="form-label">メールアドレス</label>
		    <input type="text" class="form-control" id="customerEmail" name="customer_email" required>
		    <p id="result" />
		  </div>
		  <div class="mb-3">
		    <label for="address" class="form-label">住所</label>
		    <input type="text" class="form-control" id="address" name="customer_address" required>
		  </div>
		  <button type="submit" class="btn btn-primary">登録する</button>
		</form>
		<a href="#" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">顧客一覧画面へ</a>
	</div>
</body>
<script type="text/javascript">
	/*
	 * メールアドレスのバリデーションチェック
	*/
	
	/*入力フォームの要素*/
	var form=document.getElementById("customerEmail");
	/*結果出力用の要素*/
	var result=document.getElementById("result");
	/*メールアドレスのパターン 正規表現*/
	var pattern = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]+.[A-Za-z0-9]+$/;
	
	/*フォーム入力のイベントハンドラ*/
	form.addEventListener("input", function(e){
	    /*メールアドレスのパターンにマッチするかチェック*/
	    if (pattern.test(form.value)) {
	         /*パターンにマッチした場合*/
	         result.textContent = "正しいメールアドレスです";
	      } else {
	         /*パターンにマッチしない場合*/
	         result.textContent = "メールアドレスの形式が正しくありません";
	     }
	})
</script>
</html>