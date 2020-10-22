package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午9:06
 **/
@ToString
public class ProjectResponse implements Serializable {

    private static final long serialVersionUID = 4618895383067018389L;

    private List<String> category;

    private List<String> year;

    @JsonProperty("nsfc_cumulation")
    private long nsfcCumulation;

    @JsonProperty("nsfc_new")
    private long nsfcNew;

    @JsonProperty("nsfc_project")
    private List<String> nsfcProject;

    @JsonProperty("nsfc_funds")
    private List<String> nsfcFunds;

    @JsonProperty("std_cumulation")
    private String stdCumulation;

    @JsonProperty("std_new")
    private String stdNew;

    @JsonProperty("std_project")
    private String stdProject;

    @JsonProperty("std_funds")
    private String stdFunds;

    @JsonProperty("xd_cumulation")
    private String xdCumulation;

    @JsonProperty("xd_new")
    private String xdNew;

    @JsonProperty("xd_project")
    private String xdProject;

    @JsonProperty("xd_funds")
    private String xdFunds;

    private List<ProjectOrderResult> order;

    private String nsfcUpdateTime;

    private String stdUpdateTime;

    private String xdUpdateTime;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public long getNsfcCumulation() {
        return nsfcCumulation;
    }

    public void setNsfcCumulation(long nsfcCumulation) {
        this.nsfcCumulation = nsfcCumulation;
    }

    public long getNsfcNew() {
        return nsfcNew;
    }

    public void setNsfcNew(long nsfcNew) {
        this.nsfcNew = nsfcNew;
    }

    public List<String> getNsfcProject() {
        return nsfcProject;
    }

    public void setNsfcProject(List<String> nsfcProject) {
        this.nsfcProject = nsfcProject;
    }

    public List<String> getNsfcFunds() {
        return nsfcFunds;
    }

    public void setNsfcFunds(List<String> nsfcFunds) {
        this.nsfcFunds = nsfcFunds;
    }

    public String getStdCumulation() {
        return stdCumulation;
    }

    public void setStdCumulation(String stdCumulation) {
        this.stdCumulation = stdCumulation;
    }

    public String getStdNew() {
        return stdNew;
    }

    public void setStdNew(String stdNew) {
        this.stdNew = stdNew;
    }

    public String getStdProject() {
        return stdProject;
    }

    public void setStdProject(String stdProject) {
        this.stdProject = stdProject;
    }

    public String getStdFunds() {
        return stdFunds;
    }

    public void setStdFunds(String stdFunds) {
        this.stdFunds = stdFunds;
    }

    public String getXdCumulation() {
        return xdCumulation;
    }

    public void setXdCumulation(String xdCumulation) {
        this.xdCumulation = xdCumulation;
    }

    public String getXdNew() {
        return xdNew;
    }

    public void setXdNew(String xdNew) {
        this.xdNew = xdNew;
    }

    public String getXdProject() {
        return xdProject;
    }

    public void setXdProject(String xdProject) {
        this.xdProject = xdProject;
    }

    public String getXdFunds() {
        return xdFunds;
    }

    public void setXdFunds(String xdFunds) {
        this.xdFunds = xdFunds;
    }

    public List<ProjectOrderResult> getOrder() {
        return order;
    }

    public void setOrder(List<ProjectOrderResult> order) {
        this.order = order;
    }

    public String getNsfcUpdateTime() {
        return nsfcUpdateTime;
    }

    public void setNsfcUpdateTime(String nsfcUpdateTime) {
        this.nsfcUpdateTime = nsfcUpdateTime;
    }

    public String getStdUpdateTime() {
        return stdUpdateTime;
    }

    public void setStdUpdateTime(String stdUpdateTime) {
        this.stdUpdateTime = stdUpdateTime;
    }

    public String getXdUpdateTime() {
        return xdUpdateTime;
    }

    public void setXdUpdateTime(String xdUpdateTime) {
        this.xdUpdateTime = xdUpdateTime;
    }
}
