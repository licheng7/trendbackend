package cn.arp.trend.data.model.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午9:02
 **/
public class OverviewResponse implements Serializable {

    private static final long serialVersionUID = -6880710850280864142L;

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
}
