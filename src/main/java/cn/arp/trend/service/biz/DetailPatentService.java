package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.ZKYChinaPatentQueryDO;
import cn.arp.trend.data.model.DO.ZKYPCTPatentQueryDO;
import cn.arp.trend.data.model.DTO.ZKYChinaPatentInfoDTO;
import cn.arp.trend.data.model.DTO.ZKYPCTPatentInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/22
 * Time:下午12:16
 **/
public interface DetailPatentService {

    ZKYPCTPatentInfoDTO patentZKYPCTQuery(ZKYPCTPatentQueryDO query) throws Exception;

    ZKYChinaPatentInfoDTO patentZKYChinaQuery(ZKYChinaPatentQueryDO query);
}
