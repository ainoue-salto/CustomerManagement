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
 * 顧客情報登録
 * 
 * @author ayaka
 *
 */
public class RegisterCustomerData {

	/**
	 * 登録処理
	 * 
	 * @param admin_id 管理者ID
	 * @param name 顧客名
	 * @param address 顧客の住所
	 * @param email　顧客のメールアドレス
	 * @throws FileNotFoundException
	 */
	public void customer_register(int admin_id, String name, String address, String email) throws FileNotFoundException {
		
		Logger logger = Logger.getLogger(LoginServlet.class.getName());
		logger.setLevel(Level.INFO);
		Handler handler = null;

		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// 実行SQL
		String register_sql = "insert into customer_tb"
				+ "(admin_id, name, address, email) values(?,?,?,?)";

		/**
		 * データベースへの接続
		 * try〜catch〜resources構文を使用
		 * 
		 * @throws SQLException
		 */
		try{
			Connection conn = DriverManager.getConnection(url,user,pass);
			// オートコミット機能(SQL文の実行ごとに自動的にコミット処理を行う機能)を無効化
			conn.setAutoCommit(false);

			/**
			 * 登録のsqlをコミット
			 * @throws SQLException
			 */
			try{
				//register_sqlをプリコンパイルしてstmtに格納
				PreparedStatement stmt = conn.prepareStatement(register_sql);
				// 変数register_sqlの1番目の?にdmin_idをセット
				stmt.setInt(1, admin_id);
				// 変数register_sqlの2番目の?にnameをセット
				stmt.setString(2, name);
				// 変数register_sqlの3番目の?にaddressをセット
				stmt.setString(3, address);
				// 変数register_sqlの4番目の?にemailをセット
				stmt.setString(4, email);
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
		        logger.info("コミット処理を行いました");
		        
			} catch (SQLException e) {
			
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
				//登録処理中に予期せぬ事態が発生した場合、登録までの一連の処理を無効にする
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}