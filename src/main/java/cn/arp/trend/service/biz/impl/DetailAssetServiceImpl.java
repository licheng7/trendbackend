package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.OverviewQueryDO;
import cn.arp.trend.data.model.DTO.OverviewInfoDTO;
import cn.arp.trend.repository.biz.manual.StatArpFinAssetManualMapper;
import cn.arp.trend.service.biz.DetailAssetService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/19
 * Time:上午12:11
 **/
@Service
public class DetailAssetServiceImpl implements DetailAssetService {

    @Resource
    private StatArpFinAssetManualMapper statArpFinAssetManualMapper;

    @Override
    public OverviewInfoDTO overviewQuery(OverviewQueryDO query) {

        List<Map<String, Object>> queryResult1
                = statArpFinAssetManualMapper.queryOverview1(query);

        List<Map<String, Object>> queryResult2
                = statArpFinAssetManualMapper.queryOverview2(query);

        Map<String, Object> newRst = null;

        if(queryResult1.get(0) != null) {
            newRst = queryResult1.get(0);
        }

        List<Map<String, Object>> incomeDistributionAry = Lists.newArrayList();

        queryResult2.stream().forEach(map -> {
            Map<String, Object> value = Maps.newHashMap();
            value.put("field", map.get("research_field"));
            value.put("income", map.get("sr"));
            incomeDistributionAry.add(value);
        });

        OverviewInfoDTO overviewInfo = new OverviewInfoDTO();
        overviewInfo.setAsset(newRst == null ? 0D : ((Number) newRst.get("zzc")).doubleValue());
        overviewInfo.setIncome(newRst == null ? 0D : ((Number) newRst.get("sr")).doubleValue());
        overviewInfo.setOutcome(newRst == null ? 0D : ((Number) newRst.get("zc")).doubleValue());
        overviewInfo.setDeposit(newRst == null ? 0D : ((Number) newRst.get("ye")).doubleValue());
        overviewInfo.setAssetUnit("万元");
        overviewInfo.setIncomeUnit("万元");
        overviewInfo.setOutcomeUnit("万元");
        overviewInfo.setDepositUnit("万元");
        overviewInfo.setIncomeDistribution(incomeDistributionAry);

        return overviewInfo;
    }
}
