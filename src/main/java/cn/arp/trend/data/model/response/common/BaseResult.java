package cn.arp.trend.data.model.response.common;

import io.swagger.annotations.ApiModel;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/20
 * Time:下午6:47
 **/
@ToString
@ApiModel(value="BaseResult",description="返回结果基础包装对象")
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 714200721146022337L;

    /**
     * 标记接口调用是否成功，
     *  取值 true 则业务响应字段data被赋值
     *  取值 false 则错误描述字段errorCode、errorMsg被赋值
     */
    /*@ApiModelProperty(name = "success", value = "标记接口调用是否成功：取值 true 则业务响应字段data被赋值；取值 false 则错误描述字段errorCode、errorMsg被赋值")*/
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public BaseResult() {
    }

    public BaseResult(boolean success) {
        this.success = success;
    }
}
