package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午1:24
 **/
@ToString
public class SortingResponse implements Serializable {

    private static final long serialVersionUID = -2726800844033725680L;

    private List<Map<String, Object>> country;

    public List<Map<String, Object>> getCountry() {
        return country;
    }

    public void setCountry(List<Map<String, Object>> country) {
        this.country = country;
    }

    public SortingResponse() {
    }

    public SortingResponse(List<Map<String, Object>> country) {
        this.country = country;
    }
}
