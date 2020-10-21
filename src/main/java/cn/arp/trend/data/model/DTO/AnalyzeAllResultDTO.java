package cn.arp.trend.data.model.DTO;

import lombok.ToString;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/6
 * Time:上午12:28
 **/
@ToString
public class AnalyzeAllResultDTO {

    private int index;

    private String faf;

    private int mentor;

    private int concurrent;

    private int talent100;

    private long patent;

    private int paper;

    private long projectTotal = this.projectNsfc + this.projectKjb + this.projectXd;

    private long projectNsfc;

    private long projectKjb;

    private long projectXd;

    private double finance;

    private int acadenician = this.cas + this.cae;

    private int cas;

    private int cae;

    private long award;

    private String field;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFaf() {
        return faf;
    }

    public void setFaf(String faf) {
        this.faf = faf;
    }

    public int getMentor() {
        return mentor;
    }

    public void setMentor(int mentor) {
        this.mentor = mentor;
    }

    public int getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(int concurrent) {
        this.concurrent = concurrent;
    }

    public int getTalent100() {
        return talent100;
    }

    public void setTalent100(int talent100) {
        this.talent100 = talent100;
    }

    public long getPatent() {
        return patent;
    }

    public void setPatent(long patent) {
        this.patent = patent;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public long getProjectTotal() {
        return projectTotal;
    }

    public void setProjectTotal(long projectTotal) {
        this.projectTotal = projectTotal;
    }

    public long getProjectNsfc() {
        return projectNsfc;
    }

    public void setProjectNsfc(long projectNsfc) {
        this.projectNsfc = projectNsfc;
    }

    public long getProjectKjb() {
        return projectKjb;
    }

    public void setProjectKjb(long projectKjb) {
        this.projectKjb = projectKjb;
    }

    public long getProjectXd() {
        return projectXd;
    }

    public void setProjectXd(long projectXd) {
        this.projectXd = projectXd;
    }

    public double getFinance() {
        return finance;
    }

    public void setFinance(double finance) {
        this.finance = finance;
    }

    public int getAcadenician() {
        return acadenician;
    }

    public void setAcadenician(int acadenician) {
        this.acadenician = acadenician;
    }

    public int getCas() {
        return cas;
    }

    public void setCas(int cas) {
        this.cas = cas;
    }

    public int getCae() {
        return cae;
    }

    public void setCae(int cae) {
        this.cae = cae;
    }

    public long getAward() {
        return award;
    }

    public void setAward(long award) {
        this.award = award;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public AnalyzeAllResultDTO(int index, String faf, int mentor, int concurrent, int talent100,
                               long patent, int paper, long projectTotal, long projectNsfc, long
                                       projectKjb, long projectXd, double finance, int
                                       acadenician, int cas, int cae, long award, String field) {
        this.index = index;
        this.faf = faf;
        this.mentor = mentor;
        this.concurrent = concurrent;
        this.talent100 = talent100;
        this.patent = patent;
        this.paper = paper;
        this.projectTotal = projectTotal;
        this.projectNsfc = projectNsfc;
        this.projectKjb = projectKjb;
        this.projectXd = projectXd;
        this.finance = finance;
        this.acadenician = acadenician;
        this.cas = cas;
        this.cae = cae;
        this.award = award;
        this.field = field;
    }

    public AnalyzeAllResultDTO() {
    }
}
