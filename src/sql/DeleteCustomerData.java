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
 * 顧客情報削除SQL
 * @author ayaka
 *
 */
public class DeleteCustomerData {
	/**
	 * 顧客情報削除処理
	 * 
	 * @param customer_id 顧客ID(customer_tbの主キー)
	 * @throws FileNotFoundException
	 */
	public void customer_delete(int customer_id) throws FileNotFoundException {

		Logger logger = Logger.getLogger(LoginServlet.class.getName());
		logger.setLevel(Level.INFO);
		Handler handler = null;
		
		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// 実行SQL
		String delete_sql = "delete from customer_tb where customer_id = ?;";

		/**
		 * データベースへの接続
		 * try〜catch〜resources構文を使用
		 * @throws SQLException
		 */
		try{
			Connection conn = DriverManager.getConnection(url,user,pass);
			// オートコミット機能を無効化
			conn.setAutoCommit(false);
			
			/**
			 * 削除sqlをコミット
			 * @throws SQLException
			 */
			try{
				//delete_sqlをプリコンパイルしてstmtに格納
				PreparedStatement stmt = conn.prepareStatement(delete_sql);
				// 変数delete_sqlの一番目の?にcustomer_idをセット
				stmt.setInt(1, customer_id);
				// SQLの実行
				stmt.executeUpdate();
				// コミット
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
		        logger.info("削除処理が成功しました");

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