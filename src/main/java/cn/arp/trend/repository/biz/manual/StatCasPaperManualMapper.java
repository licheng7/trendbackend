package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.StatCasPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatCasPaperManualMapper {

    /**
     * 各所论文
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatCasPaper> queryByNf(@Param("startYear") String startYear,
                                 @Param("endYear") String endYear);
}