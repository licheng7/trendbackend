package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.data.model.DTO.AcademicianInfoDTO;
import cn.arp.trend.data.model.DTO.InternationInfoDTO;
import cn.arp.trend.data.model.DTO.OrgInfoDTO;
import cn.arp.trend.entity.biz.RefOrgType;
import cn.arp.trend.repository.biz.manual.*;
import cn.arp.trend.service.biz.BasicService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午11:43
 **/
@Service
public class BasicServiceImpl extends AbstructServiceHelper implements BasicService {

    @Resource
    private RefOrgTypeManualMapper refOrgTypeManualMapper;

    @Resource
    private CasAcademicianChinaManualMapper casAcademicianChinaManualMapper;

    @Resource
    private CasAcademicianCaeChinaManualMapper casAcademicianCaeChinaManualMapper;

    @Resource
    private IcComeManualMapper icComeManualMapper;

    @Resource
    private IcGoManualMapper icGoManualMapper;

    @Override
    public OrgInfoDTO orgInfoQuery(OrgInfoQueryDO orgInfoQuery) {
        /*List<RefOrgType> queryResult1 =
                refOrgTypeManualMapper.queryOrgByJGBHAndResearch(orgInfoQuery);*/
        List<String> queryResult2 =
                refOrgTypeManualMapper.queryResearchByJGBHAndResearch(orgInfoQuery);
        List<RefOrgType> queryResult3 =
                refOrgTypeManualMapper.queryOrgAndResearchByAll();

        List<Map<String, Object>> institutions = Lists.newArrayList();
        queryResult3.stream().forEach(obj -> {
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", obj.getJgbh());
            map.put("institution", obj.getJgmc());
            map.put("affiliation_field", obj.getResearchField());
            institutions.add(map);
        });

        List<Map<String, Object>> fields = Lists.newArrayList();
        queryResult2.stream().forEach(str -> {
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", "");
            map.put("field", str);
            fields.add(map);
        });

        return new OrgInfoDTO(institutions, fields);
    }

    @Override
    public List<String> yearQuery() {
        List<String> result = Lists.newArrayList();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        for(int i=0; i<10; i++) {
            result.add(simpleDateFormat.format(cal.getTime()));
            cal.add(Calendar.YEAR, -1);
        }

        return result;
    }

    @Override
    public AcademicianInfoDTO academicianQuery(AcademicianQueryDO academicianQuery) {
        List<String> institutionsZKY =
                casAcademicianChinaManualMapper.queryInstitutionsZKY(academicianQuery);
        List<String> institutionsGCY =
                casAcademicianCaeChinaManualMapper.queryInstitutionsGCY(academicianQuery);
        List<String> fieldsZKY =
                casAcademicianChinaManualMapper.queryFieldsZKY(academicianQuery);
        List<String> fieldsGCY =
                casAcademicianCaeChinaManualMapper.queryFieldsGCY(academicianQuery);

        List<Map<String, String>> fieldsZKYMap = Lists.newArrayList();
        fieldsZKY.stream().forEach(str -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("field", str);
            fieldsZKYMap.add(map);
        });

