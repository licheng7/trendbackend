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

    Map<String, RefOrgType> afByJgmcMap;

    Map<String, StatTeacherStudent> eduByJgmcMap;

    Map<String, StatCasTalentsProgram> talent100ByJgmcMap;

    Map<String, StatPatent> patentByJgmcMap;

    Map<String, StatCasPaper> paperByJgmcMap;

    Map<String, StatNsfcProject> nsfcByJgmcMap;

    Map<String, StatMostProject> kjbByJgmcMap;

    Map<String, StatXdzx> xdByJgmcMap;

    Map<String, Funds> fundsByJgmcMap;

    Map<String, CasAcademicianChina> casByGzdwGf1Map;

    Map<String, CasAcademicianCaeChina> caeByGzdwGf1Map;

    Map<String, StatChinaAward10yearFinalCount> awardByFirstWcdwMap;

    public AnalyzeIntermediateResultDTO(List<RefOrgType> afList, List<StatTeacherStudent> eduList, List<StatCasTalentsProgram> talent100List, List<StatPatent> patentList, List<StatCasPaper> paperList, List<StatNsfcProject> nsfcList, List<StatMostProject> kjbList, List<StatXdzx> xdList, List<Funds> fundsList, List<CasAcademicianChina> casList, List<CasAcademicianCaeChina> caeList, List<StatChinaAward10yearFinalCount> awardList, Map<String, RefOrgType> afByJgmcMap, Map<String, StatTeacherStudent> eduByJgmcMap, Map<String, StatCasTalentsProgram> talent100ByJgmcMap, Map<String, StatPatent> patentByJgmcMap, Map<String, StatCasPaper> paperByJgmcMap, Map<String, StatNsfcProject> nsfcByJgmcMap, Map<String, StatMostProject> kjbByJgmcMap, Map<String, StatXdzx> xdByJgmcMap, Map<String, Funds> fundsByJgmcMap, Map<String, CasAcademicianChina> casByGzdwGf1Map, Map<String, CasAcademicianCaeChina> caeByGzdwGf1Map, Map<String, StatChinaAward10yearFinalCount> awardByFirstWcdwMap) {
        this.afList = afList;
        this.eduList = eduList;
        this.talent100List = talent100List;
        this.patentList = patentList;
        this.paperList = paperList;
        this.nsfcList = nsfcList;
        this.kjbList = kjbList;
        this.xdList = xdList;
        this.fundsList = fundsList;
        this.casList = casList;
        this.caeList = caeList;
        this.awardList = awardList;
        this.afByJgmcMap = afByJgmcMap;
        this.eduByJgmcMap = eduByJgmcMap;
        this.talent100ByJgmcMap = talent100ByJgmcMap;
        this.patentByJgmcMap = patentByJgmcMap;
        this.paperByJgmcMap = paperByJgmcMap;
        this.nsfcByJgmcMap = nsfcByJgmcMap;
        this.kjbByJgmcMap = kjbByJgmcMap;
        this.xdByJgmcMap = xdByJgmcMap;
        this.fundsByJgmcMap = fundsByJgmcMap;
        this.casByGzdwGf1Map = casByGzdwGf1Map;
        this.caeByGzdwGf1Map = caeByGzdwGf1Map;
        this.awardByFirstWcdwMap = awardByFirstWcdwMap;
    }

    public AnalyzeIntermediateResultDTO() {
    }

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

    public Map<String, RefOrgType> getAfByJgmcMap() {
        return afByJgmcMap;
    }

    public void setAfByJgmcMap(Map<String, RefOrgType> afByJgmcMap) {
        this.afByJgmcMap = afByJgmcMap;
    }

    public Map<String, StatTeacherStudent> getEduByJgmcMap() {
        return eduByJgmcMap;
    }

    public void setEduByJgmcMap(Map<String, StatTeacherStudent> eduByJgmcMap) {
        this.eduByJgmcMap = eduByJgmcMap;
    }

    public Map<String, StatCasTalentsProgram> getTalent100ByJgmcMap() {
        return talent100ByJgmcMap;
    }

    public void setTalent100ByJgmcMap(Map<String, StatCasTalentsProgram> talent100ByJgmcMap) {
        this.talent100ByJgmcMap = talent100ByJgmcMap;
    }

    public Map<String, StatPatent> getPatentByJgmcMap() {
        return patentByJgmcMap;
    }

    public void setPatentByJgmcMap(Map<String, StatPatent> patentByJgmcMap) {
        this.patentByJgmcMap = patentByJgmcMap;
    }

    public Map<String, StatCasPaper> getPaperByJgmcMap() {
        return paperByJgmcMap;
    }

    public void setPaperByJgmcMap(Map<String, StatCasPaper> paperByJgmcMap) {
        this.paperByJgmcMap = paperByJgmcMap;
    }

    public Map<String, StatNsfcProject> getNsfcByJgmcMap() {
        return nsfcByJgmcMap;
    }

    public void setNsfcByJgmcMap(Map<String, StatNsfcProject> nsfcByJgmcMap) {
        this.nsfcByJgmcMap = nsfcByJgmcMap;
    }

    public Map<String, StatMostProject> getKjbByJgmcMap() {
        return kjbByJgmcMap;
    }

    public void setKjbByJgmcMap(Map<String, StatMostProject> kjbByJgmcMap) {
        this.kjbByJgmcMap = kjbByJgmcMap;
    }

    public Map<String, StatXdzx> getXdByJgmcMap() {
        return xdByJgmcMap;
    }

    public void setXdByJgmcMap(Map<String, StatXdzx> xdByJgmcMap) {
        this.xdByJgmcMap = xdByJgmcMap;
    }

    public Map<String, Funds> getFundsByJgmcMap() {
        return fundsByJgmcMap;
    }

    public void setFundsByJgmcMap(Map<String, Funds> fundsByJgmcMap) {
        this.fundsByJgmcMap = fundsByJgmcMap;
    }

    public Map<String, CasAcademicianChina> getCasByGzdwGf1Map() {
        return casByGzdwGf1Map;
    }

    public void setCasByGzdwGf1Map(Map<String, CasAcademicianChina> casByGzdwGf1Map) {
        this.casByGzdwGf1Map = casByGzdwGf1Map;
    }

    public Map<String, CasAcademicianCaeChina> getCaeByGzdwGf1Map() {
        return caeByGzdwGf1Map;
    }

    public void setCaeByGzdwGf1Map(Map<String, CasAcademicianCaeChina> caeByGzdwGf1Map) {
        this.caeByGzdwGf1Map = caeByGzdwGf1Map;
    }

    public Map<String, StatChinaAward10yearFinalCount> getAwardByFirstWcdwMap() {
        return awardByFirstWcdwMap;
    }

    public void setAwardByFirstWcdwMap(Map<String, StatChinaAward10yearFinalCount> awardByFirstWcdwMap) {
        this.awardByFirstWcdwMap = awardByFirstWcdwMap;
    }
}
