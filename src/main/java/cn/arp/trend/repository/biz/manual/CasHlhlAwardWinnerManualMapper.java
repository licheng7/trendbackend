package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.DetailAwardDetailQueryDO;
import cn.arp.trend.data.model.DO.DetailAwardTrendQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CasHlhlAwardWinnerManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAward2(@Param("query") DetailAwardTrendQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAward3(@Param("query") DetailAwardTrendQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAward4(@Param("query") DetailAwardTrendQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAwardDetail1(@Param("query") DetailAwardDetailQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAwardDetail2(@Param("query") DetailAwardDetailQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAwardDetail3(@Param("query") DetailAwardDetailQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAwardDetail4(@Param("query") DetailAwardDetailQueryDO query,
                                                @Param("jxlb") String jxlb);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAwardDetail7(@Param("query") DetailAwardDetailQueryDO query);
}