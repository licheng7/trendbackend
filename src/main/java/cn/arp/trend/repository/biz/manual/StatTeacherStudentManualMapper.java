package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.*;
import cn.arp.trend.entity.biz.StatTeacherStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatTeacherStudentManualMapper {

    /**
     * 导师信息
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatTeacherStudent> queryByNf(@Param("startYear") String startYear,
                                       @Param("endYear") String endYear);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> trend4DoctoralSupervisor(
            @Param("query") TrendDoctoralSupervisorQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> trend4MasterSupervisor(
            @Param("query") TrendMasterSupervisorQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> trend4All1(
            @Param("query") TrendAllQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> trend4All2(
            @Param("query") TrendAllQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> mentorDetail(
            @Param("query") MentorDetailQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4D1 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4D2 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4D3 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4D4 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4M1 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4M2 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4M3 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4M4 (@Param("query") AreaEduQueryDO query);
}