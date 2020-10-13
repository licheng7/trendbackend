package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class CompareResponse implements Serializable {

    private static final long serialVersionUID = 2550264323455920610L;

    private List<Object> result;

    public List<Object> getResult() {
        return result;
    }

    public void setResult(List<Object> result) {
        this.result = result;
    }

    public CompareResponse() {
    }

    public CompareResponse(List<Object> result) {
        this.result = result;
    }
}
