package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:05
 **/
public class ChildLevelDistributionQueryDO {

    private String endYear;

    private List<String> affiliationId;

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

    public ChildLevelDistributionQueryDO() {
    }

    public ChildLevelDistributionQueryDO(String endYear, List<String> affiliationId) {
        this.endYear = endYear;
        this.affiliationId = affiliationId;
    }
}
