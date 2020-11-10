package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AreaEduQueryDO;
import cn.arp.trend.data.model.DTO.AreaEduDInfoDTO;
import cn.arp.trend.data.model.DTO.AreaEduMInfoDTO;
import cn.arp.trend.data.model.DTO.AreaEduStudentInfoDTO;
import cn.arp.trend.repository.biz.manual.CasEduFTeacherInfoManualMapper;
import cn.arp.trend.repository.biz.manual.RefEduEnrollmentManualMapper;
import cn.arp.trend.repository.biz.manual.StatTeacherStudentManualMapper;
import cn.arp.trend.service.biz.AreaEduService;
import cn.arp.trend.service.biz.common.ConcurrentSupport;
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
 * Date:2020/10/30
 * Time:上午7:28
 **/
@Service
public class AreaEduServiceImpl extends ConcurrentSupport implements AreaEduService {

    @Resource
    private CasEduFTeacherInfoManualMapper casEduFTeacherInfoManualMapper;

    @Resource
    private StatTeacherStudentManualMapper statTeacherStudentManualMapper;

    @Resource
    private RefEduEnrollmentManualMapper refEduEnrollmentManualMapper;

    @Override
    public AreaEduDInfoDTO areaEduDQuery(AreaEduQueryDO query) {

        return this.areaEduDQueryByCs(query);
    }

    @Override
    public AreaEduMInfoDTO areaEduMQuery(AreaEduQueryDO query) {

        return this.areaEduMQueryByCs(query);
    }

    @Override
    public AreaEduStudentInfoDTO areaEduStudentQuery(AreaEduQueryDO query) {

        return this.areaEduStudentQueryByCs(query);
    }



    private AreaEduDInfoDTO areaEduDQueryByCs(AreaEduQueryDO query) {

        ConcurrentSupport.ConcurrentSupportContext context = this.initConcurrentSupportContext();

        ConcurrentSupport.Task<List<Map<String, Object>>> task1 =
                new Task<>("queryEdu4D1",
                        () -> statTeacherStudentManualMapper.queryEdu4D1(query));
        context.registerTask(task1);

        ConcurrentSupport.Task<List<Map<String, Object>>> task2 =
                new Task<>("queryEdu4D2",
                        () -> statTeacherStudentManualMapper.queryEdu4D2(query));
        context.registerTask(task2);

        ConcurrentSupport.Task<List<Map<String, Object>>> task3 =
                new Task<>("queryEdu4D3",
                        () -> statTeacherStudentManualMapper.queryEdu4D3(query));
        context.registerTask(task3);

        ConcurrentSupport.Task<List<Map<String, Object>>> task4 =
                new Task<>("queryEdu4D4",
                        () -> statTeacherStudentManualMapper.queryEdu4D4(query));
        context.registerTask(task4);

        ConcurrentSupport.Task<List<Map<String, Object>>> task5 =
                new Task<>("queryEdu4D5",
                        () -> casEduFTeacherInfoManualMapper.queryEdu4D5(query));
        context.registerTask(task5);

        context.taskExecute();
        context.await();

        if(!context.isSuccess()) {
            throw new RuntimeException(context.getError());
        }
        Map<String, List> queryResult = context.getResult();

        List<Map<String, Object>> doctorTeacherOriginal = queryResult.get(task1.getTaskName());
        List<Map<String, Object>> doctorTeacherNumberOriginal = queryResult.get(task2.getTaskName());
        List<Map<String, Object>> doctorTeacherSumOriginal = queryResult.get(task3.getTaskName());
        List<Map<String, Object>> doctorTeacherUnitOriginal = queryResult.get(task4.getTaskName());
        List<Map<String, Object>> doctorTeacherYearOriginal = queryResult.get(task5.getTaskName());

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> doctorTeacherOriginalMap = Maps.newHashMap();
        doctorTeacherOriginal.stream().forEach(map ->
                doctorTeacherOriginalMap.put((String) map.get("nf"), map));

        List<Integer> doctorTeacherList = Lists.newArrayList();
        yearList.stream().forEach(year ->
                doctorTeacherList.add(doctorTeacherOriginalMap.get(year) == null ? null :
                        ((Number) doctorTeacherOriginalMap.get(year).get("rs")).intValue()));

        List<Map<String, Object>> doctorTeacherUnitList
                = doctorTeacherUnitOriginal.stream().map(map -> {
            Map<String, Object> value = Maps.newHashMap();
            value.put("name", map.get("jgmc"));
            value.put("value", map.get("rs"));
            value.put("id", map.get("jgbh"));
            return value;
        }).collect(Collectors.toList());

        List<Object> year = Lists.newArrayList();
        List<Object> number = Lists.newArrayList();
        doctorTeacherYearOriginal.stream().forEach(map -> {
            year.add(map.get("age"));
            number.add(map.get("number"));
        });
        Map<String, List<Object>> doctorTeacherYear = Maps.newHashMap();
        doctorTeacherYear.put("year", year);
        doctorTeacherYear.put("number", number);

        Map<String, Object> doctorTeacherPie = Maps.newHashMap();
        doctorTeacherPie.put("sum", doctorTeacherSumOriginal.get(0).get("rs"));
        doctorTeacherPie.put("number", doctorTeacherNumberOriginal.get(0).get("rs"));
        //doctorTeacherPie.put("sum", doctorTeacherNumberOriginal.get(0).get("rs"));
        //doctorTeacherPie.put("number", doctorTeacherSumOriginal.get(0).get("rs"));

        AreaEduDInfoDTO info = new AreaEduDInfoDTO();
        info.setYearList(yearList);
        info.setDoctorTeacherList(doctorTeacherList);
        info.setDoctorTeacherUnitList(doctorTeacherUnitList);
        info.setDoctorTeacherYear(doctorTeacherYear);
        info.setDoctorTeacherPie(doctorTeacherPie);

        return info;
    }

