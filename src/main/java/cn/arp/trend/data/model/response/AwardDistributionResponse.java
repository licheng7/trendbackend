package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AwardDistributionResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("award_original_ary")
    private List<Map<String, Object>> awardOriginalAry;

    @JsonProperty("award_ary")
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

    public AwardDistributionResponse(List<Map<String, Object>> awardOriginalAry, List<Map<String, Object>> awardAry) {
        this.awardOriginalAry = awardOriginalAry;
        this.awardAry = awardAry;
    }

    public AwardDistributionResponse() {
    }
}
