package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.AreaHrQueryDO;
import cn.arp.trend.data.model.DTO.*;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/30
 * Time:上午7:27
 **/
public interface AreaHrService {

    AreaHrStaffTrendInfoDTO areaStaffTrendQuery(AreaHrQueryDO query);

    AreaHrStaffDistInfoDTO areaStaffDistQuery(AreaHrQueryDO query);

    AreaHrAcadCaeTrendInfoDTO areaAcadCaeTrendQuery(AreaHrQueryDO query);

    AreaHrAcadCaeDistInfoDTO areaAcadCaeDistQuery(AreaHrQueryDO query);

    AreaHrAcadCasTrendInfoDTO areaAcadCasTrendQuery(AreaHrQueryDO query);

    AreaHrAcadCasDistInfoDTO areaAcadCasDistQuery(AreaHrQueryDO query);

    AreaHrYoungEliteInfoDTO areaYoungEliteQuery(AreaHrQueryDO query);
}
