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
@ApiModel(value="DetailProjectIncreaseQueryRequest", description="detailProject.js的/increase请求参数")
@Validated
public class DetailProjectIncreaseQueryRequest implements Serializable {

    private static final long serialVersionUID = -1479890721140834646L;

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

}
