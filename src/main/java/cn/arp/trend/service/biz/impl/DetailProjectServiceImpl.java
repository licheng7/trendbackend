package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.repository.biz.manual.StatArpStaffDutyManualMapper;
import cn.arp.trend.repository.biz.manual.StatMostProjectManualMapper;
import cn.arp.trend.repository.biz.manual.StatNsfcProjectManualMapper;
import cn.arp.trend.repository.biz.manual.StatXdzxManualMapper;
import cn.arp.trend.service.biz.DetailProjectService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/20
 * Time:上午11:23
 **/
@Service
public class DetailProjectServiceImpl extends AbstructServiceHelper implements DetailProjectService {

    @Resource
    private StatNsfcProjectManualMapper statNsfcProjectManualMapper;

    @Resource
    private StatMostProjectManualMapper statMostProjectManualMapper;

    @Resource
    private StatXdzxManualMapper statXdzxManualMapper;

    @Resource
    private StatArpStaffDutyManualMapper statArpStaffDutyManualMapper;

    @Override
    public DetailProjectNsfcInfoDTO nsfcQuery(ProjectQueryDO query) {

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        List<Map<String, Object>> queryResult1
                = statNsfcProjectManualMapper.queryNsfc1(query);

        List<Map<String, Object>> queryResult2
                = statNsfcProjectManualMapper.queryNsfc2(query);

        List<Map<String, Object>> queryResult3
                = statNsfcProjectManualMapper.queryNsfc3(query);

        List<Map<String, Object>> queryResult4
                = statNsfcProjectManualMapper.queryNsfc4(query);

        Map<String, Object> trendNumberMap = Maps.newHashMap();
        Map<String, Object> trendFundsMap = Maps.newHashMap();
        yearList.stream().forEach(str -> {
            trendNumberMap.put(str, null);
            trendFundsMap.put(str, null);
        });

        queryResult2.stream().forEach(map -> {
            if(map.get("year") != null) {
                String year = (String) map.get("year");
                if (yearList.contains(year)) {
                    trendNumberMap.put(year, map.get("xzxms"));
                }
            }
        });
        queryResult3.stream().forEach(map -> {
            if(map.get("year") != null) {
                String year = (String) map.get("year");
                if (yearList.contains(year)) {
                    trendFundsMap.put(year, map.get("xzzjf"));
                }
            }
        });

        List<String> fieldsList = queryResult1.stream().map(
                map -> (String) map.get("research_field")).distinct().collect(Collectors.toList());
        Map<String, Map<String, Object>> fieldNumberMap = Maps.newHashMap();
        Map<String, Map<String, Object>> fieldFundsMap = Maps.newHashMap();
        this.initBizMap(fieldNumberMap, fieldFundsMap, fieldsList, 0);
        queryResult1.stream().forEach(map -> {
            if(map.get("research_field") != null) {
                String researchField = (String) map.get("research_field");
                if (fieldNumberMap.containsKey(researchField)) {
                    Map<String, Object> fieldNumberValue = fieldNumberMap.get(researchField);
                    fieldNumberValue.put("value", ((Number) fieldNumberValue.get("value")).longValue() +
                            ((Number) map.get("xzxms")).longValue());
                }
                if (fieldFundsMap.containsKey(researchField)) {
                    Map<String, Object> fieldFundsValue = fieldFundsMap.get(researchField);
                    fieldFundsValue.put("value", ((Number) fieldFundsValue.get("value")).doubleValue() +
                            ((Number) map.get("xzxm_zjf")).doubleValue());
                }
            }
        });

        List<String> affiliationsList = queryResult1.stream()
                .map(map -> (String) map.get("jgmc")).distinct().collect(Collectors.toList());
        Map<String, Map<String, Object>> rankNumberMap = Maps.newHashMap();
        Map<String, Map<String, Object>> rankFundsMap = Maps.newHashMap();
        this.initBizMap(rankNumberMap, rankFundsMap, affiliationsList, 0);
        queryResult1.stream().forEach(map -> {
            if(map.get("jgmc") != null) {
                String jgmc = (String) map.get("jgmc");
                if (rankNumberMap.containsKey(jgmc)) {
                    Map<String, Object> rankNumberValue = rankNumberMap.get(jgmc);
                    rankNumberValue.put("value", ((Number) rankNumberValue.get("value")).longValue() +
                            ((Number) map.get("xzxms")).longValue());
                }
                if (rankFundsMap.containsKey(jgmc)) {
                    Map<String, Object> rankFundsValue = rankFundsMap.get(jgmc);
                    rankFundsValue.put("value", ((Number) rankFundsValue.get("value")).doubleValue() +
                            ((Number) map.get("xzxm_zjf")).doubleValue());
                }
            }
        });

        List<Object> nsfcLineChartMoney = Lists.newArrayList();
        List<Object> nsfcLineChartObg = Lists.newArrayList();
        queryResult4.stream().forEach(map -> {
            nsfcLineChartMoney.add(map.get("jf"));
            nsfcLineChartObg.add(map.get("xm"));
        });

        List<Map<String, Object>> fieldNumber = this.dMap2List(fieldNumberMap);
        List<Map<String, Object>> fieldFunds = this.dMap2List(fieldFundsMap);
        List<Map<String, Object>> rankNumber = this.dMap2List(rankNumberMap);
        List<Map<String, Object>> rankFunds = this.dMap2List(rankFundsMap);

        List<Map<String, Object>> sortedRankNumber = rankNumber.stream().sorted(
                Comparator.comparingLong((m) -> (long) m.get("value") * -1)
        ).collect(Collectors.toList());
        List<Map<String, Object>> sortedRankFunds = rankFunds.stream().sorted(
                Comparator.comparingDouble((m) -> (double) m.get("value") * -1)
        ).collect(Collectors.toList());

        DetailProjectNsfcInfoDTO detailProjectNsfcInfo = new DetailProjectNsfcInfoDTO();
        detailProjectNsfcInfo.setYear(yearList);
        detailProjectNsfcInfo.setTrendNumber(this.map2List(yearList, trendNumberMap));
        detailProjectNsfcInfo.setTrendFunds(this.map2List(yearList, trendFundsMap));
        detailProjectNsfcInfo.setFieldNumber(fieldNumber);
        detailProjectNsfcInfo.setFieldFunds(fieldFunds);
        detailProjectNsfcInfo.setRankNumber(sortedRankNumber);
        detailProjectNsfcInfo.setRankFunds(sortedRankFunds);
        detailProjectNsfcInfo.setUpdateTime("2019年10月");
        detailProjectNsfcInfo.setNsfcLineChartMoney(nsfcLineChartMoney);
        detailProjectNsfcInfo.setNsfcLineChartObg(nsfcLineChartObg);

        return detailProjectNsfcInfo;
    }

