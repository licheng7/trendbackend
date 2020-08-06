package cn.arp.statistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.arp.statistic.entity.VAcl;

public interface VAclDAO extends JpaRepository<VAcl, Integer>, JpaSpecificationExecutor<VAcl> {

}
