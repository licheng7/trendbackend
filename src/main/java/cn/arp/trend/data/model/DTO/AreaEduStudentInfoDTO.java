package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
@ToString
public class AreaEduStudentInfoDTO {

    private List<String> yearList;

    private List<Object> doctorListSchool;

    private List<Object> doctorListGraduate;

    private List<Object> masterListSchool;

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

    public AreaEduStudentInfoDTO(List<String> yearList, List<Object> doctorListSchool, List<Object> doctorListGraduate, List<Object> masterListSchool, List<Object> masterListGraduate) {
        this.yearList = yearList;
        this.doctorListSchool = doctorListSchool;
        this.doctorListGraduate = doctorListGraduate;
        this.masterListSchool = masterListSchool;
        this.masterListGraduate = masterListGraduate;
    }

    public AreaEduStudentInfoDTO() {
    }
}
