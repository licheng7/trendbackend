package cn.arp.trend.service.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.arp.trend.repository.biz.manual.ContrastCustomManualMapper;
import cn.arp.trend.service.biz.ContrastCustomService;

/**
 * Created with IDEA author:licheng Date:2020/10/12 Time:下午1:48
 **/
@Service
public class ContrastCustomServiceImpl implements ContrastCustomService {

	@Resource
	private ContrastCustomManualMapper contrastCustomManualMapper;

	@Override
	public List<HashMap<String, Object>> getAffiliations() {

		List<HashMap<String, Object>> tem1 = null;
		try {
			tem1 = contrastCustomManualMapper.getAffiliations();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

		return tem1;
	}

	@Transactional
	@Override
	public List<HashMap<String, Object>> getUserTags(String userId) throws Exception {
		List<HashMap<String, Object>> userTags = contrastCustomManualMapper.getUserTags(userId);

		// new user
		HashMap<String, ArrayList<String>> researchFildMapJgbhList = new HashMap<String, ArrayList<String>>();
		if(userTags.size() == 0)
		{
			// init researchFildMapJgbhList content
			List<HashMap<String, Object>> defaultUserTags = contrastCustomManualMapper.getUserTags("default");
			for(HashMap<String, Object> oneitem : defaultUserTags)
			{
				String researchField = oneitem.get("research_field").toString();
				String filedId = oneitem.get("id").toString();

				researchFildMapJgbhList.put(researchField, new ArrayList<String>());
				List<HashMap<String, Object>> defaultUserAffiliations = contrastCustomManualMapper.getFieldAffiliations("default", filedId);

				for(HashMap<String, Object> oneUserAffiliations : defaultUserAffiliations)
				{
					researchFildMapJgbhList.get(researchField).add(oneUserAffiliations.get("jgbh").toString());
				}
			}

			// add default data to new user
			for(String researchField : researchFildMapJgbhList.keySet())
			{
				HashMap<String, Object> res = addField2(userId, researchField);
				String fieldId = res.get("id").toString();
				HashMap<String, Object> temRes = updateRelationFieldAffiliation(userId, fieldId, researchField,researchFildMapJgbhList.get(researchField));
				if((int)temRes.get("code") != 200)
				{
					throw new Exception("给新用户添加默认Tag失败!");
				}
			}

			userTags = contrastCustomManualMapper.getUserTags(userId);
		}

		return userTags;
	}

	@Override
	public List<HashMap<String, Object>> getFieldAffiliations(String userId, String fieldId) {
		return contrastCustomManualMapper.getFieldAffiliations(userId, fieldId);
	}

	@Override
	public HashMap<String, Object> addField1(String userId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", -1);
		params.put("userId", userId);
		params.put("result",  contrastCustomManualMapper.addField1(params));
		return params;
	}

	@Override
	public HashMap<String, Object> addField2(String userId, String researchField) {
		int tem1 = -1;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", -1);
		params.put("userId", userId);
		params.put("researchField", researchField);
		tem1 = contrastCustomManualMapper.addField2(params);
		params.put("result", tem1);

		return params;
	}

	@Transactional
	@Override
	public HashMap<String, Object> updateRelationFieldAffiliation(String userId, String fieldId, String researchField,
			ArrayList<String> affiliations) {

		HashMap<String, Object> returnMap = new HashMap<String, Object>();

		HashMap<String, Object> params1 = new HashMap<String, Object>();
		params1.put("userId", userId);
		params1.put("fieldId", fieldId);
		params1.put("researchField", researchField);
		int tem1 = contrastCustomManualMapper.updateRelationFieldUser(params1);

		if (tem1 == 0) {
			// 该用户没有创建该标签
			returnMap.put("msg", "该用户没有创建该标签");
			returnMap.put("code", 500);
			returnMap.put("data", "error");
			return params1;
		}

		HashMap<String, Object> params2 = new HashMap<String, Object>();
		params2.put("userId", userId);
		params2.put("fieldId", fieldId);
		int tem2 = contrastCustomManualMapper.deleteRelationFieldAffiliation(params2);

		HashMap<String, Object> params3 = new HashMap<String, Object>();

		List<String> fieldIdsQuotes = new ArrayList<String>();
		for (int i = 0; i < affiliations.size(); i++) {
			fieldIdsQuotes.add(" \"" + affiliations.get(i) + "\" ");
		}
		String arrayStr = " in (" + String.join(",", fieldIdsQuotes) + ") ";

		params3.put("userId", userId);
		params3.put("fieldId", fieldId);
		params3.put("researchField", researchField);
		params3.put("arrayStr", arrayStr);
		int tem3 = contrastCustomManualMapper.addRelationFieldAffiliation(params3);

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
		int tem1 = contrastCustomManualMapper.deleteRelationFieldAffiliation(params1);

		if (tem1 == 0) {
			returnMap.put("msg", "DELETE 机构出错");
			returnMap.put("code", 500);
			returnMap.put("data", "error");
			return params1;
		}

		HashMap<String, Object> params2 = new HashMap<String, Object>();
		params2.put("userId", userId);
		params2.put("fieldId", fieldId);
		int tem2 = contrastCustomManualMapper.deleteRelationFieldUser(params2);

		returnMap.put("msg", "");
		returnMap.put("code", 200);
		returnMap.put("data", "well done");
		return returnMap;
	}
}
