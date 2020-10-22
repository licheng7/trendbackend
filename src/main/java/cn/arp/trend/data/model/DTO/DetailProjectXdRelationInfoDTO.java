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
public class DetailProjectXdRelationInfoDTO {

    private List<Map<String, Object>> fieldFunds;

    private List<Map<String, Object>> fieldNumber;

    private List<Map<Object, Object>> rankNumber;

    private List<Map<Object, Object>> rankFunds;

    private List<Map<String, Object>> relationNsfc;

    private Double increaseFunds;

    private Integer increaseNumber;

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
