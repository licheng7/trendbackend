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
public class AgeDistributionResponse implements Serializable {

    private static final long serialVersionUID = -2950932750550952186L;

    private String updateTime;

    private List<MapResult> detail;

    private List<Map<String, Object>> resultList;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<MapResult> getDetail() {
        return detail;
    }

    public void setDetail(List<MapResult> detail) {
        this.detail = detail;
    }

    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }

    public AgeDistributionResponse() {
    }

    public AgeDistributionResponse(String updateTime, List<MapResult> detail, List<Map<String, Object>> resultList) {
        this.updateTime = updateTime;
        this.detail = detail;
        this.resultList = resultList;
    }
}
