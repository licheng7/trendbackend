package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AreaFinanceQueryDO;
import cn.arp.trend.data.model.DO.AssetDetailQueryDO;
import cn.arp.trend.entity.biz.Funds;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatArpFinIncomeManualMapper {

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<Funds> queryFunds(@Param("startYear") String startYear,
                           @Param("endYear") String endYear);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAssetDetail1(@Param("query") AssetDetailQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAssetDetail2(@Param("query") AssetDetailQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAssetDetail3(@Param("query") AssetDetailQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAssetDetail4(@Param("query") AssetDetailQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaFinanceIncome(@Param("query") AreaFinanceQueryDO query);

    /**
     * 
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaFinanceOutcome(@Param("query") AreaFinanceQueryDO query);
}