package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/5
 * Time:下午11:04
 **/
@ToString
public class AnalyzeResponse implements Serializable {

    private static final long serialVersionUID = -5769768276312224992L;

    private List<AnalyzeAllResult> all;

    private List<String> fieldMap;

    public AnalyzeResponse() {
    }

    public AnalyzeResponse(List<AnalyzeAllResult> all, List<String> fieldMap) {
        this.all = all;
        this.fieldMap = fieldMap;
    }

    public List<AnalyzeAllResult> getAll() {
        return all;
    }

    public void setAll(List<AnalyzeAllResult> all) {
        this.all = all;
    }

    public List<String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(List<String> fieldMap) {
        this.fieldMap = fieldMap;
    }
}
