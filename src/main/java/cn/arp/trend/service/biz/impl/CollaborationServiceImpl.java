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
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(new Date(2012 - 1900, 1,1));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(new Date(2016 - 1900, 12,31));
        while(startCalendar.before(endCalendar)) {
            startCalendar.add(Calendar.MONTH, 1);
            String time = simpleDateFormat.format(startCalendar.getTime());
            timeList.add(time);
            goToByTimeObjMap.put(time, new GoToByTimeObjDTO(time, null, null));
        }

        // 填充查询结果
        List<GoAndComeLink> goLinks = icGoManualMapper.queryGoLink();
        List<GoAndComeLink> comeLinks = icComeManualMapper.queryComeLink();
        for(GoAndComeLink goAndComeLink : goLinks) {
            String time = goAndComeLink.getDate();
            if(goToByTimeObjMap.containsKey(time)) {
                goToByTimeObjMap.get(time).setTo(
                        GoAndComeLinkConverter.INSTANCE.domain2dto(goAndComeLink));
            }
        }
        for(GoAndComeLink goAndComeLink : comeLinks) {
            String time = goAndComeLink.getDate();
            if(goToByTimeObjMap.containsKey(time)) {
                goToByTimeObjMap.get(time).setCome(
                        GoAndComeLinkConverter.INSTANCE.domain2dto(goAndComeLink));
            }
        }

        return new LinksInfoDTO(timeList, Lists.newArrayList(goToByTimeObjMap.values()));
    }

    @Override
    public Map<String, Map<String, Integer>> countryNumQuery() {
        List<String> distinctCountryList = icComeManualMapper.queryDistinctCountry();
        List<CountryAndNationality> goCountryAndNationalityList = icGoManualMapper
                .queryCountryAndNationality();
        List<CountryAndNationality> comeCountryAndNationalityList = icComeManualMapper
                .queryCountryAndNationality();

        Map<String, Map<String, Integer>> result = Maps.newHashMap();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(new Date(2012 - 1900, 1,1));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(new Date(2016 - 1900, 12,31));
        while(startCalendar.before(endCalendar)) {
            startCalendar.add(Calendar.MONTH, 1);
            String time = simpleDateFormat.format(startCalendar.getTime());

            Map<String, Integer> countryMap = Maps.newHashMap();
            for(String country : distinctCountryList) {
                countryMap.put(country, 0);
            }
            result.put(time, countryMap);
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
                if(countryMap.containsKey(country) & countryMap.containsKey(nationality)) {
                    countryMap.put(country, countryMap.get(country) == null ? 0 : countryMap.get(country) + 1);
                    countryMap.put(nationality, countryMap.get(nationality) == null ? 0 : countryMap.get(nationality) + 1);
                }
            }
        }

        return result;
    }

    @Override
    public GoAnalyseInfoDTO goAnalyseQuery(GoAnalyseQueryDO goAnalyseQuery) {
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

        countryList.add("中国");

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
            fieldObjMap.put(form, 0);
        }

        Map<String, Integer> affiliationObjMap = Maps.newHashMap();
        for(String affiliation : affiliationList) {
            affiliationObjMap.put(affiliation, 0);
        }

        Map<String, Integer> countryNumberObjectMap = Maps.newHashMap();
        for(String country : countryList) {
            if(country == null) {
                continue;
            }
            countryNumberObjectMap.put(country, 0);
        }

        Map<Integer, Integer> ageNumMap = Maps.newHashMap();
        for(Integer age : agelist) {
            ageNumMap.put(age, 0);
        }

        Map<String, Integer> yearMap = Maps.newHashMap();
        for(String year : yearlist) {
            yearMap.put(year, 0);
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
            countryNumberObjectMap.put("中国", countryNumberObjectMap.get("中国") + 1);
            if(ageNumMap.containsKey(goAnalyse.getYearOld())) {
                ageNumMap.put(goAnalyse.getYearOld(), ageNumMap.get(goAnalyse.getYearOld()) + 1);
            }
            if(yearMap.containsKey(goAnalyse.getDate())) {
                int date = Integer.valueOf(goAnalyse.getDate());
                yearMap.put(goAnalyse.getDate(),
                        yearMap.get(date) == null ? 0 : yearMap.get(date) + 1);
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

        goCountryObjMap.entrySet().stream().sorted
                (Collections.reverseOrder(Map.Entry.comparingByValue()));

        int top10 = goCountryObjMap.size() < 10 ? goCountryObjMap.size() : 10;

        Map<String, Map<String, Integer>> topTenCountryMap = Maps.newHashMap();

        Map<String, Integer> tempYearMap = Maps.newHashMap();

        for(String year : yearlist) {
            tempYearMap.put(year, 0);
        }

        List<String> topTenCountryName = Lists.newArrayList();
        for(String country : goCountryObjMap.keySet()) {
            if(country == null) {
                continue;
            }
            topTenCountryMap.put(country, tempYearMap);
            topTenCountryName.add(country);
            top10 --;
            if(top10 == 0) {
                break;
            }
        }

        for(GoAndComeAnalyse goAnalyse : goAnalyseList) {
            if(topTenCountryMap.containsKey(goAnalyse.getCountry())) {
                Map<String, Integer> countryCount = topTenCountryMap.get(goAnalyse.getCountry());
                if(countryCount.containsKey(goAnalyse.getDate())) {
                    countryCount.put(goAnalyse.getDate(), countryCount.get(goAnalyse.getDate()) +
                            1);
                }
            }
        }

        GoAnalyseInfoDTO goAnalyseInfo = new GoAnalyseInfoDTO();
        goAnalyseInfo.setCountryNum(goCountryList.size());
        goAnalyseInfo.setCountryPeopleNum(goCountryPeopleNum);
        goAnalyseInfo.setYdylCountryNum(distinctGoydylCountryList.size());
        goAnalyseInfo.setYdylCountryPeopleNum(goydylCountryPeopleNum);
        goAnalyseInfo.setCountryObjList(goCountryObjList);
        goAnalyseInfo.setFieldObjList(fieldObjMap);
        goAnalyseInfo.setFormObjList(formObjMap);
        affiliationObjMap.entrySet().stream().sorted
                (Collections.reverseOrder(Map.Entry.comparingByValue()));
        goAnalyseInfo.setAffiliationObjList(affiliationObjMap);
        goAnalyseInfo.setYearNumList(yearMap);
        goAnalyseInfo.setAgeNumList(ageNumMap);
        goAnalyseInfo.setAgelist(newAgelist);
        goAnalyseInfo.setYearList(yearlist);
        goAnalyseInfo.setCountryNumberObject(countryNumberObjectMap);
        goAnalyseInfo.setTopTenCountryName(topTenCountryName);
        goAnalyseInfo.setTopTenCountryList(topTenCountryMap);
        goAnalyseInfo.setCityAndCountryMapList(tempObjList);
        goAnalyseInfo.setCityList(cityList);

        return goAnalyseInfo;
    }

    @Override
    public ComeAnalyseInfoDTO comeAnalyseQuery(ComeAnalyseQueryDO comeAnalyseQuery) {
        List<GoAndComeAnalyse> comeAnalyseList = icComeManualMapper.queryComeAnalyse
                (comeAnalyseQuery);

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

        countryList.add("中国");

        List<String> goCountryObjList = comeAnalyseList.stream().map(obj -> obj.getCity())
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
            fieldObjMap.put(form, 0);
        }

        Map<String, Integer> affiliationObjMap = Maps.newHashMap();
        for(String affiliation : affiliationList) {
            affiliationObjMap.put(affiliation, 0);
        }

        Map<String, Integer> countryNumberObjectMap = Maps.newHashMap();
        for(String country : countryList) {
            if(country == null) {
                continue;
            }
            countryNumberObjectMap.put(country, 0);
        }

        Map<Integer, Integer> ageNumMap = Maps.newHashMap();
        for(Integer age : agelist) {
            ageNumMap.put(age, 0);
        }

        Map<String, Integer> yearMap = Maps.newHashMap();
        for(String year : yearlist) {
            yearMap.put(year, 0);
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
            countryNumberObjectMap.put("中国", countryNumberObjectMap.get("中国") + 1);
            if(ageNumMap.containsKey(comeAnalyse.getYearOld())) {
                ageNumMap.put(comeAnalyse.getYearOld(), ageNumMap.get(comeAnalyse.getYearOld()) + 1);
            }
            if(yearMap.containsKey(comeAnalyse.getDate())) {
                int date = Integer.valueOf(comeAnalyse.getDate());
                yearMap.put(comeAnalyse.getDate(),
                        yearMap.get(date) == null ? 0 : yearMap.get(date) + 1);
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

        comeCountryObjMap.entrySet().stream().sorted
                (Collections.reverseOrder(Map.Entry.comparingByValue()));

        int top10 = comeCountryObjMap.size() < 10 ? comeCountryObjMap.size() : 10;

        Map<String, Map<String, Integer>> topTenCountryMap = Maps.newHashMap();

        Map<String, Integer> tempYearMap = Maps.newHashMap();

        for(String year : yearlist) {
            tempYearMap.put(year, 0);
        }

        List<String> topTenCountryName = Lists.newArrayList();
        for(String country : comeCountryObjMap.keySet()) {
            if(country == null) {
                continue;
            }
            topTenCountryMap.put(country, tempYearMap);
            topTenCountryName.add(country);
            top10 --;
            if(top10 == 0) {
                break;
            }
        }

        for(GoAndComeAnalyse goAnalyse : comeAnalyseList) {
            if(topTenCountryMap.containsKey(goAnalyse.getCountry())) {
                Map<String, Integer> countryCount = topTenCountryMap.get(goAnalyse.getCountry());
                if(countryCount.containsKey(goAnalyse.getDate())) {
                    countryCount.put(goAnalyse.getDate(), countryCount.get(goAnalyse.getDate()) +
                            1);
                }
            }
        }

        ComeAnalyseInfoDTO comeAnalyseInfo = new ComeAnalyseInfoDTO();
        comeAnalyseInfo.setCountryNum(goCountryList.size());
        comeAnalyseInfo.setCountryPeopleNum(comeCountryPeopleNum);
        comeAnalyseInfo.setYdylCountryNum(distinctComeYdylCountryList.size());
        comeAnalyseInfo.setYdylCountryPeopleNum(comeYdylCountryPeopleNum);
        comeAnalyseInfo.setCountryObjList(goCountryObjList);
        comeAnalyseInfo.setFieldObjList(fieldObjMap);
        comeAnalyseInfo.setFormObjList(formObjMap);
        affiliationObjMap.entrySet().stream().sorted
                (Collections.reverseOrder(Map.Entry.comparingByValue()));
        comeAnalyseInfo.setAffiliationObjList(affiliationObjMap);
        comeAnalyseInfo.setYearNumList(yearMap);
        comeAnalyseInfo.setAgeNumList(ageNumMap);
        comeAnalyseInfo.setAgelist(newAgelist);
        comeAnalyseInfo.setYearList(yearlist);
        comeAnalyseInfo.setCountryNumberObject(countryNumberObjectMap);
        comeAnalyseInfo.setTopTenCountryName(topTenCountryName);
        comeAnalyseInfo.setTopTenCountryList(topTenCountryMap);
        comeAnalyseInfo.setCityAndCountryMapList(tempObjList);
        comeAnalyseInfo.setCityList(cityList);

        return comeAnalyseInfo;
    }
}
