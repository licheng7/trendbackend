package cn.arp.trend.data.model.response;

import cn.arp.trend.data.model.request.AreaHrRequest;
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
public class AreaHrStaffDistResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("unit_ary")
    private List<Map<String, Object>> unitAry;

    @JsonProperty("age_ary")
    private Map<String, List<Object>> ageAry;

    @JsonProperty("entirety_ary")
    private Map<String, List<Map<String, Object>>> entiretyAry;

    @JsonProperty("position_ary")
    private Map<String, List<Map<String, Object>>> positionAry;

    @JsonProperty("education_ary")
    private Map<String, List<Object>> educationAry;

    @JsonProperty("title_ary")
    private Map<String, List<Map<String, Object>>> titleAry;

    @JsonProperty("result_array")
    private List<List<Map<String, Object>>> resultArray;

    @JsonProperty("update_time")
    private String updateTime;

    private AreaHrRequest body;

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

    public AreaHrRequest getBody() {
        return body;
    }

    public void setBody(AreaHrRequest body) {
        this.body = body;
    }

    public AreaHrStaffDistResponse(List<Map<String, Object>> unitAry, Map<String, List<Object>> ageAry, Map<String, List<Map<String, Object>>> entiretyAry, Map<String, List<Map<String, Object>>> positionAry, Map<String, List<Object>> educationAry, Map<String, List<Map<String, Object>>> titleAry, List<List<Map<String, Object>>> resultArray, String updateTime, AreaHrRequest body) {
        this.unitAry = unitAry;
        this.ageAry = ageAry;
        this.entiretyAry = entiretyAry;
        this.positionAry = positionAry;
        this.educationAry = educationAry;
        this.titleAry = titleAry;
        this.resultArray = resultArray;
        this.updateTime = updateTime;
        this.body = body;
    }

    public AreaHrStaffDistResponse() {
    }
}
