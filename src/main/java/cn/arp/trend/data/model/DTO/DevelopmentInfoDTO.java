package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午9:59
 **/
@ToString
public class DevelopmentInfoDTO {

    private List<String> institution;

    List<Double> zrkx;

    List<Double> jsfm;

    List<Double> jsjb;

    List<MapResultDTO> newkj;

    List<MapResultDTO> newkx;

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

    public List<MapResultDTO> getNewkj() {
        return newkj;
    }

    public void setNewkj(List<MapResultDTO> newkj) {
        this.newkj = newkj;
    }

    public List<MapResultDTO> getNewkx() {
        return newkx;
    }

    public void setNewkx(List<MapResultDTO> newkx) {
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
