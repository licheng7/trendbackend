package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.StatHcauthorsCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatHcauthorsCountManualMapper {

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatHcauthorsCount> queryScientist1(@Param("startYear") String startYear,
                                             @Param("endYear") String endYear);

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatHcauthorsCount> queryScientist2(@Param("startYear") String startYear,
                                             @Param("endYear") String endYear);

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<Map<String, Object>> queryScientist3(@Param("startYear") String startYear,
                                              @Param("endYear") String endYear);

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<Map<String, Object>> queryScientist4(@Param("startYear") String startYear,
                                              @Param("endYear") String endYear);
}