    private AreaEduMInfoDTO areaEduMQueryByCs(AreaEduQueryDO query) {

        ConcurrentSupport.ConcurrentSupportContext context = this.initConcurrentSupportContext();

        ConcurrentSupport.Task<List<Map<String, Object>>> task1 =
                new Task<>("queryEdu4M1",
                        () -> statTeacherStudentManualMapper.queryEdu4M1(query));
        context.registerTask(task1);

        ConcurrentSupport.Task<List<Map<String, Object>>> task2 =
                new Task<>("queryEdu4M2",
                        () -> statTeacherStudentManualMapper.queryEdu4M2(query));
        context.registerTask(task2);

        ConcurrentSupport.Task<List<Map<String, Object>>> task3 =
                new Task<>("queryEdu4M3",
                        () -> statTeacherStudentManualMapper.queryEdu4M3(query));
        context.registerTask(task3);

        ConcurrentSupport.Task<List<Map<String, Object>>> task4 =
                new Task<>("queryEdu4M4",
                        () -> statTeacherStudentManualMapper.queryEdu4M4(query));
        context.registerTask(task4);

        ConcurrentSupport.Task<List<Map<String, Object>>> task5 =
                new Task<>("queryEdu4M5",
                        () -> casEduFTeacherInfoManualMapper.queryEdu4M5(query));
        context.registerTask(task5);

        context.taskExecute();
        context.await();

        if(!context.isSuccess()) {
            throw new RuntimeException(context.getError());
        }
        Map<String, List> queryResult = context.getResult();

        List<Map<String, Object>> masterTeacherOriginal = queryResult.get(task1.getTaskName());
        List<Map<String, Object>> masterTeacherNumberOriginal = queryResult.get(task2.getTaskName());
        List<Map<String, Object>> masterTeacherSumOriginal = queryResult.get(task3.getTaskName());
        List<Map<String, Object>> masterTeacherUnitOriginal = queryResult.get(task4.getTaskName());
        List<Map<String, Object>> masterTeacherYearOriginal = queryResult.get(task5.getTaskName());

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> masterTeacherOriginalMap = Maps.newHashMap();
        masterTeacherOriginal.stream().forEach(map ->
                masterTeacherOriginalMap.put((String) map.get("nf"), map));

        List<Integer> masterTeacherList = Lists.newArrayList();
        yearList.stream().forEach(year ->
                masterTeacherList.add(masterTeacherOriginalMap.get(year) == null ? null :
                        ((Number) masterTeacherOriginalMap.get(year).get("rs")).intValue()));

        List<Map<String, Object>> masterTeacherUnitList
                = masterTeacherUnitOriginal.stream().map(map -> {
            Map<String, Object> value = Maps.newHashMap();
            value.put("name", map.get("jgmc"));
            value.put("value", map.get("rs"));
            value.put("id", map.get("jgbh"));
            return value;
        }).collect(Collectors.toList());

        List<Object> year = Lists.newArrayList();
        List<Object> number = Lists.newArrayList();
        masterTeacherYearOriginal.stream().forEach(map -> {
            year.add(map.get("age"));
            number.add(map.get("number"));
        });
        Map<String, List<Object>> masterTeacherYear = Maps.newHashMap();
        masterTeacherYear.put("year", year);
        masterTeacherYear.put("number", number);

        Map<String, Object> doctorTeacherPie = Maps.newHashMap();
        doctorTeacherPie.put("sum", masterTeacherSumOriginal.get(0).get("rs"));
        doctorTeacherPie.put("number", masterTeacherNumberOriginal.get(0).get("rs"));
        //doctorTeacherPie.put("sum", masterTeacherNumberOriginal.get(0).get("rs"));
        //doctorTeacherPie.put("number", masterTeacherSumOriginal.get(0).get("rs"));

        AreaEduMInfoDTO info = new AreaEduMInfoDTO();
        info.setYearList(yearList);
        info.setMasterTeacherList(masterTeacherList);
        info.setMasterTeacherUnitList(masterTeacherUnitList);
        info.setMasterTeacherYear(masterTeacherYear);
        info.setMasterTeacherPie(doctorTeacherPie);

        return info;
    }

