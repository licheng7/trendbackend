package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
@ToString
public class AreaAwardDistributionInfoDTO {

    private List<Map<String, Object>> awardOriginalAry;

    private List<Map<String, Object>> awardAry;

    public List<Map<String, Object>> getAwardOriginalAry() {
        return awardOriginalAry;
    }

    public void setAwardOriginalAry(List<Map<String, Object>> awardOriginalAry) {
        this.awardOriginalAry = awardOriginalAry;
    }

    public List<Map<String, Object>> getAwardAry() {
        return awardAry;
    }

    public void setAwardAry(List<Map<String, Object>> awardAry) {
        this.awardAry = awardAry;
    }

    public AreaAwardDistributionInfoDTO(List<Map<String, Object>> awardOriginalAry, List<Map<String, Object>> awardAry) {
        this.awardOriginalAry = awardOriginalAry;
        this.awardAry = awardAry;
    }

    public AreaAwardDistributionInfoDTO() {
    }
}
