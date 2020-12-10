package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.DetailAwardTrendQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CasChinaAward10YearFinalManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAward1(@Param("query") DetailAwardTrendQueryDO query);

    /**
     *
     * @param orgList
     * @return
     */
    List<Map<String, Object>> queryCompareAward1(@Param("orgList") List<String> orgList);

    /**
     *
     * @return
     */
    List<Map<String, Object>> queryCompareAward2();

    /**
     *
     * @return
     */
    List<Map<String, Object>> queryCompareAward3();

    /**
     *
     * @return
     */
    List<Map<String, Object>> queryCompareAward4();
}