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


	// ŒÚ‹q“o˜^‰æ–Ê‚ğ•\¦‚³‚¹‚é
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// •¶šƒR[ƒh‚Ìİ’è
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// ŒÚ‹q“o˜^‰æ–Ê‚Å“ü—Í‚³‚ê‚½’l‚ğæ“¾
		String customer_name = request.getParameter("name");
		String customer_email = request.getParameter("email");
		String customer_address = request.getParameter("address");

		//ƒƒOƒCƒ“‚Éİ’è‚µ‚½ŠÇ—Ò‚ÌƒZƒbƒVƒ‡ƒ“î•ñ‚ğæ“¾
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");

		RegisterCustomerData register = new RegisterCustomerData();

		//ŒÚ‹qî•ñ‚ğ“o˜^‚·‚éƒƒ\ƒbƒh(customer_register)‚Éˆø”(ŠÇ—ÒID,ŒÚ‹q–¼,ZŠ)‚ğİ’è‚µA“o˜^ˆ—‚ğÀs‚·‚é
		register.customer_register(admin.getId(), customer_name, customer_address, customer_email);

		Login login = new Login();
		List<Customer> customer = null;

		// ƒf[ƒ^ƒx[ƒX‚©‚çæ“¾‚µ‚½ŒÚ‹qî•ñ‚ğŠi”[
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// Ši”[‚µ‚½ŒÚ‹qî•ñ‚ğ‘JˆÚæ‚Ì‰æ–Ê‚É“n‚·
		request.setAttribute("customer", customer);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}
}