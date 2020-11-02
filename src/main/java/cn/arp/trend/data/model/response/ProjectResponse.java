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
    private long stdCumulation;

    @JsonProperty("std_new")
    private long stdNew;

    @JsonProperty("std_project")
    private List<String> stdProject;

    @JsonProperty("std_funds")
    private List<String> stdFunds;

    @JsonProperty("xd_cumulation")
    private long xdCumulation;

    @JsonProperty("xd_new")
    private long xdNew;

    @JsonProperty("xd_project")
    private List<String> xdProject;

    @JsonProperty("xd_funds")
    private List<String> xdFunds;

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

    public long getStdCumulation() {
        return stdCumulation;
    }

    public void setStdCumulation(long stdCumulation) {
        this.stdCumulation = stdCumulation;
    }

    public long getStdNew() {
        return stdNew;
    }

    public void setStdNew(long stdNew) {
        this.stdNew = stdNew;
    }

    public List<String> getStdProject() {
        return stdProject;
    }

    public void setStdProject(List<String> stdProject) {
        this.stdProject = stdProject;
    }

    public List<String> getStdFunds() {
        return stdFunds;
    }

    public void setStdFunds(List<String> stdFunds) {
        this.stdFunds = stdFunds;
    }

    public long getXdCumulation() {
        return xdCumulation;
    }

    public void setXdCumulation(long xdCumulation) {
        this.xdCumulation = xdCumulation;
    }

    public long getXdNew() {
        return xdNew;
    }

    public void setXdNew(long xdNew) {
        this.xdNew = xdNew;
    }

    public List<String> getXdProject() {
        return xdProject;
    }

    public void setXdProject(List<String> xdProject) {
        this.xdProject = xdProject;
    }

    public List<String> getXdFunds() {
        return xdFunds;
    }

    public void setXdFunds(List<String> xdFunds) {
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
