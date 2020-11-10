package cn.arp.trend.service.biz.impl;

import cn.arp.trend.repository.biz.manual.ContrastCustomManualMapper;
import cn.arp.trend.service.biz.ContrastCustomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ContrastCustomServiceImpl implements ContrastCustomService {

    @Resource
    private ContrastCustomManualMapper contrastCustomTagManualMapper;

    @Override
    public List<HashMap<String, Object>> getAffiliations() {

        List<HashMap<String, Object>> tem1 = null;
        try
        {
              tem1 = contrastCustomTagManualMapper.getAffiliations();
        }
        catch (Exception e)
        {
        	System.out.println(e.toString());
            return null;
        }

        return tem1;
    }

    @Override
    public List<HashMap<String, Object>> getUserTags(String userId) {
        List<HashMap<String, Object>> tem1 = null;
        try
        {
            tem1 = contrastCustomTagManualMapper.getUserTags(userId);
        }
        catch (Exception e)
        {
        	System.out.println(e.toString());
            return null;
        }

        return tem1;
    }

    @Override
    public List<HashMap<String, Object>> getFieldAffiliations(String userId, String fieldId) {
        List<HashMap<String, Object>> tem1 = null;
        try
        {
            tem1 = contrastCustomTagManualMapper.getFieldAffiliations(userId, fieldId);
        }
        catch (Exception e)
        {
        	System.out.println(e.toString());
            return null;
        }

        return tem1;
    }

    @Override
    public HashMap<String, Object> addField1(String userId) {
        int tem1 = -1;
        HashMap<String, Object> params = new HashMap<String, Object>();
        try
        {
            params.put("id", -1);
            params.put("userId", userId);
            tem1 = contrastCustomTagManualMapper.addField1(params);
            params.put("result", tem1);
        }
        catch (Exception e)
        {
        	System.out.println(e.toString());
            return null;
        }

        return params;
    }

    @Override
    public HashMap<String, Object> addField2(String userId, String researchField) {
        int tem1 = -1;
        HashMap<String, Object> params = new HashMap<String, Object>();
        try
        {
            params.put("id", -1);
            params.put("userId", userId);
            params.put("researchField", researchField);
            tem1 = contrastCustomTagManualMapper.addField2(params);
            params.put("result", tem1);
        }
        catch (Exception e)
        {
        	System.out.println(e.toString());
            return null;
        }

        return params;
    }

    @Transactional
    @Override
    public HashMap<String, Object> updateRelationFieldAffiliation(String userId, String fieldId, String researchField, ArrayList<String> affiliations) {

        HashMap<String, Object> returnMap = new HashMap<String, Object>();

        HashMap<String, Object> params1 = new HashMap<String, Object>();
        params1.put("userId", userId);
        params1.put("fieldId", fieldId);
        params1.put("researchField", researchField);
        int tem1 = contrastCustomTagManualMapper.updateRelationFieldUser(params1);

        if(tem1 == 0)
        {
            // 该用户没有创建该标签
            returnMap.put("msg", "该用户没有创建该标签");
            returnMap.put("code", 500);
            returnMap.put("data", "error");
            return params1;
        }

        HashMap<String, Object> params2 = new HashMap<String, Object>();
        params2.put("userId", userId);
        params2.put("fieldId", fieldId);
        int tem2 = contrastCustomTagManualMapper.deleteRelationFieldAffiliation(params2);

        HashMap<String, Object> params3 = new HashMap<String, Object>();

        List<String> fieldIdsQuotes = new ArrayList<String>();
        for(int i=0;i<affiliations.size();i++)
        {
            fieldIdsQuotes.add(" \"" + affiliations.get(i) + "\" ");
        }
        String arrayStr = " in (" + String.join(",", fieldIdsQuotes) +  ") ";

        params3.put("userId", userId);
        params3.put("fieldId", fieldId);
        params3.put("researchField", researchField);
        params3.put("arrayStr", arrayStr);
        int tem3 = contrastCustomTagManualMapper.addRelationFieldAffiliation(params3);

        returnMap.put("msg", "");
        returnMap.put("code", 200);
        returnMap.put("data", "well done");
        return returnMap;
    }


    @Transactional
    @Override
    public HashMap<String, Object> deleteRelationFieldAffiliation(String userId, String fieldId) {

        HashMap<String, Object> returnMap = new HashMap<String, Object>();

        HashMap<String, Object> params1 = new HashMap<String, Object>();
        params1.put("userId", userId);
        params1.put("fieldId", fieldId);
        int tem1 = contrastCustomTagManualMapper.deleteRelationFieldAffiliation(params1);

        if(tem1 == 0)
        {
            returnMap.put("msg", "DELETE 机构出错");
            returnMap.put("code", 500);
            returnMap.put("data", "error");
            return params1;
        }

        HashMap<String, Object> params2 = new HashMap<String, Object>();
        params2.put("userId", userId);
        params2.put("fieldId", fieldId);
        int tem2 = contrastCustomTagManualMapper.deleteRelationFieldUser(params2);

        returnMap.put("msg", "");
        returnMap.put("code", 200);
        returnMap.put("data", "well done");
        return returnMap;
    }
}
