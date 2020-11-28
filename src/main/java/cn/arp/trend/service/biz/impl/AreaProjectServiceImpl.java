package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AreaProjectNsfcDistQueryDO;
import cn.arp.trend.data.model.DO.AreaProjectNsfcTrendQueryDO;
import cn.arp.trend.data.model.DO.AreaProjectQueryDO;
import cn.arp.trend.data.model.DTO.AreaProjectNsfcDistInfoDTO;
import cn.arp.trend.data.model.DTO.AreaProjectNsfcTrendInfoDTO;
import cn.arp.trend.data.model.DTO.AreaProjectXdzxInfoDTO;
import cn.arp.trend.data.model.DTO.AreaProjectZdyfInfoDTO;
import cn.arp.trend.repository.biz.manual.RefOrgTypeManualMapper;
import cn.arp.trend.service.biz.AreaProjectService;
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
 * Date:2020/11/3
 * Time:下午4:55
 **/
@Service
public class AreaProjectServiceImpl extends AbstructServiceHelper implements AreaProjectService {

    @Resource
    private RefOrgTypeManualMapper refOrgTypeManualMapper;

    @Override
    public AreaProjectXdzxInfoDTO xdzxQuery(AreaProjectQueryDO query) {

        List<Map<String, Object>> proData =
                refOrgTypeManualMapper.queryAreaProjectXdzx1(query);

        List<Map<String, Object>> unitData =
                refOrgTypeManualMapper.queryAreaProjectXdzx3(query);

        Map<String, Object> proSelect =
                refOrgTypeManualMapper.queryAreaProjectXdzx2(query).get(0);

        Map<String, Object> proAll =
                refOrgTypeManualMapper.queryAreaProjectXdzx4(query).get(0);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        List<Integer> taskNumData = Lists.newArrayList();
        List<Integer> expenditureNumData = Lists.newArrayList();
        List<Map<String, Object>> unitTaskData = Lists.newArrayList();
        List<Map<String, Object>> unitExpenditureData = Lists.newArrayList();

        Map<String, Map<String, Object>> proDataGroupByNf = this.bizData4MapByKeyLimit1(proData,
                "nf");

        for(int i = 0; i < yearList.size(); i++) {
            String yearsEle = yearList.get(i);
            Map<String, Object> targetObj = proDataGroupByNf.get(yearsEle);
            taskNumData.add(targetObj == null ? 0 : ((Number) targetObj.get("xm")).intValue());
            expenditureNumData.add(targetObj == null ? 0 : ((Number) targetObj.get("jf")).intValue());
        }

        unitData.stream().forEach(map -> {
            Map<String, Object> unitTaskDataE = Maps.newHashMap();
            unitTaskDataE.put("name", map.get("jgmc"));
            unitTaskDataE.put("value", map.get("xm"));
            unitTaskData.add(unitTaskDataE);

            Map<String, Object> unitExpenditureDataE = Maps.newHashMap();
            unitExpenditureDataE.put("name", map.get("jgmc"));
            unitExpenditureDataE.put("value", map.get("jf"));
            unitExpenditureData.add(unitExpenditureDataE);
        });

        List<Map<String, Object>> sortedUnitTaskData  = unitTaskData.stream().sorted(Comparator
                .comparingInt(map -> ((Number) map.get("value"))
                .intValue() * -1)).collect(Collectors.toList());

        List<Map<String, Object>> sortedUnitExpenditureData  = unitExpenditureData.stream()
                .sorted(Comparator.comparingInt(map -> ((Number) map.get("value"))
                        .intValue() * -1)).collect(Collectors.toList());

        Map<String, Object> tasktotal = Maps.newHashMap();
        tasktotal.put("all", proAll.get("xm"));
        tasktotal.put("select", proSelect.get("xm"));

        Map<String, Object> expenditureTotal = Maps.newHashMap();
        expenditureTotal.put("all", proAll.get("jf"));
        expenditureTotal.put("select", proSelect.get("jf"));

        AreaProjectXdzxInfoDTO info = new AreaProjectXdzxInfoDTO();
        info.setUpdateTime(AbstructServiceHelper.UPDATETIME);
        info.setYearList(yearList);
        info.setTaskNumData(taskNumData);
        info.setExpenditureNumData(expenditureNumData);
        info.setUnitTaskData(sortedUnitTaskData);
        info.setUnitExpenditureData(sortedUnitExpenditureData);
        info.setTaskTotal(tasktotal);
        info.setExpenditureTotal(expenditureTotal);

        return info;
    }

