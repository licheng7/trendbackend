package cn.arp.trend.data.model.DTO;

import lombok.ToString;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/6
 * Time:下午10:31
 **/
@ToString
public class DetailProjectIncreaseInfoDTO {

    private Long numberNsfc;

    private Double fundsNsfc;

    private Long numberKjb;

    private Double fundsKjb;

    private Long numberXd;

    private Double fundsXd;

    private String updateTime;

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
