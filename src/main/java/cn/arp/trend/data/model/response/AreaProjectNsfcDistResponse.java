package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/11/1
 * Time:上午2:00
 **/
public class AreaProjectNsfcDistResponse implements Serializable {

    private static final long serialVersionUID = -2177081419249257911L;

    @JsonProperty("unit_project_data")
    private List<Map<String, Object>> unitProjectData;

    @JsonProperty("unit_expenditure_data")
    private List<Map<String, Object>> unitExpenditureData;

    @JsonProperty("project_total")
    private Map<String, Object> projectTotal;

    @JsonProperty("expenditure_total")
    private Map<String, Object> expenditureTotal;

    @JsonProperty("update_time")
    private String updateTime;

    @JsonProperty("result_array")
    private List<List<Map<String, Object>>> resultArray;

    public List<Map<String, Object>> getUnitProjectData() {
        return unitProjectData;
    }

    public void setUnitProjectData(List<Map<String, Object>> unitProjectData) {
        this.unitProjectData = unitProjectData;
    }

    public List<Map<String, Object>> getUnitExpenditureData() {
        return unitExpenditureData;
    }

    public void setUnitExpenditureData(List<Map<String, Object>> unitExpenditureData) {
        this.unitExpenditureData = unitExpenditureData;
    }

    public Map<String, Object> getProjectTotal() {
        return projectTotal;
    }

    public void setProjectTotal(Map<String, Object> projectTotal) {
        this.projectTotal = projectTotal;
    }

    public Map<String, Object> getExpenditureTotal() {
        return expenditureTotal;
    }

    public void setExpenditureTotal(Map<String, Object> expenditureTotal) {
        this.expenditureTotal = expenditureTotal;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<List<Map<String, Object>>> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<List<Map<String, Object>>> resultArray) {
        this.resultArray = resultArray;
    }

    public AreaProjectNsfcDistResponse(List<Map<String, Object>> unitProjectData, List<Map<String, Object>> unitExpenditureData, Map<String, Object> projectTotal, Map<String, Object> expenditureTotal, String updateTime, List<List<Map<String, Object>>> resultArray) {
        this.unitProjectData = unitProjectData;
        this.unitExpenditureData = unitExpenditureData;
        this.projectTotal = projectTotal;
        this.expenditureTotal = expenditureTotal;
        this.updateTime = updateTime;
        this.resultArray = resultArray;
    }

    public AreaProjectNsfcDistResponse() {
    }
}
