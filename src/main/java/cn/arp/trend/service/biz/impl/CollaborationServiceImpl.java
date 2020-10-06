package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DTO.RankInfoDTO;
import cn.arp.trend.entity.biz.Rank;
import cn.arp.trend.repository.biz.manual.IcComeManualMapper;
import cn.arp.trend.repository.biz.manual.IcGoManualMapper;
import cn.arp.trend.service.biz.CollaborationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午12:09
 **/
@Service
public class CollaborationServiceImpl implements CollaborationService {

    @Resource
    private IcGoManualMapper icGoManualMapper;

    @Resource
    private IcComeManualMapper icComeManualMapper;

    @Override
    public RankInfoDTO rankQuery() {
        List<Rank> icGoRankList = icGoManualMapper.queryRank();
        List<Rank> icComeRankList = icComeManualMapper.queryRank();

        List<String> goCountryList = icGoRankList.stream().map(obj -> obj.getCountry()).distinct().collect(Collectors.toList());
        int goCountryPeopleNum = icGoRankList.size();
        List<Rank> goydylIcGoRankList = icGoRankList.stream().filter(
                obj -> obj.getYdyl().equals("Y")).collect(Collectors.toList());
        int goydylCountryPeopleNum = goydylIcGoRankList.size();
        List<String> goydylCountryList = goydylIcGoRankList.stream().map(obj -> obj.getCountry()).distinct().collect(Collectors.toList());

        List<String> comeCountryList = icComeRankList.stream().map(obj -> obj.getCountry()).distinct().collect(Collectors.toList());
        int comeCountryPeopleNum = icComeRankList.size();
        List<Rank> goydylIcComeRankList = icComeRankList.stream().filter(
                obj -> obj.getYdyl().equals("Y")).collect(Collectors.toList());
        int comeydylCountryPeopleNum = goydylIcComeRankList.size();
        List<String> comeydylCountryList = goydylIcComeRankList.stream().map(obj -> obj.getCountry()).distinct().collect(Collectors.toList());

        RankInfoDTO rankInfo = new RankInfoDTO();
        rankInfo.setGoCountryNum(goCountryList.size());
        rankInfo.setGoCountryPeopleNum(goCountryPeopleNum);
        rankInfo.setGoydylCountryNum(goydylCountryList.size());
        rankInfo.setGoydylCountryPeopleNum(goydylCountryPeopleNum);
        rankInfo.setComeCountryNum(comeCountryList.size());
        rankInfo.setComeCountryPeopleNum(comeCountryPeopleNum);
        rankInfo.setComeydylCountryNum(comeydylCountryList.size());
        rankInfo.setComeydylCountryPeopleNum(comeydylCountryPeopleNum);
        return rankInfo;
    }
}
