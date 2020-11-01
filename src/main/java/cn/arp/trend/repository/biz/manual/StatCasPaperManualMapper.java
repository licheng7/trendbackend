package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AreaPaperQueryDO;
import cn.arp.trend.data.model.DO.PaperSciQueryDO;
import cn.arp.trend.entity.biz.StatCasPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatCasPaperManualMapper {

    /**
     * 各所论文
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatCasPaper> queryByNf(@Param("startYear") String startYear,
                                 @Param("endYear") String endYear);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> querySci1(@Param("query") PaperSciQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> querySci2(@Param("query") PaperSciQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPaperSci1(@Param("query") AreaPaperQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPaperSci2(@Param("query") AreaPaperQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPaperSci3(@Param("query") AreaPaperQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPaperSci4(@Param("query") AreaPaperQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPaperSci5(@Param("query") AreaPaperQueryDO query);
}