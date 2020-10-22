package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午6:56
 **/
@ToString
public class FacilityResponse implements Serializable {

    private static final long serialVersionUID = 1884761190553249526L;

    @JsonProperty("year")
    private List<String> yearList;

    @JsonProperty("platform")
    private List<MapResult> platformList;

    @JsonProperty("keylab")
    private List<MapResult> keylabList;

    private String updateTimeBas;

    private String updateTimeLab;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<MapResult> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<MapResult> platformList) {
        this.platformList = platformList;
    }

    public List<MapResult> getKeylabList() {
        return keylabList;
    }

    public void setKeylabList(List<MapResult> keylabList) {
        this.keylabList = keylabList;
    }

    public String getUpdateTimeBas() {
        return updateTimeBas;
    }

    public void setUpdateTimeBas(String updateTimeBas) {
        this.updateTimeBas = updateTimeBas;
    }

    public String getUpdateTimeLab() {
        return updateTimeLab;
    }

    public void setUpdateTimeLab(String updateTimeLab) {
        this.updateTimeLab = updateTimeLab;
    }

    public FacilityResponse() {
    }

    public FacilityResponse(List<String> yearList, List<MapResult> platformList, List<MapResult> keylabList, String updateTimeBas, String updateTimeLab) {
        this.yearList = yearList;
        this.platformList = platformList;
        this.keylabList = keylabList;
        this.updateTimeBas = updateTimeBas;
        this.updateTimeLab = updateTimeLab;
    }
}
