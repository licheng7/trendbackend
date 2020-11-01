package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
@ToString
public class AreaHrAcadCaeDistInfoDTO {

    private Map<String, Object> proportion;

    private List<Map<String, Object>> unitData;

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

    public AreaHrAcadCaeDistInfoDTO(Map<String, Object> proportion, List<Map<String, Object>> unitData, String updateTime) {
        this.proportion = proportion;
        this.unitData = unitData;
        this.updateTime = updateTime;
    }

    public AreaHrAcadCaeDistInfoDTO() {
    }
}
