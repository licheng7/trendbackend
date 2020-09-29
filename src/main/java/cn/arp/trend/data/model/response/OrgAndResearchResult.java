package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午12:51
 **/
@ToString
public class OrgAndResearchResult implements Serializable {

    private static final long serialVersionUID = -2190179458036144138L;

    private String jgbh;

    private String jgmc;

    private String researchField;

    public OrgAndResearchResult(String jgbh, String jgmc, String researchField) {
        this.jgbh = jgbh;
        this.jgmc = jgmc;
        this.researchField = researchField;
    }

    public OrgAndResearchResult() {

    }

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
}
