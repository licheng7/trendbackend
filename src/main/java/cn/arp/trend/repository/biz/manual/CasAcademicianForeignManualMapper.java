package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.ForeignQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CasAcademicianForeignManualMapper {

    /**
     *
     * @param foreignQuery
     * @return
     */
    List<Map<String, Object>> queryForeign(@Param("request") ForeignQueryDO foreignQuery);

    /**
     *
     * @return
     */
    List<String> queryAcademicianNew1();

    /**
     *
     * @param academicianQuery
     * @return
     */
    List<String> queryAcademicianNew3(@Param("academicianQuery") AcademicianQueryDO
                                              academicianQuery);
}