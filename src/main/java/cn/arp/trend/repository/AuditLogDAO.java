package cn.arp.trend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.arp.trend.entity.AuditLog;

public interface AuditLogDAO extends JpaRepository<AuditLog, Integer>, JpaSpecificationExecutor<AuditLog> {
	
}
