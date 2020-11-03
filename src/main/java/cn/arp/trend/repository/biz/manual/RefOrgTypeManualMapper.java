package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.*;
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
    List<RefOrgType> queryOrgByJGBHAndResearch(
            @Param("orgInfoQuery") OrgInfoQueryDO orgInfoQueryDO);

    /**
     *
     * @param orgInfoQueryDO
     * @return
     */
    List<String> queryResearchByJGBHAndResearch(
            @Param("orgInfoQuery") OrgInfoQueryDO orgInfoQueryDO);

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
    List<Map<String, Object>> queryAwardDistribution(
            @Param("query") DetailAwardDistributionQueryDO query);

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
    List<Map<String, Object>> queryAreaAwardDistribution(
            @Param("query") DetailAwardDistributionQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectXdzx1(@Param("query")AreaProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectXdzx2(@Param("query")AreaProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectXdzx3(@Param("query")AreaProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectXdzx4(@Param("query")AreaProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectZdyf1(@Param("query")AreaProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectZdyf2(@Param("query")AreaProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectZdyf3(@Param("query")AreaProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectZdyf4(@Param("query")AreaProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectNsfcTrend1(
            @Param("query") AreaProjectNsfcTrendQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectNsfcTrend2(
            @Param("query") AreaProjectNsfcTrendQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectNsfcDist1(
            @Param("query") AreaProjectNsfcDistQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectNsfcDist2(
            @Param("query") AreaProjectNsfcDistQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaProjectNsfcDist3(
            @Param("query") AreaProjectNsfcDistQueryDO query);
}