    @Override
    public DetailProjectKjbInfoDTO kjbQuery(ProjectQueryDO query) {

        List<Map<String, Object>> queryResult1
                = statMostProjectManualMapper.queryKjb1(query);

        List<Map<String, Object>> queryResult2
                = statMostProjectManualMapper.queryKjb2(query);

        List<Map<String, Object>> queryResult3
                = statMostProjectManualMapper.queryKjb3(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Object> trendNumberMap = Maps.newHashMap();
        Map<String, Object> trendFundsMap = Maps.newHashMap();
        yearList.stream().forEach(str -> {
            trendNumberMap.put(str, null);
            trendFundsMap.put(str, null);
        });

        queryResult2.stream().forEach(map -> {
            String year = (String) map.get("year");
            if(yearList.contains(year)) {
                trendNumberMap.put(year, map.get("xzxms"));
            }
        });
        queryResult3.stream().forEach(map -> {
            String year = (String) map.get("year");
            if(yearList.contains(year)) {
                trendFundsMap.put(year, map.get("xzzjf"));
            }
        });

        List<String> fieldsList = queryResult1.stream().map(
                map -> (String) map.get("research_field")).distinct().collect(Collectors.toList());
        Map<String, Map<String, Object>> fieldNumberMap = Maps.newHashMap();
        Map<String, Map<String, Object>> fieldFundsMap = Maps.newHashMap();
        this.initBizMap(fieldNumberMap, fieldFundsMap, fieldsList, 0);
        queryResult1.stream().forEach(map -> {
            String researchField = (String) map.get("research_field");
            if(fieldNumberMap.containsKey(researchField)) {
                Map<String, Object> fieldNumberValue = fieldNumberMap.get(researchField);
                fieldNumberValue.put("value", ((Number) fieldNumberValue.get("value")).longValue() +
                        ((Number) map.get("xzxms")).longValue());
            }
            if(fieldFundsMap.containsKey(researchField)) {
                Map<String, Object> fieldFundsValue = fieldFundsMap.get(researchField);
                fieldFundsValue.put("value", ((Number) fieldFundsValue.get("value")).doubleValue() +
                        ((Number) map.get("xzxm_zjf")).doubleValue());
            }
        });

        List<String> affiliationsList = queryResult1.stream().map(
                map -> (String) map.get("jgmc")).distinct().collect(Collectors.toList());
        Map<String, Map<String, Object>> rankNumberMap = Maps.newHashMap();
        Map<String, Map<String, Object>> rankFundsMap = Maps.newHashMap();
        this.initBizMap(rankNumberMap, rankFundsMap, affiliationsList, 0);
        queryResult1.stream().forEach(map -> {
            String jgmc = (String) map.get("jgmc");
            if(rankNumberMap.containsKey(jgmc)) {
                Map<String, Object> rankNumberValue = rankNumberMap.get(jgmc);
                rankNumberValue.put("value", ((Number) rankNumberValue.get("value")).longValue() +
                        ((Number) map.get("xzxms")).longValue());
            }
            if(rankFundsMap.containsKey(jgmc)) {
                Map<String, Object> rankFundsValue = rankFundsMap.get(jgmc);
                rankFundsValue.put("value", ((Number) rankFundsValue.get("value")).doubleValue() +
                        ((Number) map.get("xzxm_zjf")).doubleValue());
            }
        });

        List<Map<String, Object>> fieldNumber = this.dMap2List(fieldNumberMap);
        List<Map<String, Object>> fieldFunds = this.dMap2List(fieldFundsMap);
        List<Map<String, Object>> rankNumber = this.dMap2List(rankNumberMap);
        List<Map<String, Object>> rankFunds = this.dMap2List(rankFundsMap);

        List<Map<String, Object>> sortedRankNumber = rankNumber.stream().sorted(
                Comparator.comparingLong((m) -> (long) m.get("value") * -1)
        ).collect(Collectors.toList());
        List<Map<String, Object>> sortedRankFunds = rankFunds.stream().sorted(
                Comparator.comparingDouble((m) -> (double) m.get("value") * -1)
        ).collect(Collectors.toList());

        DetailProjectKjbInfoDTO detailProjectKjbInfo = new DetailProjectKjbInfoDTO();
        detailProjectKjbInfo.setYear(yearList);
        detailProjectKjbInfo.setTrendNumber(this.map2List(yearList, trendNumberMap));
        detailProjectKjbInfo.setTrendFunds(this.map2List(yearList, trendFundsMap));
        detailProjectKjbInfo.setFieldNumber(fieldNumber);
        detailProjectKjbInfo.setFieldFunds(fieldFunds);
        detailProjectKjbInfo.setRankNumber(sortedRankNumber);
        detailProjectKjbInfo.setRankFunds(sortedRankFunds);
        detailProjectKjbInfo.setUpdateTime("2019年10月");
        detailProjectKjbInfo.setFundsUnit("万元");

        return detailProjectKjbInfo;
    }

    @Override
    public DetailProjectXdInfoDTO xdQuery(ProjectQueryDO query) {

        List<Map<String, Object>> queryResult1
                = statXdzxManualMapper.queryXd1(query);

        List<Map<String, Object>> queryResult2
                = statXdzxManualMapper.queryXd2(query);

        List<Map<String, Object>> queryResult3
                = statXdzxManualMapper.queryXd3(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Object> trendNumberMap = Maps.newHashMap();
        Map<String, Object> trendFundsMap = Maps.newHashMap();
        yearList.stream().forEach(str -> {
            trendNumberMap.put(str, null);
            trendFundsMap.put(str, null);
        });

        queryResult2.stream().forEach(map -> {
            String year = (String) map.get("year");
            if(yearList.contains(year)) {
                trendNumberMap.put(year, map.get("xzxms"));
            }
        });
        queryResult3.stream().forEach(map -> {
            String year = (String) map.get("year");
            if(yearList.contains(year)) {
                trendFundsMap.put(year, map.get("xzzjf"));
            }
        });

        List<String> fieldsList = queryResult1.stream().map(
                map -> (String) map.get("research_field")).distinct().collect(Collectors.toList());
        Map<String, Map<String, Object>> fieldNumberMap = Maps.newHashMap();
        Map<String, Map<String, Object>> fieldFundsMap = Maps.newHashMap();
        this.initBizMap(fieldNumberMap, fieldFundsMap, fieldsList, 0);
        queryResult1.stream().forEach(map -> {
            String researchField = (String) map.get("research_field");
            if(fieldNumberMap.containsKey(researchField)) {
                Map<String, Object> fieldNumberValue = fieldNumberMap.get(researchField);
                fieldNumberValue.put("value", ((Number) fieldNumberValue.get("value")).longValue() +
                        ((Number) map.get("xzxms")).longValue());
            }
            if(fieldFundsMap.containsKey(researchField)) {
                Map<String, Object> fieldFundsValue = fieldFundsMap.get(researchField);
                fieldFundsValue.put("value", ((Number) fieldFundsValue.get("value")).doubleValue() +
                        ((Number) map.get("xzxm_zjf")).doubleValue());
            }
        });

        List<String> affiliationsList = queryResult1.stream().map(
                map -> (String) map.get("jgmc")).distinct().collect(Collectors.toList());
        Map<String, Map<String, Object>> rankNumberMap = Maps.newHashMap();
        Map<String, Map<String, Object>> rankFundsMap = Maps.newHashMap();
        this.initBizMap(rankNumberMap, rankFundsMap, affiliationsList, 0);
        queryResult1.stream().forEach(map -> {
            String jgmc = (String) map.get("jgmc");
            if(rankNumberMap.containsKey(jgmc)) {
                Map<String, Object> rankNumberValue = rankNumberMap.get(jgmc);
                rankNumberValue.put("value", ((Number) rankNumberValue.get("value")).longValue() +
                        ((Number) map.get("xzxms")).longValue());
            }
            if(rankFundsMap.containsKey(jgmc)) {
                Map<String, Object> rankFundsValue = rankFundsMap.get(jgmc);
                rankFundsValue.put("value", ((Number) rankFundsValue.get("value")).doubleValue() +
                        ((Number) map.get("xzxm_zjf")).doubleValue());
            }
        });

        List<Map<String, Object>> fieldNumber = this.dMap2List(fieldNumberMap);
        List<Map<String, Object>> fieldFunds = this.dMap2List(fieldFundsMap);
        List<Map<String, Object>> rankNumber = this.dMap2List(rankNumberMap);
        List<Map<String, Object>> rankFunds = this.dMap2List(rankFundsMap);

        List<Map<String, Object>> sortedRankNumber = rankNumber.stream().sorted(
                Comparator.comparingLong((m) -> (long) m.get("value") * -1)
        ).collect(Collectors.toList());
        List<Map<String, Object>> sortedRankFunds = rankFunds.stream().sorted(
                Comparator.comparingDouble((m) -> (double) m.get("value") * -1)
        ).collect(Collectors.toList());

        DetailProjectXdInfoDTO detailProjectXdInfo = new DetailProjectXdInfoDTO();
        detailProjectXdInfo.setYear(yearList);
        detailProjectXdInfo.setTrendNumber(this.map2List(yearList, trendNumberMap));
        detailProjectXdInfo.setTrendFunds(this.map2List(yearList, trendFundsMap));
        detailProjectXdInfo.setFieldNumber(fieldNumber);
        detailProjectXdInfo.setFieldFunds(fieldFunds);
        detailProjectXdInfo.setRankNumber(sortedRankNumber);
        detailProjectXdInfo.setRankFunds(sortedRankFunds);
        detailProjectXdInfo.setUpdateTime("2019年10月");
        detailProjectXdInfo.setFundsUnit("万元");

        return detailProjectXdInfo;
    }

    @Override
    public DetailProjectIncreaseInfoDTO increaseQuery(ProjectQueryDO query) {

        List<Map<String, Object>> queryResult1
                = statNsfcProjectManualMapper.queryIncrease1(query);

        List<Map<String, Object>> queryResult2
                = statMostProjectManualMapper.queryIncrease2(query);

        List<Map<String, Object>> queryResult3
                = statXdzxManualMapper.queryIncrease3(query);

        Long numberNsfc = null;
        Double fundsNsfc = null;
        if(queryResult1.get(0) != null) {
            Map<String, Object> resultMap = queryResult1.get(0);
            numberNsfc = ((Number) resultMap.get("xzxms")).longValue();
            fundsNsfc = ((Number) resultMap.get("xzzjf")).doubleValue();
        }

        Long numberKjb = null;
        Double fundsKjb = null;
        if(queryResult2.get(0) != null) {
            Map<String, Object> resultMap = queryResult2.get(0);
            numberKjb = ((Number) resultMap.get("xzxms")).longValue();
            fundsKjb = ((Number) resultMap.get("xzzjf")).doubleValue();
        }

        Long numberXd = null;
        Double fundsXd = null;
        if(queryResult3.get(0) != null) {
            Map<String, Object> resultMap = queryResult3.get(0);
            numberXd = ((Number) resultMap.get("xzxms")).longValue();
            fundsXd = ((Number) resultMap.get("xzzjf")).doubleValue();
        }

        DetailProjectIncreaseInfoDTO detailProjectIncreaseInfo = new DetailProjectIncreaseInfoDTO();
        detailProjectIncreaseInfo.setNumberNsfc(numberNsfc);
        detailProjectIncreaseInfo.setFundsNsfc(fundsNsfc);
        detailProjectIncreaseInfo.setNumberKjb(numberKjb);
        detailProjectIncreaseInfo.setFundsKjb(fundsKjb);
        detailProjectIncreaseInfo.setNumberXd(numberXd);
        detailProjectIncreaseInfo.setFundsXd(fundsXd);
        detailProjectIncreaseInfo.setUpdateTime("2019年10月");
        detailProjectIncreaseInfo.setFundsUnit("万元");

        return detailProjectIncreaseInfo;
    }

    @Override
    public DetailProjectNsfcRelationInfoDTO nsfcRelationQuery(ProjectQueryDO query) {

        List<Map<String, Object>> queryResult
                = statArpStaffDutyManualMapper.queryNsfcRelation(query);

        double totalFun = 0D;
        int totalNum = 0;
        Map<String, Integer> fieldNumberMap = Maps.newHashMap();
        Map<String, Double> fieldFundsMap = Maps.newHashMap();
        List<String> fieldList = queryResult.stream().map(map -> (String) map.get("field"))
                .distinct().collect(Collectors.toList());

        fieldList.stream().forEach(str -> {
            fieldNumberMap.put(str, 0);
            fieldFundsMap.put(str, 0D);
        });

        for(Map<String, Object> map : queryResult) {
            String field = (String) map.get("field");
            int numbers = ((Number) map.get("numbers")).intValue();
            double funds = ((Number) map.get("funds")).doubleValue();

            if(fieldNumberMap.containsKey(field)) {
                fieldNumberMap.put(field, (int) fieldNumberMap.get(field) + numbers);
            }
            if(fieldFundsMap.containsKey(field)) {
                fieldFundsMap.put(field, (double) fieldFundsMap.get(field) + funds);
            }

            totalFun += funds;
            totalNum += numbers;
        }

        List<Map<String, Object>> relationNsfc = Lists.newArrayList();
        List<Map<Object, Object>> rankNumber = Lists.newArrayList();
        List<Map<Object, Object>> rankFunds = Lists.newArrayList();

        for(Map<String, Object> map : queryResult) {
            Map<String, Object> relationNsfcValue = Maps.newHashMap();
            relationNsfcValue.put("name", map.get("jgmc"));
            relationNsfcValue.put("id", map.get("jgbh"));
            relationNsfcValue.put("number", map.get("numbers"));
            relationNsfcValue.put("funds_original", map.get("funds"));
            relationNsfcValue.put("funds",
                    totalFun == 0 ? null : ((Number) map.get("funds")).doubleValue() / totalFun * 100);
            relationNsfcValue.put("staff", map.get("staff"));
            relationNsfcValue.put("field", map.get("field"));
            relationNsfc.add(relationNsfcValue);

            Map<Object, Object> rankNumberValue = Maps.newHashMap();
            rankNumberValue.put("name", map.get("jgmc"));
            rankNumberValue.put("value", map.get("numbers"));
            rankNumber.add(rankNumberValue);

            Map<Object, Object> rankFundsValue = Maps.newHashMap();
            rankFundsValue.put("name", map.get("jgmc"));
            rankFundsValue.put("value", map.get("funds"));
            rankFunds.add(rankFundsValue);
        }

        List<Map<Object, Object>> sortedRankNumber = rankNumber.stream().sorted(
                Comparator.comparingLong((m) -> ((Number) m.get("value")).intValue() * -1)
        ).collect(Collectors.toList());
        List<Map<Object, Object>> sortedRankFunds = rankFunds.stream().sorted(
                Comparator.comparingDouble((m) -> ((Number) m.get("value")).doubleValue() * -1)
        ).collect(Collectors.toList());

        List<Map<String, Object>> fieldNumber = Lists.newArrayList();
        fieldNumberMap.entrySet().stream().forEach(map -> {
            Map<String, Object> fieldNumberValue = Maps.newHashMap();
            fieldNumberValue.put("name", map.getKey());
            fieldNumberValue.put("value", map.getValue());
            fieldNumber.add(fieldNumberValue);
        });

        List<Map<String, Object>> fieldFunds = Lists.newArrayList();
        fieldFundsMap.entrySet().stream().forEach(map -> {
            Map<String, Object> fieldFundsValue = Maps.newHashMap();
            fieldFundsValue.put("name", map.getKey());
            fieldFundsValue.put("value", map.getValue());
            fieldFunds.add(fieldFundsValue);
        });

        DetailProjectNsfcRelationInfoDTO detailProjectNsfcRelationInfo = new
                DetailProjectNsfcRelationInfoDTO();
        detailProjectNsfcRelationInfo.setFieldFunds(fieldFunds);
        detailProjectNsfcRelationInfo.setFieldNumber(fieldNumber);
        detailProjectNsfcRelationInfo.setRankNumber(sortedRankNumber);
        detailProjectNsfcRelationInfo.setRankFunds(sortedRankFunds);
        detailProjectNsfcRelationInfo.setRelationNsfc(relationNsfc);
        detailProjectNsfcRelationInfo.setIncreaseFunds(totalFun);
        detailProjectNsfcRelationInfo.setIncreaseNumber(totalNum);
        detailProjectNsfcRelationInfo.setUpdateTime("2019年10月");
        return detailProjectNsfcRelationInfo;
    }

    @Override
    public DetailProjectKjbRelationInfoDTO kjbRelationQuery(ProjectQueryDO query) {

        List<Map<String, Object>> queryResult
                = statArpStaffDutyManualMapper.queryKjbRelation(query);

        double totalFun = 0D;
        int totalNum = 0;
        Map<String, Integer> fieldNumberMap = Maps.newHashMap();
        Map<String, Double> fieldFundsMap = Maps.newHashMap();
        List<String> fieldList = queryResult.stream().map(map -> (String) map.get("field"))
                .distinct().collect(Collectors.toList());

        fieldList.stream().forEach(str -> {
            fieldNumberMap.put(str, 0);
            fieldFundsMap.put(str, 0D);
        });

        for(Map<String, Object> map : queryResult) {
            String field = (String) map.get("field");
            int numbers = ((Number) map.get("numbers")).intValue();
            double funds = ((Number) map.get("funds")).doubleValue();

            if(fieldNumberMap.containsKey(field)) {
                fieldNumberMap.put(field, (int) fieldNumberMap.get(field) + numbers);
            }
            if(fieldFundsMap.containsKey(field)) {
                fieldFundsMap.put(field, (double) fieldFundsMap.get(field) + funds);
            }

            totalFun += funds;
            totalNum += numbers;
        }

        List<Map<String, Object>> relationNsfc = Lists.newArrayList();
        List<Map<Object, Object>> rankNumber = Lists.newArrayList();
        List<Map<Object, Object>> rankFunds = Lists.newArrayList();

        for(Map<String, Object> map : queryResult) {
            Map<String, Object> relationNsfcValue = Maps.newHashMap();
            relationNsfcValue.put("name", map.get("jgmc"));
            relationNsfcValue.put("id", map.get("jgbh"));
            relationNsfcValue.put("number", map.get("numbers"));
            relationNsfcValue.put("funds_original", map.get("funds"));
            relationNsfcValue.put("funds",
                    totalFun == 0 ? null : ((Number) map.get("funds")).doubleValue() / totalFun * 100);
            relationNsfcValue.put("staff", map.get("staff"));
            relationNsfcValue.put("field", map.get("field"));
            relationNsfc.add(relationNsfcValue);

            Map<Object, Object> rankNumberValue = Maps.newHashMap();
            rankNumberValue.put("name", map.get("jgmc"));
            rankNumberValue.put("value", map.get("numbers"));
            rankNumber.add(rankNumberValue);

            Map<Object, Object> rankFundsValue = Maps.newHashMap();
            rankFundsValue.put("name", map.get("jgmc"));
            rankFundsValue.put("value", map.get("funds"));
            rankFunds.add(rankFundsValue);
        }

        List<Map<Object, Object>> sortedRankNumber = rankNumber.stream().sorted(
                Comparator.comparingLong((m) -> ((Number) m.get("value")).intValue() * -1)
        ).collect(Collectors.toList());
        List<Map<Object, Object>> sortedRankFunds = rankFunds.stream().sorted(
                Comparator.comparingDouble((m) -> ((Number) m.get("value")).doubleValue() * -1)
        ).collect(Collectors.toList());

        List<Map<String, Object>> fieldNumber = Lists.newArrayList();
        fieldNumberMap.entrySet().stream().forEach(map -> {
            Map<String, Object> fieldNumberValue = Maps.newHashMap();
            fieldNumberValue.put("name", map.getKey());
            fieldNumberValue.put("value", map.getValue());
            fieldNumber.add(fieldNumberValue);
        });

        List<Map<String, Object>> fieldFunds = Lists.newArrayList();
        fieldFundsMap.entrySet().stream().forEach(map -> {
            Map<String, Object> fieldFundsValue = Maps.newHashMap();
            fieldFundsValue.put("name", map.getKey());
            fieldFundsValue.put("value", map.getValue());
            fieldFunds.add(fieldFundsValue);
        });

        DetailProjectKjbRelationInfoDTO detailProjectKjbRelationInfo = new
                DetailProjectKjbRelationInfoDTO();
        detailProjectKjbRelationInfo.setFieldFunds(fieldFunds);
        detailProjectKjbRelationInfo.setFieldNumber(fieldNumber);
        detailProjectKjbRelationInfo.setRankNumber(sortedRankNumber);
        detailProjectKjbRelationInfo.setRankFunds(sortedRankFunds);
        detailProjectKjbRelationInfo.setRelationNsfc(relationNsfc);
        detailProjectKjbRelationInfo.setIncreaseFunds(totalFun);
        detailProjectKjbRelationInfo.setIncreaseNumber(totalNum);
        detailProjectKjbRelationInfo.setUpdateTime("2019年10月");
        return detailProjectKjbRelationInfo;
    }

    @Override
    public DetailProjectXdRelationInfoDTO xdRelationQuery(ProjectQueryDO query) {

        List<Map<String, Object>> queryResult
                = statArpStaffDutyManualMapper.queryXdRelation(query);

        double totalFun = 0D;
        int totalNum = 0;
        Map<String, Integer> fieldNumberMap = Maps.newHashMap();
        Map<String, Double> fieldFundsMap = Maps.newHashMap();
        List<String> fieldList = queryResult.stream().map(map -> (String) map.get("field"))
                .distinct().collect(Collectors.toList());

        fieldList.stream().forEach(str -> {
            fieldNumberMap.put(str, 0);
            fieldFundsMap.put(str, 0D);
        });

        for(Map<String, Object> map : queryResult) {
            String field = (String) map.get("field");
            int numbers = ((Number) map.get("numbers")).intValue();
            double funds = ((Number) map.get("funds")).doubleValue();

            totalFun += funds;
            totalNum += numbers;

            if(fieldNumberMap.containsKey(field)) {
                fieldNumberMap.put(field, (int) fieldNumberMap.get(field) + numbers);
            }
            if(fieldFundsMap.containsKey(field)) {
                fieldFundsMap.put(field, (double) fieldFundsMap.get(field) + funds);
            }
        }

        List<Map<String, Object>> relationNsfc = Lists.newArrayList();
        List<Map<Object, Object>> rankNumber = Lists.newArrayList();
        List<Map<Object, Object>> rankFunds = Lists.newArrayList();

        for(Map<String, Object> map : queryResult) {
            Map<String, Object> relationNsfcValue = Maps.newHashMap();
            relationNsfcValue.put("name", map.get("jgmc"));
            relationNsfcValue.put("id", map.get("jgbh"));
            relationNsfcValue.put("number", map.get("numbers"));
            relationNsfcValue.put("funds_original", map.get("funds"));
            relationNsfcValue.put("funds",
                    totalFun == 0 ? null : ((Number) map.get("funds")).doubleValue() / totalFun * 100);
            relationNsfcValue.put("staff", map.get("staff"));
            relationNsfcValue.put("field", map.get("field"));
            relationNsfc.add(relationNsfcValue);

            Map<Object, Object> rankNumberValue = Maps.newHashMap();
            rankNumberValue.put("name", map.get("jgmc"));
            rankNumberValue.put("value", map.get("numbers"));
            rankNumber.add(rankNumberValue);

            Map<Object, Object> rankFundsValue = Maps.newHashMap();
            rankFundsValue.put("name", map.get("jgmc"));
            rankFundsValue.put("value", map.get("funds"));
            rankFunds.add(rankFundsValue);
        }

        List<Map<Object, Object>> sortedRankNumber = rankNumber.stream().sorted(
                Comparator.comparingLong((m) -> ((Number) m.get("value")).intValue() * -1)
        ).collect(Collectors.toList());
        List<Map<Object, Object>> sortedRankFunds = rankFunds.stream().sorted(
                Comparator.comparingDouble((m) -> ((Number) m.get("value")).doubleValue() * -1)
        ).collect(Collectors.toList());

        List<Map<String, Object>> fieldNumber = Lists.newArrayList();
        fieldNumberMap.entrySet().stream().forEach(map -> {
            Map<String, Object> fieldNumberValue = Maps.newHashMap();
            fieldNumberValue.put("name", map.getKey());
            fieldNumberValue.put("value", map.getValue());
            fieldNumber.add(fieldNumberValue);
        });

        List<Map<String, Object>> fieldFunds = Lists.newArrayList();
        fieldFundsMap.entrySet().stream().forEach(map -> {
            Map<String, Object> fieldFundsValue = Maps.newHashMap();
            fieldFundsValue.put("name", map.getKey());
            fieldFundsValue.put("value", map.getValue());
            fieldFunds.add(fieldFundsValue);
        });

        DetailProjectXdRelationInfoDTO detailProjectXdRelationInfo = new
                DetailProjectXdRelationInfoDTO();
        detailProjectXdRelationInfo.setFieldFunds(fieldFunds);
        detailProjectXdRelationInfo.setFieldNumber(fieldNumber);
        detailProjectXdRelationInfo.setRankNumber(sortedRankNumber);
        detailProjectXdRelationInfo.setRankFunds(sortedRankFunds);
        detailProjectXdRelationInfo.setRelationNsfc(relationNsfc);
        detailProjectXdRelationInfo.setIncreaseFunds(totalFun);
        detailProjectXdRelationInfo.setIncreaseNumber(totalNum);
        detailProjectXdRelationInfo.setUpdateTime("2019年10月");
        return detailProjectXdRelationInfo;
    }

    private List<Map<String, Object>> dMap2List(Map<String, Map<String, Object>> orginMap) {
        List<Map<String, Object>> list = Lists.newArrayList();
        orginMap.entrySet().stream().forEach(map -> {
            Map<String, Object> rankFundsValue = Maps.newHashMap();
            rankFundsValue.put("name", map.getKey());
            rankFundsValue.put("value", map.getValue().get("value"));
            list.add(rankFundsValue);
        });
        return list;
    }

    private List<Object> map2List(List<String> list, Map<String, Object> map) {
        List<Object> targetList = Lists.newArrayList();
        list.stream().forEach(str -> {
            if(map.containsKey(str)) {
                targetList.add(map.get(str));
            }
        });
        return targetList;
    }

    private void initBizMap(Map<String, Map<String, Object>> map1, Map<String, Map<String, Object>>
            map2, List<String> list, Object defaultValue) {
        list.stream().forEach(str -> {
            Map<String, Object> map1Value = Maps.newHashMap();
            map1Value.put("name", str);
            map1Value.put("value", defaultValue);
            map1.put(str, map1Value);

            Map<String, Object> map2Value = Maps.newHashMap();
            map2Value.put("name", str);
            map2Value.put("value", defaultValue);
            map2.put(str, map2Value);
        });
    }

}
