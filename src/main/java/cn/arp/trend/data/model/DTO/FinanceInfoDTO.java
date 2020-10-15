package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午9:11
 **/
public class FinanceInfoDTO {

    private List<String> year;

    private List<Map<String, Object>> detail;

    private String updateTime;

    public FinanceInfoDTO() {
    }

    public FinanceInfoDTO(List<String> year, List<Map<String, Object>> detail, String updateTime) {
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

    public List<Map<String, Object>> getDetail() {
        return detail;
    }

    public void setDetail(List<Map<String, Object>> detail) {
        this.detail = detail;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
