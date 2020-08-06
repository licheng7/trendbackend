package cn.arp.trend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.arp.trend.data.model.DataModel;
import cn.arp.trend.entity.StatisticOrgAddress;

@Repository
public interface StatisticOrgAddressRepository
		extends JpaRepository<StatisticOrgAddress, String>, JpaSpecificationExecutor<StatisticOrgAddress> {

	@Query(value = "select new cn.arp.statistic.data.model.DataModel(s.address as name,count(*) as value) from StatisticOrgAddress s group by s.address order by value desc")
	public List<DataModel> getOrgAddressCount();

}
