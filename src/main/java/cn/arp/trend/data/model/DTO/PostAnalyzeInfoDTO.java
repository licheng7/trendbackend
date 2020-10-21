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
public class PostAnalyzeInfoDTO {

    private List<Map<String, Object>> detail;

    private String updateTime;

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

    public PostAnalyzeInfoDTO(List<Map<String, Object>> detail, String updateTime) {
        this.detail = detail;
        this.updateTime = updateTime;
    }

    public PostAnalyzeInfoDTO() {
    }
}
