package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.DistributionAffiliationQueryDO;
import cn.arp.trend.data.model.DO.DistributionFieldQueryDO;
import cn.arp.trend.data.model.DO.YoungEliteQueryDO;
import cn.arp.trend.data.model.DTO.DistributionAffiliationInfoDTO;
import cn.arp.trend.data.model.DTO.DistributionFieldInfoDTO;
import cn.arp.trend.data.model.DTO.YoungEliteTrendInfoDTO;
import cn.arp.trend.data.model.DTO.YoungProjectInfoDTO;
import cn.arp.trend.repository.biz.manual.StatCasStaffQianzaiyearcountBydanweiManualMapper;
import cn.arp.trend.repository.biz.manual.TempCasStaffForRencaicountManualMapper;
import cn.arp.trend.service.biz.DetailYoungEliteService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/24
 * Time:下午4:20
 **/
@Service
public class DetailYoungEliteServiceImpl implements DetailYoungEliteService {

    @Resource
    private StatCasStaffQianzaiyearcountBydanweiManualMapper qianzaiyearcountBydanweiManualMapper;

    @Resource
    private TempCasStaffForRencaicountManualMapper tempCasStaffForRencaicountManualMapper;

    @Override
    public YoungEliteTrendInfoDTO trendQuery(YoungEliteQueryDO query) {

        List<Map<String, Object>> queryResult =
                qianzaiyearcountBydanweiManualMapper.queryYoungEliteTrend(query);

        List<Object> year = Lists.newArrayList();
        List<Object> youngElite = Lists.newArrayList();
        List<Object> staffNumber = Lists.newArrayList();
        List<Object> proportion = Lists.newArrayList();

        queryResult.stream().forEach(map -> {
            year.add(map.get("year"));
            youngElite.add(map.get("young_elite"));
            staffNumber.add(map.get("staff_number"));
            proportion.add(map.get("proportion"));
        });

        return new YoungEliteTrendInfoDTO(year, youngElite, staffNumber, proportion);
    }

    @Override
    public DistributionFieldInfoDTO distributionFieldQuery(DistributionFieldQueryDO query) {

        List<Map<String, Object>> queryResult =
                qianzaiyearcountBydanweiManualMapper.queryDistributionField(query);

        List<Object> fields = Lists.newArrayList();
        List<Object> youngElite = Lists.newArrayList();
        List<Object> staffNumber = Lists.newArrayList();
        List<Object> proportion = Lists.newArrayList();

        queryResult.stream().forEach(map -> {
            fields.add(map.get("field"));
            youngElite.add(map.get("young_elite"));
            staffNumber.add(map.get("staff_number"));
            proportion.add(map.get("proportion"));
        });

        return new DistributionFieldInfoDTO(fields, youngElite, staffNumber, proportion);
    }

    @Override
    public DistributionAffiliationInfoDTO distributionAffiliationQuery(DistributionAffiliationQueryDO query) {

        List<Map<String, Object>> queryResult
                = qianzaiyearcountBydanweiManualMapper.queryDistributionAffiliation(query);

        List<Map<String, Object>> detail = Lists.newArrayList();

        queryResult.stream().forEach(map -> {
            Map<String, Object> detailValue = Maps.newHashMap();
            detailValue.put("id", map.get("id"));
            detailValue.put("affiliation", map.get("year"));
            detailValue.put("young_elite", map.get("young_elite"));
            detailValue.put("staff_number", map.get("staff_number"));
            detailValue.put("proportion", map.get("proportion"));
            detail.add(detailValue);
        });

        return new DistributionAffiliationInfoDTO(detail);
    }

