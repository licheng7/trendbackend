package cn.arp.trend.data.model.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:上午12:05
 **/
public class DACompareInfoDTO {

    private List<Map<String, List<Integer>>> ageTimeline;

    private List<Map<String, List<Integer>>> countTimeline;

    private List<Map<String, List<Integer>>> electedAgeTimeLine;

    private List<MapResultDTO<String, Integer>> fieldsPieCAS;

    private List<MapResultDTO<String, Integer>> fieldsPieCAE;

    private List<DACompareInfoDTO.Galaxy> galaxyList;

    private List<MapResultDTO<String, Integer>> topAcademicianAffiliation;

    public List<Map<String, List<Integer>>> getAgeTimeline() {
        return ageTimeline;
    }

    public void setAgeTimeline(List<Map<String, List<Integer>>> ageTimeline) {
        this.ageTimeline = ageTimeline;
    }

    public List<Map<String, List<Integer>>> getCountTimeline() {
        return countTimeline;
    }

    public void setCountTimeline(List<Map<String, List<Integer>>> countTimeline) {
        this.countTimeline = countTimeline;
    }

    public List<Map<String, List<Integer>>> getElectedAgeTimeLine() {
        return electedAgeTimeLine;
    }

    public void setElectedAgeTimeLine(List<Map<String, List<Integer>>> electedAgeTimeLine) {
        this.electedAgeTimeLine = electedAgeTimeLine;
    }

    public List<MapResultDTO<String, Integer>> getFieldsPieCAS() {
        return fieldsPieCAS;
    }

    public void setFieldsPieCAS(List<MapResultDTO<String, Integer>> fieldsPieCAS) {
        this.fieldsPieCAS = fieldsPieCAS;
    }

    public List<MapResultDTO<String, Integer>> getFieldsPieCAE() {
        return fieldsPieCAE;
    }

    public void setFieldsPieCAE(List<MapResultDTO<String, Integer>> fieldsPieCAE) {
        this.fieldsPieCAE = fieldsPieCAE;
    }

    public List<Galaxy> getGalaxyList() {
        return galaxyList;
    }

    public void setGalaxyList(List<Galaxy> galaxyList) {
        this.galaxyList = galaxyList;
    }

    public List<MapResultDTO<String, Integer>> getTopAcademicianAffiliation() {
        return topAcademicianAffiliation;
    }

    public void setTopAcademicianAffiliation(List<MapResultDTO<String, Integer>> topAcademicianAffiliation) {
        this.topAcademicianAffiliation = topAcademicianAffiliation;
    }

    public class Galaxy{
        private List<Integer> galaxyFields;

        private Map<String, Integer> galaxyTotal;

        public List<Integer> getGalaxyFields() {
            return galaxyFields;
        }

        public void setGalaxyFields(List<Integer> galaxyFields) {
            this.galaxyFields = galaxyFields;
        }

        public Map<String, Integer> getGalaxyTotal() {
            return galaxyTotal;
        }

        public void setGalaxyTotal(Map<String, Integer> galaxyTotal) {
            this.galaxyTotal = galaxyTotal;
        }

        public Galaxy() {
        }

        public Galaxy(List<Integer> galaxyFields, Map<String, Integer> galaxyTotal) {
            this.galaxyFields = galaxyFields;
            this.galaxyTotal = galaxyTotal;
        }
    }
}
