package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/5
 * Time:下午11:04
 **/
@ToString
public class DetailProjectKjbResponse implements Serializable {

    private static final long serialVersionUID = -5769768276312224992L;

    @JsonProperty("year")
    private List<String> year;

    @JsonProperty("trend_number")
    private List<Object> trendNumber;

    @JsonProperty("trend_funds")
    private List<Object> trendFunds;

    @JsonProperty("field_number")
    private List<Map<String, Object>> fieldNumber;

    @JsonProperty("field_funds")
    private List<Map<String, Object>> fieldFunds;

    @JsonProperty("rank_number")
    private List<Map<String, Object>> rankNumber;

    @JsonProperty("rank_funds")
    private List<Map<String, Object>> rankFunds;

    @JsonProperty("updateTime")
    private String updateTime;

    @JsonProperty("funds_unit")
    private String fundsUnit;

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

    public List<Map<String, Object>> getRankFunds() {
        return rankFunds;
    }

    public void setRankFunds(List<Map<String, Object>> rankFunds) {
        this.rankFunds = rankFunds;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFundsUnit() {
        return fundsUnit;
    }

    public void setFundsUnit(String fundsUnit) {
        this.fundsUnit = fundsUnit;
    }
}
