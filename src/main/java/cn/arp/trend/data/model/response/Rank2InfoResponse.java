package cn.arp.trend.data.model.response;

import cn.arp.trend.data.model.DTO.GotoCountryDTO;
import cn.arp.trend.data.model.DTO.GotoUnitDTO;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:下午2:25
 **/
@ToString
public class Rank2InfoResponse implements Serializable {

    private static final long serialVersionUID = 688588754548744329L;

    private List<GotoUnitDTO> gotoUnitList;

    private List<GotoCountryDTO> gotoCountryList;

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

    public Rank2InfoResponse() {
    }

    public Rank2InfoResponse(List<GotoUnitDTO> gotoUnitList, List<GotoCountryDTO> gotoCountryList) {
        this.gotoUnitList = gotoUnitList;
        this.gotoCountryList = gotoCountryList;
    }
}
