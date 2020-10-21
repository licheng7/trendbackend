package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/10
 * Time:下午5:39
 **/
@ToString
public class PaperResponse implements Serializable {

    private static final long serialVersionUID = 1344252624744218685L;

    private List<String> year;

    private List<MapResult> detail;

    private String paperUpdateTimeLw;

    private String paperUpdateTimeGby;

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<MapResult> getDetail() {
        return detail;
    }

    public void setDetail(List<MapResult> detail) {
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

    public PaperResponse() {
    }

    public PaperResponse(List<String> year, List<MapResult> detail, String paperUpdateTimeLw, String paperUpdateTimeGby) {
        this.year = year;
        this.detail = detail;
        this.paperUpdateTimeLw = paperUpdateTimeLw;
        this.paperUpdateTimeGby = paperUpdateTimeGby;
    }
}
