package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午11:20
 **/
public class AllSupervisorInfoDTO {

    private List<Map> distributionAge;

    private List<Map> distributionField;

    public List<Map> getDistributionAge() {
        return distributionAge;
    }

    public void setDistributionAge(List<Map> distributionAge) {
        this.distributionAge = distributionAge;
    }

    public List<Map> getDistributionField() {
        return distributionField;
    }

    public void setDistributionField(List<Map> distributionField) {
        this.distributionField = distributionField;
    }
}
