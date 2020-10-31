package cn.arp.trend.service.biz;

import java.util.List;

/**
 * Created with IDEA
 * author:david
 * Date:2020/10/12
 * Time:下午1:47
 **/
public interface ContrastAcademicianService {

    Object byField(String userId, String startYear, String endYear, List<String> fieldIds);

    Object byUnit(String userId, String startYear, String endYear, List<String> jgbhs);
}
