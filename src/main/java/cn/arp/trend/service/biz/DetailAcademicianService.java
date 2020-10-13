package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.data.model.DTO.ForeignInfoDTO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午1:47
 **/
public interface DetailAcademicianService {

    ForeignInfoDTO foreignQuery(List<String> affiliation);

    List<Object> compareQuery(DACompareQueryDO query);
}
