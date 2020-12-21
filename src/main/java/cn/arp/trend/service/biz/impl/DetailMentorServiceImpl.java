package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.*;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.data.model.response.MentorRadarResponse;
import cn.arp.trend.repository.biz.manual.CasEduFTeacherInfoManualMapper;
import cn.arp.trend.repository.biz.manual.MentorRadarManualMapper;
import cn.arp.trend.repository.biz.manual.StatTeacherStudentManualMapper;
import cn.arp.trend.service.biz.DetailMentorService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午10:43
 **/
@Service
public class DetailMentorServiceImpl extends AbstructServiceHelper implements DetailMentorService {

    @Resource
    private CasEduFTeacherInfoManualMapper casEduFTeacherInfoManualMapper;

    @Resource
    private StatTeacherStudentManualMapper statTeacherStudentManualMapper;

    @Resource
    private MentorRadarManualMapper mentorRadarManualMapper;


    @Override
    public DoctoralSupervisorInfoDTO doctoralSupervisorQuery(DoctoralSupervisorQueryDO query) {

        List<Map<String, Object>> distributionAgeList = casEduFTeacherInfoManualMapper
                .distributionAge4DoctoralSupervisor(query);

        List<Map> distributionAge = Lists.newArrayList();

        distributionAgeList.stream().forEach(map -> {
            int age = ((Number) map.get("age")).intValue();
            Long number = (Long) map.get("number");
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
            Long number = (Long) map.get("number");
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
            Long number = (Long) map.get("number");
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
            Long number = (Long) map.get("number");
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
            Long number = (Long) map.get("number");
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
            Long number = (Long) map.get("number");
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

    @Override
    public TrendMasterSupervisorInfoDTO trendMasterSupervisorQuery(TrendMasterSupervisorQueryDO query) {

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Integer> mentorList = Maps.newHashMap();
        Map<String, Integer> studentList = Maps.newHashMap();

        yearList.stream().forEach(str -> {
            mentorList.put(str, 0);
            studentList.put(str, 0);
        });

        List<Map<String, Object>> queryResult =
                statTeacherStudentManualMapper.trend4MasterSupervisor(query);

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

        return new TrendMasterSupervisorInfoDTO(result);
    }

    @Override
    public TrendAllInfoDTO trendAllQuery(TrendAllQueryDO query) {

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Integer> mentorList = Maps.newHashMap();
        Map<String, Integer> studentList = Maps.newHashMap();

        yearList.stream().forEach(str -> {
            mentorList.put(str, 0);
            studentList.put(str, 0);
        });

        List<Map<String, Object>> queryResult1 =
                statTeacherStudentManualMapper.trend4All1(query);
        List<Map<String, Object>> queryResult2 =
                statTeacherStudentManualMapper.trend4All2(query);
        List<Map<String, Object>> queryResult = Lists.newArrayList();
        queryResult.addAll(queryResult1);
        queryResult.addAll(queryResult2);

        queryResult.stream().forEach(map -> {
            String year = (String) map.get("year");
            if(mentorList.containsKey(year)) {
                mentorList.put(year, mentorList.get(year) + ((Number) map.get("mentor_number")).intValue());
                studentList.put(year, studentList.get(year) + ((Number) map.get("student_number")).intValue());
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

        return new TrendAllInfoDTO(result);
    }

    @Override
    public MentorDetailInfoDTO detailQuery(MentorDetailQueryDO query) {

        List<Map<String, Object>> queryResult =
                statTeacherStudentManualMapper.mentorDetail(query);

        Map<String, List<Map<String, Integer>>> tmp = Maps.newHashMap();
        queryResult.stream().forEach(map -> {
            String affiliation = (String) map.get("affiliation");
            if(!tmp.containsKey(affiliation)) {
                tmp.put(affiliation, Lists.newArrayList());
            }
        });

        int maxMentorD = 0;
        int maxMentorM = 0;
        int maxYear = 0;

        for(Map<String, Object> map : queryResult) {
            String affiliation = (String) map.get("affiliation");

            int bd = ((Number) map.get("bd")).intValue();
            int sd = ((Number) map.get("sd")).intValue();
            int year = Integer.valueOf((String) map.get("year"));
            int bs = ((Number) map.get("bs")).intValue();
            int ss = ((Number) map.get("ss")).intValue();

            Map<String, Integer> o = Maps.newHashMap();
            o.put("year", year);
            o.put("bd", bd);
            o.put("bs", bs);
            o.put("sd", sd);
            o.put("ss", ss);
            tmp.get(affiliation).add(o);


            if(bd > maxMentorD) {
                maxMentorD = bd;
            }

            if(sd > maxMentorM) {
                maxMentorM = sd;
            }

            if(year > maxYear) {
                maxYear = year;
            }
        }

        List<Map> data = Lists.newArrayList();

        for(String key : tmp.keySet()) {
            Map chart = Maps.newHashMap();

            int endYearBd = 0;
            int endYearSd = 0;
            int endYearBs = 0;
            int endYearSs = 0;

            chart.put("pie1", Lists.newArrayList());
            chart.put("pie2", Lists.newArrayList());

            Map line1Value = Maps.newHashMap();
            line1Value.put("year", Lists.newArrayList());
            line1Value.put("num", Lists.newArrayList());
            chart.put("line1", line1Value);
            Map line2Value = Maps.newHashMap();
            line2Value.put("year", Lists.newArrayList());
            line2Value.put("num", Lists.newArrayList());
            chart.put("line2", line2Value);

            Map bar1Value = Maps.newHashMap();
            bar1Value.put("totalNum", maxMentorD);
            chart.put("bar1", bar1Value);
            Map bar2Value = Maps.newHashMap();
            bar2Value.put("totalNum", maxMentorD + maxMentorM);
            chart.put("bar2", bar2Value);

            List<Map<String, Integer>> tmpValue = tmp.get(key);
            for(Map<String, Integer> map : tmpValue) {
                ((List) ((Map) chart.get("line1")).get("year")).add(map.get("year"));
                ((List) ((Map) chart.get("line1")).get("num")).add(map.get("bd"));

                ((List) ((Map) chart.get("line2")).get("year")).add(map.get("year"));
                ((List) ((Map) chart.get("line2")).get("num")).add(map.get("sd"));

                if(map.get("year") == maxYear) {
                    endYearBd = map.get("bd");
                    endYearSd = map.get("sd");
                    endYearBs = map.get("bs");
                    endYearSs = map.get("ss");

                    ((Map) chart.get("bar1")).put("nowNum", map.get("bd"));
                    ((Map) chart.get("bar2")).put("nowNum", map.get("sd"));

                    Map pie1Value1 = Maps.newHashMap();
                    pie1Value1.put("name", "博导");
                    pie1Value1.put("value", map.get("bd"));
                    Map pie1Value2 = Maps.newHashMap();
                    pie1Value2.put("name", "博士生");
                    pie1Value2.put("value", map.get("bs"));
                    List list1 = ((List) chart.get("pie1"));
                    list1.add(pie1Value1);
                    list1.add(pie1Value2);

                    Map pie2Value1 = Maps.newHashMap();
                    pie2Value1.put("name", "硕导");
                    pie2Value1.put("value", map.get("sd"));
                    Map pie2Value2 = Maps.newHashMap();
                    pie2Value2.put("name", "硕士生");
                    pie2Value2.put("value", map.get("ss"));
                    List list2 = ((List) chart.get("pie2"));
                    list2.add(pie2Value1);
                    list2.add(pie2Value2);
                }
            }

            Map<String, Object> percentValue = Maps.newHashMap();
            percentValue.put("schoolName", key);
            percentValue.put("teacherNum", endYearBd + endYearSd);
            percentValue.put("studentsNum", endYearBs + endYearSs);
            chart.put("percent", percentValue);

            chart.put("order_mentor", endYearBd + endYearSd);
            chart.put("order_mentor_d", endYearBd);
            chart.put("order_mentor_m", endYearSd);

            chart.put("order_student", endYearBs + endYearSs);
            chart.put("order_student_d", endYearBs);
            chart.put("order_student_m", endYearSs);

            BigDecimal orderStudent = new BigDecimal((int) chart.get("order_student"));
            BigDecimal orderMentor = new BigDecimal((int) chart.get("order_mentor"));
            Double result1 = orderMentor.intValue() == 0 ? null :
                    orderStudent.divide(orderMentor, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            chart.put("order_propotion", result1);

            BigDecimal orderStudentD = new BigDecimal((int) chart.get("order_student_d"));
            BigDecimal orderMentorD = new BigDecimal((int) chart.get("order_mentor_d"));
            Double result2 = orderMentorD.intValue() == 0 ? null :
                    orderStudentD.divide(orderMentorD, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            chart.put("order_propotion_d", result2);

            BigDecimal orderStudentM = new BigDecimal((int) chart.get("order_student_m"));
            BigDecimal orderMentorM = new BigDecimal((int) chart.get("order_mentor_m"));
            Double result3 = orderMentorM.intValue() == 0 ? null :
                    orderStudentM.divide(orderMentorM, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            chart.put("order_propotion_m", result3);

            data.add(chart);
        }

        data.stream().sorted(
                (m1, m2) -> (int) m1.get("order_mentor") - (int) m2.get("order_mentor")
        ).collect(Collectors.toList());

        return new MentorDetailInfoDTO(data);
    }

    @Override
    public List<MentorRadarResponse> radarDetail(List<String> affiliationIds) {

        String arrayStr = "";
        if(affiliationIds == null) {
            arrayStr = "IS NOT NULL";
        } else if(affiliationIds.size() == 0) {
            return new ArrayList<MentorRadarResponse>();
        } else {
            List<String> fieldIdsQuotes = new ArrayList<String>();
            for(int i=0;i<affiliationIds.size();i++)
            {
                fieldIdsQuotes.add(" \"" + affiliationIds.get(i) + "\" ");
            }
            arrayStr = " in (" + String.join(",", fieldIdsQuotes) +  ") ";
        }
        Map<String, Object> parmas = new HashMap<String, Object>();
        parmas.put("arrayStr", arrayStr);
        List<Map<String, Object>> res = mentorRadarManualMapper.mentorRadarQuery(parmas);

        List<MentorRadarResponse> mentorRadarResponseList = new ArrayList<MentorRadarResponse>();
        for(Map<String, Object> map : res) {
            MentorRadarResponse oneResponse = new MentorRadarResponse();
            oneResponse.setUnit(map.get("jgmc").toString());
            oneResponse.setJgbh(map.get("jgbh").toString());

            List<Map> values = new ArrayList<>();

            HashMap<String, Object> oneValue1 = new HashMap<String, Object>();
            oneValue1.put("name", "人才比");
            oneValue1.put("value", map.get("zb_rc"));
            values.add(oneValue1);

            HashMap<String, Object> oneValue2 = new HashMap<String, Object>();
            oneValue2.put("name", "导师总数");
            oneValue2.put("value", map.get("ds"));
            values.add(oneValue2);

            HashMap<String, Object> oneValue3 = new HashMap<String, Object>();
            oneValue3.put("name", "平均百名学生管理人员数");
            oneValue3.put("value", map.get("zb_glry"));
            values.add(oneValue3);

            HashMap<String, Object> oneValue4 = new HashMap<String, Object>();
            oneValue4.put("name", "师生比");
            oneValue4.put("value", map.get("ssb"));
            values.add(oneValue4);

            HashMap<String, Object> oneValue5 = new HashMap<String, Object>();
            oneValue5.put("name", "省部级教育教学成果奖总数");
            oneValue5.put("value", map.get("jyjx"));
            values.add(oneValue5);

            HashMap<String, Object> oneValue6 = new HashMap<String, Object>();
            oneValue6.put("name", "授课主讲学时总数");
            oneValue6.put("value", map.get("zjxs"));
            values.add(oneValue6);

            HashMap<String, Object> oneValue7 = new HashMap<String, Object>();
            oneValue7.put("name", "授课教师数/导师总数");
            oneValue7.put("value", map.get("zb_skjs"));
            values.add(oneValue7);

            HashMap<String, Object> oneValue8 = new HashMap<String, Object>();
            oneValue8.put("name", "研究生优秀生源率");
            oneValue8.put("value", map.get("zb_yxsy"));
            values.add(oneValue8);

            HashMap<String, Object> oneValue9 = new HashMap<String, Object>();
            oneValue9.put("name", "学生论文总数/学生总数");
            oneValue9.put("value", map.get("zb_xslw"));
            values.add(oneValue9);

            HashMap<String, Object> oneValue10 = new HashMap<String, Object>();
            oneValue10.put("name", "学生论文检索数/学生总数（高质量论文情况）");
            oneValue10.put("value", map.get("zb_xsgzllw"));
            values.add(oneValue10);

            HashMap<String, Object> oneValue11 = new HashMap<String, Object>();
            oneValue11.put("name", "学生获奖/学生总数");
            oneValue11.put("value", map.get("zb_xshj"));
            values.add(oneValue11);

            HashMap<String, Object> oneValue12 = new HashMap<String, Object>();
            oneValue12.put("name", "科技获奖总数");
            oneValue12.put("value", map.get("kjj"));
            values.add(oneValue12);

            oneResponse.setValue(values);
            mentorRadarResponseList.add(oneResponse);
        }
        return mentorRadarResponseList;
    }
}
