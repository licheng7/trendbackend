package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.data.model.DO.ForeignQueryDO;
import cn.arp.trend.data.model.DTO.DACompareInfoDTO;
import cn.arp.trend.data.model.DTO.ForeignInfoDTO;
import cn.arp.trend.data.model.DTO.MapResultDTO;
import cn.arp.trend.repository.biz.manual.CasAcademicianChinaManualMapper;
import cn.arp.trend.repository.biz.manual.CasAcademicianForeignManualMapper;
import cn.arp.trend.service.biz.DetailAcademicianService;
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
 * Time:下午1:48
 **/
@Service
public class DetailAcademicianServiceImpl implements DetailAcademicianService {

    @Resource
    private CasAcademicianForeignManualMapper casAcademicianForeignManualMapper;

    @Resource
    private CasAcademicianChinaManualMapper casAcademicianChinaManualMapper;

    @Override
    public ForeignInfoDTO foreignQuery(List<String> affiliation) {

        ForeignQueryDO foreignQuery = new ForeignQueryDO();
        foreignQuery.setAffiliation(affiliation);
        if(affiliation != null && !affiliation.isEmpty() && affiliation.contains("未知")) {
            foreignQuery.setFlag("OR");
        } else {
            foreignQuery.setFlag("");
        }
        List<Map<String, Object>> queryResult = casAcademicianForeignManualMapper.queryForeign
                (foreignQuery);

        return new ForeignInfoDTO(
                queryResult.stream().map(
                        map -> {
                            return Lists.newArrayList(map.get("guoji"), map.get("rs"));
                        }).collect(Collectors.toList()
                )
        );
    }

