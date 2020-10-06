package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.entity.biz.CasAcademicianCaeChina;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}