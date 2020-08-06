package cn.arp.trend.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.arp.trend.entity.OrgnizationArea;
import cn.arp.trend.error.RestError;
import cn.arp.trend.repository.OrgnizationAreaDAO;
import cn.arp.trend.service.OrgnizationAreaService;
import cn.arp.trend.tools.PredicateBuilder;

@Service
public class OrgnizationAreaServiceImpl implements OrgnizationAreaService {
	@Autowired
	private OrgnizationAreaDAO dao;

	@Override
	public Page<OrgnizationArea> find(String q, Pageable page) {
		return dao.findAll(new Specification<OrgnizationArea>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<OrgnizationArea> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				PredicateBuilder<OrgnizationArea> builder = new PredicateBuilder<>(criteriaBuilder, root);
				return builder.include("orgName", q).include("orgId", q).include("address", q).or();
			}
		}, page);
	}

	@Override
	public OrgnizationArea create(OrgnizationArea org) throws RestError {
		OrgnizationArea old = find(org.getOrgId());
		if (old != null) {
			throw RestError.badArgument("机构已存在。");
		}
		return dao.save(org);
	}

	@Override
	public OrgnizationArea update(OrgnizationArea org) {
		return dao.save(org);
	}

	@Override
	public OrgnizationArea find(String orgId) {
		Optional<OrgnizationArea> opt = dao.findById(orgId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public void delete(String orgId) {
		dao.deleteById(orgId);
	}

	@Override
	public void delete(List<String> orgIds) {
		dao.deleteByIds(orgIds);
	}

	@Override
	public List<OrgnizationArea> find(List<String> orgIds) {
		return dao.findAllById(orgIds);
	}

	@Override
	public String findOrgId(String ip) {
		OrgnizationArea area = this.dao.findByIp("172.31.160");
		return area.getOrgId();
	}

}
