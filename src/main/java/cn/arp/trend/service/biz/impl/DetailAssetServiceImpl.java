package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AssetDetailQueryDO;
import cn.arp.trend.data.model.DO.AssetIncomeQueryDO;
import cn.arp.trend.data.model.DO.ExecutionTrendQueryDO;
import cn.arp.trend.data.model.DO.OverviewQueryDO;
import cn.arp.trend.data.model.DTO.AssetDetailInfoDTO;
import cn.arp.trend.data.model.DTO.AssetIncomeInfoDTO;
import cn.arp.trend.data.model.DTO.ExecutionTrendInfoDTO;
import cn.arp.trend.data.model.DTO.OverviewInfoDTO;
import cn.arp.trend.repository.biz.manual.CasInstitutionIncomeManualMapper;
import cn.arp.trend.repository.biz.manual.StatArpFinAssetManualMapper;
import cn.arp.trend.repository.biz.manual.StatArpFinBudgetExecuteManualMapper;
import cn.arp.trend.repository.biz.manual.StatArpFinIncomeManualMapper;
import cn.arp.trend.service.biz.DetailAssetService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private StatArpFinIncomeManualMapper statArpFinIncomeManualMapper;

    @Resource
    private CasInstitutionIncomeManualMapper casInstitutionIncomeManualMapper;

    @Resource
    private StatArpFinBudgetExecuteManualMapper statArpFinBudgetExecuteManualMapper;

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

    @Override
    public AssetDetailInfoDTO detailQuery(AssetDetailQueryDO query) {

        int yearInt = Integer.parseInt(query.getYear());

        List<Map<String, Object>> detailOriginal;
        List<Map<String, Object>> fieldDis;

        if(yearInt >= 2019) {
            detailOriginal = statArpFinIncomeManualMapper.queryAssetDetail1(query);
            fieldDis = statArpFinIncomeManualMapper.queryAssetDetail1(query);
        } else {
            detailOriginal = statArpFinIncomeManualMapper.queryAssetDetail3(query);
            fieldDis = statArpFinIncomeManualMapper.queryAssetDetail4(query);
        }

        List<Map<String, Object>> detail = Lists.newArrayList();

        detailOriginal.stream().forEach(map -> {
            double tmpIncomeAvg =
                    (new BigDecimal(((Number) map.get("income")).doubleValue())
                            .divide(new BigDecimal(((Number) map.get("rs")).doubleValue()), 2,
                                    BigDecimal.ROUND_HALF_UP)).doubleValue();
            double tmpOutcomeAvg =
                    (new BigDecimal(((Number) map.get("outcome")).doubleValue())
                            .divide(new BigDecimal(((Number) map.get("rs")).doubleValue()), 2,
                                    BigDecimal.ROUND_HALF_UP)).doubleValue();

            Map detailValue = Maps.newHashMap();
            detailValue.put("affiliation_field", map.get("research_field"));
            detailValue.put("affiliation_id", map.get("jgbh"));
            detailValue.put("affiliation_name", map.get("jgmc"));
            detailValue.put("income", map.get("income"));

            detailValue.put("income_avg", tmpIncomeAvg);
            detailValue.put("income_unit", "万元");
            detailValue.put("outcome", map.get("outcome"));
            detailValue.put("outcome_avg", tmpOutcomeAvg);
            detailValue.put("outcome_unit", "万元");
            detailValue.put("staff_number", map.get("rs"));
            detail.add(detailValue);
        });

        List<Map<String, Object>> incomeDis = Lists.newArrayList();
        List<Map<String, Object>> outcomeDis = Lists.newArrayList();

        fieldDis.stream().forEach(map -> {
            Map incomeDisValue = Maps.newHashMap();
            incomeDisValue.put("field", map.get("research_field"));
            incomeDisValue.put("income", map.get("income"));
            incomeDis.add(incomeDisValue);

            Map outcomeDisValue = Maps.newHashMap();
            outcomeDisValue.put("field", map.get("research_field"));
            outcomeDisValue.put("outcome", map.get("outcome"));
            outcomeDis.add(outcomeDisValue);
        });

        return new AssetDetailInfoDTO(
                "2019年10月",
                detail,
                incomeDis,
                outcomeDis,
                Lists.newArrayList(detailOriginal, fieldDis)
        );
    }

    @Override
    public AssetIncomeInfoDTO incomeQuery(AssetIncomeQueryDO query) {

        List<Map<String, Object>> queryResult
                = casInstitutionIncomeManualMapper.queryAssetIncome(query);

        List<Map<String, Object>> detail = Lists.newArrayList();
        queryResult.stream().forEach(map -> {
            Map<String, Object> detailValue = Maps.newHashMap();
            detailValue.put("year", map.get("stat_year"));
            detailValue.put("czbz", map.get("czbtsr"));
            detailValue.put("czbz_unit", "万元");
            detailValue.put("sy", map.get("sysr"));
            detailValue.put("sy_unit", "万元");
            detailValue.put("ky", map.get("kysr"));
            detailValue.put("ky_unit", "万元");
            detailValue.put("brzk", map.get("zsrbrzk"));
            detailValue.put("brzk_unit", "万元");
            detailValue.put("other", map.get("qtsr"));
            detailValue.put("other_unit", "万元");
            detail.add(detailValue);
        });
        List<Map<String, Object>> newDetail = detail.stream().sorted((map1, map2) -> Integer.parseInt(
                (String) map1.get("year")) - Integer.parseInt((String) map2.get("year")))
                .collect(Collectors.toList());

        List<String> legend = Lists.newArrayList("财政补助收入", "事业收入", "科研收入", "拨入专款", "其它收入");
        List<String> legendAttr = Lists.newArrayList("czbz", "sy", "ky", "brzk", "other");

        return new AssetIncomeInfoDTO(
                "2019年10月",
                newDetail,
                legend,
                legendAttr
        );
    }

    @Override
    public ExecutionTrendInfoDTO executionTrendQuery(ExecutionTrendQueryDO query) {

        List<Map<String, Object>> queryResult
                = statArpFinBudgetExecuteManualMapper.queryExecutionTrend(query);

        List<Map<String, Object>> detail = Lists.newArrayList();
        queryResult.stream().forEach(map -> {
            Map<String, Object> detailValue = Maps.newHashMap();
            detailValue.put("execution", map.get("wcl"));
            detailValue.put("income", map.get("czbk"));
            detailValue.put("income_unit", "万元");
            detailValue.put("month", map.get("yf"));
            detailValue.put("outcome", map.get("zcje"));
            detailValue.put("outcome_unit", "万元");
            detail.add(detailValue);
        });

        return new ExecutionTrendInfoDTO(detail, queryResult);
    }
}
