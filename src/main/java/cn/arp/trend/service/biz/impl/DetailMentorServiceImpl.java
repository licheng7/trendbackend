package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AllSupervisorQueryDO;
import cn.arp.trend.data.model.DO.DoctoralSupervisorQueryDO;
import cn.arp.trend.data.model.DO.MasterSupervisorQueryDO;
import cn.arp.trend.data.model.DO.TrendDoctoralSupervisorQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.repository.biz.manual.CasEduFTeacherInfoManualMapper;
import cn.arp.trend.repository.biz.manual.StatTeacherStudentManualMapper;
import cn.arp.trend.service.biz.DetailMentorService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午10:43
 **/
@Service
public class DetailMentorServiceImpl implements DetailMentorService {

    @Resource
    private CasEduFTeacherInfoManualMapper casEduFTeacherInfoManualMapper;

    @Resource
    private StatTeacherStudentManualMapper statTeacherStudentManualMapper;


    @Override
    public DoctoralSupervisorInfoDTO doctoralSupervisorQuery(DoctoralSupervisorQueryDO query) {

        List<Map<String, Object>> distributionAgeList = casEduFTeacherInfoManualMapper
                .distributionAge4DoctoralSupervisor(query);

        List<Map> distributionAge = Lists.newArrayList();

        distributionAgeList.stream().forEach(map -> {
            int age = ((Number) map.get("age")).intValue();
            int number = (Integer) map.get("number");
            Map<String, Object> _map = Maps.newHashMap();
            _map.put("age", age);
            _map.put("number", number);
            distributionAge.add(_map);
        });

        List<Map<String, Object>> distributionFieldList = casEduFTeacherInfoManualMapper
                .distributionField4DoctoralSupervisor(query);

        List<Map> distributionField = Lists.newArrayList();

        distributionFieldList.stream().forEach(map -> {
            String field = (String) map.get("field");
            int number = (Integer) map.get("number");
            Map<String, Object> _map = Maps.newHashMap();
            _map.put("field", field);
            _map.put("number", number);
            distributionField.add(_map);
        });

        DoctoralSupervisorInfoDTO result = new DoctoralSupervisorInfoDTO();
        result.setDistributionAge(distributionAge);
        result.setDistributionField(distributionField);

        return result;
    }

    @Override
    public MasterSupervisorInfoDTO masterSupervisorQuery(MasterSupervisorQueryDO query) {

        List<Map<String, Object>> distributionAgeList = casEduFTeacherInfoManualMapper
                .distributionAge4MasterSupervisor(query);

        List<Map> distributionAge = Lists.newArrayList();

        distributionAgeList.stream().forEach(map -> {
            int age = ((Number) map.get("age")).intValue();
            int number = (Integer) map.get("number");
            Map<String, Object> _map = Maps.newHashMap();
            _map.put("age", age);
            _map.put("number", number);
            distributionAge.add(_map);
        });

        List<Map<String, Object>> distributionFieldList = casEduFTeacherInfoManualMapper
                .distributionField4MasterSupervisor(query);

        List<Map> distributionField = Lists.newArrayList();

        distributionFieldList.stream().forEach(map -> {
            String field = (String) map.get("field");
            int number = (Integer) map.get("number");
            Map<String, Object> _map = Maps.newHashMap();
            _map.put("field", field);
            _map.put("number", number);
            distributionField.add(_map);
        });

        MasterSupervisorInfoDTO result = new MasterSupervisorInfoDTO();
        result.setDistributionAge(distributionAge);
        result.setDistributionField(distributionField);

        return result;
    }

    @Override
    public AllSupervisorInfoDTO allSupervisorQuery(AllSupervisorQueryDO query) {
        List<Map<String, Object>> distributionAgeList = casEduFTeacherInfoManualMapper
                .distributionAge4AllSupervisor(query);

        List<Map> distributionAge = Lists.newArrayList();

        distributionAgeList.stream().forEach(map -> {
            int age = ((Number) map.get("age")).intValue();
            int number = (Integer) map.get("number");
            Map<String, Object> _map = Maps.newHashMap();
            _map.put("age", age);
            _map.put("number", number);
            distributionAge.add(_map);
        });

        List<Map<String, Object>> distributionFieldList = casEduFTeacherInfoManualMapper
                .distributionField4AllSupervisor(query);

        List<Map> distributionField = Lists.newArrayList();

        distributionFieldList.stream().forEach(map -> {
            String field = (String) map.get("field");
            int number = (Integer) map.get("number");
            Map<String, Object> _map = Maps.newHashMap();
            _map.put("field", field);
            _map.put("number", number);
            distributionField.add(_map);
        });

        AllSupervisorInfoDTO result = new AllSupervisorInfoDTO();
        result.setDistributionAge(distributionAge);
        result.setDistributionField(distributionField);

        return result;
    }

    @Override
    public TrendDoctoralSupervisorInfoDTO trendDoctoralSupervisorQuery(TrendDoctoralSupervisorQueryDO query) {

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Integer> mentorList = Maps.newHashMap();
        Map<String, Integer> studentList = Maps.newHashMap();

        yearList.stream().forEach(str -> {
            mentorList.put(str, 0);
            studentList.put(str, 0);
        });

        List<Map<String, Object>> queryResult = statTeacherStudentManualMapper
                .trend4DoctoralSupervisor(query);

        queryResult.stream().forEach(map -> {
            String year = (String) map.get("year");
            if(mentorList.containsKey(year)) {
                mentorList.put(year, mentorList.get(year) +
                        ((Number) map.get("mentor_number")).intValue());
                studentList.put(year, studentList.get(year) +
                        ((Number) map.get("student_number")).intValue());
            }
        });

        List<Map> result = Lists.newArrayList();

        yearList.stream().forEach(str -> {
            Map data = Maps.newHashMap();
            data.put("year", str);
            data.put("mentor_number", mentorList.get(str));
            data.put("student_number", studentList.get(str));
            result.add(data);
        });

        return new TrendDoctoralSupervisorInfoDTO(result);
    }

    private List<String> buildYearlist(String startYear, String endYear) {
        List<String> yearlist = Lists.newArrayList();
        int _startYear = Integer.valueOf(startYear);
        int _endYear = Integer.valueOf(endYear);
        while(_startYear <= _endYear) {
            yearlist.add(String.valueOf(_startYear));
            _startYear ++;
        }
        return yearlist;
    }

}
