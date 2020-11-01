package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.entity.biz.CasAcademicianChina;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ContrastAcademicianManualMapper {

    List<HashMap<String, Object>> contrastByField(
            Map<String, Object> params
    );

    Object contrastByUnit(
            @Param("userId") String userId,
            @Param("startYear") Integer startYear,
            @Param("endYear") Integer endYear,
            @Param("fieldIdsStr") String fieldIdsStr
    );
}