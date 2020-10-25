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
public class AwardDetailResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("he__pie")
    private List<Map<String, Object>> hePie;

    @JsonProperty("future_pie")
    private List<Map<String, Object>> futurePie;

    @JsonProperty("outstanding_pie")
    private List<Map<String, Object>> outstandingPie;

    @JsonProperty("progress_pie")
    private List<Map<String, Object>> progressPie;

    @JsonProperty("nature_pie")
    private List<Map<String, Object>> naturePie;

    @JsonProperty("invent_pie")
    private List<Map<String, Object>> inventPie;

    @JsonProperty("highest_list")
    private List<Map<String, Object>> highestList;

    @JsonProperty("highest_mun")
    private int highestMun;

    @JsonProperty("other_num")
    int otherNum;

    @JsonProperty("state_num")
    int stateNum;

    public List<Map<String, Object>> getHePie() {
        return hePie;
    }

    public void setHePie(List<Map<String, Object>> hePie) {
        this.hePie = hePie;
    }

    public List<Map<String, Object>> getFuturePie() {
        return futurePie;
    }

    public void setFuturePie(List<Map<String, Object>> futurePie) {
        this.futurePie = futurePie;
    }

    public List<Map<String, Object>> getOutstandingPie() {
        return outstandingPie;
    }

    public void setOutstandingPie(List<Map<String, Object>> outstandingPie) {
        this.outstandingPie = outstandingPie;
    }

    public List<Map<String, Object>> getProgressPie() {
        return progressPie;
    }

    public void setProgressPie(List<Map<String, Object>> progressPie) {
        this.progressPie = progressPie;
    }

    public List<Map<String, Object>> getNaturePie() {
        return naturePie;
    }

    public void setNaturePie(List<Map<String, Object>> naturePie) {
        this.naturePie = naturePie;
    }

    public List<Map<String, Object>> getInventPie() {
        return inventPie;
    }

    public void setInventPie(List<Map<String, Object>> inventPie) {
        this.inventPie = inventPie;
    }

    public List<Map<String, Object>> getHighestList() {
        return highestList;
    }

    public void setHighestList(List<Map<String, Object>> highestList) {
        this.highestList = highestList;
    }

    public int getHighestMun() {
        return highestMun;
    }

    public void setHighestMun(int highestMun) {
        this.highestMun = highestMun;
    }

    public int getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(int otherNum) {
        this.otherNum = otherNum;
    }

    public int getStateNum() {
        return stateNum;
    }

    public void setStateNum(int stateNum) {
        this.stateNum = stateNum;
    }
}
