package sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.DBconfig;
import servlet.LoginServlet;

/**
 * 顧客情報の更新
 * @author ayaka
 *
 */
public class UpdateCustomerData {
	
	/**
	 * 更新処理
	 * @param name 顧客名
	 * @param email 顧客のメールアドレス
	 * @param address 顧客の住所
	 * @param customer_id 顧客ID(customer_tbの主キー)
	 * @throws FileNotFoundException
	 */
	public  void customer_update(String name, String email, String address, int customer_id) throws FileNotFoundException {
		
		Logger logger = Logger.getLogger(LoginServlet.class.getName());
		logger.setLevel(Level.INFO);
		Handler handler = null;

		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// 実行SQL
		String update_sql = "update customer_tb "
				+ "set name = ?,  email = ?, address = ? where customer_id = ?;";

		/**
		 * DBへの接続
		 * try〜catch〜resources構文を使用
		 * @throws SQLException
		 */
		try{
			Connection conn = DriverManager.getConnection(url,user,pass);
			// オートコミット機能を無効化
			conn.setAutoCommit(false);

			/**
			 * update_sqlをプリコンパイルしてstmtに格納
			 * @throws SQLException
			 */
			try{
				//update_sqlをプリコンパイルしてstmtに格納
				PreparedStatement stmt = conn.prepareStatement(update_sql);
				// 変数update_sqlの一番目の?にnameをセット
				stmt.setString(1, name);
				// 変数update_sqlの二番目の?にemailをセット
				stmt.setString(2, email);
				// 変数update_sqlの三番目の?にaddressをセット
				stmt.setString(3, address);
				// 変数update_sqlの四番目の?にcustomer_idをセット
				stmt.setInt(4, customer_id);
				
				// SQLの実行
				stmt.executeUpdate();
				//コミット
				conn.commit();
				
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
		        logger.info("更新処理が成功しました");
		        
			} catch (SQLException e) {
				conn.rollback();
				
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
		        logger.info("ロールバック処理を行いました");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}