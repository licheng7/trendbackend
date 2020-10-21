package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:07
 **/
@ToString
public class PostDistributionInfoDTO {

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

    public PostDistributionInfoDTO() {
    }

    public PostDistributionInfoDTO(Map<String, Object> detail, String updateTime) {
        this.detail = detail;
        this.updateTime = updateTime;
    }
}
