package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AreaHrQueryDO;
import cn.arp.trend.data.model.DO.IncreaseTrendQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.repository.biz.manual.*;
import cn.arp.trend.service.biz.AreaHrService;
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
 * Date:2020/10/31
 * Time:上午11:28
 **/
@Service
public class AreaHrServiceImpl extends AbstructServiceHelper implements AreaHrService {

    @Resource
    private StatArpStaffDutyManualMapper statArpStaffDutyManualMapper;

    @Resource
    private StatArpStaffEducationManualMapper statArpStaffEducationManualMapper;

    @Resource
    private CasAcademicianCaeChinaManualMapper casAcademicianCaeChinaManualMapper;

    @Resource
    private CasAcademicianChinaManualMapper casAcademicianChinaManualMapper;

    @Resource
    private StatCasStaffQianzaiyearcountBydanweiManualMapper statCasStaffQianzaiyearcountBydanweiManualMapper;

    @Override
    public AreaHrStaffTrendInfoDTO areaStaffTrendQuery(AreaHrQueryDO query) {

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> resultMapByYear = Maps.newHashMap();
        yearList.stream().forEach(str -> {
            Map<String, Object> value = Maps.newHashMap();
            resultMapByYear.put(str, value);
        });

        IncreaseTrendQueryDO increaseTrendQuery = new IncreaseTrendQueryDO(
                query.getStartYear(),
                query.getEndYear(),
                query.getUnitIdAry()
        );

        List<Map<String, Object>> queryResult =
                statArpStaffDutyManualMapper.queryIncreaseTrend(increaseTrendQuery);

        Map<String, Map<String, Object>> doctorOriginalSchoolMap
                = this.bizData4MapByKeyLimit1(queryResult, "nf");

        List<Object> sumAry = Lists.newArrayList();
        List<Object> majorAry = Lists.newArrayList();
        List<Object> administrativeAry = Lists.newArrayList();
        List<Object> workerAry = Lists.newArrayList();

        yearList.stream().forEach(year -> {
            if(doctorOriginalSchoolMap.containsKey(year)) {
                Map<String, Object> bizData = doctorOriginalSchoolMap.get(year);
                sumAry.add(bizData.get("zs"));
                majorAry.add(bizData.get("zyjsry"));
                administrativeAry.add(bizData.get("xzglry"));
                workerAry.add(bizData.get("gr"));
            } else {
                sumAry.add(null);
                majorAry.add(null);
                administrativeAry.add(null);
                workerAry.add(null);
            }
        });

        return new AreaHrStaffTrendInfoDTO(
                yearList,
                sumAry,
                majorAry,
                administrativeAry,
                workerAry,
                AbstructServiceHelper.UPDATETIME
        );
    }