    private AreaEduStudentInfoDTO areaEduStudentQueryByCs(AreaEduQueryDO query) {

        ConcurrentSupport.ConcurrentSupportContext context = this.initConcurrentSupportContext();

        ConcurrentSupport.Task<List<Map<String, Object>>> task1 =
                new Task<>("queryEduStudent1",
                        () -> refEduEnrollmentManualMapper.queryEduStudent1(query));
        context.registerTask(task1);

        ConcurrentSupport.Task<List<Map<String, Object>>> task2 =
                new Task<>("queryEduStudent2",
                        () -> refEduEnrollmentManualMapper.queryEduStudent2(query));
        context.registerTask(task2);

        ConcurrentSupport.Task<List<Map<String, Object>>> task3 =
                new Task<>("queryEduStudent3",
                        () -> refEduEnrollmentManualMapper.queryEduStudent3(query));
        context.registerTask(task3);

        ConcurrentSupport.Task<List<Map<String, Object>>> task4 =
                new Task<>("queryEduStudent4",
                        () -> refEduEnrollmentManualMapper.queryEduStudent4(query));
        context.registerTask(task4);

        context.taskExecute();
        context.await();

        if(!context.isSuccess()) {
            throw new RuntimeException(context.getError());
        }
        Map<String, List> queryResult = context.getResult();

        List<Map<String, Object>> doctorOriginalSchool = queryResult.get(task1.getTaskName());
        List<Map<String, Object>> doctorOriginalGraduate = queryResult.get(task2.getTaskName());
        List<Map<String, Object>> masterOriginalSchool = queryResult.get(task3.getTaskName());
        List<Map<String, Object>> masterOriginalGraduate = queryResult.get(task4.getTaskName());

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> doctorOriginalSchoolMap =
                this.bizData4MapByKeyLimit1(doctorOriginalSchool, "nf");

        Map<String, Map<String, Object>> doctorOriginalGraduateMap =
                this.bizData4MapByKeyLimit1(doctorOriginalGraduate, "nf");

        Map<String, Map<String, Object>> masterOriginalSchoolMap =
                this.bizData4MapByKeyLimit1(masterOriginalSchool, "nf");

        Map<String, Map<String, Object>> masterOriginalGraduateMap =
                this.bizData4MapByKeyLimit1(masterOriginalGraduate, "nf");

        List<Object> doctorListSchool = Lists.newArrayList();
        List<Object> doctorListGraduate = Lists.newArrayList();
        List<Object> masterListSchool = Lists.newArrayList();
        List<Object> masterListGraduate = Lists.newArrayList();

        yearList.stream().forEach(year -> {
            Map<String, Object> doctor = doctorOriginalSchoolMap.get(year);
            Map<String, Object> doctorGrd = doctorOriginalGraduateMap.get(year);
            Map<String, Object> master = masterOriginalSchoolMap.get(year);
            Map<String, Object> masterGrd = masterOriginalGraduateMap.get(year);
            doctorListSchool.add(doctor == null ? null : doctor.get("rs"));
            doctorListGraduate.add(doctorGrd == null ? null : doctorGrd.get("rs"));
            masterListSchool.add(master == null ? null : master.get("rs"));
            masterListGraduate.add(masterGrd == null ? null : masterGrd.get("rs"));
        });

        return new AreaEduStudentInfoDTO(
                yearList,
                doctorListSchool,
                doctorListGraduate,
                masterListSchool,
                masterListGraduate
        );
    }






