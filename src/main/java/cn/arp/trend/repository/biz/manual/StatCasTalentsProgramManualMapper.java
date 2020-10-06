package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.StatCasTalentsProgram;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatCasTalentsProgramManualMapper {

    /**
     * 各所百人
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatCasTalentsProgram> queryBySslyAndNfAndLx(@Param("startYear") String startYear,
                                                      @Param("endYear") String endYear);
}