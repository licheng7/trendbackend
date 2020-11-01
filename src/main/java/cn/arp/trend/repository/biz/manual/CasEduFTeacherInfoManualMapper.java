package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AllSupervisorQueryDO;
import cn.arp.trend.data.model.DO.AreaEduQueryDO;
import cn.arp.trend.data.model.DO.DoctoralSupervisorQueryDO;
import cn.arp.trend.data.model.DO.MasterSupervisorQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CasEduFTeacherInfoManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> distributionAge4DoctoralSupervisor(@Param("query") DoctoralSupervisorQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> distributionField4DoctoralSupervisor(@Param("query") DoctoralSupervisorQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> distributionAge4MasterSupervisor(@Param("query") MasterSupervisorQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> distributionField4MasterSupervisor(@Param("query") MasterSupervisorQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> distributionAge4AllSupervisor(@Param("query") AllSupervisorQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> distributionField4AllSupervisor(@Param("query") AllSupervisorQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4D5 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEdu4M5 (@Param("query") AreaEduQueryDO query);
}