package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:05
 **/
public class AssetIncomeQueryDO {

    private String year;

    private String startYear;

    private List<String> affiliationId;

    private List<String> fieldName;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getAffiliationId() {
        return affiliationId;
    }

    public void setAffiliationId(List<String> affiliationId) {
        this.affiliationId = affiliationId;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public List<String> getFieldName() {
        return fieldName;
    }

    public void setFieldName(List<String> fieldName) {
        this.fieldName = fieldName;
    }

    public AssetIncomeQueryDO(String year, String startYear, List<String> affiliationId, List<String> fieldName) {
        this.year = year;
        this.startYear = startYear;
        this.affiliationId = affiliationId;
        this.fieldName = fieldName;
    }

    public AssetIncomeQueryDO() {
    }
}