    @Override
    public AreaProjectZdyfInfoDTO zdyfQuery(AreaProjectQueryDO query) {

        List<Map<String, Object>> proData =
                refOrgTypeManualMapper.queryAreaProjectZdyf1(query);

        List<Map<String, Object>> unitData =
                refOrgTypeManualMapper.queryAreaProjectZdyf3(query);

        Map<String, Object> proSelect =
                refOrgTypeManualMapper.queryAreaProjectZdyf2(query).get(0);

        Map<String, Object> proAll =
                refOrgTypeManualMapper.queryAreaProjectZdyf4(query).get(0);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        List<Integer> taskNumData = Lists.newArrayList();
        List<Integer> expenditureNumData = Lists.newArrayList();
        List<Map<String, Object>> unitTaskData = Lists.newArrayList();
        List<Map<String, Object>> unitExpenditureData = Lists.newArrayList();

        Map<String, Map<String, Object>> proDataGroupByNf = this.bizData4MapByKeyLimit1(proData,
                "nf");

        for(int i = 0; i < yearList.size(); i++) {
            String yearsEle = yearList.get(i);
            Map<String, Object> targetObj = proDataGroupByNf.get(yearsEle);
            taskNumData.add(targetObj == null ? 0 : ((Number) targetObj.get("xm")).intValue());
            expenditureNumData.add(targetObj == null ? 0 : ((Number) targetObj.get("jf")).intValue());
        }

        unitData.stream().forEach(map -> {
            Map<String, Object> unitTaskDataE = Maps.newHashMap();
            unitTaskDataE.put("name", map.get("jgmc"));
            unitTaskDataE.put("value", map.get("xm"));
            unitTaskData.add(unitTaskDataE);

            Map<String, Object> unitExpenditureDataE = Maps.newHashMap();
            unitExpenditureDataE.put("name", map.get("jgmc"));
            unitExpenditureDataE.put("value", map.get("jf"));
            unitExpenditureData.add(unitExpenditureDataE);
        });

        List<Map<String, Object>> sortedUnitTaskData  = unitTaskData.stream().sorted(Comparator
                .comparingInt(map -> ((Number) map.get("value"))
                        .intValue() * -1)).collect(Collectors.toList());

        List<Map<String, Object>> sortedUnitExpenditureData  = unitExpenditureData.stream()
                .sorted(Comparator.comparingInt(map -> ((Number) map.get("value"))
                        .intValue() * -1)).collect(Collectors.toList());

        Map<String, Object> tasktotal = Maps.newHashMap();
        tasktotal.put("all", proAll == null ? 0 : proAll.get("xm"));
        tasktotal.put("select", proSelect == null ? 0 : proSelect.get("xm"));

        Map<String, Object> expenditureTotal = Maps.newHashMap();
        expenditureTotal.put("all", proAll == null ? 0 : proAll.get("jf"));
        expenditureTotal.put("select", proSelect == null ? 0 : proSelect.get("jf"));

        AreaProjectZdyfInfoDTO info = new AreaProjectZdyfInfoDTO();
        info.setUpdateTime(AbstructServiceHelper.UPDATETIME);
        info.setYearList(yearList);
        info.setTaskNumData(taskNumData);
        info.setExpenditureNumData(expenditureNumData);
        info.setUnitTaskData(sortedUnitTaskData);
        info.setUnitExpenditureData(sortedUnitExpenditureData);
        info.setTaskTotal(tasktotal);
        info.setExpenditureTotal(expenditureTotal);

        return info;
    }

