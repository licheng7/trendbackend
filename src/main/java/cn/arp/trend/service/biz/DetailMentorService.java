package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.*;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.data.model.response.MentorRadarResponse;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午10:43
 **/
public interface DetailMentorService {

    DoctoralSupervisorInfoDTO doctoralSupervisorQuery(DoctoralSupervisorQueryDO query);

    MasterSupervisorInfoDTO masterSupervisorQuery(MasterSupervisorQueryDO query);

    AllSupervisorInfoDTO allSupervisorQuery(AllSupervisorQueryDO query);

    TrendDoctoralSupervisorInfoDTO trendDoctoralSupervisorQuery(TrendDoctoralSupervisorQueryDO query);

    TrendMasterSupervisorInfoDTO trendMasterSupervisorQuery(TrendMasterSupervisorQueryDO query);

    TrendAllInfoDTO trendAllQuery(TrendAllQueryDO query);

    MentorDetailInfoDTO detailQuery(MentorDetailQueryDO query);

    List<MentorRadarResponse> radarDetail(List<String> affiliationIds);
}
