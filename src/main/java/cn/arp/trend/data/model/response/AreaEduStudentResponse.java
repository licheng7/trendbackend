package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class AreaEduStudentResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("year_list")
    private List<String> yearList;

    @JsonProperty("doctor_list_school")
    private List<Object> doctorListSchool;

    @JsonProperty("doctor_list_graduate")
    private List<Object> doctorListGraduate;

    @JsonProperty("master_list_school")
    private List<Object> masterListSchool;

    @JsonProperty("master_list_graduate")
    private List<Object> masterListGraduate;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<Object> getDoctorListSchool() {
        return doctorListSchool;
    }

    public void setDoctorListSchool(List<Object> doctorListSchool) {
        this.doctorListSchool = doctorListSchool;
    }

    public List<Object> getDoctorListGraduate() {
        return doctorListGraduate;
    }

    public void setDoctorListGraduate(List<Object> doctorListGraduate) {
        this.doctorListGraduate = doctorListGraduate;
    }

    public List<Object> getMasterListSchool() {
        return masterListSchool;
    }

    public void setMasterListSchool(List<Object> masterListSchool) {
        this.masterListSchool = masterListSchool;
    }

    public List<Object> getMasterListGraduate() {
        return masterListGraduate;
    }

    public void setMasterListGraduate(List<Object> masterListGraduate) {
        this.masterListGraduate = masterListGraduate;
    }

    public AreaEduStudentResponse(List<String> yearList, List<Object> doctorListSchool, List<Object> doctorListGraduate, List<Object> masterListSchool, List<Object> masterListGraduate) {
        this.yearList = yearList;
        this.doctorListSchool = doctorListSchool;
        this.doctorListGraduate = doctorListGraduate;
        this.masterListSchool = masterListSchool;
        this.masterListGraduate = masterListGraduate;
    }

    public AreaEduStudentResponse() {
    }
}
