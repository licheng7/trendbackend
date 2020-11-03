package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.ComeAnalyseQueryDO;
import cn.arp.trend.data.model.DO.GoAnalyseQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.data.model.converter.GoAndComeLinkConverter;
import cn.arp.trend.entity.biz.*;
import cn.arp.trend.repository.biz.manual.IcComeManualMapper;
import cn.arp.trend.repository.biz.manual.IcGoManualMapper;
import cn.arp.trend.service.biz.CollaborationService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午12:09
 **/
@Service
public class CollaborationServiceImpl implements CollaborationService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM");

    @Resource
    private IcGoManualMapper icGoManualMapper;

    @Resource
    private IcComeManualMapper icComeManualMapper;

    @Override
    public RankInfoDTO rankQuery() {
        List<Rank> icGoRankList = icGoManualMapper.queryRank();
        List<Rank> icComeRankList = icComeManualMapper.queryRank();

        List<String> goCountryList = icGoRankList.stream().map(obj -> obj.getCountry()).distinct().collect(Collectors.toList());
        int goCountryPeopleNum = icGoRankList.size();
        List<Rank> goydylIcGoRankList = icGoRankList.stream().filter(
                obj -> obj.getYdyl().equals("Y")).collect(Collectors.toList());
        int goydylCountryPeopleNum = goydylIcGoRankList.size();
        List<String> goydylCountryList = goydylIcGoRankList.stream().map(obj -> obj.getCountry()).distinct().collect(Collectors.toList());

        List<String> comeCountryList = icComeRankList.stream().map(obj -> obj.getCountry()).distinct().collect(Collectors.toList());
        int comeCountryPeopleNum = icComeRankList.size();
        List<Rank> goydylIcComeRankList = icComeRankList.stream().filter(
                obj -> obj.getYdyl().equals("Y")).collect(Collectors.toList());
        int comeydylCountryPeopleNum = goydylIcComeRankList.size();
        List<String> comeydylCountryList = goydylIcComeRankList.stream().map(obj -> obj.getCountry()).distinct().collect(Collectors.toList());

        RankInfoDTO rankInfo = new RankInfoDTO();
        rankInfo.setGoCountryNum(goCountryList.size());
        rankInfo.setGoCountryPeopleNum(goCountryPeopleNum);
        rankInfo.setGoydylCountryNum(goydylCountryList.size());
        rankInfo.setGoydylCountryPeopleNum(goydylCountryPeopleNum);
        rankInfo.setComeCountryNum(comeCountryList.size());
        rankInfo.setComeCountryPeopleNum(comeCountryPeopleNum);
        rankInfo.setComeydylCountryNum(comeydylCountryList.size());
        rankInfo.setComeydylCountryPeopleNum(comeydylCountryPeopleNum);
        return rankInfo;
    }

    @Override
    public Rank2InfoDTO rankQuery2() {
        List<Unit> goUnitList = icGoManualMapper.queryGoUnit();
        List<Country> goCountryList = icGoManualMapper.queryGoCountry();
        List<Unit> comeUnitList = icComeManualMapper.queryComeUnit();
        List<Country> comeCountryList = icComeManualMapper.queryComeCountry();

        List<GotoUnitDTO> gotoUnitList = Lists.newArrayList();
        for(Unit unit : goUnitList) {
            gotoUnitList.add(new GotoUnitDTO(
                    unit.getJgmc(),
                    unit.getNum(),
                    null
            ));
        }
        for(Unit unit : comeUnitList) {
            gotoUnitList.add(new GotoUnitDTO(
                    unit.getJgmc(),
                    null,
                    unit.getNum()
            ));
        }
        Map<String, List<GotoUnitDTO>> gotoUnitMap = gotoUnitList.stream().collect(
                Collectors.groupingBy(obj -> obj.getName()));
        List<GotoUnitDTO> distinctGotoUnitList = Lists.newArrayList();
        for(String key : gotoUnitMap.keySet()) {
            List<GotoUnitDTO> gotoUnitByKeyList = gotoUnitMap.get(key);
            int _goNum = 0;
            int _comeNum = 0;
            for(GotoUnitDTO gotoUnit : gotoUnitByKeyList) {
                _goNum = _goNum + (gotoUnit.getGoNum() == null ? 0 : gotoUnit.getGoNum());
                _comeNum = _comeNum + (gotoUnit.getComeNum() == null ? 0 : gotoUnit.getComeNum());
            }
            GotoUnitDTO newGotoUnitDTO = new GotoUnitDTO(key, _goNum, _comeNum);
            distinctGotoUnitList.add(newGotoUnitDTO);
        }

        List<GotoCountryDTO> gotoCountryList = Lists.newArrayList();
        for(Country country : goCountryList) {
            gotoCountryList.add(new GotoCountryDTO(
                    country.getCountry(),
                    country.getNum(),
                    null
            ));
        }
        for(Country country : comeCountryList) {
            gotoCountryList.add(new GotoCountryDTO(
                    country.getCountry(),
                    null,
                    country.getNum()
            ));
        }
        Map<String, List<GotoCountryDTO>> gotoCountryMap = gotoCountryList.stream().collect(
                Collectors.groupingBy(obj -> obj.getName()));
        List<GotoCountryDTO> distinctGotoCountryList = Lists.newArrayList();
        for(String key : gotoCountryMap.keySet()) {
            List<GotoCountryDTO> gotoCountryByKeyList = gotoCountryMap.get(key);
            int _goNum = 0;
            int _comeNum = 0;
            for(GotoCountryDTO gotoCountry : gotoCountryByKeyList) {
                _goNum = _goNum + (gotoCountry.getGoNum() == null ? 0 : gotoCountry.getGoNum());
                _comeNum = _comeNum + (gotoCountry.getComeNum() == null ? 0 : gotoCountry.getComeNum());
            }
            GotoCountryDTO newGotoCountryDTO = new GotoCountryDTO(key, _goNum, _comeNum);
            distinctGotoCountryList.add(newGotoCountryDTO);
        }

        return new Rank2InfoDTO(distinctGotoUnitList, distinctGotoCountryList);
    }

    @Override
    public LinksInfoDTO linksQuery() {
        // 先初始化时间列表
        List<String> timeList = Lists.newArrayList();
        Map<String, GoToByTimeObjDTO> goToByTimeObjMap = Maps.newHashMap();
        LocalDate startTime = LocalDate.parse("2012-01-01");
        LocalDate endTime = LocalDate.parse("2016-12-31");
        while(startTime.isBefore(endTime)) {
            String time = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            timeList.add(time);
            goToByTimeObjMap.put(time,
                    new GoToByTimeObjDTO(Lists.newArrayList(), Lists.newArrayList()));
            startTime = startTime.plusMonths(1);
        }

        // 填充查询结果
        List<GoAndComeLink> goLinks = icGoManualMapper.queryGoLink();
        List<GoAndComeLink> comeLinks = icComeManualMapper.queryComeLink();
        for(GoAndComeLink goAndComeLink : goLinks) {
            String time = goAndComeLink.getDate();
            if(goToByTimeObjMap.containsKey(time)) {
                goToByTimeObjMap.get(time).getTo().add(
                        GoAndComeLinkConverter.INSTANCE.domain2dto(goAndComeLink));
            }
        }
        for(GoAndComeLink goAndComeLink : comeLinks) {
            String time = goAndComeLink.getDate();
            if(goToByTimeObjMap.containsKey(time)) {
                goToByTimeObjMap.get(time).getCome().add(
                        GoAndComeLinkConverter.INSTANCE.domain2dto(goAndComeLink));
            }
        }

        List<GoToByTimeObjDTO> timeListObj = Lists.newArrayList();
        timeList.stream().forEach(time -> {
            timeListObj.add(goToByTimeObjMap.get(time));
        });

        return new LinksInfoDTO(timeList, timeListObj);
    }

    @Override
    public List<List<Map<String, Object>>> countryNumQuery() {

        List<String> distinctCountryList = icComeManualMapper.queryDistinctCountry();

        List<CountryAndNationality> goCountryAndNationalityList =
                icGoManualMapper.queryCountryAndNationality();

        List<CountryAndNationality> comeCountryAndNationalityList =
                icComeManualMapper.queryCountryAndNationality();

        List<String> ymList = Lists.newArrayList();
        Map<String, Map<String, Integer>> result = Maps.newHashMap();
        LocalDate startTime = LocalDate.parse("2012-01-01");
        LocalDate endTime = LocalDate.parse("2016-12-31");
        while (startTime.isBefore(endTime)) {
            String time = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            Map<String, Integer> countryMap = Maps.newHashMap();
            for(String country : distinctCountryList) {
                countryMap.put(country, 0);
            }
            result.put(time, countryMap);
            ymList.add(time);
            startTime = startTime.plusMonths(1);
        }

        List<CountryAndNationality> allCountryAndNationalityList = Lists.newArrayList();
        allCountryAndNationalityList.addAll(goCountryAndNationalityList);
        allCountryAndNationalityList.addAll(comeCountryAndNationalityList);

        for(CountryAndNationality countryAndNationality : allCountryAndNationalityList) {
            String date = countryAndNationality.getDate();
            String country = countryAndNationality.getCountry();
            String nationality = countryAndNationality.getNationality();
            if(result.containsKey(date)) {
                Map<String, Integer> countryMap = result.get(date);
                if(countryMap.containsKey(country) && countryMap.containsKey(nationality)) {
                    countryMap.put(country, countryMap.get(country) + 1);
                    countryMap.put(nationality, countryMap.get(nationality) + 1);
                }
            }
        }

        List<List<Map<String, Object>>> listOut = Lists.newArrayList();

        ymList.stream().forEach(time -> {
            Map<String, Integer> map1 = result.get(time);
            List<Map<String, Object>> listIn = Lists.newArrayList();
            map1.entrySet().stream().forEach(
                    map2 -> {
                        String key2 = map2.getKey();
                        Integer value2 = map2.getValue();
                        Map<String, Object> obj = Maps.newHashMap();
                        obj.put("name", key2);
                        obj.put("value", value2);
                        listIn.add(obj);
                    }
            );
            listOut.add(listIn);
        });

        return listOut;
    }

    @Override
    public GoAnalyseInfoDTO goAnalyseQuery(GoAnalyseQueryDO goAnalyseQuery) {

        return this.goAnalyseQueryNew(goAnalyseQuery);
    }

    @Override
    public ComeAnalyseInfoDTO comeAnalyseQuery(ComeAnalyseQueryDO comeAnalyseQuery) {

        List<GoAndComeAnalyse> comeAnalyseList =
                icComeManualMapper.queryComeAnalyse(comeAnalyseQuery);

        List<String> yearlist = Lists.newArrayList();
        int startYear = Integer.valueOf(comeAnalyseQuery.getStartYear());
        int endYear = Integer.valueOf(comeAnalyseQuery.getEndYear());
        while(startYear <= endYear) {
            yearlist.add(String.valueOf(startYear));
            startYear ++;
        }

        List<Integer> agelist = Lists.newArrayList();
        List<String> newAgelist = Lists.newArrayList();
        int startAge = comeAnalyseQuery.getStartAge();
        int endAge = comeAnalyseQuery.getEndAge();
        while (startAge <= endAge) {
            agelist.add(startAge);
            newAgelist.add(String.valueOf(startAge));
            startAge ++;
        }

        int comeCountryPeopleNum = comeAnalyseList.size();

        List<String> goCountryList = comeAnalyseList.stream().map(
                obj -> obj.getCountry()).distinct().collect(Collectors.toList());

        List<String> countryList = goCountryList;

        List<String> comeYdylCountryList = comeAnalyseList.stream()
                .filter(obj -> obj.getYdyl().equals("Y"))
                .map(obj -> obj.getCountry()).collect(Collectors.toList());

        int comeYdylCountryPeopleNum = comeYdylCountryList.size();

        List<String> distinctComeYdylCountryList = comeYdylCountryList.stream()
                .distinct().collect(Collectors.toList());

        List<String> fieldList = comeAnalyseList.stream().map(obj -> obj.getField())
                .distinct().collect(Collectors.toList());

        List<String> formList = comeAnalyseList.stream().map(obj -> obj.getForm())
                .distinct().collect(Collectors.toList());

        List<String> affiliationList = comeAnalyseList.stream().map(obj -> obj.getJgmc())
                .distinct().collect(Collectors.toList());

        List<String> cityList = comeAnalyseList.stream().map(obj -> obj.getCity())
                .distinct().collect(Collectors.toList());

        Map<String, Integer> comeCountryObjMap = Maps.newHashMap();
        for(String country : goCountryList) {
            comeCountryObjMap.put(country, 0);
        }

        Map<String, Integer> fieldObjMap = Maps.newHashMap();
        for(String field : fieldList) {
            fieldObjMap.put(field, 0);
        }

        Map<String, Integer> formObjMap = Maps.newHashMap();
        for(String form : formList) {
            formObjMap.put(form, 0);
        }

        Map<String, Integer> affiliationObjMap = Maps.newHashMap();
        for(String affiliation : affiliationList) {
            affiliationObjMap.put(affiliation, 0);
        }

        Map<String, Integer> countryNumberObjectMap = Maps.newHashMap();
        for(String country : countryList) {
            countryNumberObjectMap.put(country, 0);
        }

        countryList.add("中国");

        Map<String, Integer> countryNumberObjectMap2 = Maps.newHashMap();
        for(String country : countryList) {
            countryNumberObjectMap2.put(country, 0);
        }

        Map<Integer, Integer> ageNumMap = Maps.newHashMap();
        for(Integer age : agelist) {
            ageNumMap.put(age, null);
        }

        Map<String, Integer> yearMap = Maps.newHashMap();
        for(String year : yearlist) {
            yearMap.put(year, null);
        }

        Map<String, List<String>> cityAndCountryObjMap = Maps.newHashMap();
        for(String city : cityList) {
            cityAndCountryObjMap.put(city, Lists.newArrayList());
        }

        for(GoAndComeAnalyse comeAnalyse : comeAnalyseList) {
            if(comeCountryObjMap.containsKey(comeAnalyse.getCountry())) {
                comeCountryObjMap.put(comeAnalyse.getCountry(), comeCountryObjMap.get(comeAnalyse
                        .getCountry()) + 1);
            }
            if(fieldObjMap.containsKey(comeAnalyse.getField())) {
                fieldObjMap.put(comeAnalyse.getField(), fieldObjMap.get(comeAnalyse.getField()) + 1);
            }
            if(formObjMap.containsKey(comeAnalyse.getForm())) {
                formObjMap.put(comeAnalyse.getForm(), formObjMap.get(comeAnalyse.getForm()) + 1);
            }
            if(affiliationObjMap.containsKey(comeAnalyse.getJgmc())) {
                affiliationObjMap.put(comeAnalyse.getJgmc(), affiliationObjMap.get(comeAnalyse.getJgmc()) + 1);
            }
            if(countryNumberObjectMap.containsKey(comeAnalyse.getCountry())) {
                countryNumberObjectMap.put(comeAnalyse.getCountry(), countryNumberObjectMap.get(comeAnalyse.getCountry()) + 1);
            }
            if(countryNumberObjectMap2.containsKey(comeAnalyse.getCountry())) {
                countryNumberObjectMap2.put(comeAnalyse.getCountry(), countryNumberObjectMap2.get
                        (comeAnalyse.getCountry()) + 1);
            }
            countryNumberObjectMap2.put("中国", countryNumberObjectMap2.get("中国") + 1);
            if(ageNumMap.containsKey(comeAnalyse.getYearOld())) {
                ageNumMap.put(comeAnalyse.getYearOld(), ageNumMap.get(comeAnalyse.getYearOld())
                                == null ? 1 : ageNumMap.get(comeAnalyse.getYearOld()) + 1);
            }
            if(yearMap.containsKey(comeAnalyse.getDate())) {
                yearMap.put(comeAnalyse.getDate(),
                        yearMap.get(comeAnalyse.getDate()) == null ? 1 : yearMap.get(comeAnalyse
                                .getDate()) + 1);
            }
            if(cityAndCountryObjMap.containsKey(comeAnalyse.getCity())) {
                cityAndCountryObjMap.get(comeAnalyse.getCity()).add(comeAnalyse.getCountry());
            }
        }

        List<TempObjDTO> tempObjList = Lists.newArrayList();

        for(String city : cityAndCountryObjMap.keySet()) {
            List<String> countylist = cityAndCountryObjMap.get(city);
            Map<String, Integer> tempCountryMap = Maps.newHashMap();
            for(String country : countylist) {
                if(tempCountryMap.containsKey(country)) {
                    tempCountryMap.put(country, tempCountryMap.get(country) + 1);
                }
                else {
                    tempCountryMap.put(country, 1);
                }
            }
            for(String country : tempCountryMap.keySet()) {
                tempObjList.add(new TempObjDTO(city, country, tempCountryMap.get(country)));
            }
        }

        Map<String, Integer> sortedComeCountryObjMap = Maps.newHashMap();

        comeCountryObjMap.entrySet().stream().sorted(Comparator.comparingInt(map ->  map
                .getValue() * -1)).limit(10).forEach(obj -> sortedComeCountryObjMap.put(obj
                .getKey(), obj.getValue()));


        Map<String, Map<String, Integer>> topTenCountryMap = Maps.newHashMap();

        for(String country : sortedComeCountryObjMap.keySet()) {
            Map<String, Integer> tempYearMap = Maps.newHashMap();
            for(String year : yearlist) {
                tempYearMap.put(year, null);
            }
            topTenCountryMap.put(country, tempYearMap);
        }

        for(GoAndComeAnalyse goAnalyse : comeAnalyseList) {
            if(topTenCountryMap.containsKey(goAnalyse.getCountry())) {
                Map<String, Integer> countryCount = topTenCountryMap.get(goAnalyse.getCountry());
                if(countryCount.containsKey(goAnalyse.getDate())) {
                    countryCount.put(goAnalyse.getDate(), countryCount.get(goAnalyse.getDate())
                                    == null ? 1 : countryCount.get(goAnalyse.getDate()) + 1);
                }
            }
        }

        List<Map<String, Object>> topTenCountryList = topTenCountryMap.entrySet().stream().map(
                map -> {
                    Map<String, Object> result = Maps.newHashMap();
                    result.put("name", map.getKey());
                    List<Integer> value = Lists.newArrayList();
                    yearlist.stream().forEach(year -> {
                        value.add(map.getValue().get(year));
                    });
                    result.put("value", value);
                    return result;
        }).collect(Collectors.toList());

        List<String> topTenCountryName = topTenCountryList.stream().map(map -> (String) map.get
                ("name")).collect(Collectors.toList());

        ComeAnalyseInfoDTO comeAnalyseInfo = new ComeAnalyseInfoDTO();
        comeAnalyseInfo.setCountryNum(goCountryList.size());
        comeAnalyseInfo.setCountryPeopleNum(comeCountryPeopleNum);
        comeAnalyseInfo.setYdylCountryNum(distinctComeYdylCountryList.size());
        comeAnalyseInfo.setYdylCountryPeopleNum(comeYdylCountryPeopleNum);

        List<Map<String, Object>> countryNumberObjectList =
                countryNumberObjectMap.entrySet().stream().map(
                map -> {
                    Map<String, Object> result = Maps.newHashMap();
                    result.put("name", map.getKey());
                    result.put("value", map.getValue());
                    return result;
                }
        ).sorted(Comparator.comparingInt(
                map -> ((Number) map.get("value")).intValue() * -1))
                .collect(Collectors.toList());

        List<Map<String, Object>> countryNumberObjectList2 =
                countryNumberObjectMap2.entrySet().stream().map(
                        map -> {
                            Map<String, Object> result = Maps.newHashMap();
                            result.put("name", map.getKey());
                            result.put("value", map.getValue());
                            return result;
                        }
                ).sorted(Comparator.comparingInt(
                        map -> ((Number) map.get("value")).intValue() * -1))
                        .collect(Collectors.toList());

        comeAnalyseInfo.setCountryObjList(countryNumberObjectList);
        comeAnalyseInfo.setFieldObjList(
                fieldObjMap.entrySet().stream().map(
                    map -> {
                        Map<String, Object> result = Maps.newHashMap();
                        result.put("name", map.getKey());
                        result.put("value", map.getValue());
                        return result;
                    }
                ).collect(Collectors.toList())
        );
        comeAnalyseInfo.setFormObjList(
                formObjMap.entrySet().stream().map(
                    map -> {
                        Map<String, Object> result = Maps.newHashMap();
                        result.put("name", map.getKey());
                        result.put("value", map.getValue());
                        return result;
                    }
                ).collect(Collectors.toList()));
        Map<String, Integer> sortedAffiliationObjMap = Maps.newHashMap();
        affiliationObjMap.entrySet().stream().sorted
                (Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(obj ->
                sortedAffiliationObjMap.put(obj.getKey(), obj.getValue()));
        comeAnalyseInfo.setAffiliationObjList(sortedAffiliationObjMap.entrySet().stream().map(
                map -> {
                    Map<String, Object> result = Maps.newHashMap();
                    result.put("name", map.getKey());
                    result.put("value", map.getValue());
                    return result;
                }
        ).collect(Collectors.toList()));
        List<Integer> yearNumList = Lists.newArrayList();
        yearlist.stream().forEach(year -> yearNumList.add(yearMap.get(year)));
        comeAnalyseInfo.setYearNumList(yearNumList);
        comeAnalyseInfo.setAgeNumList(
                ageNumMap.entrySet().stream().map(
                        map -> map.getValue()).collect(Collectors.toList()));
        comeAnalyseInfo.setAgelist(newAgelist);
        comeAnalyseInfo.setYearList(yearlist);
        comeAnalyseInfo.setCountryNumObject(countryNumberObjectList2);
        comeAnalyseInfo.setTopTenCountryName(topTenCountryName);
        comeAnalyseInfo.setTopTenCountryList(topTenCountryList);
        comeAnalyseInfo.setCityAndCountryMapList(tempObjList);
        comeAnalyseInfo.setCityList(cityList);

        return comeAnalyseInfo;
    }

    public GoAnalyseInfoDTO goAnalyseQueryNew(GoAnalyseQueryDO goAnalyseQuery) {

        List<GoAndComeAnalyse> goAnalyseList = icGoManualMapper.queryGoAnalyse(goAnalyseQuery);

        List<String> yearlist = Lists.newArrayList();
        int startYear = Integer.valueOf(goAnalyseQuery.getStartYear());
        int endYear = Integer.valueOf(goAnalyseQuery.getEndYear());
        while(startYear <= endYear) {
            yearlist.add(String.valueOf(startYear));
            startYear ++;
        }

        List<Integer> agelist = Lists.newArrayList();
        List<String> newAgelist = Lists.newArrayList();
        int startAge = goAnalyseQuery.getStartAge();
        int endAge = goAnalyseQuery.getEndAge();
        while (startAge <= endAge) {
            agelist.add(startAge);
            newAgelist.add(String.valueOf(startAge));
            startAge ++;
        }

        int goCountryPeopleNum = goAnalyseList.size();

        List<String> goCountryList = goAnalyseList.stream().map(
                obj -> obj.getCountry()).distinct().collect(Collectors.toList());

        List<String> countryList = goCountryList;

        List<String> goydylCountryList = goAnalyseList.stream()
                .filter(obj -> obj.getYdyl().equals("Y"))
                .map(obj -> obj.getCountry()).collect(Collectors.toList());

        int goydylCountryPeopleNum = goydylCountryList.size();

        List<String> distinctGoydylCountryList = goydylCountryList.stream()
                .distinct().collect(Collectors.toList());

        List<String> fieldList = goAnalyseList.stream().map(obj -> obj.getField())
                .distinct().collect(Collectors.toList());

        List<String> formList = goAnalyseList.stream().map(obj -> obj.getForm())
                .distinct().collect(Collectors.toList());

        List<String> affiliationList = goAnalyseList.stream().map(obj -> obj.getJgmc())
                .distinct().collect(Collectors.toList());

        List<String> cityList = goAnalyseList.stream().map(obj -> obj.getCity())
                .distinct().collect(Collectors.toList());

        List<String> goCountryObjList = goAnalyseList.stream().map(obj -> obj.getCity())
                .distinct().collect(Collectors.toList());

        Map<String, Integer> goCountryObjMap = Maps.newHashMap();
        for(String country : goCountryList) {
            goCountryObjMap.put(country, 0);
        }

        Map<String, Integer> fieldObjMap = Maps.newHashMap();
        for(String field : fieldList) {
            fieldObjMap.put(field, 0);
        }

        Map<String, Integer> formObjMap = Maps.newHashMap();
        for(String form : formList) {
            formObjMap.put(form, 0);
        }

        Map<String, Integer> affiliationObjMap = Maps.newHashMap();
        for(String affiliation : affiliationList) {
            affiliationObjMap.put(affiliation, 0);
        }

        Map<String, Integer> countryNumberObjectMap = Maps.newHashMap();
        for(String country : countryList) {
            countryNumberObjectMap.put(country, 0);
        }

        countryList.add("中国");

        Map<String, Integer> countryNumberObjectMap2 = Maps.newHashMap();
        for(String country : countryList) {
            countryNumberObjectMap2.put(country, 0);
        }

        Map<Integer, Integer> ageNumMap = Maps.newHashMap();
        for(Integer age : agelist) {
            ageNumMap.put(age, null);
        }

        Map<String, Integer> yearMap = Maps.newHashMap();
        for(String year : yearlist) {
            yearMap.put(year, null);
        }

        Map<String, List<String>> cityAndCountryObjMap = Maps.newHashMap();
        for(String city : cityList) {
            cityAndCountryObjMap.put(city, Lists.newArrayList());
        }

        for(GoAndComeAnalyse goAnalyse : goAnalyseList) {
            if(goCountryObjMap.containsKey(goAnalyse.getCountry())) {
                goCountryObjMap.put(goAnalyse.getCountry(), goCountryObjMap.get(goAnalyse
                        .getCountry()) + 1);
            }
            if(fieldObjMap.containsKey(goAnalyse.getField())) {
                fieldObjMap.put(goAnalyse.getField(), fieldObjMap.get(goAnalyse.getField()) + 1);
            }
            if(formObjMap.containsKey(goAnalyse.getForm())) {
                formObjMap.put(goAnalyse.getForm(), formObjMap.get(goAnalyse.getForm()) + 1);
            }
            if(affiliationObjMap.containsKey(goAnalyse.getJgmc())) {
                affiliationObjMap.put(goAnalyse.getJgmc(), affiliationObjMap.get(goAnalyse.getJgmc()) + 1);
            }
            if(countryNumberObjectMap.containsKey(goAnalyse.getCountry())) {
                countryNumberObjectMap.put(goAnalyse.getCountry(), countryNumberObjectMap.get(goAnalyse.getCountry()) + 1);
            }
            if(countryNumberObjectMap2.containsKey(goAnalyse.getCountry())) {
                countryNumberObjectMap2.put(goAnalyse.getCountry(), countryNumberObjectMap2.get
                        (goAnalyse.getCountry()) + 1);
            }
            countryNumberObjectMap2.put("中国", countryNumberObjectMap2.get("中国") + 1);
            if(ageNumMap.containsKey(goAnalyse.getYearOld())) {
                ageNumMap.put(goAnalyse.getYearOld(), ageNumMap.get(goAnalyse.getYearOld())
                        == null ? 1 : ageNumMap.get(goAnalyse.getYearOld()) + 1);
            }
            if(yearMap.containsKey(goAnalyse.getDate())) {
                yearMap.put(goAnalyse.getDate(),
                        yearMap.get(goAnalyse.getDate()) == null ? 1 : yearMap.get(goAnalyse
                                .getDate()) + 1);
            }
            if(cityAndCountryObjMap.containsKey(goAnalyse.getCity())) {
                cityAndCountryObjMap.get(goAnalyse.getCity()).add(goAnalyse.getCountry());
            }
        }

        List<TempObjDTO> tempObjList = Lists.newArrayList();

        for(String city : cityAndCountryObjMap.keySet()) {
            List<String> countylist = cityAndCountryObjMap.get(city);
            Map<String, Integer> tempCountryMap = Maps.newHashMap();
            for(String country : countylist) {
                if(tempCountryMap.containsKey(country)) {
                    tempCountryMap.put(country, tempCountryMap.get(country) + 1);
                }
                else {
                    tempCountryMap.put(country, 1);
                }
            }
            for(String country : tempCountryMap.keySet()) {
                tempObjList.add(new TempObjDTO(city, country, tempCountryMap.get(country)));
            }
        }

        Map<String, Integer> sortedGoCountryObjMap = Maps.newHashMap();

        goCountryObjMap.entrySet().stream().sorted(Comparator.comparingInt(map ->  map
                .getValue() * -1)).limit(10).forEach(obj -> sortedGoCountryObjMap.put(obj
                .getKey(), obj.getValue()));


        Map<String, Map<String, Integer>> topTenCountryMap = Maps.newHashMap();

        for(String country : sortedGoCountryObjMap.keySet()) {
            Map<String, Integer> tempYearMap = Maps.newHashMap();
            for(String year : yearlist) {
                tempYearMap.put(year, null);
            }
            topTenCountryMap.put(country, tempYearMap);
        }

        for(GoAndComeAnalyse goAnalyse : goAnalyseList) {
            if(topTenCountryMap.containsKey(goAnalyse.getCountry())) {
                Map<String, Integer> countryCount = topTenCountryMap.get(goAnalyse.getCountry());
                if(countryCount.containsKey(goAnalyse.getDate())) {
                    countryCount.put(goAnalyse.getDate(), countryCount.get(goAnalyse.getDate())
                            == null ? 1 : countryCount.get(goAnalyse.getDate()) + 1);
                }
            }
        }

        List<Map<String, Object>> topTenCountryList = topTenCountryMap.entrySet().stream().map(
                map -> {
                    Map<String, Object> result = Maps.newHashMap();
                    result.put("name", map.getKey());
                    List<Integer> value = Lists.newArrayList();
                    yearlist.stream().forEach(year -> {
                        value.add(map.getValue().get(year));
                    });
                    result.put("value", value);
                    return result;
                }).collect(Collectors.toList());

        List<String> topTenCountryName = topTenCountryList.stream().map(map -> (String) map.get
                ("name")).collect(Collectors.toList());


        GoAnalyseInfoDTO goAnalyseInfo = new GoAnalyseInfoDTO();
        goAnalyseInfo.setCountryNum(goCountryList.size());
        goAnalyseInfo.setCountryPeopleNum(goCountryPeopleNum);
        goAnalyseInfo.setYdylCountryNum(distinctGoydylCountryList.size());
        goAnalyseInfo.setYdylCountryPeopleNum(goydylCountryPeopleNum);

        List<Map<String, Object>> countryNumberObjectList =
                countryNumberObjectMap.entrySet().stream().map(
                        map -> {
                            Map<String, Object> result = Maps.newHashMap();
                            result.put("name", map.getKey());
                            result.put("value", map.getValue());
                            return result;
                        }
                ).sorted(Comparator.comparingInt(
                        map -> ((Number) map.get("value")).intValue() * -1))
                        .collect(Collectors.toList());

        List<Map<String, Object>> countryNumberObjectList2 =
                countryNumberObjectMap2.entrySet().stream().map(
                        map -> {
                            Map<String, Object> result = Maps.newHashMap();
                            result.put("name", map.getKey());
                            result.put("value", map.getValue());
                            return result;
                        }
                ).sorted(Comparator.comparingInt(
                        map -> ((Number) map.get("value")).intValue() * -1))
                        .collect(Collectors.toList());

        goAnalyseInfo.setCountryObjList(countryNumberObjectList);
        goAnalyseInfo.setFieldObjList(
                fieldObjMap.entrySet().stream().map(
                        map -> {
                            Map<String, Object> result = Maps.newHashMap();
                            result.put("name", map.getKey());
                            result.put("value", map.getValue());
                            return result;
                        }
                ).collect(Collectors.toList())
        );
        goAnalyseInfo.setFormObjList(
                formObjMap.entrySet().stream().map(
                        map -> {
                            Map<String, Object> result = Maps.newHashMap();
                            result.put("name", map.getKey());
                            result.put("value", map.getValue());
                            return result;
                        }
                ).collect(Collectors.toList()));
        Map<String, Integer> sortedAffiliationObjMap = Maps.newHashMap();
        affiliationObjMap.entrySet().stream().sorted
                (Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(obj ->
                sortedAffiliationObjMap.put(obj.getKey(), obj.getValue()));
        goAnalyseInfo.setAffiliationObjList(sortedAffiliationObjMap.entrySet().stream().map(
                map -> {
                    Map<String, Object> result = Maps.newHashMap();
                    result.put("name", map.getKey());
                    result.put("value", map.getValue());
                    return result;
                }
        ).collect(Collectors.toList()));
        List<Integer> yearNumList = Lists.newArrayList();
        yearlist.stream().forEach(year -> yearNumList.add(yearMap.get(year)));
        goAnalyseInfo.setYearNumList(yearNumList);
        goAnalyseInfo.setAgeNumList(
                ageNumMap.entrySet().stream().map(
                        map -> map.getValue()).collect(Collectors.toList()));
        goAnalyseInfo.setAgelist(newAgelist);
        goAnalyseInfo.setYearList(yearlist);
        goAnalyseInfo.setCountryNumObject(countryNumberObjectList2);
        goAnalyseInfo.setTopTenCountryName(topTenCountryName);
        goAnalyseInfo.setTopTenCountryList(topTenCountryList);
        goAnalyseInfo.setCityAndCountryMapList(tempObjList);
        goAnalyseInfo.setCityList(cityList);

        return goAnalyseInfo;
    }
}
