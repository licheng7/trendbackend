package cn.arp.trend.data.model.exception;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/20
 * Time:下午8:31
 **/
public class TrendServerException extends RuntimeException {

    public TrendServerException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private Integer errorCode;

    private String errorMsg;

    public Integer TrendServerExceptiongetErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
