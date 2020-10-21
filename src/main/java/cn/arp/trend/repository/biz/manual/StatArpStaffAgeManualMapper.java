package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AgeDistributionQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpStaffAgeManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAgeDistribution(@Param("query") AgeDistributionQueryDO query);
}