        List<Map<String, String>> fieldsGCYMap = Lists.newArrayList();
        fieldsGCY.stream().forEach(str -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("field", str);
            fieldsGCYMap.add(map);
        });

        Map<String, List<Map<String, String>>> fields = Maps.newHashMap();
        fields.put("fieldsZKY", fieldsZKYMap);
        fields.put("fieldsGCY", fieldsGCYMap);

        List<String> institutions = Lists.newArrayList();
        if(null != institutionsZKY && !institutionsZKY.isEmpty()) {
            institutions.addAll(institutionsZKY);
        }
        if(null != institutionsGCY && !institutionsGCY.isEmpty()) {
            institutions.addAll(institutionsGCY);
        }

        // 去除左右两边的空格
        List<String> trimInstitutions =
                institutions.stream().map(item -> item.trim()).collect(Collectors.toList());

        // 去重
        List<String> distinctInstitutions =
                trimInstitutions.stream().distinct().collect(Collectors.toList());
        List<Map<String, String>> institutionsNew = Lists.newArrayList();
        distinctInstitutions.stream().forEach(str -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("institution", str);
            institutionsNew.add(map);
        });

        AcademicianInfoDTO academicianInfoDTO = new AcademicianInfoDTO();
        academicianInfoDTO.setFields(fields);
        academicianInfoDTO.setInstitutions(institutionsNew);
        academicianInfoDTO.setResultArray(Lists.newArrayList());
        return academicianInfoDTO;
    }

    @Override
    public InternationInfoDTO internationInfoQuery() {
        // step1 country
        List<String> icComeCountryList = icComeManualMapper.queryCountry();
        List<String> icGoCountryList = icGoManualMapper.queryCountry();
        List<String> sortedCountryList = this.doInternationInfoQuery(icComeCountryList, icGoCountryList);
        // step2 Nntionality
        List<String> icComeNationalityList = icComeManualMapper.queryNationality();
        List<String> icGoNationalityList = icGoManualMapper.queryNationality();
        List<String> sortedNationalityList = this.doInternationInfoQuery(icComeNationalityList, icGoNationalityList);
        // step3 form
        List<String> icComeFormList = icComeManualMapper.queryForm();
        List<String> icGoFormList = icGoManualMapper.queryForm();
        List<String> sortedFormList = this.doInternationInfoQuery(icComeFormList, icGoFormList);
        // step4 ageYear
        List<String> icComeAgeYearList = icComeManualMapper.queryAgeYear();
        List<String> icGoAgeYearList = icGoManualMapper.queryAgeYear();
        List<String> sortedAgeYearList = this.doInternationInfoQuery(icComeAgeYearList,
                icGoAgeYearList, -1);
        // step5 获取年龄列表
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        Integer currentYear = Integer.valueOf(simpleDateFormat.format(cal.getTime()));
        List<Integer> ageList = sortedAgeYearList.stream().map(year -> currentYear - Integer.valueOf
                (year)).collect(Collectors.toList());

        List<Map<String, String>> sortedCountryMap = Lists.newArrayList();
        sortedCountryList.stream().forEach(str -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("country", str);
            sortedCountryMap.add(map);
        });

        List<Map<String, String>> sortedNationalityMap = Lists.newArrayList();
        sortedNationalityList.stream().forEach(str -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("nationality", str);
            sortedNationalityMap.add(map);
        });

        List<Map<String, String>> sortedFormMap = Lists.newArrayList();
        sortedFormList.stream().forEach(str -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("form", str);
            sortedFormMap.add(map);
        });

        InternationInfoDTO internationInfoDTO = new InternationInfoDTO();
        internationInfoDTO.setCountry(sortedCountryMap);
        internationInfoDTO.setNationality(sortedNationalityMap);
        internationInfoDTO.setForm(sortedFormMap);
        internationInfoDTO.setSortedAgeYearList(sortedAgeYearList);
        internationInfoDTO.setAgeList(ageList);
        return internationInfoDTO;
    }

    private List<String> doInternationInfoQuery(List<String> list1, List<String> list2) {
        return this.doInternationInfoQuery(list1, list2, 1);
    }

    private List<String> doInternationInfoQuery(
            List<String> list1, List<String> list2, Integer sortItem) {
        // step1、合并list
        List<String> mergeList = Lists.newArrayList();
        if(null != list1 && !list1.isEmpty()) {
            mergeList.addAll(list1);
        }
        if(null != list2 && !list2.isEmpty()) {
            mergeList.addAll(list2);
        }
        // step2、去重
        List<String> distinctList = mergeList.stream().distinct().collect(Collectors
                .toList());
        // step3、String类型集合排序
        List<String> sortedList;
        if(null != sortItem && -1 == sortItem) {
            sortedList = distinctList.stream().sorted((o1, o2) ->
                    -1 * o1.compareToIgnoreCase(o2)).collect(Collectors.toList());
        }
        else {
            sortedList = distinctList.stream().sorted((o1, o2) ->
                    o1.compareToIgnoreCase(o2)).collect(Collectors.toList());
        }

        return sortedList;
    }
}
