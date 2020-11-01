package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class AreaEduDResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("year_list")
    private List<String> yearList;

    @JsonProperty("doctor_teacher_ary")
    private List<Integer> doctorTeacherList;

    @JsonProperty("doctor_teacher_unit")
    private List<Map<String, Object>> doctorTeacherUnitList;

    @JsonProperty("doctor_teacher_year")
    private Map<String, List<Object>> doctorTeacherYear;

    @JsonProperty("doctor_teacher_pie")
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

    public AreaEduDResponse(List<String> yearList, List<Integer> doctorTeacherList, List<Map<String, Object>> doctorTeacherUnitList, Map<String, List<Object>> doctorTeacherYear, Map<String, Object> doctorTeacherPie) {
        this.yearList = yearList;
        this.doctorTeacherList = doctorTeacherList;
        this.doctorTeacherUnitList = doctorTeacherUnitList;
        this.doctorTeacherYear = doctorTeacherYear;
        this.doctorTeacherPie = doctorTeacherPie;
    }

    public AreaEduDResponse() {
    }
}
