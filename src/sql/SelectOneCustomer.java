package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBconfig;
import object.Customer;

public class SelectOneCustomer {

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
			System.out.println("�f�[�^�x�[�X�Ƃ̐ڑ�����܂�");
			e.printStackTrace();
		}
		return one_customer;
	}
}