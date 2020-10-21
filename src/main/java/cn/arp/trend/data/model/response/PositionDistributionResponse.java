package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午11:32
 **/
@ToString
public class PositionDistributionResponse implements Serializable {

    private static final long serialVersionUID = -2950932750550952186L;

    private Map<String, Object> detail;

    private String updateTime;

    public Map<String, Object> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, Object> detail) {
        this.detail = detail;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public PositionDistributionResponse() {
    }

    public PositionDistributionResponse(Map<String, Object> detail, String updateTime) {
        this.detail = detail;
        this.updateTime = updateTime;
    }
}
