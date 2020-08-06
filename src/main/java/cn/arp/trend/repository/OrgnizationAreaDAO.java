package cn.arp.trend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.arp.trend.entity.OrgnizationArea;

@Repository
public interface OrgnizationAreaDAO extends JpaRepository<OrgnizationArea, String>, JpaSpecificationExecutor<OrgnizationArea> {
	@Modifying
	@Transactional
	@Query("delete from OrgnizationArea where orgId in ?1")
	void deleteByIds(List<String> orgIds);
	
	
	OrgnizationArea findByIp(String ip);
}