    @Override
    public List<Object> compareQuery(DACompareQueryDO query) {

        List<Map<String, Object>> originalData;
        List<Map<String, Object>> allData = casAcademicianChinaManualMapper.queryCompareAll();
        if(query.getAffiliation() != null) {
            originalData = casAcademicianChinaManualMapper.queryCompareUnit(query);
        } else {
            originalData = allData;
        }

        List<String> unitAry = originalData.stream().map(map -> (String) map.get
                ("institution")).collect(Collectors.toList());
        List<String> xuebuAry = originalData.stream().map(map -> (String) map.get
                ("xuebu")).collect(Collectors.toList());

        List<String> distinctInstitutionList = unitAry.stream().distinct().collect(Collectors.toList());

        Map<String, MapResultDTO<String, Integer>> topAcademicianAffiliation = this.initDetail(
                distinctInstitutionList, Integer.class, 0);

        List<Integer> ageTime = this.fillArray(105-35, Integer.class, 0);
        List<Integer> electedAgeTime = this.fillArray(105-35, Integer.class, 0);
        List<Integer> allYearAry = this.fillArray(10, Integer.class, 0);

        List<Integer> yearAry = Lists.newArrayList();
        for(int i=2010; i<2020; i++) {
            yearAry.add(i);
        }

        List<Map<String, Object>> ZKYAry = Lists.newArrayList();
        List<Map<String, Object>> GCYAry = Lists.newArrayList();
        List<Map<String, List<Integer>>> countTimeline = Lists.newArrayList();
        Map<String, List<Integer>> dangxuan = Maps.newHashMap();
        dangxuan.put("当选", this.fillArray(10, Integer.class, 0));
        Map<String, List<Integer>> zhanbi = Maps.newHashMap();
        dangxuan.put("占比", this.fillArray(10, Integer.class, 0));
        countTimeline.add(dangxuan);
        countTimeline.add(zhanbi);

        originalData.stream().forEach(map -> {
            if(null != map.get("institution")) {
                String institution = (String) map.get("institution");
                if (topAcademicianAffiliation.containsKey(institution)) {
                    MapResultDTO<String, Integer> mapResult = topAcademicianAffiliation.get
                            (institution);
                    mapResult.setValue(mapResult.getValue() + 1);
                }
            }
            int dxnf = map.get("dxnf") == null ? 0 : Integer.valueOf((String) map.get("dxnf"));
            int csnf = map.get("csnf") == null ? 0 : Integer.valueOf((String) map.get("csnf"));
            int age = map.get("age") == null ? 0 : ((Number) map.get("age")).intValue();

            String category = map.get("category") == null ? "" : (String) map.get("category");
            ageTime.set(age - 35, ageTime.get(age - 35) + 1);
            if(dxnf - csnf - 35 < 0) {
                System.out.println(map);
            }
            electedAgeTime.set((dxnf - csnf - 35), electedAgeTime.get(dxnf - csnf - 35) + 1);
            int countShow = yearAry.indexOf(dxnf);
            if(countShow != -1) {
                countTimeline.get(0).get("当选").set(countShow, countTimeline.get(0).get("当选").get
                        (countShow) +1);
            }
            if(category.equals("中科院院士")) {
                ZKYAry.add(map);
            } else {
                GCYAry.add(map);
            }
        });

        allData.stream().forEach(map -> {
            int dxnf = map.get("dxnf") == null ? 0 : Integer.valueOf((String) map.get("dxnf"));
            int countShow = yearAry.indexOf(dxnf);
            if(countShow != -1) {
                allYearAry.set(countShow, allYearAry.get(countShow) + 1);
            }
        });

        List<Integer> list = countTimeline.get(0).get("当选");
        for(int i=0; i<list.size(); i++) {
            Integer value = new BigDecimal(list.get(i) / allYearAry.get(i)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).intValue() * 100;
            countTimeline.get(1).get("占比").set(i, value);
        }

        List<Map<String, List<Integer>>> ageTimeline = Lists.newArrayList();
        Map<String, List<Integer>> ageMap = Maps.newHashMap();
        ageMap.put("实际年龄", ageTime);
        ageTimeline.add(ageMap);

        List<Map<String, List<Integer>>> electedAgeTimeLine = Lists.newArrayList();
        Map<String, List<Integer>> selectMap = Maps.newHashMap();
        selectMap.put("当选年龄", electedAgeTime);
        electedAgeTimeLine.add(selectMap);

        DACompareInfoDTO dACompareInfo = new DACompareInfoDTO();

        List<DACompareInfoDTO.Galaxy> galaxyList = Lists.newArrayList();
        DACompareInfoDTO.Galaxy galaxy = dACompareInfo.new Galaxy();
        Map<String, Integer> galaxyTotal = Maps.newHashMap();
        topAcademicianAffiliation.entrySet().stream().forEach(obj -> {
            galaxyTotal.put(obj.getValue().getName(), obj.getValue().getValue());
        });
        galaxy.setGalaxyTotal(galaxyTotal);
        unitAry.stream().forEach( str -> {
                List<Integer> obj = Lists.newArrayList();
                xuebuAry.stream().forEach(xuebu -> {
                    obj.add(0);
                });
                originalData.stream().forEach(map -> {
                    if(map.get("xuebu") != null) {
                        int xuebu = Integer.valueOf((String) map.get("xuebu"));
                        obj.set(xuebu, obj.get(xuebu) + 1);
                    }
                });
                galaxy.setGalaxyFields(obj);
            }
        );
        galaxyList.add(galaxy);

        List<String> distinctXuebuZKYAry = ZKYAry.stream().map(map -> (String) map.get("xuebu"))
                .distinct().collect(Collectors.toList());
        Map<String, MapResultDTO<String, Integer>> fieldsPieCAS = this.initDetail(
                distinctXuebuZKYAry, Integer.class, 0);
        ZKYAry.stream().forEach(map -> {
            if(null != map.get("xuebu")) {
                String xuebu = (String) map.get("xuebu");
                if (fieldsPieCAS.containsKey(xuebu)) {
                    MapResultDTO<String, Integer> mapResult = fieldsPieCAS.get(xuebu);
                    mapResult.setValue(mapResult.getValue() + 1);
                }
            }
        });
        List<MapResultDTO<String, Integer>> _fieldsPieCAS = Lists.newArrayList();
        fieldsPieCAS.entrySet().stream().forEach(map -> {
            _fieldsPieCAS.add(map.getValue());
        });

        List<String> distinctXuebuGCYAry = GCYAry.stream().map(map -> (String) map.get("xuebu"))
                .distinct().collect(Collectors.toList());
        Map<String, MapResultDTO<String, Integer>> fieldsPieCAE = this.initDetail(
                distinctXuebuGCYAry, Integer.class, 0);
        GCYAry.stream().forEach(map -> {
            if(null != map.get("xuebu")) {
                String xuebu = (String) map.get("xuebu");
                if (fieldsPieCAE.containsKey(xuebu)) {
                    MapResultDTO<String, Integer> mapResult = fieldsPieCAE.get(xuebu);
                    mapResult.setValue(mapResult.getValue() + 1);
                }
            }
        });
        List<MapResultDTO<String, Integer>> _fieldsPieCAE = Lists.newArrayList();
        fieldsPieCAE.entrySet().stream().forEach(map -> {
            _fieldsPieCAE.add(map.getValue());
        });

        List<MapResultDTO<String, Integer>> _topAcademicianAffiliation = Lists.newArrayList();
        topAcademicianAffiliation.entrySet().stream().forEach(map -> {
            _topAcademicianAffiliation.add(map.getValue());
        });

        dACompareInfo.setAgeTimeline(ageTimeline);
        dACompareInfo.setCountTimeline(countTimeline);
        dACompareInfo.setElectedAgeTimeLine(electedAgeTimeLine);
        dACompareInfo.setFieldsPieCAS(_fieldsPieCAS);
        dACompareInfo.setFieldsPieCAE(_fieldsPieCAE);
        dACompareInfo.setGalaxyList(galaxyList);
        dACompareInfo.setTopAcademicianAffiliation(_topAcademicianAffiliation);

        List<Object> result = Lists.newArrayList(dACompareInfo, Lists.newArrayList(originalData, allData));

        return result;
    }

    private <V> Map<String, MapResultDTO<String, V>> initDetail(
            List<String> list, Class<V> v, V defaultValue) {
        Map<String, MapResultDTO<String, V>> detail = Maps.newHashMap();
        for(String str : list) {
            detail.put(str, new MapResultDTO(str, defaultValue));
        }
        return detail;
    }

    private <V> Map<String, Map<String, MapResultDTO<String, V>>> initDetail2(
            List<String> nameList, List<String> yearlist, Class<V> v, V defaultValue) {
        Map<String, Map<String, MapResultDTO<String, V>>> detail = Maps.newHashMap();
        for(String name : nameList) {
            Map<String, MapResultDTO<String, V>> map = Maps.newHashMap();
            for(String year : yearlist) {
                map.put(year, new MapResultDTO(year, defaultValue));
            }
            detail.put(name, map);
        }
        return detail;
    }

    private <V> List<V> fillArray(int count, Class<V> v, V defaultValue) {
        List list = Lists.newArrayList();
        for(int i = 0; i < count; i++) {
            list.add(defaultValue);
        }
        return list;
    }

}
