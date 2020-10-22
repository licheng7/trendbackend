package cn.arp.trend.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_TREND_AUDIT_LOG")
public class AuditLog {
	@Column(name = "ARGS", length = 4096)
	private String args;
	@Column(name = "HTTP_STATUS")
	private int httpStatus;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "INTERNAL_IP", length = 255)
	private String internalIp;
	@Column(name = "OCCUR_TIME")
	private Date occurTime = new Date();
	@Column(name = "OP_NAME", length = 255)
	private String operationName;
	@Column(name = "REMOTE_IP", length = 255)
	private String remoteIp;
	@Column(name = "REMOTE_PORT")
	private int remotePort;

	@Column(name = "SESSIONID")
	private String sessionId;

	@Column(name = "USER_ID", length = 255)
	private String userId;
	
	@Column(name = "USER_NAME", length = 255)
	private String userName;

	public String getArgs() {
		return args;
	}
	public int getHttpStatus() {
		return httpStatus;
	}

	public int getId() {
		return id;
	}

	public String getInternalIp() {
		return internalIp;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public String getOperationName() {
		return operationName;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setArgs(String argValues) {
		this.args = argValues;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInternalIp(String internalIp) {
		this.internalIp = internalIp;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString() {
		ArrayList<String> parts = new ArrayList<>();
		parts.add(userId);
		parts.add(userName);
		parts.add(operationName);
		parts.add(args);
		parts.add(Integer.toString(httpStatus));
		parts.add(remoteIp);
		parts.add(Integer.toString(remotePort));
		parts.add(sessionId);
		parts.add(internalIp);
		return String.join(" ", parts);
	}

}
