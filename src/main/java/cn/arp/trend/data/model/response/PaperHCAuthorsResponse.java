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
public class PaperHCAuthorsResponse implements Serializable {

    private static final long serialVersionUID = -1843502965655966849L;

    private List<Object> classify;

    private Map<String, List<Object>> fields;

    @JsonProperty("new_data")
    private List<Map<String, Object>> newData;

    private List<Map<Object, Object>> topAuthors;

    private List<String> year;

    private String updateTime;

    public PaperHCAuthorsResponse(List<Object> classify, Map<String, List<Object>> fields, List<Map<String, Object>> newData, List<Map<Object, Object>> topAuthors, List<String> year, String updateTime) {
        this.classify = classify;
        this.fields = fields;
        this.newData = newData;
        this.topAuthors = topAuthors;
        this.year = year;
        this.updateTime = updateTime;
    }

    public PaperHCAuthorsResponse() {
    }

    public List<Object> getClassify() {
        return classify;
    }

    public void setClassify(List<Object> classify) {
        this.classify = classify;
    }

    public Map<String, List<Object>> getFields() {
        return fields;
    }

    public void setFields(Map<String, List<Object>> fields) {
        this.fields = fields;
    }

    public List<Map<String, Object>> getNewData() {
        return newData;
    }

    public void setNewData(List<Map<String, Object>> newData) {
        this.newData = newData;
    }

    public List<Map<Object, Object>> getTopAuthors() {
        return topAuthors;
    }

    public void setTopAuthors(List<Map<Object, Object>> topAuthors) {
        this.topAuthors = topAuthors;
    }

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
