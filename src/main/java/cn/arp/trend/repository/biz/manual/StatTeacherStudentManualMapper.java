package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.MentorDetailQueryDO;
import cn.arp.trend.data.model.DO.TrendAllQueryDO;
import cn.arp.trend.data.model.DO.TrendDoctoralSupervisorQueryDO;
import cn.arp.trend.data.model.DO.TrendMasterSupervisorQueryDO;
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
}