    @Override
    public YoungProjectInfoDTO projectQuery(YoungEliteQueryDO query) {

        List<Map<String, Object>> queryResult1
                = tempCasStaffForRencaicountManualMapper.queryProject1(query);

        List<Map<String, Object>> queryResult2
                = tempCasStaffForRencaicountManualMapper.queryProject2(query);

        List<Map<String, Object>> queryResult3
                = tempCasStaffForRencaicountManualMapper.queryProject3(query);

        List<Map<String, Object>> trend = Lists.newArrayList();
        Integer numberNsfc = 0;
        Float fundsNsfc = 0F;
        Integer numberKeyplan = 0;
        Float fundsKeyplanTotal = 0F;
        Float fundsKeyplanZycz = 0F;
        Float fundsKeyplanSelf = 0F;
        Integer countNsfc = 0;
        Integer countKeyplan = 0;

        for(Map<String, Object> map : queryResult1) {
            Map<String, Object> trendValue = Maps.newHashMap();
            trendValue.put("year", map.get("year"));
            trendValue.put("number_nsfc", map.get("nsfc_number"));
            trendValue.put("funds_nsfc", map.get("nsfc_funds"));
            trendValue.put("number_keyplan", map.get("keyplan_number"));
            trendValue.put("funds_keyplan_total", map.get("keyplan_funds"));
            trendValue.put("funds_keyplan_zycz", map.get("keyplan_funds_zycz"));
            trendValue.put("funds_keyplan_self", map.get("keyplan_funds_self"));
            trend.add(trendValue);

            numberNsfc += map.get("nsfc_number") == null ? 0 : ((Number) map.get("nsfc_number"))
                    .intValue();
            fundsNsfc += map.get("nsfc_funds") == null ? 0F : ((Number) map.get("nsfc_funds"))
                    .floatValue();
            numberKeyplan += map.get("keyplan_number") == null ? 0 : ((Number) map.get
                    ("keyplan_number")).intValue();
            fundsKeyplanTotal += map.get("keyplan_funds") == null ? 0F : ((Number) map.get
                    ("keyplan_funds")).floatValue();
            fundsKeyplanZycz += map.get("keyplan_funds_zycz") == null ? 0F : ((Number) map.get
                    ("keyplan_funds_zycz")).floatValue();
            fundsKeyplanSelf += map.get("keyplan_funds_self") == null ? 0F : ((Number) map.get
                    ("keyplan_funds_self")).floatValue();
            countNsfc += map.get("nsfc_rs") == null ? 0 : ((Number) map.get("nsfc_rs")).intValue();
            countKeyplan += map.get("keyplan_rs") == null ? 0 : ((Number) map.get("keyplan_rs"))
                    .intValue();
        }

        Integer number = numberNsfc + numberKeyplan;
        Float funds = fundsNsfc + fundsKeyplanTotal;
        Integer count = countNsfc + countKeyplan;
        Float avgNumber = null;
        Float avgFunds = null;
        if(count > 0) {
            avgNumber = new BigDecimal(number).divide(new BigDecimal(count), 2, BigDecimal
                    .ROUND_HALF_UP).floatValue();
            avgFunds = new BigDecimal(funds).divide(new BigDecimal(count), 2, BigDecimal
                    .ROUND_HALF_UP).floatValue();
        }

        int rs2 = queryResult2.get(0).get("rs") == null ? 0 : ((Number) queryResult2.get(0).get
                ("rs")).intValue();
        Float avgNumberNsfc = null;
        if(rs2 > 0) {
            avgNumberNsfc = new BigDecimal(numberNsfc).divide(new BigDecimal(rs2), 2,
                    BigDecimal.ROUND_HALF_UP).floatValue();
        }

        int rs3 = queryResult3.get(0).get("rs") == null ? 0 : ((Number) queryResult3.get(0).get
                ("rs")).intValue();
        Float avgNumberKeyplan = null;
        if(rs3 > 0) {
            avgNumberKeyplan = new BigDecimal(numberKeyplan).divide(new BigDecimal(rs3), 2,
                    BigDecimal.ROUND_HALF_UP).floatValue();
        }

        YoungProjectInfoDTO info = new YoungProjectInfoDTO();
        info.setTrend(trend);
        info.setNumber(number);
        info.setNumberNsfc(numberNsfc);
        info.setNumberKeyplan(numberKeyplan);
        info.setFunds(funds);
        info.setFundsNsfc(fundsNsfc);
        info.setFundsKeyplanTotal(fundsKeyplanTotal);
        info.setFundsKeyplanZycz(fundsKeyplanZycz);
        info.setFundsKeyplanSelf(fundsKeyplanSelf);
        info.setCount(count);
        info.setCountNsfc(countNsfc);
        info.setCountKeyplan(countKeyplan);
        info.setAvgNumber(avgNumber);
        info.setAvgNumberNsfc(avgNumberNsfc);
        info.setAvgNumberKeyplan(avgNumberKeyplan);
        info.setAvgFunds(avgFunds);
        info.setAvgFundsNsfc(null);
        info.setAvgFundsKeyplan(null);
        info.setUpdateTime("2019年10月");

        return info;
    }
}
