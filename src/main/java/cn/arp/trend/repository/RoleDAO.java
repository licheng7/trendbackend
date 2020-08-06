package cn.arp.trend.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.arp.trend.entity.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {
	@Modifying
	@Transactional
	@Query("delete from Role where id in ?1")
	void deleteByIds(List<Integer> ids);

	@Query("from Role where name=?1")
	Optional<Role> findByName(String name);
}
