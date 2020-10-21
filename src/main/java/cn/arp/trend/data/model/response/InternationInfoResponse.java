package cn.arp.trend.data.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午1:24
 **/
@ToString
public class InternationInfoResponse implements Serializable {

    private static final long serialVersionUID = -2726800844033725680L;

    private List<Map<String, String>> country;

    private List<Map<String, String>> nationality;

    private List<Map<String, String>> form;

    private List<Integer> ageList;

    private List<SexResult> sexList;

    public InternationInfoResponse() {
    }

    public InternationInfoResponse(List<Map<String, String>> country, List<Map<String, String>> nationality, List<Map<String, String>> form, List<Integer> ageList, List<SexResult> sexList) {
        this.country = country;
        this.nationality = nationality;
        this.form = form;
        this.ageList = ageList;
        this.sexList = sexList;
    }

    public List<Map<String, String>> getCountry() {
        return country;
    }

    public void setCountry(List<Map<String, String>> country) {
        this.country = country;
    }

    public List<Map<String, String>> getNationality() {
        return nationality;
    }

    public void setNationality(List<Map<String, String>> nationality) {
        this.nationality = nationality;
    }

    public List<Map<String, String>> getForm() {
        return form;
    }

    public void setForm(List<Map<String, String>> form) {
        this.form = form;
    }

    public List<Integer> getAgeList() {
        return ageList;
    }

    public void setAgeList(List<Integer> ageList) {
        this.ageList = ageList;
    }

    public List<SexResult> getSexList() {
        return sexList;
    }

    public void setSexList(List<SexResult> sexList) {
        this.sexList = sexList;
    }
}
