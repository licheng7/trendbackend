package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.PostDistributionQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpStaffPostManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryPostDistribution(@Param("query")PostDistributionQueryDO query);
}