    @Override
    public AreaHrStaffDistInfoDTO areaStaffDistQuery(AreaHrQueryDO query) {

        List<Map<String, Object>> queryResult1 =
                statArpStaffEducationManualMapper.queryHrStaffDist1(query);

        List<Map<String, Object>> queryResult2 =
                statArpStaffEducationManualMapper.queryHrStaffDist1(query);

        List<Map<String, Object>> queryResult3 =
                statArpStaffEducationManualMapper.queryHrStaffDist1(query);

        List<Map<String, Object>> queryResult4 =
                statArpStaffEducationManualMapper.queryHrStaffDist1(query);

        List<Map<String, Object>> queryResult5 =
                statArpStaffEducationManualMapper.queryHrStaffDist5(query);

        List<Map<String, Object>> queryResult6 =
                statArpStaffEducationManualMapper.queryHrStaffDist1(query);

        List<Map<String, Object>> unitAry = queryResult1.stream().map(map -> {
            Map<String, Object> bizMap = Maps.newHashMap();
            bizMap.put("name", map.get("jgmc"));
            bizMap.put("value", map.get("rs"));
            return bizMap;
        }).collect(Collectors.toList());

        Map<String, List<Map<String, Object>>> entiretyAry = Maps.newHashMap();
        if(!queryResult2.isEmpty()) {
            entiretyAry = this.buildEntiretyAry(queryResult2.get(0));
        }

        Map<String, List<Map<String, Object>>> positionAry = Maps.newHashMap();
        if(!queryResult3.isEmpty()) {
            positionAry = this.buildPositionAry(queryResult3.get(0));
        }

        List<Object> ageAryName = Lists.newArrayList();
        List<Object> ageAryValue = Lists.newArrayList();
        queryResult4.stream().forEach(map -> {
            ageAryName.add(map.get("nld"));
            ageAryValue.add(map.get("rs"));
        });
        Map<String, List<Object>> ageAry = Maps.newHashMap();
        ageAry.put("age_ary_name", ageAryName);
        ageAry.put("age_ary_value", ageAryValue);

        List<Object> educationAryName = Lists.newArrayList();
        List<Object> educationAryValue = Lists.newArrayList();
        queryResult5.stream().forEach(map -> {
            Map<String, Object> bizNameMap = Maps.newHashMap();
            bizNameMap.put("name", map.get("xl"));
            bizNameMap.put("icon", "roundRect");
            educationAryName.add(bizNameMap);

            Map<String, Object> bizValueMap = Maps.newHashMap();
            bizValueMap.put("name", map.get("xl"));
            bizValueMap.put("value", map.get("rs"));
            educationAryValue.add(bizValueMap);
        });
        Map<String, List<Object>> educationAry = Maps.newHashMap();
        educationAry.put("ary_name", educationAryName);
        educationAry.put("ary_value", educationAryValue);

        Map<String, List<Map<String, Object>>> titleAry = Maps.newHashMap();
        if(!queryResult6.isEmpty()) {
            titleAry = this.buildTitleAry(queryResult6.get(0));
        }

        AreaHrStaffDistInfoDTO info = new AreaHrStaffDistInfoDTO();
        info.setUpdateTime(AbstructServiceHelper.UPDATETIME);
        info.setResultArray(Lists.newArrayList(queryResult1, queryResult2, queryResult3,
                queryResult4, queryResult5, queryResult6));
        info.setUnitAry(unitAry);
        info.setAgeAry(ageAry);
        info.setEducationAry(educationAry);
        info.setEntiretyAry(entiretyAry);
        info.setPositionAry(positionAry);
        info.setTitleAry(titleAry);

        return info;
    }

