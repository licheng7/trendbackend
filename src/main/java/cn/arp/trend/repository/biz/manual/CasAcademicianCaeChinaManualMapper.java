package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
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
}