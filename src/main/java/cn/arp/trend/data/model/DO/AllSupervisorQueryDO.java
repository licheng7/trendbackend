package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午11:08
 **/
public class AllSupervisorQueryDO {

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

    public AllSupervisorQueryDO(String endYear, List<String> affiliationId, List<String> fieldName) {
        this.endYear = endYear;
        this.affiliationId = affiliationId;
        this.fieldName = fieldName;
    }

    public AllSupervisorQueryDO() {
    }
}
