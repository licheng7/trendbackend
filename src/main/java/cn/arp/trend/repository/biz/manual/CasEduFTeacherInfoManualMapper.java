package cn.arp.trend.repository.biz.manual;

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
}