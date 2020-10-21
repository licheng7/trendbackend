package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午11:36
 **/
@ToString
public class OrgInfoDTO {

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

    public OrgInfoDTO() {
    }

    public OrgInfoDTO(List<Map<String, Object>> institutions, List<Map<String, Object>> fields) {
        this.institutions = institutions;
        this.fields = fields;
    }
}
