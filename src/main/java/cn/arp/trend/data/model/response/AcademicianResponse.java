package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/25
 * Time:下午4:21
 **/
@ToString
public class AcademicianResponse implements Serializable {

    private static final long serialVersionUID = 2838943461724995830L;

    private FieldsResult fields;

    private List<String> institution;

    public FieldsResult getFields() {
        return fields;
    }

    public void setFields(FieldsResult fields) {
        this.fields = fields;
    }

    public List<String> getInstitution() {
        return institution;
    }

    public void setInstitution(List<String> institution) {
        this.institution = institution;
    }

    public AcademicianResponse() {}

    public AcademicianResponse(FieldsResult fields, List<String> institution) {
        this.fields = fields;
        this.institution = institution;
    }
}
