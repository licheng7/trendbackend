package cn.arp.trend.data.model.response.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/20
 * Time:下午6:52
 **/
@Data
@ToString
@ApiModel(value="DataResult",description="返回结果数据包装对象")
public class DataResult<T> extends BaseResult {

    /**
     * 业务响应字段
     */
    @ApiModelProperty(name = "data", value = "业务响应字段（当success=true时可获取）")
    private T data;

    /**
     * 错误码
     */
    @ApiModelProperty(name = "errorCode", value = "错误码（当success=false时可获取）")
    private Integer errorCode;

    /**
     * 错误描述
     */
    @ApiModelProperty(name = "errorMsg", value = "错误描述（当success=false时可获取）")
    private String errorMsg;
}
