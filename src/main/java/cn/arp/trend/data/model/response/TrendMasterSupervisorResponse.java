package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午11:32
 **/
@ToString
public class TrendMasterSupervisorResponse implements Serializable {

    private static final long serialVersionUID = -2808799995579868832L;

    private List<Map> detail;

    public List<Map> getDetail() {
        return detail;
    }

    public void setDetail(List<Map> detail) {
        this.detail = detail;
    }

    public TrendMasterSupervisorResponse() {
    }

    public TrendMasterSupervisorResponse(List<Map> detail) {
        this.detail = detail;
    }
}