    @Deprecated
    private AreaEduDInfoDTO areaEduDQueryOrigin(AreaEduQueryDO query) {

        List<Map<String, Object>> doctorTeacherOriginal
                = statTeacherStudentManualMapper.queryEdu4D1(query);

        List<Map<String, Object>> doctorTeacherNumberOriginal
                = statTeacherStudentManualMapper.queryEdu4D2(query);

        List<Map<String, Object>> doctorTeacherSumOriginal
                = statTeacherStudentManualMapper.queryEdu4D3(query);

        List<Map<String, Object>> doctorTeacherUnitOriginal
                = statTeacherStudentManualMapper.queryEdu4D4(query);

        List<Map<String, Object>> doctorTeacherYearOriginal
                = casEduFTeacherInfoManualMapper.queryEdu4D5(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> doctorTeacherOriginalMap = Maps.newHashMap();
        doctorTeacherOriginal.stream().forEach(map ->
            doctorTeacherOriginalMap.put((String) map.get("nf"), map));

        List<Integer> doctorTeacherList = Lists.newArrayList();
        yearList.stream().forEach(year ->
            doctorTeacherList.add(doctorTeacherOriginalMap.get(year) == null ? null :
                    ((Number) doctorTeacherOriginalMap.get(year).get("rs")).intValue()));

        List<Map<String, Object>> doctorTeacherUnitList
                = doctorTeacherUnitOriginal.stream().map(map -> {
                    Map<String, Object> value = Maps.newHashMap();
                    value.put("name", map.get("jgmc"));
                    value.put("value", map.get("rs"));
                    value.put("id", map.get("jgbh"));
                    return value;
                }).collect(Collectors.toList());

        List<Object> year = Lists.newArrayList();
        List<Object> number = Lists.newArrayList();
        doctorTeacherYearOriginal.stream().forEach(map -> {
            year.add(map.get("age"));
            number.add(map.get("number"));
        });
        Map<String, List<Object>> doctorTeacherYear = Maps.newHashMap();
        doctorTeacherYear.put("year", year);
        doctorTeacherYear.put("number", number);

        Map<String, Object> doctorTeacherPie = Maps.newHashMap();
        doctorTeacherPie.put("sum", doctorTeacherSumOriginal.get(0).get("rs"));
        doctorTeacherPie.put("number", doctorTeacherNumberOriginal.get(0).get("rs"));

        AreaEduDInfoDTO info = new AreaEduDInfoDTO();
        info.setYearList(yearList);
        info.setDoctorTeacherList(doctorTeacherList);
        info.setDoctorTeacherUnitList(doctorTeacherUnitList);
        info.setDoctorTeacherYear(doctorTeacherYear);
        info.setDoctorTeacherPie(doctorTeacherPie);

        return info;
    }

    @Deprecated
    private AreaEduMInfoDTO areaEduMQueryOrigin(AreaEduQueryDO query) {

        List<Map<String, Object>> masterTeacherOriginal
                = statTeacherStudentManualMapper.queryEdu4M1(query);

        List<Map<String, Object>> masterTeacherNumberOriginal
                = statTeacherStudentManualMapper.queryEdu4M2(query);

        List<Map<String, Object>> masterTeacherSumOriginal
                = statTeacherStudentManualMapper.queryEdu4M3(query);

        List<Map<String, Object>> masterTeacherUnitOriginal
                = statTeacherStudentManualMapper.queryEdu4M4(query);

        List<Map<String, Object>> masterTeacherYearOriginal
                = casEduFTeacherInfoManualMapper.queryEdu4M5(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> masterTeacherOriginalMap = Maps.newHashMap();
        masterTeacherOriginal.stream().forEach(map ->
                masterTeacherOriginalMap.put((String) map.get("nf"), map));

        List<Integer> masterTeacherList = Lists.newArrayList();
        yearList.stream().forEach(year ->
                masterTeacherList.add(masterTeacherOriginalMap.get(year) == null ? null :
                        ((Number) masterTeacherOriginalMap.get(year).get("rs")).intValue()));

        List<Map<String, Object>> masterTeacherUnitList
                = masterTeacherUnitOriginal.stream().map(map -> {
            Map<String, Object> value = Maps.newHashMap();
            value.put("name", map.get("jgmc"));
            value.put("value", map.get("rs"));
            value.put("id", map.get("jgbh"));
            return value;
        }).collect(Collectors.toList());

        List<Object> year = Lists.newArrayList();
        List<Object> number = Lists.newArrayList();
        masterTeacherYearOriginal.stream().forEach(map -> {
            year.add(map.get("age"));
            number.add(map.get("number"));
        });
        Map<String, List<Object>> masterTeacherYear = Maps.newHashMap();
        masterTeacherYear.put("year", year);
        masterTeacherYear.put("number", number);

        Map<String, Object> doctorTeacherPie = Maps.newHashMap();
        doctorTeacherPie.put("sum", masterTeacherSumOriginal.get(0).get("rs"));
        doctorTeacherPie.put("number", masterTeacherNumberOriginal.get(0).get("rs"));

        AreaEduMInfoDTO info = new AreaEduMInfoDTO();
        info.setYearList(yearList);
        info.setMasterTeacherList(masterTeacherList);
        info.setMasterTeacherUnitList(masterTeacherUnitList);
        info.setMasterTeacherYear(masterTeacherYear);
        info.setMasterTeacherPie(doctorTeacherPie);

        return info;
    }

    @Deprecated
    private AreaEduStudentInfoDTO areaEduStudentQueryOrigin(AreaEduQueryDO query) {

        List<Map<String, Object>> doctorOriginalSchool
                = refEduEnrollmentManualMapper.queryEduStudent1(query);

        List<Map<String, Object>> doctorOriginalGraduate
                = refEduEnrollmentManualMapper.queryEduStudent2(query);

        List<Map<String, Object>> masterOriginalSchool
                = refEduEnrollmentManualMapper.queryEduStudent3(query);

        List<Map<String, Object>> masterOriginalGraduate
                = refEduEnrollmentManualMapper.queryEduStudent4(query);

        List<String> yearList = this.buildYearlist(query.getStartYear(), query.getEndYear());

        Map<String, Map<String, Object>> doctorOriginalSchoolMap =
                this.bizData4MapByKeyLimit1(doctorOriginalSchool, "nf");

        Map<String, Map<String, Object>> doctorOriginalGraduateMap =
                this.bizData4MapByKeyLimit1(doctorOriginalGraduate, "nf");

        Map<String, Map<String, Object>> masterOriginalSchoolMap =
                this.bizData4MapByKeyLimit1(masterOriginalSchool, "nf");

        Map<String, Map<String, Object>> masterOriginalGraduateMap =
                this.bizData4MapByKeyLimit1(masterOriginalGraduate, "nf");

        List<Object> doctorListSchool = Lists.newArrayList();
        List<Object> doctorListGraduate = Lists.newArrayList();
        List<Object> masterListSchool = Lists.newArrayList();
        List<Object> masterListGraduate = Lists.newArrayList();

        yearList.stream().forEach(year -> {
            Map<String, Object> doctor = doctorOriginalSchoolMap.get(year);
            Map<String, Object> doctorGrd = doctorOriginalGraduateMap.get(year);
            Map<String, Object> master = masterOriginalSchoolMap.get(year);
            Map<String, Object> masterGrd = masterOriginalGraduateMap.get(year);
            doctorListSchool.add(doctor == null ? null : doctor.get("rs"));
            doctorListGraduate.add(doctorGrd == null ? null : doctorGrd.get("rs"));
            masterListSchool.add(master == null ? null : master.get("rs"));
            masterListGraduate.add(masterGrd == null ? null : masterGrd.get("rs"));
        });

        return new AreaEduStudentInfoDTO(
                yearList,
                doctorListSchool,
                doctorListGraduate,
                masterListSchool,
                masterListGraduate
        );
    }
}
