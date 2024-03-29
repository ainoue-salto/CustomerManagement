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
import sql.RegisterCustomerData;

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 顧客登録画面を表示させる
	 * 
	 * @param request 
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/customer_register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 顧客情報を遷移先の画面に渡す
	 * 
	 * @param request 
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 顧客登録画面で入力された値を取得
		String customer_name = request.getParameter("name");
		String customer_email = request.getParameter("email");
		String customer_address = request.getParameter("address");

		//ログイン時に設定した管理者のセッション情報を取得
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");

		RegisterCustomerData register = new RegisterCustomerData();

		//顧客情報を登録するメソッド(customer_register)に引数(管理者ID,顧客名,住所)を設定し、登録処理を実行する
		register.customer_register(admin.getId(), customer_name, customer_address, customer_email);

		Login login = new Login();
		List<Customer> customer = null;

		// データベースから取得した顧客情報を格納
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// 格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("customer", customer);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}
}