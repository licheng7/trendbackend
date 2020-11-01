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
public class AreaFinanceOutcomeResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private List<Map<String, Object>> dataAry;

    @JsonProperty("year_list")
    private List<String> yearList;

    public List<Map<String, Object>> getDataAry() {
        return dataAry;
    }

    public void setDataAry(List<Map<String, Object>> dataAry) {
        this.dataAry = dataAry;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public AreaFinanceOutcomeResponse() {
    }

    public AreaFinanceOutcomeResponse(List<Map<String, Object>> dataAry, List<String> yearList) {
        this.dataAry = dataAry;
        this.yearList = yearList;
    }
}
