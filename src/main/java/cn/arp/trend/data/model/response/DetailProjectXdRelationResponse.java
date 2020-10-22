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
public class DetailProjectXdRelationResponse implements Serializable {

    private static final long serialVersionUID = -5769768276312224992L;

    @JsonProperty("field_funds")
    private List<Map<String, Object>> fieldFunds;

    @JsonProperty("field_number")
    private List<Map<String, Object>> fieldNumber;

    @JsonProperty("rank_number")
    private List<Map<Object, Object>> rankNumber;

    @JsonProperty("rank_funds")
    private List<Map<Object, Object>> rankFunds;

    @JsonProperty("relation_nsfc")
    private List<Map<String, Object>> relationNsfc;

    @JsonProperty("increase_funds")
    private Double increaseFunds;

    @JsonProperty("increase_number")
    private Integer increaseNumber;

    @JsonProperty("updateTime")
    private String updateTime;

    public List<Map<String, Object>> getFieldFunds() {
        return fieldFunds;
    }

    public void setFieldFunds(List<Map<String, Object>> fieldFunds) {
        this.fieldFunds = fieldFunds;
    }

    public List<Map<String, Object>> getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(List<Map<String, Object>> fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public List<Map<Object, Object>> getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(List<Map<Object, Object>> rankNumber) {
        this.rankNumber = rankNumber;
    }

    public List<Map<Object, Object>> getRankFunds() {
        return rankFunds;
    }

    public void setRankFunds(List<Map<Object, Object>> rankFunds) {
        this.rankFunds = rankFunds;
    }

    public List<Map<String, Object>> getRelationNsfc() {
        return relationNsfc;
    }

    public void setRelationNsfc(List<Map<String, Object>> relationNsfc) {
        this.relationNsfc = relationNsfc;
    }

    public Double getIncreaseFunds() {
        return increaseFunds;
    }

    public void setIncreaseFunds(Double increaseFunds) {
        this.increaseFunds = increaseFunds;
    }

    public Integer getIncreaseNumber() {
        return increaseNumber;
    }

    public void setIncreaseNumber(Integer increaseNumber) {
        this.increaseNumber = increaseNumber;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
