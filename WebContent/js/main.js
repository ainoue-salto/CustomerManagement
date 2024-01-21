/*
 * メールアドレスのバリデーションチェック
*/

/*フォーム入力のイベントハンドラ*/
window.addEventListener("input", function(e){
	/*入力フォームの要素*/
	var form = document.getElementById("email");
	/*結果出力用の要素*/
	var result = document.getElementById("result");
	/*メールアドレスのパターン 正規表現*/
	var pattern = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]+.[A-Za-z0-9]+$/;
	
    /*メールアドレスのパターンにマッチするかチェック*/
    if (pattern.test(form.value)) {
         /*パターンにマッチした場合*/
         result.textContent = "正しいメールアドレスです";
      } else {
         /*パターンにマッチしない場合*/
         result.textContent = "メールアドレスの形式が正しくありません";
     }
})

/*
 * ログアウト確認ダイアログ表示
*/

function Logout_Dialog(){
	var res = confirm("ログアウトします。よろしいですか?");
	if(res){
		return true;
	} else {
		return false;
	};
};

/*
 * 削除ボタン押下時間違い防止に確認ダイアログを表示
 */

function Delete_Dialog(){
	var res = confirm("選択した顧客データを削除します。よろしいですか?");
	if(res){
		return true;
	} else {
		return false;
	};
};