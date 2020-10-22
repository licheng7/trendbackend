package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.DetailPaperQueryDO;
import cn.arp.trend.data.model.DTO.PaperSciInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/22
 * Time:上午10:48
 **/
public interface DetailPaperService {

    PaperSciInfoDTO paperSciQuery(DetailPaperQueryDO query);
}
