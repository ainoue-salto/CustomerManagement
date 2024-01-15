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
import sql.SelectOneCustomer;
import sql.UpdateCustomerData;

/**
 * Servlet implementation class CustomerUpdateServlet
 */
@WebServlet("/CustomerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 「顧客編集画面」への遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 「編集」リンクから顧客IDを取得
		int id = Integer.parseInt(request.getParameter("id"));

		SelectOneCustomer one_customer = new SelectOneCustomer();
		// リンクで選択された顧客情報を取得する
		Customer customer = one_customer.get_One_Customer_Info(id);

		// 遷移先画面に値を渡す
		request.setAttribute("customer", customer);
		
		//customer_update.jspを表示
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_update.jsp");
		//customer_update.jspに処理を投げる
		dispatcher.forward(request, response);
	}

	// 顧客編集処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		UpdateCustomerData sql = new UpdateCustomerData();
		// 顧客情報編集（更新）処理を実行
		sql.customer_update(name, email, address, id);

		// 管理者のセッションを取得
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");

		Login login = new Login();
		List<Customer> customer = null;

		// 更新処理後に「顧客一覧画面」に遷移させるために、データベースから再取得した顧客情報を格納
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// 格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("customer", customer);
		
		//顧客一覧画面に遷移
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}
}