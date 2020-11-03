package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.DetailAwardDetailQueryDO;
import cn.arp.trend.data.model.DO.DetailAwardDistributionQueryDO;
import cn.arp.trend.data.model.DO.DetailAwardTrendQueryDO;
import cn.arp.trend.data.model.DTO.AreaAwardDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.DetailAwardDetailInfoDTO;
import cn.arp.trend.data.model.DTO.DetailAwardDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.DetailAwardTrendInfoDTO;
import cn.arp.trend.repository.biz.manual.CasChinaAward10YearFinalManualMapper;
import cn.arp.trend.repository.biz.manual.CasHlhlAwardWinnerManualMapper;
import cn.arp.trend.repository.biz.manual.RefOrgTypeManualMapper;
import cn.arp.trend.service.biz.DetailAwardService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/25
 * Time:下午12:15
 **/
@Service
public class DetailAwardServiceImpl extends AbstructServiceHelper implements DetailAwardService {

    @Resource
    private CasHlhlAwardWinnerManualMapper casHlhlAwardWinnerManualMapper;

    @Resource
    private CasChinaAward10YearFinalManualMapper casChinaAward10YearFinalManualMapper;

    @Resource
    private RefOrgTypeManualMapper refOrgTypeManualMapper;

    @Override
    public DetailAwardTrendInfoDTO trendQuery(DetailAwardTrendQueryDO query) {

        List<Map<String, Object>> stateOriginalTendency
                = casChinaAward10YearFinalManualMapper.queryAward1(query);

        List<Map<String, Object>> heOriginalTendency
                = casHlhlAwardWinnerManualMapper.queryAward2(query);

        List<Map<String, Object>> futureOriginalTendency
                = casHlhlAwardWinnerManualMapper.queryAward3(query);

        List<Map<String, Object>> outstandingOriginalTendency
                = casHlhlAwardWinnerManualMapper.queryAward4(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Integer> stateOriginalTendencyMap = this.calculateNum(stateOriginalTendency);
        Map<String, Integer> heOriginalTendencyMap = this.calculateNum(heOriginalTendency);
        Map<String, Integer> futureOriginalTendencyMap = this.calculateNum(futureOriginalTendency);
        Map<String, Integer> outstandingOriginalTendencyMap = this.calculateNum(outstandingOriginalTendency);

        List<Integer> stateTendency = Lists.newArrayList();
        List<Integer> elseTendency = Lists.newArrayList();
        List<Integer> sumTendency = Lists.newArrayList();

        yearList.stream().forEach(year -> {
            Integer stateShow = stateOriginalTendencyMap.get(year);
            Integer heOriginalTendencyShow = heOriginalTendencyMap.get(year);
            Integer futureOriginalTendencyShow = futureOriginalTendencyMap.get(year);
            Integer outstandingOriginalTendencyShow = outstandingOriginalTendencyMap.get(year);

            Integer num = null;
            if(heOriginalTendencyShow != null || futureOriginalTendencyShow != null ||
                    outstandingOriginalTendencyShow != null) {
                num = (heOriginalTendencyShow == null ? 0 : heOriginalTendencyShow)
                        + (futureOriginalTendencyShow == null ? 0 : futureOriginalTendencyShow)
                        + (outstandingOriginalTendencyShow == null ? 0 : outstandingOriginalTendencyShow);
            }

            Integer amount = null;
            if(num != null || stateShow != null) {
                amount = (num == null ? 0 : num) + (stateShow == null ? 0 : stateShow);
            }

            stateTendency.add(stateShow);
            elseTendency.add(num);
            sumTendency.add(amount);
        });

        return new DetailAwardTrendInfoDTO(
                AbstructServiceHelper.UPDATETIME,
                yearList,
                stateTendency,
                elseTendency,
                sumTendency
        );
    }

    @Override
    public DetailAwardDetailInfoDTO detailQuery(DetailAwardDetailQueryDO query) {

        List<Map<String, Object>> heOriginalPie
                = casHlhlAwardWinnerManualMapper.queryAwardDetail1(query);

        List<Map<String, Object>> futureOriginalPie
                = casHlhlAwardWinnerManualMapper.queryAwardDetail2(query);

        List<Map<String, Object>> outstandingOriginalPie
                = casHlhlAwardWinnerManualMapper.queryAwardDetail3(query);

        List<Map<String, Object>> progressOriginalPie
                = casHlhlAwardWinnerManualMapper.queryAwardDetail4(query, "国家科学技术进步奖");

        List<Map<String, Object>> natureOriginalPie
                = casHlhlAwardWinnerManualMapper.queryAwardDetail4(query, "国家自然科学奖");

        List<Map<String, Object>> inventOriginalPie
                = casHlhlAwardWinnerManualMapper.queryAwardDetail4(query, "国家技术发明奖");

        List<Map<String, Object>> highestOriginalPie
                = casHlhlAwardWinnerManualMapper.queryAwardDetail7(query);

        List<Map<String, Object>> hePie = this.setPeiAry(heOriginalPie, "hjjx");
        List<Map<String, Object>> futurePie = this.setPeiAry(futureOriginalPie, "hjlb");
        List<Map<String, Object>> outstandingPie = this.setPeiAry(outstandingOriginalPie, "hjlb");
        List<Map<String, Object>> progressPie = this.setPeiAry(progressOriginalPie, "jxlb");
        List<Map<String, Object>> naturePie = this.setPeiAry(natureOriginalPie, "jxlb");
        List<Map<String, Object>> inventPie = this.setPeiAry(inventOriginalPie, "jxlb");

        List<Map<String, Object>> highestList = Lists.newArrayList();
        highestOriginalPie.stream().forEach(map -> {
            List listAry = Lists.newArrayList();
            if (map.get("first_wcdw_std") != null) {
                Map<String, Object> aryMap = Maps.newHashMap();
                aryMap.put("unit", map.get("first_wcdw_std"));
                aryMap.put("wcdw", "第一完成单位");
                listAry.add(aryMap);
            }
            if (map.get("second_wcdw_std") != null) {
                Map<String, Object> aryMap = Maps.newHashMap();
                aryMap.put("unit", map.get("second_wcdw_std"));
                aryMap.put("wcdw", "第二完成单位");
                listAry.add(aryMap);
            }
            if (map.get("third_wcdw_std") != null) {
                Map<String, Object> aryMap = Maps.newHashMap();
                aryMap.put("unit", map.get("third_wcdw_std"));
                aryMap.put("wcdw", "第三完成单位");
                listAry.add(aryMap);
            }
            Map<String, Object> highestMap = Maps.newHashMap();
            highestMap.put("year", map.get("hjnf"));
            highestMap.put("list", listAry);
            highestList.add(highestMap);
        });

        BigDecimal hePieSum = hePie.stream().map(map -> new BigDecimal(((Number) map.get("value")).intValue())
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal futurePieSum = futurePie.stream().map(map -> new BigDecimal(((Number) map.get("value")).intValue())
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal outstandingPieSum = outstandingPie.stream().map(map -> new BigDecimal(((Number) map.get("value")).intValue())
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
        int otherNum = hePieSum.intValue() + futurePieSum.intValue() + outstandingPieSum.intValue();

        BigDecimal progressPieSum = progressPie.stream().map(map -> new BigDecimal(((Number) map.get("value")).intValue())
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal naturePieSum = naturePie.stream().map(map -> new BigDecimal(((Number) map.get("value")).intValue())
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal inventPieSum = inventPie.stream().map(map -> new BigDecimal(((Number) map.get("value")).intValue())
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
        int stateNum = progressPieSum.intValue() + naturePieSum.intValue() + inventPieSum
                .intValue() + highestOriginalPie.size();

        DetailAwardDetailInfoDTO info = new DetailAwardDetailInfoDTO();
        info.setHePie(hePie);
        info.setFuturePie(futurePie);
        info.setOutstandingPie(outstandingPie);
        info.setProgressPie(progressPie);
        info.setNaturePie(naturePie);
        info.setInventPie(inventPie);
        info.setHighestList(highestList);
        info.setHighestMun(highestOriginalPie.size());
        info.setOtherNum(otherNum);
        info.setStateNum(stateNum);

        return info;
    }

    @Override
    public DetailAwardDistributionInfoDTO distributionQuery(DetailAwardDistributionQueryDO query) {

        List<Map<String, Object>> awardOriginalAry
                = refOrgTypeManualMapper.queryAwardDistribution(query);

        Map<String, Object> pieMap1 = Maps.newHashMap();
        pieMap1.put("name", "资源生态环境");
        pieMap1.put("value", 0);

        Map<String, Object> pieMap2 = Maps.newHashMap();
        pieMap2.put("name", "材料");
        pieMap2.put("value", 0);

        Map<String, Object> pieMap3 = Maps.newHashMap();
        pieMap3.put("name", "基础前沿交叉");
        pieMap3.put("value", 0);

        Map<String, Object> pieMap4 = Maps.newHashMap();
        pieMap4.put("name", "生命与健康");
        pieMap4.put("value", 0);

        Map<String, Object> pieMap5 = Maps.newHashMap();
        pieMap5.put("name", "能源");
        pieMap5.put("value", 0);

        Map<String, Object> pieMap6 = Maps.newHashMap();
        pieMap6.put("name", "信息");
        pieMap6.put("value", 0);

        Map<String, Object> pieMap7 = Maps.newHashMap();
        pieMap7.put("name", "光电空间");
        pieMap7.put("value", 0);

        Map<String, Object> pieMap8 = Maps.newHashMap();
        pieMap8.put("name", "海洋");
        pieMap8.put("value", 0);

        List<Map<String, Object>> awardPie = Lists.newArrayList(
                pieMap1, pieMap2, pieMap3, pieMap4, pieMap5, pieMap6, pieMap7, pieMap8);

        List<Map<String, Object>> awardAry = Lists.newArrayList();

        awardOriginalAry.stream().forEach(map -> {
            int num  = ((Number) map.get("num_gjkj")).intValue() +
                    ((Number) map.get("num_hlhl")).intValue() +
                    ((Number) map.get("num_qsjc")).intValue() +
                    ((Number) map.get("num_wlkx")).intValue();
            Map<String, Object> aryMap = Maps.newHashMap();
            aryMap.put("name", map.get("jgmc"));
            aryMap.put("value", num);
            aryMap.put("id", map.get("jgbh"));
            awardAry.add(aryMap);

            for(int i = 0; i < awardPie.size(); i++) {
                Map<String, Object> awardPieElement = awardPie.get(i);
                if(((String) map.get("research_field")).equals(String.valueOf(awardPieElement.get("name")))) {
                    awardPieElement.put("value", ((Number) awardPieElement.get("value")).intValue() + num);
                }
            }
        });

        List<Map<String, Object>> sortedAwardAry = awardAry.stream().sorted(Comparator.comparingInt
                (map -> ((Number) map.get("value")).intValue() * -1)).collect(Collectors.toList());

        return new DetailAwardDistributionInfoDTO(awardPie, sortedAwardAry);
    }

    @Override
    public AreaAwardDistributionInfoDTO areaDistributionQuery(DetailAwardDistributionQueryDO query) {

        List<Map<String, Object>> awardOriginalAry
                = refOrgTypeManualMapper.queryAreaAwardDistribution(query);

        List<Map<String, Object>> awardAry = Lists.newArrayList();

        awardOriginalAry.stream().forEach(map -> {
            int num  = ((Number) map.get("num_hlhl")).intValue() +
                    ((Number) map.get("num_jsfm")).intValue() +
                    ((Number) map.get("num_kjjb")).intValue() +
                    ((Number) map.get("num_qsjc")).intValue() +
                    ((Number) map.get("num_wlkx")).intValue() +
                    ((Number) map.get("num_zgkj")).intValue() +
                    ((Number) map.get("num_zrkx")).intValue();
            Map<String, Object> aryMap = Maps.newHashMap();
            aryMap.put("name", map.get("jgmc"));
            aryMap.put("value", num);
            aryMap.put("id", map.get("jgbh"));
            awardAry.add(aryMap);
        });

        List<Map<String, Object>> sortedAwardAry = awardAry.stream().sorted(Comparator.comparingInt
                (map -> ((Number) map.get("value")).intValue() * -1)).collect(Collectors.toList());

        return new AreaAwardDistributionInfoDTO(awardOriginalAry, sortedAwardAry);
    }

    private Map<String, Integer> calculateNum(List<Map<String, Object>> list) {
        Map<String, Integer> numMap = Maps.newHashMap();
        list.stream().forEach(map -> {
            numMap.put(String.valueOf(map.get("nf")).substring(0, 4),
                    ((Number) map.get("num")).intValue());
        });
        return numMap;
    }

    private List<Map<String, Object>> setPeiAry(List<Map<String, Object>> list, String category) {
        List<Map<String, Object>> returnAry;
        switch (category) {
            case "hjjx" :
                returnAry = list.stream().map(map -> {
                    Map<String, Object> aryMap = Maps.newHashMap();
                    aryMap.put("name", String.valueOf(map.get("hjjx")));
                    aryMap.put("value", ((Number) map.get("number")).intValue());
                    return aryMap;
                }).collect(Collectors.toList());
                break;
            case "hjlb" :
                returnAry = list.stream().map(map -> {
                    Map<String, Object> aryMap = Maps.newHashMap();
                    aryMap.put("name", String.valueOf(map.get("hjlb")));
                    aryMap.put("value", ((Number) map.get("number")).intValue());
                    return aryMap;
                }).collect(Collectors.toList());
                break;
            default:
                returnAry = list.stream().map(map -> {
                    Map<String, Object> aryMap = Maps.newHashMap();
                    aryMap.put("name", String.valueOf(map.get("hjdj")));
                    aryMap.put("value", ((Number) map.get("num")).intValue());
                    return aryMap;
                }).collect(Collectors.toList());
        }
        return returnAry;
    }
}
