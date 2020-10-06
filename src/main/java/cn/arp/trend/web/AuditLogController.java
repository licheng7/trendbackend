package cn.arp.trend.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.entity.AuditLog;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.AuditLogService;
import cn.arp.trend.tools.PageParam;
import cn.arp.trend.tools.ResponseBuilder;

@RestController
@RequestMapping("/auditLogs")
@RequirePermission(roles="admin")
public class AuditLogController {
	@Autowired
	private AuditLogService logService;
	@GetMapping
	@Audit(desc="查看审计日志")
	public ResponseEntity<List<AuditLog>> list(@RequestParam String q,
					@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start,
					@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  Date end
					, PageParam page) throws RestError {
		Page<AuditLog> result = logService.find(start, end, q, page.toPageRequest());
		return ResponseBuilder.build(result);
	}
}
