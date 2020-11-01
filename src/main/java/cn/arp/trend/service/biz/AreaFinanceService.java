package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.AreaFinanceQueryDO;
import cn.arp.trend.data.model.DTO.AreaFinanceInfoDTO;
import cn.arp.trend.data.model.DTO.AreaFinanceOverviewInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/30
 * Time:下午9:26
 **/
public interface AreaFinanceService {

    AreaFinanceOverviewInfoDTO areaEduDQuery(AreaFinanceQueryDO query);

    AreaFinanceInfoDTO incomeQuery(AreaFinanceQueryDO query);

    AreaFinanceInfoDTO outcomeQuery(AreaFinanceQueryDO query);

    AreaFinanceInfoDTO rankQuery(AreaFinanceQueryDO query);
}
