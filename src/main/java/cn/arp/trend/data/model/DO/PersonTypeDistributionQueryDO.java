package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:05
 **/
public class PersonTypeDistributionQueryDO {

    private String startYear;

    private String endYear;

    private List<String> affiliationId;

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

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

    public PersonTypeDistributionQueryDO() {
    }

    public PersonTypeDistributionQueryDO(String startYear, String endYear, List<String> affiliationId) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.affiliationId = affiliationId;
    }
}
