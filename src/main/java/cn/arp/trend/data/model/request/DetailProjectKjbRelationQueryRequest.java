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
 * Date:2020/10/9
 * Time:下午9:17
 **/
@ToString
@ApiModel(value="DetailProjectKjbRelationQueryRequest", description="detailProject.js的/kjbRelation请求参数")
@Validated
public class DetailProjectKjbRelationQueryRequest implements Serializable {

    private static final long serialVersionUID = -1479890721140834646L;

    @ApiParam(name = "start_year", example = "2010")
    @JsonProperty("start_year")
    @NotBlank(message = "startYear不能为空")
    @Pattern(regexp = "^([1-2]\\d{3})$", message = "startYear格式不正确")
    private String startYear;

    @ApiParam(name = "end_year", example = "2019")
    @JsonProperty("end_year")
    @NotBlank(message = "endYear不能为空")
    @Pattern(regexp = "^([1-2]\\d{3})$", message = "endYear格式不正确")
    private String endYear;

    @ApiParam(value = "affiliation_id")
    @JsonProperty("affiliation_id")
    private List<String> affiliationIds;

    @ApiParam("field_name")
    @JsonProperty("field_name")
    private List<String> fieldNames;

    public List<String> getAffiliationIds() {
        return affiliationIds;
    }

    public void setAffiliationIds(List<String> affiliationIds) {
        this.affiliationIds = affiliationIds;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

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
