package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:07
 **/
@ToString
public class AssetIncomeInfoDTO {

    private String updateTime;

    private List<Map<String, Object>> detail;

    private List<String> legend;

    private List<String> legendAttr;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Map<String, Object>> getDetail() {
        return detail;
    }

    public void setDetail(List<Map<String, Object>> detail) {
        this.detail = detail;
    }

    public List<String> getLegend() {
        return legend;
    }

    public void setLegend(List<String> legend) {
        this.legend = legend;
    }

    public List<String> getLegendAttr() {
        return legendAttr;
    }

    public void setLegendAttr(List<String> legendAttr) {
        this.legendAttr = legendAttr;
    }

    public AssetIncomeInfoDTO(String updateTime, List<Map<String, Object>> detail, List<String> legend, List<String> legendAttr) {
        this.updateTime = updateTime;
        this.detail = detail;
        this.legend = legend;
        this.legendAttr = legendAttr;
    }

    public AssetIncomeInfoDTO() {
    }
}
