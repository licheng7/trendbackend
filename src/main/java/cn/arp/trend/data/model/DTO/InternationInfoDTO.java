package cn.arp.trend.data.model.DTO;

import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午1:29
 **/
@ToString
public class InternationInfoDTO {

    private List<String> sortedCountryList;

    private List<String> sortedNationalityList;

    private List<String> sortedFormList;

    private List<String> sortedAgeYearList;

    private List<Integer> ageList;

    public List<String> getSortedCountryList() {
        return sortedCountryList;
    }

    public void setSortedCountryList(List<String> sortedCountryList) {
        this.sortedCountryList = sortedCountryList;
    }

    public List<String> getSortedNationalityList() {
        return sortedNationalityList;
    }

    public void setSortedNationalityList(List<String> sortedNationalityList) {
        this.sortedNationalityList = sortedNationalityList;
    }

    public List<String> getSortedFormList() {
        return sortedFormList;
    }

    public void setSortedFormList(List<String> sortedFormList) {
        this.sortedFormList = sortedFormList;
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

    public InternationInfoDTO() {
    }

    public InternationInfoDTO(List<String> sortedCountryList, List<String> sortedNationalityList, List<String> sortedFormList, List<String> sortedAgeYearList, List<Integer> ageList) {
        this.sortedCountryList = sortedCountryList;
        this.sortedNationalityList = sortedNationalityList;
        this.sortedFormList = sortedFormList;
        this.sortedAgeYearList = sortedAgeYearList;
        this.ageList = ageList;
    }
}
