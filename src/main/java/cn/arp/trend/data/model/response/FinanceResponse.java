package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午9:08
 **/
@ToString
public class FinanceResponse implements Serializable {

    private static final long serialVersionUID = 4231383306892649675L;

    private List<String> year;

    private Map<String, Map<String, Double>> detail;

    private String updateTime;

    public FinanceResponse() {
    }

    public FinanceResponse(List<String> year, Map<String, Map<String, Double>> detail, String updateTime) {
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