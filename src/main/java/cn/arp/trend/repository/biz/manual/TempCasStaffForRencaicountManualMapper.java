package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.YoungEliteQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TempCasStaffForRencaicountManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryProject1(@Param("query") YoungEliteQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryProject2(@Param("query") YoungEliteQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryProject3(@Param("query") YoungEliteQueryDO query);
}