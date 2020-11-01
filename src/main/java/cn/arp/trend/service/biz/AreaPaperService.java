package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.AreaPaperQueryDO;
import cn.arp.trend.data.model.DTO.AreaPaperSciInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/31
 * Time:下午9:48
 **/
public interface AreaPaperService {

    AreaPaperSciInfoDTO sciQuery(AreaPaperQueryDO query);
}
