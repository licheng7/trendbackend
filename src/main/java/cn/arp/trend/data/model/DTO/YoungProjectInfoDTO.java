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
public class YoungProjectInfoDTO {

    private List<Map<String, Object>> trend;

    private Integer number;

    private Integer numberNsfc;

    private Integer numberKeyplan;

    private Float funds;

    private Float fundsNsfc;

    private Float fundsKeyplanTotal;

    private Float fundsKeyplanZycz;

    private Float fundsKeyplanSelf;

    private Integer count;

    private Integer countNsfc;

    private Integer countKeyplan;

    private Float avgNumber;

    private Float avgNumberNsfc;

    private Float avgNumberKeyplan;

    private Float avgFunds;

    private Float avgFundsNsfc;

    private Float avgFundsKeyplan;

    private String fundsUnit = "万元";

    private String fundsUnitNsfc = "万元";

    private String fundsUnitKeyplanTotal = "万元";

    private String fundsUnitKeyplanZycz = "万元";

    private String fundsUnitKeyplanSelf = "万元";

    private String updateTime;

    public List<Map<String, Object>> getTrend() {
        return trend;
    }

    public void setTrend(List<Map<String, Object>> trend) {
        this.trend = trend;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberNsfc() {
        return numberNsfc;
    }

    public void setNumberNsfc(Integer numberNsfc) {
        this.numberNsfc = numberNsfc;
    }

    public Integer getNumberKeyplan() {
        return numberKeyplan;
    }

    public void setNumberKeyplan(Integer numberKeyplan) {
        this.numberKeyplan = numberKeyplan;
    }

    public Float getFunds() {
        return funds;
    }

    public void setFunds(Float funds) {
        this.funds = funds;
    }

    public Float getFundsNsfc() {
        return fundsNsfc;
    }

    public void setFundsNsfc(Float fundsNsfc) {
        this.fundsNsfc = fundsNsfc;
    }

    public Float getFundsKeyplanTotal() {
        return fundsKeyplanTotal;
    }

    public void setFundsKeyplanTotal(Float fundsKeyplanTotal) {
        this.fundsKeyplanTotal = fundsKeyplanTotal;
    }

    public Float getFundsKeyplanZycz() {
        return fundsKeyplanZycz;
    }

    public void setFundsKeyplanZycz(Float fundsKeyplanZycz) {
        this.fundsKeyplanZycz = fundsKeyplanZycz;
    }

    public Float getFundsKeyplanSelf() {
        return fundsKeyplanSelf;
    }

    public void setFundsKeyplanSelf(Float fundsKeyplanSelf) {
        this.fundsKeyplanSelf = fundsKeyplanSelf;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCountNsfc() {
        return countNsfc;
    }

    public void setCountNsfc(Integer countNsfc) {
        this.countNsfc = countNsfc;
    }

    public Integer getCountKeyplan() {
        return countKeyplan;
    }

    public void setCountKeyplan(Integer countKeyplan) {
        this.countKeyplan = countKeyplan;
    }

    public Float getAvgNumber() {
        return avgNumber;
    }

    public void setAvgNumber(Float avgNumber) {
        this.avgNumber = avgNumber;
    }

    public Float getAvgNumberNsfc() {
        return avgNumberNsfc;
    }

    public void setAvgNumberNsfc(Float avgNumberNsfc) {
        this.avgNumberNsfc = avgNumberNsfc;
    }

    public Float getAvgNumberKeyplan() {
        return avgNumberKeyplan;
    }

    public void setAvgNumberKeyplan(Float avgNumberKeyplan) {
        this.avgNumberKeyplan = avgNumberKeyplan;
    }

    public Float getAvgFunds() {
        return avgFunds;
    }

    public void setAvgFunds(Float avgFunds) {
        this.avgFunds = avgFunds;
    }

    public Float getAvgFundsNsfc() {
        return avgFundsNsfc;
    }

    public void setAvgFundsNsfc(Float avgFundsNsfc) {
        this.avgFundsNsfc = avgFundsNsfc;
    }

    public Float getAvgFundsKeyplan() {
        return avgFundsKeyplan;
    }

    public void setAvgFundsKeyplan(Float avgFundsKeyplan) {
        this.avgFundsKeyplan = avgFundsKeyplan;
    }

    public String getFundsUnit() {
        return fundsUnit;
    }

    public void setFundsUnit(String fundsUnit) {
        this.fundsUnit = fundsUnit;
    }

    public String getFundsUnitNsfc() {
        return fundsUnitNsfc;
    }

    public void setFundsUnitNsfc(String fundsUnitNsfc) {
        this.fundsUnitNsfc = fundsUnitNsfc;
    }

    public String getFundsUnitKeyplanTotal() {
        return fundsUnitKeyplanTotal;
    }

    public void setFundsUnitKeyplanTotal(String fundsUnitKeyplanTotal) {
        this.fundsUnitKeyplanTotal = fundsUnitKeyplanTotal;
    }

    public String getFundsUnitKeyplanZycz() {
        return fundsUnitKeyplanZycz;
    }

    public void setFundsUnitKeyplanZycz(String fundsUnitKeyplanZycz) {
        this.fundsUnitKeyplanZycz = fundsUnitKeyplanZycz;
    }

    public String getFundsUnitKeyplanSelf() {
        return fundsUnitKeyplanSelf;
    }

    public void setFundsUnitKeyplanSelf(String fundsUnitKeyplanSelf) {
        this.fundsUnitKeyplanSelf = fundsUnitKeyplanSelf;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
