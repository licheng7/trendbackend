package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/5
 * Time:下午11:04
 **/
@ToString
public class DetailProjectIncreaseResponse implements Serializable {

    private static final long serialVersionUID = -5769768276312224992L;

    @JsonProperty("number_nsfc")
    private Long numberNsfc;

    @JsonProperty("funds_nsfc")
    private Double fundsNsfc;

    @JsonProperty("number_kjb")
    private Long numberKjb;

    @JsonProperty("funds_kjb")
    private Double fundsKjb;

    @JsonProperty("number_xd")
    private Long numberXd;

    @JsonProperty("funds_xd")
    private Double fundsXd;

    @JsonProperty("updateTime")
    private String updateTime;

    @JsonProperty("funds_unit")
    private String fundsUnit;

    public Long getNumberNsfc() {
        return numberNsfc;
    }

    public void setNumberNsfc(Long numberNsfc) {
        this.numberNsfc = numberNsfc;
    }

    public Double getFundsNsfc() {
        return fundsNsfc;
    }

    public void setFundsNsfc(Double fundsNsfc) {
        this.fundsNsfc = fundsNsfc;
    }

    public Long getNumberKjb() {
        return numberKjb;
    }

    public void setNumberKjb(Long numberKjb) {
        this.numberKjb = numberKjb;
    }

    public Double getFundsKjb() {
        return fundsKjb;
    }

    public void setFundsKjb(Double fundsKjb) {
        this.fundsKjb = fundsKjb;
    }

    public Long getNumberXd() {
        return numberXd;
    }

    public void setNumberXd(Long numberXd) {
        this.numberXd = numberXd;
    }

    public Double getFundsXd() {
        return fundsXd;
    }

    public void setFundsXd(Double fundsXd) {
        this.fundsXd = fundsXd;
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
