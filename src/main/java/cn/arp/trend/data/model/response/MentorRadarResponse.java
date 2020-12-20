package cn.arp.trend.data.model.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/15
 * Time:下午3:37
 **/
public class MentorRadarResponse implements Serializable {

    private static final long serialVersionUID = 7885443820367368468L;

    private String unit;

    private String jgbh;

    private List<Map> value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getJgbh() {
        return jgbh;
    }

    public void setJgbh(String jgbh) {
        this.jgbh = jgbh;
    }

    public List<Map> getValue() {
        return value;
    }

    public void setValue(List<Map> value) {
        this.value = value;
    }

    public MentorRadarResponse() {
    }

    public MentorRadarResponse(List<Map> value) {
        this.value = value;
    }
}
