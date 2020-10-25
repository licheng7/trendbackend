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
public class DistributionFieldInfoDTO {

    private List<Object> fields;

    private List<Object> youngElite;

    private List<Object> staffNumber;

    private List<Object> proportion;

    public List<Object> getFields() {
        return fields;
    }

    public void setFields(List<Object> fields) {
        this.fields = fields;
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

    public DistributionFieldInfoDTO(List<Object> fields, List<Object> youngElite, List<Object> staffNumber, List<Object> proportion) {
        this.fields = fields;
        this.youngElite = youngElite;
        this.staffNumber = staffNumber;
        this.proportion = proportion;
    }

    public DistributionFieldInfoDTO() {
    }
}
