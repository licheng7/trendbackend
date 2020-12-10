package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.ComeAnalyseQueryDO;
import cn.arp.trend.data.model.DO.GoAnalyseQueryDO;
import cn.arp.trend.data.model.DTO.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午12:09
 **/
public interface CollaborationService {

    RankInfoDTO rankQuery();

    Rank2InfoDTO rankQuery2();

    LinksInfoDTO linksQuery(String startYear, String endYear);

    List<List<Map<String, Object>>> countryNumQuery();

    GoAnalyseInfoDTO goAnalyseQuery(GoAnalyseQueryDO goAnalyseQuery);

    ComeAnalyseInfoDTO comeAnalyseQuery(ComeAnalyseQueryDO comeAnalyseQuery);
}
