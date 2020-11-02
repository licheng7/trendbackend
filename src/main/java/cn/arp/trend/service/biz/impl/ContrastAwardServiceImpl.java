package cn.arp.trend.service.biz.impl;

import cn.arp.trend.repository.biz.manual.ContrastAcademicianManualMapper;
import cn.arp.trend.repository.biz.manual.ContrastAwardManualMapper;
import cn.arp.trend.service.biz.ContrastAcademicianService;
import cn.arp.trend.service.biz.ContrastAwardService;
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
public class ContrastAwardServiceImpl implements ContrastAwardService {

    @Resource
    private ContrastAwardManualMapper contrastAwardManualMapper;

    @Override
    public List<HashMap<String, Object>> byField1(String userId, Integer startYear, Integer endYear, List<String> fieldIds) {

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
              tem1 = contrastAwardManualMapper.contrastByField1(params);
        }
        catch (Exception e)
        {
            return null;
        }

        return tem1;
    }

    @Override
    public List<HashMap<String, Object>> byField2(String userId, Integer startYear, Integer endYear, List<String> fieldIds) {

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

        List<HashMap<String, Object>> tem2 = null;
        try
        {
            tem2 = contrastAwardManualMapper.contrastByField2(params);
        }
        catch (Exception e)
        {
            return null;
        }

        return tem2;
    }


    @Override
    public List<HashMap<String, Object>> byField3(String userId, Integer startYear, Integer endYear, List<String> fieldIds) {

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

        List<HashMap<String, Object>> tem3 = null;
        try
        {
            tem3 = contrastAwardManualMapper.contrastByField3(params);
        }
        catch (Exception e)
        {
            return null;
        }

        return tem3;
    }


    @Override
    public List<HashMap<String, Object>>  byUnit(String userId, Integer startYear, Integer endYear, List<String> fieldIds) {

        return null;
    }
}
