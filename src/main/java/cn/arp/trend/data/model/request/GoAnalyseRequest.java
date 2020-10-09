package cn.arp.trend.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午12:08
 **/
@ToString
@ApiModel(value="GoAnalyseRequest", description="collaboration.js的/goAnalyse请求参数")
@Validated
public class GoAnalyseRequest implements Serializable {

    private static final long serialVersionUID = -7068352001183526256L;

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

    @ApiParam(name = "startAge", example = "35")
    @JsonProperty("start_age")
    @NotNull(message = "startAge不能为空")
    @Min(value = 0, message = "startAge不能小于0")
    @Max(value = 200, message = "startAge过大")
    private Integer startAge;

    @ApiParam(name = "endAge", example = "90")
    @JsonProperty("end_age")
    @NotNull(message = "endAge不能为空")
    @Min(value = 0, message = "endAge不能小于0")
    @Max(value = 200, message = "endAge过大")
    private Integer endAge;

    @ApiParam("sex")
    @JsonProperty("sex")
    private String sex;

    @ApiParam("affiliationId")
    @JsonProperty("affiliation_id")
    private List<String> affiliationId;

    @ApiParam("fieldName")
    @JsonProperty("field_name")
    private List<String> fieldName;

    @ApiParam("country")
    @JsonProperty("country")
    private List<String> country;

    @ApiParam("nationality")
    @JsonProperty("nationality")
    private List<String> nationality;

    @ApiParam("form")
    @JsonProperty("form")
    private List<String> form;

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

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getNationality() {
        return nationality;
    }

    public void setNationality(List<String> nationality) {
        this.nationality = nationality;
    }

    public List<String> getForm() {
        return form;
    }

    public void setForm(List<String> form) {
        this.form = form;
    }
}
