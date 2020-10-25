package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.DistributionAffiliationQueryDO;
import cn.arp.trend.data.model.DO.DistributionFieldQueryDO;
import cn.arp.trend.data.model.DO.YoungEliteQueryDO;
import cn.arp.trend.data.model.DTO.DistributionAffiliationInfoDTO;
import cn.arp.trend.data.model.DTO.DistributionFieldInfoDTO;
import cn.arp.trend.data.model.DTO.YoungEliteTrendInfoDTO;
import cn.arp.trend.data.model.DTO.YoungProjectInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/24
 * Time:下午4:20
 **/
public interface DetailYoungEliteService {

    YoungEliteTrendInfoDTO trendQuery(YoungEliteQueryDO query);

    DistributionFieldInfoDTO distributionFieldQuery(DistributionFieldQueryDO query);

    DistributionAffiliationInfoDTO distributionAffiliationQuery(DistributionAffiliationQueryDO query);

    YoungProjectInfoDTO projectQuery(YoungEliteQueryDO query);
}
