package cn.arp.trend.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午4:32
 **/
@ToString
public class FrequencyRequest implements Serializable {

    private static final long serialVersionUID = -2726439716077831238L;

    @ApiParam("name")
    @JsonProperty("name")
    @NotBlank(message = "name不能为空")
    private String name;

    @ApiParam("classify")
    @JsonProperty("classify")
    private String classify;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
