package cn.arp.trend.data.model.DO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午9:23
 **/
public class AreaProjectNsfcTrendQueryDO {

    private String startYear;

    private String endYear;

    private List<String> unitIdAry;

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

    public List<String> getUnitIdAry() {
        return unitIdAry;
    }

    public void setUnitIdAry(List<String> unitIdAry) {
        this.unitIdAry = unitIdAry;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AreaProjectNsfcTrendQueryDO(String startYear, String endYear, List<String> unitIdAry, String category) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.unitIdAry = unitIdAry;
        this.category = category;
    }

    public AreaProjectNsfcTrendQueryDO() {
    }
}
