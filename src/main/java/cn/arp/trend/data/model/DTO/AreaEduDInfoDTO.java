package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
@ToString
public class AreaEduDInfoDTO {

    private List<String> yearList;

    private List<Integer> doctorTeacherList;

    private List<Map<String, Object>> doctorTeacherUnitList;

    private Map<String, List<Object>> doctorTeacherYear;

    private Map<String, Object> doctorTeacherPie;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<Integer> getDoctorTeacherList() {
        return doctorTeacherList;
    }

    public void setDoctorTeacherList(List<Integer> doctorTeacherList) {
        this.doctorTeacherList = doctorTeacherList;
    }

    public List<Map<String, Object>> getDoctorTeacherUnitList() {
        return doctorTeacherUnitList;
    }

    public void setDoctorTeacherUnitList(List<Map<String, Object>> doctorTeacherUnitList) {
        this.doctorTeacherUnitList = doctorTeacherUnitList;
    }

    public Map<String, List<Object>> getDoctorTeacherYear() {
        return doctorTeacherYear;
    }

    public void setDoctorTeacherYear(Map<String, List<Object>> doctorTeacherYear) {
        this.doctorTeacherYear = doctorTeacherYear;
    }

    public Map<String, Object> getDoctorTeacherPie() {
        return doctorTeacherPie;
    }

    public void setDoctorTeacherPie(Map<String, Object> doctorTeacherPie) {
        this.doctorTeacherPie = doctorTeacherPie;
    }
}
