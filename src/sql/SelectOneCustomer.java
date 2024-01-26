package sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.DBconfig;
import object.Customer;
import servlet.LoginServlet;

/**
 * �I�������ڋq���ڍׂ̎擾
 * @author ayaka
 *
 */
public class SelectOneCustomer {
	
	/**
	 * DB����I�����ꂽ�ڋq�����擾
	 * 
	 * @param customer_id �ڋqID�icustomer_tb�̎�L�[�j
	 * @return�@��L�[�ɕR�Â������ׂĂ̌ڋq���
	 * @throws FileNotFoundException
	 */
	public Customer get_One_Customer_Info(int customer_id) throws FileNotFoundException {
		// �f�[�^�x�[�X�ւ̐ڑ������v���p�e�B�t�@�C������擾
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// �Y������ڋq���̃I�u�W�F�N�g���쐬
		Customer one_customer = new Customer();

		// ���sSQL
		// customer_id�i�ڋqID�j�ŊY������ڋq�����擾����
		String one_customer_sql = "select * from customer_tb where customer_id = ?;";

		/**
		 * @throws SQLException
		 */
		try{
			Connection conn = DriverManager.getConnection(url,user,pass);
			//one_customer_sql���v���R���p�C������stmt�Ɋi�[
			PreparedStatement stmt = conn.prepareStatement(one_customer_sql);

			//one_customer_sql��?������cutomer_id���Z�b�g
			stmt.setInt(1, customer_id);
			//���ʂ��Z�b�g
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				//�I�u�W�F�N�g�Ƀf�[�^���ꎞ�i�[
				one_customer.setCustomer_id(rs.getInt("customer_id"));
				one_customer.setName(rs.getString("name"));
				one_customer.setEmail(rs.getString("email"));
				one_customer.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
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
	        logger.info("�f�[�^�x�[�X�Ƃ̐ڑ�����܂�");
			e.printStackTrace();
		}
		return one_customer;
	}
}