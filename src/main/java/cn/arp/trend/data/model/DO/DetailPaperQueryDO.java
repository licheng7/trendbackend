package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午9:23
 **/
public class DetailPaperQueryDO {

    private String startYear;

    private String endYear;

    private List<String> affiliation;

    private List<String> fields;

    private String category;

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

    public List<String> getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(List<String> affiliation) {
        this.affiliation = affiliation;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DetailPaperQueryDO(String startYear, String endYear, List<String> affiliation, List<String> fields, String category) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.affiliation = affiliation;
        this.fields = fields;
        this.category = category;
    }

    public DetailPaperQueryDO() {
    }
}
