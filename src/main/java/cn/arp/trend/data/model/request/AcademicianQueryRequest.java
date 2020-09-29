package cn.arp.trend.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:12
 **/
@ToString
@ApiModel(value="AcademicianQueryRequest", description="获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）请求参数")
public class AcademicianQueryRequest implements Serializable {

    private static final long serialVersionUID = -6536218052575197273L;

    @ApiParam("affiliationIds")
    @JsonProperty("affiliation_id")
    private List<String> affiliationIds;

    @ApiParam("afieldNameZky")
    @JsonProperty("field_name_zky")
    private List<String> afieldNameZky;

    @ApiParam("fieldNameGcy")
    @JsonProperty("field_name_gcy")
    private List<String> fieldNameGcy;

    public List<String> getAffiliationIds() {
        return affiliationIds;
    }

    public void setAffiliationIds(List<String> affiliationIds) {
        this.affiliationIds = affiliationIds;
    }

    public List<String> getAfieldNameZky() {
        return afieldNameZky;
    }

    public void setAfieldNameZky(List<String> afieldNameZky) {
        this.afieldNameZky = afieldNameZky;
    }

    public List<String> getFieldNameGcy() {
        return fieldNameGcy;
    }

    public void setFieldNameGcy(List<String> fieldNameGcy) {
        this.fieldNameGcy = fieldNameGcy;
    }

    public AcademicianQueryRequest() {
    }

    public AcademicianQueryRequest(List<String> affiliationIds, List<String> afieldNameZky, List<String> fieldNameGcy) {
        this.affiliationIds = affiliationIds;
        this.afieldNameZky = afieldNameZky;
        this.fieldNameGcy = fieldNameGcy;
    }
}
