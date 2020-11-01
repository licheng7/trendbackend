package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AreaEduQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RefEduEnrollmentManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEduStudent1 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEduStudent2 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEduStudent3 (@Param("query") AreaEduQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryEduStudent4 (@Param("query") AreaEduQueryDO query);
}