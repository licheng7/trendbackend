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
public class AreaHrAcadCasTrendInfoDTO {

    private List<String> yearList;

    private List<Object> dataList;

    private String updateTime;

    private List<Map<String, Object>> resultArray;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Map<String, Object>> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<Map<String, Object>> resultArray) {
        this.resultArray = resultArray;
    }

    public AreaHrAcadCasTrendInfoDTO() {
    }

    public AreaHrAcadCasTrendInfoDTO(List<String> yearList, List<Object> dataList, String updateTime, List<Map<String, Object>> resultArray) {
        this.yearList = yearList;
        this.dataList = dataList;
        this.updateTime = updateTime;
        this.resultArray = resultArray;
    }
}
