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
public class AreaHrStaffTrendResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("year_list")
    private List<String> yearList;

    @JsonProperty("sum_ary")
    private List<Object> sumAry;

    @JsonProperty("major_ary")
    private List<Object> majorAry;

    @JsonProperty("administrative_ary")
    private List<Object> administrativeAry;

    @JsonProperty("worker_ary")
    private List<Object> workerAry;

    @JsonProperty("update_time")
    private String updateTime;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<Object> getSumAry() {
        return sumAry;
    }

    public void setSumAry(List<Object> sumAry) {
        this.sumAry = sumAry;
    }

    public List<Object> getMajorAry() {
        return majorAry;
    }

    public void setMajorAry(List<Object> majorAry) {
        this.majorAry = majorAry;
    }

    public List<Object> getAdministrativeAry() {
        return administrativeAry;
    }

    public void setAdministrativeAry(List<Object> administrativeAry) {
        this.administrativeAry = administrativeAry;
    }

    public List<Object> getWorkerAry() {
        return workerAry;
    }

    public void setWorkerAry(List<Object> workerAry) {
        this.workerAry = workerAry;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public AreaHrStaffTrendResponse(List<String> yearList, List<Object> sumAry, List<Object> majorAry, List<Object> administrativeAry, List<Object> workerAry, String updateTime) {
        this.yearList = yearList;
        this.sumAry = sumAry;
        this.majorAry = majorAry;
        this.administrativeAry = administrativeAry;
        this.workerAry = workerAry;
        this.updateTime = updateTime;
    }

    public AreaHrStaffTrendResponse() {
    }
}
