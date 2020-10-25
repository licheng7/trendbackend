package cn.arp.trend.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午4:32
 **/
@ToString
public class DistributionFieldRequest implements Serializable {

    private static final long serialVersionUID = -2726439716077831238L;

    @ApiParam(name = "year", example = "2010")
    @JsonProperty("year")
    @NotBlank(message = "year不能为空")
    @Pattern(regexp = "^([1-2]\\d{3})$", message = "year格式不正确")
    private String year;

    @ApiParam("affiliation_id")
    @JsonProperty("affiliation_id")
    private List<String> affiliationId;

    @ApiParam("field_name")
    @JsonProperty("field_name")
    private List<String> fieldName;

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

    public List<String> getFieldName() {
        return fieldName;
    }

    public void setFieldName(List<String> fieldName) {
        this.fieldName = fieldName;
    }
}
