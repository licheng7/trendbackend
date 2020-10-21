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
public class PaperInfoDTO {

    private List<String> year;

    private List<MapResultDTO> detail;

    private String paperUpdateTimeLw;

    private String paperUpdateTimeGby;

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<MapResultDTO> getDetail() {
        return detail;
    }

    public void setDetail(List<MapResultDTO> detail) {
        this.detail = detail;
    }

    public String getPaperUpdateTimeLw() {
        return paperUpdateTimeLw;
    }

    public void setPaperUpdateTimeLw(String paperUpdateTimeLw) {
        this.paperUpdateTimeLw = paperUpdateTimeLw;
    }

    public String getPaperUpdateTimeGby() {
        return paperUpdateTimeGby;
    }

    public void setPaperUpdateTimeGby(String paperUpdateTimeGby) {
        this.paperUpdateTimeGby = paperUpdateTimeGby;
    }
}
