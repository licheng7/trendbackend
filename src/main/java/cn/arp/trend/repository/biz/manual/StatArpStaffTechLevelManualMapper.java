package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.PositionDistributionQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpStaffTechLevelManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryPositionDistribution(
            @Param("query") PositionDistributionQueryDO query);

}