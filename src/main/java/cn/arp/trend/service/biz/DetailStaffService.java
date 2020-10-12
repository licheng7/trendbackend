package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.AgeDistributionQueryDO;
import cn.arp.trend.data.model.DTO.AgeDistributionInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:03
 **/
public interface DetailStaffService {

    AgeDistributionInfoDTO ageDistributionQuery(AgeDistributionQueryDO query);
}
