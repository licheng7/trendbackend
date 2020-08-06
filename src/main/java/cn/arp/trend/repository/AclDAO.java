package cn.arp.trend.repository;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.arp.trend.entity.Acl;
import cn.arp.trend.entity.VAcl;

@Repository
public interface AclDAO extends JpaRepository<Acl, Integer>, JpaSpecificationExecutor<Acl> {
	@Modifying
	@Transactional
	@Query("delete from Acl where id in ?1")
	void deleteByIds(Collection<Integer> ids);

	@Query("from VAcl where userId=?1")
	List<VAcl> findAcls(String userId);

}
