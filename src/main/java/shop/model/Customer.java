package shop.model;

public class Customer {
	
	private String Customername;
	 private long Phonenumber;
	 private String Email;
	 private String Password;
	 public Customer() {}
	public Customer(String customername, long phonenumber, String email, String password) {
		super();
		Customername = customername;
		Phonenumber = phonenumber;
		Email = email;
		Password = password;
	}
	public String getCustomername() {
		return Customername;
	}
	public void setCustomername(String customername) {
		Customername = customername;
	}
	public long getPhonenumber() {
		return Phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		Phonenumber = phonenumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	 
	

}
