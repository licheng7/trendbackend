package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:18
 **/
public class AcademicianQueryDO {

    private List<String> affiliationIds;

    private List<String> afieldNameZky;

    private List<String> fieldNameGcy;

    public List<String> getAffiliationIds() {
        return affiliationIds;
    }

    public void setAffiliationIds(List<String> affiliationIds) {
        this.affiliationIds = affiliationIds;
    }

    public List<String> getAfieldNameZky() {
        return afieldNameZky;
    }

    public void setAfieldNameZky(List<String> afieldNameZky) {
        this.afieldNameZky = afieldNameZky;
    }

    public List<String> getFieldNameGcy() {
        return fieldNameGcy;
    }

    public void setFieldNameGcy(List<String> fieldNameGcy) {
        this.fieldNameGcy = fieldNameGcy;
    }
}
