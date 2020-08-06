package cn.arp.trend.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.arp.trend.entity.Role;
import cn.arp.trend.entity.RoleMember;
import cn.arp.trend.error.RestError;

public interface RoleService {
	Role create (Role role);
	void delete(int roleId);
	Role findById(int roleId);
	List<Role> findByIds(Collection<Integer> roleIds);
	Page<Role> find(String q, Pageable pageRequest);
	void deleteByIds(List<Integer> ids);
	Role update(Role t);

	List<Role> findUserRoles(String userId);
	void addMember(int roleId, String userId);
	void addMembers(int roleId, Collection<String> userIds) throws RestError;
	void removeMemeber(int roleId, String userId);
	void removeMembers(int roleId, Collection<String> userIds);
	Page<RoleMember> findMembers(int roleId, String q, Pageable pageRequest);
	Optional<Role> findByName(String name);
	void removeAllMembers(int roleId);
}
