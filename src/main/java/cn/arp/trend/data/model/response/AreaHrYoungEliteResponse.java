package cn.arp.trend.data.model.response;

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
public class AreaHrYoungEliteResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("year_list")
    private List<String> yearList;

    @JsonProperty("rs_young_list")
    private List<Object> rsYoungList;

    @JsonProperty("rs_staff_list")
    private List<Object> rsStaffList;

    @JsonProperty("unit_list")
    private List<Map<String, Object>> unitList;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<Object> getRsYoungList() {
        return rsYoungList;
    }

    public void setRsYoungList(List<Object> rsYoungList) {
        this.rsYoungList = rsYoungList;
    }

    public List<Object> getRsStaffList() {
        return rsStaffList;
    }

    public void setRsStaffList(List<Object> rsStaffList) {
        this.rsStaffList = rsStaffList;
    }

    public List<Map<String, Object>> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Map<String, Object>> unitList) {
        this.unitList = unitList;
    }

    public AreaHrYoungEliteResponse() {
    }

    public AreaHrYoungEliteResponse(List<String> yearList, List<Object> rsYoungList, List<Object> rsStaffList, List<Map<String, Object>> unitList) {
        this.yearList = yearList;
        this.rsYoungList = rsYoungList;
        this.rsStaffList = rsStaffList;
        this.unitList = unitList;
    }
}
