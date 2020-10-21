package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.PostAnalyzeQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpStaffPromotionManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryPostAnalyze(@Param("query") PostAnalyzeQueryDO query);
}