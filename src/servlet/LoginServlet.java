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
import java.util.logging.*;

import object.Admin;
import object.Customer;
import sql.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���O�C�����(login.jsp)��\��
	 * 
	 * 	@param request �u���E�U(login.jsp)�����URL���N�G�X�g
	 *  @param response ���X�|���X
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//forward���\�b�h���g�p���邽�߁ARequestDispatcher�C���^�[�t�F�[�X�̃I�u�W�F�N�g�𐶐�
		//RequestDispatcher = �N���C�A���g���烊�N�G�X�g����M���A�T�[�o�[��̔C�ӂ̃��\�[�X�i�T�[�u���b�g�AHTML �t�@�C���AJSP �t�@�C���Ȃǁj�ɑ��M����I�u�W�F�N�g
		//�u���E�U(login.jsp)�����URL���N�G�X�g����M���ALoginServlet.java�Ŏ󂯎��
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
		//login.jsp�Ŏ󂯎����request��response��login.jsp�ɓ]������
		//forward = Servlet�N���X��jsp�t�@�C���ɏo�͂̕\�����˗�����(���������̂܂ܓ]���ł���)
		dispatcher.forward(request, response);
	}

	/**
	 * ���O�C������
	 * 
	 * 	@param request �u���E�U(login.jsp)�����URL���N�G�X�g
	 *  @param response ���X�|���X
	 *  @param admin_id ���O�C����ʂœ��͂��ꂽID
	 *  @param password ���O�C����ʂœ��͂��ꂽ�p�X���[�h
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(LoginServlet.class.getName());
		logger.setLevel(Level.INFO);
		Handler handler = null;
		
		// �����R�[�h�̐ݒ�
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// ���O�C����ʂœ��͂��ꂽ�l(name)���擾
		String admin_id = request.getParameter("admin_id");
		String password = request.getParameter("password");

		// ���O�C����ʂœ��͂��ꂽ�l�����Ƃ�
		// �f�[�^�x�[�X�ɓo�^���ꂽ�Ǘ��҂̒l���擾
		// ���͂��ꂽ���Ńf�[�^�x�[�X����l���擾�ł��Ȃ��ꍇ
		// ���O�C�����s
		Login login = new Login();
		Admin admin = login.check(admin_id, password);

		//login_flag��true or false�ŏ�������
		if(admin.isLogin_flag()) {
			/*Logger logger = Logger.getLogger(LoginServlet.class.getName());
			logger.setLevel(Level.INFO);
			Handler handler = null;*/
			try {
				handler = new FileHandler("C:\\eclipse-jee-oxygen-3a-win32-x86_64\\workspace\\CustomerManagement\\src\\servlet\\sample.log", true);
			} catch (SecurityException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			}
	        logger.addHandler(handler);
	        // log()���g�p���Ďw��̃��O���x�����b�Z�[�W���o��
	        logger.info("���O�C������");
			
			// ���O�C���������ɃZ�b�V�����I�u�W�F�N�g���쐬����
			HttpSession admin_session = request.getSession(true);
			// �Z�b�V�����ɊǗ��ҏ��(�I�u�W�F�N�g)���i�[
			admin_session.setAttribute("admin", admin);

			//customer��������
			List<Customer> customer = null;
			// �f�[�^�x�[�X����擾�����ڋq�����i�[
			customer = login.getCustomerInfo(admin_id);

			// �i�[�����ڋq����J�ڐ�̉�ʂɓn��
			//request.setAttribute(�g�f�[�^�̖��O�h,�o�^����f�[�^)
			request.setAttribute("customer", customer);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/customer_list.jsp");
			dispatcher.forward(request, response);
		} else {
			/*Logger logger = Logger.getLogger(LoginServlet.class.getName());
			logger.setLevel(Level.INFO);
			Handler handler = null;*/
			try {
				handler = new FileHandler("C:\\eclipse-jee-oxygen-3a-win32-x86_64\\workspace\\CustomerManagement\\src\\servlet\\sample.log", true);
			} catch (SecurityException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO �����������ꂽ catch �u���b�N
				e1.printStackTrace();
			}
	        logger.addHandler(handler);
	        // log()���g�p���Ďw��̃��O���x�����b�Z�[�W���o��
	        logger.info("���O�C�����s");
            
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}