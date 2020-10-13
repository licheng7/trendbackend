package cn.arp.trend.data.model.response;

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
public class DoctoralSupervisorResponse implements Serializable {

    private static final long serialVersionUID = -499911772883452519L;

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

    public DoctoralSupervisorResponse() {
    }

    public DoctoralSupervisorResponse(List<Map> distributionAge, List<Map> distributionField) {
        this.distributionAge = distributionAge;
        this.distributionField = distributionField;
    }
}
