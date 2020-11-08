package cn.arp.trend.data.model.response.contrast;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/25
 * Time:下午4:21
 **/
@ToString
public class ContrastAwardByFieldResponse implements Serializable {

    private static final long serialVersionUID = 2828943461724995832L;

    private List<Object> res;

    public List<Object> getRes() {
        return res;
    }

    public void setRes(List<Object> res) {
        this.res = res;
    }

    public ContrastAwardByFieldResponse() {
    }

    public ContrastAwardByFieldResponse(
            List<Object> res) {
        this.res = res;
    }
}
