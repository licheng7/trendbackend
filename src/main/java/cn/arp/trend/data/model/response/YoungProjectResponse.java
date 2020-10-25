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
public class YoungProjectResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private List<Map<String, Object>> trend;

    private Integer number;

    @JsonProperty("number_nsfc")
    private Integer numberNsfc;

    @JsonProperty("number_keyplan")
    private Integer numberKeyplan;

    private Float funds;

    @JsonProperty("funds_nsfc")
    private Float fundsNsfc;

    @JsonProperty("funds_keyplan_total")
    private Float fundsKeyplanTotal;

    @JsonProperty("funds_keyplan_zycz")
    private Float fundsKeyplanZycz;

    @JsonProperty("funds_keyplan_self")
    private Float fundsKeyplanSelf;

    private Integer count;

    @JsonProperty("count_nsfc")
    private Integer countNsfc;

    @JsonProperty("count_keyplan")
    private Integer countKeyplan;

    @JsonProperty("avg_number")
    private Float avgNumber;

    @JsonProperty("avg_number_nsfc")
    private Float avgNumberNsfc;

    @JsonProperty("avg_number_keyplan")
    private Float avgNumberKeyplan;

    @JsonProperty("avg_funds")
    private Float avgFunds;

    @JsonProperty("avg_funds_nsfc")
    private Float avgFundsNsfc;

    @JsonProperty("avg_funds_keyplan")
    private Float avgFundsKeyplan;

    @JsonProperty("funds_unit")
    private String fundsUnit = "万元";

    @JsonProperty("funds_unit_nsfc")
    private String fundsUnitNsfc = "万元";

    @JsonProperty("funds_unit_keyplan_total")
    private String fundsUnitKeyplanTotal = "万元";

    @JsonProperty("funds_unit_keyplan_zycz")
    private String fundsUnitKeyplanZycz = "万元";

    @JsonProperty("funds_unit_keyplan_self")
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
