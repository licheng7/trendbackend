package cn.arp.trend.data.model.DTO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:下午3:37
 **/
public class LinksInfoDTO {

    private List<String> timeList;

    private List<GoToByTimeObjDTO> timeListObj;

    public LinksInfoDTO(List<String> timeList, List<GoToByTimeObjDTO> timeListObj) {
        this.timeList = timeList;
        this.timeListObj = timeListObj;
    }

    public LinksInfoDTO() {
    }

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
}
