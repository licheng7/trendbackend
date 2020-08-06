package cn.arp.statistic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Access Control List
 * @author xiejj@cnic.cn
 *
 */
@Entity
@Table(name = "T_TREND_ACL")
public class Acl {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "ORG_ID")
	private String orgId;

	@Column(name = "USER_ID")
	private String userId;

	public int getId() {
		return id;
	}
	public String getOrgId() {
		return orgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
