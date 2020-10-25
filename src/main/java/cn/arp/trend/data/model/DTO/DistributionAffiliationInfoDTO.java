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
public class DistributionAffiliationInfoDTO {

    private List<Map<String, Object>> detail;

    public List<Map<String, Object>> getDetail() {
        return detail;
    }

    public void setDetail(List<Map<String, Object>> detail) {
        this.detail = detail;
    }

    public DistributionAffiliationInfoDTO(List<Map<String, Object>> detail) {
        this.detail = detail;
    }

    public DistributionAffiliationInfoDTO() {
    }
}
