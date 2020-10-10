package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.entity.biz.StatMostProject;
import cn.arp.trend.entity.biz.CompareProjectObj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatMostProjectManualMapper {

    /**
     * 各所科技部项目
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatMostProject> queryByNf(@Param("startYear") String startYear,
                                    @Param("endYear") String endYear);

    /**
     *
     * @param projectQuery
     * @return
     */
    List<CompareProjectObj> queryProject(@Param("query") ProjectQueryDO projectQuery);
}