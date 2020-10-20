package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.DetailProjectKjbInfoDTO;
import cn.arp.trend.data.model.DTO.DetailProjectNsfcInfoDTO;
import cn.arp.trend.repository.biz.manual.StatMostProjectManualMapper;
import cn.arp.trend.repository.biz.manual.StatNsfcProjectManualMapper;
import cn.arp.trend.service.biz.DetailProjectService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

        DetailProjectNsfcInfoDTO detailProjectNsfcInfo = new DetailProjectNsfcInfoDTO();
        detailProjectNsfcInfo.setYear(yearList);
        detailProjectNsfcInfo.setTrendNumber(this.map2List(yearList, trendNumberMap));
        detailProjectNsfcInfo.setTrendFunds(this.map2List(yearList, trendFundsMap));
        detailProjectNsfcInfo.setFieldNumber(fieldNumber);
        detailProjectNsfcInfo.setFieldFunds(fieldFunds);
        detailProjectNsfcInfo.setRankNumber(rankNumber);
        detailProjectNsfcInfo.setRankFunds(rankFunds);
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

        DetailProjectKjbInfoDTO detailProjectKjbInfo = new DetailProjectKjbInfoDTO();
        detailProjectKjbInfo.setYear(yearList);
        detailProjectKjbInfo.setTrendNumber(this.map2List(yearList, trendNumberMap));
        detailProjectKjbInfo.setTrendFunds(this.map2List(yearList, trendFundsMap));
        detailProjectKjbInfo.setFieldNumber(fieldNumber);
        detailProjectKjbInfo.setFieldFunds(fieldFunds);
        detailProjectKjbInfo.setRankNumber(rankNumber);
        detailProjectKjbInfo.setRankFunds(rankFunds);
        detailProjectKjbInfo.setUpdateTime("2019年10月");
        detailProjectKjbInfo.setFundsUnit("万元");

        return detailProjectKjbInfo;
    }

    private List<Map<String, Object>> dMap2List(Map<String, Map<String, Object>> orginMap) {
        List<Map<String, Object>> list = Lists.newArrayList();
        orginMap.entrySet().stream().forEach(map -> {
            Map<String, Object> rankFundsValue = Maps.newHashMap();
            rankFundsValue.put("key", map.getKey());
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
