package cn.arp.trend.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.arp.icas.batch.CollectivePool;
import cn.arp.icas.batch.Consumer;
import cn.arp.trend.entity.AuditLog;
import cn.arp.trend.repository.AuditLogDAO;
import cn.arp.trend.service.AuditLogService;
import cn.arp.trend.tools.PredicateBuilder;

@Service
public class AuditLogServiceImpl implements AuditLogService {
	@Autowired
	private AuditLogDAO dao;
	private CollectivePool<AuditLog> pool;

	@PostConstruct
	private void setUp() {
		pool = new CollectivePool<>(20, 5, new Consumer<AuditLog>() {
			@Override
			public int onBath(List<AuditLog> data) {
				dao.saveAll(data);
				return data.size();
			}

		});
	}

	@PreDestroy
	private void tearDown() {
		pool.shutdown();
	}

	@Override
	public void asyncInsert(AuditLog log) {
		pool.add(log);
	}

	@Override
	public Page<AuditLog> find(Date start, Date end, String q, PageRequest pageRequest) {
		return dao.findAll(new Specification<AuditLog>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<AuditLog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				PredicateBuilder<AuditLog> builder = new PredicateBuilder<AuditLog>(criteriaBuilder, root);
				builder
				.greaterThanOrEqualTo("occurTime", start)
				.lessThanOrEqualTo("occurTime", end);
				
				builder.subBuilder()
						.include("operationName", q)
						.include("userName", q)
						.include("userId", q)
						.include("remoteIp", q).or();
				return builder.and();
			}

		}, pageRequest);
	}

}
