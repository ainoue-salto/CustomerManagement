package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBconfig;

public class Register {

	public void customer_register(int admin_id, String name, String address, String email) throws FileNotFoundException {

		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// 実行SQL
		String register_sql = "insert into customer_tb"
				+ "(admin_id, name, address, email) values(?,?,?,?)";

		// データベースへの接続
		// try〜catch〜resources構文を使用
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			// オートコミット機能(SQL文の実行ごとに自動的にコミット処理を行う機能)を無効化
			conn.setAutoCommit(false);

			
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
				System.out.println("コミット処理を行いました");
			} catch (SQLException e) {
				//登録処理中に予期せぬ事態が発生した場合、登録までの一連の処理を無効にする
				conn.rollback();
				System.out.println("ロールバック処理を行いました");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}