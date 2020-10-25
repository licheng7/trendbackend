package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
@ToString
public class YoungEliteTrendInfoDTO {

    private List<Object> year;

    private List<Object> youngElite;

    private List<Object> staffNumber;

    private List<Object> proportion;

    public List<Object> getYear() {
        return year;
    }

    public void setYear(List<Object> year) {
        this.year = year;
    }

    public List<Object> getYoungElite() {
        return youngElite;
    }

    public void setYoungElite(List<Object> youngElite) {
        this.youngElite = youngElite;
    }

    public List<Object> getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(List<Object> staffNumber) {
        this.staffNumber = staffNumber;
    }

    public List<Object> getProportion() {
        return proportion;
    }

    public void setProportion(List<Object> proportion) {
        this.proportion = proportion;
    }

    public YoungEliteTrendInfoDTO(List<Object> year, List<Object> youngElite, List<Object> staffNumber, List<Object> proportion) {
        this.year = year;
        this.youngElite = youngElite;
        this.staffNumber = staffNumber;
        this.proportion = proportion;
    }

    public YoungEliteTrendInfoDTO() {
    }
}
