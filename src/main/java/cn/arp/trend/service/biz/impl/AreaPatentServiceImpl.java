package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AreaPatentQueryDO;
import cn.arp.trend.data.model.DTO.AreaPatentInfoDTO;
import cn.arp.trend.repository.biz.manual.StatPatentNewManualMapper;
import cn.arp.trend.service.biz.AreaPatentService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import cn.arp.trend.service.biz.common.ConcurrentSupport;
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
 * Date:2020/10/31
 * Time:下午11:08
 **/
@Service
public class AreaPatentServiceImpl extends ConcurrentSupport implements AreaPatentService {

    @Resource
    private StatPatentNewManualMapper statPatentNewManualMapper;

    @Override
    public AreaPatentInfoDTO query(AreaPatentQueryDO query) {

        return this.queryByCs(query);
    }

    private Map<String, List<Object>> initPCTInventAry() {
        List<Object> lj = Lists.newArrayList();
        List<Object> xz = Lists.newArrayList();
        Map<String, List<Object>> map = Maps.newHashMap();
        map.put("lj", lj);
        map.put("xz", xz);
        return map;
    }

    private Map<String, List<Object>> initInventAndARPAry() {
        List<Object> sq = Lists.newArrayList();
        List<Object> pz = Lists.newArrayList();
        List<Object> sx = Lists.newArrayList();
        Map<String, List<Object>> map = Maps.newHashMap();
        map.put("sq", sq);
        map.put("pz", pz);
        map.put("sx", sx);
        return map;
    }

    private void buildPCTInventAry(
            Map<String, List<Object>> bizList, Map<String, Object> resultMap) {
        if(resultMap == null) {
            bizList.get("lj").add(null);
            bizList.get("xz").add(null);
        } else {
            bizList.get("lj").add(resultMap == null ? null :
                    (resultMap.get("lj") == null ? null : resultMap.get("lj")));
            bizList.get("xz").add(resultMap == null ? null :
                    (resultMap.get("xz") == null ? null : resultMap.get("xz")));
        }
    }

    private void buildInventAndARpAry(
            Map<String, List<Object>> bizList, Map<String, Object> resultMap) {
        if(resultMap == null) {
            bizList.get("sq").add(null);
            bizList.get("pz").add(null);
            bizList.get("sx").add(null);
        } else {
            bizList.get("sq").add(resultMap == null ? null : (resultMap.get("sq") == null ? null :
                    resultMap.get("sq")));
            bizList.get("pz").add(resultMap == null ? null : (resultMap.get("pz") == null ? null
                    : resultMap.get("pz")));
            bizList.get("sx").add(resultMap == null ? null : (resultMap.get("sx") == null ? null
                    : resultMap.get("sx")));
        }
    }


