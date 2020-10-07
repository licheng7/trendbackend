package cn.arp.trend.data.model.response;

import cn.arp.trend.data.model.DTO.GoToByTimeObjDTO;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:下午4:45
 **/
@ToString
public class LinksInfoReponse implements Serializable {

    private static final long serialVersionUID = 7875957941300921166L;

    private List<String> timeList;

    private List<GoToByTimeObjDTO> timeListObj;

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }

    public List<GoToByTimeObjDTO> getTimeListObj() {
        return timeListObj;
    }

    public void setTimeListObj(List<GoToByTimeObjDTO> timeListObj) {
        this.timeListObj = timeListObj;
    }

    public LinksInfoReponse(List<String> timeList, List<GoToByTimeObjDTO> timeListObj) {
        this.timeList = timeList;
        this.timeListObj = timeListObj;
    }

    public LinksInfoReponse() {
    }
}
