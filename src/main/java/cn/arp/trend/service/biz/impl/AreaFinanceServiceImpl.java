package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AreaFinanceQueryDO;
import cn.arp.trend.data.model.DTO.AreaFinanceInfoDTO;
import cn.arp.trend.data.model.DTO.AreaFinanceOverviewInfoDTO;
import cn.arp.trend.repository.biz.manual.StatArpFinAssetManualMapper;
import cn.arp.trend.repository.biz.manual.StatArpFinIncomeManualMapper;
import cn.arp.trend.repository.biz.manual.StatArpStaffDutyManualMapper;
import cn.arp.trend.service.biz.AreaFinanceService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/30
 * Time:下午9:27
 **/
@Service
public class AreaFinanceServiceImpl extends AbstructServiceHelper implements AreaFinanceService {

    @Resource
    private StatArpFinAssetManualMapper statArpFinAssetManualMapper;

    @Resource
    private StatArpFinIncomeManualMapper statArpFinIncomeManualMapper;

    @Resource
    private StatArpStaffDutyManualMapper statArpStaffDutyManualMapper;

    @Override
    public AreaFinanceOverviewInfoDTO overviewQuery(AreaFinanceQueryDO query) {

        List<Map<String, Object>> queryResult
                = statArpFinAssetManualMapper.queryFinanceOverview(query);

        List<Map<String, Object>> queryResult2
                = statArpFinAssetManualMapper.queryFinanceOverview2(query);

        List<Map<String, Object>> queryResult3
                = statArpFinAssetManualMapper.queryFinanceOverview3(query);

        return new AreaFinanceOverviewInfoDTO(Lists.newArrayList(queryResult, queryResult3,
                queryResult2));
    }

    @Override
    public AreaFinanceInfoDTO incomeQuery(AreaFinanceQueryDO query) {

        List<Map<String, Object>> queryResult
                = statArpFinIncomeManualMapper.queryAreaFinanceIncome(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, List<Map<String, Object>>> tempBizMap = Maps.newHashMap();
        queryResult.stream().forEach(map -> {
            String nf = (String) map.get("nf");
            if(tempBizMap.containsKey(nf)) {
                tempBizMap.get(nf).add(map);
            } else {
                tempBizMap.put(nf, Lists.newArrayList(map));
            }
        });

        List<Double> czbzAry = Lists.newArrayList();
        List<Double> sysrAry = Lists.newArrayList();
        List<Double> jysrAry = Lists.newArrayList();
        List<Double> qtsrAry = Lists.newArrayList();

        yearList.stream().forEach(year -> {
            if(tempBizMap.containsKey(year)) {
                List<Map<String, Object>> bizDataByYear = tempBizMap.get(year);
                double czbz = 0D;
                double sysr = 0D;
                double jysr = 0D;
                double qtsr = 0D;
                for(Map<String, Object> map : bizDataByYear) {
                    czbz += (double) map.get("czbz");
                    sysr += (double) map.get("sysr");
                    jysr += (double) map.get("jysr");
                    qtsr += (double) map.get("qtsr");
                }
                czbzAry.add(doubleFormat(czbz));
                sysrAry.add(doubleFormat(sysr));
                jysrAry.add(doubleFormat(jysr));
                qtsrAry.add(doubleFormat(qtsr));
            } else {
                czbzAry.add(null);
                sysrAry.add(null);
                jysrAry.add(null);
                qtsrAry.add(null);
            }
        });

        Map<String, Object> czbzMap = Maps.newHashMap();
        czbzMap.put("name", "财政补助收入");
        czbzMap.put("value", czbzAry);

        Map<String, Object> sysrMap = Maps.newHashMap();
        sysrMap.put("name", "事业收入");
        sysrMap.put("value", sysrAry);

        Map<String, Object> jysrMap = Maps.newHashMap();
        jysrMap.put("name", "经营收入");
        jysrMap.put("value", jysrAry);

        Map<String, Object> qtsrMap = Maps.newHashMap();
        qtsrMap.put("name", "其他收入");
        qtsrMap.put("value", qtsrAry);

        List<Map<String, Object>> dataAry = Lists.newArrayList(
                czbzMap, sysrMap, jysrMap, qtsrMap);

        return new AreaFinanceInfoDTO(dataAry, yearList);
    }

    @Override
    public AreaFinanceInfoDTO outcomeQuery(AreaFinanceQueryDO query) {

        List<Map<String, Object>> queryResult
                = statArpFinIncomeManualMapper.queryAreaFinanceOutcome(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, List<Map<String, Object>>> tempBizMap = Maps.newHashMap();
        queryResult.stream().forEach(map -> {
            String nf = (String) map.get("nf");
            if(tempBizMap.containsKey(nf)) {
                tempBizMap.get(nf).add(map);
            } else {
                tempBizMap.put(nf, Lists.newArrayList(map));
            }
        });

        List<Double> ryzcAry = Lists.newArrayList();
        List<Double> gyzcAry = Lists.newArrayList();
        List<Double> jyzcAry = Lists.newArrayList();

        yearList.stream().forEach(year -> {
            if(tempBizMap.containsKey(year)) {
                List<Map<String, Object>> bizDataByYear = tempBizMap.get(year);
                double ryzc = 0D;
                double gyzc = 0D;
                double jyzc = 0D;
                for(Map<String, Object> map : bizDataByYear) {
                    ryzc += (double) map.get("ryzc");
                    gyzc += (double) map.get("gyzc");
                    jyzc += (double) map.get("jyzc");
                }
                ryzcAry.add(doubleFormat(ryzc));
                gyzcAry.add(doubleFormat(gyzc));
                jyzcAry.add(doubleFormat(jyzc));
            } else {
                ryzcAry.add(null);
                gyzcAry.add(null);
                jyzcAry.add(null);
            }
        });

        Map<String, Object> ryzcMap = Maps.newHashMap();
        ryzcMap.put("name", "人员支出");
        ryzcMap.put("value", ryzcAry);

        Map<String, Object> gyzcMap = Maps.newHashMap();
        gyzcMap.put("name", "公用支出");
        gyzcMap.put("value", gyzcAry);

        Map<String, Object> jyzcMap = Maps.newHashMap();
        jyzcMap.put("name", "经营支出");
        jyzcMap.put("value", jyzcAry);

        List<Map<String, Object>> dataAry = Lists.newArrayList(
                ryzcMap, gyzcMap, jyzcMap);

        return new AreaFinanceInfoDTO(dataAry, yearList);
    }

    @Override
    public AreaFinanceInfoDTO rankQuery(AreaFinanceQueryDO query) {

        List<Map<String, Object>> queryResult
                = statArpStaffDutyManualMapper.queryFinanceRank(query);

        return new AreaFinanceInfoDTO(null, null, queryResult);
    }

}
