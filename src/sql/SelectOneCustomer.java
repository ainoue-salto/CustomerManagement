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
 * 選択した顧客情報詳細の取得
 * @author ayaka
 *
 */
public class SelectOneCustomer {
	
	/**
	 * DBから選択された顧客情報を取得
	 * 
	 * @param customer_id 顧客ID（customer_tbの主キー）
	 * @return　主キーに紐づいたすべての顧客情報
	 * @throws FileNotFoundException
	 */
	public Customer get_One_Customer_Info(int customer_id) throws FileNotFoundException {
		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// 該当する顧客情報のオブジェクトを作成
		Customer one_customer = new Customer();

		// 実行SQL
		// customer_id（顧客ID）で該当する顧客情報を取得する
		String one_customer_sql = "select * from customer_tb where customer_id = ?;";

		/**
		 * @throws SQLException
		 */
		try{
			Connection conn = DriverManager.getConnection(url,user,pass);
			//one_customer_sqlをプリコンパイルしてstmtに格納
			PreparedStatement stmt = conn.prepareStatement(one_customer_sql);

			//one_customer_sqlの?部分にcutomer_idをセット
			stmt.setInt(1, customer_id);
			//結果をセット
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				//オブジェクトにデータを一時格納
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
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
	        logger.addHandler(handler);
	        // log()を使用して指定のログレベルメッセージを出力
	        logger.info("データベースとの接続を閉じます");
			e.printStackTrace();
		}
		return one_customer;
	}
}