package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class ForeignResponse implements Serializable {

    private static final long serialVersionUID = 4406090897171861487L;

    private List<Map<String, Object>> topCountry;

    public List<Map<String, Object>> getTopCountry() {
        return topCountry;
    }

    public void setTopCountry(List<Map<String, Object>> topCountry) {
        this.topCountry = topCountry;
    }

    public ForeignResponse() {
    }

    public ForeignResponse(List<Map<String, Object>> topCountry) {
        this.topCountry = topCountry;
    }
}
