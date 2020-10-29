package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.data.model.DTO.AcademicianInfoDTO;
import cn.arp.trend.data.model.DTO.InternationInfoDTO;
import cn.arp.trend.data.model.DTO.OrgInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午1:00
 **/
public interface BasicService {

    OrgInfoDTO orgInfoQuery(OrgInfoQueryDO orgInfoQueryDO);

    List<String> yearQuery();

    AcademicianInfoDTO academicianQuery(AcademicianQueryDO academicianQueryDO);

    AcademicianInfoDTO academicianNewQuery(AcademicianQueryDO academicianQueryDO);

    InternationInfoDTO internationInfoQuery();

    List<Map<String, Object>> sortingQuery();
}
