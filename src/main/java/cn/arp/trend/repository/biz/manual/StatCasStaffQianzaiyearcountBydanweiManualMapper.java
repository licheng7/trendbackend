package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.DistributionAffiliationQueryDO;
import cn.arp.trend.data.model.DO.DistributionFieldQueryDO;
import cn.arp.trend.data.model.DO.YoungEliteQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatCasStaffQianzaiyearcountBydanweiManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryYoungEliteTrend(@Param("query") YoungEliteQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryDistributionField(@Param("query") DistributionFieldQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryDistributionAffiliation(@Param("query") DistributionAffiliationQueryDO query);
}