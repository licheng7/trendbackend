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
public class DetailAwardDistributionInfoDTO {

    private List<Map<String, Object>> awardPie;

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

    public DetailAwardDistributionInfoDTO(List<Map<String, Object>> awardPie, List<Map<String, Object>> awardAry) {
        this.awardPie = awardPie;
        this.awardAry = awardAry;
    }

    public DetailAwardDistributionInfoDTO() {
    }
}
