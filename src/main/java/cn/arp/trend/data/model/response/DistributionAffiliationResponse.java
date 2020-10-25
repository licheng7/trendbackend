package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class DistributionAffiliationResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private String updateTime;

    private List<Map<String, Object>> detail;

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

    public DistributionAffiliationResponse(String updateTime, List<Map<String, Object>> detail) {
        this.updateTime = updateTime;
        this.detail = detail;
    }

    public DistributionAffiliationResponse() {
    }
}
