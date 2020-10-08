package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.ComeAnalyseQueryDO;
import cn.arp.trend.data.model.DO.GoAnalyseQueryDO;
import cn.arp.trend.data.model.DTO.*;

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

    LinksInfoDTO linksQuery();

    Map<String, Map<String, Integer>> countryNumQuery();

    GoAnalyseInfoDTO goAnalyseQuery(GoAnalyseQueryDO goAnalyseQuery);

    ComeAnalyseInfoDTO comeAnalyseQuery(ComeAnalyseQueryDO comeAnalyseQuery);
}
