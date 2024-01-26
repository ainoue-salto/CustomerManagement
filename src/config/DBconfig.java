package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import servlet.LoginServlet;

/**
 * DB接続情報を取得する
 * 
 * @author ayaka
 *
 */
public class DBconfig {

	// データベースの接続情報を取得するメソッド
	public Map<String, String>  getDBinfo() throws FileNotFoundException {

		// プロパティファイルのフルパスを指定
		String db_properties_file = "/eclipse-jee-oxygen-3a-win32-x86_64/workspace/CustomerManagement/DBconfig.properties";
		
		//上記のプロパティファイルを読み込む為に必要なPropertiesクラスをインスタンス化
		Properties db_info = new Properties();
		//ファイルをJava仮想マシンに読み込む為の入出力ストリームクラス
		FileInputStream db_file_stream = new FileInputStream(db_properties_file);

		/**
		 * @param db_url 接続情報
		 * @param db_user ユーザー名
		 * @param db_password パスワード
		 * @return DBの接続情報
		 * @throws IOException
		 */
		try {
			// プロパティファイルを読み込む
			db_info.load(db_file_stream);
			
			//DBconfig.propertiesのキーから値を取得する
			String db_url = db_info.getProperty("url");
			String db_user = db_info.getProperty("user");
			String db_pass = db_info.getProperty("password");

			// 取得したデータベースの接続情報をMapに格納する
			Map<String,String> getDBinfoForMap = new HashMap<>();

			getDBinfoForMap.put("url", db_url);
			getDBinfoForMap.put("user", db_user);
			getDBinfoForMap.put("password", db_pass);
			
			// DBconfigクラスの
			// getDBinfoメソッドが呼び出された際に
			// 『接続情報、ユーザ名、パスワード』の情報を返す
			return getDBinfoForMap;
			
		} catch (IOException e) {
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
	        logger.severe("データベース設定ファイルが認識できませんでした");
			System.out.println("データベース設定ファイルが認識できませんでした");
			e.printStackTrace();
		}
		return null;
	}
}