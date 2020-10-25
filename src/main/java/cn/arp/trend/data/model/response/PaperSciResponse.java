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
public class PaperSciResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private String updateTime;

    private List<Integer> classify;

    @JsonProperty("SCIClassify")
    private List<Integer> sCIClassify;

    private List<String> yearList;

    private List<Map<String, Object>> fields;

    private List<Map<String, Object>> topAffiliations;

    @JsonProperty("result_array")
    private List<List> resultArray;

    @JsonProperty("SCINewAry")
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
}
