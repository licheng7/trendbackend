package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午1:53
 **/
@ToString
public class Rank2InfoDTO {

    private List<GotoUnitDTO> gotoUnitList;

    private List<GotoCountryDTO> gotoCountryList;

    public Rank2InfoDTO(List<GotoUnitDTO> gotoUnitList, List<GotoCountryDTO> gotoCountryList) {
        this.gotoUnitList = gotoUnitList;
        this.gotoCountryList = gotoCountryList;
    }

    public Rank2InfoDTO() {
    }

    public List<GotoUnitDTO> getGotoUnitList() {
        return gotoUnitList;
    }

    public void setGotoUnitList(List<GotoUnitDTO> gotoUnitList) {
        this.gotoUnitList = gotoUnitList;
    }

    public List<GotoCountryDTO> getGotoCountryList() {
        return gotoCountryList;
    }

    public void setGotoCountryList(List<GotoCountryDTO> gotoCountryList) {
        this.gotoCountryList = gotoCountryList;
    }
}
