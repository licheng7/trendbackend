package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.ZKYChinaPatentQueryDO;
import cn.arp.trend.data.model.DO.ZKYPCTPatentQueryDO;
import cn.arp.trend.data.model.DTO.ZKYChinaPatentInfoDTO;
import cn.arp.trend.data.model.DTO.ZKYPCTPatentInfoDTO;
import cn.arp.trend.repository.biz.manual.StatPatentManualMapper;
import cn.arp.trend.repository.biz.manual.StatPatentNewManualMapper;
import cn.arp.trend.service.biz.DetailPatentService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/22
 * Time:下午12:16
 **/
@Service
public class DetailPatentServiceImpl extends AbstructServiceHelper implements DetailPatentService {

    @Resource
    private StatPatentManualMapper statPatentManualMapper;

    @Resource
    private StatPatentNewManualMapper statPatentNewManualMapper;

    @Override
    public ZKYPCTPatentInfoDTO patentZKYPCTQuery(ZKYPCTPatentQueryDO query) throws Exception {

        List<Map<String, Object>> queryResult
                = statPatentManualMapper.queryZKYPCTPatent1(query);


        List<String> distinctAffiliations = queryResult.stream().map(map -> (String) map.get
                ("jgmc")).distinct().collect(Collectors.toList());
        List<String> distinctFields = queryResult.stream().map(map -> (String) map.get
                ("ssly")).distinct().collect(Collectors.toList());

        Map<String, Map<String, Object>> temp = Maps.newHashMap();
        distinctAffiliations.stream().forEach(str -> {
            Map<String, Object> map = Maps.newHashMap();
            map.put("name", str);
            map.put("value", 0l);
            temp.put(str, map);
        });

        Map<String, Object> countMap = Maps.newHashMap();
        distinctFields.stream().forEach(str -> {
            countMap.put(str, null);
        });

        switch (query.getCategory()) {
            case "新增" :
                this.doBuildTemp(queryResult, temp, Map.class.getMethod("get", Object.class), "xz");
                this.doBuildCount(queryResult, countMap, Map.class.getMethod("get", Object.class), "xz");
                break;
            case "失效" :
                this.doBuildTemp(queryResult, temp, Map.class.getMethod("get", Object.class), "sx");
                this.doBuildCount(queryResult, countMap, Map.class.getMethod("get", Object.class), "sx");
                break;
            case "有效" :
                this.doBuildTemp(queryResult, temp, Map.class.getMethod("get", Object.class), "yx");
                this.doBuildCount(queryResult, countMap, Map.class.getMethod("get", Object.class), "yx");
                break;
            default :
                this.doBuildTemp(queryResult, temp, Map.class.getMethod("get", Object.class), "lj");
                this.doBuildCount(queryResult, countMap, Map.class.getMethod("get", Object.class), "lj");
        }

        List<List<Object>> orderChinapatent = Lists.newArrayList();
        temp.entrySet().stream().forEach(map -> {
            Map<String, Object> tValue = map.getValue();
            String name = (String) tValue.get("name");
            Object value = tValue.get("value");
            List<Object> tempList = Lists.newArrayList();
            tempList.add(name.replace("中国科学院", ""));
            tempList.add(value);
            orderChinapatent.add(tempList);
        });

        List<List<Object>> sortedOrderChinapatent = orderChinapatent.stream().sorted(
                Comparator.comparingLong(list -> (long) list.get(1) * -1)
        ).collect(Collectors.toList());

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());
        Map<String, Long> ljMap = Maps.newHashMap();
        Map<String, Long> xzMap = Maps.newHashMap();
        Map<String, Long> sxMap = Maps.newHashMap();
        Map<String, Long> yxMap = Maps.newHashMap();
        yearList.stream().forEach(year -> {
            ljMap.put(year, null);
            xzMap.put(year, null);
            sxMap.put(year, null);
            yxMap.put(year, null);
        });

