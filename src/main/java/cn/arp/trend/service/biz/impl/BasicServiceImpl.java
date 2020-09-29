package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.data.model.DTO.AcademicianInfoDTO;
import cn.arp.trend.data.model.DTO.InternationInfoDTO;
import cn.arp.trend.data.model.DTO.OrgInfoDTO;
import cn.arp.trend.entity.biz.RefOrgType;
import cn.arp.trend.repository.biz.manual.*;
import cn.arp.trend.service.biz.BasicService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午11:43
 **/
@Service
public class BasicServiceImpl implements BasicService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy");

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
    public OrgInfoDTO orgInfoQuery(OrgInfoQueryDO orgInfoQueryDO) {
        // List<RefOrgType> list1 = refOrgTypeManualMapper.queryOrgByJGBHAndResearch(orgInfoQuery);
        List<String> list2 = refOrgTypeManualMapper.queryResearchByJGBHAndResearch(orgInfoQueryDO);
        List<RefOrgType> list3 = refOrgTypeManualMapper.queryOrgAndResearchByAll();

        List<OrgInfoDTO.OrgAndResearchDTO> data = Lists.newArrayList();
        for(RefOrgType refOrgType : list3) {
            data.add(new OrgInfoDTO.OrgAndResearchDTO(
                    refOrgType.getJgbh(), refOrgType.getJgmc(), refOrgType.getResearchField()));
        }

        OrgInfoDTO result = new OrgInfoDTO();
        result.setFields(list2);
        result.setInstitutions(data);
        return result;
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
    public AcademicianInfoDTO academicianQuery(AcademicianQueryDO academicianQueryDO) {
        List<String> institutionsZKY = casAcademicianChinaManualMapper.queryInstitutionsZKY
                (academicianQueryDO);
        List<String> institutionsGCY = casAcademicianCaeChinaManualMapper
                .queryInstitutionsGCY(academicianQueryDO);
        List<String> fieldsZKY = casAcademicianChinaManualMapper.queryFieldsZKY
                (academicianQueryDO);
        List<String> fieldsGCY = casAcademicianCaeChinaManualMapper
                .queryFieldsGCY(academicianQueryDO);
        AcademicianInfoDTO academicianInfoDTO = new AcademicianInfoDTO();
        academicianInfoDTO.setFieldsZKY(fieldsZKY);
        academicianInfoDTO.setFieldsGCY(fieldsGCY);
        academicianInfoDTO.setInstitutionsZKY(institutionsZKY);
        academicianInfoDTO.setInstitutionsGCY(institutionsGCY);

        List<String> institutions = Lists.newArrayList();

        if(null != institutionsZKY && !institutionsZKY.isEmpty()) {
            institutions.addAll(institutionsZKY);
        }
        if(null != institutionsGCY && !institutionsGCY.isEmpty()) {
            institutions.addAll(institutionsGCY);
        }

        // 去除左右两边的空格
        List<String> trimInstitutions = institutions.stream().map(item -> item.trim()).collect(Collectors
                .toList());

        // 去重
        List<String> distinctInstitutions = trimInstitutions.stream().distinct().collect(Collectors
                .toList());

        academicianInfoDTO.setInstitutions(distinctInstitutions);

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

        InternationInfoDTO internationInfoDTO = new InternationInfoDTO();
        internationInfoDTO.setSortedCountryList(sortedCountryList);
        internationInfoDTO.setSortedNationalityList(sortedNationalityList);
        internationInfoDTO.setSortedFormList(sortedFormList);
        internationInfoDTO.setSortedAgeYearList(sortedAgeYearList);
        internationInfoDTO.setAgeList(ageList);
        return internationInfoDTO;
    }

    private List<String> doInternationInfoQuery(List<String> list1, List<String> list2) {
        return this.doInternationInfoQuery(list1, list2, 1);
    }

    private List<String> doInternationInfoQuery(List<String> list1, List<String> list2, Integer
            sortItem) {
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