    @Override
    public AreaProjectNsfcTrendInfoDTO nsfcTrendQuery(AreaProjectNsfcTrendQueryDO query) {

        List<Map<String, Object>> proData =
                refOrgTypeManualMapper.queryAreaProjectNsfcTrend1(query);

        List<Map<String, Object>> unitData =
                refOrgTypeManualMapper.queryAreaProjectNsfcTrend2(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> proDataGroupByNf = this.bizData4MapByKeyLimit1(proData,
                "nf");
        Map<String, Map<String, Object>> unitDataGroupByNf = this.bizData4MapByKeyLimit1(unitData,
                "nf");

        List<Integer> projectNumData = Lists.newArrayList();
        List<Integer> expenditureNumData = Lists.newArrayList();
        List<Integer> aRPProjectNumData = Lists.newArrayList();
        List<Integer> aRPExpenditureNumData = Lists.newArrayList();;

        for(int i = 0; i < yearList.size(); i++) {
            String yearsEle = yearList.get(i);

            Map<String, Object> targetObj1 = proDataGroupByNf.get(yearsEle);
            projectNumData.add(targetObj1 == null ? 0 : ((Number) targetObj1.get("xm")).intValue());
            expenditureNumData.add(targetObj1 == null ? 0 : ((Number) targetObj1.get("jf"))
                    .intValue());

            Map<String, Object> targetObj2 = unitDataGroupByNf.get(yearsEle);
            aRPProjectNumData.add(targetObj2 == null ? 0 : ((Number) targetObj2.get("xm")).intValue());
            aRPExpenditureNumData.add(targetObj2 == null ? 0 : ((Number) targetObj2.get("jf"))
                    .intValue());
        }

        AreaProjectNsfcTrendInfoDTO info = new AreaProjectNsfcTrendInfoDTO();
        info.setUpdateTime(AbstructServiceHelper.UPDATETIME);
        info.setYearList(yearList);
        info.setProjectNumData(projectNumData);
        info.setExpenditureNumData(expenditureNumData);
        info.setARPProjectNumData(aRPProjectNumData);
        info.setARPExpenditureNumData(aRPExpenditureNumData);
        info.setResultArray(Lists.newArrayList(proData, unitData));

        return info;
    }

    @Override
    public AreaProjectNsfcDistInfoDTO nsfcDistQuery(AreaProjectNsfcDistQueryDO query) {

        List<Map<String, Object>> unitData =
                refOrgTypeManualMapper.queryAreaProjectNsfcDist1(query);

        List<Map<String, Object>> proSelectList =
                refOrgTypeManualMapper.queryAreaProjectNsfcDist2(query);
        Map<String, Object> proSelect = proSelectList.get(0);

        List<Map<String, Object>> proAllList =
                refOrgTypeManualMapper.queryAreaProjectNsfcDist3(query);
        Map<String, Object> proAll = proAllList.get(0);

        List<Map<String, Object>> unitProjectData = Lists.newArrayList();
        List<Map<String, Object>> unitExpenditureData = Lists.newArrayList();

        unitData.stream().forEach(map -> {
            Map<String, Object> unitProjectDataE = Maps.newHashMap();
            unitProjectDataE.put("name", map.get("jgmc"));
            unitProjectDataE.put("value", map.get("xm"));
            unitProjectData.add(unitProjectDataE);

            Map<String, Object> unitExpenditureDataE = Maps.newHashMap();
            unitExpenditureDataE.put("name", map.get("jgmc"));
            unitExpenditureDataE.put("value", map.get("jf"));
            unitExpenditureData.add(unitExpenditureDataE);
        });

        List<Map<String, Object>> sortedUnitProjectData  = unitProjectData.stream().sorted(Comparator
                .comparingInt(map -> ((Number) map.get("value"))
                        .intValue() * -1)).collect(Collectors.toList());

        List<Map<String, Object>> sortedUnitExpenditureData  = unitExpenditureData.stream()
                .sorted(Comparator.comparingInt(map -> ((Number) map.get("value"))
                        .intValue() * -1)).collect(Collectors.toList());

        Map<String, Object> projectTotal = Maps.newHashMap();
        projectTotal.put("all", proAll == null ? 0 : proAll.get("xm"));
        projectTotal.put("select", proSelect == null ? 0 : proSelect.get("xm"));

        Map<String, Object> expenditureTotal = Maps.newHashMap();
        expenditureTotal.put("all", proAll == null ? 0 : proAll.get("jf"));
        expenditureTotal.put("select", proSelect == null ? 0 : proSelect.get("jf"));

        AreaProjectNsfcDistInfoDTO info = new AreaProjectNsfcDistInfoDTO();
        info.setUpdateTime(AbstructServiceHelper.UPDATETIME);
        info.setResultArray(Lists.newArrayList(unitData, proSelectList, proAllList));
        info.setUnitProjectData(unitProjectData);
        info.setUnitExpenditureData(unitExpenditureData);
        info.setProjectTotal(projectTotal);
        info.setExpenditureTotal(expenditureTotal);

        return info;
    }
}
