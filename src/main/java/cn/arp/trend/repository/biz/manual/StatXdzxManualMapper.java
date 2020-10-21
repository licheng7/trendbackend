package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.entity.biz.CompareProjectObj;
import cn.arp.trend.entity.biz.StatXdzx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatXdzxManualMapper {

    /**
     * 各所先导项目
     * @param startYear
     * @param endYear
     * @return
     */
    List<StatXdzx> queryByNf(@Param("startYear") String startYear,
                             @Param("endYear") String endYear);

    /**
     *
     * @param projectQuery
     * @return
     */
    List<CompareProjectObj> queryProject(@Param("query") ProjectQueryDO projectQuery);
}