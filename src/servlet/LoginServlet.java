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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	//���O�C����ʂ�\��������
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	// ���O�C�������̎���
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����R�[�h�̐ݒ�
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// ���O�C����ʂœ��͂��ꂽ�l���擾
		String admin_id = request.getParameter("admin_id");
		String password = request.getParameter("password");

		// ���O�C����ʂœ��͂��ꂽ�l�����Ƃ�
		// �f�[�^�x�[�X�ɓo�^���ꂽ�Ǘ��҂̒l���擾
		// ���͂��ꂽ���Ńf�[�^�x�[�X����l���擾�ł��Ȃ��ꍇ
		// ���O�C�����s
		Login login = new Login();
		Admin admin = login.check(admin_id, password);

		if(admin.isLogin_flag()) {
			// ���O�C������ �� ���̉�ʂ֑J��
			System.out.println("���O�C������");
			
			// ���O�C���������ɃZ�b�V�����I�u�W�F�N�g���쐬����
			HttpSession admin_session = request.getSession(true);
			// �Z�b�V�����ɊǗ��ҏ��(�I�u�W�F�N�g)���i�[
			admin_session.setAttribute("admin", admin);


			List<Customer> customer = null;
			// �f�[�^�x�[�X����擾�����ڋq�����i�[
			customer = login.getCustomerInfo(admin_id);

			// �i�[�����ڋq����J�ڐ�̉�ʂɓn��
			//request.setAttribute(�g�f�[�^�̖��O�h,�o�^����f�[�^)
			request.setAttribute("customer", customer);

			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/customer_list.jsp");
			dispatcher.forward(request, response);
		} else {
			// ���O�C�����s �� ���O�C����ʂ֑J��
			System.out.println("���O�C�����s");
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}