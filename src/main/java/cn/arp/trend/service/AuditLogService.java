package cn.arp.trend.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import cn.arp.trend.entity.AuditLog;

public interface AuditLogService {
	void asyncInsert(AuditLog log);

	Page<AuditLog> find(Date start, Date end, String q, PageRequest pageRequest);
}
