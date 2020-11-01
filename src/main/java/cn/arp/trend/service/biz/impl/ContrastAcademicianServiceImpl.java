package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.data.model.DO.ForeignQueryDO;
import cn.arp.trend.data.model.DTO.DACompareInfoDTO;
import cn.arp.trend.data.model.DTO.ForeignInfoDTO;
import cn.arp.trend.data.model.DTO.MapResultDTO;
import cn.arp.trend.repository.biz.manual.CasAcademicianChinaManualMapper;
import cn.arp.trend.repository.biz.manual.CasAcademicianForeignManualMapper;
import cn.arp.trend.repository.biz.manual.ContrastAcademicianManualMapper;
import cn.arp.trend.service.biz.ContrastAcademicianService;
import cn.arp.trend.service.biz.ContrastBaseService;
import cn.arp.trend.service.biz.DetailAcademicianService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午1:48
 **/
@Service
public class ContrastAcademicianServiceImpl implements ContrastAcademicianService {

    @Resource
    private ContrastAcademicianManualMapper contrastAcademicianManualMapper;

    @Override
    public List<HashMap<String, Object>>  byField(String userId, Integer startYear, Integer endYear, List<String> fieldIds) {

        List<String> fieldIdsQuotes = new ArrayList<String>();
        for(int i=0;i<fieldIds.size();i++)
        {
            fieldIdsQuotes.add(" \"" + fieldIds.get(i) + "\" ");
        }

        String fieldIdsStr = " in (" + String.join(",", fieldIdsQuotes) +  ") ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("startYear", startYear);
        params.put("endYear", endYear);
        params.put("fieldIdsStr", fieldIdsStr);

        List<HashMap<String, Object>> tem = null;
        try
        {
              tem = contrastAcademicianManualMapper.contrastByField(params);
        }
        catch (Exception e)
        {
            return null;
        }

        return tem;
    }

    @Override
    public Object byUnit(String userId, Integer startYear, Integer endYear, List<String> jgbhs) {
        return null;
    }
}
