package object;

//DB����擾�����l���i�[�E�擾����I�u�W�F�N�g
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

	//���O�C����ʂœ��͂����l��DB�ł̒l����v�����true��Ԃ��t���O
	public void setLogin_flag(boolean login_flag) {
		this.login_flag = login_flag;
	}
}