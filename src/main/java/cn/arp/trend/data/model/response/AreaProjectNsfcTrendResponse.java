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
public class AreaProjectNsfcTrendResponse implements Serializable {

    private static final long serialVersionUID = -2177081419249257911L;

    @JsonProperty("year_list")
    private List<String> yearList;

    @JsonProperty("project_num_data")
    private List<Integer> projectNumData;

    @JsonProperty("expenditure_num_data")
    private List<Integer> expenditureNumData;

    @JsonProperty("ARP_project_num_data")
    private List<Integer> ARPProjectNumData;

    @JsonProperty("ARP_expenditure_num_data")
    private List<Integer> ARPExpenditureNumData;

    @JsonProperty("update_time")
    private String updateTime;

    @JsonProperty("result_array")
    private List<List<Map<String, Object>>> resultArray;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<Integer> getProjectNumData() {
        return projectNumData;
    }

    public void setProjectNumData(List<Integer> projectNumData) {
        this.projectNumData = projectNumData;
    }

    public List<Integer> getExpenditureNumData() {
        return expenditureNumData;
    }

    public void setExpenditureNumData(List<Integer> expenditureNumData) {
        this.expenditureNumData = expenditureNumData;
    }

    public List<Integer> getARPProjectNumData() {
        return ARPProjectNumData;
    }

    public void setARPProjectNumData(List<Integer> ARPProjectNumData) {
        this.ARPProjectNumData = ARPProjectNumData;
    }

    public List<Integer> getARPExpenditureNumData() {
        return ARPExpenditureNumData;
    }

    public void setARPExpenditureNumData(List<Integer> ARPExpenditureNumData) {
        this.ARPExpenditureNumData = ARPExpenditureNumData;
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

    public AreaProjectNsfcTrendResponse(List<String> yearList, List<Integer> projectNumData, List<Integer> expenditureNumData, List<Integer> ARPProjectNumData, List<Integer> ARPExpenditureNumData, String updateTime, List<List<Map<String, Object>>> resultArray) {
        this.yearList = yearList;
        this.projectNumData = projectNumData;
        this.expenditureNumData = expenditureNumData;
        this.ARPProjectNumData = ARPProjectNumData;
        this.ARPExpenditureNumData = ARPExpenditureNumData;
        this.updateTime = updateTime;
        this.resultArray = resultArray;
    }

    public AreaProjectNsfcTrendResponse() {
    }
}
