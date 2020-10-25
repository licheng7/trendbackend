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
public class AcademicianInfoDTO {

    private Map<String, List<Map<String, String>>> fields;

    private List<Map<String, String>> institutions;

    private List<List> resultArray;

    public AcademicianInfoDTO() {
    }

    public AcademicianInfoDTO(Map<String, List<Map<String, String>>> fields, List<Map<String, String>> institutions, List<List> resultArray) {
        this.fields = fields;
        this.institutions = institutions;
        this.resultArray = resultArray;
    }

    public List<List> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<List> resultArray) {
        this.resultArray = resultArray;
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
