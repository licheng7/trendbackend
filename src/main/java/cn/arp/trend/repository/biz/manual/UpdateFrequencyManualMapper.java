package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.UpdateDataQueryDO;
import cn.arp.trend.entity.biz.UpdateFrequency;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpdateFrequencyManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<UpdateFrequency> queryAll(@Param("query") UpdateDataQueryDO query);
}