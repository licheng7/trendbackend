package cn.arp.trend.data.model.DTO;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/6
 * Time:下午10:31
 **/
@ToString
public class AnalyzeInfoDTO {

    Map<String, String> fieldMap;

    List<List<Object>> all;

    public AnalyzeInfoDTO() {
    }

    public AnalyzeInfoDTO(Map<String, String> fieldMap, List<List<Object>> all) {
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
