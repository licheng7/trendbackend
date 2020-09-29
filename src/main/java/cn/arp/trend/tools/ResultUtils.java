package cn.arp.trend.tools;

import cn.arp.trend.data.model.response.common.DataResult;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/20
 * Time:下午7:30
 **/
public class ResultUtils {

    public static <T> DataResult<T> wrapSuccess(T data) {
        DataResult<T> result = new DataResult();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static DataResult wrapFailure(Integer errorCode, String errorMsg) {
        DataResult result = new DataResult();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }
}
