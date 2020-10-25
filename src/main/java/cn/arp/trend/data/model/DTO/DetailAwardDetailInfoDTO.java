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
public class DetailAwardDetailInfoDTO {

    private List<Map<String, Object>> hePie;

    private List<Map<String, Object>> futurePie;

    private List<Map<String, Object>> outstandingPie;

    private List<Map<String, Object>> progressPie;

    private List<Map<String, Object>> naturePie;

    private List<Map<String, Object>> inventPie;

    private List<Map<String, Object>> highestList;

    private int highestMun;

    int otherNum;

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
