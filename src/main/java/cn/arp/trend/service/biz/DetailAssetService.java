package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.AssetDetailQueryDO;
import cn.arp.trend.data.model.DO.AssetIncomeQueryDO;
import cn.arp.trend.data.model.DO.ExecutionTrendQueryDO;
import cn.arp.trend.data.model.DO.OverviewQueryDO;
import cn.arp.trend.data.model.DTO.AssetDetailInfoDTO;
import cn.arp.trend.data.model.DTO.AssetIncomeInfoDTO;
import cn.arp.trend.data.model.DTO.ExecutionTrendInfoDTO;
import cn.arp.trend.data.model.DTO.OverviewInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/19
 * Time:上午12:11
 **/
public interface DetailAssetService {

    OverviewInfoDTO overviewQuery(OverviewQueryDO query);

    AssetDetailInfoDTO detailQuery(AssetDetailQueryDO query);

    AssetIncomeInfoDTO incomeQuery(AssetIncomeQueryDO query);

    ExecutionTrendInfoDTO executionTrendQuery(ExecutionTrendQueryDO query);
}
