package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.OverviewQueryDO;
import cn.arp.trend.data.model.DTO.OverviewInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/19
 * Time:上午12:11
 **/
public interface DetailAssetService {

    OverviewInfoDTO overviewQuery(OverviewQueryDO query);
}
