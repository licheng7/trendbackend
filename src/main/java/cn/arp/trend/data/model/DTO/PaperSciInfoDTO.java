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
public class PaperSciInfoDTO {

    private Map<String, List<Map<String, String>>> fields;

    private List<Map<String, String>> institutions;

    public PaperSciInfoDTO() {
    }

    public PaperSciInfoDTO(Map<String, List<Map<String, String>>> fields, List<Map<String, String>> institutions) {
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
