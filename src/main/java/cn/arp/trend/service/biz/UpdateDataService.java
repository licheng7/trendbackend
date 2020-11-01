package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.UpdateDataQueryDO;

import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午3:37
 **/
public interface UpdateDataService {

    /**
     * updateData.js对应的/
     * @param query
     * @return
     */
    Map<String, String> queryAll(UpdateDataQueryDO query);
}
