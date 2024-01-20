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
@WebServlet("/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// •¶šƒR[ƒh‚Ìİ’è
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// uíœvƒŠƒ“ƒN‚©‚ç‘ÎÛ‚ÌŒÚ‹qID‚ğæ“¾
		int id =Integer.parseInt(request.getParameter("id"));

		DeleteCustomerData sql = new DeleteCustomerData();
		// ŒÚ‹qíœˆ—‚ğÀs
		sql.customer_delete(id);

		// ŠÇ—Ò‚ÌƒZƒbƒVƒ‡ƒ“‚ğæ“¾
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");

		Login login = new Login();
		List<Customer> customer = null;

		// ƒf[ƒ^ƒx[ƒX‚©‚çæ“¾‚µ‚½ŒÚ‹qî•ñ‚ğŠi”[
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// Ši”[‚µ‚½ŒÚ‹qî•ñ‚ğ‘JˆÚæ‚Ì‰æ–Ê‚É“n‚·
		request.setAttribute("customer", customer);

		//ŒÚ‹qˆê——‰æ–Ê‚ğ•\¦
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}

}