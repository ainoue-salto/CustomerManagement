package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import object.Admin;
import object.Customer;
import sql.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ログイン画面(login.jsp)を表示
	 * 
	 * 	@param request ブラウザ(login.jsp)からのURLリクエスト
	 *  @param response レスポンス
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//forwardメソッドを使用するため、RequestDispatcherインターフェースのオブジェクトを生成
		//RequestDispatcher = クライアントからリクエストを受信し、サーバー上の任意のリソース（サーブレット、HTML ファイル、JSP ファイルなど）に送信するオブジェクト
		//ブラウザ(login.jsp)からのURLリクエストを受信し、LoginServlet.javaで受け取り
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
		//login.jspで受け取ったrequestとresponseをlogin.jspに転送する
		//forward = Servletクラスがjspファイルに出力の表示を依頼する(処理をそのまま転送できる)
		dispatcher.forward(request, response);
	}

	/**
	 * ログイン処理
	 * 
	 * 	@param request ブラウザ(login.jsp)からのURLリクエスト
	 *  @param response レスポンス
	 *  @param admin_id ログイン画面で入力されたID
	 *  @param password ログイン画面で入力されたパスワード
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// ログイン画面で入力された値(name)を取得
		String admin_id = request.getParameter("admin_id");
		String password = request.getParameter("password");

		// ログイン画面で入力された値をもとに
		// データベースに登録された管理者の値を取得
		// 入力された情報でデータベースから値が取得できない場合
		// ログイン失敗
		Login login = new Login();
		Admin admin = login.check(admin_id, password);

		//login_flagがtrue or falseで条件分岐
		if(admin.isLogin_flag()) {
			// ログイン成功 → 次の画面へ遷移
			System.out.println("ログイン成功");
			
			// ログイン成功時にセッションオブジェクトを作成する
			HttpSession admin_session = request.getSession(true);
			// セッションに管理者情報(オブジェクト)を格納
			admin_session.setAttribute("admin", admin);

			//customerを初期化
			List<Customer> customer = null;
			// データベースから取得した顧客情報を格納
			customer = login.getCustomerInfo(admin_id);

			// 格納した顧客情報を遷移先の画面に渡す
			//request.setAttribute(“データの名前”,登録するデータ)
			request.setAttribute("customer", customer);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/customer_list.jsp");
			dispatcher.forward(request, response);
		} else {
			// ログイン失敗 → ログイン画面へ遷移
			System.out.println("ログイン失敗");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}