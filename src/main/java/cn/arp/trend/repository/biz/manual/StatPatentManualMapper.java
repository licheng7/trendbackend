package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.StatPatent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatPatentManualMapper {

    /**
     * 各所专利
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatPatent> queryByNf(@Param("startYear") String startYear,
                               @Param("endYear") String endYear);
}