package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.CasPxxCzbk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CasStatsScienceActivitiesManualMapper {

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<CasPxxCzbk> queryFinance(@Param("startYear") String startYear, @Param("endYear") String
            endYear);
}