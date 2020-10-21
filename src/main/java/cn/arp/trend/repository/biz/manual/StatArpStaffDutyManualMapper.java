package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.IncreaseTrendQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpStaffDutyManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryIncreaseTrend(@Param("query")IncreaseTrendQueryDO query);
}