    private AreaPatentInfoDTO queryByCs(AreaPatentQueryDO query) {

        ConcurrentSupport.ConcurrentSupportContext context = this.initConcurrentSupportContext();

        ConcurrentSupport.Task<List<Map<String, Object>>> task1 =
                new ConcurrentSupport.Task<>("queryAreaPatent1",
                        () -> statPatentNewManualMapper.queryAreaPatent1(query));
        context.registerTask(task1);

        ConcurrentSupport.Task<List<Map<String, Object>>> task2 =
                new ConcurrentSupport.Task<>("queryAreaPatent2",
                        () -> statPatentNewManualMapper.queryAreaPatent2(query));
        context.registerTask(task2);

        ConcurrentSupport.Task<List<Map<String, Object>>> task3 =
                new ConcurrentSupport.Task<>("queryAreaPatent3",
                        () -> statPatentNewManualMapper.queryAreaPatent3(query));
        context.registerTask(task3);

        ConcurrentSupport.Task<List<Map<String, Object>>> task4 =
                new ConcurrentSupport.Task<>("queryAreaPatent4",
                        () -> statPatentNewManualMapper.queryAreaPatent4(query));
        context.registerTask(task4);

        ConcurrentSupport.Task<List<Map<String, Object>>> task5 =
                new ConcurrentSupport.Task<>("queryAreaPatent5",
                        () -> statPatentNewManualMapper.queryAreaPatent5(query));
        context.registerTask(task5);

        ConcurrentSupport.Task<List<Map<String, Object>>> task6 =
                new ConcurrentSupport.Task<>("queryAreaPatent6",
                        () -> statPatentNewManualMapper.queryAreaPatent6(query));
        context.registerTask(task6);

        ConcurrentSupport.Task<List<Map<String, Object>>> task7 =
                new ConcurrentSupport.Task<>("queryAreaPatent7",
                        () -> statPatentNewManualMapper.queryAreaPatent7(query));
        context.registerTask(task7);

        ConcurrentSupport.Task<List<Map<String, Object>>> task8 =
                new ConcurrentSupport.Task<>("queryAreaPatent8",
                        () -> statPatentNewManualMapper.queryAreaPatent8(query));
        context.registerTask(task8);

        ConcurrentSupport.Task<List<Map<String, Object>>> task9 =
                new ConcurrentSupport.Task<>("queryAreaPatent9",
                        () -> statPatentNewManualMapper.queryAreaPatent9(query));
        context.registerTask(task9);

        context.taskExecute();
        context.await();

        if(!context.isSuccess()) {
            throw new RuntimeException(context.getError());
        }
        Map<String, List> queryResult = context.getResult();

        List<Map<String, Object>> originalInvent = queryResult.get(task1.getTaskName());
        List<Map<String, Object>> aRPOriginalInvent = queryResult.get(task2.getTaskName());
        List<Map<String, Object>> originalInventList = queryResult.get(task3.getTaskName());
        List<Map<String, Object>> originalUnit = queryResult.get(task4.getTaskName());
        List<Map<String, Object>> pCTOriginalInvent = queryResult.get(task5.getTaskName());
        List<Map<String, Object>> pCTOriginalInventList = queryResult.get(task6.getTaskName());
        List<Map<String, Object>> pCTOriginalUnit = queryResult.get(task7.getTaskName());
        List<Map<String, Object>> originalInvent2List = queryResult.get(task8.getTaskName());
        List<Map<String, Object>> pCTOriginalInvent2List = queryResult.get(task9.getTaskName());

        Map<String, Object> originalInventProportion =
                originalInventList == null ? null : originalInventList.get(0);
        Map<String, Object> pCTOriginalInventProportion =
                pCTOriginalInventList == null ? null : pCTOriginalInventList.get(0);
        Map<String, Object> originalInventProportionRemain =
                originalInvent2List == null ? null : originalInvent2List.get(0);
        Map<String, Object> pCTOriginalInventProportionRemain =
                pCTOriginalInvent2List == null ? null : pCTOriginalInvent2List.get(0);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> originalInventMap
                = this.bizData4MapByKeyLimit1(originalInvent, "nf");

        Map<String, Map<String, Object>> aRPOriginalInventMap
                = this.bizData4MapByKeyLimit1(aRPOriginalInvent, "nf");

        Map<String, Map<String, Object>> pCTOriginalInventMap
                = this.bizData4MapByKeyLimit1(pCTOriginalInvent, "nf");

        Map<String, List<Object>> inventAry = this.initInventAndARPAry();
        Map<String, List<Object>> aRPInventAry = this.initInventAndARPAry();
        Map<String, List<Object>> pCTInventAry = this.initPCTInventAry();
        yearList.stream().forEach(year -> {
            this.buildInventAndARpAry(inventAry, originalInventMap.get(year));
            this.buildInventAndARpAry(aRPInventAry, aRPOriginalInventMap.get(year));
            this.buildPCTInventAry(pCTInventAry, pCTOriginalInventMap.get(year));
        });

        Map<String, Object> originalInventProportionMap1 = Maps.newHashMap();
        originalInventProportionMap1.put("name", "所选申请");
        originalInventProportionMap1.put("value", originalInventProportion == null ? 0 :
                originalInventProportion.get("sq"));
        Map<String, Object> originalInventProportionMap2 = Maps.newHashMap();
        originalInventProportionMap2.put("name", "其他申请");
        originalInventProportionMap2.put("value", (originalInventProportionRemain == null ? 0 :
                ((Number) originalInventProportionRemain.get("sq")).intValue()) -
                (originalInventProportion == null ? 0 :
                        ((Number) originalInventProportion.get("sq")).intValue()));
        List<Map<String, Object>> inventproportion = Lists.newArrayList
                (originalInventProportionMap1, originalInventProportionMap2);

        Map<String, Object> pCTInventProportionMap1 = Maps.newHashMap();
        pCTInventProportionMap1.put("name", "所选新增");
        pCTInventProportionMap1.put("value", pCTOriginalInventProportion == null ? 0 :
                pCTOriginalInventProportion.get("xz"));
        Map<String, Object> pCTInventProportionMap2 = Maps.newHashMap();
        pCTInventProportionMap2.put("name", "其他新增");
        pCTInventProportionMap2.put("value", (pCTOriginalInventProportionRemain == null ? 0 : (
                (Number) pCTOriginalInventProportionRemain.get
                ("xz")).intValue()) - (pCTOriginalInventProportion == null ? 0 : (Number)
                pCTOriginalInventProportion.get("xz")).intValue());
        List<Map<String, Object>> pCTInventProportion = Lists.newArrayList
                (pCTInventProportionMap1, pCTInventProportionMap2);

        List<Map<String, Object>> originalUnitAry = originalUnit.stream().map(map -> {
            Map<String, Object> data = Maps.newHashMap();
            data.put("name", map.get("jgmc"));
            data.put("value", map.get("sq"));
            return data;
        }).collect(Collectors.toList());

        List<Map<String, Object>> pCTOriginalUnitAry = pCTOriginalUnit.stream().map(map -> {
            Map<String, Object> data = Maps.newHashMap();
            data.put("name", map.get("jgmc"));
            data.put("value", map.get("xz"));
            return data;
        }).collect(Collectors.toList());

        AreaPatentInfoDTO info = new AreaPatentInfoDTO();
        info.setInventAry(inventAry);
        info.setaRPInventAry(aRPInventAry);
        info.setpCTInventAry(pCTInventAry);
        info.setInventproportion(inventproportion);
        info.setpCTInventProportion(pCTInventProportion);
        info.setOriginalUnitAry(originalUnitAry);
        info.setpCTOriginalUnitAry(pCTOriginalUnitAry);
        info.setYearList(yearList);
        info.setUpdateTime(AbstructServiceHelper.UPDATETIME);

        return info;
    }





