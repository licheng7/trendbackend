package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.DoctoralSupervisorQueryDO;
import cn.arp.trend.data.model.DTO.DoctoralSupervisorInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午10:43
 **/
public interface DetailMentorService {

    DoctoralSupervisorInfoDTO doctoralupervisorQuery(DoctoralSupervisorQueryDO query);
}
