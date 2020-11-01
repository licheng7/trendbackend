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
public class AreaEduMInfoDTO {

    private List<String> yearList;

    private List<Integer> masterTeacherList;

    private List<Map<String, Object>> masterTeacherUnitList;

    private Map<String, List<Object>> masterTeacherYear;

    private Map<String, Object> masterTeacherPie;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<Integer> getMasterTeacherList() {
        return masterTeacherList;
    }

    public void setMasterTeacherList(List<Integer> masterTeacherList) {
        this.masterTeacherList = masterTeacherList;
    }

    public List<Map<String, Object>> getMasterTeacherUnitList() {
        return masterTeacherUnitList;
    }

    public void setMasterTeacherUnitList(List<Map<String, Object>> masterTeacherUnitList) {
        this.masterTeacherUnitList = masterTeacherUnitList;
    }

    public Map<String, List<Object>> getMasterTeacherYear() {
        return masterTeacherYear;
    }

    public void setMasterTeacherYear(Map<String, List<Object>> masterTeacherYear) {
        this.masterTeacherYear = masterTeacherYear;
    }

    public Map<String, Object> getMasterTeacherPie() {
        return masterTeacherPie;
    }

    public void setMasterTeacherPie(Map<String, Object> masterTeacherPie) {
        this.masterTeacherPie = masterTeacherPie;
    }

    public AreaEduMInfoDTO(List<String> yearList, List<Integer> masterTeacherList, List<Map<String, Object>> masterTeacherUnitList, Map<String, List<Object>> masterTeacherYear, Map<String, Object> masterTeacherPie) {
        this.yearList = yearList;
        this.masterTeacherList = masterTeacherList;
        this.masterTeacherUnitList = masterTeacherUnitList;
        this.masterTeacherYear = masterTeacherYear;
        this.masterTeacherPie = masterTeacherPie;
    }

    public AreaEduMInfoDTO() {
    }
}
