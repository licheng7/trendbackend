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
public class ForeignResponse implements Serializable {

    private static final long serialVersionUID = 4406090897171861487L;

    private List<List<Object>> topCountry;

    public List<List<Object>> getTopCountry() {
        return topCountry;
    }

    public void setTopCountry(List<List<Object>> topCountry) {
        this.topCountry = topCountry;
    }

    public ForeignResponse() {
    }

    public ForeignResponse(List<List<Object>> topCountry) {
        this.topCountry = topCountry;
    }
}
