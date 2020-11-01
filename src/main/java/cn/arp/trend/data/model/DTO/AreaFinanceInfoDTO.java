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
public class AreaFinanceInfoDTO {

    private List<Map<String, Object>> dataAry;

    private List<String> yearList;

    private List<Map<String, Object>> resultList;

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

    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }

    public AreaFinanceInfoDTO(List<Map<String, Object>> dataAry, List<String> yearList) {
        this.dataAry = dataAry;
        this.yearList = yearList;
    }

    public AreaFinanceInfoDTO(List<Map<String, Object>> dataAry, List<String> yearList, List<Map<String, Object>> resultList) {
        this.dataAry = dataAry;
        this.yearList = yearList;
        this.resultList = resultList;
    }

    public AreaFinanceInfoDTO() {
    }
}
