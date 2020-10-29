package cn.arp.trend.data.model.DTO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午1:49
 **/
public class ForeignInfoDTO {

    private List<List<Object>> topCountry;

    public List<List<Object>> getTopCountry() {
        return topCountry;
    }

    public void setTopCountry(List<List<Object>> topCountry) {
        this.topCountry = topCountry;
    }

    public ForeignInfoDTO() {
    }

    public ForeignInfoDTO(List<List<Object>> topCountry) {
        this.topCountry = topCountry;
    }
}
