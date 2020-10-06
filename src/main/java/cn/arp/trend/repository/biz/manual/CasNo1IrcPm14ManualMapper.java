package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.CasNo1IrcPm14;
import cn.arp.trend.entity.biz.Funds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CasNo1IrcPm14ManualMapper {

    /**
     * 各所经费
     * @param startYear
     * @param endYear
     * @return
     */
    List<CasNo1IrcPm14> queryByNf(@Param("startYear") String startYear,
                                  @Param("endYear") String endYear);

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<Funds> queryFunds(@Param("startYear") String startYear,
                           @Param("endYear") String endYear);
}