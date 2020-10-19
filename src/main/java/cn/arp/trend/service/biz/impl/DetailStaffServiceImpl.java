package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.*;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.repository.biz.manual.*;
import cn.arp.trend.service.biz.DetailStaffService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
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
 * Date:2020/10/12
 * Time:上午1:04
 **/
@Service
public class DetailStaffServiceImpl extends AbstructServiceHelper implements DetailStaffService {

    @Resource
    private StatArpStaffAgeManualMapper statArpStaffAgeManualMapper;

    @Resource
    private StatArpStaffEducationManualMapper statArpStaffEducationManualMapper;

    @Resource
    private StatArpStaffDutyManualMapper statArpStaffDutyManualMapper;

    @Resource
    private StatArpStaffManualMapper statArpStaffManualMapper;

    @Resource
    private StatArpStaffTechLevelManualMapper statArpStaffTechLevelManualMapper;

    @Resource
    private StatArpStaffPromotionManualMapper statArpStaffPromotionManualMapper;

    @Resource
    private StatArpStaffPostManualMapper statArpStaffPostManualMapper;

    @Override
    public AgeDistributionInfoDTO ageDistributionQuery(AgeDistributionQueryDO query) {
        List<Map<String, Object>> queryResult =
                statArpStaffAgeManualMapper.queryAgeDistribution(query);
        List<MapResultDTO> result = Lists.newArrayList();
        queryResult.stream().forEach(map -> {
            String nld = (String) map.get("nld");
            Integer rs = ((BigDecimal) map.get("rs")).intValue();
            result.add(new MapResultDTO<String, Integer>(nld, rs));
        });

        AgeDistributionInfoDTO ageDistributionInfo = new AgeDistributionInfoDTO();
        ageDistributionInfo.setDetail(result);
        ageDistributionInfo.setUpdateTime("2019年10月");
        ageDistributionInfo.setResultList(queryResult);
        return ageDistributionInfo;
    }

    @Override
    public ChildLevelDistributionInfoDTO childLevelDistributionQuery(ChildLevelDistributionQueryDO query) {
        List<Map<String, Object>> queryResult =
                statArpStaffEducationManualMapper.queryChildLevelDistribution(query);
        int totalNum = 0;
        List<Map> total = Lists.newArrayList();
        for(Map<String, Object> map : queryResult) {
            totalNum += ((Number) map.get("rs")).intValue();
            Map<String, Object> totalElement = Maps.newHashMap();
            totalElement.put("name", map.get("xl"));
            totalElement.put("value", ((Number) map.get("rs")).intValue());
            total.add(totalElement);
        }
        return new ChildLevelDistributionInfoDTO("2019年10月", total, totalNum);
    }

    @Override
    public IncreaseTrendInfoDTO increaseTrendQuery(IncreaseTrendQueryDO query) {
        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> resultMapByYear = Maps.newHashMap();
        yearList.stream().forEach(str -> {
            Map<String, Object> value = Maps.newHashMap();
            resultMapByYear.put(str, value);
        });

        List<Map<String, Object>> queryResult =
                statArpStaffDutyManualMapper.queryIncreaseTrend(query);

        int totalZYJSRY = 0;
        int totalXZGLRY = 0;
        int totalGR = 0;
        Object allTotal = null;

        for(Map<String, Object> map : queryResult) {
            String nf = (String) map.get("nf");
            if(resultMapByYear.containsKey(nf)) {
                Map<String, Object> value = resultMapByYear.get(nf);
                value.put("ZS", map.get("zs"));
                value.put("ZYJSRY", map.get("zyjsry"));
                value.put("XZGLRY", map.get("xzglry"));
                value.put("GR", map.get("gr"));

                allTotal = map.get("zs");
                totalZYJSRY += ((Number) map.get("zyjsry")).intValue();
                totalXZGLRY += ((Number) map.get("xzglry")).intValue();
                totalGR += ((Number) map.get("gr")).intValue();
            }
        }

        IncreaseTrendInfoDTO increaseTrendInfo = new IncreaseTrendInfoDTO();
        increaseTrendInfo.setYear(yearList);
        increaseTrendInfo.setUpdateTime("2019年10月");

        IncreaseTrendInfoDTO.IncreaseTrendDetailResultDTO detail =
                increaseTrendInfo.new IncreaseTrendDetailResultDTO();

        List ZS = Lists.newArrayList();
        List ZYJSRY = Lists.newArrayList();
        List XZGLRY = Lists.newArrayList();
        List GR = Lists.newArrayList();
        resultMapByYear.entrySet().stream().forEach(map -> {
            ZS.add(map.getValue().get("ZS"));
            ZYJSRY.add(map.getValue().get("ZYJSRY"));
            XZGLRY.add(map.getValue().get("XZGLRY"));
            GR.add(map.getValue().get("GR"));
        });
        Map<String, List> zsValue = Maps.newHashMap();
        zsValue.put("总数", ZS);
        Map<String, List> zyjsryValue = Maps.newHashMap();
        zsValue.put("专业技术人员", ZYJSRY);
        Map<String, List> xzglryValue = Maps.newHashMap();
        zsValue.put("行政管理人员", XZGLRY);
        Map<String, List> grValue = Maps.newHashMap();
        zsValue.put("工人", GR);

        List<Map<String, List>> typeList =
                Lists.newArrayList(zsValue, zyjsryValue, xzglryValue, grValue);

        detail.setZS(zsValue);
        detail.setZYJSRY(zyjsryValue);
        detail.setXZGLRY(xzglryValue);
        detail.setGR(grValue);
        detail.setAllTotal(allTotal);
        detail.setTotalGR(totalGR);
        detail.setTotalXZGLRY(totalXZGLRY);
        detail.setTotalZYJSRY(totalZYJSRY);
        detail.setTypeList(typeList);
        increaseTrendInfo.setDetail(detail);

        return increaseTrendInfo;
    }

