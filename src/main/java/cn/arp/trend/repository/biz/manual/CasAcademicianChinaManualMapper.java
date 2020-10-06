package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.entity.biz.CasAcademicianChina;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}