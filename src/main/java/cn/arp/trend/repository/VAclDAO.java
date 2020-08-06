package cn.arp.trend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.arp.trend.entity.VAcl;

public interface VAclDAO extends JpaRepository<VAcl, Integer>, JpaSpecificationExecutor<VAcl> {

}
