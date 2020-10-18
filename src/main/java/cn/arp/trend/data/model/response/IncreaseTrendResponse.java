package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午11:32
 **/
@ToString
public class IncreaseTrendResponse implements Serializable {

    private static final long serialVersionUID = -2950932750550952186L;

    private List<String> year;

    private IncreaseTrendDetailResult detail;

    private String updateTime;

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public IncreaseTrendDetailResult getDetail() {
        return detail;
    }

    public void setDetail(IncreaseTrendDetailResult detail) {
        this.detail = detail;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
