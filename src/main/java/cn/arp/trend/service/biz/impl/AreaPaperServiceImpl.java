package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AreaPaperQueryDO;
import cn.arp.trend.data.model.DTO.AreaPaperSciInfoDTO;
import cn.arp.trend.repository.biz.manual.StatCasPaperManualMapper;
import cn.arp.trend.service.biz.AreaPaperService;
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
 * Time:下午9:48
 **/
@Service
public class AreaPaperServiceImpl extends AbstructServiceHelper implements AreaPaperService {

    @Resource
    private StatCasPaperManualMapper statCasPaperManualMapper;

    @Override
    public AreaPaperSciInfoDTO sciQuery(AreaPaperQueryDO query) {

        List<Map<String, Object>> queryResult1 =
                statCasPaperManualMapper.queryAreaPaperSci1(query);

        List<Map<String, Object>> queryResult2 =
                statCasPaperManualMapper.queryAreaPaperSci2(query);

        List<Map<String, Object>> queryResult3 =
                statCasPaperManualMapper.queryAreaPaperSci3(query);

        List<Map<String, Object>> queryResult4 =
                statCasPaperManualMapper.queryAreaPaperSci4(query);

        List<Map<String, Object>> queryResult5 =
                statCasPaperManualMapper.queryAreaPaperSci5(query);

        Map<String, Object> proportionMap1 = Maps.newHashMap();
        proportionMap1.put("name", "所选占比");
        proportionMap1.put("value", queryResult4.get(0).get("sci"));
        Map<String, Object> proportionMap2 = Maps.newHashMap();
        proportionMap2.put("name", "其他占比");
        proportionMap2.put("value", ((Number) queryResult5.get(0).get("sci")).intValue() -
                ((Number) queryResult4.get(0).get("sci")).intValue());
        List<Map<String, Object>> paperProportion = Lists.newArrayList(proportionMap1, proportionMap2);

        List<Map<String, Object>> unitAry = queryResult3.stream().map(map -> {
            Map<String, Object> data = Maps.newHashMap();
            data.put("name", map.get("jgmc"));
            data.put("value", map.get("sci"));
            return data; }).collect(Collectors.toList());

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> originalPaperOfficialMap =
                this.bizData4MapByKeyLimit1(queryResult1, "nf");

        Map<String, Map<String, Object>> originalPaperArpMap =
                this.bizData4MapByKeyLimit1(queryResult2, "nf");

        List<Object> officialList = Lists.newArrayList();
        List<Object> arpList = Lists.newArrayList();

        yearList.stream().forEach(year -> {
            //Map<String, Object> officialMap = Maps.newHashMap();
            //officialMap.put("sci", originalPaperOfficialMap.get(year));
            //officialList.add(officialMap);
            officialList.add(originalPaperOfficialMap.get(year) == null ? null
                    : originalPaperOfficialMap.get(year).get("sci"));

            //Map<String, Object> arpMap = Maps.newHashMap();
            //arpMap.put("sci", originalPaperArpMap.get(year));
            //arpList.add(arpMap);
            arpList.add(originalPaperArpMap.get(year) == null ? null :
                    originalPaperArpMap.get(year).get("sci"));
        });

        return new AreaPaperSciInfoDTO(
                officialList,
                arpList,
                unitAry,
                paperProportion,
                yearList,
                AbstructServiceHelper.UPDATETIME);
    }
}
