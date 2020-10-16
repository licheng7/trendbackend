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
public class ChildLevelDistributionInfoDTO {

    private String updateTime;

    private List<Map> total;

    private int totalNum;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Map> getTotal() {
        return total;
    }

    public void setTotal(List<Map> total) {
        this.total = total;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public ChildLevelDistributionInfoDTO(String updateTime, List<Map> total, int totalNum) {
        this.updateTime = updateTime;
        this.total = total;
        this.totalNum = totalNum;
    }

    public ChildLevelDistributionInfoDTO() {
    }
}
