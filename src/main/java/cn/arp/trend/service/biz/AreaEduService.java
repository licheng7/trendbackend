package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.AreaEduQueryDO;
import cn.arp.trend.data.model.DTO.AreaEduDInfoDTO;
import cn.arp.trend.data.model.DTO.AreaEduMInfoDTO;
import cn.arp.trend.data.model.DTO.AreaEduStudentInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/30
 * Time:上午7:27
 **/
public interface AreaEduService {

    AreaEduDInfoDTO areaEduDQuery(AreaEduQueryDO query);

    AreaEduMInfoDTO areaEduMQuery(AreaEduQueryDO query);

    AreaEduStudentInfoDTO areaEduStudentQuery(AreaEduQueryDO query);
}
