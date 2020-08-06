package cn.arp.trend.auth;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;

public class UserSubject implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<OrgPrincipal> orgs;
	private List<RolePrincipal> roles;
	private UserPrincipal user;

	public boolean canAccess(String orgId) {
		if (orgs != null) {
			for (OrgPrincipal org : orgs) {
				if (org.getName().equals(orgId)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasRole(String roleName){
		if (roles!=null){
			for (RolePrincipal role:roles){
				if (StringUtils.equals(roleName, role.getName())){
					return true;
				}
			}
		}
		return false;
	}

	public List<OrgPrincipal> getOrgs() {
		return orgs;
	}

	public List<RolePrincipal> getRoles() {
		return roles;
	}

	public UserPrincipal getUser() {
		return user;
	}

	public void setOrgs(List<OrgPrincipal> orgs) {
		this.orgs = orgs;
	}

	public void setRoles(List<RolePrincipal> roles) {
		this.roles = roles;
	}

	public void setUser(UserPrincipal user) {
		this.user = user;
	}
}
