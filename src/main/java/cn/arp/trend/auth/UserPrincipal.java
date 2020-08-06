package cn.arp.trend.auth;

import java.security.Principal;

public class UserPrincipal implements Principal {
	private String email;
	private String userName;

	public UserPrincipal(String email, String userName) {
		this.email = email;
		this.userName = userName;
	}
	public UserPrincipal(){
		//do nothing
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getName() {
		return email;
	}

	public String getUserName() {
		return userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
