<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 使用するタグライブラリ(JSTL = JSPでよく使用される機能をタグライブラリとしてまとめたもの)の宣言 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.List" %>
<%@ page import="object.Customer" %>
<!-- request.setAttributeで設定した値をJSP側で取得し、新たにリスト形式の変数に代入 -->
<% List<Customer> customer_list = (List<Customer>)request.getAttribute("customer"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客一覧画面</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="mx-auto text-center" style="width: 70%;">
	<h2 class="text-center mb-3">顧客一覧</h2>
	<table class="table table-striped">
		<thead>
			 <tr>
			 	<th scope="col">顧客ID</th>
			 	<th scope="col">お客様名</th>
			 	<th scope="col">住所</th>
			 	<th scope="col">登録日</th>
			 	<th scope="col">更新日</th>
			 	<th scope="col">操作</th>
			 </tr>
		</thead>
		<tbody>
			<!-- リスト形式で取得した値が存在する分だけ表示 -->
			 <% for(Customer cus : customer_list) { %>
			 <tr>
			 	<td><%= cus.getCustomer_id() %></td>
			 	<td><%= cus.getName() %></td>
			 	<td><%= cus.getAddress() %></td>
			 	<td><%= cus.getRegistered_time() %></td>
			 	<td><%= cus.getUpdated_time() %></td>
				<!-- JSTLを使用して顧客IDのデータをリンクの -->
				<!-- パラメーターに設定してサーブレットで取得する -->
			 	<c:url var="update" value="/CustomerUpdateServlet">
			 		<c:param name="id" value="<%= String.valueOf(cus.getCustomer_id()) %>"></c:param>
			 	</c:url>
			 	<c:url var="delete" value="/CustomerDeleteServlet">
			 		<c:param name="id" value="<%= String.valueOf(cus.getCustomer_id()) %>"></c:param>
			 	</c:url>
			 	<!-- varで取得した変数をURLパラメータ内に設定することで選択した顧客データの編集画面に飛ぶことができる -->
			 	<td><a href="${update}" >編集</a> | <a href="${delete}" onclick="return Delete_Dialog()">削除</a></td>
			 </tr>
			 <% } %>
		</tbody>
	</table>
	<a href="<%= request.getContextPath() %>/CustomerRegisterServlet">顧客登録画面へ</a>
</div>
</body>
<!-- 削除ボタン押下時間違い防止に確認ダイアログを表示 -->
<script type="text/javascript">
	function Delete_Dialog(){
		var res = confirm("選択した顧客データを削除します。よろしいですか?");
		if(res){
			return true;
		} else {
			return false;
		};
	};
</script>
</html>