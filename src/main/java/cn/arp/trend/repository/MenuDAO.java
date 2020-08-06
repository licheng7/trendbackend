package cn.arp.trend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.arp.trend.entity.Menu;

public interface MenuDAO extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {

}
