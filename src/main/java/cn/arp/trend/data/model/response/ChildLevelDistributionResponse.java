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
public class ChildLevelDistributionResponse implements Serializable {

    private static final long serialVersionUID = -2950932750550952186L;

    private String updateTime;

    private List<Map> detail;

    private int totalNum;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Map> getDetail() {
        return detail;
    }

    public void setDetail(List<Map> detail) {
        this.detail = detail;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public ChildLevelDistributionResponse(String updateTime, List<Map> detail, int totalNum) {
        this.updateTime = updateTime;
        this.detail = detail;
        this.totalNum = totalNum;
    }

    public ChildLevelDistributionResponse() {
    }
}
