package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AreaFinanceRankResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    @JsonProperty("result_array")
    private List<Map<String, Object>> resultList;

    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }

    public AreaFinanceRankResponse() {
    }

    public AreaFinanceRankResponse(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }
}
