package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.data.model.DTO.ForeignInfoDTO;

import java.util.List;

/**
 * Created with IDEA
 * author:david
 * Date:2020/10/12
 * Time:下午1:47
 **/
public interface ContrastBaseService {

    Object byField(String userId, String startYear, String endYear, List<String> fieldIds);

    Object byUnit(String userId, String startYear, String endYear, List<String> jgbhs);
}
