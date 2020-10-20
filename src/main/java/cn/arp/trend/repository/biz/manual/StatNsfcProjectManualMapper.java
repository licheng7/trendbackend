package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.entity.biz.CompareProjectObj;
import cn.arp.trend.entity.biz.StatNsfcProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatNsfcProjectManualMapper {

    /**
     * 各所自然科学基金项目
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatNsfcProject> queryByNf(@Param("startYear") String startYear,
                                    @Param("endYear") String endYear);

    /**
     *
     * @param projectQuery
     * @return
     */
    List<CompareProjectObj> queryProject(@Param("query") ProjectQueryDO projectQuery);

    /**
     *
     * @param projectQuery
     * @return
     */
    List<Map<String, Object>> queryNsfc1(@Param("query") ProjectQueryDO projectQuery);

    /**
     *
     * @param projectQuery
     * @return
     */
    List<Map<String, Object>> queryNsfc2(@Param("query") ProjectQueryDO projectQuery);

    /**
     *
     * @param projectQuery
     * @return
     */
    List<Map<String, Object>> queryNsfc3(@Param("query") ProjectQueryDO projectQuery);

    /**
     *
     * @param projectQuery
     * @return
     */
    List<Map<String, Object>> queryNsfc4(@Param("query") ProjectQueryDO projectQuery);
}