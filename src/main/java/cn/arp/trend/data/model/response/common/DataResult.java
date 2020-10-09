package cn.arp.trend.data.model.response.common;

import io.swagger.annotations.ApiModel;
import lombok.ToString;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/20
 * Time:下午6:52
 **/
@ToString
@ApiModel(value="DataResult",description="返回结果数据包装对象")
public class DataResult<T> extends BaseResult {

    /**
     * 业务响应字段
     */
    /*@ApiModelProperty(name = "data", value = "业务响应字段（当success=true时可获取）")*/
    private T data;

    /**
     * 错误码
     */
    /*@ApiModelProperty(name = "errorCode", value = "错误码（当success=false时可获取）")*/
    private Integer errorCode;

    /**
     * 错误描述
     */
    /*@ApiModelProperty(name = "errorMsg", value = "错误描述（当success=false时可获取）")*/
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataResult() {}

    public DataResult(T data, Integer errorCode, String errorMsg) {
        this.data = data;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DataResult(boolean success, T data, Integer errorCode, String errorMsg) {
        super(success);
        this.data = data;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
