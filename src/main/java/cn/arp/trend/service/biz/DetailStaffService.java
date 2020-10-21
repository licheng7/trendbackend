package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.*;
import cn.arp.trend.data.model.DTO.*;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:03
 **/
public interface DetailStaffService {

    AgeDistributionInfoDTO ageDistributionQuery(AgeDistributionQueryDO query);

    ChildLevelDistributionInfoDTO childLevelDistributionQuery(ChildLevelDistributionQueryDO query);

    IncreaseTrendInfoDTO increaseTrendQuery(IncreaseTrendQueryDO query);

    PersonTypeDistributionInfoDTO personTypeDistributionQuery(PersonTypeDistributionQueryDO query);

    PostDistributionInfoDTO postDistributionQuery(PostDistributionQueryDO query);

    PositionDistributionInfoDTO positionDistributionQuery(PositionDistributionQueryDO query);

    DrRankInfoDTO drRankQuery(DrRankQueryDO query);

    PostAnalyzeInfoDTO postAnalyzeQuery(PostAnalyzeQueryDO query);
}
