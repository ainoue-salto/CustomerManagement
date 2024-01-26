package servlet;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	/**
	 * ���O�A�E�g�����̎���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����R�[�h�̐ݒ�
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// �Z�b�V�����I�u�W�F�N�g���쐬
		// ����(false) �� �Z�b�V�������Ȃ����null��Ԃ�
		HttpSession admin_session = request.getSession(false);

		//�Z�b�V���������݂��Ă�����
		if(admin_session != null) {
			
			Logger logger = Logger.getLogger(LoginServlet.class.getName());
			logger.setLevel(Level.INFO);
			Handler handler = null;
			try {
				handler = new FileHandler("C:\\eclipse-jee-oxygen-3a-win32-x86_64\\workspace\\CustomerManagement\\src\\logger\\sample.log", true);
			} catch (SecurityException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			}
	        logger.addHandler(handler);
	        // log()���g�p���Ďw��̃��O���x�����b�Z�[�W���o��
	        logger.info("�Z�b�V���������݂��Ă��܂��B���̂��߁A�Z�b�V������j�����܂��B");

			// �Z�b�V������j������
			admin_session.invalidate();
		} else {
			
			Logger logger = Logger.getLogger(LoginServlet.class.getName());
			logger.setLevel(Level.INFO);
			Handler handler = null;
			try {
				handler = new FileHandler("C:\\eclipse-jee-oxygen-3a-win32-x86_64\\workspace\\CustomerManagement\\src\\logger\\sample.log", true);
			} catch (SecurityException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			}
	        logger.addHandler(handler);
	        // log()���g�p���Ďw��̃��O���x�����b�Z�[�W���o��
	        logger.info("�Z�b�V���������݂��Ă��܂���B");
		}

		admin_session = request.getSession(false);

		if(admin_session == null) {
			
			Logger logger = Logger.getLogger(LoginServlet.class.getName());
			logger.setLevel(Level.INFO);
			Handler handler = null;
			try {
				handler = new FileHandler("C:\\eclipse-jee-oxygen-3a-win32-x86_64\\workspace\\CustomerManagement\\src\\logger\\sample.log", true);
			} catch (SecurityException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			}
	        logger.addHandler(handler);
	        // log()���g�p���Ďw��̃��O���x�����b�Z�[�W���o��
	        logger.info("�Z�b�V�������j������܂����B");
		};

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

}