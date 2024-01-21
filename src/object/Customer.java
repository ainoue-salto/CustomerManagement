package object;

import java.util.Date;

/**
 * DB‚©‚çæ“¾‚µ‚½ŒÚ‹qî•ñ‚Ì’l‚ğŠi”[‚·‚é
 * 
 * @author ayaka
 *@@param customer_id  customer_tbƒe[ƒuƒ‹‚ÌåƒL[
 *@@param admin_id ƒƒOƒCƒ“‚µ‚½ŠÇ—Ò‚ÌŠÇ—Òid(ƒƒOƒCƒ“‚µ‚½l‚ª“o˜^‚µ‚½ŒÚ‹qî•ñ‚ğ•ª‚¯‚é‚½‚ß)
 *@@param name ŒÚ‹q–¼
 *@@param email ŒÚ‹q‚Ìƒ[ƒ‹ƒAƒhƒŒƒX
 *@@param address ŒÚ‹q‚ÌZŠ
 *@@param registered_time “o˜^“ú
 * @param update_time XV“ú
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