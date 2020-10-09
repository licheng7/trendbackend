package cn.arp.trend.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午11:29
 **/
@ToString
@ApiModel(value="FundsRequest", description="compare.js的/funds请求参数")
@Validated
public class FundsRequest implements Serializable {

    private static final long serialVersionUID = -3111666669295478029L;

    @ApiParam(name = "startYear", example = "2010")
    @JsonProperty("start_year")
    @NotBlank(message = "startYear不能为空")
    @Pattern(regexp = "^([1-2]\\d{3})$", message = "startYear格式不正确")
    private String startYear;

    @ApiParam(name = "endYear", example = "2019")
    @JsonProperty("end_year")
    @NotBlank(message = "endYear不能为空")
    @Pattern(regexp = "^([1-2]\\d{3})$", message = "endYear格式不正确")
    private String endYear;

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }
}
