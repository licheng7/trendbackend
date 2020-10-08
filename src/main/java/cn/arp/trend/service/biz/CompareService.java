package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DTO.FundsInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午12:26
 **/
public interface CompareService {

    FundsInfoDTO fundsQuery(String startYear, String endYear);
}
