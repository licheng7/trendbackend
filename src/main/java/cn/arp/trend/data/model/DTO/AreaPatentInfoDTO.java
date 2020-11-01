package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/31
 * Time:下午9:51
 **/
public class AreaPatentInfoDTO {

    private Map<String, List<Object>> inventAry;

    private Map<String, List<Object>> aRPInventAry;

    private Map<String, List<Object>> pCTInventAry;

    private List<Map<String, Object>> inventproportion;

    private List<Map<String, Object>> pCTInventProportion;

    private List<Map<String, Object>> originalUnitAry;

    private List<Map<String, Object>> pCTOriginalUnitAry;

    private List<String> yearList;

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
}
