package cn.arp.statistic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_STATISTIC_ORG_AREA")
public class OrgnizationArea {
	private static final String HEAD_QUARTER = "310111";
	@Id
	@Column(name = "ORGID")
	private String orgId;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "IP")
	private String ip;
	@Column(name = "ORGNAME")
	private String orgName;
	
	@Column(name="BRANCH")
	private String branch;

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAddress() {
		return address;
	}

	public String getIp() {
		return ip;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String composeIP(HostType type) {
		String result = ip;
		if (HEAD_QUARTER.equals(orgId)) {
			switch (type) {
			case APP:
				result = result + ".130";
				break;
			case THIRD_PARTY:
				result = result + ".132";
				break;
			case DB:
				result = result + ".134";
				break;
			}
		}else{
			switch (type) {
			case APP:
				result = result + ".30";
				break;
			case THIRD_PARTY:
				result = result + ".32";
				break;
			case DB:
				result = result + ".34";
				break;
			}
		}
		return result;
	}
}
