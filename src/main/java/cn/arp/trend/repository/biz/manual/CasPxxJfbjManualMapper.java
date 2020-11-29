package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.CasPxxCzbk;
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
    List<CasPxxJfbj> queryFunds(@Param("startYear") String startYear, @Param("endYear") String endYear);

    /**
     *
     * @param startYear
     * @param endYear
     * @param ysYear
     * @return
     */
    List<CasPxxJfbj> queryNewFunds(@Param("startYear") String startYear, @Param("endYear") String endYear, @Param("ysYear") String ysYear);

    /**
     *
     * @param startYear
     * @param endYear
     * @param ysYear
     * @return
     */
    List<CasPxxJfbj> queryNewFinance(@Param("startYear") String startYear, @Param("endYear") String endYear, @Param("ysYear") String ysYear);
}