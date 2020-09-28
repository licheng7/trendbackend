package cn.arp.trend.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午12:51
 **/
@Data
@ToString
@ApiModel(value="OrgInfoQueryRequest", description="单位查询请求参数")
@NoArgsConstructor
@AllArgsConstructor
public class OrgInfoQueryRequest implements Serializable {

    private static final long serialVersionUID = 3459087622107972063L;

    @JsonProperty("affiliation_id")
    private List<String> affiliationIds;

    @JsonProperty("field_name")
    private List<String> fieldNames;

    public void setAffiliationIds(List<String> affiliationIds) {
        this.affiliationIds = affiliationIds;
    }

    public List<String> getAffiliationIds() {
        return affiliationIds;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }
}
