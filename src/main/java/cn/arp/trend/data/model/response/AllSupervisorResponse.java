package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午11:32
 **/
@ToString
public class AllSupervisorResponse implements Serializable {

    private static final long serialVersionUID = 7124613119104042271L;

    @JsonProperty("distribution_age")
    private List<Map> distributionAge;

    @JsonProperty("distribution_field")
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

    public AllSupervisorResponse() {
    }

    public AllSupervisorResponse(List<Map> distributionAge, List<Map> distributionField) {
        this.distributionAge = distributionAge;
        this.distributionField = distributionField;
    }
}
