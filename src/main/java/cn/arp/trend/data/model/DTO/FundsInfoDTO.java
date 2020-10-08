package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午12:28
 **/
@ToString
public class FundsInfoDTO {

    private List<String> year;

    private Map<String, Map<String, Double>> detail;

    private String updateTime;

    public FundsInfoDTO() {
    }

    public FundsInfoDTO(List<String> year, Map<String, Map<String, Double>> detail, String updateTime) {
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
