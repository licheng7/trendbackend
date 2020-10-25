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
public class DetailAwardTrendInfoDTO {

    private String updateTime;

    private List<String> yearList;

    private List<Integer> stateTendency;

    private List<Integer> elseTendency;

    private List<Integer> sumTendency;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<Integer> getStateTendency() {
        return stateTendency;
    }

    public void setStateTendency(List<Integer> stateTendency) {
        this.stateTendency = stateTendency;
    }

    public List<Integer> getElseTendency() {
        return elseTendency;
    }

    public void setElseTendency(List<Integer> elseTendency) {
        this.elseTendency = elseTendency;
    }

    public List<Integer> getSumTendency() {
        return sumTendency;
    }

    public void setSumTendency(List<Integer> sumTendency) {
        this.sumTendency = sumTendency;
    }

    public DetailAwardTrendInfoDTO(String updateTime, List<String> yearList, List<Integer> stateTendency, List<Integer> elseTendency, List<Integer> sumTendency) {
        this.updateTime = updateTime;
        this.yearList = yearList;
        this.stateTendency = stateTendency;
        this.elseTendency = elseTendency;
        this.sumTendency = sumTendency;
    }

    public DetailAwardTrendInfoDTO() {
    }
}