    @Override
    public PostDistributionInfoDTO postDistributionQuery(PostDistributionQueryDO query) {

        List<Map<String, Object>> queryResult
                = statArpStaffPostManualMapper.queryPostDistribution(query);

        int KYRYRS = 0;
        int GLRYRS = 0;
        int ZCRYRS = 0;
        int QTRYRS = 0;

        if(queryResult.get(0) != null) {
            Map<String, Object> map = queryResult.get(0);
            KYRYRS = ((Number) map.get("ky")).intValue();
            GLRYRS = ((Number) map.get("gl")).intValue();
            ZCRYRS = ((Number) map.get("zc")).intValue();
            QTRYRS = ((Number) map.get("qt")).intValue();
        }

        int allTotal = KYRYRS + GLRYRS + ZCRYRS + QTRYRS;

        Map<String, Object> detail = Maps.newHashMap();

        Map<String, Object> QTRSMap = Maps.newHashMap();
        QTRSMap.put("name", "科研人员");
        QTRSMap.put("value", KYRYRS);

        Map<String, Object> ZJRSMap = Maps.newHashMap();
        ZJRSMap.put("name", "管理人员");
        ZJRSMap.put("value", GLRYRS);

        Map<String, Object> FGRSMap = Maps.newHashMap();
        FGRSMap.put("name", "支撑人员");
        FGRSMap.put("value", ZCRYRS);

        Map<String, Object> GJRSMap = Maps.newHashMap();
        GJRSMap.put("name", "其他人员");
        GJRSMap.put("value", QTRYRS);

        List<Map<String, Object>> disList = Lists.newArrayList(QTRSMap, ZJRSMap, FGRSMap, GJRSMap);
        detail.put("allTotal", allTotal);
        detail.put("disList", disList);

        return new PostDistributionInfoDTO(detail, "2019年10月");
    }

    @Override
    public PositionDistributionInfoDTO positionDistributionQuery(PositionDistributionQueryDO query) {
        List<Map<String, Object>> queryResult =
                statArpStaffTechLevelManualMapper.queryPositionDistribution(query);

        int GJRS = 0;
        int FGRS = 0;
        int ZJRS = 0;
        int QTRS = 0;

        if(queryResult.get(0) != null) {
            Map<String, Object> map = queryResult.get(0);
            GJRS = ((Number) map.get("gj")).intValue();
            FGRS = ((Number) map.get("fg")).intValue();
            ZJRS = ((Number) map.get("zj")).intValue();
            QTRS = ((Number) map.get("cj")).intValue();
        }

        int allTotal = GJRS + FGRS + ZJRS + QTRS;

        Map<String, Object> detail = Maps.newHashMap();

        Map<String, Object> QTRSMap = Maps.newHashMap();
        QTRSMap.put("name", "初级");
        QTRSMap.put("value", QTRS);

        Map<String, Object> ZJRSMap = Maps.newHashMap();
        ZJRSMap.put("name", "中级");
        ZJRSMap.put("value", ZJRS);

        Map<String, Object> FGRSMap = Maps.newHashMap();
        FGRSMap.put("name", "副高");
        FGRSMap.put("value", FGRS);

        Map<String, Object> GJRSMap = Maps.newHashMap();
        GJRSMap.put("name", "高级");
        GJRSMap.put("value", GJRS);

        List<Map<String, Object>> disList = Lists.newArrayList(QTRSMap, ZJRSMap, FGRSMap, GJRSMap);

        detail.put("allTotal", allTotal);
        detail.put("disList", disList);

        return new PositionDistributionInfoDTO(detail, "2019年10月");
    }

