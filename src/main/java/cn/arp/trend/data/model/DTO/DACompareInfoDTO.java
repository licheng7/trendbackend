package cn.arp.trend.data.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:上午12:05
 **/
public class DACompareInfoDTO {

    private List<List<Object>> ageTimeline;

    private List<List<Object>> countTimeline;

    private List<List<Object>> electedAgeTimeLine;

    private List<List<Object>> fieldsPieCAS;

    private List<List<Object>> fieldsPieCAE;

    private List<DACompareInfoDTO.Galaxy> galaxy;

    private List<List<Object>> topAcademicianAffiliation;

    public List<List<Object>> getAgeTimeline() {
        return ageTimeline;
    }

    public void setAgeTimeline(List<List<Object>> ageTimeline) {
        this.ageTimeline = ageTimeline;
    }

    public List<List<Object>> getCountTimeline() {
        return countTimeline;
    }

    public void setCountTimeline(List<List<Object>> countTimeline) {
        this.countTimeline = countTimeline;
    }

    public List<List<Object>> getElectedAgeTimeLine() {
        return electedAgeTimeLine;
    }

    public void setElectedAgeTimeLine(List<List<Object>> electedAgeTimeLine) {
        this.electedAgeTimeLine = electedAgeTimeLine;
    }

    public List<List<Object>> getFieldsPieCAS() {
        return fieldsPieCAS;
    }

    public void setFieldsPieCAS(List<List<Object>> fieldsPieCAS) {
        this.fieldsPieCAS = fieldsPieCAS;
    }

    public List<List<Object>> getFieldsPieCAE() {
        return fieldsPieCAE;
    }

    public void setFieldsPieCAE(List<List<Object>> fieldsPieCAE) {
        this.fieldsPieCAE = fieldsPieCAE;
    }

    public List<Galaxy> getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(List<Galaxy> galaxy) {
        this.galaxy = galaxy;
    }

    public List<List<Object>> getTopAcademicianAffiliation() {
        return topAcademicianAffiliation;
    }

    public void setTopAcademicianAffiliation(List<List<Object>> topAcademicianAffiliation) {
        this.topAcademicianAffiliation = topAcademicianAffiliation;
    }

    public class Galaxy{

        @JsonProperty("galaxy_fields")
        private List<Map<String, Object>> galaxyFields;

        @JsonProperty("galaxy_total")
        private List<List<Object>> galaxyTotal;

        public List<Map<String, Object>> getGalaxyFields() {
            return galaxyFields;
        }

        public void setGalaxyFields(List<Map<String, Object>> galaxyFields) {
            this.galaxyFields = galaxyFields;
        }

        public List<List<Object>> getGalaxyTotal() {
            return galaxyTotal;
        }

        public void setGalaxyTotal(List<List<Object>> galaxyTotal) {
            this.galaxyTotal = galaxyTotal;
        }
    }
}
