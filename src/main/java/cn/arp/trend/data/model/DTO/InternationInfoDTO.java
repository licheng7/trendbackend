package cn.arp.trend.data.model.DTO;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午1:29
 **/
@Data
@ToString
public class InternationInfoDTO {

    private List<String> sortedCountryList;

    private List<String> sortedNationalityList;

    private List<String> sortedFormList;

    private List<String> sortedAgeYearList;

    private List<Integer> ageList;
}
