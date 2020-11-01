package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class AreaHrAcadCaeTrendResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("year_list")
    private List<String> yearList;

    @JsonProperty("data_list")
    private List<Object> dataList;

    @JsonProperty("update_time")
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

    public AreaHrAcadCaeTrendResponse(List<String> yearList, List<Object> dataList, String updateTime) {
        this.yearList = yearList;
        this.dataList = dataList;
        this.updateTime = updateTime;
    }

    public AreaHrAcadCaeTrendResponse() {
    }
}
