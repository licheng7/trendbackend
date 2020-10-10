package cn.arp.trend.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午9:17
 **/
@ToString
@ApiModel(value="ProjectQueryRequest", description="compare.js的/project请求参数")
@Validated
public class ProjectQueryRequest implements Serializable {

    private static final long serialVersionUID = -1479890721140834646L;

    @ApiParam(value = "affiliationIds")
    @JsonProperty("affiliation")
    private List<String> affiliationIds;

    @ApiParam("fieldName")
    @JsonProperty("field")
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
}
