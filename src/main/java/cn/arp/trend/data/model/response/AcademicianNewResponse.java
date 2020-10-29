package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/25
 * Time:下午4:21
 **/
@ToString
public class AcademicianNewResponse implements Serializable {

    private static final long serialVersionUID = 2838943461724995830L;

    private Map<String, List<Map<String, String>>> fields;

    private List<Map<String, String>> institutions;

    @JsonProperty("result_array")
    private List<List> resultArray;

    public Map<String, List<Map<String, String>>> getFields() {
        return fields;
    }

    public void setFields(Map<String, List<Map<String, String>>> fields) {
        this.fields = fields;
    }

    public List<Map<String, String>> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<Map<String, String>> institutions) {
        this.institutions = institutions;
    }

    public List<List> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<List> resultArray) {
        this.resultArray = resultArray;
    }

    public AcademicianNewResponse() {
    }

    public AcademicianNewResponse(Map<String, List<Map<String, String>>> fields, List<Map<String, String>> institutions, List<List> resultArray) {
        this.fields = fields;
        this.institutions = institutions;
        this.resultArray = resultArray;
    }
}
