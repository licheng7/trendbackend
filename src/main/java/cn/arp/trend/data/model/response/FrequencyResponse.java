package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class FrequencyResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private Object unit;

    private Object upData;

    public Object getUnit() {
        return unit;
    }

    public void setUnit(Object unit) {
        this.unit = unit;
    }

    public Object getUpData() {
        return upData;
    }

    public void setUpData(Object upData) {
        this.upData = upData;
    }

    public FrequencyResponse() {
    }

    public FrequencyResponse(Object unit, Object upData) {
        this.unit = unit;
        this.upData = upData;
    }
}
