package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午11:08
 **/
public class PostDistributionQueryDO {

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


    public PostDistributionQueryDO(String endYear, List<String> affiliationId) {
        this.endYear = endYear;
        this.affiliationId = affiliationId;
    }

    public PostDistributionQueryDO() {
    }
}
