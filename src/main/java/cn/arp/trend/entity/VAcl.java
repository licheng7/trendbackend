package cn.arp.trend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_TREND_ACL")
public class VAcl {
	@Id
	@Column(name = "ID")
	private long id;
	@Column(name = "ORG_ID")
	private String orgId;
	@Column(name = "ORGNAME")
	private String orgName;
	@Column(name = "USER_ID")
	private String userId;

	public long getId() {
		return id;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getUserId() {
		return userId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
