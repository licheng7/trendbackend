package cn.arp.trend.data.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:上午12:05
 **/
@Data
public class DACompareInfoDTO {

    private List<List<Object>> ageTimeline;

    private List<List<Object>> countTimeline;

    private List<List<Object>> electedAgeTimeLine;

    private List<List<Object>> fieldsPieCAS;

    private List<List<Object>> fieldsPieCAE;

    private List<DACompareInfoDTO.Galaxy> galaxy;

    private List<List<Object>> topAcademicianAffiliation;



    @Data
    public class Galaxy{

        @JsonProperty("galaxy_fields")
        private List<Map<String, Integer>> galaxyFields;

        @JsonProperty("galaxy_total")
        private List<List<Object>> galaxyTotal;

    }
}
