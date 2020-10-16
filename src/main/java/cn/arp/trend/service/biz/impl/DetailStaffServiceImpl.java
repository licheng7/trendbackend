package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.AgeDistributionQueryDO;
import cn.arp.trend.data.model.DO.ChildLevelDistributionQueryDO;
import cn.arp.trend.data.model.DTO.AgeDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.ChildLevelDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.MapResultDTO;
import cn.arp.trend.repository.biz.manual.StatArpStaffAgeManualMapper;
import cn.arp.trend.repository.biz.manual.StatArpStaffEducationManualMapper;
import cn.arp.trend.service.biz.DetailStaffService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午1:04
 **/
@Service
public class DetailStaffServiceImpl extends AbstructServiceHelper implements DetailStaffService {

    @Resource
    private StatArpStaffAgeManualMapper statArpStaffAgeManualMapper;

    @Resource
    private StatArpStaffEducationManualMapper statArpStaffEducationManualMapper;

    @Override
    public AgeDistributionInfoDTO ageDistributionQuery(AgeDistributionQueryDO query) {
        List<Map<String, Object>> queryResult =
                statArpStaffAgeManualMapper.queryAgeDistribution(query);
        List<MapResultDTO> result = Lists.newArrayList();
        queryResult.stream().forEach(map -> {
            String nld = (String) map.get("nld");
            Integer rs = ((BigDecimal) map.get("rs")).intValue();
            result.add(new MapResultDTO<String, Integer>(nld, rs));
        });

        AgeDistributionInfoDTO ageDistributionInfo = new AgeDistributionInfoDTO();
        ageDistributionInfo.setDetail(result);
        ageDistributionInfo.setUpdateTime("2019年10月");
        ageDistributionInfo.setResultList(queryResult);
        return ageDistributionInfo;
    }

    @Override
    public ChildLevelDistributionInfoDTO childLevelDistributionQuery(ChildLevelDistributionQueryDO query) {
        List<Map<String, Object>> queryResult =
                statArpStaffEducationManualMapper.queryChildLevelDistribution(query);
        int totalNum = 0;
        List<Map> total = Lists.newArrayList();
        for(Map<String, Object> map : queryResult) {
            totalNum += ((Number) map.get("rs")).intValue();
            Map<String, Object> totalElement = Maps.newHashMap();
            totalElement.put("name", map.get("xl"));
            totalElement.put("value", ((Number) map.get("rs")).intValue());
            total.add(totalElement);
        }
        return new ChildLevelDistributionInfoDTO("2019年10月", total, totalNum);
    }
}
