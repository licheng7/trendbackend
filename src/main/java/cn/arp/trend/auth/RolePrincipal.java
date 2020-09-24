package cn.arp.trend.auth;

import java.security.Principal;

public class RolePrincipal implements Principal {
	private String description;
	private String name;
	private int roleId;
	
	public RolePrincipal()	{
		
	}
	public RolePrincipal(int roleId, String name, String description){
		this.name = name;
		this.setRoleId(roleId);
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
