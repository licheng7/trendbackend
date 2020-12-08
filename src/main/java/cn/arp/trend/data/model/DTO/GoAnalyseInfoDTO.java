package cn.arp.trend.data.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午1:46
 **/
public class GoAnalyseInfoDTO {

    private int countryNum;

    private int countryPeopleNum;

    private int ydylCountryNum;

    private int ydylCountryPeopleNum;

    private List<Map<String, Object>> countryObjList;

    private List<Map<String, Object>> fieldObjList;

    private List<Map<String, Object>> formObjList;

    private List<Map<String, Object>> affiliationObjList;

    private List<Integer> yearNumList;

    private List<Integer> ageNumList;

    private List<String> agelist;

    private List<String> yearList;

    private List<Map<String, Object>> countryNumObject;

    private List<String> topTenCountryName;

    private List<Map<String, Object>> topTenCountryList;

    private List<List<TempObjDTO>> cityAndCountryMapList;

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

    public List<Map<String, Object>> getCountryObjList() {
        return countryObjList;
    }

    public void setCountryObjList(List<Map<String, Object>> countryObjList) {
        this.countryObjList = countryObjList;
    }

    public List<Map<String, Object>> getFieldObjList() {
        return fieldObjList;
    }

    public void setFieldObjList(List<Map<String, Object>> fieldObjList) {
        this.fieldObjList = fieldObjList;
    }

    public List<Map<String, Object>> getFormObjList() {
        return formObjList;
    }

    public void setFormObjList(List<Map<String, Object>> formObjList) {
        this.formObjList = formObjList;
    }

    public List<Map<String, Object>> getAffiliationObjList() {
        return affiliationObjList;
    }

    public void setAffiliationObjList(List<Map<String, Object>> affiliationObjList) {
        this.affiliationObjList = affiliationObjList;
    }

    public List<Integer> getYearNumList() {
        return yearNumList;
    }

    public void setYearNumList(List<Integer> yearNumList) {
        this.yearNumList = yearNumList;
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

    public List<Map<String, Object>> getCountryNumObject() {
        return countryNumObject;
    }

    public void setCountryNumObject(List<Map<String, Object>> countryNumObject) {
        this.countryNumObject = countryNumObject;
    }

    public List<String> getTopTenCountryName() {
        return topTenCountryName;
    }

    public void setTopTenCountryName(List<String> topTenCountryName) {
        this.topTenCountryName = topTenCountryName;
    }

    public List<Map<String, Object>> getTopTenCountryList() {
        return topTenCountryList;
    }

    public void setTopTenCountryList(List<Map<String, Object>> topTenCountryList) {
        this.topTenCountryList = topTenCountryList;
    }

    public List<List<TempObjDTO>> getCityAndCountryMapList() {
        return cityAndCountryMapList;
    }

    public void setCityAndCountryMapList(List<List<TempObjDTO>> cityAndCountryMapList) {
        this.cityAndCountryMapList = cityAndCountryMapList;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }

    public List<Integer> getAgeNumList() {
        return ageNumList;
    }

    public void setAgeNumList(List<Integer> ageNumList) {
        this.ageNumList = ageNumList;
    }
}