        queryResult.stream().forEach(map -> {
            String nf = (String) map.get("nf");
            if(ljMap.containsKey(nf)) {
                ljMap.put(nf, (ljMap.get(nf) == null ? 0L : ljMap.get(nf))
                        + (map.get("lj") == null ? 0L : ((Number) map.get("lj")).longValue()));
            }
            if(xzMap.containsKey(nf)) {
                xzMap.put(nf, (xzMap.get(nf) == null ? 0L : xzMap.get(nf))
                        + (map.get("xz") == null ? 0L : ((Number) map.get("xz")).longValue()));
            }
            if(sxMap.containsKey(nf)) {
                sxMap.put(nf, (sxMap.get(nf) == null ? 0L : sxMap.get(nf))
                        + (map.get("sx") == null ? 0L : ((Number) map.get("sx")).longValue()));
            }
            if(yxMap.containsKey(nf)) {
                yxMap.put(nf, (yxMap.get(nf) == null ? 0L : yxMap.get(nf))
                        + (map.get("yx") == null ? 0L : ((Number) map.get("yx")).longValue()));
            }
        });

        List<Long> lj = Lists.newArrayList();
        List<Long> xz = Lists.newArrayList();
        List<Long> sx = Lists.newArrayList();
        List<Long> yx = Lists.newArrayList();
        yearList.stream().forEach(year -> {
            lj.add(ljMap.get(year));
            xz.add(xzMap.get(year));
            sx.add(sxMap.get(year));
            yx.add(yxMap.get(year));
        });

        List<List> classify = Lists.newArrayList();
        this.doBuildClassify(classify, "累计", lj);
        this.doBuildClassify(classify, "新增", xz);
        this.doBuildClassify(classify, "失效", sx);
        this.doBuildClassify(classify, "有效", yx);

        List<List<Object>> fields = Lists.newArrayList();
        countMap.entrySet().stream().forEach(map -> {
            List<Object> tempList = Lists.newArrayList(map.getKey(), map.getValue());
            fields.add(tempList);
        });

        ZKYPCTPatentInfoDTO info = new ZKYPCTPatentInfoDTO();
        info.setClassify(classify);
        info.setFields(fields);
        info.setOrderChinapatent(sortedOrderChinapatent);
        info.setYearList(yearList);
        info.setUpdateTime("2019年10月");

        return info;
    }

    @Override
    public ZKYChinaPatentInfoDTO patentZKYChinaQuery(ZKYChinaPatentQueryDO query) {

        List<Map<String, Object>> pie
                = statPatentNewManualMapper.queryZKYChinaPatent1(query);

        List<Map<String, Object>> histogram
                = statPatentNewManualMapper.queryZKYChinaPatent2(query);

        List<Map<String, Object>> graph
                = statPatentNewManualMapper.queryZKYChinaPatent3(query);

        List<Map<String, Object>> arpGraph
                = statPatentNewManualMapper.queryZKYChinaPatent4(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        List<List> histogramList = histogram.stream().map(map -> Lists.newArrayList(map.get
                ("jgmc"), map.get("sq"))).collect(Collectors.toList());

        ZKYChinaPatentInfoDTO info = new ZKYChinaPatentInfoDTO();
        info.setPie(pie);
        info.setHistogramList(histogramList);
        info.setGraph(graph);
        info.setArpGraph(arpGraph);
        info.setYearList(yearList);
        info.setResultArray(Lists.newArrayList(pie, histogram, graph, arpGraph));

        return info;
    }

    private void doBuildClassify(List<List> classify, String name, List<Long> list) {
        List temp = Lists.newArrayList();
        temp.add(name);
        temp.add(list);
        classify.add(temp);
    }

    private void doBuildCount(List<Map<String, Object>> queryResult, Map<String, Object>
            countMap, Method method, String arg) {
        queryResult.stream().forEach(map -> {
            String ssly = (String) map.get("ssly");
            if(countMap.containsKey(ssly)) {
                Object value = null;
                try {
                    value = method.invoke(map, arg);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                countMap.put(ssly, countMap.get(ssly) == null ? 0L : (Long) countMap
                        .get(ssly) + (Long) value);
            }
        });
    }

    private void doBuildTemp(List<Map<String, Object>> queryResult, Map<String, Map<String,
            Object>> temp, Method method, String arg) throws Exception {
        queryResult.stream().forEach(map -> {
            String jgmc = (String) map.get("jgmc");
            if(temp.containsKey(jgmc)) {
                Object value = null;
                Map<String, Object> targetValue = temp.get(jgmc);
                try {
                    value = method.invoke(map, arg);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                targetValue.put("value", targetValue.get("value") == null ? 0L : (Long) targetValue
                        .get("value") + (Long) value);
            }
        });
    }
}
