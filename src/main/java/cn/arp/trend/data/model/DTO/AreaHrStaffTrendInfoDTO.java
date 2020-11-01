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
public class AreaHrStaffTrendInfoDTO {

    private List<String> yearList;

    private List<Object> sumAry;

    private List<Object> majorAry;

    private List<Object> administrativeAry;

    private List<Object> workerAry;

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

    public AreaHrStaffTrendInfoDTO(List<String> yearList, List<Object> sumAry, List<Object> majorAry, List<Object> administrativeAry, List<Object> workerAry, String updateTime) {
        this.yearList = yearList;
        this.sumAry = sumAry;
        this.majorAry = majorAry;
        this.administrativeAry = administrativeAry;
        this.workerAry = workerAry;
        this.updateTime = updateTime;
    }

    public AreaHrStaffTrendInfoDTO() {
    }
}
