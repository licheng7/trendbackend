package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/6
 * Time:下午10:31
 **/
@ToString
public class DetailProjectNsfcInfoDTO {

    private List<String> year;

    private List<Object> trendNumber;

    private List<Object> trendFunds;

    private List<Map<String, Object>> fieldNumber;

    private List<Map<String, Object>> fieldFunds;

    private List<Map<String, Object>> rankNumber;

    private List<Map<String, Object>> rankFunds;

    private List<Object> nsfcLineChartMoney;

    private List<Object> nsfcLineChartObg;

    private String updateTime;

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<Object> getTrendNumber() {
        return trendNumber;
    }

    public void setTrendNumber(List<Object> trendNumber) {
        this.trendNumber = trendNumber;
    }

    public List<Object> getTrendFunds() {
        return trendFunds;
    }

    public void setTrendFunds(List<Object> trendFunds) {
        this.trendFunds = trendFunds;
    }

    public List<Map<String, Object>> getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(List<Map<String, Object>> fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public List<Map<String, Object>> getFieldFunds() {
        return fieldFunds;
    }

    public void setFieldFunds(List<Map<String, Object>> fieldFunds) {
        this.fieldFunds = fieldFunds;
    }

    public List<Map<String, Object>> getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(List<Map<String, Object>> rankNumber) {
        this.rankNumber = rankNumber;
    }

    public List<Object> getNsfcLineChartMoney() {
        return nsfcLineChartMoney;
    }

    public void setNsfcLineChartMoney(List<Object> nsfcLineChartMoney) {
        this.nsfcLineChartMoney = nsfcLineChartMoney;
    }

    public List<Object> getNsfcLineChartObg() {
        return nsfcLineChartObg;
    }

    public void setNsfcLineChartObg(List<Object> nsfcLineChartObg) {
        this.nsfcLineChartObg = nsfcLineChartObg;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Map<String, Object>> getRankFunds() {
        return rankFunds;
    }

    public void setRankFunds(List<Map<String, Object>> rankFunds) {
        this.rankFunds = rankFunds;
    }
}
