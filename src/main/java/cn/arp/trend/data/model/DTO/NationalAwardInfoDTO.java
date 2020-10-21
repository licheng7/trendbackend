package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午9:59
 **/
@ToString
public class NationalAwardInfoDTO {

    private List<String> institution;

    private List<Long> zrkx;

    private List<Long> jsfm;

    private List<Long> jsjb;

    private List<List<Map<String, Object>>> resultArray;

    private String zrkxUpdateTime;

    private String jsfmUpdateTime;

    private String jsjbUpdateTime;

    private String kyyxUpdateTime;

    public NationalAwardInfoDTO(List<String> institution, List<Long> zrkx, List<Long> jsfm, List<Long> jsjb, List<List<Map<String, Object>>> resultArray, String zrkxUpdateTime, String jsfmUpdateTime, String jsjbUpdateTime, String kyyxUpdateTime) {
        this.institution = institution;
        this.zrkx = zrkx;
        this.jsfm = jsfm;
        this.jsjb = jsjb;
        this.resultArray = resultArray;
        this.zrkxUpdateTime = zrkxUpdateTime;
        this.jsfmUpdateTime = jsfmUpdateTime;
        this.jsjbUpdateTime = jsjbUpdateTime;
        this.kyyxUpdateTime = kyyxUpdateTime;
    }

    public NationalAwardInfoDTO() {
    }

    public List<String> getInstitution() {
        return institution;
    }

    public void setInstitution(List<String> institution) {
        this.institution = institution;
    }

    public List<Long> getZrkx() {
        return zrkx;
    }

    public void setZrkx(List<Long> zrkx) {
        this.zrkx = zrkx;
    }

    public List<Long> getJsfm() {
        return jsfm;
    }

    public void setJsfm(List<Long> jsfm) {
        this.jsfm = jsfm;
    }

    public List<Long> getJsjb() {
        return jsjb;
    }

    public void setJsjb(List<Long> jsjb) {
        this.jsjb = jsjb;
    }

    public List<List<Map<String, Object>>> getResultArray() {
        return resultArray;
    }

    public void setResultArray(List<List<Map<String, Object>>> resultArray) {
        this.resultArray = resultArray;
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
