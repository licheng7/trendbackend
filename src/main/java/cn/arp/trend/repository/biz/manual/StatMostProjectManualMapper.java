package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.entity.biz.CompareProjectObj;
import cn.arp.trend.entity.biz.StatMostProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * @param query
     * @return
     */
    List<CompareProjectObj> queryProject(@Param("query") ProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryKjb1(@Param("query") ProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryKjb2(@Param("query") ProjectQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryKjb3(@Param("query") ProjectQueryDO query);
}