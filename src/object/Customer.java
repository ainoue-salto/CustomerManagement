package object;

import java.util.Date;

/**
 * DBから取得した顧客情報の値を格納する
 * 
 * @author ayaka
 *　@param customer_id  customer_tbテーブルの主キー
 *　@param admin_id ログインした管理者の管理者id(ログインした人が登録した顧客情報を分けるため)
 *　@param name 顧客名
 *　@param email 顧客のメールアドレス
 *　@param address 顧客の住所
 *　@param registered_time 登録日時
 * @param update_time 更新日時
 *
 *
 */
public class Customer {

	private int customer_id;
	private int admin_id;
	private String name;
	private String email;
	private String address;
	private Date registered_time;
	private Date updated_time;


	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegistered_time() {
		return registered_time;
	}
	public void setRegistered_time(Date registered_time) {
		this.registered_time = registered_time;
	}
	public Date getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}
}