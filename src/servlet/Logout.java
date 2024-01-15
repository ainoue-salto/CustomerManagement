package servlet;

import java.io.IOException;

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

	// ���O�A�E�g�����̎���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����R�[�h�̐ݒ�
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// �Z�b�V�����I�u�W�F�N�g���쐬
		// ����(false) �� �Z�b�V�������Ȃ����null��Ԃ�
		HttpSession admin_session = request.getSession(false);

		//�Z�b�V���������݂��Ă�����
		if(admin_session != null) {
			System.out.println("�Z�b�V���������݂��Ă��܂��B���̂��߁A�Z�b�V������j�����܂��B");
			// �Z�b�V������j������
			admin_session.invalidate();
		} else {
			System.out.println("�Z�b�V���������݂��Ă��܂���B");
		}

		admin_session = request.getSession(false);

		if(admin_session == null) {
			System.out.println("�Z�b�V�������j������܂����B");
		};

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	// �g�p���܂���
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}