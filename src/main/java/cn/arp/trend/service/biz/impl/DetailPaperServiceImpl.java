package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.PaperHCAuthorsQueryDO;
import cn.arp.trend.data.model.DO.PaperSciQueryDO;
import cn.arp.trend.data.model.DTO.PaperHCAuthorsInfoDTO;
import cn.arp.trend.data.model.DTO.PaperSciInfoDTO;
import cn.arp.trend.error.RestError;
import cn.arp.trend.repository.biz.manual.StatCasPaperManualMapper;
import cn.arp.trend.repository.biz.manual.StatHcauthorsCountManualMapper;
import cn.arp.trend.service.biz.DetailPaperService;
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
 * Date:2020/10/22
 * Time:上午10:51
 **/
@Service
public class DetailPaperServiceImpl extends AbstructServiceHelper implements DetailPaperService {

    @Resource
    private StatCasPaperManualMapper statCasPaperManualMapper;

    @Resource
    private StatHcauthorsCountManualMapper statHcauthorsCountManualMapper;

    @Override
    public PaperSciInfoDTO paperSciQuery(PaperSciQueryDO query) {

        List<Map<String, Object>> queryResult1
                = statCasPaperManualMapper.querySci1(query);

        List<Map<String, Object>> queryResult2
                = statCasPaperManualMapper.querySci2(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        /*List<String> distinctFields = queryResult1.stream().map(map -> (String) map.get
                ("research_field")).distinct().collect(Collectors.toList());*/

        Map<String, Integer> fieldMap = Maps.newHashMap();
        Map<String, Integer> topAffiliationMap = Maps.newHashMap();
        Map<String, Integer> classifyMap = Maps.newHashMap();
        Map<String, Integer> sCIClassifyMap = Maps.newHashMap();

        queryResult1.stream().forEach(map -> {
            String researchField = (String) map.get("research_field");
            if(fieldMap.containsKey(researchField)) {
                fieldMap.put(researchField, fieldMap.get(researchField) + ((Number) map.get
                        ("lws")).intValue());
            } else {
                fieldMap.put(researchField, ((Number) map.get("lws")).intValue());
            }

            String nf = (String) map.get("nf");
            if(classifyMap.containsKey(nf)) {
                classifyMap.put(nf, classifyMap.get(nf) + ((Number) map.get
                        ("lws")).intValue());
            } else {
                classifyMap.put(nf, ((Number) map.get("lws")).intValue());
            }

            String jgmc = (String) map.get("jgmc");
            if(topAffiliationMap.containsKey(jgmc)) {
                topAffiliationMap.put(jgmc, topAffiliationMap.get(jgmc) + ((Number) map.get
                        ("lws")).intValue());
            } else {
                topAffiliationMap.put(jgmc, ((Number) map.get("lws")).intValue());
            }
        });

        queryResult2.stream().forEach(map -> {
            String nf = (String) map.get("nf");
            if(sCIClassifyMap.containsKey(nf)) {
                sCIClassifyMap.put(nf, sCIClassifyMap.get(nf) + ((Number) map.get
                        ("num")).intValue());
            } else {
                sCIClassifyMap.put(nf, ((Number) map.get("num")).intValue());
            }
        });

        List<Integer> classify = Lists.newArrayList();
        List<Integer> sCIClassify = Lists.newArrayList();
        yearList.stream().forEach(year -> {
            if(classifyMap.containsKey(year)) {
                classify.add(classifyMap.get(year));
            } else {
                classify.add(null);
            }
            if(sCIClassifyMap.containsKey(year)) {
                sCIClassify.add(sCIClassifyMap.get(year));
            } else {
                sCIClassify.add(null);
            }
        });

        List<Map<String, Object>> fields = fieldMap.entrySet().stream().map(
                map -> {
                    Map<String, Object> m = Maps.newHashMap();
                    m.put("fieldsName", map.getKey());
                    m.put("fieldsNumber", map.getValue());
                    return m;
                }).collect(Collectors.toList());

        List<Map<String, Object>> topAffiliations = topAffiliationMap.entrySet().stream().map(
                map -> {
                    Map<String, Object> m = Maps.newHashMap();
                    m.put("unitName", map.getKey());
                    m.put("unitNumber", map.getValue());
                    return m;
                }).collect(Collectors.toList());

        PaperSciInfoDTO info = new PaperSciInfoDTO();
        info.setUpdateTime("2019年10月");
        info.setClassify(classify);
        info.setsCIClassify(sCIClassify);
        info.setYearList(yearList);
        info.setFields(fields);
        info.setTopAffiliations(topAffiliations);
        info.setResultArray(Lists.newArrayList(queryResult1, queryResult2));
        info.setsCINewAry(queryResult2);

        return info;
    }

    @Override
    public PaperHCAuthorsInfoDTO hCAuthorsQuery(PaperHCAuthorsQueryDO query) {

        List<Map<String, Object>> rstRank = null;
        List<Map<String, Object>> rstTrend = null;
        List<Map<String, Object>> rstField = null;

        switch (query.getCategory()) {
            case "世界":
                rstRank = statHcauthorsCountManualMapper.queryHCAuthors1(query);
                rstTrend = statHcauthorsCountManualMapper.queryHCAuthors2(query);
                rstField = statHcauthorsCountManualMapper.queryHCAuthors3(query);
                break;
            case "中国" :
                rstRank = statHcauthorsCountManualMapper.queryHCAuthors4(query);
                rstTrend = statHcauthorsCountManualMapper.queryHCAuthors5(query);
                rstField = statHcauthorsCountManualMapper.queryHCAuthors6(query);
                break;
            default :
                RestError.badArgument("category取值有误");
        }

        List<List<Object>> topAuthors = rstRank.stream().map(map -> {
            List<Object> topAuthor = Lists.newArrayList(map.get("jgmc"), map.get("rc"));
            return topAuthor;
        }).collect(Collectors.toList());

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        List<Object> classify = Lists.newArrayList();
        Map<String, Integer> subClassify1 = Maps.newHashMap();
        Map<String, Integer> subClassify2 = Maps.newHashMap();

        yearList.stream().forEach(year -> {
            subClassify1.put(year, 0);
            subClassify2.put(year, 0);
        });

        rstTrend.stream().forEach(map -> {
            String nf = (String) map.get("nf");
            if(subClassify1.containsKey(nf)) {
                subClassify1.put(nf, subClassify1.get(nf) + ((Number) map.get("rc")).intValue());
            }
        });

        List<Object> subClassify1List = Lists.newArrayList();
        yearList.stream().forEach(year -> {
            subClassify1List.add(subClassify1.get(year));
        });

        classify.add(Lists.newArrayList("高被引科学家", subClassify1List));
        classify.add(Lists.newArrayList("占世界比重", subClassify2.entrySet().stream().map(map ->
                map.getValue()).collect(Collectors.toList())));

        List<Map<String, Object>> newData = Lists.newArrayList();

        List<Object> count = Lists.newArrayList();
        List<Object> category = Lists.newArrayList();
        List<Object> world = Lists.newArrayList();

        rstField.stream().forEach(map -> {
            Map<String, Object> newDataValue = Maps.newHashMap();
            newDataValue.put("count", map.get("up"));
            newDataValue.put("category", map.get("field"));
            newDataValue.put("World", map.get("down"));

            count.add(map.get("up"));
            category.add(map.get("field"));
            world.add(map.get("down"));
        });

        Map<String, List<Object>> fields = Maps.newHashMap();
        fields.put("count", count);
        fields.put("category", category);
        fields.put("World", world);

        PaperHCAuthorsInfoDTO info = new PaperHCAuthorsInfoDTO();
        info.setClassify(classify);
        info.setFields(fields);
        info.setNewData(newData);
        info.setTopAuthors(topAuthors);
        info.setYear(yearList);
        info.setUpdateTime("2019年10月");

        return info;
    }
}
