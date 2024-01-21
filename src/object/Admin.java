package object;


/**
 *DBから取得した値を格納・取得するオブジェクト 
 * @author ayaka
 * @param id admin_tbテーブルのadmin_idカラムに格納されている値
 * @param name admin_tbテーブルのnameカラムに格納されている値
 * @param password admin_tbテーブルのpasswordカラムに格納されている値
 *
 */
public class Admin {

	private int id;
	private String name;
	private String password;
	private boolean login_flag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogin_flag() {
		return login_flag;
	}

	/**
	 * ログイン画面で入力した値とDBでの値が一致すればtrueを返すフラグ
	 * 
	 * @param login_flag 一致すればtrue,そうでなければfalse
	 */
	public void setLogin_flag(boolean login_flag) {
		this.login_flag = login_flag;
	}
}