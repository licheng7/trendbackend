package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.AreaHrQueryDO;
import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.entity.biz.CasAcademicianChina;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CasAcademicianChinaManualMapper {

    /**
     *
     * @param academicianQuery
     * @return
     */
    List<String> queryInstitutionsZKY(@Param("academicianQuery") AcademicianQueryDO
                                              academicianQuery);

    /**
     *
     * @param academicianQuery
     * @return
     */
    List<String> queryFieldsZKY(@Param("academicianQuery") AcademicianQueryDO academicianQuery);

    /**
     * 各所中科院院士
     * @param endYear
     * @return
     */
    List<CasAcademicianChina> queryByDxnf(@Param("endYear") String endYear);

    /**
     *
     * @return
     */
    List<Map<String, Object>> queryCompareAll();

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryCompareUnit(@Param("query") DACompareQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryHrAcadCasTrend(@Param("query") AreaHrQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryHrAcadCasDist1(@Param("query") AreaHrQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryHrAcadCasDist2(@Param("query") AreaHrQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryHrAcadCasDist3(@Param("query") AreaHrQueryDO query);
}