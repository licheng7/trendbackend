package cn.arp.trend.data.model.response;

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
public class AcademicianResponse implements Serializable {

    private static final long serialVersionUID = 2838943461724995830L;

    private Map<String, List<Map<String, String>>> fields;

    private List<Map<String, String>> institutions;

    public AcademicianResponse() {
    }

    public AcademicianResponse(Map<String, List<Map<String, String>>> fields, List<Map<String, String>> institutions) {
        this.fields = fields;
        this.institutions = institutions;
    }

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
}
