package cn.arp.trend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_TREND_ROLE_MEMBER")
public class RoleMember {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "ROLE_ID")
	private int roleId;
	@Column(name = "USER_ID")
	private String userId;

	public int getId() {
		return id;
	}

	public int getRoleId() {
		return roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
