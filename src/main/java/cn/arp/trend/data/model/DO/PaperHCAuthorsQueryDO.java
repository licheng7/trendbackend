package cn.arp.trend.data.model.DO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午9:23
 **/
public class PaperHCAuthorsQueryDO {

    private String startYear;

    private String endYear;

    private String affiliation;

    private String fields;

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

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public PaperHCAuthorsQueryDO() {
    }

    public PaperHCAuthorsQueryDO(String startYear, String endYear, String affiliation, String fields, String category) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.affiliation = affiliation;
        this.fields = fields;
        this.category = category;
    }
}
