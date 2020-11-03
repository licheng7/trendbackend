package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/31
 * Time:下午9:51
 **/
public class AreaProjectNsfcTrendInfoDTO {

    private List<String> yearList;

    private List<Integer> projectNumData;

    private List<Integer> expenditureNumData;

    private List<Integer> ARPProjectNumData;

    private List<Integer> ARPExpenditureNumData;

    private String updateTime;

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
}
