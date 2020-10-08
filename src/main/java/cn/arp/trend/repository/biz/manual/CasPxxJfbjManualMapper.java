package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.CasPxxJfbj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CasPxxJfbjManualMapper {

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<CasPxxJfbj> queryFunds(@Param("startYear") String startYear, @Param("endYear") String
            endYear);
}