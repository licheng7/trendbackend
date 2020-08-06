package cn.arp.trend.repository;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.arp.trend.entity.RoleMember;

@Repository
public interface RoleMemberDAO extends JpaRepository<RoleMember, Integer>, JpaSpecificationExecutor<RoleMember> {
	@Modifying
	@Transactional
	@Query("delete from RoleMember where roleId=?1")
	void delelteByRoleId(int roleId);

	@Query("from RoleMember where roleId=?1 and userId=?2")
	RoleMember findByUserIdAndRoleId(int roleId, String userId);

	@Query("select userId from RoleMember where roleId=?1 and userId in ?2")
	List<String> findExist(int roleId, Collection<String> userIds);

	@Query("select roleId from RoleMember where userId = ?1")
	List<Integer> findAllRoleId(String userId);

	@Modifying
	@Transactional
	@Query("delete from RoleMember where roleId =?1 and userId=?2")
	void removeMember(int roleId, String userId);

	@Modifying
	@Transactional
	@Query("delete from RoleMember where roleId =?1 and userId in ?2")
	void removeMember(int roleId, Collection<String> userIds);

	@Modifying
	@Transactional
	@Query("delete from RoleMember where roleId in ?1")
	void delelteByRoleIds(List<Integer> ids);
}
