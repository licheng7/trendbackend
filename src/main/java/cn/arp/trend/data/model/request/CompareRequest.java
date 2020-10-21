package cn.arp.trend.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午4:32
 **/
@ToString
public class CompareRequest implements Serializable {

    private static final long serialVersionUID = -5939131828367268915L;

    @ApiParam("affiliation")
    @JsonProperty("affiliation")
    private List<String> affiliation;

    public List<String> getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(List<String> affiliation) {
        this.affiliation = affiliation;
    }
}
