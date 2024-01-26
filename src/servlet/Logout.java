package servlet;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	/**
	 * ログアウト処理の実装
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// セッションオブジェクトを作成
		// 引数(false) → セッションがなければnullを返す
		HttpSession admin_session = request.getSession(false);

		//セッションが存在していたら
		if(admin_session != null) {
			
			Logger logger = Logger.getLogger(LoginServlet.class.getName());
			logger.setLevel(Level.INFO);
			Handler handler = null;
			try {
				handler = new FileHandler("C:\\eclipse-jee-oxygen-3a-win32-x86_64\\workspace\\CustomerManagement\\src\\logger\\sample.log", true);
			} catch (SecurityException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
	        logger.addHandler(handler);
	        // log()を使用して指定のログレベルメッセージを出力
	        logger.info("セッションが存在しています。そのため、セッションを破棄します。");

			// セッションを破棄する
			admin_session.invalidate();
		} else {
			
			Logger logger = Logger.getLogger(LoginServlet.class.getName());
			logger.setLevel(Level.INFO);
			Handler handler = null;
			try {
				handler = new FileHandler("C:\\eclipse-jee-oxygen-3a-win32-x86_64\\workspace\\CustomerManagement\\src\\logger\\sample.log", true);
			} catch (SecurityException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
	        logger.addHandler(handler);
	        // log()を使用して指定のログレベルメッセージを出力
	        logger.info("セッションが存在していません。");
		}

		admin_session = request.getSession(false);

		if(admin_session == null) {
			
			Logger logger = Logger.getLogger(LoginServlet.class.getName());
			logger.setLevel(Level.INFO);
			Handler handler = null;
			try {
				handler = new FileHandler("C:\\eclipse-jee-oxygen-3a-win32-x86_64\\workspace\\CustomerManagement\\src\\logger\\sample.log", true);
			} catch (SecurityException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
	        logger.addHandler(handler);
	        // log()を使用して指定のログレベルメッセージを出力
	        logger.info("セッションが破棄されました。");
		};

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

}