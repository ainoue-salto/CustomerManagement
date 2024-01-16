/*
 * メールアドレスのバリデーションチェック
*/

/*フォーム入力のイベントハンドラ*/
window.addEventListener("input", function(e){
	/*入力フォームの要素*/
	var form = document.getElementById("email");
	/*結果出力用の要素*/
	var result = document.getElementById("result");
	/*メールアドレスの(ユーザー名@ドメイン名) 正規表現*/
	/*先頭1文字目に半角英数字 ＋ それ以降に半角英数字または_.- ＋ @ ＋ 半角英数字または_.- ＋ . ＋ 半角英数字*/
	var pattern = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]+.[A-Za-z0-9]+$/;
	
    /*メールアドレスのパターン(ユーザー名@ドメイン名)にマッチするかチェック*/
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