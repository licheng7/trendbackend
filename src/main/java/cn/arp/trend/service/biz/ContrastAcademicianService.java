package cn.arp.trend.service.biz;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IDEA
 * author:david
 * Date:2020/10/12
 * Time:下午1:47
 **/
public interface ContrastAcademicianService {

    List<HashMap<String, Object>> byField(String userId, Integer startYear, Integer endYear, List<String> fieldIds);

    List<HashMap<String, Object>> byUnit(String userId, Integer startYear, Integer endYear, List<String> fieldIds);
}
