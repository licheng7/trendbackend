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
public class MentorDetailResponse implements Serializable {

    private static final long serialVersionUID = 7885443820367368468L;

    private List<Map> detail;

    public List<Map> getDetail() {
        return detail;
    }

    public void setDetail(List<Map> detail) {
        this.detail = detail;
    }

    public MentorDetailResponse() {
    }

    public MentorDetailResponse(List<Map> detail) {
        this.detail = detail;
    }
}
