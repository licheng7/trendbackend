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
 * Time:下午8:20
 **/
@ToString
@ApiModel(value="IncomeTrendRequest", description="detailAsset.js的/income_trend请求参数")
@Validated
public class IncomeTrendRequest implements Serializable {

    private static final long serialVersionUID = 8272739627610382426L;

    @ApiParam(name = "year", example = "2010")
    @JsonProperty("year")
    @NotBlank(message = "year不能为空")
    @Pattern(regexp = "^([1-2]\\d{3})$", message = "year格式不正确")
    private String year;

    @ApiParam("affiliationId")
    @JsonProperty("affiliation_id")
    private List<String> affiliationId;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getAffiliationId() {
        return affiliationId;
    }

    public void setAffiliationId(List<String> affiliationId) {
        this.affiliationId = affiliationId;
    }

}
