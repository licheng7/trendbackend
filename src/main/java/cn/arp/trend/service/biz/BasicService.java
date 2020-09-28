package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.data.model.DTO.OrgInfoDTO;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午1:00
 **/
public interface BasicService {

    OrgInfoDTO orgInfoQuery(OrgInfoQueryDO orgInfoQueryDO);

    List<String> queryYear();

    //void academician(AcademicianQueryDTO academician);
}
