package cn.arp.trend.data.model.response;

import cn.arp.trend.data.model.DTO.TempObjDTO;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午12:23
 **/
@ToString
public class ComeAnalyseResponse implements Serializable{

    private static final long serialVersionUID = -5271723399710091607L;

    private int countryNum;

    private int countryPeopleNum;

    private int ydylCountryNum;

    private int ydylCountryPeopleNum;

    private List<String> countryObjList;

    private Map<String, Integer> fieldObjList;

    private Map<String, Integer> formObjList;

    private Map<String, Integer> affiliationObjList;

    private Map<String, Integer> yearNumList;

    private Map<Integer, Integer> ageNumList;

    private List<String> agelist;

    private List<String> yearList;

    private Map<String, Integer> countryNumberObject;

    private List<String> topTenCountryName;

    private Map<String, Map<String, Integer>> topTenCountryList;

    private List<TempObjDTO> cityAndCountryMapList;

    private List<String> cityList;

    public int getCountryNum() {
        return countryNum;
    }

    public void setCountryNum(int countryNum) {
        this.countryNum = countryNum;
    }

    public int getCountryPeopleNum() {
        return countryPeopleNum;
    }

    public void setCountryPeopleNum(int countryPeopleNum) {
        this.countryPeopleNum = countryPeopleNum;
    }

    public int getYdylCountryNum() {
        return ydylCountryNum;
    }

    public void setYdylCountryNum(int ydylCountryNum) {
        this.ydylCountryNum = ydylCountryNum;
    }

    public int getYdylCountryPeopleNum() {
        return ydylCountryPeopleNum;
    }

    public void setYdylCountryPeopleNum(int ydylCountryPeopleNum) {
        this.ydylCountryPeopleNum = ydylCountryPeopleNum;
    }

    public List<String> getCountryObjList() {
        return countryObjList;
    }

    public void setCountryObjList(List<String> countryObjList) {
        this.countryObjList = countryObjList;
    }

    public Map<String, Integer> getFieldObjList() {
        return fieldObjList;
    }

    public void setFieldObjList(Map<String, Integer> fieldObjList) {
        this.fieldObjList = fieldObjList;
    }

    public Map<String, Integer> getFormObjList() {
        return formObjList;
    }

    public void setFormObjList(Map<String, Integer> formObjList) {
        this.formObjList = formObjList;
    }

    public Map<String, Integer> getAffiliationObjList() {
        return affiliationObjList;
    }

    public void setAffiliationObjList(Map<String, Integer> affiliationObjList) {
        this.affiliationObjList = affiliationObjList;
    }

    public Map<String, Integer> getYearNumList() {
        return yearNumList;
    }

    public void setYearNumList(Map<String, Integer> yearNumList) {
        this.yearNumList = yearNumList;
    }

    public Map<Integer, Integer> getAgeNumList() {
        return ageNumList;
    }

    public void setAgeNumList(Map<Integer, Integer> ageNumList) {
        this.ageNumList = ageNumList;
    }

    public List<String> getAgelist() {
        return agelist;
    }

    public void setAgelist(List<String> agelist) {
        this.agelist = agelist;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public Map<String, Integer> getCountryNumberObject() {
        return countryNumberObject;
    }

    public void setCountryNumberObject(Map<String, Integer> countryNumberObject) {
        this.countryNumberObject = countryNumberObject;
    }

    public List<String> getTopTenCountryName() {
        return topTenCountryName;
    }

    public void setTopTenCountryName(List<String> topTenCountryName) {
        this.topTenCountryName = topTenCountryName;
    }

    public Map<String, Map<String, Integer>> getTopTenCountryList() {
        return topTenCountryList;
    }

    public void setTopTenCountryList(Map<String, Map<String, Integer>> topTenCountryList) {
        this.topTenCountryList = topTenCountryList;
    }

    public List<TempObjDTO> getCityAndCountryMapList() {
        return cityAndCountryMapList;
    }

    public void setCityAndCountryMapList(List<TempObjDTO> cityAndCountryMapList) {
        this.cityAndCountryMapList = cityAndCountryMapList;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }
}
