package vo;

import java.io.Serializable;


public class ManagerVO  implements Serializable {
	String username;
	String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ManagerVO(String username, String password) {
		this.username=username;
		this.password=password;
	}
		// TODO Auto-generated constructor stub
	}
