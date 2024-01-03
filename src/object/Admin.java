package object;

//DBから取得した値を格納・取得するオブジェクト
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

	//ログイン画面で入力した値とDBでの値が一致すればtrueを返すフラグ
	public void setLogin_flag(boolean login_flag) {
		this.login_flag = login_flag;
	}
}