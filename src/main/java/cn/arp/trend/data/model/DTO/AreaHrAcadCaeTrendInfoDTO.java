package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
@ToString
public class AreaHrAcadCaeTrendInfoDTO {

    private List<String> yearList;

    private List<Object> dataList;

    private String updateTime;

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

    public AreaHrAcadCaeTrendInfoDTO() {
    }

    public AreaHrAcadCaeTrendInfoDTO(List<String> yearList, List<Object> dataList, String updateTime) {
        this.yearList = yearList;
        this.dataList = dataList;
        this.updateTime = updateTime;
    }
}
