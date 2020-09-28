package cn.arp.trend.data.model.constant;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/20
 * Time:下午9:17
 **/
public enum ExceptionCode {

    EXCEPTION(-100, "server exception, msg is %s");

    public final int code;
    public final String message;

    private ExceptionCode(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
