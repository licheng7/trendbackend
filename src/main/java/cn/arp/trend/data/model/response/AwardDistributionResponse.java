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

    @JsonProperty("award_pie")
    private List<Map<String, Object>> awardPie;

    @JsonProperty("award_ary")
    private List<Map<String, Object>> awardAry;

    public List<Map<String, Object>> getAwardPie() {
        return awardPie;
    }

    public void setAwardPie(List<Map<String, Object>> awardPie) {
        this.awardPie = awardPie;
    }

    public List<Map<String, Object>> getAwardAry() {
        return awardAry;
    }

    public void setAwardAry(List<Map<String, Object>> awardAry) {
        this.awardAry = awardAry;
    }

    public AwardDistributionResponse(List<Map<String, Object>> awardPie, List<Map<String, Object>> awardAry) {
        this.awardPie = awardPie;
        this.awardAry = awardAry;
    }

    public AwardDistributionResponse() {
    }
}
