package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBconfig;
import object.Admin;
import object.Customer;

/**
 * 入力された値(管理者IDとパスワード)とDBに登録された値が一致するかの確認を行う
 * 
 * @author ayaka
 */
public class Login {

	/**
	 * DBに登録されている管理者情報を確認
	 * 
	 * @param admin_id admin_tbテーブルのadmin_idカラムに格納されている値
	 * @param password admin_tbテーブルのpasswordカラムに格納されている値
	 * @return
	 * @throws FileNotFoundException
	 */
	public Admin check(String admin_id, String password) throws FileNotFoundException {

		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// 実行SQL(1つ目の?にadmin_id, 2つ目の?にpasswordが入る)
		String login_sql = "select * from admin_tb "
				+ "where admin_id = ? and password = ?;";
		// 管理者のオブジェクト(Admin.java)を作成
		Admin admin = new Admin();

		/**
		 * DBへの接続
		 * 
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,user,pass);
			//login_sqlをプリコンパイルしてstmtに格納
			PreparedStatement stmt = conn.prepareStatement(login_sql);

			// 変数login_sqlの一番目の?に引数のuser_idをセット
			stmt.setString(1, admin_id);
			// 変数login_sqlの二番目の?に引数のpasswordをセット
			stmt.setString(2, password);

			// SQLを実行し、結果を取得
			ResultSet rs = stmt.executeQuery();

			/**
			 * データベースから取得した値をAdminオブジェクトに格納
			 * 値がなければ、login_flag（false）のみ格納
			 */
			if(rs.next()) {
				admin.setId(rs.getInt("admin_id"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
				admin.setLogin_flag(true);
			} else {
				admin.setLogin_flag(false);
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("データベースとの接続を閉じます");
			e.printStackTrace();
		}
		// データベースから取得した値を返す
		return admin;
	}
	
		/**
		 * ログイン成功後に管理者が管理する顧客情報の取得
		 * 
		 * @param admin_id 管理者ID
		 * @return cus_list 顧客情報一覧
		 * @throws FileNotFoundException
		 */
		public List<Customer> getCustomerInfo(String admin_id) throws FileNotFoundException {

			// データベースへの接続情報をプロパティファイルから取得
			DBconfig db_info = new DBconfig();
			String url = db_info.getDBinfo().get("url");
			String user = db_info.getDBinfo().get("user");
			String pass = db_info.getDBinfo().get("password");

			// 実行SQL
			// admin_id(管理者ID)で該当する顧客情報を取得する
			String customer_sql = "select * from customer_tb "
					            + "where admin_id = ?;";

			// 顧客情報のデータを格納するListを作成
			List<Customer> cus_list = new ArrayList<Customer>();

			/**
			 * 顧客情報を格納
			 * 
			 * @throws SQLException
			 */
			try{
				Connection conn = DriverManager.getConnection(url, user, pass);
				//customer_sqlをプリコンパイルしてstmtに格納
				PreparedStatement stmt = conn.prepareStatement(customer_sql);

				stmt.setString(1, admin_id);
				//結果をセット
				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {
					// 顧客情報用のオブジェクトを作成
					Customer cus_info = new Customer();
					// オブジェクトにデータを一時格納
					cus_info.setCustomer_id(rs.getInt("customer_id"));
					cus_info.setAdmin_id(rs.getInt("admin_id"));
					cus_info.setName(rs.getString("name"));
					cus_info.setEmail(rs.getString("email"));
					cus_info.setAddress(rs.getString("address"));
					cus_info.setRegistered_time(rs.getDate("registered_time"));
					cus_info.setUpdated_time(rs.getDate("updated_time"));

					// オブジェクトに格納された
					// 顧客情報用のデータをリストに加える
					cus_list.add(cus_info);
				}
			} catch (SQLException e) {
				System.out.println("データベースとの接続を閉じます");
				e.printStackTrace();
			}
			return cus_list;
		}
}