    @Override
    public AreaHrAcadCaeTrendInfoDTO areaAcadCaeTrendQuery(AreaHrQueryDO query) {

        List<Map<String, Object>> queryResult =
                casAcademicianCaeChinaManualMapper.queryHrAcadCaeTrend(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> bizDataMap
                = this.bizData4MapByKeyLast(queryResult, "dxnf");

        List<Object> dataList = Lists.newArrayList();
        yearList.stream().forEach(year -> {
            dataList.add(bizDataMap.get(year) == null ? null : bizDataMap.get(year).get("dxrs"));
        });

        return new AreaHrAcadCaeTrendInfoDTO(
                yearList, dataList, AbstructServiceHelper.UPDATETIME);
    }

    @Override
    public AreaHrAcadCasTrendInfoDTO areaAcadCasTrendQuery(AreaHrQueryDO query) {

        List<Map<String, Object>> queryResult =
                casAcademicianChinaManualMapper.queryHrAcadCasTrend(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> bizDataMap
                = this.bizData4MapByKeyLast(queryResult, "dxnf");

        List<Object> dataList = Lists.newArrayList();
        yearList.stream().forEach(year -> {
            dataList.add(bizDataMap.get(year) == null ? null : bizDataMap.get(year).get("dxrs"));
        });

        return new AreaHrAcadCasTrendInfoDTO(
                yearList, dataList, AbstructServiceHelper.UPDATETIME, queryResult);
    }

    @Override
    public AreaHrAcadCasDistInfoDTO areaAcadCasDistQuery(AreaHrQueryDO query) {

        List<Map<String, Object>> queryResult1 =
                casAcademicianChinaManualMapper.queryHrAcadCasDist1(query);

        List<Map<String, Object>> queryResult2 =
                casAcademicianChinaManualMapper.queryHrAcadCasDist2(query);

        List<Map<String, Object>> queryResult3 =
                casAcademicianChinaManualMapper.queryHrAcadCasDist3(query);

        Map<String, Object> proportion = Maps.newHashMap();
        proportion.put("max", queryResult2.get(0).get("rs"));
        proportion.put("min", queryResult1.get(0).get("rs"));

        return new AreaHrAcadCasDistInfoDTO(
                proportion,
                queryResult3,
                AbstructServiceHelper.UPDATETIME);
    }

    @Override
    public AreaHrYoungEliteInfoDTO areaYoungEliteQuery(AreaHrQueryDO query) {

        List<Map<String, Object>> queryResult1 =
                statCasStaffQianzaiyearcountBydanweiManualMapper.queryYoungElite1(query);

        List<Map<String, Object>> queryResult2 =
                statCasStaffQianzaiyearcountBydanweiManualMapper.queryYoungElite2(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> bizDataMap
                = this.bizData4MapByKeyLimit1(queryResult1, "nf");

        List<Object> rsYoungList = Lists.newArrayList();
        List<Object> rsStaffList = Lists.newArrayList();
        yearList.stream().forEach(year -> {
            rsYoungList.add(bizDataMap.get(year) == null ? null : bizDataMap.get(year).get("rs_young"));
            rsStaffList.add(bizDataMap.get(year) == null ? null : bizDataMap.get(year).get("rs_staff"));
        });

        return new AreaHrYoungEliteInfoDTO(
                yearList, rsYoungList, rsStaffList, queryResult2);
    }

    @Override
    public AreaHrAcadCaeDistInfoDTO areaAcadCaeDistQuery(AreaHrQueryDO query) {

        List<Map<String, Object>> queryResult1 =
                casAcademicianCaeChinaManualMapper.queryHrAcadCaeDist1(query);

        List<Map<String, Object>> queryResult2 =
                casAcademicianCaeChinaManualMapper.queryHrAcadCaeDist2(query);

        List<Map<String, Object>> queryResult3 =
                casAcademicianCaeChinaManualMapper.queryHrAcadCaeDist3(query);

        Map<String, Object> proportion = Maps.newHashMap();
        proportion.put("max", queryResult2.get(0).get("rs"));
        proportion.put("min", queryResult1.get(0).get("rs"));

        return new AreaHrAcadCaeDistInfoDTO(
                proportion,
                queryResult3,
                AbstructServiceHelper.UPDATETIME);
    }

    private Map<String, List<Map<String, Object>>> buildEntiretyAry(Map<String, Object> resultMap) {
        Map<String, Object> aryNameValue1 = Maps.newHashMap();
        aryNameValue1.put("name", "在编人员人数");
        aryNameValue1.put("icon", "roundRect");
        Map<String, Object> aryNameValue2 = Maps.newHashMap();
        aryNameValue2.put("name", "流动人员人数");
        aryNameValue2.put("icon", "roundRect");
        Map<String, Object> aryNameValue3 = Maps.newHashMap();
        aryNameValue3.put("name", "退休人员人数");
        aryNameValue3.put("icon", "roundRect");
        List<Map<String, Object>> aryName = Lists.newArrayList(
                aryNameValue1, aryNameValue2, aryNameValue3);

        Map<String, Object> aryValueValue1 = Maps.newHashMap();
        aryValueValue1.put("name", "在编人员人数");
        aryValueValue1.put("value", resultMap.get("zb"));
        Map<String, Object> aryValueValue2 = Maps.newHashMap();
        aryValueValue2.put("name", "流动人员人数");
        aryValueValue2.put("value", resultMap.get("ld"));
        Map<String, Object> aryValueValue3 = Maps.newHashMap();
        aryValueValue3.put("name", "退休人员人数");
        aryValueValue3.put("value", resultMap.get("tx"));
        List<Map<String, Object>> aryValue = Lists.newArrayList(
                aryValueValue1, aryValueValue2, aryValueValue3);

        Map<String, List<Map<String, Object>>> entiretyAry = Maps.newHashMap();
        entiretyAry.put("ary_name", aryName);
        entiretyAry.put("ary_value", aryValue);

        return entiretyAry;
    }

    private Map<String, List<Map<String, Object>>> buildPositionAry(Map<String, Object> resultMap) {
        Map<String, Object> aryNameValue1 = Maps.newHashMap();
        aryNameValue1.put("name", "科研人员人数");
        aryNameValue1.put("icon", "roundRect");
        Map<String, Object> aryNameValue2 = Maps.newHashMap();
        aryNameValue2.put("name", "管理人员人数");
        aryNameValue2.put("icon", "roundRect");
        Map<String, Object> aryNameValue3 = Maps.newHashMap();
        aryNameValue3.put("name", "支撑人员人数");
        aryNameValue3.put("icon", "roundRect");
        Map<String, Object> aryNameValue4 = Maps.newHashMap();
        aryNameValue4.put("name", "其它人员人数");
        aryNameValue4.put("icon", "roundRect");
        List<Map<String, Object>> aryName = Lists.newArrayList(
                aryNameValue1, aryNameValue2, aryNameValue3, aryNameValue4);

        Map<String, Object> aryValueValue1 = Maps.newHashMap();
        aryValueValue1.put("name", "科研人员人数");
        aryValueValue1.put("value", resultMap.get("ky"));
        Map<String, Object> aryValueValue2 = Maps.newHashMap();
        aryValueValue2.put("name", "管理人员人数");
        aryValueValue2.put("value", resultMap.get("gl"));
        Map<String, Object> aryValueValue3 = Maps.newHashMap();
        aryValueValue3.put("name", "支撑人员人数");
        aryValueValue3.put("value", resultMap.get("zc"));
        Map<String, Object> aryValueValue4 = Maps.newHashMap();
        aryValueValue4.put("name", "其它人员人数");
        aryValueValue4.put("value", resultMap.get("qt"));
        List<Map<String, Object>> aryValue = Lists.newArrayList(
                aryValueValue1, aryValueValue2, aryValueValue3);

        Map<String, List<Map<String, Object>>> positionAry = Maps.newHashMap();
        positionAry.put("ary_name", aryName);
        positionAry.put("ary_value", aryValue);

        return positionAry;
    }

    private Map<String, List<Map<String, Object>>> buildTitleAry(Map<String, Object> resultMap) {
        Map<String, Object> aryNameValue1 = Maps.newHashMap();
        aryNameValue1.put("name", "高级人数");
        aryNameValue1.put("icon", "roundRect");
        Map<String, Object> aryNameValue2 = Maps.newHashMap();
        aryNameValue2.put("name", "高级人数");
        aryNameValue2.put("icon", "roundRect");
        Map<String, Object> aryNameValue3 = Maps.newHashMap();
        aryNameValue3.put("name", "高级人数");
        aryNameValue3.put("icon", "roundRect");
        Map<String, Object> aryNameValue4 = Maps.newHashMap();
        aryNameValue4.put("name", "高级人数");
        aryNameValue4.put("icon", "roundRect");
        List<Map<String, Object>> aryName = Lists.newArrayList(
                aryNameValue1, aryNameValue2, aryNameValue3, aryNameValue4);

        Map<String, Object> aryValueValue1 = Maps.newHashMap();
        aryValueValue1.put("name", "高级人数");
        aryValueValue1.put("value", resultMap.get("gj"));
        Map<String, Object> aryValueValue2 = Maps.newHashMap();
        aryValueValue2.put("name", "高级人数");
        aryValueValue2.put("value", resultMap.get("fg"));
        Map<String, Object> aryValueValue3 = Maps.newHashMap();
        aryValueValue3.put("name", "高级人数");
        aryValueValue3.put("value", resultMap.get("zj"));
        Map<String, Object> aryValueValue4 = Maps.newHashMap();
        aryValueValue4.put("name", "高级人数");
        aryValueValue4.put("value", resultMap.get("cj"));
        List<Map<String, Object>> aryValue = Lists.newArrayList(
                aryValueValue1, aryValueValue2, aryValueValue3);

        Map<String, List<Map<String, Object>>> titleAry = Maps.newHashMap();
        titleAry.put("ary_name", aryName);
        titleAry.put("ary_value", aryValue);

        return titleAry;
    }
}
