package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.PersonTypeDistributionQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpStaffManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryPersonTypeDistribution(
            @Param("query") PersonTypeDistributionQueryDO query);
}