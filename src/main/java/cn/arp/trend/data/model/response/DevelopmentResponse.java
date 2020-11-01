package cn.arp.trend.data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/11
 * Time:上午12:22
 **/
@ToString
public class DevelopmentResponse implements Serializable {

    private static final long serialVersionUID = -5390073799804670291L;

    private List<String> institution;

    List<Double> zrkx;

    List<Double> jsfm;

    List<Double> jsjb;

    @JsonProperty("kj")
    List<MapResult> newkj;

    @JsonProperty("kx")
    List<MapResult> newkx;

    private String zrkxUpdateTime;

    private String jsfmUpdateTime;

    private String jsjbUpdateTime;

    private String kyyxUpdateTime;

    public List<String> getInstitution() {
        return institution;
    }

    public void setInstitution(List<String> institution) {
        this.institution = institution;
    }

    public List<Double> getZrkx() {
        return zrkx;
    }

    public void setZrkx(List<Double> zrkx) {
        this.zrkx = zrkx;
    }

    public List<Double> getJsfm() {
        return jsfm;
    }

    public void setJsfm(List<Double> jsfm) {
        this.jsfm = jsfm;
    }

    public List<Double> getJsjb() {
        return jsjb;
    }

    public void setJsjb(List<Double> jsjb) {
        this.jsjb = jsjb;
    }

    public List<MapResult> getNewkj() {
        return newkj;
    }

    public void setNewkj(List<MapResult> newkj) {
        this.newkj = newkj;
    }

    public List<MapResult> getNewkx() {
        return newkx;
    }

    public void setNewkx(List<MapResult> newkx) {
        this.newkx = newkx;
    }

    public String getZrkxUpdateTime() {
        return zrkxUpdateTime;
    }

    public void setZrkxUpdateTime(String zrkxUpdateTime) {
        this.zrkxUpdateTime = zrkxUpdateTime;
    }

    public String getJsfmUpdateTime() {
        return jsfmUpdateTime;
    }

    public void setJsfmUpdateTime(String jsfmUpdateTime) {
        this.jsfmUpdateTime = jsfmUpdateTime;
    }

    public String getJsjbUpdateTime() {
        return jsjbUpdateTime;
    }

    public void setJsjbUpdateTime(String jsjbUpdateTime) {
        this.jsjbUpdateTime = jsjbUpdateTime;
    }

    public String getKyyxUpdateTime() {
        return kyyxUpdateTime;
    }

    public void setKyyxUpdateTime(String kyyxUpdateTime) {
        this.kyyxUpdateTime = kyyxUpdateTime;
    }
}
