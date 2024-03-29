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
import sql.DeleteCustomerData;
import sql.Login;

/**
 * Servlet implementation class CustomerDeleteServlet
 */
/**
 * 顧客情報の削除
 * @author ayaka
 *
 */
@WebServlet("/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 顧客情報削除処理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 「削除」リンクから対象の顧客IDを取得
		int id =Integer.parseInt(request.getParameter("id"));

		DeleteCustomerData sql = new DeleteCustomerData();
		// 顧客削除処理を実行
		sql.customer_delete(id);

		// 管理者のセッションを取得
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");

		Login login = new Login();
		List<Customer> customer = null;

		// データベースから取得した顧客情報を格納
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// 格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("customer", customer);

		//顧客一覧画面を表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}

}