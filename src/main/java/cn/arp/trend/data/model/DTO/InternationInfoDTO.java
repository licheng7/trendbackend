package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午1:29
 **/
@ToString
public class InternationInfoDTO {

    private List<Map<String, String>> country;

    private List<Map<String, String>> nationality;

    private List<Map<String, String>> form;

    private List<String> sortedAgeYearList;

    private List<Integer> ageList;

    public InternationInfoDTO(List<Map<String, String>> country, List<Map<String, String>> nationality, List<Map<String, String>> form, List<String> sortedAgeYearList, List<Integer> ageList) {
        this.country = country;
        this.nationality = nationality;
        this.form = form;
        this.sortedAgeYearList = sortedAgeYearList;
        this.ageList = ageList;
    }

    public InternationInfoDTO() {
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

    public List<String> getSortedAgeYearList() {
        return sortedAgeYearList;
    }

    public void setSortedAgeYearList(List<String> sortedAgeYearList) {
        this.sortedAgeYearList = sortedAgeYearList;
    }

    public List<Integer> getAgeList() {
        return ageList;
    }

    public void setAgeList(List<Integer> ageList) {
        this.ageList = ageList;
    }
}
