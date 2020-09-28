package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
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
}