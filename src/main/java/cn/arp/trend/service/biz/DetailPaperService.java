package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.PaperHCAuthorsQueryDO;
import cn.arp.trend.data.model.DO.PaperSciQueryDO;
import cn.arp.trend.data.model.DTO.PaperHCAuthorsInfoDTO;
import cn.arp.trend.data.model.DTO.PaperSciInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/22
 * Time:上午10:48
 **/
public interface DetailPaperService {

    PaperSciInfoDTO paperSciQuery(PaperSciQueryDO query);

    PaperHCAuthorsInfoDTO hCAuthorsQuery(PaperHCAuthorsQueryDO query);
}
