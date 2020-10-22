package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.IncreaseTrendQueryDO;
import cn.arp.trend.data.model.DO.ProjectQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpStaffDutyManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryIncreaseTrend(@Param("query") IncreaseTrendQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryNsfcRelation(@Param("query") ProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryKjbRelation(@Param("query") ProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryXdRelation(@Param("query") ProjectQueryDO query);
}