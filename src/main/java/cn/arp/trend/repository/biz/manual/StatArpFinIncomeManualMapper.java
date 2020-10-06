package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.Funds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatArpFinIncomeManualMapper {

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<Funds> queryFunds(@Param("startYear") String startYear,
                           @Param("endYear") String endYear);
}