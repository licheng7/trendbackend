package cn.arp.trend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.arp.trend.entity.Menu;

@Repository
public interface MenuDAO extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {

	@Modifying
	@Query("delete from Menu where id in ?1")
	void deleteByIds(List<String> filtered);

}
