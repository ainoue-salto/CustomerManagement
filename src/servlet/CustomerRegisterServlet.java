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


	// �ڋq�o�^��ʂ�\��������
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����R�[�h�̐ݒ�
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// �ڋq�o�^��ʂœ��͂��ꂽ�l���擾
		String customer_name = request.getParameter("name");
		String customer_email = request.getParameter("email");
		String customer_address = request.getParameter("address");

		//���O�C�����ɐݒ肵���Ǘ��҂̃Z�b�V���������擾
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");

		RegisterCustomerData register = new RegisterCustomerData();

		//�ڋq����o�^���郁�\�b�h(customer_register)�Ɉ���(�Ǘ���ID,�ڋq��,�Z��)��ݒ肵�A�o�^���������s����
		register.customer_register(admin.getId(), customer_name, customer_address, customer_email);

		Login login = new Login();
		List<Customer> customer = null;

		// �f�[�^�x�[�X����擾�����ڋq�����i�[
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// �i�[�����ڋq����J�ڐ�̉�ʂɓn��
		request.setAttribute("customer", customer);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}
}