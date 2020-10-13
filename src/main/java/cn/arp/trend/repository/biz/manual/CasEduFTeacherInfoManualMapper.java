package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.DoctoralSupervisorQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CasEduFTeacherInfoManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> distributionAge(@Param("query")DoctoralSupervisorQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> distributionField(@Param("query")DoctoralSupervisorQueryDO query);
}