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
public class AreaProjectZdyfResponse implements Serializable {

    private static final long serialVersionUID = -2177081419249257911L;

    @JsonProperty("year_list")
    private List<String> yearList;

    @JsonProperty("task_num_data")
    private List<Integer> taskNumData;

    @JsonProperty("expenditure_num_data")
    private List<Integer> expenditureNumData;

    @JsonProperty("unit_task_data")
    private List<Map<String, Object>> unitTaskData;

    @JsonProperty("unit_expenditure_data")
    private List<Map<String, Object>> unitExpenditureData;

    @JsonProperty("update_time")
    private String updateTime;

    @JsonProperty("task_total")
    private Map<String, Object> taskTotal;

    @JsonProperty("expenditure_total")
    private Map<String, Object> expenditureTotal;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<Integer> getTaskNumData() {
        return taskNumData;
    }

    public void setTaskNumData(List<Integer> taskNumData) {
        this.taskNumData = taskNumData;
    }

    public List<Integer> getExpenditureNumData() {
        return expenditureNumData;
    }

    public void setExpenditureNumData(List<Integer> expenditureNumData) {
        this.expenditureNumData = expenditureNumData;
    }

    public List<Map<String, Object>> getUnitTaskData() {
        return unitTaskData;
    }

    public void setUnitTaskData(List<Map<String, Object>> unitTaskData) {
        this.unitTaskData = unitTaskData;
    }

    public List<Map<String, Object>> getUnitExpenditureData() {
        return unitExpenditureData;
    }

    public void setUnitExpenditureData(List<Map<String, Object>> unitExpenditureData) {
        this.unitExpenditureData = unitExpenditureData;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Map<String, Object> getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(Map<String, Object> taskTotal) {
        this.taskTotal = taskTotal;
    }

    public Map<String, Object> getExpenditureTotal() {
        return expenditureTotal;
    }

    public void setExpenditureTotal(Map<String, Object> expenditureTotal) {
        this.expenditureTotal = expenditureTotal;
    }

    public AreaProjectZdyfResponse(List<String> yearList, List<Integer> taskNumData, List<Integer> expenditureNumData, List<Map<String, Object>> unitTaskData, List<Map<String, Object>> unitExpenditureData, String updateTime, Map<String, Object> taskTotal, Map<String, Object> expenditureTotal) {
        this.yearList = yearList;
        this.taskNumData = taskNumData;
        this.expenditureNumData = expenditureNumData;
        this.unitTaskData = unitTaskData;
        this.unitExpenditureData = unitExpenditureData;
        this.updateTime = updateTime;
        this.taskTotal = taskTotal;
        this.expenditureTotal = expenditureTotal;
    }

    public AreaProjectZdyfResponse() {
    }
}
