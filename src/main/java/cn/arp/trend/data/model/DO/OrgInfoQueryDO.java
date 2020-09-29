package cn.arp.trend.data.model.DO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午11:30
 **/
@ToString
public class OrgInfoQueryDO {

    private List<String> affiliationIds;

    private List<String> fieldNames;

    public List<String> getAffiliationIds() {
        return affiliationIds;
    }

    public void setAffiliationIds(List<String> affiliationIds) {
        this.affiliationIds = affiliationIds;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public OrgInfoQueryDO() {
    }

    public OrgInfoQueryDO(List<String> affiliationIds, List<String> fieldNames) {
        this.affiliationIds = affiliationIds;
        this.fieldNames = fieldNames;
    }
}