    @Deprecated
    private AreaPatentInfoDTO queryOrigin(AreaPatentQueryDO query) {

        List<Map<String, Object>> originalInvent =
                statPatentNewManualMapper.queryAreaPatent1(query);

        List<Map<String, Object>> aRPOriginalInvent =
                statPatentNewManualMapper.queryAreaPatent2(query);

        Map<String, Object> originalInventProportion =
                statPatentNewManualMapper.queryAreaPatent3(query).get(0);

        List<Map<String, Object>> originalUnit =
                statPatentNewManualMapper.queryAreaPatent4(query);

        List<Map<String, Object>> pCTOriginalInvent =
                statPatentNewManualMapper.queryAreaPatent5(query);

        Map<String, Object> pCTOriginalInventProportion =
                statPatentNewManualMapper.queryAreaPatent6(query).get(0);

        List<Map<String, Object>> pCTOriginalUnit =
                statPatentNewManualMapper.queryAreaPatent7(query);

        Map<String, Object> originalInventProportionRemain =
                statPatentNewManualMapper.queryAreaPatent8(query).get(0);

        Map<String, Object> pCTOriginalInventProportionRemain =
                statPatentNewManualMapper.queryAreaPatent9(query).get(0);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> originalInventMap
                = this.bizData4MapByKeyLimit1(originalInvent, "nf");

        Map<String, Map<String, Object>> aRPOriginalInventMap
                = this.bizData4MapByKeyLimit1(aRPOriginalInvent, "nf");

        Map<String, Map<String, Object>> pCTOriginalInventMap
                = this.bizData4MapByKeyLimit1(pCTOriginalInvent, "nf");

        Map<String, List<Object>> inventAry = this.initInventAndARPAry();
        Map<String, List<Object>> aRPInventAry = this.initInventAndARPAry();
        Map<String, List<Object>> pCTInventAry = this.initPCTInventAry();
        yearList.stream().forEach(year -> {
            this.buildInventAndARpAry(inventAry, originalInventMap.get(year));
            this.buildInventAndARpAry(aRPInventAry, aRPOriginalInventMap.get(year));
            this.buildPCTInventAry(pCTInventAry, pCTOriginalInventMap.get(year));
        });

        Map<String, Object> originalInventProportionMap1 = Maps.newHashMap();
        originalInventProportionMap1.put("name", "所选申请");
        originalInventProportionMap1.put("value", originalInventProportion.get("sq"));
        Map<String, Object> originalInventProportionMap2 = Maps.newHashMap();
        originalInventProportionMap2.put("name", "其他申请");
        originalInventProportionMap2.put("value", ((Number) originalInventProportionRemain.get
                ("sq")).intValue() - ((Number) originalInventProportion.get("sq")).intValue());
        List<Map<String, Object>> inventproportion = Lists.newArrayList
                (originalInventProportionMap1, originalInventProportionMap2);

        Map<String, Object> pCTInventProportionMap1 = Maps.newHashMap();
        pCTInventProportionMap1.put("name", "所选新增");
        pCTInventProportionMap1.put("value", pCTOriginalInventProportion.get("xz"));
        Map<String, Object> pCTInventProportionMap2 = Maps.newHashMap();
        pCTInventProportionMap2.put("name", "其他新增");
        pCTInventProportionMap2.put("value", ((Number) pCTOriginalInventProportionRemain.get
                ("xz")).intValue() - ((Number) pCTOriginalInventProportion.get("xz")).intValue());
        List<Map<String, Object>> pCTInventProportion = Lists.newArrayList
                (pCTInventProportionMap1, pCTInventProportionMap2);

        List<Map<String, Object>> originalUnitAry = originalUnit.stream().map(map -> {
            Map<String, Object> data = Maps.newHashMap();
            data.put("name", map.get("jgmc"));
            data.put("value", map.get("sq"));
            return data;
        }).collect(Collectors.toList());

        List<Map<String, Object>> pCTOriginalUnitAry = pCTOriginalUnit.stream().map(map -> {
            Map<String, Object> data = Maps.newHashMap();
            data.put("name", map.get("jgmc"));
            data.put("value", map.get("xz"));
            return data;
        }).collect(Collectors.toList());

        AreaPatentInfoDTO info = new AreaPatentInfoDTO();
        info.setInventAry(inventAry);
        info.setaRPInventAry(aRPInventAry);
        info.setpCTInventAry(pCTInventAry);
        info.setInventproportion(inventproportion);
        info.setpCTInventProportion(pCTInventProportion);
        info.setOriginalUnitAry(originalUnitAry);
        info.setpCTOriginalUnitAry(pCTOriginalUnitAry);
        info.setYearList(yearList);
        info.setUpdateTime(AbstructServiceHelper.UPDATETIME);

        return info;
    }
}
