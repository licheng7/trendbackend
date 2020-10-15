package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午12:50
 **/
@ToString
public class OrgInfoResponse implements Serializable {

    private static final long serialVersionUID = 3127637399912081785L;

    private List<Map<String, Object>> institutions;

    private List<Map<String, Object>> fields;

    public List<Map<String, Object>> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<Map<String, Object>> institutions) {
        this.institutions = institutions;
    }

    public List<Map<String, Object>> getFields() {
        return fields;
    }

    public void setFields(List<Map<String, Object>> fields) {
        this.fields = fields;
    }

    public OrgInfoResponse() {
    }

    public OrgInfoResponse(List<Map<String, Object>> institutions, List<Map<String, Object>> fields) {
        this.institutions = institutions;
        this.fields = fields;
    }
}
