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
public class PaperHCAuthorsInfoDTO {

    private List<Object> classify;

    private Map<String, List<Object>> fields;

    private List<Map<String, Object>> newData;

    private List<Map<Object, Object>> topAuthors;

    private List<String> year;

    private String updateTime;

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