    @Override
    public DrRankInfoDTO drRankQuery(DrRankQueryDO query) {

        List<Map<String, Object>> queryResult =
                statArpStaffEducationManualMapper.queryDrRank(query);

        List<String> collegeNameList = queryResult.stream().map(map -> (String) map.get("jgmc"))
                .distinct().collect(Collectors.toList());

        Map<String, Map<String, Object>> mapResult = Maps.newHashMap();

        collegeNameList.stream().forEach(str -> {
            Map<String, Object> map = Maps.newHashMap();
            map.put("name", str);
            map.put("bs", null);
            map.put("qt", null);
            map.put("percent", null);
            mapResult.put(str, map);
        });

        queryResult.stream().forEach(map -> {
            if(map.get("jgmc") != null) {
                String jgmc = (String) map.get("jgmc");
                if(mapResult.containsKey(jgmc)) {
                    Map<String, Object> result = mapResult.get(jgmc);
                    result.put("bs", map.get("rs"));
                    result.put("qt", (
                            result.get("qt") == null ? 0 : (Number) result.get("qt")).intValue() +
                            ((Number) map.get("rs")).intValue());
                }
            }
        });

        mapResult.entrySet().stream().forEach(map -> {
            if(map.getValue().get("bs") != null) {
                int bs = ((Number)map.getValue().get("bs")).intValue();
                Map valueMap = map.getValue();
                if(bs == 0) {
                    valueMap.put("percent", 0);
                } else {
                    BigDecimal bBs = new BigDecimal(bs);
                    BigDecimal bQt = new BigDecimal(((Number)map.getValue().get("qt")).intValue());
                    valueMap.put("percent", bBs.divide(bQt, 2, BigDecimal.ROUND_HALF_UP));
                }
            }
        });

        List<Map<String, Object>> newlist = Lists.newArrayList();

        mapResult.entrySet().stream().forEach(map -> {
            newlist.add(map.getValue());
        });

        newlist.stream().sorted((map1, map2) -> ((Number) map2.get("bs")).intValue() - ((Number)
                map1.get("bs")).intValue()).collect(Collectors.toList());

        return new DrRankInfoDTO(newlist, "2019年10月");
    }

    @Override
    public PostAnalyzeInfoDTO postAnalyzeQuery(PostAnalyzeQueryDO query) {

        List<Map<String, Object>> queryResult =
                statArpStaffPromotionManualMapper.queryPostAnalyze(query);

        return new PostAnalyzeInfoDTO(queryResult, "2019年10月");
    }

    @Override
    public PersonTypeDistributionInfoDTO personTypeDistributionQuery(PersonTypeDistributionQueryDO query) {
        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        List<Map<String, Object>> queryResult =
                statArpStaffManualMapper.queryPersonTypeDistribution(query);

        int ZBRS = 0;
        int LDRS = 0;
        int LTXRS = 0;

        if(queryResult.get(0) != null) {
            Map<String, Object> map = queryResult.get(0);
            ZBRS = ((Number) map.get("zb")).intValue();
            LDRS = ((Number) map.get("ld")).intValue();
            LTXRS = ((Number) map.get("tx")).intValue();
        }

        int allTotal = ZBRS + LDRS + LTXRS;

        Map<String, Object> detail = Maps.newHashMap();

        Map<String, Object> LTXRSMap = Maps.newHashMap();
        LTXRSMap.put("name", "离退休");
        LTXRSMap.put("value", LTXRS);

        Map<String, Object> LDRSMap = Maps.newHashMap();
        LDRSMap.put("name", "流动");
        LDRSMap.put("value", LDRS);

        Map<String, Object> ZBRSMap = Maps.newHashMap();
        ZBRSMap.put("name", "在编");
        ZBRSMap.put("value", ZBRS);

        List<Map<String, Object>> disList = Lists.newArrayList(LTXRSMap, LDRSMap, ZBRSMap);

        detail.put("allTotal", allTotal);
        detail.put("disList", disList);

        return new PersonTypeDistributionInfoDTO(detail, "2019年10月", queryResult.get(0));
    }
}
