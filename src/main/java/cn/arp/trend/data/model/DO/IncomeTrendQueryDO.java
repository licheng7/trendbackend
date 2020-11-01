package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:05
 **/
public class IncomeTrendQueryDO {

    private String year;

    private List<String> affiliationId;

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

    public IncomeTrendQueryDO(String year, List<String> affiliationId) {
        this.year = year;
        this.affiliationId = affiliationId;
    }

    public IncomeTrendQueryDO() {
    }
}
