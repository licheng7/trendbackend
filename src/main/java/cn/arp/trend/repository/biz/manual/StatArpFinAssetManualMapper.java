package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AreaFinanceQueryDO;
import cn.arp.trend.data.model.DO.OverviewQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpFinAssetManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryOverview1(@Param("query") OverviewQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryOverview2(@Param("query") OverviewQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryFinanceOverview(@Param("query") AreaFinanceQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryFinanceOverview2(@Param("query") AreaFinanceQueryDO query);
}