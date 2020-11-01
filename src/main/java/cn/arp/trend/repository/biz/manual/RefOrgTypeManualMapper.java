package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.DetailAwardDistributionQueryDO;
import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.entity.biz.RefOrgType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RefOrgTypeManualMapper {
    /**
     *
     * @param orgInfoQueryDO
     * @return
     */
    List<RefOrgType> queryOrgByJGBHAndResearch(@Param("orgInfoQuery") OrgInfoQueryDO
                                                       orgInfoQueryDO);

    /**
     *
     * @param orgInfoQueryDO
     * @return
     */
    List<String> queryResearchByJGBHAndResearch(@Param("orgInfoQuery") OrgInfoQueryDO
                                                        orgInfoQueryDO);

    /**
     *
     * @return
     */
    List<RefOrgType> queryOrgAndResearchByAll();

    /**
     * 中科院各所名称、领域
     * @return
     */
    List<RefOrgType> queryJgmcAndResearchAndSsfy();

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAwardDistribution(@Param("query")
                                                     DetailAwardDistributionQueryDO query);

    /**
     *
     * @return
     */
    List<RefOrgType> querySorting();

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaAwardDistribution(@Param("query")
                                                     DetailAwardDistributionQueryDO query);
}