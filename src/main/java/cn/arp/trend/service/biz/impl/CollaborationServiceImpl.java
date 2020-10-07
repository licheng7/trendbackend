package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DTO.GotoCountryDTO;
import cn.arp.trend.data.model.DTO.GotoUnitDTO;
import cn.arp.trend.data.model.DTO.Rank2InfoDTO;
import cn.arp.trend.data.model.DTO.RankInfoDTO;
import cn.arp.trend.entity.biz.Country;
import cn.arp.trend.entity.biz.Rank;
import cn.arp.trend.entity.biz.Unit;
import cn.arp.trend.repository.biz.manual.IcComeManualMapper;
import cn.arp.trend.repository.biz.manual.IcGoManualMapper;
import cn.arp.trend.service.biz.CollaborationService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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

    @Override
    public Rank2InfoDTO rankQuery2() {
        List<Unit> goUnitList = icGoManualMapper.queryGoUnit();
        List<Country> goCountryList = icGoManualMapper.queryGoCountry();
        List<Unit> comeUnitList = icComeManualMapper.queryComeUnit();
        List<Country> comeCountryList = icComeManualMapper.queryComeCountry();

        List<GotoUnitDTO> gotoUnitList = Lists.newArrayList();
        for(Unit unit : goUnitList) {
            gotoUnitList.add(new GotoUnitDTO(
                    unit.getJgmc(),
                    unit.getNum(),
                    null
            ));
        }
        for(Unit unit : comeUnitList) {
            gotoUnitList.add(new GotoUnitDTO(
                    unit.getJgmc(),
                    null,
                    unit.getNum()
            ));
        }
        Map<String, List<GotoUnitDTO>> gotoUnitMap = gotoUnitList.stream().collect(
                Collectors.groupingBy(obj -> obj.getName()));
        List<GotoUnitDTO> distinctGotoUnitList = Lists.newArrayList();
        for(String key : gotoUnitMap.keySet()) {
            List<GotoUnitDTO> gotoUnitByKeyList = gotoUnitMap.get(key);
            int _goNum = 0;
            int _comeNum = 0;
            for(GotoUnitDTO gotoUnit : gotoUnitByKeyList) {
                _goNum = _goNum + (gotoUnit.getGoNum() == null ? 0 : gotoUnit.getGoNum());
                _comeNum = _comeNum + (gotoUnit.getComeNum() == null ? 0 : gotoUnit.getComeNum());
            }
            GotoUnitDTO newGotoUnitDTO = new GotoUnitDTO(key, _goNum, _comeNum);
            distinctGotoUnitList.add(newGotoUnitDTO);
        }

        List<GotoCountryDTO> gotoCountryList = Lists.newArrayList();
        for(Country country : goCountryList) {
            gotoCountryList.add(new GotoCountryDTO(
                    country.getCountry(),
                    country.getNum(),
                    null
            ));
        }
        for(Country country : comeCountryList) {
            gotoCountryList.add(new GotoCountryDTO(
                    country.getCountry(),
                    null,
                    country.getNum()
            ));
        }
        Map<String, List<GotoCountryDTO>> gotoCountryMap = gotoCountryList.stream().collect(
                Collectors.groupingBy(obj -> obj.getName()));
        List<GotoCountryDTO> distinctGotoCountryList = Lists.newArrayList();
        for(String key : gotoCountryMap.keySet()) {
            List<GotoCountryDTO> gotoCountryByKeyList = gotoCountryMap.get(key);
            int _goNum = 0;
            int _comeNum = 0;
            for(GotoCountryDTO gotoCountry : gotoCountryByKeyList) {
                _goNum = _goNum + (gotoCountry.getGoNum() == null ? 0 : gotoCountry.getGoNum());
                _comeNum = _comeNum + (gotoCountry.getComeNum() == null ? 0 : gotoCountry.getComeNum());
            }
            GotoCountryDTO newGotoCountryDTO = new GotoCountryDTO(key, _goNum, _comeNum);
            distinctGotoCountryList.add(newGotoCountryDTO);
        }

        return new Rank2InfoDTO(distinctGotoUnitList, distinctGotoCountryList);
    }
}
