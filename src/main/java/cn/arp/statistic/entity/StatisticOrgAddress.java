package cn.arp.statistic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_TREND_ORG_AREA")
public class StatisticOrgAddress {

	@Id
	@Column(name = "ORGID")
	private String orgId;

	@Column(name = "ORGNAME")
	private String orgName;

	@Column(name = "IP")
	private String ip;

	@Column(name = "ADDRESS")
	private String address;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
