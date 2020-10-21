package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午1:49
 **/
public class ForeignInfoDTO {

    private List<Map<String, Object>> topCountry;

    public List<Map<String, Object>> getTopCountry() {
        return topCountry;
    }

    public void setTopCountry(List<Map<String, Object>> topCountry) {
        this.topCountry = topCountry;
    }

    public ForeignInfoDTO() {
    }

    public ForeignInfoDTO(List<Map<String, Object>> topCountry) {
        this.topCountry = topCountry;
    }
}
