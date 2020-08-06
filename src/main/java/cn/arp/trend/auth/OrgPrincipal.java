package cn.arp.trend.auth;

import java.security.Principal;

public class OrgPrincipal implements Principal {
	private String orgId;

	private String orgName;

	public OrgPrincipal() {
		// do nothing
	}

	public OrgPrincipal(String orgId, String orgName) {
		this.orgId = orgId;
		this.orgName = orgName;
	}

	@Override
	public String getName() {
		return orgId;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
