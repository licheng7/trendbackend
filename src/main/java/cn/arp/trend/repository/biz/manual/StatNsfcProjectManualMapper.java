package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.StatNsfcProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatNsfcProjectManualMapper {

    /**
     * 各所自然科学基金项目
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatNsfcProject> queryByNf(@Param("startYear") String startYear,
                                    @Param("endYear") String endYear);
}