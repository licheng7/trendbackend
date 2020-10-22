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
public class ZKYPCTPatentRequest implements Serializable {

    private static final long serialVersionUID = -2726439716077831238L;

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

    @ApiParam("affiliation_id")
    @JsonProperty("affiliation_id")
    private List<String> affiliation;

    @ApiParam("field_name")
    @JsonProperty("field_name")
    private List<String> fields;

    @ApiParam("category")
    @JsonProperty("category")
    @NotBlank(message = "category不能为空")
    private String category;

    @ApiParam("subDataType")
    @JsonProperty("subDataType")
    @NotBlank(message = "subDataType不能为空")
    private String subDataType;

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

    public List<String> getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(List<String> affiliation) {
        this.affiliation = affiliation;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubDataType() {
        return subDataType;
    }

    public void setSubDataType(String subDataType) {
        this.subDataType = subDataType;
    }
}
