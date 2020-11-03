package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.AreaProjectNsfcDistQueryDO;
import cn.arp.trend.data.model.DO.AreaProjectNsfcTrendQueryDO;
import cn.arp.trend.data.model.DO.AreaProjectQueryDO;
import cn.arp.trend.data.model.DTO.AreaProjectNsfcDistInfoDTO;
import cn.arp.trend.data.model.DTO.AreaProjectNsfcTrendInfoDTO;
import cn.arp.trend.data.model.DTO.AreaProjectXdzxInfoDTO;
import cn.arp.trend.data.model.DTO.AreaProjectZdyfInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/31
 * Time:下午9:48
 **/
public interface AreaProjectService {

    AreaProjectXdzxInfoDTO xdzxQuery(AreaProjectQueryDO query);

    AreaProjectZdyfInfoDTO zdyfQuery(AreaProjectQueryDO query);

    AreaProjectNsfcTrendInfoDTO nsfcTrendQuery(AreaProjectNsfcTrendQueryDO query);

    AreaProjectNsfcDistInfoDTO nsfcDistQuery(AreaProjectNsfcDistQueryDO query);
}
