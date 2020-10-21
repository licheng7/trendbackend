package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:07
 **/
@ToString
public class AssetDetailInfoDTO {

    private String updateTime;

    private List<Map<String, Object>> detail;

    private List<Map<String, Object>> incomeDistribution;

    private List<Map<String, Object>> outcomeDistribution;

    private List<List> resultArray;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Map<String, Object>> getDetail() {
        return detail;
    }

    public void setDetail(List<Map<String, Object>> detail) {
        this.detail = detail;
    }

    public List<Map<String, Object>> getIncomeDistribution() {
        return incomeDistribution;
    }

    public void setIncomeDistribution(List<Map<String, Object>> incomeDistribution) {
        this.incomeDistribution = incomeDistribution;
    }

    public List<Map<String, Object>> getOutcomeDistribution() {
        return outcomeDistribution;
    }

    public void setOutcomeDistribution(List<Map<String, Object>> outcomeDistribution) {
        this.outcomeDistribution = outcomeDistribution;
    }

    public List<List> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<List> resultArray) {
        this.resultArray = resultArray;
    }

    public AssetDetailInfoDTO(String updateTime, List<Map<String, Object>> detail, List<Map<String, Object>> incomeDistribution, List<Map<String, Object>> outcomeDistribution, List<List> resultArray) {
        this.updateTime = updateTime;
        this.detail = detail;
        this.incomeDistribution = incomeDistribution;
        this.outcomeDistribution = outcomeDistribution;
        this.resultArray = resultArray;
    }

    public AssetDetailInfoDTO() {
    }
}
