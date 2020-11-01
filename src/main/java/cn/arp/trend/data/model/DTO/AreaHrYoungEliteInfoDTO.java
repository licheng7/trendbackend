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
public class AreaHrYoungEliteInfoDTO {

    private List<String> yearList;

    private List<Object> rsYoungList;

    private List<Object> rsStaffList;

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

    public AreaHrYoungEliteInfoDTO(List<String> yearList, List<Object> rsYoungList, List<Object> rsStaffList, List<Map<String, Object>> unitList) {
        this.yearList = yearList;
        this.rsYoungList = rsYoungList;
        this.rsStaffList = rsStaffList;
        this.unitList = unitList;
    }

    public AreaHrYoungEliteInfoDTO() {
    }
}
