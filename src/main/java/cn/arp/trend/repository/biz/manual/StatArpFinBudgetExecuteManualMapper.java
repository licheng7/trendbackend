package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.ExecutionTrendQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpFinBudgetExecuteManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryExecutionTrend(@Param("query") ExecutionTrendQueryDO query);
}