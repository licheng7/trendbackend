package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:05
 **/
public class PostAnalyzeQueryDO {

    private String endYear;

    private List<String> affiliationId;

    private String positionValue;

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

    public String getPositionValue() {
        return positionValue;
    }

    public void setPositionValue(String positionValue) {
        this.positionValue = positionValue;
    }

    public PostAnalyzeQueryDO() {
    }

    public PostAnalyzeQueryDO(String endYear, List<String> affiliationId, String positionValue) {
        this.endYear = endYear;
        this.affiliationId = affiliationId;
        this.positionValue = positionValue;
    }
}
