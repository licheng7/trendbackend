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
public class AreaHrStaffDistInfoDTO {

    private List<Map<String, Object>> unitAry;

    private Map<String, List<Object>> ageAry;

    private Map<String, List<Map<String, Object>>> entiretyAry;

    private Map<String, List<Map<String, Object>>> positionAry;

    private Map<String, List<Object>> educationAry;

    private Map<String, List<Map<String, Object>>> titleAry;

    private List<List<Map<String, Object>>> resultArray;

    private String updateTime;

    public List<Map<String, Object>> getUnitAry() {
        return unitAry;
    }

    public void setUnitAry(List<Map<String, Object>> unitAry) {
        this.unitAry = unitAry;
    }

    public Map<String, List<Object>> getAgeAry() {
        return ageAry;
    }

    public void setAgeAry(Map<String, List<Object>> ageAry) {
        this.ageAry = ageAry;
    }

    public Map<String, List<Map<String, Object>>> getEntiretyAry() {
        return entiretyAry;
    }

    public void setEntiretyAry(Map<String, List<Map<String, Object>>> entiretyAry) {
        this.entiretyAry = entiretyAry;
    }

    public Map<String, List<Map<String, Object>>> getPositionAry() {
        return positionAry;
    }

    public void setPositionAry(Map<String, List<Map<String, Object>>> positionAry) {
        this.positionAry = positionAry;
    }

    public Map<String, List<Object>> getEducationAry() {
        return educationAry;
    }

    public void setEducationAry(Map<String, List<Object>> educationAry) {
        this.educationAry = educationAry;
    }

    public Map<String, List<Map<String, Object>>> getTitleAry() {
        return titleAry;
    }

    public void setTitleAry(Map<String, List<Map<String, Object>>> titleAry) {
        this.titleAry = titleAry;
    }

    public List<List<Map<String, Object>>> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<List<Map<String, Object>>> resultArray) {
        this.resultArray = resultArray;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
