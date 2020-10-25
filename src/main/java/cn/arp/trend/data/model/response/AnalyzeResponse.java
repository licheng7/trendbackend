package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/5
 * Time:下午11:04
 **/
@ToString
public class AnalyzeResponse implements Serializable {

    private static final long serialVersionUID = -5769768276312224992L;

    @JsonProperty("field_map")
    Map<String, String> fieldMap;

    List<List<Object>> all;

    public AnalyzeResponse() {
    }

    public AnalyzeResponse(Map<String, String> fieldMap, List<List<Object>> all) {
        this.fieldMap = fieldMap;
        this.all = all;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public List<List<Object>> getAll() {
        return all;
    }

    public void setAll(List<List<Object>> all) {
        this.all = all;
    }
}
