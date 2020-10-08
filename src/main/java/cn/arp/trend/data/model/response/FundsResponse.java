package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午11:32
 **/
@ToString
public class FundsResponse implements Serializable {

    private static final long serialVersionUID = -455594318699304073L;

    private List<String> year;

    private Map<String, Map<String, Double>> detail;

    private String updateTime;

    public FundsResponse() {
    }

    public FundsResponse(List<String> year, Map<String, Map<String, Double>> detail, String updateTime) {
        this.year = year;
        this.detail = detail;
        this.updateTime = updateTime;
    }

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Map<String, Map<String, Double>> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, Map<String, Double>> detail) {
        this.detail = detail;
    }
}
