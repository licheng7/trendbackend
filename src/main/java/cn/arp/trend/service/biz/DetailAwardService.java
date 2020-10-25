package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.DetailAwardDetailQueryDO;
import cn.arp.trend.data.model.DO.DetailAwardDistributionQueryDO;
import cn.arp.trend.data.model.DO.DetailAwardTrendQueryDO;
import cn.arp.trend.data.model.DTO.DetailAwardDetailInfoDTO;
import cn.arp.trend.data.model.DTO.DetailAwardDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.DetailAwardTrendInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/25
 * Time:下午12:15
 **/
public interface DetailAwardService {

    DetailAwardTrendInfoDTO trendQuery(DetailAwardTrendQueryDO query);

    DetailAwardDetailInfoDTO detailQuery(DetailAwardDetailQueryDO query);

    DetailAwardDistributionInfoDTO distributionQuery(DetailAwardDistributionQueryDO query);
}
