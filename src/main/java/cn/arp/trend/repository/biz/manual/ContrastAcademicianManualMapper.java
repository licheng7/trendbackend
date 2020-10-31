package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.entity.biz.CasAcademicianChina;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ContrastAcademicianManualMapper {

    Object contrastByField(
            @Param("userId") String userId,
            @Param("startYear") String startYear,
            @Param("endYear") String endYear,
            @Param("fieldIdsStr") String fieldIdsStr
    );

    Object contrastByUnit(
            @Param("userId") String userId,
            @Param("startYear") String startYear,
            @Param("endYear") String endYear,
            @Param("fieldIdsStr") String fieldIdsStr
    );
}