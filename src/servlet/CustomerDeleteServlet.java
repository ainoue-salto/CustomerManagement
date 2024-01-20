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
		// �����R�[�h�̐ݒ�
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// �u�폜�v�����N����Ώۂ̌ڋqID���擾
		int id =Integer.parseInt(request.getParameter("id"));

		DeleteCustomerData sql = new DeleteCustomerData();
		// �ڋq�폜���������s
		sql.customer_delete(id);

		// �Ǘ��҂̃Z�b�V�������擾
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");

		Login login = new Login();
		List<Customer> customer = null;

		// �f�[�^�x�[�X����擾�����ڋq�����i�[
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// �i�[�����ڋq����J�ڐ�̉�ʂɓn��
		request.setAttribute("customer", customer);

		//�ڋq�ꗗ��ʂ�\��
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}

}