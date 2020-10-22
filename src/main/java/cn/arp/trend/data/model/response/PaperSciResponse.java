package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class PaperSciResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    Map<String, String> result;

    public PaperSciResponse() {
    }

    public PaperSciResponse(Map<String, String> result) {
        this.result = result;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}
