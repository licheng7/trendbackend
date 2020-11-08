package cn.arp.trend.data.model.request.contrast;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:david
 * Date:2020/10/30
 * Time:21:32
 **/
@ToString
public class ContrastBaseRequest implements Serializable {

    private static final long serialVersionUID = -5929331828347268915L;

    @ApiParam(name = "userId", example = "11")
    @JsonProperty("userId")
    @NotBlank(message = "userId不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "userId格式不正确")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ApiParam("dataAry")
    @JsonProperty("dataAry")
    private List<String> dataAry;

    public List<String> getDataAry() {
        return dataAry;
    }

    public void setDataAry(List<String> dataAry) {
        this.dataAry = dataAry;
    }
}

