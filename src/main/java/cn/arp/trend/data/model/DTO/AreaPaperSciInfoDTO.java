package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/31
 * Time:下午9:51
 **/
public class AreaPaperSciInfoDTO {

    private List<Object> officialList;

    private List<Object> arpList;

    private List<Map<String, Object>> unitAry;

    private List<Map<String, Object>> paperProportion;

    private List<String> yearList;

    private String updateTime;

    public List<Object> getOfficialList() {
        return officialList;
    }

    public void setOfficialList(List<Object> officialList) {
        this.officialList = officialList;
    }

    public List<Object> getArpList() {
        return arpList;
    }

    public void setArpList(List<Object> arpList) {
        this.arpList = arpList;
    }

    public List<Map<String, Object>> getUnitAry() {
        return unitAry;
    }

    public void setUnitAry(List<Map<String, Object>> unitAry) {
        this.unitAry = unitAry;
    }

    public List<Map<String, Object>> getPaperProportion() {
        return paperProportion;
    }

    public void setPaperProportion(List<Map<String, Object>> paperProportion) {
        this.paperProportion = paperProportion;
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

    public AreaPaperSciInfoDTO(List<Object> officialList, List<Object> arpList, List<Map<String, Object>> unitAry, List<Map<String, Object>> paperProportion, List<String> yearList, String updateTime) {
        this.officialList = officialList;
        this.arpList = arpList;
        this.unitAry = unitAry;
        this.paperProportion = paperProportion;
        this.yearList = yearList;
        this.updateTime = updateTime;
    }

    public AreaPaperSciInfoDTO() {
    }
}
