package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午11:36
 **/
@ToString
public class OrgInfoDTO {

    private List<String> fields;

    private List<OrgAndResearchDTO> institutions;

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<OrgAndResearchDTO> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<OrgAndResearchDTO> institutions) {
        this.institutions = institutions;
    }

    public OrgInfoDTO() {
    }

    public OrgInfoDTO(List<String> fields, List<OrgAndResearchDTO> institutions) {
        this.fields = fields;
        this.institutions = institutions;
    }

    @ToString
    public static class OrgAndResearchDTO {

        private String jgbh;

        private String jgmc;

        private String researchField;

        public String getJgbh() {
            return jgbh;
        }

        public void setJgbh(String jgbh) {
            this.jgbh = jgbh;
        }

        public String getJgmc() {
            return jgmc;
        }

        public void setJgmc(String jgmc) {
            this.jgmc = jgmc;
        }

        public String getResearchField() {
            return researchField;
        }

        public void setResearchField(String researchField) {
            this.researchField = researchField;
        }

        public OrgAndResearchDTO() {
        }

        public OrgAndResearchDTO(String jgbh, String jgmc, String researchField) {
            this.jgbh = jgbh;
            this.jgmc = jgmc;
            this.researchField = researchField;
        }
    }
}
