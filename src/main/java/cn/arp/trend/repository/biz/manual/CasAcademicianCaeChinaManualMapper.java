package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.AreaHrQueryDO;
import cn.arp.trend.entity.biz.CasAcademicianCaeChina;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CasAcademicianCaeChinaManualMapper {

    /**
     *
     * @param academicianQuery
     * @return
     */
    List<String> queryInstitutionsGCY(@Param("academicianQuery") AcademicianQueryDO academicianQuery);

    /**
     *
     * @param academicianQuery
     * @return
     */
    List<String> queryFieldsGCY(@Param("academicianQuery") AcademicianQueryDO academicianQuery);

    /**
     * 各所工程院院士
     * @param endYear
     * @return
     */
    List<CasAcademicianCaeChina> queryByDxnf(@Param("endYear") String endYear);

    /**
     *
     * @return
     */
    List<String> queryAcademicianNew2();

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryHrAcadCaeTrend(@Param("query") AreaHrQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryHrAcadCaeDist1(@Param("query") AreaHrQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryHrAcadCaeDist2(@Param("query") AreaHrQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryHrAcadCaeDist3(@Param("query") AreaHrQueryDO query);
}