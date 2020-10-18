package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午11:08
 **/
public class PostDistributionQueryDO {

    private String startYear;

    private String endYear;

    private List<String> affiliationId;

    private List<String> fieldName;

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public List<String> getAffiliationId() {
        return affiliationId;
    }

    public void setAffiliationId(List<String> affiliationId) {
        this.affiliationId = affiliationId;
    }

    public List<String> getFieldName() {
        return fieldName;
    }

    public void setFieldName(List<String> fieldName) {
        this.fieldName = fieldName;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public PostDistributionQueryDO(String startYear, String endYear, List<String> affiliationId, List<String> fieldName) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.affiliationId = affiliationId;
        this.fieldName = fieldName;
    }

    public PostDistributionQueryDO() {
    }
}
