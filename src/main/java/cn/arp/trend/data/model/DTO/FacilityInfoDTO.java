package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午9:59
 **/
@ToString
public class FacilityInfoDTO {

    private List<String> yearList;

    private List<MapResultDTO> platformList;

    private List<MapResultDTO> keylabList;

    private String updateTimeBas;

    private String updateTimeLab;

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<MapResultDTO> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<MapResultDTO> platformList) {
        this.platformList = platformList;
    }

    public List<MapResultDTO> getKeylabList() {
        return keylabList;
    }

    public void setKeylabList(List<MapResultDTO> keylabList) {
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

    public FacilityInfoDTO() {
    }

    public FacilityInfoDTO(List<String> yearList, List<MapResultDTO> platformList, List<MapResultDTO> keylabList, String updateTimeBas, String updateTimeLab) {
        this.yearList = yearList;
        this.platformList = platformList;
        this.keylabList = keylabList;
        this.updateTimeBas = updateTimeBas;
        this.updateTimeLab = updateTimeLab;
    }
}
