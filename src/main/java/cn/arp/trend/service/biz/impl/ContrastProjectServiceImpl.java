package cn.arp.trend.service.biz.impl;

import cn.arp.trend.repository.biz.manual.ContrastProjectManualMapper;
import cn.arp.trend.service.biz.ContrastProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午1:48
 **/
@Service
public class ContrastProjectServiceImpl implements ContrastProjectService {

    @Resource
    private ContrastProjectManualMapper contrastProjectManualMapper;

    @Override
    public List<HashMap<String, Object>> byField(String userId, Integer startYear, Integer endYear, List<String> fieldIds) {

        List<String> fieldIdsQuotes = new ArrayList<String>();
        for(int i=0;i<fieldIds.size();i++)
        {
            fieldIdsQuotes.add(" \"" + fieldIds.get(i) + "\" ");
        }

        String arrayStr = " in (" + String.join(",", fieldIdsQuotes) +  ") ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("startYear", startYear);
        params.put("endYear", endYear);
        params.put("arrayStr", arrayStr);

        List<HashMap<String, Object>> tem1 = null;
        try
        {
              tem1 = contrastProjectManualMapper.contrastByField(params);
        }
        catch (Exception e)
        {
            return null;
        }

        return tem1;
    }

    @Override
    public List<HashMap<String, Object>> byUnit(String userId, Integer startYear, Integer endYear, List<String> fieldIds) {

        List<String> fieldIdsQuotes = new ArrayList<String>();
        for(int i=0;i<fieldIds.size();i++)
        {
            fieldIdsQuotes.add(" \"" + fieldIds.get(i) + "\" ");
        }

        String arrayStr = " in (" + String.join(",", fieldIdsQuotes) +  ") ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("startYear", startYear);
        params.put("endYear", endYear);
        params.put("arrayStr", arrayStr);

        List<HashMap<String, Object>> tem1 = null;
        try
        {
            tem1 = contrastProjectManualMapper.contrastByUnit(params);
        }
        catch (Exception e)
        {
            return null;
        }

        return tem1;
    }
}
