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
public class OverviewInfoDTO {

    private double asset;

    private double income;

    private double outcome;

    private double deposit;

    private String assetUnit;

    private String incomeUnit;

    private String outcomeUnit;

    private String depositUnit;

    private List<Map<String, Object>> incomeDistribution;

    public double getAsset() {
        return asset;
    }

    public void setAsset(double asset) {
        this.asset = asset;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getOutcome() {
        return outcome;
    }

    public void setOutcome(double outcome) {
        this.outcome = outcome;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getAssetUnit() {
        return assetUnit;
    }

    public void setAssetUnit(String assetUnit) {
        this.assetUnit = assetUnit;
    }

    public String getIncomeUnit() {
        return incomeUnit;
    }

    public void setIncomeUnit(String incomeUnit) {
        this.incomeUnit = incomeUnit;
    }

    public String getOutcomeUnit() {
        return outcomeUnit;
    }

    public void setOutcomeUnit(String outcomeUnit) {
        this.outcomeUnit = outcomeUnit;
    }

    public String getDepositUnit() {
        return depositUnit;
    }

    public void setDepositUnit(String depositUnit) {
        this.depositUnit = depositUnit;
    }

    public List<Map<String, Object>> getIncomeDistribution() {
        return incomeDistribution;
    }

    public void setIncomeDistribution(List<Map<String, Object>> incomeDistribution) {
        this.incomeDistribution = incomeDistribution;
    }

    public OverviewInfoDTO(double asset, double income, double outcome, double deposit, String assetUnit, String incomeUnit, String outcomeUnit, String depositUnit, List<Map<String, Object>> incomeDistribution) {
        this.asset = asset;
        this.income = income;
        this.outcome = outcome;
        this.deposit = deposit;
        this.assetUnit = assetUnit;
        this.incomeUnit = incomeUnit;
        this.outcomeUnit = outcomeUnit;
        this.depositUnit = depositUnit;
        this.incomeDistribution = incomeDistribution;
    }

    public OverviewInfoDTO() {
    }
}
