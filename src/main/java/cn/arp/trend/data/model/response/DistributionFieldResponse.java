package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class DistributionFieldResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private List<Object> fields;

    @JsonProperty("young_elite")
    private List<Object> youngElite;

    @JsonProperty("staff_number")
    private List<Object> staffNumber;

    private List<Object> proportion;

    private String updateTime;

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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public DistributionFieldResponse() {
    }

    public DistributionFieldResponse(List<Object> fields, List<Object> youngElite, List<Object> staffNumber, List<Object> proportion, String updateTime) {
        this.fields = fields;
        this.youngElite = youngElite;
        this.staffNumber = staffNumber;
        this.proportion = proportion;
        this.updateTime = updateTime;
    }
}
