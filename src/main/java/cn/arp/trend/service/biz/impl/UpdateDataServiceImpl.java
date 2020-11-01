package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.UpdateDataQueryDO;
import cn.arp.trend.entity.biz.UpdateFrequency;
import cn.arp.trend.repository.biz.manual.UpdateFrequencyManualMapper;
import cn.arp.trend.service.biz.UpdateDataService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午3:38
 **/
@Service
public class UpdateDataServiceImpl implements UpdateDataService {

    @Resource
    private UpdateFrequencyManualMapper updateFrequencyManualMapper;

    /**
     * updateData.js对应的/
     * @param query
     * @return
     */
    @Override
    public Map<String, String> queryAll(UpdateDataQueryDO query) {
        List<UpdateFrequency> queryResult
                = updateFrequencyManualMapper.queryAll(query);
        UpdateFrequency updateFrequency = queryResult.get(0);
        Map<String, String> result = Maps.newHashMap();
        result.put("unit", updateFrequency.getDataValue());
        result.put("upData", updateFrequency.getFrequency());
        return result;
    }
}
