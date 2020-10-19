package cn.arp.trend.data.model.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午9:02
 **/
public class AssetIncomeResponse implements Serializable {

    private static final long serialVersionUID = -6880710850280864142L;

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

    public AssetIncomeResponse() {
    }

    public AssetIncomeResponse(String updateTime, List<Map<String, Object>> detail, List<String> legend, List<String> legendAttr) {
        this.updateTime = updateTime;
        this.detail = detail;
        this.legend = legend;
        this.legendAttr = legendAttr;
    }
}
