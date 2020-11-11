package cn.arp.trend.service.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IDEA
 * author:david
 * Date:2020/10/12
 * Time:下午1:47
 **/
public interface ContrastCustomService {

    List<HashMap<String, Object>> getAffiliations();

    List<HashMap<String, Object>> getUserTags(String userId) throws Exception;

    List<HashMap<String, Object>> getFieldAffiliations(String userId, String fieldId);

    HashMap<String, Object> addField1(String userId);

    HashMap<String, Object> addField2(String userId, String researchField);

    HashMap<String, Object> updateRelationFieldAffiliation(String userId, String fieldId, String researchField, ArrayList<String> affiliations);

    HashMap<String, Object> deleteRelationFieldAffiliation(String userId, String fieldId);
}
