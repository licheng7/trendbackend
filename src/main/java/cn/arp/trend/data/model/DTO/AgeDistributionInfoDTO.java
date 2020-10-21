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
public class AgeDistributionInfoDTO {

    private String updateTime;

    private List<MapResultDTO> detail;

    private List<Map<String, Object>> resultList;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<MapResultDTO> getDetail() {
        return detail;
    }

    public void setDetail(List<MapResultDTO> detail) {
        this.detail = detail;
    }

    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }
}
