package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/14
 * Time:上午1:07
 **/
public class TrendAllInfoDTO {

    private List<Map> result;

    public List<Map> getResult() {
        return result;
    }

    public void setResult(List<Map> result) {
        this.result = result;
    }

    public TrendAllInfoDTO() {
    }

    public TrendAllInfoDTO(List<Map> result) {
        this.result = result;
    }
}
