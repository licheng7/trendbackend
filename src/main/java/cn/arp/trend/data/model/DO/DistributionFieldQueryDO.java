package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午9:23
 **/
public class DistributionFieldQueryDO {

    private String year;

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

    public List<String> getFieldName() {
        return fieldName;
    }

    public void setFieldName(List<String> fieldName) {
        this.fieldName = fieldName;
    }

    public DistributionFieldQueryDO(String year, List<String> affiliationId, List<String> fieldName) {
        this.year = year;
        this.affiliationId = affiliationId;
        this.fieldName = fieldName;
    }

    public DistributionFieldQueryDO() {
    }
}
