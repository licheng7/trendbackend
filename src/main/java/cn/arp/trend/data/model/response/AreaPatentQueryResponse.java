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
public class AreaPatentQueryResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("invent_ary")
    private Map<String, List<Object>> inventAry;

    @JsonProperty("ARP_invent_ary")
    private Map<String, List<Object>> aRPInventAry;

    @JsonProperty("PCT_invent_ary")
    private Map<String, List<Object>> pCTInventAry;

    @JsonProperty("invent_proportion")
    private List<Map<String, Object>> inventproportion;

    @JsonProperty("PCT_invent_proportion")
    private List<Map<String, Object>> pCTInventProportion;

    @JsonProperty("original_unit_ary")
    private List<Map<String, Object>> originalUnitAry;

    @JsonProperty("PCT_original_unit_ary")
    private List<Map<String, Object>> pCTOriginalUnitAry;

    @JsonProperty("year_list")
    private List<String> yearList;

    @JsonProperty("update_time")
    private String updateTime;

    public Map<String, List<Object>> getInventAry() {
        return inventAry;
    }

    public void setInventAry(Map<String, List<Object>> inventAry) {
        this.inventAry = inventAry;
    }

    public Map<String, List<Object>> getaRPInventAry() {
        return aRPInventAry;
    }

    public void setaRPInventAry(Map<String, List<Object>> aRPInventAry) {
        this.aRPInventAry = aRPInventAry;
    }

    public Map<String, List<Object>> getpCTInventAry() {
        return pCTInventAry;
    }

    public void setpCTInventAry(Map<String, List<Object>> pCTInventAry) {
        this.pCTInventAry = pCTInventAry;
    }

    public List<Map<String, Object>> getInventproportion() {
        return inventproportion;
    }

    public void setInventproportion(List<Map<String, Object>> inventproportion) {
        this.inventproportion = inventproportion;
    }

    public List<Map<String, Object>> getpCTInventProportion() {
        return pCTInventProportion;
    }

    public void setpCTInventProportion(List<Map<String, Object>> pCTInventProportion) {
        this.pCTInventProportion = pCTInventProportion;
    }

    public List<Map<String, Object>> getOriginalUnitAry() {
        return originalUnitAry;
    }

    public void setOriginalUnitAry(List<Map<String, Object>> originalUnitAry) {
        this.originalUnitAry = originalUnitAry;
    }

    public List<Map<String, Object>> getpCTOriginalUnitAry() {
        return pCTOriginalUnitAry;
    }

    public void setpCTOriginalUnitAry(List<Map<String, Object>> pCTOriginalUnitAry) {
        this.pCTOriginalUnitAry = pCTOriginalUnitAry;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public AreaPatentQueryResponse(Map<String, List<Object>> inventAry, Map<String, List<Object>> aRPInventAry, Map<String, List<Object>> pCTInventAry, List<Map<String, Object>> inventproportion, List<Map<String, Object>> pCTInventProportion, List<Map<String, Object>> originalUnitAry, List<Map<String, Object>> pCTOriginalUnitAry, List<String> yearList, String updateTime) {
        this.inventAry = inventAry;
        this.aRPInventAry = aRPInventAry;
        this.pCTInventAry = pCTInventAry;
        this.inventproportion = inventproportion;
        this.pCTInventProportion = pCTInventProportion;
        this.originalUnitAry = originalUnitAry;
        this.pCTOriginalUnitAry = pCTOriginalUnitAry;
        this.yearList = yearList;
        this.updateTime = updateTime;
    }

    public AreaPatentQueryResponse() {
    }
}
