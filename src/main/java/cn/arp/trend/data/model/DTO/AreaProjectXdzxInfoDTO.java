package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/31
 * Time:下午9:51
 **/
public class AreaProjectXdzxInfoDTO {

    private List<String> yearList;

    private List<Integer> taskNumData;

    private List<Integer> expenditureNumData;

    private List<Map<String, Object>> unitTaskData;

    private List<Map<String, Object>> unitExpenditureData;

    private String updateTime;

    private Map<String, Object> taskTotal;

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
}
