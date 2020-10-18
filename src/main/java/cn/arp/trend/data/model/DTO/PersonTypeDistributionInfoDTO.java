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
public class PersonTypeDistributionInfoDTO {

    private Map<String, Object> detail;

    private String updateTime;

    private Map<String, Object> resultArray;

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

    public Map<String, Object> getResultArray() {
        return resultArray;
    }

    public void setResultArray(Map<String, Object> resultArray) {
        this.resultArray = resultArray;
    }

    public PersonTypeDistributionInfoDTO(Map<String, Object> detail, String updateTime, Map<String, Object> resultArray) {
        this.detail = detail;
        this.updateTime = updateTime;
        this.resultArray = resultArray;
    }

    public PersonTypeDistributionInfoDTO() {
    }
}
