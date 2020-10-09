package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DTO.FacilityInfoDTO;
import cn.arp.trend.data.model.DTO.FinanceInfoDTO;
import cn.arp.trend.data.model.DTO.FundsInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午12:26
 **/
public interface CompareService {

    FundsInfoDTO fundsQuery(String startYear, String endYear);

    FinanceInfoDTO financeQuery(String startYear, String endYear);

    FacilityInfoDTO facilityQuery(String startYear, String endYear);
}
