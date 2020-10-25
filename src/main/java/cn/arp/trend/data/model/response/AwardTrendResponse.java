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
public class AwardTrendResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("update_time")
    private String updateTime;

    @JsonProperty("year_list")
    private List<String> yearList;

    @JsonProperty("state_tendency")
    private List<Integer> stateTendency;

    @JsonProperty("else_tendency")
    private List<Integer> elseTendency;

    @JsonProperty("sum_tendency")
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

    public AwardTrendResponse() {
    }

    public AwardTrendResponse(String updateTime, List<String> yearList, List<Integer> stateTendency, List<Integer> elseTendency, List<Integer> sumTendency) {
        this.updateTime = updateTime;
        this.yearList = yearList;
        this.stateTendency = stateTendency;
        this.elseTendency = elseTendency;
        this.sumTendency = sumTendency;
    }
}
