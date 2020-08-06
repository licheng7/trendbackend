package cn.arp.trend.auth;

import java.security.Principal;

public class RolePrincipal implements Principal {
	private String description;
	private String name;
	
	public RolePrincipal()	{
		
	}
	public RolePrincipal(String name, String description){
		this.name = name;
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

}
