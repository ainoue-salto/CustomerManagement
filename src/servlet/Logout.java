package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ログアウト処理の実装
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// セッションオブジェクトを作成
		// 引数(false) → セッションがなければnullを返す
		HttpSession admin_session = request.getSession(false);

		//セッションが存在していたら
		if(admin_session != null) {
			System.out.println("セッションが存在しています。そのため、セッションを破棄します。");
			// セッションを破棄する
			admin_session.invalidate();
		} else {
			System.out.println("セッションが存在していません。");
		}

		admin_session = request.getSession(false);

		if(admin_session == null) {
			System.out.println("セッションが破棄されました。");
		};

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	// 使用しません
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}