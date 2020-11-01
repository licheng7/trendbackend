package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
@ToString
public class AreaFinanceOverviewInfoDTO {

    private List<List<Map<String, Object>>> resultList;

    public List<List<Map<String, Object>>> getResultList() {
        return resultList;
    }

    public void setResultList(List<List<Map<String, Object>>> resultList) {
        this.resultList = resultList;
    }

    public AreaFinanceOverviewInfoDTO(List<List<Map<String, Object>>> resultList) {
        this.resultList = resultList;
    }

    public AreaFinanceOverviewInfoDTO() {
    }
}
