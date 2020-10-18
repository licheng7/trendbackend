package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.ChildLevelDistributionQueryDO;
import cn.arp.trend.data.model.DO.DrRankQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpStaffEducationManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryChildLevelDistribution(
            @Param("query") ChildLevelDistributionQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryDrRank(
            @Param("query") DrRankQueryDO query);
}