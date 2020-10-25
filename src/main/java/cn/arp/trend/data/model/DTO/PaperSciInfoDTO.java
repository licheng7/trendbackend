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
public class PaperSciInfoDTO {

    private String updateTime;

    private List<Integer> classify;

    private List<Integer> sCIClassify;

    private List<String> yearList;

    private List<Map<String, Object>> fields;

    private List<Map<String, Object>> topAffiliations;

    private List<List> resultArray;

    private List<Map<String, Object>> sCINewAry;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Integer> getClassify() {
        return classify;
    }

    public void setClassify(List<Integer> classify) {
        this.classify = classify;
    }

    public List<Integer> getsCIClassify() {
        return sCIClassify;
    }

    public void setsCIClassify(List<Integer> sCIClassify) {
        this.sCIClassify = sCIClassify;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<List> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<List> resultArray) {
        this.resultArray = resultArray;
    }

    public List<Map<String, Object>> getsCINewAry() {
        return sCINewAry;
    }

    public void setsCINewAry(List<Map<String, Object>> sCINewAry) {
        this.sCINewAry = sCINewAry;
    }

    public List<Map<String, Object>> getFields() {
        return fields;
    }

    public void setFields(List<Map<String, Object>> fields) {
        this.fields = fields;
    }

    public List<Map<String, Object>> getTopAffiliations() {
        return topAffiliations;
    }

    public void setTopAffiliations(List<Map<String, Object>> topAffiliations) {
        this.topAffiliations = topAffiliations;
    }
}
