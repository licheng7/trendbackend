package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class AreaHrAcadCasDistResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private Map<String, Object> proportion;

    @JsonProperty("unit_data")
    private List<Map<String, Object>> unitData;

    @JsonProperty("update_time")
    private String updateTime;

    public Map<String, Object> getProportion() {
        return proportion;
    }

    public void setProportion(Map<String, Object> proportion) {
        this.proportion = proportion;
    }

    public List<Map<String, Object>> getUnitData() {
        return unitData;
    }

    public void setUnitData(List<Map<String, Object>> unitData) {
        this.unitData = unitData;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public AreaHrAcadCasDistResponse(Map<String, Object> proportion, List<Map<String, Object>> unitData, String updateTime) {
        this.proportion = proportion;
        this.unitData = unitData;
        this.updateTime = updateTime;
    }

    public AreaHrAcadCasDistResponse() {
    }
}
