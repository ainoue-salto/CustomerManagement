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

	// �u�ڋq�ҏW��ʁv�ւ̑J��
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����R�[�h�̐ݒ�
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// �u�ҏW�v�����N����ڋqID���擾
		int id = Integer.parseInt(request.getParameter("id"));

		SelectOneCustomer one_customer = new SelectOneCustomer();
		// �����N�őI�����ꂽ�ڋq�����擾����
		Customer customer = one_customer.get_One_Customer_Info(id);

		// �J�ڐ��ʂɒl��n��
		request.setAttribute("customer", customer);
		
		//customer_update.jsp��\��
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_update.jsp");
		//customer_update.jsp�ɏ����𓊂���
		dispatcher.forward(request, response);
	}

	// �ڋq�ҏW����
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����R�[�h�̐ݒ�
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		UpdateCustomerData sql = new UpdateCustomerData();
		// �ڋq���ҏW�i�X�V�j���������s
		sql.customer_update(name, email, address, id);

		// �Ǘ��҂̃Z�b�V�������擾
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");

		Login login = new Login();
		List<Customer> customer = null;

		// �X�V������Ɂu�ڋq�ꗗ��ʁv�ɑJ�ڂ����邽�߂ɁA�f�[�^�x�[�X����Ď擾�����ڋq�����i�[
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// �i�[�����ڋq����J�ڐ�̉�ʂɓn��
		request.setAttribute("customer", customer);
		
		//�ڋq�ꗗ��ʂɑJ��
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}
}