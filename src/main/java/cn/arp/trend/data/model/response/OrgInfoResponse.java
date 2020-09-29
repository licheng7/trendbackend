package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午12:50
 **/
@ToString
public class OrgInfoResponse implements Serializable {

    private static final long serialVersionUID = 3127637399912081785L;

    private List<String> fields;

    private List<OrgAndResearchResult> institutions;

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<OrgAndResearchResult> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<OrgAndResearchResult> institutions) {
        this.institutions = institutions;
    }

    public OrgInfoResponse() {
    }

    public OrgInfoResponse(List<String> fields, List<OrgAndResearchResult> institutions) {
        this.fields = fields;
        this.institutions = institutions;
    }
}
