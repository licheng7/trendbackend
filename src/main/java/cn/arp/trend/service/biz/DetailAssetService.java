package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.*;
import cn.arp.trend.data.model.DTO.*;

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
