package cn.arp.trend.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.arp.trend.entity.Role;
import cn.arp.trend.entity.RoleMember;
import cn.arp.trend.error.RestError;
import cn.arp.trend.repository.RoleDAO;
import cn.arp.trend.repository.RoleMemberDAO;
import cn.arp.trend.service.EventService;
import cn.arp.trend.service.RoleService;
import cn.arp.trend.tools.PredicateBuilder;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO dao;
	@Autowired
	private RoleMemberDAO members;
	@Autowired
	private EventService events;

	@Override
	public Role create(Role role) {
		return dao.save(role);
	}

	@Override
	@Transactional
	public void delete(int roleId) {
		dao.deleteById(roleId);
		members.delelteByRoleId(roleId);
		events.fireEvent("DeleteRole", roleId);
	}

	@Override
	public Role findById(int roleId) {
		Optional<Role> opt = dao.findById(roleId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Role> findByIds(Collection<Integer> roleIds) {
		return dao.findAllById(roleIds);
	}

	@Override
	@Transactional
	public void addMember(int roleId, String userId) {
		RoleMember membership = members.findByUserIdAndRoleId(roleId, userId);
		if (membership == null) {
			membership = newMembers(roleId, userId);
			members.save(membership);
		}
	}

	private RoleMember newMembers(int roleId, String userId) {
		RoleMember membership;
		membership = new RoleMember();
		membership.setRoleId(roleId);
		membership.setUserId(userId);
		return membership;
	}

	@Override
	public void addMembers(int roleId, Collection<String> userIds) throws RestError {
		Role role = findById(roleId);
		if (role!=null){
			List<String> list = members.findExist(roleId, userIds);
			List<RoleMember> memberList = new ArrayList<>();
			for (String userId : userIds) {
				if (!list.contains(userId)) {
					memberList.add(newMembers(roleId, userId));
				}
			}
			if (memberList.size() > 0) {
				members.saveAll(memberList);
			}
		}else{
			throw RestError.badArgument("目标的角色(id="+roleId+")未找到");
		}
	}

	@Override
	public List<Role> findUserRoles(String userId) {
		List<Integer> roleIds = members.findAllRoleId(userId);
		return dao.findAllById(roleIds);
	}

	@Override
	public void removeMemeber(int roleId, String userId) {
		members.removeMember(roleId, userId);

	}

	@Override
	public void removeMembers(int roleId, Collection<String> userIds) {
		members.removeMember(roleId, userIds);
	}

	@Override
	public Page<Role> find(String q, Pageable pageRequest) {
		return dao.findAll(new Specification<Role>(){
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				PredicateBuilder<Role> builder = new PredicateBuilder<>(criteriaBuilder, root);
				return builder.include("name", q).include("description", q).or();
			}
			
		}, pageRequest);
	}

	@Override
	@Transactional
	public void deleteByIds(List<Integer> ids) {
		dao.deleteByIds(ids);
		members.delelteByRoleIds(ids);
		events.fireEvent("DeleteRoles", ids);
	}

	@Override
	public Role update(Role t) {
		return dao.save(t);
	}

	@Override
	public Page<RoleMember> findMembers(int roleId, String q, Pageable pageRequest) {
		return members.findAll(new Specification<RoleMember>(){
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<RoleMember> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				PredicateBuilder<RoleMember> builder = new PredicateBuilder<>(criteriaBuilder, root);
				builder.equals("roleId", Integer.valueOf(roleId));
				builder.include("userId", q);
				return builder.and();
			}
		}, pageRequest);
	}

	@Override
	public Optional<Role> findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public void removeAllMembers(int roleId) {
		members.delelteByRoleId(roleId);
	}
}
