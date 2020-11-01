package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.AreaPatentQueryDO;
import cn.arp.trend.data.model.DTO.AreaPatentInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/31
 * Time:下午9:48
 **/
public interface AreaPatentService {

    AreaPatentInfoDTO query(AreaPatentQueryDO query);
}
