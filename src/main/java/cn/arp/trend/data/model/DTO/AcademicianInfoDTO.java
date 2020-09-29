package cn.arp.trend.data.model.DTO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:39
 **/
public class AcademicianInfoDTO {

    List<String> institutionsZKY;

    List<String> institutionsGCY;

    List<String> fieldsZKY;

    List<String> fieldsGCY;

    List<String> institutions;

    public List<String> getInstitutionsZKY() {
        return institutionsZKY;
    }

    public void setInstitutionsZKY(List<String> institutionsZKY) {
        this.institutionsZKY = institutionsZKY;
    }

    public List<String> getInstitutionsGCY() {
        return institutionsGCY;
    }

    public void setInstitutionsGCY(List<String> institutionsGCY) {
        this.institutionsGCY = institutionsGCY;
    }

    public List<String> getFieldsZKY() {
        return fieldsZKY;
    }

    public void setFieldsZKY(List<String> fieldsZKY) {
        this.fieldsZKY = fieldsZKY;
    }

    public List<String> getFieldsGCY() {
        return fieldsGCY;
    }

    public void setFieldsGCY(List<String> fieldsGCY) {
        this.fieldsGCY = fieldsGCY;
    }

    public List<String> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<String> institutions) {
        this.institutions = institutions;
    }
}
