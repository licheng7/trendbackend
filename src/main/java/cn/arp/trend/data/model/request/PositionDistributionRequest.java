package cn.arp.trend.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午11:29
 **/
@ToString
@ApiModel(value="PositionDistributionRequest", description="detailStaff.js的/positionDistribution请求参数")
@Validated
public class PositionDistributionRequest implements Serializable {

    private static final long serialVersionUID = -2484807406496233562L;

    @ApiParam(name = "endYear", example = "2019")
    @JsonProperty("end_year")
    @NotBlank(message = "endYear不能为空")
    @Pattern(regexp = "^([1-2]\\d{3})$", message = "endYear格式不正确")
    private String endYear;

    @ApiParam("affiliationId")
    @JsonProperty("affiliation_id")
    private List<String> affiliationId;

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public List<String> getAffiliationId() {
        return affiliationId;
    }

    public void setAffiliationId(List<String> affiliationId) {
        this.affiliationId = affiliationId;
    }
}
