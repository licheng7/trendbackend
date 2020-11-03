package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/31
 * Time:下午9:51
 **/
public class AreaProjectNsfcDistInfoDTO {

    private List<Map<String, Object>> unitProjectData;

    private List<Map<String, Object>> unitExpenditureData;

    private Map<String, Object> projectTotal;

    private Map<String, Object> expenditureTotal;

    private String updateTime;

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
}
