package cn.arp.trend.data.model.DTO;

import cn.arp.trend.entity.biz.*;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/6
 * Time:上午1:49
 **/
@ToString
public class AnalyzeIntermediateResultDTO {

    private List<RefOrgType> afList;

    private List<StatTeacherStudent> eduList;

    private List<StatCasTalentsProgram> talent100List;

    private List<StatPatent> patentList;

    private List<StatCasPaper> paperList;

    private List<StatNsfcProject> nsfcList;

    private List<StatMostProject> kjbList;

    private List<StatXdzx> xdList;

    private List<Funds> fundsList;

    private List<CasAcademicianChina> casList;

    private List<CasAcademicianCaeChina> caeList;

    private List<StatChinaAward10yearFinalCount> awardList;

    Map<String, List<RefOrgType>> afByJgmcMap;

    Map<String, List<StatTeacherStudent>> eduByJgmcMap;

    Map<String, List<StatCasTalentsProgram>> talent100ByJgmcMap;

    Map<String, List<StatPatent>> patentByJgmcMap;

    Map<String, List<StatCasPaper>> paperByJgmcMap;

    Map<String, List<StatNsfcProject>> nsfcByJgmcMap;

    Map<String, List<StatMostProject>> kjbByJgmcMap;

    Map<String, List<StatXdzx>> xdByJgmcMap;

    Map<String, List<Funds>> fundsByJgmcMap;

    Map<String, List<CasAcademicianChina>> casByGzdwGf1Map;

    Map<String, List<CasAcademicianCaeChina>> caeByGzdwGf1Map;

    Map<String, List<StatChinaAward10yearFinalCount>> awardByFirstWcdwMap;

    public List<RefOrgType> getAfList() {
        return afList;
    }

    public void setAfList(List<RefOrgType> afList) {
        this.afList = afList;
    }

    public List<StatTeacherStudent> getEduList() {
        return eduList;
    }

    public void setEduList(List<StatTeacherStudent> eduList) {
        this.eduList = eduList;
    }

    public List<StatCasTalentsProgram> getTalent100List() {
        return talent100List;
    }

    public void setTalent100List(List<StatCasTalentsProgram> talent100List) {
        this.talent100List = talent100List;
    }

    public List<StatPatent> getPatentList() {
        return patentList;
    }

    public void setPatentList(List<StatPatent> patentList) {
        this.patentList = patentList;
    }

    public List<StatCasPaper> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<StatCasPaper> paperList) {
        this.paperList = paperList;
    }

    public List<StatNsfcProject> getNsfcList() {
        return nsfcList;
    }

    public void setNsfcList(List<StatNsfcProject> nsfcList) {
        this.nsfcList = nsfcList;
    }

    public List<StatMostProject> getKjbList() {
        return kjbList;
    }

    public void setKjbList(List<StatMostProject> kjbList) {
        this.kjbList = kjbList;
    }

    public List<StatXdzx> getXdList() {
        return xdList;
    }

    public void setXdList(List<StatXdzx> xdList) {
        this.xdList = xdList;
    }

    public List<Funds> getFundsList() {
        return fundsList;
    }

    public void setFundsList(List<Funds> fundsList) {
        this.fundsList = fundsList;
    }

    public List<CasAcademicianChina> getCasList() {
        return casList;
    }

    public void setCasList(List<CasAcademicianChina> casList) {
        this.casList = casList;
    }

    public List<CasAcademicianCaeChina> getCaeList() {
        return caeList;
    }

    public void setCaeList(List<CasAcademicianCaeChina> caeList) {
        this.caeList = caeList;
    }

    public List<StatChinaAward10yearFinalCount> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<StatChinaAward10yearFinalCount> awardList) {
        this.awardList = awardList;
    }

    public Map<String, List<RefOrgType>> getAfByJgmcMap() {
        return afByJgmcMap;
    }

    public void setAfByJgmcMap(Map<String, List<RefOrgType>> afByJgmcMap) {
        this.afByJgmcMap = afByJgmcMap;
    }

    public Map<String, List<StatTeacherStudent>> getEduByJgmcMap() {
        return eduByJgmcMap;
    }

    public void setEduByJgmcMap(Map<String, List<StatTeacherStudent>> eduByJgmcMap) {
        this.eduByJgmcMap = eduByJgmcMap;
    }

    public Map<String, List<StatCasTalentsProgram>> getTalent100ByJgmcMap() {
        return talent100ByJgmcMap;
    }

    public void setTalent100ByJgmcMap(Map<String, List<StatCasTalentsProgram>> talent100ByJgmcMap) {
        this.talent100ByJgmcMap = talent100ByJgmcMap;
    }

    public Map<String, List<StatPatent>> getPatentByJgmcMap() {
        return patentByJgmcMap;
    }

    public void setPatentByJgmcMap(Map<String, List<StatPatent>> patentByJgmcMap) {
        this.patentByJgmcMap = patentByJgmcMap;
    }

    public Map<String, List<StatCasPaper>> getPaperByJgmcMap() {
        return paperByJgmcMap;
    }

    public void setPaperByJgmcMap(Map<String, List<StatCasPaper>> paperByJgmcMap) {
        this.paperByJgmcMap = paperByJgmcMap;
    }

    public Map<String, List<StatNsfcProject>> getNsfcByJgmcMap() {
        return nsfcByJgmcMap;
    }

    public void setNsfcByJgmcMap(Map<String, List<StatNsfcProject>> nsfcByJgmcMap) {
        this.nsfcByJgmcMap = nsfcByJgmcMap;
    }

    public Map<String, List<StatMostProject>> getKjbByJgmcMap() {
        return kjbByJgmcMap;
    }

    public void setKjbByJgmcMap(Map<String, List<StatMostProject>> kjbByJgmcMap) {
        this.kjbByJgmcMap = kjbByJgmcMap;
    }

    public Map<String, List<StatXdzx>> getXdByJgmcMap() {
        return xdByJgmcMap;
    }

    public void setXdByJgmcMap(Map<String, List<StatXdzx>> xdByJgmcMap) {
        this.xdByJgmcMap = xdByJgmcMap;
    }

    public Map<String, List<Funds>> getFundsByJgmcMap() {
        return fundsByJgmcMap;
    }

    public void setFundsByJgmcMap(Map<String, List<Funds>> fundsByJgmcMap) {
        this.fundsByJgmcMap = fundsByJgmcMap;
    }

    public Map<String, List<CasAcademicianChina>> getCasByGzdwGf1Map() {
        return casByGzdwGf1Map;
    }

    public void setCasByGzdwGf1Map(Map<String, List<CasAcademicianChina>> casByGzdwGf1Map) {
        this.casByGzdwGf1Map = casByGzdwGf1Map;
    }

    public Map<String, List<CasAcademicianCaeChina>> getCaeByGzdwGf1Map() {
        return caeByGzdwGf1Map;
    }

    public void setCaeByGzdwGf1Map(Map<String, List<CasAcademicianCaeChina>> caeByGzdwGf1Map) {
        this.caeByGzdwGf1Map = caeByGzdwGf1Map;
    }

    public Map<String, List<StatChinaAward10yearFinalCount>> getAwardByFirstWcdwMap() {
        return awardByFirstWcdwMap;
    }

    public void setAwardByFirstWcdwMap(Map<String, List<StatChinaAward10yearFinalCount>> awardByFirstWcdwMap) {
        this.awardByFirstWcdwMap = awardByFirstWcdwMap;
    }
}
