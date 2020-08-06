package cn.arp.trend.service.impl;

import java.util.Collection;
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

import cn.arp.trend.entity.Acl;
import cn.arp.trend.entity.VAcl;
import cn.arp.trend.repository.AclDAO;
import cn.arp.trend.repository.VAclDAO;
import cn.arp.trend.service.AclService;
import cn.arp.trend.tools.PredicateBuilder;

@Service
public class AclServiceImpl implements AclService {
	@Autowired
	private AclDAO dao;
	@Autowired
	private VAclDAO vdao;
	public AclServiceImpl(){
		System.out.println("hello");
	}
	@Override
	public List<VAcl> findAcls(String userId) {
		return dao.findAcls(userId);
	}

	@Override
	public Acl save(Acl acl) {
		return dao.save(acl);
	}

	@Override
	public Acl find(String userId, String orgId) {
		Optional<Acl> opt = dao.findOne(new Specification<Acl>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Acl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("orgId"), orgId), cb.equal(root.get("userId"), userId));
			}
		});

		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public Acl find(Integer id) {
		Optional<Acl> opt = dao.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Acl> findAll(List<Integer> ids) {
		return dao.findAllById(ids);
	}

	@Override
	public Page<VAcl> find(String q, Pageable page) {
		return vdao.findAll(new Specification<VAcl>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<VAcl> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				PredicateBuilder<VAcl> builder = new PredicateBuilder<VAcl>(criteriaBuilder, root);
				return builder.include("orgId", q)
						.include("orgName", q)
						.include("userId", q)
						.or();
			}

		}, page);
	}

	@Override
	public void remove(Collection<Integer> ids) {
		dao.deleteByIds(ids);
	}

	@Override
	public void remove(Integer k) {
		dao.deleteById(k);
	}

	@Override
	public Page<Acl> findOrgAcls(String orgId,String q,  Pageable page) {
		return dao.findAll(new Specification<Acl>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Acl> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				PredicateBuilder<Acl> builder = new PredicateBuilder<Acl>(criteriaBuilder, root);
				return builder.equal("orgId", orgId).include("userId", q).and();
			}

		}, page);
	}

	@Override
	public Page<VAcl> findUserAcls(String userId,String q, Pageable page) {
		return vdao.findAll(new Specification<VAcl>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<VAcl> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				PredicateBuilder<VAcl> builder = new PredicateBuilder<VAcl>(criteriaBuilder, root);
				PredicateBuilder<VAcl> orBuilder = new PredicateBuilder<VAcl>(criteriaBuilder, root);
				return builder.equal("userId", userId)
						.addPredicate(orBuilder.include("orgId", q).include("orgName", q).or()).and();
			}

		}, page);
	}
}
